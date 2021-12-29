/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import Objeto.Equipo;
import Fuentes.ServiceResponse;
import com.google.gson.Gson;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Rubén
 */
public final class Ventana extends javax.swing.JFrame implements Runnable {
    Gson gson = new Gson();
    String jsonRequest;
    String uri="https://ti.cosmic.mx/";
    ServiceResponse sr=new ServiceResponse();
    Equipo equipo=new Equipo();
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
    Double tamanoDisco; //espacio total del disco
    Double usoDisco; //espacio en uso del disco
    Double libreDisco; //espacio libre del disco
    int porcentageDisco; //porcentage de uso del disco
    int ramTotal; //ram total
    int ramDisp; //ram disponible
    int ramUso; //ram en uso
    int porcentageRam; //porcentage de uso de ram
    String fechaArranque; //fecha del ultimo reinicio
    String updates; //actualizaciones instaladas
    
    TrayIcon trayIcon = null;
    ImageIcon icon;
    
    
    
    public Ventana() throws AWTException {
        
        

        initComponents();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(178, 22, 33));
//        icon=new ImageIcon(this.getClass().getResource("/Fuentes/cosmic.png"));
//        this.setIconImage(icon.getImage());
        
        Oficina.removeAllItems();
        Oficina.addItem("Selecciona...");
        Oficina.addItem("Corporativo");
        Oficina.addItem("Instituto COSMIC");
        Oficina.addItem("Instituto Norte VDM");
        Oficina.addItem("Home Office");
        Oficina.addItem("Aguascalientes");
        Oficina.addItem("Cancún");
        Oficina.addItem("Culiacán");
        Oficina.addItem("Chihuahua");
        Oficina.addItem("La Paz");
        Oficina.addItem("Guadalajara");
        Oficina.addItem("Hermosillo");
        Oficina.addItem("Ciudad Obregón");
        Oficina.addItem("León");
        Oficina.addItem("Mérida");
        Oficina.addItem("Mexicali");
        Oficina.addItem("Monterrey");
        Oficina.addItem("Morelia");
        Oficina.addItem("Puebla");
        Oficina.addItem("Querétaro");
        Oficina.addItem("Saltillo");
        Oficina.addItem("Toluca");
        Oficina.addItem("Torreón");
        Oficina.addItem("Veracruz");
        Oficina.addItem("Villahermosa");
        Oficina.addItem("San Luis Potosi");

        this.setTitle("Registro");
        
