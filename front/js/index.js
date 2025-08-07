document.addEventListener("DOMContentLoaded", function () {
    const token = localStorage.getItem("jwtToken");
    const username = localStorage.getItem("username");
    const userRol = localStorage.getItem("userRole");
    const formContacto = document.getElementById("formContacto");

    if (token && username) {
        const loginNav = document.getElementById("login-nav");
        const registerNav = document.getElementById("register-nav");

        if (loginNav) loginNav.style.display = "none";
        if (registerNav) registerNav.style.display = "none";

        const userNav = document.getElementById("user-nav");

        if (userNav) {
            // Crear elementos
            const userLabel = document.createElement("a");
            userLabel.classList.add("nav-link");
            userLabel.textContent = `USUARIO: ${username}`;

            const btnVerTurnos = document.createElement("button");
            btnVerTurnos.classList.add("btn-pink");
            // Dependiendo del rol del usuario, se redirige a una página diferente
            if (userRol === "PACIENTE") {
                btnVerTurnos.textContent = "Ver turnos";
                btnVerTurnos.addEventListener("click", function () {
                    window.location.href = "/html/info/paciente/listadoDeTurnosPorPaciente.html";
                });
            } else if (userRol === "ODONTOLOGO") {
                btnVerTurnos.textContent = "Ver pacientes";
                btnVerTurnos.addEventListener("click", function () {
                    window.location.href = "/html/info/odontologosPages/listaDePacientes.html";
                });
            } else {
                btnVerTurnos.textContent = "Gestión";
                btnVerTurnos.addEventListener("click", function () {
                    window.location.href = "/html/info/recepcionista/gestion.html";
                });
            }

            // Crear botón de cerrar sesión
            const btnCerrarSesion = document.createElement("button");
            btnCerrarSesion.classList.add("btn-black");
            btnCerrarSesion.textContent = "Cerrar Sesión";
            btnCerrarSesion.id = "btn-cerrar-sesion";

            // Agregar evento al botón de cerrar sesión
            btnCerrarSesion.addEventListener("click", function () {
                // console.log("click cerrar sesión");

                localStorage.removeItem("jwtToken");
                localStorage.removeItem("username");
                localStorage.removeItem("userRole");
                localStorage.clear()

                window.location.href = "/index.html";
            });

            // Agregar elementos al contenedor
            userNav.appendChild(userLabel);
            userNav.appendChild(btnVerTurnos);
            userNav.appendChild(btnCerrarSesion);
        }
    }

    // Agregar evento al formulario de contacto
    if (formContacto) {
        formContacto.addEventListener("submit", function (event) {
            event.preventDefault();

            const name = document.getElementById("name");
            const lastname = document.getElementById("surname");
            const email = document.getElementById("email");
            const message = document.getElementById("message");

            // validarFormulario(name, lastname, email, message)
            if (validarFormulario(name, lastname, email, message)) {
                setTimeout(() => {
                    window.location.href = "/index.html";
                }, 1500);
                
            }

        });
    }
    function validarFormulario(name, lastname, email, message) {
        if (name.value !== "" || lastname.value !== "" || email.value !== "" || message.value !== "") {
            
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Formulario enviado correctamente",
                showConfirmButton: false,
                timer: 1500
            });
        }
        return true;
    }
});

