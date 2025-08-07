document.addEventListener("DOMContentLoaded", async function () {
    const token = localStorage.getItem("jwtToken");
    const userList = document.getElementById("userList");
    const prevBtn = document.getElementById("prevPage");
    const nextBtn = document.getElementById("nextPage");
    let turnos = [];
    let currentPage = 1;
    const rowsPerPage = 10; // CANTIDAD DE FILAS DE TURNOS QUE SE MUESTRAN

    // TRAEMOS LOS TURNOS RESERVADOS
    try {
        const turnosResponse = await fetch(`http://localhost:8081/api/turno/getTurnos`, {
            headers: { Authorization: `Bearer ${token}` }
        });

        if (!turnosResponse.ok) {
            throw new Error("Error al cargar turnos");
        }
        turnos = await turnosResponse.json();
        console.log("turnos", turnos);
        // LLAMAMOS A LA FUNCION PARA RENDERIZAR LOS DATOS
        renderTable();
    } catch (error) {
        console.error(error);
    }

    // FORMATEAR FECHA
    function formatFecha(fecha) {
        if (!fecha) return "Sin fecha";
        const [year, month, day] = fecha.split("-");
        return `${day}/${month}/${year}`;
    }

    // FUNCION PARA RENDERIZAR LOS TURNOS
    function renderTable() {
        userList.innerHTML = "";
        const start = (currentPage - 1) * rowsPerPage;
        const end = start + rowsPerPage;
        const paginatedItems = turnos.slice(start, end);
        paginatedItems.forEach((turnos, index) => {
            const row = document.createElement("tr");
            const estadoConfirmado = turnos.estado === "CONFIRMADO"; // Verificamos el estado
            row.innerHTML = `
                <td>${start + index + 1}</td>
                <td>${turnos.paciente.nombre + " " + turnos.paciente.apellido || "Sin nombre"}</td>
                <td>${turnos.paciente.dni || "Sin DNI"}</td>
                <td>${turnos.paciente.correoElectronico || "Sin correo electrónico"}</td>
                <td>${turnos.paciente.telefono || "Sin teléfono"}</td>
                <td>${turnos.odontologo.nombre + " " + turnos.odontologo.apellido || "Sin odontologo"}</td>
                <td>${formatFecha(turnos.fechaTurno) || "Sin fecha"}</td>
                <td>${turnos.horaTurno || "Sin horario"}</td>
                <td>${turnos.estado || "Sin estado"}</td>
                <td>
                    <button class="btn ${estadoConfirmado ? "btn-secondary" : "btn-success"} btn-sm confirmar-btn" data-id="${turnos.idTurno}" ${estadoConfirmado ? "disabled" : ""}>
                        ${estadoConfirmado ? "Confirmado" : "Confirmar"}
                    </button>
                    <button class="btn btn-sm btn-danger eliminar-btn" data-id="${turnos.idTurno}">Eliminar</button>
                </td>
            `;
            userList.appendChild(row);
        });
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
    }

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
                        window.location.href = "/html/info/recepcionista/listadoDeTurnos.html";
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
                    throw new Error("Error al eliminar el turno");
                } else {
                    Swal.fire({
                        icon: "success",
                        title: "Turno eliminado",
                        text: "El turno ha sido eliminado correctamente",
                    });
                    setTimeout(() => {
                        window.location.href = "/html/info/recepcionista/listadoDeTurnos.html";
                    }, 2000);
                }
            } catch (error) {
                console.error(error);
            }
        }
    }

    // FUNCION PARA ACTUALIZAR LA PAGINA DE TURNOS (SE MUESTRAN DE A 10)
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
