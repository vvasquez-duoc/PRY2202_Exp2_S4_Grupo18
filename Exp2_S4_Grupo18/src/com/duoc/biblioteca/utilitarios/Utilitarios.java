package com.duoc.biblioteca.utilitarios;

import java.text.NumberFormat;
import java.util.Locale;

public class Utilitarios {
    public static void limpiaPantalla(){
        for(int i=0; i<30; i++){
            System.out.println("");
        }
    }
    
    public static String formatoRut(String rut){
        int R = Integer.parseInt(rut.substring(0, rut.length() - 1));
        String DV = rut.substring(rut.length() - 1, rut.length());
        
        NumberFormat formato = NumberFormat.getInstance(new Locale("es", "CL"));
        formato.setGroupingUsed(true);
        return formato.format(R)+"-"+DV;
    }
    
    public static Boolean validaRut(String rut){
        int rutNum;
        if(rut == null || rut.isEmpty() || rut.length() < 8 || rut.length() > 9) return false;
        
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        rut = rut.toUpperCase();
        
        String R = rut.substring(0, rut.length() - 1);
        String DV = rut.substring(rut.length() - 1, rut.length());
        
        try{
            rutNum = Integer.parseInt(R);
        }catch(NumberFormatException e){
            return false;
        }
        
        return DV.toLowerCase().equals(dv(R));
    }
    
    public static String dv(String rut){
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        
        return ( S > 0 ) ? String.valueOf(S-1) : "k";		
    }
}
