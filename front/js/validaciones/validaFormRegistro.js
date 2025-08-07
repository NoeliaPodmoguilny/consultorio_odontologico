// VALIDACION


const nombre = document.getElementById("nombre");
const apellido = document.getElementById("apellido");
const dni = document.getElementById("dni");
const username = document.getElementById("username");
const fechaNacimiento = document.getElementById("fechaNacimiento");
const telefono = document.getElementById("telefono");
const correoElectronico = document.getElementById("correoElectronico");
const contrasenia = document.getElementById("contrasenia");

// strings
const validarString = (e) => {
    const field = e.target;
    const fieldValue = e.target.value;

    if (fieldValue.trim().length === 0) {
        field.classList.add("invalido");
        field.nextElementSibling.classList.add("error");
        field.nextElementSibling.innerText = "Debe completar el campo";
    } else if (fieldValue.trim().length > 20) {
        field.classList.add("invalido");
        field.nextElementSibling.classList.add("error");
        field.nextElementSibling.innerText = `El ${field.id} debe contener entre 3 y 15 caracteres`;
    } else {
        field.classList.remove("invalido");
        field.nextElementSibling.classList.remove("error");
        field.nextElementSibling.innerText = "";
    }
};


//correoElectronico
const validarEmail = (e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const regexp = new RegExp(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/);

    if (fieldValue.trim().length > 5 && !regexp.test(fieldValue)) {
        field.classList.add("invalido");
        field.nextElementSibling.classList.add("error");
        field.nextElementSibling.innerText = "Debe completar el campo";
    } else {
        field.classList.remove("invalido");
        field.nextElementSibling.classList.remove("error");
        field.nextElementSibling.innerText = "";
    }
};

//dni
const validarDNI = (e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const regExpDNI = new RegExp(/^\d{7,8}$/);

    if (!regExpDNI.test(fieldValue)) {
        field.classList.add("invalido");
        field.nextElementSibling.classList.add("error");
        field.nextElementSibling.innerText = "Ingrese un DNI válido";
    } else {
        field.classList.remove("invalido");
        field.nextElementSibling.classList.remove("error");
        field.nextElementSibling.innerText = "";
    }

}

//fecha de nacimiento
const validarFechaNacimiento = (e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const regExp = new RegExp(/^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|1\d|2\d|30|31)$/);

    if (!regExp.test(fieldValue)) {
        field.classList.add("invalido");
        field.nextElementSibling.classList.add("error");
        field.nextElementSibling.innerText = "Ingrese una fehca válida (AAAA-MM-DD)";
    } else {
        field.classList.remove("invalido");
        field.nextElementSibling.classList.remove("error");
        field.nextElementSibling.innerText = "";
    }
}

// telefono
const validarTelefono = (e) => {
    const field = e.target;
    const fieldValue = e.target.value;
    const regExp = new RegExp(/^\d{8,15}$/);

    if (!regExp.test(fieldValue)) {
        field.classList.add("invalido");
        field.nextElementSibling.classList.add("error");
        field.nextElementSibling.innerText = "Ingrese un numero de teléfono válido (sólo numeros)";
    } else {
        field.classList.remove("invalido");
        field.nextElementSibling.classList.remove("error");
        field.nextElementSibling.innerText = "";
    }
}

//contraseña
const validarContraseña = (e) => {
    const field = e.target;
    const fieldValue = e.target.value;

    if (fieldValue.trim().length === 0) {
        field.classList.add("invalido");
        field.nextElementSibling.classList.add("error");
        field.nextElementSibling.innerText = "Ingrese una contraseña válida\n-8 a 15 carcateres\n-1 letra mayúscula\n-1 letra minúscula\n-1 caracter especial\n-1 número"
    } else {
        field.classList.remove("invalido");
        field.nextElementSibling.classList.remove("error");
        field.nextElementSibling.innerText = "";
    }
}


// nombre
nombre.addEventListener("blur", validarString);

// apellido
apellido.addEventListener("blur", validarString);

// correoElectronico
correoElectronico.addEventListener("input", validarEmail);

//dni
dni.addEventListener("input", validarDNI);

//username
username.addEventListener("blur", validarString);

// //fecha de nacimiento
fechaNacimiento.addEventListener("blur", validarFechaNacimiento);

// //telefono
telefono.addEventListener("input", validarTelefono);

//contraseña
contrasenia.addEventListener("input", validarContraseña);