        ImageIcon iconB=new ImageIcon(this.getClass().getResource("/cosmic.png"));
        this.setIconImage(iconB.getImage());
        
        
        
        
        
        
        
    }
    
    

    

   

    public void generar() {
        if (!Nombre.getText().isEmpty() && !Apellido.getText().isEmpty() && !Puesto.getText().isEmpty()
                && !NoEmpleado.getText().isEmpty() && Oficina.getSelectedIndex() != 0) {
            Generar.setEnabled(true);
        } else {
            Generar.setEnabled(false);
        }
        
       

//        if (HomeOffice.isSelected()) {
//            if (!Nombre.getText().isEmpty() && !Apellido.getText().isEmpty() && !Puesto.getText().isEmpty() && !NoEmpleado.getText().isEmpty()) {
//                Generar.setEnabled(true);
//            } else {
//                Generar.setEnabled(false);
//            }
//        } else {
////            if (!Nombre.getText().isEmpty() && !Apellido.getText().isEmpty() && !Puesto.getText().isEmpty() && !NoEmpleado.getText().isEmpty() && Oficina.getSelectedIndex()!=0) {
////                Generar.setEnabled(true);
////            }else{
////                Generar.setEnabled(false);
////            }
//        }        
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
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Apellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        NoEmpleado = new javax.swing.JTextField();
        Generar = new javax.swing.JButton();
        Puesto = new javax.swing.JTextField();
        Oficina = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Reset = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Resultado = new javax.swing.JTextArea();
        label2 = new java.awt.Label();
        label1 = new java.awt.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        salidacmd = new javax.swing.JTextArea();
        enviar = new javax.swing.JButton();

        pop.setLabel("popupMenu1");

        abrir.setLabel("Abrir");
        pop.add(abrir);

        setIconImage(getIconImage());
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(361, 361));

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Ingresa tus datos");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("No. empleado:");

        Apellido.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                ApellidoAncestorRemoved(evt);
            }
        });
        Apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ApellidoKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Oficina:");

        NoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoEmpleadoActionPerformed(evt);
            }
        });
        NoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NoEmpleadoKeyReleased(evt);
            }
        });

        Generar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Generar.setText("Generar");
        Generar.setEnabled(false);
        Generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarActionPerformed(evt);
            }
        });

        Puesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PuestoActionPerformed(evt);
            }
        });
        Puesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PuestoKeyReleased(evt);
            }
        });

        Oficina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Oficina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OficinaActionPerformed(evt);
            }
        });
        Oficina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OficinaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                OficinaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                OficinaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Nombre(s):");

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });
        Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombreKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Apellidos:");

        Reset.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Reset.setText("Reiniciar");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Puesto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Reset)
                                .addGap(22, 22, 22)
                                .addComponent(Generar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(NoEmpleado, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Puesto, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Apellido, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nombre)
                                    .addComponent(Oficina, 0, 233, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Puesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Oficina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Reset)
                    .addComponent(Generar))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Resultado.setEditable(false);
        Resultado.setColumns(20);
        Resultado.setRows(5);
        jScrollPane1.setViewportView(Resultado);

        label2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label2.setText("Información del usuario");

        label1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label1.setText("Información del sistema");

        salidacmd.setEditable(false);
        salidacmd.setColumns(20);
        salidacmd.setRows(5);
        jScrollPane2.setViewportView(salidacmd);

        enviar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        enviar.setText("Guardar");
        enviar.setEnabled(false);
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(enviar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enviar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(269, 269, 269))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void setLocationRelativeTo(Component c) {
        super.setLocationRelativeTo(c); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocale(Locale l) {
        super.setLocale(l); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocation(Point p) {
        super.setLocation(p); //To change body of generated methods, choose Tools | Templates.
    }

    

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void OficinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OficinaActionPerformed
        generar();
    }//GEN-LAST:event_OficinaActionPerformed

    private void PuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PuestoActionPerformed

    private void GenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarActionPerformed
        setCursor(new Cursor(3));
        
        String nombre =obj.removAc(Nombre.getText().concat(" "+Apellido.getText()));
        String puesto=obj.removAc(Puesto.getText());
        
        equipo.setNombre(nombre);
        equipo.setNoEmpleado(NoEmpleado.getText());
        equipo.setPuesto(puesto);
        equipo.setIdOficina(Oficina.getSelectedIndex());
        equipo.setGeneralInfo("ok");
        
        Resultado.append("Datos a enviar:\n"
            + "Nombre del empleado: "+equipo.getNombre()+"\n"
            +"Puesto: "+equipo.getPuesto()+"\n"
            +"ID Oficina: "+equipo.getIdOficina()+" ("+Oficina.getSelectedItem()+")\n"
            +"GeneralInfo: "+equipo.getGeneralInfo());
        

        //CMD:
        //Llenando el último espacio del arreglo nulo
        String[] dato = obj.getSysInfo();
        dato[dato.length-1]="Espacio vacío";
        
        //Cadena larga con toda la información
        datos=" ";
        
        dato[dato.length-1]="Espacio vacío";
        
        String datosS=" ";
        
        
        for (int i = 0; i < dato.length-1; i++) {
            //System.out.println(datos[i]);
            datosS=datosS.concat(dato[i]);
        }
        
        String fechaI;
        
        String directorio;
        
        String idioma=Locale.getDefault().getLanguage();
        
        
        //equipo.setDatos(obj.removAc(datosS));
        
        
        
        if (idioma.contains("es")) {
            salidacmd.append("Idioma del sistema: "+idioma+"(Español)\n\n");
            fechaI=obj.getInstalacion(dato);
            fechaI=obj.fecha(fechaI);
            fechaI=obj.removAc(fechaI);
            directorio=obj.getDirectorio(dato);
            
            //Obteniendo fabricante
            fabricante=obj.getVendor(dato);
            fabricante=obj.removAc(fabricante);
            equipo.setFabricante(fabricante);
            salidacmd.append("Fabricante: "+equipo.getFabricante()+"\n"+"\n");

            //Obteniendo modelo
            modelo=obj.getModelo(dato);
            modelo =obj.removAc(modelo);
            equipo.setModelo(modelo);
            salidacmd.append("Modelo: "+equipo.getModelo()+"\n"+"\n");

            //Obteniendo el múmero de serie

            if (equipo.getNoEmpleado().equals("790999")) {
                noSerie="8CG43602YP";
            }else{
                noSerie=obj.getSerial();
            }
            equipo.setNoSerie(noSerie);
            salidacmd.append("Número de serie: "+equipo.getNoSerie()+"\n"+"\n");

            //obteniendo el sistema operativo
            so=obj.getSo(dato);
            so=obj.removAc(so);
            soVer=obj.getSoVer(dato);
            soVer=obj.removAc(soVer);
            equipo.setSo(so);
            equipo.setSoVer(soVer);
            salidacmd.append("Sistema operativo: "+equipo.getSo()+"\n"+"Versión del sistema operativo: "+equipo.getSoVer()+"\n"+"\n");

            //Obteniendo bios
            biosVer=obj.getBiosVer(dato);
            biosVer=obj.removAc(biosVer);
            equipo.setBiosVer(obj.removAc(biosVer));
            salidacmd.append("Versión del BIOS: "+equipo.getBiosVer()+"\n"+"\n");

            //Obteniendo modelo del cpu
            cpuName=obj.getCpuName();
            cpuName=obj.removAc(cpuName);
            equipo.setCpuName(cpuName);
            salidacmd.append("Modelo del procesador: "+equipo.getCpuName()+"\n"+"\n");

            //Obteniendo nombe del equipo
            hostName=obj.getPcName();
            hostName=obj.removAc(hostName);
            equipo.setHostname(hostName);
            salidacmd.append("Nombre del host: "+equipo.getHostname()+"\n"+"\n");

            //obteniendo tamaño disco duro
            String diskS=obj.getDiskSize().substring(0,obj.getDiskSize().length()-2);
            Double disk=Double.parseDouble(diskS);
            tamanoDisco=disk/1024/1024/1024;
            String freeDisk=obj.getFreeSize().substring(0,obj.getFreeSize().length()-2);
            Double free=Double.parseDouble(freeDisk);
            libreDisco=free/1024/1024/1024;
            usoDisco=tamanoDisco-libreDisco;
            porcentageDisco=(int) ((usoDisco*100)/tamanoDisco);
            float tama=Math.round(tamanoDisco);
            float libre=Math.round(libreDisco);
            float uso=Math.round(usoDisco);

            equipo.setTamanoDisco(tama);
            equipo.setLibreDisco(libre);
            equipo.setUsoDisco(uso);
            equipo.setPorcentageDisco(porcentageDisco);
            salidacmd.append("Capacdad del disco duro(GB): "+equipo.getTamanoDisco()+"\n");
            salidacmd.append("Almacenamiento en uso(GB): "+equipo.getUsoDisco()+"("+equipo.getPorcentageDisco()+"%)"+"\n"+"Almacenamiento libre(GB): "+equipo.getLibreDisco()+"\n"+"\n");

            //Obteniendo memoria ram
            ramTotal=obj.ramInt(obj.getRam(dato));
            ramDisp=obj.ramInt(obj.getRamDisp(dato));
            ramUso=ramTotal-ramDisp;
            porcentageRam=(int) (((ramUso)*100)/ramTotal);
            equipo.setRamTotal(ramTotal);
            equipo.setRamUso(ramUso);
            equipo.setRamDisp(ramDisp);
            equipo.setPorcentageRam(porcentageRam);
            salidacmd.append("Memoria RAM: "+equipo.getRamTotal()+"\n");
            salidacmd.append("Memoria RAM en uso: "+equipo.getRamUso()+"("+equipo.getPorcentageRam()+"%)"+"\n"+"Memoria libre: "+equipo.getRamDisp()+"\n"+"\n");

            //Fecha de arranque
            fechaArranque=obj.getArranque(dato);
            fechaArranque=obj.fecha(fechaArranque);
            equipo.setFechaArranque(fechaArranque);
            salidacmd.append("Fecha de arranque del sistema: "+equipo.getFechaArranque()+"\n"+"\n");

            //Obteniendo las actualizaciones
            int noUpdates=obj.getUpdates(dato).length-1;
            String[] upd=obj.getUpdates(dato);
            updates="";

            if (upd.length>15) {
                int mas=upd.length-1-15;
                for (int i = upd.length-1-15; i <upd.length-1; i++) {
                    upd[i]=upd[i].replace(" ", "");
                    updates=updates.concat(upd[i]+"\n");
                }
                updates=updates.concat(" y "+mas+" mas");
            }else{
                for (int i = 0; i < upd.length-1; i++) {
                    upd[i]=upd[i].replace(" ", "");
                    updates=updates.concat(upd[i]+"\n");
                }
            }
            updates=obj.removAc(updates);
            updates=updates.replace(" ", "");
            equipo.setUpdates(updates);
            salidacmd.append("Actualizaciones instaladas: "+noUpdates+"\n"+equipo.getUpdates()+"\n"+"\n");

            String info="Idioma: "+idioma+" Serial: "
                    +equipo.getNoSerie()+" Fecha de instalación original: "
                    +fechaI+" Directorio de Windows: "
                    +directorio+"Numero de empleado: "
                    +equipo.getNoEmpleado();

            info=obj.removAc(info);
            info=info.replace(" ", "");
            info=info.replace("-", "");
            info="ok".concat(info);
            equipo.setDatos(info);
            //info="ok";
            equipo.setDatos(info);

            salidacmd.append("\n\n Archivo de datos: "+equipo.getDatos()+"\n\n");

            

//Si el idioma del sistema es inglés            
        }else {
            salidacmd.append("Idioma del sistema: "+idioma+"(Inglés)\n\n");
            fechaI=obj.getEnInstalacion(dato);
            fechaI=obj.fecha(fechaI);
            fechaI=obj.removAc(fechaI);
            directorio=obj.getDirectorio(dato);
            
            //Obteniendo fabricante
            fabricante=obj.getEnVendor(dato);
            fabricante=obj.removAc(fabricante);
            equipo.setFabricante(fabricante);
            salidacmd.append("Fabricante: "+equipo.getFabricante()+"\n"+"\n");

            //Obteniendo modelo
            modelo=obj.getEnModelo(dato);
            modelo =obj.removAc(modelo);
            equipo.setModelo(modelo);
            salidacmd.append("Modelo: "+equipo.getModelo()+"\n"+"\n");

            //Obteniendo el múmero de serie

            if (equipo.getNoEmpleado().equals("790999")) {
                noSerie="8CG43602YP";
            }else{
                noSerie=obj.getSerial();
            }
            equipo.setNoSerie(noSerie);
            salidacmd.append("Número de serie: "+equipo.getNoSerie()+"\n"+"\n");

            //obteniendo el sistema operativo
            so=obj.getEnSo(dato);
            so=obj.removAc(so);
            soVer=obj.getEnSoVer(dato);
            soVer=obj.removAc(soVer);
            equipo.setSo(so);
            equipo.setSoVer(soVer);
            salidacmd.append("Sistema operativo: "+equipo.getSo()+"\n"+"Versión del sistema operativo: "+equipo.getSoVer()+"\n"+"\n");

            //Obteniendo bios
            biosVer=obj.getEnBiosVer(dato);
            biosVer=obj.removAc(biosVer);
            equipo.setBiosVer(obj.removAc(biosVer));
            salidacmd.append("Versión del BIOS: "+equipo.getBiosVer()+"\n"+"\n");

            //Obteniendo modelo del cpu
            cpuName=obj.getCpuName();
            cpuName=obj.removAc(cpuName);
            equipo.setCpuName(cpuName);
            salidacmd.append("Modelo del procesador: "+equipo.getCpuName()+"\n"+"\n");

            //Obteniendo nombe del equipo
            hostName=obj.getPcName();
            hostName=obj.removAc(hostName);
            equipo.setHostname(hostName);
            salidacmd.append("Nombre del host: "+equipo.getHostname()+"\n"+"\n");

            //obteniendo tamaño disco duro
            String diskS=obj.getDiskSize().substring(0,obj.getDiskSize().length()-2);
            Double disk=Double.parseDouble(diskS);
            tamanoDisco=disk/1024/1024/1024;
            String freeDisk=obj.getFreeSize().substring(0,obj.getFreeSize().length()-2);
            Double free=Double.parseDouble(freeDisk);
            libreDisco=free/1024/1024/1024;
            usoDisco=tamanoDisco-libreDisco;
            porcentageDisco=(int) ((usoDisco*100)/tamanoDisco);
            double tama=Math.round(tamanoDisco);
            double libre=Math.round(libreDisco);
            double uso=Math.round(usoDisco);

            equipo.setTamanoDisco(tama);
            equipo.setLibreDisco(libre);
            equipo.setUsoDisco(uso);
            equipo.setPorcentageDisco(porcentageDisco);
            salidacmd.append("Capacdad del disco duro(GB): "+equipo.getTamanoDisco()+"\n");
            salidacmd.append("Almacenamiento en uso(GB): "+equipo.getUsoDisco()+"("+equipo.getPorcentageDisco()+"%)"+"\n"+"Almacenamiento libre(GB): "+equipo.getLibreDisco()+"\n"+"\n");

            //Obteniendo memoria ram
            ramTotal=obj.ramInt(obj.getEnRam(dato));
            ramDisp=obj.ramInt(obj.getEnRamDisp(dato));
            ramUso=ramTotal-ramDisp;
            porcentageRam=(int) (((ramUso)*100)/ramTotal);
            equipo.setRamTotal(ramTotal);
            equipo.setRamUso(ramUso);
            equipo.setRamDisp(ramDisp);
            equipo.setPorcentageRam(porcentageRam);
            salidacmd.append("Memoria RAM: "+equipo.getRamTotal()+"\n");
            salidacmd.append("Memoria RAM en uso: "+equipo.getRamUso()+"("+equipo.getPorcentageRam()+"%)"+"\n"+"Memoria libre: "+equipo.getRamDisp()+"\n"+"\n");

            //Fecha de arranque
            fechaArranque=obj.getEnArranque(dato);
            fechaArranque=obj.fecha(fechaArranque);
            equipo.setFechaArranque(fechaArranque);
            salidacmd.append("Fecha de arranque del sistema: "+equipo.getFechaArranque()+"\n"+"\n");

            //Obteniendo las actualizaciones
            int noUpdates=obj.getUpdates(dato).length-1;
            String[] upd=obj.getUpdates(dato);
            updates="";

            if (upd.length>15) {
                int mas=upd.length-1-15;
                for (int i = upd.length-1-15; i <upd.length-1; i++) {
                    upd[i]=upd[i].replace(" ", "");
                    updates=updates.concat(upd[i]+"\n");
                }
                updates=updates.concat(" y "+mas+" mas");
            }else{
                for (int i = 0; i < upd.length-1; i++) {
                    upd[i]=upd[i].replace(" ", "");
                    updates=updates.concat(upd[i]+"\n");
                }
            }
            updates=obj.removAc(updates);
            updates=updates.replace(" ", "");
            equipo.setUpdates(updates);
            salidacmd.append("Actualizaciones instaladas: "+noUpdates+"\n"+equipo.getUpdates()+"\n"+"\n");

            String info="Idioma: "+idioma+" Serial: "
                    +equipo.getNoSerie()+" Fecha de instalación original: "
                    +fechaI+" Directorio de Windows: "
                    +directorio+"Numero de empleado: "
                    +equipo.getNoEmpleado();

            info=obj.removAc(info);
            info=info.replace(" ", "");
            
            info="ok".concat(info);
            equipo.setDatos(info);
            //info="ok";
            equipo.setDatos(info);

            salidacmd.append("\n\n Archivo de datos: "+equipo.getDatos()+"\n\n");

            
        }
        
        //Generando reporte de batería
//            String rutaA = System.getProperty("user.home") + "\\";
//            rutaA=rutaA.concat("batteryReport.html");
//            String rutaF = obj.ruta().concat("batteryReport.html");
  //          String reporte=obj.reporteBateria();
//            salidacmd.append(reporte+"\n\nRuta generada"+rutaF);
        
        

        enviar.setEnabled(true);
        Generar.setEnabled(false);
        //abrirReport.setEnabled(true);
        setCursor(new Cursor(0));
        
        
        


    }//GEN-LAST:event_GenerarActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        Nombre.setText("");
        Apellido.setText("");
        Puesto.setText("");
        NoEmpleado.setText("");
        Oficina.setEnabled(true);
        Oficina.setSelectedIndex(0);
        Generar.setEnabled(false);
        Resultado.setText("");
        salidacmd.setText("");

    }//GEN-LAST:event_ResetActionPerformed

    private void NoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoEmpleadoActionPerformed

    private void NombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreKeyReleased
        generar();
    }//GEN-LAST:event_NombreKeyReleased

    private void ApellidoAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ApellidoAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidoAncestorRemoved

    private void ApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApellidoKeyReleased
        generar();
    }//GEN-LAST:event_ApellidoKeyReleased

    private void PuestoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PuestoKeyReleased
        generar();
    }//GEN-LAST:event_PuestoKeyReleased

    private void NoEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoEmpleadoKeyReleased
        generar();
    }//GEN-LAST:event_NoEmpleadoKeyReleased

    private void OficinaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OficinaKeyReleased
        generar();
    }//GEN-LAST:event_OficinaKeyReleased

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        setCursor(new Cursor(3));
        System.out.println(equipo.getBiosVer());
        System.out.println(equipo.getNombre());
        System.out.println(equipo.getDatos());
        
        
        
        try{
            jsonRequest = gson.toJson(equipo);
            String respuestaService = sr.postObject("https://ti.cosmic.mx/api/CatEquipo/save", jsonRequest);
            if (respuestaService.contains("Guardado exitoso")) {
                JOptionPane.showMessageDialog(null, "Registro exitoso ");
            }else if (respuestaService.contains("Este equipo ya se encuentra asignado a ese usuario")) {
                JOptionPane.showMessageDialog(null, "Este equipo ya se encuentra asignado a ese número de empleado");
            }else if (respuestaService.contains("No es un numero de empleado valido")) {
                JOptionPane.showMessageDialog(null, "Número de empleado no válido");
            }else{
                JOptionPane.showMessageDialog(null, "Error -1\n -"+respuestaService+"- ");
            }

            System.out.println(respuestaService);
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, "Error -1");
        }
        
        
        
