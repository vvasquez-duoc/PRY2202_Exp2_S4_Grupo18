package com.duoc.biblioteca.entidades.libros;

public class Libros {
    private String titulo;
    private String autor;
    private int isbn;
    private boolean prestado;

    public Libros(String titulo, String autor, int isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.prestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
    
    public int getIsbn() {
        return isbn;
    }

    public String estaPrestado() {
        return (prestado)? "SI" : "NO";
    }
    
    public boolean isPrestado(){
        return prestado;
    }

    public boolean prestar(){
        prestado = true;
        return true;
    }

    public boolean devolver(){
        prestado = false;
        return true;
    }
}