package com.duoc.biblioteca.manejadores;

public class LibroYaPrestado extends Exception {
    public LibroYaPrestado(String mensaje) {
        super(mensaje);
    }
}