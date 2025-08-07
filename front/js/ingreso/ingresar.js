btnIngreso.addEventListener("click", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("contraseña").value;

    if (!username || !password) {
        Swal.fire({
            icon: "info",
            title: "¡Atención!",
            text: "Debe ingresar un usuario y una contraseña"
        });
    }

    const dataUsu = {
        username: username,
        password: password,
    };

    let url = "http://localhost:8081/api/auth/login";

    try {
        const response = await fetch(url, {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(dataUsu),
        });

        if (!response.ok && (username!=="" || password!=="")) {
            Swal.fire({
                icon: "error",
                title: "¡Error!",
                text: "Usuario o contraseña incorrectos"
            });
        }
        const result = await response.json();
        const token = result.jwt;

        // Si se obtiene un token, almacenarlo en localStorage
        // Función para obtener el rol del token decodificado 
        function getRoleFromToken(token) {
            if (!token) return null;
            try {
                // Se obtiene el payload del token, se decodifica y se parsea a JSON (atob: decodifica cadenas de datos codificadas en base64)
                const payload = JSON.parse(atob(token.split('.')[1]));
                // Se obtiene el rol del token, se separan los roles por coma y se busca el que empiece con "ROLE_" y se le quita ese prefijo
                return payload.authorities?.split(",").find(r => r.startsWith("ROLE_"))?.replace("ROLE_", "") || null;
            } catch (error) {
                console.error("Error al decodificar el token:", error);
                return null;
            }
        }
        const userRol = getRoleFromToken(token);
        if (userRol) localStorage.setItem("userRole", userRol);

        if (token) {
            // Almacenar el token y el nombre del usuario en localStorage
            localStorage.setItem("jwtToken", token);
            localStorage.setItem("username", dataUsu.username);

            if (userRol == "ODONTOLOGO") {
                window.location.href = "/html/info/odontologosPages/odontologosIndex.html";
            } else {
                // Redirigir a la página principal
                window.location.href = "/";
            }
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Error en la autenticación: " + error.message);
    }
});
