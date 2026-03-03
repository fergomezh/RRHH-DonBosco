package com.udb.rrhhdonbosco.util;

import java.util.regex.Pattern;

public class Validador {
    private static final String EMAIL_VALIDADOR="^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String DUI_CONSTITUCION="^[0-9]{8}-[0-9]{1}$";

    //Metodo para validar ingreso de Documento Unico de Identidad DUI
    public static boolean validarCargo(String dui) {
        if (dui==null) return false;
        return dui.matches(DUI_CONSTITUCION);
        }
    }







