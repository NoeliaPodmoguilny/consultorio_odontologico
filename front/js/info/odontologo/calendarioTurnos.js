document.addEventListener('DOMContentLoaded', async function () {
    const calendarEl = document.getElementById('calendar');
    const username = localStorage.getItem("username");
    const detailsEl = document.getElementById('turno-details');
    // Verificar si los turnos ya están almacenados en localStorage
    let turnosCache = JSON.parse(localStorage.getItem("turnos"));

    // Inicialización de FullCalendar
    const calendar = new FullCalendar.Calendar(calendarEl, {
        themeSystem: 'bootstrap',
        locale: 'es',
        initialView: 'dayGridMonth',
        selectable: true,
        scrollTime: "08:00:00",
        nowIndicator: true,
        eventLimit: true,
        aspectRatio: 2,
        eventOverlap: false,
        height: "80vh",
        expandRows: true,
        businessHours: [
            {
                daysOfWeek: [1, 2, 3, 4, 5], // Días laborales
                startTime: '08:00', // Hora de inicio
                endTime: '21:00'  // Hora de fin  
            },
            {
                daysOfWeek: [6],
                startTime: '09:00',
                endTime: '12:00'
            }
        ],
        headerToolbar: {
            left: 'prevCustom,nextCustom today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        customButtons: {
            prevCustom: {
                text: "<",
                click: function () {
                    calendar.prev();
                }
            },
            nextCustom: {
                text: ">",
                click: function () {
                    calendar.next();
                }
            }
        },
        initialView: 'dayGridMonth',
        events: async function (info, successCallback, failureCallback) {
            if (!turnosCache) {
                // Si no están en localStorage, obtenemos los turnos del backend
                turnosCache = await obtenerTurnos(username);
                localStorage.setItem("turnos", JSON.stringify(turnosCache)); // Se almacenan los turnos en localStorage
            }
            successCallback(turnosCache);
        },
        eventClick: function (info) {
            mostrarDetallesTurno(info.event.extendedProps);
        },
        eventLimit: true,
        editable: false,
        droppable: false
    });

    calendar.render();

    // Obtener los turnos desde el servidor
    async function obtenerTurnos(username) {
        try {
            
            const response = await fetch(`http://localhost:8081/api/turno/getTurnosPorOdontologo/${username}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error("Error al obtener los turnos");
            }

            const turnos = await response.json();
            console.log("turnos: ", turnos);

            // Mapeamos los turnos y generamos el objeto para FullCalendar
            return turnos.map(turno => {
                // Verificamos que cada turno tenga la información necesaria
                if (!turno.fechaTurno || !turno.horaTurno) {
                    console.error("Turno con datos incompletos:", turno);
                    return null;
                }

                // Construimos la fecha y hora correctamente 
                const fechaHora = `${turno.fechaTurno}T${turno.horaTurno}`;

                // Devolvemos el evento para FullCalendar
                return {
                    title: `Paciente: ${turno.paciente.nombre} ${turno.paciente.apellido}`,
                    start: fechaHora,
                    extendedProps: {
                        nombre: turno.paciente.nombre,
                        apellido: turno.paciente.apellido,
                        dni: turno.paciente.dni,
                        email: turno.paciente.correoElectronico,
                        telefono: turno.paciente.telefono,
                        tratamiento: turno.tratamiento.tipoTratamiento,
                        odontologo: `${turno.odontologo.nombre} ${turno.odontologo.apellido}`,
                        fechaTurno: turno.fechaTurno,
                        horaTurno: turno.horaTurno,
                        estado: turno.estado

                    }
                };
            }).filter(turno => turno !== null);
        } catch (error) {
            console.error("Error al cargar los turnos:", error);
            return [];
        }
    }

    // Mostrar detalles del turno seleccionado
    function mostrarDetallesTurno(turno) {
        detailsEl.style.display = 'block';
        document.getElementById('detalle-nombre').textContent = turno.nombre;
        document.getElementById('detalle-apellido').textContent = turno.apellido;
        document.getElementById('detalle-dni').textContent = turno.dni;
        document.getElementById('detalle-email').textContent = turno.email;
        document.getElementById('detalle-telefono').textContent = turno.telefono;
        document.getElementById('detalle-tratamiento').textContent = turno.tratamiento;
        document.getElementById('detalle-odontologo').textContent = turno.odontologo;
        document.getElementById('detalle-dia').textContent = turno.fechaTurno;
        document.getElementById('detalle-hora').textContent = turno.horaTurno;
        document.getElementById('detalle-estado').textContent = turno.estado;

    }
});

