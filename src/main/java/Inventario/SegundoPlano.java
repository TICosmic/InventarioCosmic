/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import Fuentes.ServiceResponse;
import javax.swing.ImageIcon;
import java.awt.AWTException;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Objeto.Bitacora;
import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Locale;

/**
 *
 * @author Rubén
 */
public class SegundoPlano extends javax.swing.JFrame {

    /**
     * Creates new form Vista
     */
    private TrayIcon iconT;
    private SystemTray tray;
    private ImageIcon icon = new ImageIcon(this.getClass().getResource("/cosmic.png"));

    Gson gson = new Gson();
    String jsonRequest;
    ServiceResponse sr = new ServiceResponse();

    //procesos en segundo plano
    String noSerie;
    String datos;
    String fabricante;
    String modelo;
    String so;
    String soVer;
    String biosVer;
    String cpuName;
    String hostName;
    double tamanoDisco;
    double usoDisco;
    double libreDisco;
    int porcentageDisco;
    int ramTotal;
    int ramDisp;
    int ramUso;
    int porcentageRam;
    String fechaArranque;
    String generalInfo;
    String updates;

    String idioma = Locale.getDefault().getLanguage();

    Bitacora bit = new Bitacora();
    Ventana ven = new Ventana();

    IniciarAdmin in = new IniciarAdmin();

//    ImageIcon pause=new ImageIcon(ClassLoader.getSystemResource("Fuentes/pause.png"));
//    ImageIcon play=new ImageIcon(ClassLoader.getSystemResource("Fuentes/play.png"));
    ImageIcon admin = new ImageIcon(this.getClass().getResource("/admin2.png"));
    ImageIcon registro = new ImageIcon(this.getClass().getResource("/registro.png"));
    ImageIcon btnUpd = new ImageIcon(this.getClass().getResource("/botonActualizar.png"));
    ImageIcon hide = new ImageIcon(this.getClass().getResource("/hide.png"));
    ImageIcon subir = new ImageIcon(this.getClass().getResource("/upload.png"));
    ImageIcon ok = new ImageIcon(this.getClass().getResource("/check.png"));
    ImageIcon fail = new ImageIcon(this.getClass().getResource("/fail.png"));
    //ImageIcon admin=new ImageIcon(getClass().getResource("Fuentes/admin2.png"));
    ImageIcon verc=new ImageIcon(this.getClass().getResource("/cosmic2.png"));
    Timer tim = new Timer();
    Timer tim2 = new Timer();

