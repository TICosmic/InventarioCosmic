/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
/**
 *
 * @author Rubén
 */
public class Metodos {
    
    static final String REG_ADD_CMD = "cmd /c reg add \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run\" /v \"{0}\" /d \"{1}\" /t REG_EXPAND_SZ";
    
    private void exec(String[] args) throws Exception{
        if (args.length != 2)
            throw new IllegalArgumentException("\n\nUsage: java SetEnv {key} {value}\n\n");
    
            String key = args[0];
            String value = args[1];
    
            String cmdLine = MessageFormat.format(REG_ADD_CMD, new Object[] { key, value });

            Runtime.getRuntime().exec(cmdLine);
    }
    
    
    public String fecha(String fecha){
        String[] fechas=fecha.split(",");
        String fechaA=fechas[0];
        String horaA=fechas[1];
        
        String[] horaArr=horaA.split(":");
        System.out.println();
        
        
        
        String[] fechaArr=fechaA.split("/");
        if (Locale.getDefault().toString().equals("es_MX") || Locale.getDefault().toString().equals("es_ES")) {
            if (horaA.contains("p.") || horaA.contains("P") ) {
                if (horaArr[0].contains("1")) {
                    horaA="13:"+horaArr[1];
                }else if (horaArr[0].contains("2")) {
                    horaA="14:"+horaArr[1];   
                }else if (horaArr[0].contains("3")) {
                    horaA="15:"+horaArr[1];  
                }else if (horaArr[0].contains("4")) {
                    horaA="16:"+horaArr[1]; 
                }else if (horaArr[0].contains("5")) {
                    horaA="17:"+horaArr[1]; 
                }else if (horaArr[0].contains("6")) {
                    horaA="18:"+horaArr[1]; 
                }else if (horaArr[0].contains("7")) {
                    horaA="19:"+horaArr[1]; 
                }else if (horaArr[0].contains("8")) {
                    horaA="20:"+horaArr[1]; 
                }else if (horaArr[0].contains("9")) {
                    horaA="21:"+horaArr[1]; 
                }else if (horaArr[0].contains("10")) {
                    horaA="22:"+horaArr[1]; 
                }else if (horaArr[0].contains("11")) {
                    horaA="23:"+horaArr[1]; 
                }
            }else if (horaA.contains("a.")|| horaA.contains("A")) {
                if (horaArr[0].contains("12")) {
                    horaA="00:"+horaArr[1];
                }else{
                    String replace = horaArr[0].replace(" ", "");
                    horaA=replace+":"+horaArr[1];
                }
            }else{
                String replace = horaArr[0].replace(" ", "");
                horaA=replace+":"+horaArr[1];
            }
            
            
            
            fechaA=fechaArr[1]+"/"+fechaArr[0]+"/"+fechaArr[2]+" "+horaA;
            
        }else if (Locale.getDefault().toString().equals("es_US") || Locale.getDefault().toString().equals("en_US")){
            if (horaA.contains("p.")|| horaA.contains("P")) {
                if (horaArr[0].contains("1")) {
                    horaA="13:"+horaArr[1];
                }else if (horaArr[0].contains("2")) {
                    horaA="14:"+horaArr[1];   
                }else if (horaArr[0].contains("3")) {
                    horaA="15:"+horaArr[1];  
                }else if (horaArr[0].contains("4")) {
                    horaA="16:"+horaArr[1]; 
                }else if (horaArr[0].contains("5")) {
                    horaA="17:"+horaArr[1]; 
                }else if (horaArr[0].contains("6")) {
                    horaA="18:"+horaArr[1]; 
                }else if (horaArr[0].contains("7")) {
                    horaA="19:"+horaArr[1]; 
                }else if (horaArr[0].contains("8")) {
                    horaA="20:"+horaArr[1]; 
                }else if (horaArr[0].contains("9")) {
                    horaA="21:"+horaArr[1]; 
                }else if (horaArr[0].contains("10")) {
                    horaA="22:"+horaArr[1]; 
                }else if (horaArr[0].contains("11")) {
                    horaA="23:"+horaArr[1]; 
                }
            }else if (horaA.contains("a.")|| horaA.contains("A")) {
                if (horaArr[0].contains("12")) {
                    horaA="00:"+horaArr[1];
                }else{
                    String replace = horaArr[0].replace(" ", "");
                    horaA=replace+":"+horaArr[1];
                }
            }else{
                String replace = horaArr[0].replace(" ", "");
                horaA=replace+":"+horaArr[1];
            }
            
            fechaA=fechaArr[0]+"/"+fechaArr[1]+"/"+fechaArr[2]+" "+horaA;
        }else{
            if (horaA.contains("p.")|| horaA.contains("P")) {
                if (horaArr[0].contains("1")) {
                    horaA="13:"+horaArr[1];
                }else if (horaArr[0].contains("2")) {
                    horaA="14:"+horaArr[1];   
                }else if (horaArr[0].contains("3")) {
                    horaA="15:"+horaArr[1];  
                }else if (horaArr[0].contains("4")) {
                    horaA="16:"+horaArr[1]; 
                }else if (horaArr[0].contains("5")) {
                    horaA="17:"+horaArr[1]; 
                }else if (horaArr[0].contains("6")) {
                    horaA="18:"+horaArr[1]; 
                }else if (horaArr[0].contains("7")) {
                    horaA="19:"+horaArr[1]; 
                }else if (horaArr[0].contains("8")) {
                    horaA="20:"+horaArr[1]; 
                }else if (horaArr[0].contains("9")) {
                    horaA="21:"+horaArr[1]; 
                }else if (horaArr[0].contains("10")) {
                    horaA="22:"+horaArr[1]; 
                }else if (horaArr[0].contains("11")) {
                    horaA="23:"+horaArr[1]; 
                }
            }else if (horaA.contains("a.")|| horaA.contains("A")) {
                if (horaArr[0].contains("12")) {
                    horaA="00:"+horaArr[1];
                }else{
                    String replace = horaArr[0].replace(" ", "");
                    horaA=replace+":"+horaArr[1];
                }
            }else{
                String replace = horaArr[0].replace(" ", "");
                horaA=replace+":"+horaArr[1];
            }
            
            
            
            fechaA=fechaArr[1]+"/"+fechaArr[0]+"/"+fechaArr[2]+" "+horaA;
        }
        
        
        return fechaA;
    }

