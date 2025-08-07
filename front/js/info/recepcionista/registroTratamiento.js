document.addEventListener("DOMContentLoaded", () => {
    const btnRegistro = document.getElementById("btnRegistro");
    const token = localStorage.getItem("jwtToken");
    const tipoTratamiento = document.getElementById("tipoTratamiento");
    const descripcion = document.getElementById("descripcion");


    // Función para redirigir a una URL
    function redirigir(url) {
        window.location.href = url;
    }

    btnRegistro.addEventListener("click", async (event) => {
        event.preventDefault();

        // Validar campos vacíos
        if (!tipoTratamiento.value.trim() || !descripcion.value.trim()) {
            Swal.fire({
                icon: "info",
                title: "Campos vacíos",
                text: "Debe completar todos los campos",
            });
            return
        }
        const datos = {
            tipoTratamiento: tipoTratamiento.value.trim(),
            descripcion: descripcion.value.trim(),
        };
        try {
            const response = await fetch("http://127.0.0.1:8081/api/tratamiento/save", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    ...(token ? { Authorization: `Bearer ${token}` } : {}),
                },
                body: JSON.stringify(datos),
            });

            if (!response.ok) {
                throw new Error(`Error en la solicitud: ${response.status} ${response.statusText}`);
            } else {
                Swal.fire({
                    icon: "success",
                    title: "Tratamiento registrado",
                    text: "El tratamiento ha sido registrado correctamente",
                });
                setTimeout(() => {
                    redirigir("/html/info/recepcionista/registroNuevoTratamiento.html");
                }, 2000);
            }
        } catch (error) {
            console.error("Error:", error);
        }
    });
});
