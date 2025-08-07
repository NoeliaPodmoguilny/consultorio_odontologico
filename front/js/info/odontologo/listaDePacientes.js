document.addEventListener("DOMContentLoaded", async function () {
    const token = localStorage.getItem("jwtToken");
    const username = localStorage.getItem("username");
    const userList = document.getElementById("userList");
    const prevBtn = document.getElementById("prevPage");
    const nextBtn = document.getElementById("nextPage");

    let currentPage = 1;
    const rowsPerPage = 10;

    if (!username) {
        console.error("No se encontró el username en localStorage");
        return;
    }

    try {
        const odontologoResponse = await fetch(`http://localhost:8081/api/odontologo/getByUsername/${username}`, {
            headers: { Authorization: `Bearer ${token}` }
        });

        if (!odontologoResponse.ok) {
            throw new Error("Error al obtener el odontólogo");
        }

        const odontologo = await odontologoResponse.json();
        console.log("odontologo", odontologo);

        const idOdontologo = odontologo.dni;

        const pacientesResponse = await fetch(`http://localhost:8081/api/paciente/getPacientesPorOdontologo/${idOdontologo}`, {
            headers: { Authorization: `Bearer ${token}` }
        });

        if (!pacientesResponse.ok) {
            throw new Error("Error al cargar pacientes");
        }
        
        pacientes = await pacientesResponse.json();
        console.log("pacientes", pacientes);
        
        renderTable();
    } catch (error) {
        console.error(error);
    }

    function formatFecha(fecha) {
        if (!fecha) return "Sin fecha";
        const [year, month, day] = fecha.split("-");
        return `${day}/${month}/${year}`;
    }

    function renderTable() {
        userList.innerHTML = "";
        const start = (currentPage - 1) * rowsPerPage;
        const end = start + rowsPerPage;
        const paginatedItems = pacientes.slice(start, end);

        paginatedItems.forEach((pacientes, index) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${start + index + 1}</td>
                <td>${pacientes.nombre || "Sin nombre"}</td>
                <td>${pacientes.apellido || "Sin apellido"}</td>
                <td>${pacientes.dni || "Sin DNI"}</td>
                <td>${formatFecha(pacientes.fechaNacimiento) || "Sin fecha"}</td>
                <td>${pacientes.correoElectronico || "Sin correo electrónico"}</td>
                <td>
                    <a class="text-decoration-none text-dark" href="/html/info/odontologosPages/historialMedicoPacientes.html">
                        <i class="bi bi-arrow-right-circle fs-5 arrowHover"></i> 
                    </a>
                </td>
            `;
            userList.appendChild(row);
        });
        updatePaginationButtons();
    }

    function updatePaginationButtons() {
        prevBtn.disabled = currentPage === 1;
        nextBtn.disabled = currentPage * rowsPerPage >= pacientes.length;
    }

    prevBtn.addEventListener("click", () => {
        if (currentPage > 1) {
            currentPage--;
            renderTable();
        }
    });

    nextBtn.addEventListener("click", () => {
        if (currentPage * rowsPerPage < pacientes.length) {
            currentPage++;
            renderTable();
        }
    });
});