//        String rutaA = System.getProperty("user.home") + "\\Desktop\\Datos " + Nombre.getText() + " " + Apellido.getText() + ".txt";
//        String rutaF = rutaA.replace('\\', '/');
//        
//
//        try {
//            //String ruta = "C:/Users/Rubén/Documents/datos.txt";
//            String contenido = Resultado.getText() + "\n" + salidacmd.getText();
//            File file = new File(rutaF);
//            // Si el archivo no existe es creado
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(contenido);
//            bw.close();
//            //JOptionPane.showMessageDialog(null, "Archivo de texto guardado en tu escritorio con el nombre: "
//                + "Datos " + Nombre.getText() + " " + Apellido.getText() + ".txt");
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Error al crear archivo\nRuta: "+rutaF);
//        }

        

        enviar.setEnabled(false);

//        Properties propiedad = new Properties();
//        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
//        propiedad.setProperty("mail.smtp.starttls.enable", "true");
//        propiedad.setProperty("mail.smtp.port", "587");
//        propiedad.setProperty("mail.smtp.auth", "true");
//        propiedad.setProperty("mail.smtp.user", "ti.cosmic2020@gmail.com");
//        
//        Session sesion = Session.getDefaultInstance(propiedad);
//        String correoEnvia = "ti.cosmic2020@gmail.com";
//        String contrasena = "holamundo28";
//        String receptor = "analista_soporte@cosmic.com.mx";
//        String asunto = "Datos de "+Nombre.getText()+" "+Apellido.getText();
//        String mensaje=Resultado.getText()+"\n"+salidacmd.getText();
//        
//        System.out.println(mensaje);
//        
//        MimeMessage mail = new MimeMessage(sesion);
//        try {
//            mail.setFrom(new InternetAddress (correoEnvia));
//            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
//            mail.setSubject(asunto);
//            mail.setText(mensaje);
//            
//            Transport transportar = sesion.getTransport("smtp");
//            transportar.connect(correoEnvia,contrasena);
//            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
//            transportar.close();
//            
//            //JOptionPane.showMessageDialog(null, "Listo, revise su correo");
//            
//            
//        } catch (AddressException ex) {
//            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
//        }
setCursor(new Cursor(0));
    }//GEN-LAST:event_enviarActionPerformed

    private void OficinaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OficinaKeyPressed
        generar();
    }//GEN-LAST:event_OficinaKeyPressed

    private void OficinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OficinaKeyTyped
        generar();
    }//GEN-LAST:event_OficinaKeyTyped

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Ventana().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JButton Generar;
    private javax.swing.JTextField NoEmpleado;
    private javax.swing.JTextField Nombre;
    private javax.swing.JComboBox<String> Oficina;
    private javax.swing.JTextField Puesto;
    private javax.swing.JButton Reset;
    private javax.swing.JTextArea Resultado;
    private java.awt.MenuItem abrir;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.PopupMenu pop;
    private javax.swing.JTextArea salidacmd;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
