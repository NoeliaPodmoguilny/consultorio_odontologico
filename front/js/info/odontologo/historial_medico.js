document.addEventListener("DOMContentLoaded", () => {
    // Obtener elementos
    // Datos sobre info del paciente
    const buscarDniInput = document.getElementById("buscar_dni");
    const buscarDniInput2 = document.getElementById("buscar_dni2");

    const nombreInput = document.getElementById("nombre");
    const apellidoInput = document.getElementById("apellido");
    const fechaNacimientoInput = document.getElementById("fecha_nacimiento");
    const edad = document.getElementById("edad");
    const dniInput = document.getElementById("dni");
    const emailInput = document.getElementById("email");
    const telefonoInput = document.getElementById("telefono");

    // Datos sobre el género
    const generoSelect = document.getElementById("genero");

    // Datos del domicilio
    const calleInput = document.getElementById("calle");
    const alturaInput = document.getElementById("altura");
    const ciudadInput = document.getElementById("ciudad");
    const provinciaInput = document.getElementById("provincia");

    // Datos sobre salud general
    const estadoSaludSelect = document.getElementById("estado_salud");
    const pesoInput = document.getElementById("peso");
    const alturaSaludInput = document.getElementById("altura");

    // Antecedentes Médicos Específicos

    // FUNCIÓN PARA OBTENER LOS VALORES DE LOS CHECKBOX
    function obtenerValoresCheckbox(nombre) {
        let cb = document.querySelectorAll(`input[name=${nombre}]:checked`)
        let valoresSeleccionadosCB = Array.from(cb).map(checkbox => checkbox.value);
        console.log(valoresSeleccionadosCB);
        return valoresSeleccionadosCB
    }

    // Obtener valores de los checkbox
    const antecedentesMedicos = obtenerValoresCheckbox("condiciones");
    const habitos = obtenerValoresCheckbox("habitos");
    const historialFamiliar = obtenerValoresCheckbox("historial_familiar");

    //Otros datos
    const medicamentos = document.getElementById("medicamentos")
    const otrosHabitos = document.getElementById("otros_habitos")
    const otrosHistorialFamiliar = document.getElementById("otros_historial_familiar")

    // Embarazo
    const embarazoSelect = document.getElementById("embarazo");

    // Seguro Médico
    const seguroMedicoSelect = document.getElementById("seguro_medico");
    const nombreSeguroMedico = document.getElementById("seguro_medico_descripcion")
    const comentariosAdicional = document.getElementById("comentarios")

    // Contacto de emergencia
    const nombreContactoEmergencia = document.getElementById("contacto_nombre")
    const telefonoContactoEmergencia = document.getElementById("contacto_telefono")
    const relacionContactoEmergencia = document.getElementById("contacto_descripcion")


    // Datos del diagnostico y tratamientos
    // Diagnostico
    const diagnostico = document.getElementById("diagnostico")

    //Fecha del diagnostico
    const fechaDiagnostico = document.getElementById("fecha_diagnostico")

    // Tratamientos
    const tratamientosSelect = document.getElementById("tratamientos");
    cargarTratamientos()


    // Botones
    const btnVolver = document.getElementById("btnVolver");
    const btnGuardar = document.getElementById("btnGuardar");
    // const btnEditar = document.getElementById("btnEditar");
    const btnDescargar = document.getElementById("btnDescargar");
    // const btnOdontograma = document.getElementById("btnOdontograma");
    const btnBuscarDni = document.getElementById("btnBuscarDni");
    const btnconhm = document.getElementById("btnconhm");



    // FUNCIÓN PARA CARGAR LA LISTA DE TRATAMIENTOS DISPONIBLES EN LA BD
    async function cargarTratamientos() {
        try {
            const response = await fetch("http://localhost:8081/api/tratamiento/get");
            const data = await response.json();
            tratamientosSelect.innerHTML = data.map(tratamiento =>
                `<option value="${tratamiento.idTratamiento}">${tratamiento.tipoTratamiento}</option>`
            ).join('');
        } catch (error) {
            console.error("Error al cargar tratamientos:", error);
        }
    }

    // Evento para buscar el paciente x DNI
    btnBuscarDni.addEventListener("click", () => {
        const dni = buscarDniInput.value;
        if (dni.length >= 7) {
            completarDatosPaciente(dni);
        } else {
            Swal.fire({
                icon: "error",
                title: "DNI inválido",
                text: "El DNI debe tener al menos 7 caracteres",
            });
        }
    });

    btnconhm.addEventListener("click", () => {
        const dni = buscarDniInput2.value;
        if (dni.length >= 7) {
            buscarhm(dni);
        }else {
            Swal.fire({
                icon: "error",
                title: "DNI inválido",
                text: "El DNI debe tener al menos 7 caracteres",
            });
        }
    });
    async function completarDatosPaciente(dni) {        
        try {
            // Llamada al endpoint de paciente para obtener los datos
            const pacienteResponse = await fetch(`http://localhost:8081/api/paciente/get/${dni}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    // 'Authorization': `Bearer ${token}`
                },
            });
            if(!pacienteResponse.ok) throw new Error("Error al obtener los datos del paciente");
            console.log("PACIENTE: ", pacienteResponse);
            
            const respuestaData = await pacienteResponse.json();
            console.log("Respuesta historial med: "+ respuestaData);
            

            // Llenar los campos con los datos del paciente
            nombreInput.value = respuestaData.nombre || "";
            apellidoInput.value = respuestaData.apellido || "";
            fechaNacimientoInput.value = respuestaData.fechaNacimiento || "";
            edad.value = calcularEdad(respuestaData.fechaNacimiento);
            dniInput.value = respuestaData.dni || "";
            emailInput.value = respuestaData.correoElectronico || "";
            telefonoInput.value = respuestaData.telefono || "";
        }
        catch(error){
                console.error("Error al obtener los datos:", error);
            }
        }

    // Función para completar los datos del usuario por dni
    async function buscarhm(dni) {        
        try {
            // Llamada al endpoint de paciente para obtener los datos
            const pacienteResponse = await fetch(`http://localhost:8081/api/historialMedico/get/${dni}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    // 'Authorization': `Bearer ${token}`
                },
            });
            if(!pacienteResponse.ok) throw new Error("Error al obtener los datos del paciente");
            console.log("PACIENTE: ", pacienteResponse);
            
            const respuestaData = await pacienteResponse.json();
            console.log("Respuesta historial med: "+ respuestaData);
            

            // Llenar los campos con los datos del paciente
            nombreInput.value = respuestaData.paciente?.nombre || "";
            apellidoInput.value = respuestaData.paciente?.apellido || "";
            fechaNacimientoInput.value = respuestaData.paciente?.fechaNacimiento || "";
            edad.value = calcularEdad(respuestaData.paciente?.fechaNacimiento);
            dniInput.value = respuestaData.paciente?.dni || "";
            emailInput.value = respuestaData.paciente?.correoElectronico || "";
            telefonoInput.value = respuestaData.paciente?.telefono || "";


            // Llenar los campos del historial médico. 
            // Domicilio
            generoSelect.value = respuestaData.genero || "";
            calleInput.value = respuestaData.calle || "";
            alturaInput.value = respuestaData.altura || "";
            ciudadInput.value = respuestaData.ciudad || "";
            provinciaInput.value = respuestaData.provincia || "";

            // Salud general
            estadoSaludSelect.value = respuestaData.estadoSalud || "";
            pesoInput.value = respuestaData.peso || "";
            alturaSaludInput.value = respuestaData.alturaCm || "";

            // Antecedentes Médicos Específicos
            // FUNCIÓN PARA SETEAR LOS VALORES DE LOS CHECKBOX
            function setearValoresCheckbox(nombre, listaCheckbox) {
                document.querySelectorAll(`input[name="${nombre}"]`).forEach(checkbox => {
                    checkbox.checked = listaCheckbox.includes(checkbox.value);
                });
            }

            // Obtener valores de los checkbox del paciente
            setearValoresCheckbox("condiciones", respuestaData.condiciones || []);
            setearValoresCheckbox("habitos", respuestaData.habitos || []);
            setearValoresCheckbox("historial_familiar", respuestaData.historialFamiliar || []);

            //Otros datos
            medicamentos.value = respuestaData.medicamentos || "";
            otrosHabitos.value = respuestaData.otrosHabitos || "";
            otrosHistorialFamiliar.value = respuestaData.otrosHistorialFamiliar || "";

            // Embarazo
            embarazoSelect.value = respuestaData.embarazo || "";

            // Seguro Médico
            seguroMedicoSelect.value = respuestaData.seguroMedico || "";
            nombreSeguroMedico.value = respuestaData.seguroMedicoDescripcion || "";
            comentariosAdicional.value = respuestaData.comentarios || "";

            // Contacto de emergencia
            nombreContactoEmergencia.value = respuestaData.contactoNombre || "";
            telefonoContactoEmergencia.value = respuestaData.contactoTelefono || "";
            relacionContactoEmergencia.value = respuestaData.contactoRelacionConPaciente || "";

            // Datos del diagnostico y tratamientos
            // Diagnostico
            diagnostico.value = respuestaData.diagnostico?.tipoDiagnostico || "";
            //Fecha del diagnostico
            fechaDiagnostico.value = respuestaData.diagnostico?.fechaDiagnostico || "";
            // Tratamientos 
            
            tratamientosPaciente = respuestaData.diagnostico?.tratamientos?.map(t => t.idTratamiento);
            if (tratamientosPaciente) {
                marcarTratamientosSeleccionados(tratamientosPaciente)
            } else {
                cargarTratamientos();
            }
             // FUNCIÓN PARA MARCAR TRATAMIENTOS SELECCIONADOS DEL PACIENTE
            function marcarTratamientosSeleccionados(tratamientosGuardados) {
                [...tratamientosSelect.options].forEach(option => {
                    option.selected = tratamientosGuardados.includes(parseInt(option.value));
                });
            }
        } catch (error) {
            console.error("Error al obtener los datos:", error);
        }
    }

    // Función para calcular la edad a partir de la fecha de nacimiento
    function calcularEdad(fechaNacimiento) {
        const hoy = new Date();
        const nacimiento = new Date(fechaNacimiento);
        if (isNaN(nacimiento)) return console.log("no existe fecha");
        const diferencia = hoy - nacimiento;
        return Math.floor(diferencia / (1000 * 60 * 60 * 24 * 365.25));
    }
    // Evento para actualizar la edad al cambiar la fecha de nacimiento
    fechaNacimientoInput.addEventListener("change", () => {
        edad.value = calcularEdad(fechaNacimientoInput.value);
    });

    // Función para obtener y guardar en la BD los tratamientos seleccionados
    function obtenerTratamientosSeleccionados() {
        return [...tratamientosSelect.selectedOptions].map(opt => parseInt(opt.value));
    }
    const tratamientosSeleccionados = obtenerTratamientosSeleccionados().map(id => ({ idTratamiento: id }));
    console.log("Console.log de Tratamientos seleccionados: ", tratamientosSeleccionados);
    
    // ACCIÓN PARA GUARDAR EL HISTORIAL MÉDICO
    if (btnGuardar) {
        btnGuardar.addEventListener("click", async function (event) {
            event.preventDefault();

            // Obtener valores de los checkbox
            const antecedentesMedicos = obtenerValoresCheckbox("condiciones");
            const habitos = obtenerValoresCheckbox("habitos");
            const historialFamiliar = obtenerValoresCheckbox("historial_familiar");

            const datosForm = {
                pacienteDNI: dniInput.value,
                edad: edad.value,
                // Datos sobre el género
                genero: generoSelect.value,
                // Datos del domicilio
                calle: calleInput.value,
                altura: alturaInput.value,
                ciudad: ciudadInput.value,
                provincia: provinciaInput.value,
                // Datos sobre salud general
                estadoSalud: estadoSaludSelect.value,
                peso: pesoInput.value,
                alturaCm: alturaSaludInput.value,
                // Antecedentes Médicos Específicos
                condiciones: antecedentesMedicos,
                habitos: habitos,
                historialFamiliar: historialFamiliar,
                //Otros datos
                medicamentos: medicamentos.value,
                otrosHabitos: otrosHabitos.value,
                otrosHistorialFamiliar: otrosHistorialFamiliar.value,
                // Embarazo
                embarazo: embarazoSelect.value,
                // Seguro Médico
                seguroMedico: seguroMedicoSelect.value,
                seguroMedicoDescripcion: nombreSeguroMedico.value,
                comentarios: comentariosAdicional.value,
                // Contacto de emergencia
                contactoNombre: nombreContactoEmergencia.value,
                contactoTelefono: telefonoContactoEmergencia.value,
                contactoRelacionConPaciente: relacionContactoEmergencia.value,
                // Datos del diagnostico y tratamientos
                tipoDiagnostico: diagnostico.value,
                //Fecha del diagnostico
                fechaDiagnostico: fechaDiagnostico.value,
                // Tratamientos
                tratamientos: tratamientosSeleccionados
            }
            console.log("Datos Historial Medico a guardar: ", datosForm);
            try {
                const response = await fetch('http://localhost:8081/api/historialMedico/save', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        // 'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify(datosForm)
                });

                if (!response.ok) {
                    throw new Error('Error al guardar el historial médico');
                }else{
                    Swal.fire({
                        icon: "success",
                        title: "Historial Médico guardado",
                        text: "El historial médico ha sido guardado correctamente",
                    });
                }

            } catch (error) {
                console.error('Error:', error);
                alert('Error al guardar los datos');
            }
        });
    }

    // FUNCIONES DE LOS BOTONES
    if (btnVolver) {
        btnVolver.addEventListener("click", async function (event) {
            event.preventDefault();
            window.location.href = "/html/info/odontologosPages/odontologosIndex.html";
        });
    }

    if (btnDescargar) {
        btnDescargar.addEventListener("click", async function (event) {
            event.preventDefault();
            window.print();
        });
    }
    
});

