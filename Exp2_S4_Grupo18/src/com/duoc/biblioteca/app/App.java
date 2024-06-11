package com.duoc.biblioteca.app;

import com.duoc.biblioteca.entidades.biblioteca.Biblioteca;
import com.duoc.biblioteca.entidades.libros.Libros;
import com.duoc.biblioteca.entidades.usuarios.Usuarios;
import com.duoc.biblioteca.manejadores.LibroNoEncontrado;
import com.duoc.biblioteca.manejadores.LibroYaPrestado;
import com.duoc.biblioteca.utilitarios.Utilitarios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    static Biblioteca biblioteca = new Biblioteca();
    static ArrayList<String> itemsMenuBiblioteca = new ArrayList<>();
    static ArrayList<String> itemsMenuUsuario = new ArrayList<>();
    static ArrayList<Integer> isbnUsados = new ArrayList<>();
    static HashMap<String, Usuarios> usuariosRegistrados = new HashMap<>();
    static HashMap<Integer, String> librosPrestados = new HashMap<>();
    
    /*public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";*/
    
    public static void main(String[] args) {
        itemsMenuBiblioteca.add("REGISTRAR USUARIO");
        itemsMenuBiblioteca.add("LOGIN USUARIO");
        itemsMenuBiblioteca.add("REGISTRAR LIBRO EN BIBLIOTECA");
        itemsMenuBiblioteca.add("MOSTRAR TODOS LIBROS EN BIBLIOTECA");
        itemsMenuBiblioteca.add("BUSCAR LIBRO EN BIBLIOTECA");
        itemsMenuBiblioteca.add("ELIMINAR LIBRO EN BIBLIOTECA");
        itemsMenuBiblioteca.add("SALIR");
        
        itemsMenuUsuario.add("PRESTAR LIBRO");
        itemsMenuUsuario.add("DEVOLVER LIBRO");
        itemsMenuUsuario.add("MOSTRAR LIBROS YA PRESTADOS");
        itemsMenuUsuario.add("VOLVER A MENU BIBLIOTECA");
        
        Utilitarios.limpiaPantalla();
        menuBiblioteca();
    }
    
    static void menuBiblioteca(){
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        
        try{
            do{
                System.out.println("BIENVENIDO A SISTEMA DE ADMINISTRACION DE BIBLIOTECA");
                System.out.println("");
                System.out.println("SELECCIONE UNA OPCION");
                for(int i=0; i<itemsMenuBiblioteca.size(); i++){
                    System.out.println("["+(i + 1)+"] "+itemsMenuBiblioteca.get(i));
                }
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > itemsMenuBiblioteca.size()){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > itemsMenuBiblioteca.size());
        }catch(InputMismatchException e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            menuBiblioteca();
        }
        
        if(opcion == 1){
            Utilitarios.limpiaPantalla();
            registrarUsuario();
        }
        
        if(opcion == 2){
            Utilitarios.limpiaPantalla();
            loginUsuario();
        }
        
        if(opcion == 3){
            Utilitarios.limpiaPantalla();
            registrarLibro();
        }
        
        if(opcion == 4){
            Utilitarios.limpiaPantalla();
            listarLibro();
        }
        
        if(opcion == 5){
            Utilitarios.limpiaPantalla();
            encontrarLibro();
        }
        
        if(opcion == 6){
            Utilitarios.limpiaPantalla();
            eliminarLibro();
        }
        
        if(opcion == 7){
            Utilitarios.limpiaPantalla();
            System.out.println("GRACIAS POR USAR SISTEMA DE ADMINISTRACION DE BIBLIOTECA");
        }
    }
    
    static void registrarUsuario(){
        Scanner teclado = new Scanner(System.in);
        String RUT;
        String NOMBRE;
        String DIRECCION;
        String COMUNA;
        boolean rutEsValido = true;
        
        do{
            Utilitarios.limpiaPantalla();
            if(rutEsValido == false) System.out.println("** EL RUT INGRESADO NO ES VALIDO **");
            System.out.println("INGRESE RUT DEL USUARIO:");
            System.out.println("* INGRESE RUT SIN PUNTOS NI GUION. EJ: 11222333K");
            RUT = teclado.nextLine();
            rutEsValido = Utilitarios.validaRut(RUT);
        }while(rutEsValido == false);
        
        Utilitarios.limpiaPantalla();
        System.out.println("INGRESE NOMBRE COMPLETO DEL USUARIO:");
        NOMBRE = teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        System.out.println("INGRESE DIRECCION DEL USUARIO:");
        DIRECCION = teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        System.out.println("INGRESE COMUNA DEL DOMICILIO DEL USUARIO:");
        COMUNA = teclado.nextLine();
        
        RUT = RUT.toUpperCase();
        NOMBRE = NOMBRE.toUpperCase();
        DIRECCION = DIRECCION.toUpperCase();
        COMUNA = COMUNA.toUpperCase();

        Usuarios auxUsuario = new Usuarios(RUT, NOMBRE, DIRECCION, COMUNA);
        usuariosRegistrados.put(RUT, auxUsuario);
        
        Utilitarios.limpiaPantalla();
        System.out.println("USUARIO REGISTRADO EXITOSAMENTE!");
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuBiblioteca();
    }
    
    
    
    static void registrarLibro(){
        biblioteca.ingresarLibro(isbnUsados);
        
        Utilitarios.limpiaPantalla();
        menuBiblioteca();
    }
    
    static void listarLibro(){
        biblioteca.mostrarLibros();
        
        Utilitarios.limpiaPantalla();
        menuBiblioteca();
    }
    
    static void encontrarLibro(){
        Scanner teclado = new Scanner(System.in);
        int isbn = 0;
        
        try{
            System.out.println("INTRODUZCA EL ISBN DEL LIBRO:");
            isbn = teclado.nextInt();
            Libros auxLibro = biblioteca.buscarLibro(isbn);
            System.out.println("");
            System.out.println("-- LIBRO ENCONTRADO --");
            System.out.println("TITULO  : "+auxLibro.getTitulo());
            System.out.println("AUTOR   : "+auxLibro.getAutor());
            System.out.println("ISBN    : "+auxLibro.getIsbn());
            System.out.println("PRESTADO: "+auxLibro.estaPrestado());
        }catch(LibroNoEncontrado e){
            System.out.println("ERROR: "+e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("ERROR: EL ISBN INGRESADO NO ES UN NUMERO");
        }finally{
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine(); teclado.nextLine();
            Utilitarios.limpiaPantalla();
            menuBiblioteca();
        }
    }
    
    static void eliminarLibro(){
        Scanner teclado = new Scanner(System.in);
        int isbn = 0;
        boolean auxEliminado = false;
        
        try{
            System.out.println("INTRODUZCA EL ISBN DEL LIBRO:");
            isbn = teclado.nextInt();
            auxEliminado = biblioteca.removerLibro(isbn);
            System.out.println(auxEliminado);
            
        }catch(LibroNoEncontrado e){
            System.out.println("ERROR: "+e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("ERROR: EL ISBN INGRESADO NO ES UN NUMERO");
        }finally{
            if(auxEliminado){
                Integer auxIsbn = isbn;
                isbnUsados.remove(auxIsbn);
                System.out.println("");
                System.out.println("-- LIBRO ELIMINADO --");
            }
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine(); teclado.nextLine();
            Utilitarios.limpiaPantalla();
            menuBiblioteca();
        }
    }
    
    static void loginUsuario(){
        Scanner teclado = new Scanner(System.in);
        String RUT;
        boolean rutEsValido = true;
        
        do{
            Utilitarios.limpiaPantalla();
            if(rutEsValido == false) System.out.println("** EL RUT INGRESADO NO ES VALIDO **");
            System.out.println("INGRESE RUT DEL USUARIO:");
            System.out.println("* INGRESE RUT SIN PUNTOS NI GUION. EJ: 11222333K");
            RUT = teclado.nextLine();
            rutEsValido = Utilitarios.validaRut(RUT);
        }while(rutEsValido == false);
        RUT = RUT.toUpperCase();
        
        if(usuariosRegistrados.containsKey(RUT)){
            Usuarios auxUsuario = usuariosRegistrados.get(RUT);
           
            menuUsuario(auxUsuario);
        }else{
            System.out.println("");
            System.out.println("EL USUARIO NO ESTA INSCRITO EN LA BIBLIOTECA");
            System.out.println("¿QUIERE INSCRIBIR EL USUARIO EN LA BIBLIOTECA? (S/N)");
            String respuesta = teclado.nextLine();
            if(respuesta.equalsIgnoreCase("S")){
                registrarUsuario();
            }else{
                System.out.println("");
                System.out.println("Presione ENTER para continuar...");
                teclado.nextLine();
                Utilitarios.limpiaPantalla();
                menuBiblioteca();
            }
        }
    }
    
    static void menuUsuario(Usuarios usuario){
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        
        Utilitarios.limpiaPantalla();
        System.out.println("LOGIN EXITOSO");
        System.out.println("RUT: "+Utilitarios.formatoRut(usuario.getRut()));
        System.out.println("NOMBRE: "+usuario.getNombreCompleto());
        System.out.println("DIRECCION: "+usuario.getDireccion()+". "+usuario.getComuna());
        
        try{
            do{
                System.out.println("");
                System.out.println("SELECCIONE UNA OPCION");
                for(int i=0; i<itemsMenuUsuario.size(); i++){
                    System.out.println("["+(i + 1)+"] "+itemsMenuUsuario.get(i));
                }
                opcion = teclado.nextInt();
                if(opcion < 1 || opcion > itemsMenuUsuario.size()){
                    Utilitarios.limpiaPantalla();
                    System.out.println("-- LA OPCION ("+opcion+") NO ES VALIDA --");
                    System.out.println("");
                }
            }while(opcion < 1 || opcion > itemsMenuUsuario.size());
        }catch(InputMismatchException e){
            Utilitarios.limpiaPantalla();
            System.out.println("ERROR: LA OPCION INGRESADA NO ES UN NUMERO");
            System.out.println("");
            menuUsuario(usuario);
        }
        
        if(opcion == 1){
            Utilitarios.limpiaPantalla();
            prestaLibro(usuario);
        }
        
        if(opcion == 2){
            Utilitarios.limpiaPantalla();
            devuelveLibro(usuario);
        }
        
        if(opcion == 3){
            Utilitarios.limpiaPantalla();
            mostrarLibrosYaPrestados(usuario);
        }
        
        if(opcion == 4){
            Utilitarios.limpiaPantalla();
            menuBiblioteca();
        }
    }
    
    static void prestaLibro(Usuarios usuario){
        Scanner teclado = new Scanner(System.in);
        String RUT = usuario.getRut();
        int isbn = 0;
        
        try{
            System.out.println("INTRODUZCA EL ISBN DEL LIBRO:");
            isbn = teclado.nextInt();
            if(biblioteca.prestarLibro(isbn)){
                System.out.println("EL LIBRO HA SIDO PRESTADO");
                librosPrestados.put(isbn, RUT);
            }
        }catch(LibroNoEncontrado e){
            System.out.println("ERROR: "+e.getMessage());
        }catch(LibroYaPrestado e){
            System.out.println("ERROR: "+e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("ERROR: EL ISBN INGRESADO NO ES UN NUMERO");
        }finally{
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine(); teclado.nextLine();
            Utilitarios.limpiaPantalla();
            menuUsuario(usuario);
        }
    }
    
    static void devuelveLibro(Usuarios usuario){
        Scanner teclado = new Scanner(System.in);
        String RUT = usuario.getRut();
        int isbn = 0;
        
        try{
            System.out.println("INTRODUZCA EL ISBN DEL LIBRO:");
            isbn = teclado.nextInt();
            if(biblioteca.devolverLibro(isbn)){
                System.out.println("EL LIBRO HA SIDO DEVUELTO");
                librosPrestados.remove(isbn);
            }
        }catch(LibroNoEncontrado e){
            System.out.println("ERROR: "+e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("ERROR: EL ISBN INGRESADO NO ES UN NUMERO");
        }finally{
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine(); teclado.nextLine();
            Utilitarios.limpiaPantalla();
            menuUsuario(usuario);
        }
    }
    
    static void mostrarLibrosYaPrestados(Usuarios usuario){
        Scanner teclado = new Scanner(System.in);
        boolean hayLibrosPrestados = false;
        String RUT = usuario.getRut();
        int isbn = 0;
        
        System.out.println("LIBROS PRESTADOS AL USUARIO: ");
        System.out.println("RUT   : "+Utilitarios.formatoRut(RUT));
        System.out.println("NOMBRE: "+usuario.getNombreCompleto());
        System.out.println("");
        for(int clave : librosPrestados.keySet()){
            isbn = clave;
            String rutUsuario = librosPrestados.get(clave);
            if(rutUsuario.equalsIgnoreCase(RUT)){
                hayLibrosPrestados = true;
                try{
                    Libros auxLibro = biblioteca.buscarLibro(isbn);
                    System.out.println("ISBN  : "+auxLibro.getIsbn());
                    System.out.println("TITULO: "+auxLibro.getTitulo());
                    System.out.println("AUTOR : "+auxLibro.getAutor());
                    System.out.println("--------------------------");
                }catch(LibroNoEncontrado e){
                    System.out.println("ERROR: "+e.getMessage());
                }
            }
        }
        if(!hayLibrosPrestados){
            System.out.println("NO HAY LIBROS PRESTADOS");
        }
        System.out.println("");
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
        Utilitarios.limpiaPantalla();
        menuUsuario(usuario);
    }
}
