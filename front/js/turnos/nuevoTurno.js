document.addEventListener('DOMContentLoaded', function () {
    const formTurno = document.getElementById('formTurno');


    // Recupera el token JWT desde el localStorage
    const token = localStorage.getItem('jwtToken');
    const username = localStorage.getItem('username');



    formTurno.addEventListener('submit', async function (e) {
        e.preventDefault();  // Evita el comportamiento por defecto del formulario para que no se recargue la página
        if (!token) {
            validarFormulario();
        }

        // Datos del turno
        const tratamiento = document.getElementById('tratamientos').value;
        const odontologo = document.getElementById('odontologos').value;
        const fecha = document.getElementById('fecha').value;
        const hora = document.getElementById('hora').value;

        // Verificamos que todos los campos estén completos
        if (!tratamiento || !odontologo || !fecha || !hora) {
            Swal.fire({
                icon: "info",
                title: "Turno denegado",
                text: "Debes completar todos los campos"
            });
        } else {
            // Datos a enviar
            const turno = { tratamiento, odontologo, fecha, hora, username };


            try {
                const response = await fetch('http://localhost:8081/api/turno/save', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify(turno)
                });

                // Recuperar los elementos del formulario y limpiarlos
                document.getElementById('tratamientos').value = '';
                document.getElementById('odontologos').value = '';
                document.getElementById('fecha').value = '';
                document.getElementById('hora').value = '';

            } catch (error) {
                console.error('Error:', error);
                alert('Error al reservar el turno');
            }
        }
    });
    function validarFormulario() {
        Swal.fire({
            icon: "error",
            title: "Turno denegado",
            text: "Debes iniciar sesión con una cuenta válida para reservar un turno"
        });
    }
});
