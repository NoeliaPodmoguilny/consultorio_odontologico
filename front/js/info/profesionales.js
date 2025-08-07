document.addEventListener("DOMContentLoaded", function () {
  // Obtener los odontólogos
  fetch('http://localhost:8081/api/odontologo/get')
    .then(response => {
      if (!response.ok) {
        throw new Error('Error al cargar odontólogos');
      }
      return response.json();
    })
    .then(listaOdontologos => {
      console.log(listaOdontologos)

      // Recorrer la lista de odontólogos
      listaOdontologos.forEach(odontologo => {
        // Crear la tarjeta del odontólogo con su lista de tratamientos
        const tratamientosHTML = odontologo.tratamiento.map(tratamiento => `
          <li>${tratamiento.tipoTratamiento}</li>
        `).join('');
        const cardHTML = `
        <div class="card border-info m-2 w-auto">
          <div class="card-header"><h5>${odontologo.nombre} ${odontologo.apellido}</h5></div>
          <div class="card-body">
            <h6 class="card-title">Lunes a viernes</h6>
            <h6 class="card-title">8:00 a 21:00 hs</h6>
            <h5 class="card-title">TRATAMIENTOS</h5>
            <p class="card-text h6">${tratamientosHTML}</p>
          </div>
        </div>
            `;
        // Insertar la tarjeta en el contenedor
        document.getElementById("cards-container").innerHTML += cardHTML;

      })
    });
});
