document.addEventListener("DOMContentLoaded", function () {
    fetch('http://localhost:8081/api/tratamiento/get')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar tratamientos');
            }
            return response.json();
        })
        .then(listaTratamientos => {
            const selectTratamiento = document.getElementById('tratamientos');

            // Limpiamos las opciones por defecto o las preexistentes
            selectTratamiento.innerHTML = '<option selected>Seleccione un tratamiento</option>';

            // Iterar sobre los tratamientos recibidos
            listaTratamientos.forEach(tratamiento => {

                // Crear una nueva opción para cada tratamiento
                const option = document.createElement('option');
                option.value = tratamiento.idTratamiento;
                option.textContent = tratamiento.tipoTratamiento;
                selectTratamiento.appendChild(option);
            });
        })
        .catch(error => console.error('Error al cargar tratamientos:', error));
});
