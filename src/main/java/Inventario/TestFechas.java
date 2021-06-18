/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Rubén
 */
public class TestFechas {
    public static void main(String[] args) throws IOException, ParseException{
        
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy,  HH:mm z Z X");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String fechaTexto = format.format(fecha);
        System.out.println("Fecha: "+fechaTexto);
        
        
        
        
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        
        
        
        System.out.println(Locale.getDefault().toString());
        
        Date fechaCorta;
        
        Metodos obj=new Metodos();
        String[] datos = obj.getSysInfo();
        datos[datos.length-1]="Espacio vacío";
        
        String fechaA=obj.getArranque(datos);
        System.out.println(fechaA);
        
        String[] fechaC=fechaA.split(",");
        obj.removAc(fechaC[1]);
        System.out.println(fechaC[0]);
        System.out.println(fechaC[1]);
        
        
        fechaCorta=new Date(fechaC[0]);
        String sample=formatter.format(fechaCorta);
        Date fechaU=new Date();
        System.out.println("Ejemplo: "+sample);
        
        System.out.println("Fecha definitiva: "+obj.fecha(fechaA));
        
        System.out.println(Locale.getDefault());
        if (Locale.getDefault().toString().equals("es_MX")) {
//            fechaU.setMonth(fechaCorta.getDay());
//            fechaU.setDate(fechaCorta.getMonth());
//            fechaU.setYear(fechaCorta.getYear());
//            sample=formatter.format(fechaU);
//            System.out.println("Ejemplo 2: "+sample);
        }else if (Locale.getDefault().toString().equals("es_ES")) {
            
            
        
            
        }
        
        
        
        //Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        
        
        
    }
    
}
