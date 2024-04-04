package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.exception;

public class BootcampIdNameMistMatchException extends RuntimeException{

    public BootcampIdNameMistMatchException(Long id, Long correctId, String name) {
        super("El bootcamp con ID " + id + " (" + name + ") ingresado no coincide con el nombre proporcionado. Pertenece al bootcamp con ID " + correctId + ".");
    }
}

