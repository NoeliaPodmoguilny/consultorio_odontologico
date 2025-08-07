document.addEventListener("DOMContentLoaded", function () {

    const fechaInput = document.getElementById("fecha");
    // Obtiene la fecha actual en formato ISO (yyyy-mm-dd) y la separa en dos partes, ejemplo "2021-08-25T00:00:00.000Z" -> ["2021-08-25", "00:00:00.000Z"]
    const hoy = new Date().toISOString().split("T")[0];

    fechaInput.setAttribute("min", hoy);
    // Si la fecha seleccionada es un fin de semana, muestra un mensaje de alerta y limpia el campo
    fechaInput.addEventListener("change", function () {
        if (!this.value) return; 

        const fechaSeleccionada = new Date(this.value);

        // Verificar si la fecha es válida antes de continuar
        if (isNaN(fechaSeleccionada.getTime())) return;

        const diaSemana = fechaSeleccionada.getDay();

        if (diaSemana === 5 || diaSemana === 6) {
            Swal.fire({
                icon: "error",
                title: "Turno no disponible",
                text: "Los turnos no están disponibles los fines de semana",
            });
            this.value = "";
        }
    });

    // Generar horarios en intervalos de 30 minutos
    const horaSelect = document.getElementById("hora");
    for (let h = 8; h < 20 || (h === 20 && 30); h++) {
        for (let m = 0; m < 60; m += 30) {
            // PadStart agrega un 0 al principio si el string tiene menos de 2 caracteres
            const hora = `${String(h).padStart(2, "0")}:${String(m).padStart(2, "0")}:00`;
            const option = new Option(hora.slice(0, 5), hora); // Muestra HH:mm pero almacena HH:mm:ss para enviar al backend
            horaSelect.appendChild(option);
        }
    }
});