document.addEventListener("DOMContentLoaded", function () {
  const modal = document.getElementById("ventanaModal");
  const fechaTurno = document.getElementById("fechaTurno");
  const horaTurno = document.getElementById("horaTurno");
  const tratamientos = document.getElementById("tratamientos");
  const odontologo = document.getElementById("odontologos");
  const cerrarModal = document.querySelector(".cerrar");
  const abrirModal = document.getElementById("abrirModal");
  const token = localStorage.getItem("jwtToken");

  // Abrir modal y actualizar la fecha y hora
  abrirModal.addEventListener("click", function () {

    // Verifica si el usuario está logueado sino no abre el modal
    if (!token) {
      return;
    }

    const fechaSeleccionada = document.getElementById("fecha").value;
    const horaSeleccionada = document.getElementById("hora").value;

    // Verifica si se seleccionó una fecha y hora
    if (!fechaSeleccionada || !horaSeleccionada || !tratamientos.value || !odontologo.value) {
      alert("Por favor completa todos los campos");
      return;
    }

    // Actualiza el contenido del modal
    fechaTurno.textContent = fechaSeleccionada;
    horaTurno.textContent = `Hora: ${horaSeleccionada}`;

    // Mostrar modal
    modal.style.display = "block";
  });

  // Cerrar modal
  cerrarModal.addEventListener("click", function () {
    modal.style.display = "none";
  });

  // Cerrar el modal si se hace clic fuera de él
  window.addEventListener("click", function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  });
});
