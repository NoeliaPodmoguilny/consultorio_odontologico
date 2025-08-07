// Selecciona el contenedor de las cards
const container = document.getElementById('tratamientos-container');

// Función para generar las cards de los tratamientos
function renderCards(tratamientos) {
  tratamientos.forEach(tratamiento => {
    const card = `
      <div class="col-md-4">
        <div class="card mb-4 shadow-sm animate__animated  animate__zoomIn">
          <div class="card-body bg-card-tratamientos box-shadow">
            <h6 class="card-title">${tratamiento.tipoTratamiento}</h6>
            <p class="card-text">${tratamiento.descripcion}</p>
          </div>
        </div>
      </div>
    `;
    container.innerHTML += card;
  });
}

// Función asincrónica para cargar los datos y renderizar las cards
async function fetchTratamientos() {
  try {
    const response = await fetch('http://127.0.0.1:8081/api/tratamiento/get');
    const tratamientos = await response.json();

    if (tratamientos) {
      renderCards(tratamientos);
    } else {
      console.error('Error al traer los datos');
    }
  } catch (error) {
    console.error('Error al cargar los datos:', error);
  }
}

// Llama a la función para cargar los tratamientos
fetchTratamientos();
