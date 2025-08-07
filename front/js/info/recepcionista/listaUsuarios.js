document.addEventListener("DOMContentLoaded", function () {
    // Función para obtener la lista de usuarios según el rol seleccionado
    const fetchUsers = async (role) => {
        try {
            const response = await fetch(`http://127.0.0.1:8081/api/${role}/get`); // Llama al endpoint según el rol
            if (!response.ok) {
                throw new Error('Error al obtener la lista de usuarios');
            }
            const usuarios = await response.json();
            renderUsers(usuarios, role); // Pasar el rol para las funciones de editar y eliminar
        } catch (error) {
            console.error('Error al cargar los usuarios:', error);
            const userList = document.getElementById('user-list');
            userList.innerHTML = '<tr><td colspan="7" class="text-center">Error al cargar usuarios</td></tr>';
        }

        console.log(role);
    };


    // Función para renderizar la lista de usuarios en la tabla
    const renderUsers = (usuarios, role) => {
        const userList = document.getElementById('user-list');
        userList.innerHTML = ''; // Limpiar la tabla antes de agregar los usuarios

        // Renderizar los usuarios obtenidos del backend
        usuarios.forEach((usuario, index) => {
            console.log(usuario); // Asegúrate de ver el objeto de usuario correctamente

            const row = document.createElement('tr');
            row.setAttribute('class', 'animate__animated animate__fadeIn')
            row.innerHTML = `
                <td>${index + 1}</td>
                <td>${usuario.nombre || 'Sin nombre'}</td>
                <td>${usuario.apellido || 'Sin apellido'}</td>
                <td>${usuario.dni || 'Sin DNI'}</td>
                <td>${usuario.rol.rol || 'Sin rol'}</td>
                <td>${usuario.fechaNacimiento || 'Sin fecha'}</td>
                <td>${usuario.correoElectronico || 'Sin correo'}</td>
                <td>
                    <button class="btn btn-info btn-sm" onclick="editarUsuario(${usuario.dni}, '${role}')">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="validarEliminacion(${usuario.dni}, '${role}')">Eliminar</button>
                </td>
            `;

            userList.appendChild(row);
        });
    };

    // Event listeners para los botones de rol
    document.getElementById('btnOdontologo').addEventListener('click', () => fetchUsers('odontologo'));
    document.getElementById('btnRecepcionista').addEventListener('click', () => fetchUsers('recepcionista'));
    document.getElementById('btnPaciente').addEventListener('click', () => fetchUsers('paciente'));

});

// Función para editar usuario
async function editarUsuario(dni, role) {
    console.log(`Editar usuario con ID: ${dni}`);

    // Obtener los datos del usuario para editar
    try {
        const response = await fetch(`http://127.0.0.1:8081/api/${role}/get/${dni}`);
        if (!response.ok) {
            throw new Error('Error al obtener los datos del usuario');
        }
        const usuario = await response.json();

        // Llenar el formulario de edición con los datos del usuario
        document.getElementById('edit-nombre').value = usuario.nombre;
        document.getElementById('edit-apellido').value = usuario.apellido;
        document.getElementById('edit-email').value = usuario.correoElectronico;
        document.getElementById('edit-fechaNacimiento').value = usuario.fechaNacimiento;
        document.getElementById('edit-dni').value = usuario.dni; // Guardar el DNI para la actualización

        // Mostrar el formulario de edición
        document.getElementById('edit-modal').style.display = 'block';

        // Agregar un listener para el formulario
        document.getElementById('edit-form').onsubmit = async (event) => {
            event.preventDefault(); // Prevenir el envío del formulario
            console.log("Preparando datos.....", document.getElementById("btnEditar"));


            // Crear el objeto de usuario con los nuevos valores
            const updatedUser = {
                nombre: document.getElementById('edit-nombre').value,
                apellido: document.getElementById('edit-apellido').value,
                correoElectronico: document.getElementById('edit-email').value,
                fechaNacimiento: document.getElementById('edit-fechaNacimiento').value,
                dni: document.getElementById('edit-dni').value // Asegúrate de incluir el DNI
            };
            console.log(updatedUser);


            // Enviar la solicitud PUT para actualizar el usuario
            try {
                const updateResponse = await fetch(`http://127.0.0.1:8081/api/${role}/update/${dni}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updatedUser)
                });

                if (!updateResponse.ok) {
                    throw new Error('Error al actualizar el usuario');
                } else {
                    Swal.fire({
                        icon: "success",
                        title: "Usuario actualizado",
                        text: "El usuario ha sido actualizado correctamente",
                    });
                    setTimeout(() => {
                        window.location.href = "/html/info/recepcionista/listaUsuarios.html";
                    }, 2000);
                }
                // cerrarFormulario(); 
                // //location.reload();
                fetchUsers(role);
            } catch (error) {
                console.error('Error al guardar cambios:', error);
            }
        };
    } catch (error) {
        console.error('Error al editar usuario:', error);
    }
}

// Función para cerrar el formulario de edición
function cerrarFormulario() {
    document.getElementById('edit-modal').style.display = 'none';
}

// Función para eliminar usuario
async function eliminarUsuario(dni, role) {

    try {
        const response = await fetch(`http://127.0.0.1:8081/api/${role}/delete/${dni}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Error al eliminar el usuario');
        } else {
            Swal.fire({
                icon: "success",
                title: "Usuario eliminado",
                text: "El usuario ha sido eliminado correctamente",
            });
            setTimeout(() => {
                window.location.href = "/html/info/recepcionista/listaUsuarios.html";
            }, 2000);
        }
        fetchUsers(role);
    } catch (error) {
        console.error('Error al eliminar usuario:', error);
    }

};

function validarEliminacion(dni, role) {
    Swal.fire({
        title: "¿Está seguro de eliminar el usuario ",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si, eliminar"
    }).then((result) => {
        if (result.isConfirmed) {
            eliminarUsuario(dni, role)
        }
    });
};
