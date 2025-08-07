

const canvas = document.getElementById('dentalCanvas');
const ctx = canvas.getContext('2d');
canvas.width = 700;
canvas.height = 500;

let drawing = false;
let color = 'blue';

// Cargar la imagen de fondo (odontograma)
const backgroundImage = new Image();
backgroundImage.src = '/assets/odontograma.png';

backgroundImage.onload = () => {
    ctx.drawImage(backgroundImage, 0, 0, canvas.width, canvas.height);
};

// Funciones para dibujar en el canvas
const startDrawing = (event) => {
    drawing = true;
    ctx.beginPath();
    ctx.moveTo(event.offsetX, event.offsetY);
};

const draw = (event) => {
    if (!drawing) return;
    ctx.strokeStyle = color;
    ctx.lineWidth = 2;
    ctx.lineTo(event.offsetX, event.offsetY);
    ctx.stroke();
};

const stopDrawing = () => {
    drawing = false;
    ctx.closePath();
};

// Eventos para el mouse
canvas.addEventListener('mousedown', startDrawing);
canvas.addEventListener('mousemove', draw);
canvas.addEventListener('mouseup', stopDrawing);
canvas.addEventListener('mouseout', stopDrawing);

// Cambiar el color de los lápices
document.getElementById('bluePen').addEventListener('click', () => {
    color = 'blue';
});

document.getElementById('redPen').addEventListener('click', () => {
    color = 'red';
});

// Limpiar el canvas
document.getElementById('clearCanvas').addEventListener('click', () => {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(backgroundImage, 0, 0, canvas.width, canvas.height);
});

// Crear nuevo odontograma (limpiar y empezar de nuevo)
document.getElementById('newCanvas').addEventListener('click', () => {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(backgroundImage, 0, 0, canvas.width, canvas.height);
});

// Guardar el odontograma
document.getElementById('saveCanvas').addEventListener('click', () => {
    const dataURL = canvas.toDataURL('image/png'); // Obtener la imagen como Base64

    // Obtener los valores del formulario
    const dni = document.getElementById('dni').value;
    const fechaOdontograma = document.getElementById('fecha_odontograma').value;
    const nombre = document.getElementById('nombre').value;

    const odontogramaData = {
        dni,
        nombre,
        fecha_odontograma: fechaOdontograma,
        odontograma_imagen: dataURL
    };

    // Enviar al backend para guardar
    fetch('http://localhost:8081/api/diagnostico/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(odontogramaData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Odontograma guardado exitosamente');
        } else {
            alert('Error al guardar el odontograma');
        }
    })
    .catch(error => {
        console.error('Error al guardar el odontograma:', error);
        alert('Hubo un problema al guardar el odontograma');
    });
});

// Función para cargar el odontograma desde la base de datos
const cargarOdontograma = (id) => {
    fetch(`http://localhost:8081/api/diagnostico/get/${id}`)
    .then(response => response.json())
    .then(data => {
        if (data && data.odontograma_imagen) {
            const odontogramaImagen = data.odontograma_imagen;
            const image = new Image();
            image.src = odontogramaImagen; // Establecer la imagen recuperada

            image.onload = () => {
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(image, 0, 0, canvas.width, canvas.height);
            };
        }
    })
    .catch(error => {
        console.error('Error al cargar el odontograma:', error);
    });
};

// Por ejemplo, cargar un odontograma al inicio si tienes un ID disponible
const odontogramaId = 1; // Reemplazar con el ID adecuado
cargarOdontograma(odontogramaId);
