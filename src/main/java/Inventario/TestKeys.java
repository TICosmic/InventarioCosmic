/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import Fuentes.WinRegistry;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.prefs.Preferences;

/**
 *
 * @author Rubén
 */
public class TestKeys {
    public static void main(String [] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
        //String comando="move /y “C:\\Users\\Rubén\\Desktop\\TeamViewer.lnk” “C:\\Users\\Rubén\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup”"
        
        try {
        

        String pathOrigen = System.getProperty("user.home")+"\\Desktop\\";
        String pathDestino = System.getProperty("user.home")+"\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\";
        // Define aqui tu directorio destino
        String fichero = "Inventario COSMIC.lnk";
        File ficheroCopiar = new File(pathOrigen, fichero);
        File ficheroDestino = new File(pathDestino, fichero);
        
        if (ficheroDestino.exists()) {
            System.out.println("Hola");
        
        }else{
            if (ficheroCopiar.exists()) {
                Files.copy(Paths.get(ficheroCopiar.getAbsolutePath()), Paths.get(ficheroDestino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            }else{
                System.out.println("El fichero " + fichero + " no existe en el directorio " + pathOrigen);
            }
        }
   

    } catch (Exception e) {
        e.printStackTrace();
    }
        
//        Path origenPath = FileSystems.getDefault().getPath(System.getProperty("user.home")+"\\Desktop\\Inventario COSMIC.lnk");
//        Path destinoPath = FileSystems.getDefault().getPath(System.getProperty("user.home")+"\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\Inventario COSMIC.lnk");
//        
//        
//        
//        try {
//            Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            System.err.println(e);
//        }
        
    }
    
}
