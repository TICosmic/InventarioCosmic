/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import java.text.SimpleDateFormat;
import Fuentes.ServiceResponse;

import Objeto.Bitacora;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import Objeto.Equipo;
import com.google.gson.Gson;
import java.text.ParseException;
import java.util.Locale;
import java.util.Set;
//import Windows.CMD;
import java.util.Scanner;
import java.util.prefs.Preferences;
/**
 *
 * @author Rubén
 */
public class Test {
    public static void main(String[] args) throws IOException, ParseException, IllegalArgumentException, IllegalAccessException{
        
        Scanner sc;
        
        
        Metodos obj=new Metodos();
//        Equipo equipo=new Equipo();
//        Gson gson = new Gson();
//    String jsonRequest;
//    String uri="https://ti.cosmic.mx/";
//    ServiceResponse sr=new ServiceResponse();

        System.out.println("estado acutal "+obj.buscarActualizacion());
        System.out.println("hola mundo");
//        
//        //Llenando el último espacio del arreglo nulo
//        String[] datos = obj.getSysInfo();
//        datos[datos.length-1]="Espacio vacío";
//        String ram=obj.getEnRam(datos);
//        System.out.println(ram);
//        int ramInt=obj.ramInt(ram);
//        System.out.println(ramInt);
//        
//        String fecha=obj.getEnArranque(datos);
//        fecha=fecha.replace(" ", "");
//        fecha=obj.fecha(fecha);
//        System.out.println(fecha);
//        
////        
//        //Cadena larga con toda la información
//        String datosS=" ";
//        
//        
//        for (int i = 0; i < datos.length-1; i++) {
//            //System.out.println(datos[i]);
//            datosS=datosS.concat(datos[i]+"\n");
//        }
//        
//        
//        
//        //Obteniendo fabricante
//        String fabricante=obj.getEnVendor(datos);
//        //System.out.println("Fabricante: "+fabricante);
//        
//        
//        //Obteniendo modelo
//        String modelo=obj.getEnModelo(datos);
//        //System.out.println("Modelo: "+modelo);
//        
//        
//        //Obteniendo el múmero de serie
//        String serial=obj.getSerial();
//        //System.out.println("Número de serie: "+serial);
//        
//        
//        //obteniendo el sistema operativo
//        String so=obj.getEnSo(datos);
//        String soVer=obj.getEnSoVer(datos);
//        //System.out.println("Sistema operativo: "+so);
//        //System.out.println("Versión del sistema operativo: "+soVer);
//        
//        
//        //Obteniendo bios
//        String bios=obj.getBiosVer(datos);
//        //System.out.println("Versión BIOS: "+bios);
//        
//        
//        //Obteniendo modelo del cpu
//        String cpu=obj.getCpuName();
//        //System.out.println("Nombre CPU: "+cpu);
//        
//        
//        //Obteniendo nombe del equipo
//        String hostname=obj.getPcName();
//        //System.out.println("Nombre equipo: "+hostname);
//        
//        
//        //obteniendo tamaño disco duro 930000000000
//        String diskS=obj.getDiskSize().substring(0,obj.getDiskSize().length()-2);
//        double disk=Double.parseDouble(diskS);
//        
//        double diskSize=(disk/1024/1024/1024);
//        
//        //System.out.println("Capacidad del disco duro: "+diskSize);
//        String freeDisk=obj.getFreeSize().substring(0,obj.getFreeSize().length()-2);
//        double free=Double.parseDouble(freeDisk);
//        double diskFree=free/1024/1024/1024;
//        double usageDisk=diskSize-diskFree;
//        int porcentageUso=(int) ((usageDisk*100)/diskSize);
//        
//        float tama=Math.round(diskSize);
//        float libre=Math.round(diskFree);
//        float uso=Math.round(usageDisk);
//        //System.out.println("Espacio usado: "+usageDisk+" ("+porcentageUso+"%)");
//        //System.out.println("Espacio libre: "+diskFree);
//        
//        
//        //Obteniendo memoria ram
//        int ramTotal=obj.ramInt(obj.getEnRam(datos));
//        //System.out.println("RAM Total: "+ramTotal);
//        //System.out.println(obj.getRamDisp(datos));
//        
//        //System.out.println(obj.ramInt("567 MB"));
//        
//        int ramDisp=obj.ramInt(obj.getEnRamDisp(datos));
//        int ramUso=ramTotal-ramDisp;
//        int porcentajeRam=(int) (((ramUso)*100)/ramTotal);
//        //System.out.println("RAM en uso: "+ramUso+" ("+porcentajeRam+"%)");
//        //System.out.println("RAM disponible: "+ramDisp);
//        
//        
//        
//        //Fecha de arranque
//        String fechaArranque=obj.getEnArranque(datos);
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        System.out.println("Fecha de arranque"+fechaArranque);
//        System.out.println("Fecha de muestra: "+obj.fecha(fechaArranque));
//        
//        
//        //System.out.println("Fecha de arranque del sistema: "+fechaArranque);
//        
//        
//        //Obteniendo las actualizaciones
//        //System.out.println("\n\nActualizaciones instaladas: "+(obj.getUpdates(datos).length-1));
//        
//        
//        
//        
//        //System.out.println(datosS+"\n\n");
//        datosS=obj.removAc(datosS);
//        //System.out.println(datosS);
//        
//        soVer=obj.removAc(soVer);
//        //System.out.println(soVer);
//        
//        String nombre=obj.removAc("Luis Alberto Miranda Morgado!|°&$");
//        //System.out.println(nombre);
//        
//        Bitacora bit=new Bitacora();
//        
//        
//        
//        datosS=datosS.replace("      ", "");
//        
//        
//        bit.setFabricante(obj.removAc(fabricante));
//        bit.setModelo(obj.removAc(modelo));
//        bit.setNoSerie("8CG43602YP");
//        bit.setSo(obj.removAc(so));
//        bit.setSoVer(obj.removAc(soVer));
//        bit.setBiosVer(obj.removAc(bios));
//        bit.setCpuName(obj.removAc(cpu));
//        bit.setHostName(obj.removAc(hostname));
//        bit.setTamanoDisco(tama);
//        bit.setUsoDisco(uso);
//        bit.setLibreDisco(libre);
//        bit.setPorcentageDisco(porcentageUso);
//        bit.setRamTotal(ramTotal);
//        bit.setRamDisp(ramDisp);
//        bit.setRamUso(ramUso);
//        bit.setPorcentageRam(porcentajeRam);
//        bit.setFechaArranque(obj.fecha(fechaArranque));
//        bit.setUpdates(obj.removAc(updatesT));
//        bit.setGeneralInfo("ok");
////        bit.setNombre(nombre);
////        bit.setNoEmpleado("790999");
////        bit.setPuesto("Analista");
////        bit.setIdOficina(1);
//        String idioma=Locale.getDefault().getLanguage();
//        String fechaI=obj.getEnInstalacion(datos);
//        fechaI=obj.fecha(fechaI);
//        String directorio=obj.getEnDirectorio(datos);
//        
//        String info="Idioma: "+idioma+" Serial: "
//                    +bit.getNoSerie()+" Fecha de instalación original: "
//                    +fechaI+" Directorio de Windows: "
//                    +directorio+" Numero de empleado: "
//                    +"790999"
//                +" Actualizaciones: "+updatesT;
//        info=obj.removAc(info);
//        System.out.println(info);
//        
//        datosS=datosS.replace(",", "");
//        System.out.println(datosS+"\n"+datosS.length());
//        bit.setDatos(info);
//        
//        
//        jsonRequest = gson.toJson(bit);
//        String respuestaService = sr.postObject("https://ti.cosmic.mx/api/BitacoraProceso", jsonRequest);
//        System.out.println(respuestaService);
//        
//        if (respuestaService.contains("The string should be correctly escaped")) {
//            
//        }
//
//        String idioma=Locale.getDefault().getLanguage();   
//        System.out.println(idioma);
//        String rutaA = System.getProperty("user.home") + "\\";
//        rutaA=rutaA.concat("batteryReport.html");
//        System.out.println("\n\n");
//        System.out.println(obj.ruta());
//        String rutaF = obj.ruta().concat("report.html");
//        System.out.println(rutaF);
        
//        String rutaC = System.getProperty("user.home") + "\\"+"Documents"+"\\"+"report.html";
//        System.out.println("cmd /c powercfg /batteryreport /output "+rutaC);
//        String reporte=obj.reporteBateria();
//        System.out.println(reporte);

//String[] upd=obj.getUpdates(datos);
//        for (int i = 0; i < upd.length-1; i++) {
////            System.out.println(upd[i]);
//        }
//                    String updatesT="";
//                    ;
//                    if (upd.length>11) {
//                    int mas=upd.length-1-10;
//                    for (int i = upd.length-1-10; i <upd.length-1; i++) {
//                        System.out.println(upd[i]);
//                        upd[i]=upd[i].replace(" ", "");
//                        updatesT=updatesT.concat(upd[i]+"\n");
//                    }
//                    updatesT=updatesT.concat(" y "+mas+" mas");
//                    }
//                    
//                    System.out.println(updatesT);
                    
//                    for (int i = 10; i >= 0; i--) {
//                        System.out.println(i);
//            
//        }

       
        

    }
}
