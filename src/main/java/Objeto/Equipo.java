/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

import Inventario.Metodos;

/**
 *
 * @author Rubén
 */
public class Equipo {
    Metodos obj=new Metodos();
    String datos; //Cadena larga con toda la información del equipo
    String fabricante; //marca del equipo
    String modelo; //modelo del equipo
    String noSerie; //Número de serie del equipo
    String so; //Sistema operativo
    String soVer; //Versión del sistema opertivo
    String biosVer; //Versión del bios
    String cpuName; //marca y modelo del procesador
    String hostName; //nombre del equipo
    double tamanoDisco; //espacio total del disco
    double usoDisco; //espacio en uso del disco
    double libreDisco; //espacio libre del disco
    int porcentageDisco; //porcentage de uso del disco
    int ramTotal; //ram total
    int ramDisp; //ram disponible
    int ramUso; //ram en uso
    int porcentageRam; //porcentage de uso de ram
    String fechaArranque; //fecha del ultimo reinicio
    String updates; //actualizaciones instaladas
    String generalInfo;
    
    String nombre;
    String noEmpleado;
    String puesto;
    int idOficina;
    
    public void setGeneralInfo(String generalInfo){
        this.generalInfo=generalInfo;
    }
    
    public String getGeneralInfo(){
        return generalInfo;
    }
    
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public String getNoEmpleado(){
        return noEmpleado;
    }
    
    public void setNoEmpleado(String noEmpleado){
        this.noEmpleado=noEmpleado;
    }
    
    public String getPuesto(){
        return puesto;
    }
    
    public void setPuesto(String puesto){
        this.puesto=puesto;
    }
    
    public int getIdOficina(){
        return idOficina;
    }
    
    public void setIdOficina(int oficina){
        this.idOficina=oficina;
    }
    
    public String getDatos(){
        return datos;
    }
    
    public String getFechaArranque(){
        return fechaArranque;
    }
    
    public void setFechaArranque(String fechaArranque){
        this.fechaArranque=fechaArranque;
    }
    
    public String getUpdates(){
        return updates;
    }
    
    public void setUpdates(String updates){
        this.updates=updates;
    }
    
    public void setDatos(String datos){
        this.datos=datos;
    }
    
    public String getFabricante(){
        return fabricante;
    }
    
    public void setFabricante(String fabricante){
        this.fabricante=fabricante;
    }
    
    public String getModelo(){
        return modelo;
    }
    
    public void setModelo(String modelo){
        this.modelo=modelo;
    }
    
    public String getSo(){
        return so;
    }
    
    public void setSo(String so){
        this.so=so;
    }
    
    public String getSoVer(){
        return soVer;
    }
    
    public void setSoVer(String soVer){
        this.soVer=soVer;
    }
    
    public String getNoSerie(){
        return noSerie;
    }
    
    public void setNoSerie(String noSerie){
        this.noSerie=noSerie;
    }
    
    public String getBiosVer(){
        return biosVer;
    }
    
    public void setBiosVer(String biosVer){
        this.biosVer=biosVer;
    }
    
    public String getCpuName(){
        return cpuName;
    }
    
    public void setCpuName(String cpuName){
        this.cpuName=cpuName;
    }
    
    public String getHostname(){
        return hostName;
    }
    
    public void setHostname(String hostName){
        this.hostName=hostName;
    }
    
    public double getTamanoDisco(){
        return tamanoDisco;
    }
    
    public void setTamanoDisco(double tamanoDisco){
        this.tamanoDisco=tamanoDisco;
    }
    
    public double getUsoDisco(){
        return usoDisco;
    }
    
    public void setUsoDisco(double usoDisco){
        this.usoDisco=usoDisco;
    }
    
    public double getLibreDisco(){
        return libreDisco;
    }
    
    public void setLibreDisco(double libreDisco){
        this.libreDisco=libreDisco;
    }
    
    public int getPorcentageDisco(){
        return porcentageDisco;
    }
    
    public void setPorcentageDisco(int porcentageDisco){
        this.porcentageDisco=porcentageDisco;
    }
    
    public int getRamTotal(){
        return ramTotal;
    }
    
    public void setRamTotal(int ramTotal){
        this.ramTotal=ramTotal;
    }
    
    public int getRamUso(){
        return ramUso;
    }
    
    public void setRamUso(int ramUso){
        this.ramUso=ramUso;
    }
    
    public int getRamDisp(){
        return ramDisp;
    }
    
    public void setRamDisp(int ramDisp){
        this.ramDisp=ramDisp;
    }
    
    public int getPorcentageRam(){
        return porcentageRam;
    }
    
    public void setPorcentageRam(int porcentageRam){
        this.porcentageRam=porcentageRam;
    }
}
