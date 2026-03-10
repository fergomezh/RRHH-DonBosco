document.addEventListener("DOMContentLoaded", function (){
    const formulario = document.querySelector("form");
    
    // Trim en inputs al perder foco
    const inputs = document.querySelectorAll("input[type='text'], input[type='number']");
    inputs.forEach(input => {
        input.addEventListener("blur", function () {
            this.value = this.value.trim();
        });
    });
    
    // Validación al enviar
    formulario.addEventListener("submit", function (e) {

        let errores = [];

        // Obtener campos
        const departamento = document.getElementById("idDepartamento");
        const puesto = document.getElementById("idEmpleado");
        const modalidad = document.getElementById("idCargo");
        const tipoContratacion = document.getElementById("idTipoContratacion");
        const fecha = document.getElementById("fechaContratacion");
        const salario = document.getElementById("salario");
        const estado = document.querySelector("select[name='estado']");

        // Validar departamento
        if (!departamento.value) {
            errores.push("❌ Debe seleccionar un departamento");
        }

        // Validar puesto
        if (!puesto.value) {
            errores.push("❌ Debe seleccionar un puesto");
        }

        // Validar modalidad
        if (!modalidad.value) {
            errores.push("❌ Debe seleccionar una modalidad");
        }

        // VALIDAR TIPO DE CONTRATACIÓN (NUEVO)
        if (!tipoContratacion.value) {
            errores.push("❌ Debe seleccionar un tipo de contratación");
        }

        // Validar fecha
        if (!fecha.value) {
            errores.push("❌ Debe ingresar la fecha de contratación");
        } else {
            const fechaSeleccionada = new Date(fecha.value);
            const hoy = new Date();
            if (fechaSeleccionada > hoy) {
                errores.push("❌ La fecha de contratación no puede ser futura");
            }
        }

        // Validar salario
        if (!salario.value.trim()) {
            errores.push("❌ El salario es obligatorio");
        } else {
            const salarioNum = Number(salario.value);
            if (isNaN(salarioNum) || salarioNum <= 0) {
                errores.push("❌ El salario debe ser un número mayor que 0");
            }
        }

        // Validar estado (opcional)
        if (!estado.value) {
            errores.push("❌ Debe seleccionar un estado del proceso");
        }

        // Mostrar errores o enviar
        if (errores.length > 0) {
            alert("Por favor corrija los siguientes errores:\n\n" + errores.join("\n"));
            e.preventDefault();
        } else {
            // Opcional: mostrar confirmación
            if (!confirm("¿Está seguro de guardar esta contratación?")) {
                e.preventDefault();
            }
        }
    });

    // Formatear salario mientras se escribe (opcional)
    const salarioInput = document.getElementById("salario");
    if (salarioInput) {
        salarioInput.addEventListener("blur", function() {
            if (this.value) {
                const valor = parseFloat(this.value);
                if (!isNaN(valor)) {
                    this.value = valor.toFixed(2);
                }
            }
        });
    }
});