package com.duoc.biblioteca.entidades.biblioteca;

import com.duoc.biblioteca.entidades.libros.Libros;
import com.duoc.biblioteca.manejadores.LibroNoEncontrado;
import com.duoc.biblioteca.manejadores.LibroYaPrestado;
import com.duoc.biblioteca.utilitarios.Utilitarios;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Biblioteca {
    private ArrayList<Libros> libros;

    public Biblioteca(){
        libros = new ArrayList<>();
    }

    public void ingresarLibro(ArrayList<Integer> isbnUsados){
        Scanner teclado = new Scanner(System.in);
        String titulo = "";
        String autor = "";
        int isbn = 0;
        
        try{
            System.out.println("INTRODUCE EL TITULO DEL LIBRO:");
            titulo = teclado.nextLine();

            System.out.println("INTRODUCE EL AUTOR DEL LIBRO:");
            autor = teclado.nextLine();

            System.out.println("INTRODUCE EL ISBN DEL LIBRO:");
            isbn = teclado.nextInt();
            
            if(isbnUsados.contains(isbn)){
                System.out.println("EL ISBN YA ESTA USADO POR OTRO LIBRO");
            }else{
                titulo = titulo.toUpperCase();
                autor = autor.toUpperCase();

                Libros libro = new Libros(titulo, autor, isbn);
                isbnUsados.add(isbn);
                libros.add(libro);

                System.out.println("EL LIBRO HA SIDO REGISTRADO EXITOSAMENTE.");
            }
        }catch(InputMismatchException e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: ENTRADA NO VALIDA. INTRODUZCA UN NUMERO PARA EL ISBN.");
            System.out.println("");
            ingresarLibro(isbnUsados);
        }

        
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine(); teclado.nextLine();
    }
    
    public void mostrarLibros(){
        Scanner teclado = new Scanner(System.in);
        if(libros.isEmpty()){
            System.out.println("NO HAY LIBROS REGISTRADOS.");
        }else{
            System.out.println("LIBROS PERTENECIENTES A BIBLIOTECA:");
            System.out.println("");
            for(Libros libro : libros){
                System.out.println("TITULO  : "+libro.getTitulo());
                System.out.println("AUTOR   : "+libro.getAutor());
                System.out.println("ISBN    : "+libro.getIsbn());
                System.out.println("PRESTADO: "+libro.estaPrestado());
                System.out.println("-------------------------------");
            }
        }
        System.out.println("");
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
    }

    public Libros buscarLibro(int isbn) throws LibroNoEncontrado{
        for(Libros libro : libros){
            if(libro.getIsbn() == isbn){
                return libro;
            }
        }
        throw new LibroNoEncontrado("EL LIBRO NO FUE ENCONTRADO");
    }
    
    public boolean removerLibro(int isbn) throws LibroNoEncontrado{
        boolean encontrado = false;
        boolean eliminado = false;
        
        Iterator<Libros> iterator = libros.iterator();
            while(iterator.hasNext()){
                Libros libro = iterator.next();
                if(libro.getIsbn() == isbn){
                    iterator.remove();
                    encontrado = true;
                    eliminado = true;
                    break;
                }
            }
        
        if(!encontrado){
            throw new LibroNoEncontrado("EL LIBRO NO FUE ENCONTRADO");
        }
        return eliminado;
    }

    public boolean prestarLibro(int isbn) throws LibroNoEncontrado, LibroYaPrestado{
        Libros libro = buscarLibro(isbn);
        if(libro.isPrestado()){
            throw new LibroYaPrestado("EL LIBRO YA ESTA PRESTADO.");
        }
        return libro.prestar();
    }

    public boolean devolverLibro(int isbn) throws LibroNoEncontrado{
        Libros libro = buscarLibro(isbn);
        return libro.devolver();
    }
}