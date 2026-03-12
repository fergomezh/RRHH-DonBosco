<%--
  Created by IntelliJ IDEA.
  User: fergo
  Date: 27/2/2026
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Agregar Cargos</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Fugaz+One&family=Google+Sans:ital,opsz,wght@0,17..18,400..700;1,17..18,400..700&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Public+Sans:ital,wght@0,100..900;1,100..900&family=Work+Sans:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/98424cc025.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/izitoast/dist/css/iziToast.min.css">
        <script src="https://cdn.jsdelivr.net/npm/izitoast/dist/js/iziToast.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", () => {
                document.getElementById("formCargo").addEventListener("submit", async (e) => {
                    e.preventDefault();

                    const datosFormulario = new FormData();
                    datosFormulario.append("cargoNombre", document.getElementById("cargoNombre").value);
                    datosFormulario.append("cargoDescripcion", document.getElementById("cargoDescripcion").value);

                    if (document.getElementById("cargoJefatura").checked) {
                        datosFormulario.append("cargoJefatura", "on");
                    }

                    try {
                        const res = await axios.post("/RRHH-DonBosco/cargos/nuevo", datosFormulario);
                        iziToast.success({
                            title: 'OK',
                            message: 'Cargo guardado'
                        });
                        formCargo.reset();
                    } catch (err) {
                        iziToast.success({
                            title: 'OK',
                            message: 'Cargo guardado'
                        });
                    }
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
            .titulo {
                font-weight: 600;
                padding: 1rem;
                color: #146ebe;
                font-size: 1.5rem;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .titulo a, .titulo span {
                color: #146ebe;
                font-size: 1.1rem;
                text-decoration: none;
            }
            form {
                padding: 1rem;
                margin-top: .5rem;
            }
            .flex {
                display: flex;
            }
            .items-center {
                align-items: center;
            }
            .justify-beetwen {
                justify-content: space-between;
            }
            .justify-end {
                justify-content: end;
            }
            .p-4 {
                padding: 1rem;
            }

            .mt-4 {
                margin-top: 1rem;
            }
            .bg-gray-100 {
                background: #f1f1f1;
            }
            .text-sm {
                font-size: 0.875rem;
                line-height: 1.25rem;
            }
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
                padding: 5px 1rem;
                color: #ededed;
                border-radius: 5px;
                box-shadow: 0 1px 3px rgba(0,0,0,0.1);
            }
        </style>
    </head>
    <body>
        <div class="contenido">
            <div>
                <h1 class="titulo">
                    <a href="/RRHH-DonBosco/cargos">
                        <i class="fa-whiteboard fa-semibold fa-circle-arrow-left"></i> Volver
                    </a>
                    <span>
                        <i class="fa-whiteboard fa-semibold fa-user-plus"></i> Agregar Cargos
                    </span>
                </h1>
                <form id="formCargo">
                    <div class="box">
                        <span  class="icon"><i class="fa-whiteboard fa-semibold fa-id-card"></i></span>
                        <input name="cargoNombre" id="cargoNombre" class="box-input" autocomplete="off" placeholder="Nombre deln cargo" required>
                    </div>
                    <textarea name="cargoDescripcion" id="cargoDescripcion" rows="6" autocomplete="off" placeholder="Escribe una descripcion para el cargo..." required></textarea>
                    <div class="flex items-center justify-beetwen p-4 bg-gray-100 mt-4">
                        <label for="cargoJefatura" class="text-sm"><i class="fa-whiteboard fa-semibold fa-check"></i> Seleccionar cargo como jefatura:</label>
                        <input type="checkbox" id="cargoJefatura" name="cargoJefatura">
                    </div>
                    <div class="flex justify-end">
                        <button type="submit" class="btnGuardar text-sm mt-4"><i class="fa-whiteboard fa-semibold fa-plus"></i> Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
