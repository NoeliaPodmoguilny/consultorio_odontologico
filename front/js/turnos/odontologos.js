document.addEventListener("DOMContentLoaded", () => {
    const tratamientoSelect = document.getElementById("tratamientos");
    const odontologoSelect = document.getElementById("odontologos");
    const mensajeError = document.getElementById("mensaje-error");

    tratamientoSelect.addEventListener("change", async () => {
        const tratamientoId = tratamientoSelect.value;
        if (tratamientoId) {
            await cargarOdontologosPorTratamiento(tratamientoId);
        } else {
            limpiarSelect(odontologoSelect);
        }
    });

    async function cargarOdontologosPorTratamiento(tratamientoId) {
        try {
            const response = await fetch(`http://localhost:8081/api/odontologo/listaOdontologoPorTratamiento/${tratamientoId}`);

            if (!response.ok) {
                throw new Error("Error al obtener odontólogos");
            }

            let odontologos = await response.json();

            // Validar que la respuesta sea un array y que contenga datos
            if (!Array.isArray(odontologos) || odontologos.length === 0) {
                mostrarMensajeError("Actualmente no hay odontólogos disponibles para el tratamiento seleccionado");
                limpiarSelect(odontologoSelect);
                return;
            }

            // Convertir los arrays en objetos con nombre y apellido
            const listaOdontologos = odontologos.map(odontologo => ({
                id: odontologo.dni,
                nombre: odontologo.nombre,
                apellido: odontologo.apellido
            }));

            actualizarSelectOdontologos(listaOdontologos);
            ocultarMensajeError();
        } catch (error) {
            console.error("Error al cargar los odontólogos:", error);
            alert("Ocurrió un error al obtener la lista de odontólogos");
        }
    }

    function actualizarSelectOdontologos(odontologos) {
        limpiarSelect(odontologoSelect);
        odontologos.forEach(({ id, nombre, apellido }) => {
            const nombreCompleto = `${nombre} ${apellido}`;
            odontologoSelect.appendChild(new Option(nombreCompleto, id));
        });
    }

    function limpiarSelect(select) {
        select.innerHTML = '<option value="">Seleccione un odontólogo</option>';
    }

    function mostrarMensajeError(mensaje) {
        mensajeError.textContent = mensaje;
        mensajeError.style.display = "block";
        mensajeError.className = "alert alert-danger text-center animate__animated animate__zoomIn";
    }

    function ocultarMensajeError() {
        mensajeError.textContent = "";
        mensajeError.style.display = "none";
        mensajeError.className = "";
    }
});
