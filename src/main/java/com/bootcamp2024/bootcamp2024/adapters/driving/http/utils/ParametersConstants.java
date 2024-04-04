package com.bootcamp2024.bootcamp2024.adapters.driving.http.utils;

public class ParametersConstants {

        private ParametersConstants() {
            throw new IllegalStateException("This is a utility class");
        }

        public static final String DEFAULT_PAGE = "0";
        public static final String DEFAULT_SIZE = "5";

        public static final String MIN_PAGE_MESSAGE = "La pagina debe ser igual o mayor a 0";
        public static final String MIN_SIZE_MESSAGE = "El tamaño debe ser mayor que 0";

        public static final String DEFAULT_FIELD = "id";
    }

