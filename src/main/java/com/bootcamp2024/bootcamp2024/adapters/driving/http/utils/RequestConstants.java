package com.bootcamp2024.bootcamp2024.adapters.driving.http.utils;

public class RequestConstants {

    private RequestConstants() {
        throw new IllegalStateException("This is a utility class");
    }

    public static final int NAME_MAX_SIZE = 50;
    public static final int DESCRIPTION_MAX_SIZE = 90;

    public static final String NAME_IS_MANDATORY = "EL NOMBRE ES OBLIGATORIO";
    public static final String DESCRIPTION_IS_MANDATORY = "LA DESCRIPCION ES OBLIGATORIO";
    public static final String TECHNOLOGIES_LIST_IS_MANDATORY = "LA LISTA DE TECNOLOGIAS NO PUEDE ESTAR VACIA";
    public static final String CAPACITIES_LIST_IS_MANDATORY = "LA LISTA DE CAPACIDAD NO PUEDE ESTAR VACIA";
    public static final String NAME_MAX_SIZE_MESSAGE = "El tamaño maximo de nombre es " + NAME_MAX_SIZE;
    public static final String DESCRIPTION_MAX_SIZE_MESSAGE = "El tamaño maximo de descripcion es" + DESCRIPTION_MAX_SIZE;
}
