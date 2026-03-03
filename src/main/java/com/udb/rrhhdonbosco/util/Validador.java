package com.udb.rrhhdonbosco.util;

/*
 * Clase utilitaria con métodos de validación reutilizables en toda la aplicación.
 * Centraliza las reglas de validación del lado del servidor.
 */
public class Validador {

    private Validador() {}

    /*
     * Verifica que un texto no sea nulo ni vacío.
     */
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /*
     * Valida el formato del DUI salvadoreño: 8 dígitos, un guión y 1 dígito (########-#).
     */
    public static boolean esDuiValido(String dui) {
        return dui != null && dui.matches("^\\d{8}-\\d$");
    }

    /*
     * Valida que el correo electrónico tenga un formato básico válido.
     */
    public static boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");
    }

    /*
     * Valida que el número de teléfono sea de 8 dígitos (formato El Salvador).
     */
    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("^\\d{8}$");
    }

    /*
     * Valida que el salario sea un número positivo válido.
     */
    public static boolean esSalarioValido(String salario) {
        if (salario == null || salario.trim().isEmpty()) return false;
        try {
            double val = Double.parseDouble(salario);
            return val > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * Valida que una cadena represente un entero positivo (para IDs de FK).
     */
    public static boolean esIdValido(String valor) {
        if (valor == null || valor.trim().isEmpty()) return false;
        try {
            return Integer.parseInt(valor) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * Valida que una cadena tenga el formato de fecha yyyy-MM-dd.
     */
    public static boolean esFechaValida(String fecha) {
        return fecha != null && fecha.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }
}
