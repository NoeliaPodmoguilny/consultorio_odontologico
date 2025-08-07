document.addEventListener("DOMContentLoaded", async function () {
    const token = localStorage.getItem("jwtToken");
    const username = localStorage.getItem("username");
    const turnosList = document.getElementById("turnosList");
    const prevBtn = document.getElementById("prevPage");
    const nextBtn = document.getElementById("nextPage");
    let turnos = [];
    let currentPage = 1;
    const rowsPerPage = 10;

    if (!username) {
        console.error("No se encontró el username en localStorage");
        return;
    }
    console.log("username", username);

    try {
        const pacienteResponse = await fetch(`http://localhost:8081/api/paciente/getPacienteByUsername/${username}`, {
            headers: { Authorization: `Bearer ${token}` }
        });

        if (!pacienteResponse.ok) {
            throw new Error("Error al obtener el odontólogo");
        }
        console.log("pacienteResponse", pacienteResponse);

        const paciente = await pacienteResponse.json();
        console.log("paciente", paciente);

        const idPaciente = paciente.dni;

        const turnosResponse = await fetch(`http://localhost:8081/api/turno/getTurnosPorPaciente/${idPaciente}`, {
            headers: { Authorization: `Bearer ${token}` }
        });

        if (!turnosResponse.ok) {
            throw new Error("Error al cargar turnos");
        }

        turnos = await turnosResponse.json();
        renderTable();
    } catch (error) {
        console.error(error);
    }

    function formatFecha(fechaTurno) {
        if (!fechaTurno) return "Sin fecha";
        const [year, month, day] = fechaTurno.split("-");
        return `${day}/${month}/${year}`;
    }

    function renderTable() {
        turnosList.innerHTML = "";
        const start = (currentPage - 1) * rowsPerPage;
        const end = start + rowsPerPage;
        const paginatedItems = turnos.slice(start, end);

        paginatedItems.forEach((turno, index) => {
            const row = document.createElement("tr");
            const estadoConfirmado = turno.estado === "CONFIRMADO"; // Verificamos el estado
            row.innerHTML = `
                <td>${start + index + 1}</td>
                <td>${formatFecha(turno.fechaTurno) || "Sin fecha"}</td>
                <td>${turno.horaTurno || "Sin hora"}</td>
                <td>${turno.odontologo.nombre + " " + turno.odontologo.apellido || "Sin datos del odontólogo"}</td>
                <td>${turno.tratamiento.tipoTratamiento || "Sin datos del tratamiento"}</td>
                <td>${turno.estado || "Sin datos del estado"}</td>
                <td>
                    <button class="btn ${estadoConfirmado ? "btn-secondary" : "btn-success"} btn-sm confirmar-btn" data-id="${turno.idTurno}" ${estadoConfirmado ? "disabled" : ""}>
                        ${estadoConfirmado ? "Confirmado" : "Confirmar"}
                    </button>
                    <button class="btn btn-sm btn-danger eliminar-btn" data-id="${turno.idTurno}">Eliminar</button>
                </td>
            `;
            turnosList.appendChild(row);
        });
        updatePaginationButtons();
    }


    // Agrega eventos a los botones después de renderizar la tabla
    document.querySelectorAll(".confirmar-btn").forEach(button => {
        button.addEventListener("click", (event) => {
            const idTurno = event.target.getAttribute("data-id");
            estadoTurno(idTurno, "confirmar");
        });
    });

    document.querySelectorAll(".eliminar-btn").forEach(button => {
        button.addEventListener("click", (event) => {
            const idTurno = event.target.getAttribute("data-id");
            estadoTurno(idTurno, "eliminar");
        });
    });
    updatePaginationButtons();
    // }

    // FUNCION PARA CONFIRMAR O ELIMINAR TURNO
    async function estadoTurno(idTurno, accion) {

        if (accion === "confirmar") {
            console.log("confirmar");
            try {
                const response = await fetch(`http://localhost:8081/api/turno/updateEstado/${idTurno}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                });
                if (!response.ok) {
                    throw new Error("Error al actualizar el turno");
                } else {
                    Swal.fire({
                        icon: "success",
                        title: "Turno confirmado",
                        text: "El turno ha sido confirmado correctamente",
                    });
                    setTimeout(() => {
                        window.location.href = "/html/info/paciente/listadoDeTurnosPorPaciente.html";
                    }, 2000);
                }
            } catch (error) {
                console.error(error);
            }
        } else if (accion === "eliminar") {
            try {
                const response = await fetch(`http://localhost:8081/api/turno/delete/${idTurno}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                if (!response.ok) {
                    Swal.fire({
                        icon: "error",
                        title: "Error",
                        text: "Error al eliminar el turno",
                    });
                } else {
                    Swal.fire({
                        icon: "success",
                        title: "Turno eliminado",
                        text: "El turno ha sido eliminado correctamente",
                    });
                    setTimeout(() => {
                        window.location.href = "/html/info/paciente/listadoDeTurnosPorPaciente.html";
                    }, 2000);
                }
            } catch (error) {
                console.error(error);
            }
        }
    }
    // FUNCIONES PARA LOS BOTONES DE CAMBIO DE PAGINA
    function updatePaginationButtons() {
        prevBtn.disabled = currentPage === 1;
        nextBtn.disabled = currentPage * rowsPerPage >= turnos.length;
    }

    prevBtn.addEventListener("click", () => {
        if (currentPage > 1) {
            currentPage--;
            renderTable();
        }
    });

    nextBtn.addEventListener("click", () => {
        if (currentPage * rowsPerPage < turnos.length) {
            currentPage++;
            renderTable();
        }
    });
});
