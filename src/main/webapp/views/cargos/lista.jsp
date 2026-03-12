<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Lista de cargos</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Fugaz+One&family=Google+Sans:ital,opsz,wght@0,17..18,400..700;1,17..18,400..700&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Public+Sans:ital,wght@0,100..900;1,100..900&family=Work+Sans:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/98424cc025.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css">
        <script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", () => {
                //parametros globales
                let idSeleccionado;
                let cargoSeleccionado;
                let descripcioinSelecciondo;
                let jefaturaSeleccionado;

                //todos los botones
                const btnAbrirModalBorrar = document.querySelectorAll(".btnBorrar");
                const btnAbrirModalEditar = document.querySelectorAll(".btnEditar");

                //modals
                const modal = document.querySelector(".contenido-modal");
                const modalEditar = document.querySelector(".modal-editar");

                btnAbrirModalBorrar.forEach(btn => {
                    btn.addEventListener("click", (e) => {
                        e.preventDefault();
                        const info = e.currentTarget.closest("tr");
                        textCargo.textContent = info.dataset.cargo;
                        idSeleccionado = info.dataset.id;
                        modal.style.display = "flex";
                    });
                });

                btnAbrirModalEditar.forEach(btn => {
                    btn.addEventListener("click", (e) => {
                        e.preventDefault();
                        const info = e.currentTarget.closest("tr");
                        const infoJefatura = info.dataset.jefatura === "true";
                        cargoNombre.value = info.dataset.cargo;
                        cargoDescripcion.value = info.dataset.descripcion;
                        cargoJefatura.checked = infoJefatura;
                        idSeleccionado = info.dataset.id;
                        modalEditar.style.display = "flex";
                    });
                });

                async function eliminarCargo(id) {
                    try {
                        const response = await axios.post("${pageContext.request.contextPath}/cargos?accion=eliminar", new URLSearchParams({
                            id: id
                        }));
                        iziToast.success({
                            title: 'Eliminado',
                            message: 'Se elimino el cargo con id: ' + id
                        });
                    } catch (error) {
                        console.error(error);
                    }
                }

                const editarCargo = async (id, cargoNombre, cargoDescripcion, cargoJefatura) => {
                    try {
                        const params = new URLSearchParams();
                        params.append("id", id);
                        params.append("cargoNombre", cargoNombre);
                        params.append("cargoDescripcion", cargoDescripcion);

                        if (cargoJefatura) {
                            params.append("cargoJefatura", "on");
                        }
                        const {data} = await axios.post("${pageContext.request.contextPath}/cargos?accion=editar", params);
                        iziToast.success({
                            title: 'Actualizado',
                            message: 'Se actualizo el cargo con id: ' + id
                        });
                        setTimeout(() => {
                            location.reload();
                        }, 2000);
                    } catch (error) {
                        console.error(error);
                    }
                };

                btnEliminarOk.addEventListener("click", (e) => {
                    e.preventDefault();
                    document.getElementById(idSeleccionado).remove();
                    eliminarCargo(idSeleccionado);
                    total.textContent = document.querySelectorAll(".btnBorrar").length;
                    modal.style.display = "none";
                });

                btnCancelarOk.addEventListener("click", (e) => {
                    e.preventDefault();
                    modal.style.display = "none";
                });


                formCargo.addEventListener("submit", (e) => {
                    e.preventDefault();
                    editarCargo(idSeleccionado, cargoNombre.value, cargoDescripcion.value, cargoJefatura.checked)
                });

                btnCancelarEditar.addEventListener("click", (e) => {
                    e.preventDefault();
                    modalEditar.style.display = "none";
                });

            });

        </script>
        <style>
            * {
                padding: 0;
                margin: 0;
                box-sizing: border-box;
                font-family: "Public Sans", sans-serif;
                font-optical-sizing: auto;
                font-weight: 400; /* 100..900 */
                font-style: normal;
            }

            body {
                background: #dceaff;
            }

            .contenido {
                width: 100%;
                max-width: 900px;
                background: #f5f9ff;
                margin: 0 auto;
                color: #146ebe;
            }
            .text-sm {
                font-size: 0.875rem;
                line-height: 1.25rem;
            }
            .font-semibold {
                font-weight: 600;
            }
            .p-4 {
                padding: 1rem;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 16px;
            }

            th, td {
                border: 1px solid #ccc;
                padding: 12px;
                text-align: left;
            }

            th {
                background: #f5f5f5;
            }
            .text-center {
                text-align: center;
            }
            button {
                outline: 0;
                background: transparent;
                border: none;
                cursor: pointer;
            }
            .flex {
                display: flex;
            }
            .items-center {
                align-items: center;
            }
            .justify-center {
                justify-content: center;
            }
            .justify-between {
                justify-content: space-between;
            }
            .justify-end {
                justify-content: end;
            }
            .contenido-modal, .modal-editar {
                background: rgba(0,0,0, .5);
                position: fixed;
                width: 100%;
                height: 100%;
                left: 0;
                top: 0;
            }
            .contenido-modal {
                display: none;
            }
            .modal-editar {
                display: none;
            }
            .modal {
                background: #fff;
                border-radius: 6px;
                width: 90%;
                max-width: 500px;
            }
            .btn {
                border: none;
                outline: none;
                cursor: pointer;
                padding: 0.85rem 1rem;
                border-radius: 5px;
                font-size: 0.95rem;
                font-weight: 700;
                transition: all 0.18s ease;
            }

            .btn-cancelar {
                background: white;
                color: #000000;
                border: 1px solid #000;
            }

            .btn-cancelar:hover, .btn-eliminar:hover, .btnGuardar:hover {
                background: linear-gradient(135deg, #dbeafe, #c7ddff);
            }

            .btn-eliminar {
                background: tomato;
                color: #ffffff;
            }

            .btn:active {
                transform: scale(0.98);
                box-shadow: 0 3px 10px rgba(0,0,0,0.10);
            }

            /* formulario editar*/


            .box {
                display: flex;
                justify-content: space-between;
                align-items: center;
                border: 1px solid #000;
                border-radius: .5rem;
                background: #f1f1f1;
                margin-bottom: 1rem;
            }
            .box span {
                background: #f1f1f1;
                padding: 1rem;
                border-top-left-radius: 7px;
                border-bottom-left-radius: 7px;
            }
            input.box-input {
                outline: 0;
                padding: 1rem;
                display: inline-block;
                flex: 1;
                background: #f1f1f1;
                border: none;
                border-top-right-radius: 7px;
                border-bottom-right-radius: 7px;
            }
            textarea {
                outline: 0;
                padding: 1rem;
                display: block;
                background: #f1f1f1;
                border: 1px solid #000;
                border-radius: 7px;
                width: 100%;
            }
            .btnGuardar {
                background: #514E4C;
                color: #ededed;
            }
            .mt-4 {
                margin-top: 1rem;
            }
            .bg-gray-100 {
                background: #f1f1f1;
            }
            .text-red-500 {
                color: tomato;
            }
        </style>
    </head>
    <body>
        <div class="contenido p-4">


            <div class="flex items-center justify-between">
                <div>
                    <i class="fa-whiteboard fa-semibold fa-list-ol"></i> Total cargos: <span id="total">${cargos.size()}</span>
                </div>
                <div>
                    <a href="${pageContext.request.contextPath}/cargos?accion=nuevo">
                        <i class="fa-whiteboard fa-semibold fa-user-plus"></i> agregar nuevo cargo
                    </a>
                </div>
            </div>

            <table  class="p-4 mt-4">
                <thead>
                    <tr>
                        <th class="text-sm font-semibold text-center">ID</th>
                        <th class="text-sm font-semibold text-center">CARGO</th>
                        <th class="text-sm font-semibold text-center">DESCRIPCIÓN</th>
                        <th class="text-sm font-semibold text-center">JEFE</th>
                        <th class="text-sm font-semibold text-center">EDITAR</th>
                        <th class="text-sm font-semibold text-center">ELIMINAR</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${cargos}">
                        <tr id="${c.idCargo}" data-id="${c.idCargo}" data-cargo="${c.cargoNombre}" data-descripcion="${c.cargoDescripcion}" data-jefatura="${c.cargoJefatura}">
                            <td class="text-sm">${c.idCargo}</td>
                            <td class="text-sm">${c.cargoNombre}</td>
                            <td class="text-sm">${c.cargoDescripcion}</td>
                            <td class="text-sm text-center">${c.cargoJefatura ? 'Sí' : 'No'}</td>
                            <td class="text-sm text-center"><button class="btnBorrar"><i class="fa-whiteboard fa-semibold fa-trash"></i></button></td>
                            <td class="text-sm text-center"><button class="btnEditar"><i class="fa-whiteboard fa-semibold fa-pencil-line"></i></button></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="contenido-modal flex items-center justify-center">
                <div class="modal p-4">
                    <h2 class="text-sm font-semibold">
                        ¿Realmente deseas eliminar el cargo "<span class="text-red-500 font-semibold" id="textCargo"></span>"?
                    </h2>
                    <div class="flex items-center justify-between mt-4">
                        <button id="btnCancelarOk" class="btn btn-cancelar text-sm">Cancelar</button>
                        <button id="btnEliminarOk" class="btn btn-eliminar text-sm">Eliminar</button>
                    </div>
                </div>
            </div>

            <div class="modal-editar flex items-center justify-center">
                <div class="modal p-4">
                    <h2 class="text-sm font-semibold">
                        <i class="fa-whiteboard fa-semibold fa-pen"></i>  Formulario de edicion
                    </h2>
                    <form class="mt-4" id="formCargo">
                        <div class="box">
                            <span  class="icon"><i class="fa-whiteboard fa-semibold fa-id-card"></i></span>
                            <input name="cargoNombre" id="cargoNombre" class="box-input" autocomplete="off" placeholder="Nombre deln cargo" required>
                        </div>
                        <textarea name="cargoDescripcion" id="cargoDescripcion" rows="6" autocomplete="off" placeholder="Escribe una descripcion para el cargo..." required></textarea>
                        <div class="flex items-center justify-between p-4 bg-gray-100 mt-4">
                            <label for="cargoJefatura" class="text-sm"><i class="fa-whiteboard fa-semibold fa-check"></i> Seleccionar cargo como jefatura:</label>
                            <input type="checkbox" id="cargoJefatura" name="cargoJefatura">
                        </div>
                        <div class="flex items-center justify-between mt-4">
                            <button id="btnCancelarEditar" class="btn btn-cancelar">Cancelar</button>
                            <button type="submit" class="btnGuardar btn text-sm"><i class="fa-whiteboard fa-semibold fa-floppy-disk"></i> Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>