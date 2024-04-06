package com.bootcamp2024.bootcamp2024.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "The element indicated does not exist";

    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s can not be empty";
    public static final String NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE = "Field %s can not receive negative values";

    public static final String TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "La tecnologia que tu quieres crear ya existe";

    public static final String TECHNOLOGY_PASS_THE_LIMIT_MESSAGE = "Las tecnologias ingresadas deben ser minimo 3 y maximo 20";
    public static final String DUPLICATE_IDS_TECHNOLOGY_IDS_EXCEPTION = "Porfavor no ingrese duplicados en la lista de tecnologias";

    public static final String CAPACITY_SIZE_IS_NOT_IN_THE_LIMIT = "Las capacidades que usted ha ingresado deben ser minimo 1 y maximo 4";

    public static final String DUPLICATE_CAPACITY_EXCEPTION = "Porfavor no ingrese duplicados en la lista de capacidades";

    public static final String PAGE_SIZE_CANT_BE_LESS_THAN_ZERO = "La pagina y/o el tamaño no pueden ser menor que 0";

    public static final String CAPACITY_NOT_FOUND_MESSAGE = "Capacity not found: %s";
    public static final String TECHNOLOGY_NOT_FOUND_MESSAGE = "Technology not found: %s";

    public static final String CAPACITY_ALREADY_EXISTS = "La capacidad que tu quieres crear ya existe";
    public static final String BOOTCAMP_ALREADY_EXISTS = "El bootcamp que tu quieres crear ya existe";

    public static final String VERSION_STARTDATE_IS_BEFORE_ENDDATE = "La fecha de fin no debe ser antes de la fecha inicio";
    public static final String VERSION_NOT_VALID_FIELD_MESSAGE = "El campo %s no es valido";
    public static final String BOOTCAMP_NOT_FOUND_MESSAGE = "El bootcamp: %s no existe";
    public static final String VERSION_MAXIMUM_CAPACITY_PASS_THE_LIMIT = "La capacidad maxima %s no es valida, debe ser mayor a 0 y menor a 50";
    public static final String VERSION_DATE_BEFORE_TODAY = "La fecha %s no puede ser antes de hoy";
    public static final String VERSION_DATE_MESSAGE = "El formato de las fechas es inválido. Se esperaba yyyy-MM-dd.";
 }
