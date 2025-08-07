document.addEventListener("DOMContentLoaded", () => {
    const btnRegistro = document.getElementById("btnRegistro");
    const btnIngreso = document.getElementById("btnIngreso");
    const token = localStorage.getItem("jwtToken");
    const tratamientosContainer = document.getElementById("tratamientos-container");
    const tratamientosSelect = document.getElementById("tratamientos");
    const selectRol = document.getElementById("rol");
    const adminRol = document.getElementById("admin");
    const odontologoRol = document.getElementById("odontologo");
    const rol = localStorage.getItem("userRole");

    // Si el rol es PACIENTE o no hay token, se eliminan los campos de ADMIN y ODONTOLOGO
    if (!token || (rol === "PACIENTE")) {
        adminRol?.remove();
        odontologoRol?.remove();
    }
    if (token || (rol === "ADMIN")) {
        btnIngreso.style.display = "none";
    } else {
        btnIngreso.style.display = "block";
    }

    // Si el rol es ADMIN o PACIENTE, se eliminan los campos de tratamientos de odontologo
    selectRol.addEventListener("change", ({ target }) => {
        if (target.value === "ODONTOLOGO") {
            cargarTratamientos();
        } else if (target.value === "ADMIN" || target.value === "PACIENTE") {
            tratamientosContainer.style.display = "none";
        }
    });

    // Si el rol es ODONTOLOGO, se cargan los tratamientos traidos del backend
    async function cargarTratamientos() {
        try {
            const response = await fetch("http://localhost:8081/api/tratamiento/get");
            const data = await response.json();
            tratamientosSelect.innerHTML = data.map(tratamiento =>
                `<option value="${tratamiento.idTratamiento}">${tratamiento.tipoTratamiento}</option>`
            ).join('');
            tratamientosContainer.style.display = "block";
            tratamientosContainer.className = "animate__animated animate__zoomIn";
        } catch (error) {
            console.error("Error al cargar tratamientos:", error);
        }
    }


    // Función para obtener los tratamientos seleccionados
    function obtenerTratamientosSeleccionados() {
        return [...tratamientosSelect.selectedOptions].map(opt => parseInt(opt.value));
    }

    // Función para redirigir a una URL
    function redirigir(url) {
        window.location.href = url;
    }

    // Al presionar el botón de registro, se obtienen los datos del formulario y se envían al backend
    btnRegistro?.addEventListener("click", async (event) => {
        event.preventDefault();
        // Se obtienen los datos del formulario y se convierten en un objeto
        const datos = Object.fromEntries(
            [...document.querySelectorAll("#registroForm input")].map(input => [input.id, input.value])
        );

        const rolSeleccionado = selectRol.value;  // Se toma el valor del rol directamente del select
        const idRol = obtenerIdRol(rolSeleccionado);
        if (!idRol) return Swal.fire({
            icon: "info",
            title: "Rol inválido",
            text: "Debe seleccionar el rol del usuario"
        });
        // datos viene con el rol como string, lo reemplazamos por un objeto con el idRol
        datos.rol = { idRol };

        // Si algun campo está vacío, se muestra un mensaje de alerta
        if (Object.values(datos).some(value => !value)) return Swal.fire({
            icon: "info",
            title: "Registro incompleto",
            text: "Debes completar todos los campos"
        });

        if (idRol === 2) {
            // Si el rol es ODONTOLOGO, se agrega el campo tratamiento con los tratamientos seleccionados
            datos.tratamiento = obtenerTratamientosSeleccionados().map(id => ({ idTratamiento: id }));
        }

        // Se envían los datos al backend llamando al endpoint que corresponde segun el rol seleccionado
        try {
            const response = await fetch(obtenerUrl(idRol), {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(datos),

            });
            Swal.fire({
                icon: "success",
                title: "Registro exitoso",
                text: "El usuario se ha registrado correctamente"
            });
            if (response.ok) {
                setTimeout(() => {
                    redirigir("/html/registro/registro.html");
                }, 2500);
            }
            else {
                Swal.fire({
                    icon: "error",
                    title: "Error al registrar",
                    text: "No se ha podido registrar al usuario"
                });
            }
        } catch (error) {
            console.error("Error:", error);
        }
    });

    // Función para obtener el id del rol ("hardcodeado")
    function obtenerIdRol(rolName) {
        const roles = { "ADMIN": 1, "ODONTOLOGO": 2, "PACIENTE": 3 };
        return roles[rolName] || null;
    }

    function obtenerUrl(idRol) {
        const urls = {
            1: "http://localhost:8081/api/recepcionista/save",
            2: "http://localhost:8081/api/odontologo/save",
            3: "http://localhost:8081/api/paciente/save",
        };
        return urls[idRol];
    }
});