    public String removAc(String texto) {
        String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ¢!|°&$\n~¤¡\\*";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyyo       ni/ ";
        String output = texto;
        for (int i=0; i<original.length(); i++) {
            // Reemplazamos los caracteres especiales.

            output = output.replace(original.charAt(i), ascii.charAt(i));

        }
        return output;
    }
    
    public String ruta(){
        String dp="";
        dp=System.getProperty("user.home");
        dp=dp.substring(0, 1);
        dp=dp+":\\";
        return dp;
    }
    
    public boolean derechos(){
        boolean ok=false;
        String dp="";
        dp=System.getProperty("user.home");
        dp=dp.substring(0, 1);
        String archivo=dp+":/test.cmd";
        File fichero=new File(archivo);
        if(fichero.exists()){
            System.out.println("El archivo "+archivo+" ya existe");
            if(fichero.delete()){
                ok=true;
                System.out.println("El fichero ha sido borrado satisfactoriamente");
            }else{
                System.out.println("El fichero no puede ser borrado");
            }
        }else{
            System.out.println("No se va a poder jóven");
            
            try{
                if(fichero.createNewFile()){
                    System.out.println("El fichero se ha creado satisfactoriamente");
                    ok=true;
                    fichero.delete();
                }else{
                    System.out.println("No se ha podido crear el fichero");
                }

            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        return ok;
    }
    
    public String getPcName(){
        Runtime r = Runtime.getRuntime();
        String pcName=null;
        try{
            Process p = r.exec("cmd /c hostname");
            InputStreamReader entrada = new InputStreamReader(p.getInputStream());
            BufferedReader cmdInput = new BufferedReader(entrada);
            pcName = cmdInput.readLine();
            
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return pcName;
    }
    
    public String getSerial(){
        Runtime r = Runtime.getRuntime();
        String serial=null;
        try{
            Process p = r.exec("cmd /c wmic csproduct get identifyingnumber");
            InputStreamReader entrada = new InputStreamReader(p.getInputStream());
            BufferedReader cmdInput = new BufferedReader(entrada);
            serial = cmdInput.readLine();
            
            serial = cmdInput.readLine();
            
            serial = cmdInput.readLine();
            
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return serial;
    }
    
    public String getCpuName(){
        Runtime r = Runtime.getRuntime();
        String cpuName=null;
        try{
            Process p = r.exec("cmd /c wmic cpu get name");
            InputStreamReader entrada = new InputStreamReader(p.getInputStream());
            BufferedReader cmdInput = new BufferedReader(entrada);
            //mostramos la salida del comando
            cpuName = cmdInput.readLine();

            cpuName = cmdInput.readLine();

            cpuName = cmdInput.readLine();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return cpuName;
    }
    
    public String getDiskSize(){
        Runtime r = Runtime.getRuntime();
        String size=null;
        try{
            Process p = r.exec("cmd /c wmic diskdrive get size");
            InputStreamReader entrada = new InputStreamReader(p.getInputStream());
            BufferedReader cmdInput = new BufferedReader(entrada);
            //mostramos la salida del comando
            size = cmdInput.readLine();

            size = cmdInput.readLine();

            size = cmdInput.readLine();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return size;
    }
    
    public String getFreeSize(){
        Runtime r = Runtime.getRuntime();
        String size=null;
        try{
            Process p = r.exec("cmd /c wmic LogicalDisk Where DriveType=\"3\" Get FreeSpace");
            InputStreamReader entrada = new InputStreamReader(p.getInputStream());
            BufferedReader cmdInput = new BufferedReader(entrada);
            //mostramos la salida del comando
            size = cmdInput.readLine();

            size = cmdInput.readLine();

            size = cmdInput.readLine();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return size;
    }
    
    public static boolean buscar(String[] strings, String searchString) {
        return Arrays.asList(strings).contains(searchString);
    }
    
    public String existeEnArreglo(String[] arreglo, String busqueda) {
        String error="NO ENCONTRADO";
        for (int x = 0; x < arreglo.length; x++) {
                if (arreglo[x].contains(busqueda)) {
                    return arreglo[x];
            }
        }
        return error;
    }
    
    public int getArrayIndex(String[] arreglo, String busqueda) {
        for (int x = 0; x < arreglo.length; x++) {
            if (arreglo[x].contains(busqueda)) {
            return x;
            }
        }
        return -1;
    }
    
    public String getBiosVer(String[] arreglo){
        String biosV=existeEnArreglo(arreglo, "BIOS");
        String[] bioArr=biosV.split(":");
        String bios=bioArr[bioArr.length-1];
        bios=bios.replace(" ", "");
        return bios;
    }
    
    public String getEnBiosVer(String[] arreglo){
        String biosV=existeEnArreglo(arreglo, "BIOS Version");
        String[] bioArr=biosV.split(":");
        String bios=bioArr[bioArr.length-1];
        bios=bios.replace(" ", "");
        return bios;
    }
    
    public String getVendor(String[] arreglo){
        String vendo=existeEnArreglo(arreglo, "Fabricante del sistema:");
        String[] vendorArr=vendo.split(":");
        String vendor=vendorArr[vendorArr.length-1];
        vendor=vendor.replace(" ", "");
        return vendor;
    }
    
    public String getEnVendor(String[] arreglo){
        String vendo=existeEnArreglo(arreglo, "System Manufacturer");
        String[] vendorArr=vendo.split(":");
        String vendor=vendorArr[vendorArr.length-1];
        vendor=vendor.replace(" ", "");
        return vendor;
    }
    
    public String getModelo(String[] arreglo){
        String mod=existeEnArreglo(arreglo, "Modelo el sistema");
        String[] modArr=mod.split(":");
        String modelo=modArr[modArr.length-1];
        modelo=modelo.replace(" ", "");
        return modelo;
    }
    
    public String getEnModelo(String[] arreglo){
        String mod=existeEnArreglo(arreglo, "System Model");
        String[] modArr=mod.split(":");
        String modelo=modArr[modArr.length-1];
        modelo=modelo.replace(" ", "");
        return modelo;
    }
    
    public String getSo(String[] arreglo){
        String soC=existeEnArreglo(arreglo, "Nombre del sistema operativo");
        String[] soArr=soC.split(":");
        String so=soArr[soArr.length-1];
        so=so.replace(" ", "");
        return so;
    }
    
    public String getEnSo(String[] arreglo){
        String soC=existeEnArreglo(arreglo, "OS Name");
        String[] soArr=soC.split(":");
        String so=soArr[soArr.length-1];
        so=so.replace(" ", "");
        return so;
    }
    
    public String getDirectorio(String[] arreglo){
        String directorio=existeEnArreglo(arreglo, "Directorio de Windows");
        String[] soArr=directorio.split("ws:");
        String directorioW=soArr[soArr.length-1];
        directorioW=directorioW.replace(" ", "");
        return directorioW;
    }
    
    public String getEnDirectorio(String[] arreglo){
        String directorio=existeEnArreglo(arreglo, "Windows Directory");
        String[] soArr=directorio.split("ry:");
        String directorioW=soArr[soArr.length-1];
        directorioW=directorioW.replace(" ", "");
        return directorioW;
    }
    
    public String getSoVer(String[] arreglo){
        String soC=existeEnArreglo(arreglo, "n del sistema operativo");
        String[] soArr=soC.split(":");
        String so=soArr[soArr.length-1];
        so=so.replace(" ", "");
        return so;
    }
    
    public String getEnSoVer(String[] arreglo){
        String soC=existeEnArreglo(arreglo, "OS Version");
        String[] soArr=soC.split(":");
        String so=soArr[soArr.length-1];
        so=so.replace(" ", "");
        return so;
    }
    
    public String getRam(String[] arreglo){
        String ramT=existeEnArreglo(arreglo,"Cantidad total de memoria");
        String[] ramArr=ramT.split(":");
        String ram=ramArr[ramArr.length-1];
        ram=ram.replace(" ", "");
        return ram;
    }
    
    public String getEnRam(String[] arreglo){
        String ramT=existeEnArreglo(arreglo,"Total Physical Memory");
        String[] ramArr=ramT.split(":");
        String ram=ramArr[ramArr.length-1];
        ram=ram.replace(" ", "");
        return ram;
    }
    
    public String getRamDisp(String[] arreglo){
        String ramT=existeEnArreglo(arreglo,"sica disponible:");
        String[] ramArr=ramT.split(":");
        String ram=ramArr[ramArr.length-1];
        ram=ram.replace(" ", "");
        return ram;
    }
    
    public String getEnRamDisp(String[] arreglo){
        String ramT=existeEnArreglo(arreglo,"Available Physical Memory");
        String[] ramArr=ramT.split(":");
        String ram=ramArr[ramArr.length-1];
        ram=ram.replace(" ", "");
        return ram;
    }
    
    public int ramInt(String cadena){
        
        int largo=cadena.length();
        String subcadena=cadena.substring(0, largo-2);
        if(subcadena.contains(",")){
            String[] elements=subcadena.split(",");
            String ram=elements[0].concat(elements[1]);
            int num=Integer.parseInt(ram);
            return num;
        }else{
            int num=Integer.parseInt(subcadena);
            return num;
        }
        
        
        
        
    }
    
    public String getArranque(String[] arreglo){
        String fecha=existeEnArreglo(arreglo,"Tiempo de arranque del sistema");
        String[] arranque=fecha.split("a: ");
        String fechaArranque=arranque[arranque.length-1];
        fechaArranque=fechaArranque.replace(" ", "");
        return fechaArranque;
    }
    
    public String getEnArranque(String[] arreglo){
        String fecha=existeEnArreglo(arreglo,"System Boot Time");
        String[] arranque=fecha.split("e: ");
        String fechaArranque=arranque[arranque.length-1];
        fechaArranque=fechaArranque.replace(" ", "");
        return fechaArranque;
    }
    
    public String getInstalacion(String[] arreglo){
        String fecha=existeEnArreglo(arreglo,"n original:");
        String[] arranque=fecha.split("al:");
        String fechaArranque=arranque[arranque.length-1];
        fechaArranque=fechaArranque.replace(" ", "");
        return fechaArranque;
    }
    
    public String getEnInstalacion(String[] arreglo){
        String fecha=existeEnArreglo(arreglo,"Original Install Date");
        String[] arranque=fecha.split("te:");
        String fechaArranque=arranque[arranque.length-1];
        fechaArranque=fechaArranque.replace(" ", "");
        return fechaArranque;
    }
    
    public String[] getUpdates(String[] arreglo){
        int x=0;
        for (int i = 0; i < arreglo.length-1; i++) {
            if(arreglo[i].contains("]: KB")){
                x++;
            }
        }
        x=x+1;
        String[] updates= new String[x];
        
        x=0;
        for (int i = 0; i < arreglo.length; i++) {
            if(arreglo[i].contains("]: KB")){
                updates[x]=arreglo[i];
                x++;
            }
        }
            
        return updates;
    }
        
        
        
    
    
    
    public String[] getSysInfo(){
        Runtime r = Runtime.getRuntime();
        String[] sysInfo;
        int i=0;
        
        String dato;
        try {
            Process p = r.exec("cmd /c systeminfo");
            InputStreamReader entrada = new InputStreamReader(p.getInputStream());
            BufferedReader cmdInput = new BufferedReader(entrada);
            //mostramos la salida del comando
            if ((dato=cmdInput.readLine()) != null) {
                while ((dato=cmdInput.readLine()) != null) {
                    //Verificando que los datos obtenidos estén bien                    
                    //System.out.println(i+": "+dato);
                    i++;
                }
            }

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        i=i+1;
        
        sysInfo=new String[i];
        i=0;
        try {
            Process p = r.exec("cmd /c systeminfo");
            InputStreamReader entrada = new InputStreamReader(p.getInputStream());
            BufferedReader cmdInput = new BufferedReader(entrada);
            //mostramos la salida del comando
            if (cmdInput.readLine() != null) {
                while ((sysInfo[i]=cmdInput.readLine()) != null) {
                    i++;
                }
            }

        } catch (IOException ex) {
            ex.getStackTrace();
        }
        
        return sysInfo;
    }
    
    public String reporteBateria(){
        Runtime r = Runtime.getRuntime();
        String resultado=null;
        String rutaA = System.getProperty("user.home") + "\\"+"Documents"+"\\"+"report.html";
        
        String rutaF = ruta();
        String comando="cmd /c powercfg /batteryreport /output ";
        comando=comando.concat(rutaA);
        try{
            Process p = r.exec(comando);
            InputStreamReader entrada = new InputStreamReader(p.getInputStream());
            BufferedReader cmdInput = new BufferedReader(entrada);
            //mostramos la salida del comando
            resultado = cmdInput.readLine();
        }catch (IOException ex) {
            ex.getStackTrace();
        }
        return resultado;
    }
    
    public void abrirarchivo(String archivo){
        try {

            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);

        }catch (IOException ex) {

            System.out.println(ex);

        }

    }  
    
}
