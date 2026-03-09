document.addEventListener("DOMContentLoaded", function (){
    const formulario = document.querySelector("form");
    
    const inputs = document.querySelectorAll("input[type='text'], input[type='number']");
    inputs.forEach(input => {
        input.addEventListener("blur", function () {
            this.value = this.value.trim();
        });
    });
    
    formulario.addEventListener("submit", function (e) {

        let errores = [];

        const salario = document.getElementById("salario");
        const fecha = document.getElementById("fechaContratacion");

        const empleado = document.getElementById("idEmpleado");
        const departamento = document.getElementById("idDepartamento");
        const cargo = document.getElementById("idCargo");
        const tipo = document.getElementById("idTipoContratacion");

        const textoSeguro = /^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\s]+$/;

        if (!empleado.value) {
            errores.push("Debe seleccionar un empleado");
        }

        if (!departamento.value) {
            errores.push("Debe seleccionar un departamento");
        }

        if (!cargo.value) {
            errores.push("Debe seleccionar un cargo");
        }

        if (!tipo.value) {
            errores.push("Debe seleccionar un tipo de contratación");
        }

        if (!salario.value.trim()) {
            errores.push("El salario es obligatorio");
        } else if (isNaN(salario.value) || Number(salario.value) <= 0) {
            errores.push("El salario debe ser un número mayor que 0");
        }

        if (!fecha.value) {
            errores.push("Debe ingresar la fecha de contratación");
        }

        if (errores.length > 0) {

            alert(errores.join("\n"));

            e.preventDefault();
        }

    });
});