    public void segundoPlano() {//Constructor
        try {
            if (SystemTray.isSupported()) {
                tray.add(iconT);
                this.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void instanciarTray() {
        iconT = new TrayIcon(icon.getImage(), "Ejecutando", pop);
        tray = SystemTray.getSystemTray();
    }

    public SegundoPlano() throws AWTException {//Constructor
        initComponents();

        this.getContentPane().setBackground(new Color(178, 22, 33));
        setLocationRelativeTo(null);
        //icon=new ImageIcon(getClass().getResource("Fuentes/cosmic.png"));
        
        
        this.setIconImage(icon.getImage());
        terminar.setIcon(admin);
        registrar.setIcon(registro);
        actualiza.setIcon(btnUpd);
        sp.setIcon(hide);
        ejecutar.setIcon(subir);
        acerca.setIcon(icon);
        instanciarTray();
        this.setTitle("Inventario Cosmic 2.2");

        Metodos obj = new Metodos();

        //Habilitar inicio automático
        try {
            System.out.println(obj.habilitarInicio());
        } catch (Exception e) {
            System.out.println("Error al habilitar inicio automático");
        }

        //Iniciar timer
        tim.schedule(new TimerTask() {
            @Override
            public void run() {
                String[] dat = obj.getSysInfo();
                dat[dat.length - 1] = "Espacio vacío";

                String mensaje = obj.buscarActualizacion();
                if (mensaje.contains("Error")) {
                    upd.setIcon(fail);
                } else {
                    upd.setIcon(ok);
                }
                upd.setText(mensaje);

                if (idioma.contains("es")) {

                    //Datos del equipo
                    marca.setText(obj.getVendor(dat));
                    modeloPC.setText(obj.getModelo(dat));
                    systemO.setText(obj.getSo(dat));
                    systemVer.setText(obj.getSoVer(dat));
                    if (obj.getSerial().contains("0") && obj.getPcName().contains("DESKTOP-CG6CU8U")) {
                        serial.setText("8CG43602YP");
                        noSerie = "8CG43602YP";
                    } else {
                        serial.setText(obj.getSerial());
                        noSerie = obj.getSerial();
                    }
                    reboot.setText(obj.getArranque(dat));

                    //datos de la ram
                    int ramT = obj.ramInt(obj.getRam(dat));
                    int ramDispo = obj.ramInt(obj.getRamDisp(dat));
                    int ramUs = ramT - ramDispo;
                    int porcentaje = (int) (((ramUs) * 100) / ramT);
                    ramTot.setText("" + ramT + " MB");
                    ramEnUso.setText("" + ramUs + " MB");
                    porcent.setText("" + porcentaje + "%");
                    ramDisponible.setText("" + ramDispo + " MB");

                    //datos del disco
                    String diskS = obj.getDiskSize().substring(0, obj.getDiskSize().length() - 2);
                    double disk = Double.parseDouble(diskS);
                    double diskSize = disk / 1024 / 1024 / 1024;

                    String freeDisk = obj.getFreeSize().substring(0, obj.getFreeSize().length() - 2);
                    double free = Double.parseDouble(freeDisk);
                    double diskFree = free / 1024 / 1024 / 1024;
                    double usageDisk = diskSize - diskFree;
                    int porcentageUso = (int) ((usageDisk * 100) / diskSize);

                    double tama = Math.round(diskSize);
                    double libre = Math.round(diskFree);
                    double uso = Math.round(usageDisk);

                    discoT.setText("" + tama + " GB");
                    discoU.setText("" + uso + " GB");
                    porcentD.setText("" + porcentageUso + "%");
                    discoD.setText("" + libre + " GB");

                    //Datos OK
                    String[] upd = obj.getUpdates(dat);
                    String updatesT = "";

                    if (upd.length > 15) {
                        int mas = upd.length - 1 - 15;
                        for (int i = upd.length - 1 - 15; i < upd.length - 1; i++) {
                            upd[i] = upd[i].replace(" ", "");
                            updatesT = updatesT.concat(upd[i] + "\n");
                        }
                        updatesT = updatesT.concat(" y " + mas + " mas");
                    } else {
                        for (int i = 0; i < upd.length - 1; i++) {
                            upd[i] = upd[i].replace(" ", "");
                            updatesT = updatesT.concat(upd[i] + "\n");
                        }
                    }

                    fabricante = obj.removAc(obj.getVendor(dat));
                    modelo = obj.removAc(obj.getModelo(dat));
                    so = obj.removAc(obj.getSo(dat));
                    soVer = obj.removAc(obj.getSoVer(dat));
                    biosVer = obj.removAc(obj.getBiosVer(dat));
                    cpuName = obj.removAc(obj.getCpuName());
                    hostName = obj.removAc(obj.getPcName());
                    tamanoDisco = tama;
                    usoDisco = uso;
                    libreDisco = libre;
                    porcentageDisco = porcentageUso;
                    ramTotal = ramT;
                    ramDisp = ramDispo;
                    ramUso = ramUs;
                    porcentageRam = porcentaje;
                    fechaArranque = obj.fecha(obj.getArranque(dat));
                    generalInfo = "OK";
                    updates = obj.removAc(updatesT);

                    bit.setNoSerie(noSerie);
                    //bit.setDatos("");
                    bit.setFabricante(fabricante);
                    bit.setModelo(modelo);
                    bit.setSo(so);
                    bit.setSoVer(soVer);
                    bit.setBiosVer(biosVer);
                    bit.setCpuName(cpuName);
                    bit.setHostName(hostName);
                    bit.setTamanoDisco(tama);
                    bit.setUsoDisco(uso);
                    bit.setLibreDisco(libre);
                    bit.setPorcentageDisco(porcentageDisco);
                    bit.setRamTotal(ramTotal);
                    bit.setRamDisp(ramDisp);
                    bit.setRamUso(ramUso);
                    bit.setPorcentageRam(porcentageRam);
                    bit.setFechaArranque(fechaArranque);
                    bit.setGeneralInfo(generalInfo);
                    bit.setUpdates(updates);

                    String fechaI = obj.getInstalacion(dat);
                    fechaI = obj.fecha(fechaI);
                    String directorio = obj.getDirectorio(dat);

                    String info = "Idioma: " + idioma + " Serial: "
                            + noSerie + " Fecha de instalación original: "
                            + fechaI + " Directorio de Windows: "
                            + directorio + " Actualizaciones: "
                            + updates;

                    info = obj.removAc(info);
                    //info=info.replace(" ", "");

                    info = "ok".concat(info);

                    bit.setDatos(info);

                    jsonRequest = gson.toJson(bit);
                    String respuestaService = sr.postObject("https://ti.cosmic.mx/api/BitacoraProceso", jsonRequest);
                    System.out.println(respuestaService);

                    //Hasta aquí datos ok
                } else {

                    //Datos del equipo
                    marca.setText(obj.getEnVendor(dat));
                    modeloPC.setText(obj.getEnModelo(dat));
                    systemO.setText(obj.getEnSo(dat));
                    systemVer.setText(obj.getEnSoVer(dat));
                    
                    serial.setText(obj.getSerial());
                    noSerie = obj.getSerial();
                    
                    reboot.setText(obj.getEnArranque(dat));

                    //datos de la ram
                    int ramT = obj.ramInt(obj.getEnRam(dat));
                    ramTot.setText("" + ramT + " MB");
                    int ramDispo = obj.ramInt(obj.getEnRamDisp(dat));
                    int ramUs = ramT - ramDispo;
                    int porcentaje = (int) (((ramUs) * 100) / ramT);
                    ramEnUso.setText("" + ramUs + " MB");
                    porcent.setText("" + porcentaje + "%");
                    ramDisponible.setText("" + ramDispo + " MB");

                    //datos del disco
                    String diskS = obj.getDiskSize().substring(0, obj.getDiskSize().length() - 2);
                    double disk = Double.parseDouble(diskS);
                    double diskSize = disk / 1024 / 1024 / 1024;
                    discoT.setText("" + diskSize + " GB");
                    String freeDisk = obj.getFreeSize().substring(0, obj.getFreeSize().length() - 2);
                    double free = Double.parseDouble(freeDisk);
                    double diskFree = free / 1024 / 1024 / 1024;
                    double usageDisk = diskSize - diskFree;
                    int porcentageUso = (int) ((usageDisk * 100) / diskSize);
                    discoU.setText("" + usageDisk + " GB");
                    porcentD.setText("" + porcentageUso + "%");
                    discoD.setText("" + diskFree + " GB");

                    double tama = Math.round(diskSize);
                    double libre = Math.round(diskFree);
                    double uso = Math.round(usageDisk);

                    String datosS = " ";
                    for (int i = 0; i < dat.length - 1; i++) {
                        //System.out.println(datos[i]);
                        datosS = datosS.concat(dat[i] + "\n");
                    }
                    datosS = obj.removAc(datosS);

                    datos = datosS;
                    fabricante = obj.removAc(obj.getEnVendor(dat));
                    modelo = obj.removAc(obj.getEnModelo(dat));
                    so = obj.removAc(obj.getEnSo(dat));
                    soVer = obj.removAc(obj.getEnSoVer(dat));
                    biosVer = obj.removAc(obj.getEnBiosVer(dat));
                    cpuName = obj.removAc(obj.getCpuName());
                    hostName = obj.removAc(obj.getPcName());
                    tamanoDisco = tama;
                    usoDisco = uso;
                    libreDisco = libre;
                    porcentageDisco = porcentageUso;
                    ramTotal = ramT;
                    ramDisp = ramDispo;
                    ramUso = ramUs;
                    porcentageRam = porcentaje;
                    fechaArranque = obj.fecha(obj.getEnArranque(dat));
                    generalInfo = "";
                    String[] upd = obj.getUpdates(dat);
                    String updatesT = "";

                    if (upd.length > 15) {
                        int mas = upd.length - 1 - 15;
                        for (int i = upd.length - 1 - 15; i < upd.length - 1; i++) {
                            upd[i] = upd[i].replace(" ", "");
                            updatesT = updatesT.concat(upd[i] + "\n");
                        }
                        updatesT = updatesT.concat(" y " + mas + " mas");
                    } else {
                        for (int i = 0; i < upd.length - 1; i++) {
                            upd[i] = upd[i].replace(" ", "");
                            updatesT = updatesT.concat(upd[i] + "\n");
                        }
                    }

                    updates = updatesT;

                    bit.setNoSerie(noSerie);
                    //bit.setDatos("");
                    bit.setFabricante(fabricante);
                    bit.setModelo(modelo);
                    bit.setSo(so);
                    bit.setSoVer(soVer);
                    bit.setBiosVer(biosVer);
                    bit.setCpuName(cpuName);
                    bit.setHostName(hostName);
                    bit.setTamanoDisco(tamanoDisco);
                    bit.setUsoDisco(usoDisco);
                    bit.setLibreDisco(libreDisco);
                    bit.setPorcentageDisco(porcentageDisco);
                    bit.setRamTotal(ramTotal);
                    bit.setRamDisp(ramDisp);
                    bit.setRamUso(ramUso);
                    bit.setPorcentageRam(porcentageRam);
                    bit.setFechaArranque(fechaArranque);
                    bit.setGeneralInfo(generalInfo);
                    bit.setUpdates(updates);

                    String fechaI = obj.getEnInstalacion(dat);
                    fechaI = obj.fecha(fechaI);
                    String directorio = obj.getEnDirectorio(dat);

                    String info = "Idioma: " + idioma + " Serial: "
                            + noSerie + " Fecha de instalación original: "
                            + fechaI + " Directorio de Windows: "
                            + directorio + " Actualizaciones: "
                            + updates;

                    info = obj.removAc(info);
                    //info=info.replace(" ", "");

                    info = "ok ".concat(info);

                    bit.setDatos(info);

                    jsonRequest = gson.toJson(bit);
                    String respuestaService = sr.postObject("https://ti.cosmic.mx/api/BitacoraProceso", jsonRequest);
                    System.out.println(respuestaService);

                }

            }

        }, 0, 14400000);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop = new java.awt.PopupMenu();
        abrir = new java.awt.MenuItem();
        salir = new java.awt.MenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ramTot = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ramEnUso = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ramDisponible = new javax.swing.JLabel();
        porcent = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        discoT = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        discoU = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        discoD = new javax.swing.JLabel();
        porcentD = new javax.swing.JLabel();
        sp = new javax.swing.JButton();
        terminar = new javax.swing.JButton();
        marca = new javax.swing.JLabel();
        modeloPC = new javax.swing.JLabel();
        systemO = new javax.swing.JLabel();
        systemVer = new javax.swing.JLabel();
        serial = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        reboot = new javax.swing.JLabel();
        ejecutar = new javax.swing.JButton();
        registrar = new javax.swing.JButton();
        actualiza = new javax.swing.JButton();
        upd = new javax.swing.JLabel();
        acerca = new javax.swing.JButton();

        pop.setLabel("Menú");
        pop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popActionPerformed(evt);
            }
        });

        abrir.setLabel("Abrir");
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        pop.add(abrir);

        salir.setLabel("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        pop.add(salir);

        pop.getAccessibleContext().setAccessibleParent(abrir);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(233, 234, 236));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Memoria RAM total:");

        ramTot.setText("_______");

        jLabel2.setText("Memoria RAM en uso:");

        ramEnUso.setText("_______");

        jLabel3.setText("Memoria RAM disponible:");

        ramDisponible.setText("_______");

        porcent.setText("__%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(95, 95, 95)
                        .addComponent(ramTot))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ramEnUso))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ramDisponible)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(porcent)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ramTot))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ramEnUso)
                    .addComponent(porcent))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ramDisponible))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setText("Espacio en disco total:");

        discoT.setText("_______");

        jLabel5.setText("Espacio en uso:");

        discoU.setText("_______");

        jLabel6.setText("Espacio disponible:");

        discoD.setText("_______");

        porcentD.setText("__%");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(95, 95, 95)
                        .addComponent(discoT))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(discoU))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(discoD)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(porcentD)
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(discoT))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(discoU)
                    .addComponent(porcentD))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(discoD))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        sp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                spMouseEntered(evt);
            }
        });
        sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spActionPerformed(evt);
            }
        });

        terminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                terminarMouseEntered(evt);
            }
        });
        terminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarActionPerformed(evt);
            }
        });

        marca.setText("marca");

        modeloPC.setText("modelo");

        systemO.setText("so");

        systemVer.setText("so ver");

        serial.setText("serial");

        jLabel7.setText("Último reinicio:");

        reboot.setText("last boot");

        ejecutar.setText("Enviar datos");
        ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarActionPerformed(evt);
            }
        });

        registrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registrarMouseEntered(evt);
            }
        });
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        actualiza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                actualizaMouseEntered(evt);
            }
        });
        actualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizaActionPerformed(evt);
            }
        });

        upd.setText("actualizaciones");

        acerca.setForeground(new java.awt.Color(214, 217, 223));
        acerca.setBorder(null);
        acerca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        acerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acercaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(reboot))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(actualiza)
                                .addGap(18, 18, 18)
                                .addComponent(registrar)
                                .addGap(18, 18, 18)
                                .addComponent(terminar)
                                .addGap(18, 18, 18)
                                .addComponent(sp))
                            .addComponent(upd, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(marca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modeloPC)
                        .addGap(18, 18, 18)
                        .addComponent(systemO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(systemVer)
                        .addGap(18, 18, 18)
                        .addComponent(serial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acerca)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marca)
                    .addComponent(modeloPC)
                    .addComponent(systemO)
                    .addComponent(systemVer)
                    .addComponent(serial)
                    .addComponent(acerca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(actualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(registrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(terminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 34, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ejecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(reboot)
                            .addComponent(upd))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed
        tray.remove(iconT);
        this.setVisible(true);
        //this.setVisible(true);
    }//GEN-LAST:event_abrirActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        in.setVisible(true);
    }//GEN-LAST:event_salirActionPerformed

    private void popActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_popActionPerformed

    private void ejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarActionPerformed
        Metodos met = new Metodos();
        setCursor(new Cursor(3));
        try {

            if (idioma.contains("es")) {

                String[] datos = met.getSysInfo();
                datos[datos.length - 1] = "Espacio vacío";

                String datosS = " ";

                for (int i = 0; i < datos.length - 1; i++) {
                    //System.out.println(datos[i]);
                    datosS = datosS.concat(datos[i] + "\n");
                }

                int ramT = met.ramInt(met.getRam(datos));

                int ramDispo = met.ramInt(met.getRamDisp(datos));
                int ramUs = ramT - ramDispo;
                int porcentaje = (int) (((ramUs) * 100) / ramT);

                String diskS = met.getDiskSize().substring(0, met.getDiskSize().length() - 2);
                double disk = Double.parseDouble(diskS);
                double diskSize = disk / 1024 / 1024 / 1024;

                String freeDisk = met.getFreeSize().substring(0, met.getFreeSize().length() - 2);
                double free = Double.parseDouble(freeDisk);
                double diskFree = free / 1024 / 1024 / 1024;
                double usageDisk = diskSize - diskFree;
                int porcentageUso = (int) ((usageDisk * 100) / diskSize);

                String[] upd = met.getUpdates(datos);
                String updatesT = "";

                if (upd.length > 15) {
                    int mas = upd.length - 1 - 15;
                    for (int i = upd.length - 1 - 15; i < upd.length - 1; i++) {
                        upd[i] = upd[i].replace(" ", "");
                        updatesT = updatesT.concat(upd[i] + "\n");
                    }
                    updatesT = updatesT.concat(" y " + mas + " mas");
                } else {
                    for (int i = 0; i < upd.length - 1; i++) {
                        upd[i] = upd[i].replace(" ", "");
                        updatesT = updatesT.concat(upd[i] + "\n");
                    }
                }

                if (met.getSerial().contains("0") && met.getPcName().contains("DESKTOP-CG6CU8U")) {
                    noSerie = "8CG43602YP";
                } else {
                    noSerie = met.getSerial();
                }

                //this.datos=met.removAc(datosS);
                fabricante = met.removAc(met.getVendor(datos));
                modelo = met.removAc(met.getModelo(datos));
                so = met.removAc(met.getSo(datos));
                soVer = met.removAc(met.getSoVer(datos));
                biosVer = met.removAc(met.getBiosVer(datos));
                cpuName = met.removAc(met.getCpuName());
                hostName = met.removAc(met.getPcName());
                tamanoDisco = diskSize;
                usoDisco = usageDisk;
                libreDisco = diskFree;
                porcentageDisco = porcentageUso;
                ramTotal = ramT;
                ramDisp = ramDispo;
                ramUso = ramUs;
                porcentageRam = porcentaje;
                fechaArranque = met.fecha(met.getArranque(datos));
                generalInfo = "OK";
                updates = met.removAc(updatesT);

                double tama = Math.round(tamanoDisco);
                double libre = Math.round(libreDisco);
                double uso = Math.round(usoDisco);

                bit.setNoSerie(noSerie);
                //bit.setDatos("");
                bit.setFabricante(fabricante);
                bit.setModelo(modelo);
                bit.setSo(so);
                bit.setSoVer(soVer);
                bit.setBiosVer(biosVer);
                bit.setCpuName(cpuName);
                bit.setHostName(hostName);
                bit.setTamanoDisco(tama);
                bit.setUsoDisco(uso);
                bit.setLibreDisco(libre);
                bit.setPorcentageDisco(porcentageDisco);
                bit.setRamTotal(ramTotal);
                bit.setRamDisp(ramDisp);
                bit.setRamUso(ramUso);
                bit.setPorcentageRam(porcentageRam);
                bit.setFechaArranque(fechaArranque);
                bit.setGeneralInfo(generalInfo);
                bit.setUpdates(updates);

                String fechaI = met.getInstalacion(datos);
                fechaI = met.fecha(fechaI);
                String directorio = met.getDirectorio(datos);

                String info = "Idioma: " + idioma + " Serial: "
                        + noSerie + " Fecha de instalación original: "
                        + fechaI + " Directorio de Windows: "
                        + directorio + " Actualizaciones: "
                        + updates;

                info = met.removAc(info);
                //info=info.replace(" ", "");

                info = "ok".concat(info);

                bit.setDatos(info);

                jsonRequest = gson.toJson(bit);
                String respuestaService = sr.postObject("https://ti.cosmic.mx/api/BitacoraProceso", jsonRequest);
                System.out.println(respuestaService);

                if (respuestaService.contains("Guardado exitoso")) {
                    JOptionPane.showMessageDialog(null, "Registro exitoso, se enviaron los datos: \n"
                            + "Número de serie: " + bit.getNoSerie()
                            + "\nFabricante: " + bit.getFabricante()
                            + "\nModelo: " + bit.getModelo()
                            + "\nSO: " + bit.getSo()
                            + "\nSO ver.: " + bit.getSoVer()
                            + "\nBIOS: ver.: " + bit.getBiosVer()
                            + "\nCPU name: " + bit.getCpuName()
                            + "\nHostname: " + bit.getHostName()
                            + "\nTamaño disco: " + bit.getTamanoDisco()
                            + "\nDisco en uso: " + bit.getUsoDisco()
                            + " Porcentaje: " + bit.getPorcentageDisco()
                            + "\nDisco libre: " + bit.getLibreDisco()
                            + "\nRam total: " + bit.getRamTotal()
                            + "\nRam en uso: " + bit.getRamUso()
                            + " Porcentaje: " + bit.getPorcentageRam()
                            + "\nRam disponible: " + bit.getRamDisp()
                            + "\nFecha de arranque: " + bit.getFechaArranque()
                            //+"\nActualizaciones: "+bit.getUpdates()
                            //+"\nArchivo de datos: "+bit.getDatos()
                            + "\nGeneralInfo: " + bit.getGeneralInfo());
                } else {
                    JOptionPane.showMessageDialog(null, "Error -1\n -" + respuestaService + "- "
                            + "\nNúmero de serie: " + bit.getNoSerie()
                            + "\nFabricante: " + bit.getFabricante()
                            + "\nModelo: " + bit.getModelo()
                            + "\nSO: " + bit.getSo()
                            + "\nSO ver.: " + bit.getSoVer()
                            + "\nBIOS: ver.: " + bit.getBiosVer()
                            + "\nCPU name: " + bit.getCpuName()
                            + "\nHostname: " + bit.getHostName()
                            + "\nTamaño disco: " + bit.getTamanoDisco()
                            + "\nDisco en uso: " + bit.getUsoDisco()
                            + " Porcentaje: " + bit.getPorcentageDisco()
                            + "\nDisco libre: " + bit.getLibreDisco()
                            + "\nRam total: " + bit.getRamTotal()
                            + "\nRam en uso: " + bit.getRamUso()
                            + " Porcentaje: " + bit.getPorcentageRam()
                            + "\nRam disponible: " + bit.getRamDisp()
                            + "\nFecha de arranque: " + bit.getFechaArranque()
                            //+"\nActualizaciones: "+bit.getUpdates()
                            //+"\nArchivo de datos: "+bit.getDatos()
                            + "\nGeneralInfo: " + bit.getGeneralInfo());
                }
            } else {
                String[] datos = met.getSysInfo();
                datos[datos.length - 1] = "Espacio vacío";

                String datosS = " ";

                for (int i = 0; i < datos.length - 1; i++) {
                    //System.out.println(datos[i]);
                    datosS = datosS.concat(datos[i] + "\n");
                }

                int ramT = met.ramInt(met.getEnRam(datos));

                int ramDispo = met.ramInt(met.getEnRamDisp(datos));
                int ramUs = ramT - ramDispo;
                int porcentaje = (int) (((ramUs) * 100) / ramT);

                String diskS = met.getDiskSize().substring(0, met.getDiskSize().length() - 2);
                double disk = Double.parseDouble(diskS);
                double diskSize = disk / 1024 / 1024 / 1024;

                String freeDisk = met.getFreeSize().substring(0, met.getFreeSize().length() - 2);
                double free = Double.parseDouble(freeDisk);
                double diskFree = free / 1024 / 1024 / 1024;
                double usageDisk = diskSize - diskFree;
                int porcentageUso = (int) ((usageDisk * 100) / diskSize);

                String[] upd = met.getUpdates(datos);
                String updatesT = "";

                if (upd.length > 15) {
                    int mas = upd.length - 1 - 15;
                    for (int i = upd.length - 1 - 15; i < upd.length - 1; i++) {
                        upd[i] = upd[i].replace(" ", "");
                        updatesT = updatesT.concat(upd[i] + "\n");
                    }
                    updatesT = updatesT.concat(" y " + mas + " mas");
                } else {
                    for (int i = 0; i < upd.length - 1; i++) {
                        upd[i] = upd[i].replace(" ", "");
                        updatesT = updatesT.concat(upd[i] + "\n");
                    }
                }

                if (met.getSerial().contains("0") && met.getPcName().contains("DESKTOP-CG6CU8U")) {
                    noSerie = "8CG43602YP";
                } else {
                    noSerie = met.getSerial();
                }
                //this.datos=met.removAc(datosS);
                fabricante = met.removAc(met.getEnVendor(datos));
                modelo = met.removAc(met.getEnModelo(datos));
                so = met.removAc(met.getEnSo(datos));
                soVer = met.removAc(met.getEnSoVer(datos));
                biosVer = met.removAc(met.getEnBiosVer(datos));
                cpuName = met.removAc(met.getCpuName());
                hostName = met.removAc(met.getPcName());
                tamanoDisco = diskSize;
                usoDisco = usageDisk;
                libreDisco = diskFree;
                porcentageDisco = porcentageUso;
                ramTotal = ramT;
                ramDisp = ramDispo;
                ramUso = ramUs;
                porcentageRam = porcentaje;
                fechaArranque = met.fecha(met.getEnArranque(datos));
                generalInfo = "OK";
                updates = met.removAc(updatesT);

                double tama = Math.round(tamanoDisco);
                double libre = Math.round(libreDisco);
                double uso = Math.round(usoDisco);

                bit.setNoSerie(noSerie);
                //bit.setDatos("");
                bit.setFabricante(fabricante);
                bit.setModelo(modelo);
                bit.setSo(so);
                bit.setSoVer(soVer);
                bit.setBiosVer(biosVer);
                bit.setCpuName(cpuName);
                bit.setHostName(hostName);
                bit.setTamanoDisco(tama);
                bit.setUsoDisco(uso);
                bit.setLibreDisco(libre);
                bit.setPorcentageDisco(porcentageDisco);
                bit.setRamTotal(ramTotal);
                bit.setRamDisp(ramDisp);
                bit.setRamUso(ramUso);
                bit.setPorcentageRam(porcentageRam);
                bit.setFechaArranque(fechaArranque);
                bit.setGeneralInfo(generalInfo);
                bit.setUpdates(updates);

                String fechaI = met.getEnInstalacion(datos);
                fechaI = met.fecha(fechaI);
                String directorio = met.getEnDirectorio(datos);

                String info = "Idioma: " + idioma + " Serial: "
                        + noSerie + " Fecha de instalación original: "
                        + fechaI + " Directorio de Windows: "
                        + directorio + " Actualizaciones: "
                        + updates;

                info = met.removAc(info);
                //info=info.replace(" ", "");

                info = "ok".concat(info);

                bit.setDatos(info);

                jsonRequest = gson.toJson(bit);
                String respuestaService = sr.postObject("https://ti.cosmic.mx/api/BitacoraProceso", jsonRequest);
                System.out.println(respuestaService);

                if (respuestaService.contains("Guardado exitoso")) {
                    JOptionPane.showMessageDialog(null, "Registro exitoso, se enviaron los datos: \n"
                            + "Número de serie: " + bit.getNoSerie()
                            + "\nFabricante: " + bit.getFabricante()
                            + "\nModelo: " + bit.getModelo()
                            + "\nSO: " + bit.getSo()
                            + "\nSO ver.: " + bit.getSoVer()
                            + "\nBIOS: ver.: " + bit.getBiosVer()
                            + "\nCPU name: " + bit.getCpuName()
                            + "\nHostname: " + bit.getHostName()
                            + "\nTamaño disco: " + bit.getTamanoDisco()
                            + "\nDisco en uso: " + bit.getUsoDisco()
                            + " Porcentaje: " + bit.getPorcentageDisco()
                            + "\nDisco libre: " + bit.getLibreDisco()
                            + "\nRam total: " + bit.getRamTotal()
                            + "\nRam en uso: " + bit.getRamUso()
                            + " Porcentaje: " + bit.getPorcentageRam()
                            + "\nRam disponible: " + bit.getRamDisp()
                            + "\nFecha de arranque: " + bit.getFechaArranque()
                            //+"\nActualizaciones: "+bit.getUpdates()
                            //+"\nArchivo de datos: "+bit.getDatos()
                            + "\nGeneralInfo: " + bit.getGeneralInfo());
                } else {
                    JOptionPane.showMessageDialog(null, "Error -1\n -" + respuestaService + "- "
                            + "\nNúmero de serie: " + bit.getNoSerie()
                            + "\nFabricante: " + bit.getFabricante()
                            + "\nModelo: " + bit.getModelo()
                            + "\nSO: " + bit.getSo()
                            + "\nSO ver.: " + bit.getSoVer()
                            + "\nBIOS: ver.: " + bit.getBiosVer()
                            + "\nCPU name: " + bit.getCpuName()
                            + "\nHostname: " + bit.getHostName()
                            + "\nTamaño disco: " + bit.getTamanoDisco()
                            + "\nDisco en uso: " + bit.getUsoDisco()
                            + " Porcentaje: " + bit.getPorcentageDisco()
                            + "\nDisco libre: " + bit.getLibreDisco()
                            + "\nRam total: " + bit.getRamTotal()
                            + "\nRam en uso: " + bit.getRamUso()
                            + " Porcentaje: " + bit.getPorcentageRam()
                            + "\nRam disponible: " + bit.getRamDisp()
                            + "\nFecha de arranque: " + bit.getFechaArranque()
                            //+"\nActualizaciones: "+bit.getUpdates()
                            //+"\nArchivo de datos: "+bit.getDatos()
                            + "\nGeneralInfo: " + bit.getGeneralInfo());
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Revisa tu conexión a internet", "Error", 2);
        }
        setCursor(new Cursor(0));

    }//GEN-LAST:event_ejecutarActionPerformed

    private void terminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarActionPerformed
        in.setVisible(true);

    }//GEN-LAST:event_terminarActionPerformed

    private void spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spActionPerformed
        // TODO add your handling code here:
        segundoPlano();
    }//GEN-LAST:event_spActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        ven.setVisible(true);
    }//GEN-LAST:event_registrarActionPerformed

    private void actualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizaActionPerformed

        setCursor(new Cursor(3));
        //Actualizaciones
        Metodos obj=new Metodos();
        String mensaje = obj.buscarActualizacion();
        if (mensaje.contains("Error")) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", 2);
            upd.setIcon(fail);
            
        } else {
            JOptionPane.showMessageDialog(null, mensaje);
            upd.setIcon(ok);
        }
        upd.setText(mensaje);

        
        

        

        setCursor(new Cursor(0));

    }//GEN-LAST:event_actualizaActionPerformed

    private void actualizaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizaMouseEntered
        actualiza.setToolTipText("Buscar actualizaciones");
    }//GEN-LAST:event_actualizaMouseEntered

    private void registrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarMouseEntered
        registrar.setToolTipText("Regístrate");
    }//GEN-LAST:event_registrarMouseEntered

    private void terminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_terminarMouseEntered
        terminar.setToolTipText("Salir");
    }//GEN-LAST:event_terminarMouseEntered

    private void spMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spMouseEntered
        sp.setToolTipText("Minimizar");
    }//GEN-LAST:event_spMouseEntered

    private void acercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acercaActionPerformed
        
        JOptionPane.showMessageDialog(null, "Inventario Cosmic 2.2\n© 2021 TI Grupo Cosmic. Todos los derechos Reservados", "Acerca de", 2, verc);
        
    }//GEN-LAST:event_acercaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SegundoPlano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SegundoPlano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SegundoPlano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SegundoPlano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SegundoPlano().setVisible(true);
                } catch (AWTException ex) {
                    Logger.getLogger(SegundoPlano.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.MenuItem abrir;
    private javax.swing.JButton acerca;
    private javax.swing.JButton actualiza;
    private javax.swing.JLabel discoD;
    private javax.swing.JLabel discoT;
    private javax.swing.JLabel discoU;
    private javax.swing.JButton ejecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel marca;
    private javax.swing.JLabel modeloPC;
    private java.awt.PopupMenu pop;
    private javax.swing.JLabel porcent;
    private javax.swing.JLabel porcentD;
    private javax.swing.JLabel ramDisponible;
    private javax.swing.JLabel ramEnUso;
    private javax.swing.JLabel ramTot;
    private javax.swing.JLabel reboot;
    private javax.swing.JButton registrar;
    private java.awt.MenuItem salir;
    private javax.swing.JLabel serial;
    private javax.swing.JButton sp;
    private javax.swing.JLabel systemO;
    private javax.swing.JLabel systemVer;
    private javax.swing.JButton terminar;
    private javax.swing.JLabel upd;
    // End of variables declaration//GEN-END:variables
}
