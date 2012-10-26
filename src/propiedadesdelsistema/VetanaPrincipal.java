/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package propiedadesdelsistema;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

/**
 *
 * @author Abner
 */
public class VetanaPrincipal extends javax.swing.JFrame {

    LlenarListas listas;
    String goverorselected;
     public ArrayList<String> listActualizacion1;
     public ArrayList<String> listActualizacion;
    private static String lookAdFeel = UIManager.getSystemLookAndFeelClassName();

    public VetanaPrincipal() {
        super("Propiedades del Sistema");
        setLookAndFeel(lookAdFeel);
        initComponents();

        setLocationRelativeTo(this);
        listas = new LlenarListas();
        setEditablejText();
        informacionGeneral();
        mostrarmeminfosimple();
        informacionparticiones();
        informacionprocesos();
        inicio();
        llenartabla();
        llenarinfocpusimple();
    }

    private void setEditablejText() {
        jTextAreaInfoGeneral.setEditable(false);
        jTextAreaRAM.setEditable(false);
        jTextAreaParticion.setEditable(false);
        
    }

    private void setLookAndFeel(String laf) {
        try {
            UIManager.setLookAndFeel(laf);

        } catch (Exception e) {
            System.out.println(e);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    
    
    //###########################################
    private void comandoconsolaActualizacion(String pcmd) {
        listActualizacion1 = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listActualizacion1.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }
     public ArrayList<String> getListActualizacion() {
        return listActualizacion1;
    }

     private void mostrarActualizacion() {
        ArrayList<String> listActualizacion = listActualizacion1;
        for (int i = 0; i < listActualizacion.size(); i++) {
          jListProcesos.setListData(listActualizacion.toArray());
            
            // listas.getListActualizacion().get(4).charAt(3);




        }
    }
     private void comandoconsolaSockets(String pcmd){
           listActualizacion1 = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listActualizacion1.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }
     
      public ArrayList<String> getListSockets() {
        return listActualizacion1;       
    }
 private void mostrarSockets() {
        ArrayList<String> listSockets=listActualizacion1;
        for (int i = 0; i < listSockets.size(); i++) {
           jTextAreaDispositivos.append(listSockets.get(i) + '\n');  
          // listas.getListActualizacion().get(4).charAt(3);
           
           
            
           
        }
    }
    
    
    
    
    private void informacionparticiones() {
        ArrayList<String> listparticiones = listas.getListparticiones();
        for (int i = 0; i < listparticiones.size(); i++) {
            jTextAreaParticion.append(listparticiones.get(i) + '\n');
        }
    }
    
   //##################################################################
    
    private void informacionprocesos(){
        ArrayList<String> listprocesos = listas.getListprocesos();
        for (int i = 0; i < listprocesos.size(); i++) {
            jListProcesos.setListData(listprocesos.toArray());
        }
        
    }
    
    private void inicio(){
        
        ArrayList<String> listmeminfo = listas.getListmeminfo();
        ArrayList<String> listcpuinfo = listas.getListcpuinfo();
        
        jLabelusername1.setText("   "+"User Name" + ':'  + "     "       + '\t'+listas.comandoconsolagenerico("hostname") + '\n'+'\n'+'\n'+'\n');
        jLabelipdireccion1.setText("   "+"IP Direcion" + ':'+ "      "       + '\t' + listas.comandoconsolagenerico("hostname -i") + '\n'+'\n'+'\n'+'\n');
        jLabelmemtotal1.setText("   "+"Memoria Total" + ':'     + '\t' + listmeminfo.get(0) + '\n'+'\n'+'\n'+'\n');
        jLabelmemcache1.setText("   "+"Memoria Cache" + ':'     + '\t' + listmeminfo.get(3) + '\n'+'\n'+'\n'+'\n');
        jLabelmodcpu1.setText("   "+"Modelo del CPU" + ':'    + '\t' + listcpuinfo.get(4) + '\n'+'\n'+'\n'+'\n');    
        jLabelvelocpu1.setText("   "+"Velocidad del CPU" + ':' + '\t' + listcpuinfo.get(7) + '\n'+'\n'+'\n'+'\n');
    }

    private void informacionGeneral() {
        ArrayList<String> listmeminfo = listas.getListmeminfo();
        ArrayList<String> listcpuinfo = listas.getListcpuinfo();
        jTextAreaInfoGeneral.append("User Name" + ':'  + "      "       + '\t' + listas.comandoconsolagenerico("hostname") + '\n');
        jTextAreaInfoGeneral.append("IP Direcion" + ':'+ "      "       + '\t' + listas.comandoconsolagenerico("hostname -i") + '\n');
        jTextAreaInfoGeneral.append("Kernel Version" + ':'    + '\t' + listas.comandoconsolagenerico("uname -a") + '\n');
        jTextAreaInfoGeneral.append("Memoria Total" + ':'     + '\t' + listmeminfo.get(0) + '\n');
        jTextAreaInfoGeneral.append("Memoria Cache" + ':'     + '\t' + listmeminfo.get(3) + '\n');
        jTextAreaInfoGeneral.append("Modelo del CPU" + ':'    + '\t' + listcpuinfo.get(4) + '\n');
        jTextAreaInfoGeneral.append("Velocidad del CPU" + ':' + '\t' + listcpuinfo.get(7) + '\n');
    }

    private void mostrarmeminfosimple() {
        ArrayList<String> listmeminfo = listas.getListmeminfo();
        jTextAreaRAM.append("Memoria Total" + ':' + "" + '\t' + listmeminfo.get(0) + '\n');
        jTextAreaRAM.append("Memoria Libre" + ':' + "" + '\t' + listmeminfo.get(1) + '\n');
        jTextAreaRAM.append("Buffers" + ':' + "             " + '\t' + listmeminfo.get(2) + '\n');
        jTextAreaRAM.append("Memoria Cache" + ':' + "" + '\t' + listmeminfo.get(3) + '\n');
        jTextAreaRAM.append("Modelo del CPU"+ ':' + "" + '\t' + listmeminfo.get(4) + '\n');
        jTextAreaRAM.append("Memoria Virtual" + ':' + "" + '\t' + listmeminfo.get(13) + '\n');
        jTextAreaRAM.append("Memoria Virtual Libre" + ':' + "" + '\t' + listmeminfo.get(14) + '\n');
    }

    private void mostrarmeminfoavanzada() {
        ArrayList<String> listmeminfo = listas.getListmeminfo();
        for (int i = 0; i < listmeminfo.size(); i++) {
            jTextAreaRAM.append(listmeminfo.get(i) + '\n');
        }
    }
    
    private void llenarinfocpusimple(){
        ArrayList<String> listcpuinfo = listas.getListcpuinfo();
        String cpu = listcpuinfo.get(1);
        String cpu1 = listcpuinfo.get(4);
        String cpu2 = listcpuinfo.get(7);
        String cpu3 = listcpuinfo.get(8);
        String cpu4 = listcpuinfo.get(23);
        TableModel model = jTable2.getModel();
        model.setValueAt(valorDepurado(cpu), 0, 1);
        model.setValueAt(valorDepurado(cpu1), 1, 1);
        model.setValueAt(valorDepurado(cpu2), 2, 1);
        model.setValueAt(valorDepurado(cpu3), 3, 1);
        model.setValueAt(valorDepurado(cpu4), 4, 1);
    }
        
     private String valorDepurado(String prueba){
       
        int indexOf = prueba.indexOf(":");//Obtiene la posicion donde estan los 2 puntos(:)
        String substring = prueba.substring(indexOf+1);//Aqui se guarda la cadena de la posicion+1 de los 2 puntos
        int x=0;
        if(substring.contains(" ")){
        
            for(int i=0;i<substring.length();i++){
                if(!Character.isSpaceChar(substring.charAt(i))){
                    x=i;
                    break;
                }
            }   
        }
        String substring1 = substring.substring(x);//Aqui se guarda la cadena ya sin espacios y sin los 2 puntos
        return substring1;//se retorna la cadena
    }
     
          private void llenartabla(){
          ArrayList<String> listcpuinfo = listas.getListcpuinfo();
          ArrayList<String> listcpufreq = listas.getListcpufreq();
        for (int i = 0; i < listcpuinfo.size() - 1; i++) {
   
            String valorDepurado = valorDepurado(listcpuinfo.get(i) + "\n");
            TableModel model = jTable1.getModel();
            model.setValueAt(valorDepurado, i, 1);
        }
        String cpu = listcpufreq.get(0);
        String cpu1 = listcpufreq.get(1);
        String cpu2 = listcpufreq.get(2);
        String cpu3 = listcpufreq.get(3);
        String cpu4 = listcpufreq.get(4);
        TableModel model = jTable1.getModel();
        model.setValueAt(cpu, 53, 1);
        model.setValueAt(cpu1, 54, 1);
        model.setValueAt(cpu2, 55, 1);
        model.setValueAt(cpu3, 56, 1);
        model.setValueAt(cpu4, 57, 1);
     }

    
    private void MatarprocesoPID(){
        
        String osName = System.getProperty("os.name");
        String system =  "";
        if(osName.toUpperCase().contains("WIN")){   //Windows
            system+="tskill " +jTextFieldNumeroProceso.getText();
        } else {                                    //Linux
            system+="killall " + jTextFieldNumeroProceso.getText();
        }      

        Process hijo;
        try {
            hijo = Runtime.getRuntime().exec(system);
            hijo.waitFor();
            if ( hijo.exitValue()==0){
                System.out.println( jTextFieldNumeroProceso.getText() + " Killed" );
            }else{
		System.out.println( "Cannot kill " + jTextFieldNumeroProceso.getText() + ". Exit code: " + hijo.exitValue() );
            }
        } catch (IOException e) {
            System.out.println("Incapaz de matar el proceso.");
        } catch (InterruptedException e) {
            System.out.println("Incapaz de matar proceso.");
        }      

        
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonVS = new javax.swing.JTabbedPane();
        jPanelInicio = new javax.swing.JPanel();
        jLabelusername1 = new javax.swing.JLabel();
        jLabelipdireccion1 = new javax.swing.JLabel();
        jLabelmemtotal1 = new javax.swing.JLabel();
        jLabelmemcache1 = new javax.swing.JLabel();
        jLabelmodcpu1 = new javax.swing.JLabel();
        jLabelvelocpu1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanelInfoSis = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInfoGeneral = new javax.swing.JTextArea();
        jPanelRAM = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaRAM = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jRadioButtonSImpleRAM = new javax.swing.JRadioButton();
        jRadioButtonAvanzadaRAM = new javax.swing.JRadioButton();
        jPanelParticiones = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaParticion = new javax.swing.JTextArea();
        jPanelProcesos = new javax.swing.JPanel();
        jButtonFinalizarProcesos = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListProcesos = new javax.swing.JList();
        jTextFieldNumeroProceso = new javax.swing.JTextField();
        jButtonActualizar = new javax.swing.JButton();
        jPanelCPU2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDispositivos = new javax.swing.JTextArea();
        jButtonBateria = new javax.swing.JButton();
        jButtonSockets = new javax.swing.JButton();
        jButtonRed = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaDistribucion = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItemGUuardar = new javax.swing.JMenuItem();
        jMenuItemCerrar_Aplicacion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuOpciones = new javax.swing.JMenu();
        jMenuItemMetal = new javax.swing.JMenuItem();
        jMenuItemNimbus = new javax.swing.JMenuItem();
        jMenuItemCDE_Motif = new javax.swing.JMenuItem();
        jMenuItemGTK = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelInicio.setBackground(new java.awt.Color(204, 255, 204));
        jPanelInicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelInicio.setForeground(new java.awt.Color(102, 0, 51));

        jLabelusername1.setBackground(new java.awt.Color(204, 204, 255));
        jLabelusername1.setFont(new java.awt.Font("Utopia", 1, 14)); // NOI18N
        jLabelusername1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelusername1.setText("Nombre");
        jLabelusername1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelipdireccion1.setFont(new java.awt.Font("Utopia", 3, 14)); // NOI18N
        jLabelipdireccion1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelipdireccion1.setText("Dirección");
        jLabelipdireccion1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelmemtotal1.setFont(new java.awt.Font("Utopia", 1, 14)); // NOI18N
        jLabelmemtotal1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelmemtotal1.setText("Mem.Total");
        jLabelmemtotal1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelmemcache1.setFont(new java.awt.Font("Utopia", 1, 14)); // NOI18N
        jLabelmemcache1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelmemcache1.setText("Mem.Caché");
        jLabelmemcache1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelmodcpu1.setFont(new java.awt.Font("Utopia", 1, 14)); // NOI18N
        jLabelmodcpu1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelmodcpu1.setText("Model.Cpu");
        jLabelmodcpu1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelvelocpu1.setFont(new java.awt.Font("Utopia", 1, 14)); // NOI18N
        jLabelvelocpu1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelvelocpu1.setText("Velocidad");
        jLabelvelocpu1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        jButton1.setText("Salir");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jButton1.setMargin(new java.awt.Insets(6, 14, 2, 14));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Nimbus Sans L", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("INFORMACIÓN PRINCIPAL DEL SISTEMA");

        javax.swing.GroupLayout jPanelInicioLayout = new javax.swing.GroupLayout(jPanelInicio);
        jPanelInicio.setLayout(jPanelInicioLayout);
        jPanelInicioLayout.setHorizontalGroup(
            jPanelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInicioLayout.createSequentialGroup()
                .addContainerGap(989, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(jPanelInicioLayout.createSequentialGroup()
                .addGroup(jPanelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInicioLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jPanelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelvelocpu1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelmemcache1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelmemtotal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelipdireccion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelusername1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelmodcpu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelInicioLayout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelInicioLayout.setVerticalGroup(
            jPanelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicioLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelusername1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelipdireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelmemtotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelmemcache1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelmodcpu1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelvelocpu1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jRadioButtonVS.addTab("Inicio", jPanelInicio);

        jTextAreaInfoGeneral.setColumns(20);
        jTextAreaInfoGeneral.setRows(5);
        jScrollPane1.setViewportView(jTextAreaInfoGeneral);

        javax.swing.GroupLayout jPanelInfoSisLayout = new javax.swing.GroupLayout(jPanelInfoSis);
        jPanelInfoSis.setLayout(jPanelInfoSisLayout);
        jPanelInfoSisLayout.setHorizontalGroup(
            jPanelInfoSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
        );
        jPanelInfoSisLayout.setVerticalGroup(
            jPanelInfoSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );

        jRadioButtonVS.addTab("Informacion General", jPanelInfoSis);

        jTextAreaRAM.setColumns(20);
        jTextAreaRAM.setRows(5);
        jScrollPane2.setViewportView(jTextAreaRAM);

        jRadioButtonSImpleRAM.setSelected(true);
        jRadioButtonSImpleRAM.setText("Vista Simple");
        jRadioButtonSImpleRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSImpleRAMActionPerformed(evt);
            }
        });

        jRadioButtonAvanzadaRAM.setText("Vista Avanzada");
        jRadioButtonAvanzadaRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAvanzadaRAMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jRadioButtonSImpleRAM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonAvanzadaRAM)
                .addGap(0, 858, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButtonSImpleRAM)
                .addComponent(jRadioButtonAvanzadaRAM))
        );

        javax.swing.GroupLayout jPanelRAMLayout = new javax.swing.GroupLayout(jPanelRAM);
        jPanelRAM.setLayout(jPanelRAMLayout);
        jPanelRAMLayout.setHorizontalGroup(
            jPanelRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE)
        );
        jPanelRAMLayout.setVerticalGroup(
            jPanelRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRAMLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
        );

        jRadioButtonVS.addTab("RAM", jPanelRAM);

        jTextAreaParticion.setColumns(20);
        jTextAreaParticion.setRows(5);
        jScrollPane4.setViewportView(jTextAreaParticion);

        javax.swing.GroupLayout jPanelParticionesLayout = new javax.swing.GroupLayout(jPanelParticiones);
        jPanelParticiones.setLayout(jPanelParticionesLayout);
        jPanelParticionesLayout.setHorizontalGroup(
            jPanelParticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
        );
        jPanelParticionesLayout.setVerticalGroup(
            jPanelParticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );

        jRadioButtonVS.addTab("Particiones", jPanelParticiones);

        jButtonFinalizarProcesos.setText("Finalizar Procesos");
        jButtonFinalizarProcesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarProcesosActionPerformed(evt);
            }
        });

        jScrollPane6.setViewportView(jListProcesos);

        jTextFieldNumeroProceso.setToolTipText("Ingresa el nombre del proceso");

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProcesosLayout = new javax.swing.GroupLayout(jPanelProcesos);
        jPanelProcesos.setLayout(jPanelProcesosLayout);
        jPanelProcesosLayout.setHorizontalGroup(
            jPanelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProcesosLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButtonActualizar)
                .addGap(237, 237, 237)
                .addComponent(jTextFieldNumeroProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonFinalizarProcesos)
                .addGap(0, 395, Short.MAX_VALUE))
            .addComponent(jScrollPane6)
        );
        jPanelProcesosLayout.setVerticalGroup(
            jPanelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProcesosLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNumeroProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFinalizarProcesos)
                    .addComponent(jButtonActualizar))
                .addGap(0, 33, Short.MAX_VALUE))
        );

        jRadioButtonVS.addTab("Procesos", jPanelProcesos);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"processor", null},
                {"vendor_id", null},
                {"cpu family", null},
                {"model", null},
                {"model name", null},
                {"stepping", null},
                {"microcode", null},
                {"cpu MHz", null},
                {"cache size", null},
                {"physical id", null},
                {"siblings", null},
                {"core id", null},
                {"cpu cores", null},
                {"apicid", null},
                {"initial apicid", null},
                {"fpu", null},
                {"fpu_exception", null},
                {"cpuid_level", null},
                {"wp", null},
                {"flags", null},
                {"bogomips", null},
                {"TLB size", null},
                {"clflush size", null},
                {"cache alignment", null},
                {"address size", null},
                {"power management", null},
                {null, null},
                {"processor", null},
                {"vendor_id", null},
                {"cpu family", null},
                {"name", null},
                {"model name", null},
                {"stepping", null},
                {"microcode", null},
                {"cpu MHz", null},
                {"cache size", null},
                {"physical id", null},
                {"siblings", null},
                {"core id", null},
                {"cpu cores", null},
                {"apicid", null},
                {"initial apicid", null},
                {"fpu", null},
                {"fpu_exception", null},
                {"cpuid level", null},
                {"wp", null},
                {"flags", null},
                {"bogomips", null},
                {"TLB size", null},
                {"clflush size", null},
                {"cache alignment", null},
                {"address sizes", null},
                {"power management", null},
                {"Max Fequencie", null},
                {"Min Frequencie", null},
                {"Transition Letancy", null},
                {"Governor", null},
                {"Available Governors", null}
            },
            new String [] {
                "Informacion", "Avanzada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"vendor_id", null},
                {"model name", null},
                {"cpu MHz", null},
                {"cache size", null},
                {"cache alignment", null}
            },
            new String [] {
                "Informacion", "Simple"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable2);
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(1).setResizable(false);

        javax.swing.GroupLayout jPanelCPU2Layout = new javax.swing.GroupLayout(jPanelCPU2);
        jPanelCPU2.setLayout(jPanelCPU2Layout);
        jPanelCPU2Layout.setHorizontalGroup(
            jPanelCPU2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCPU2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 43, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelCPU2Layout.setVerticalGroup(
            jPanelCPU2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCPU2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelCPU2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jRadioButtonVS.addTab("CPU", jPanelCPU2);

        jTextAreaDispositivos.setColumns(20);
        jTextAreaDispositivos.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDispositivos);

        jButtonBateria.setText("Inf. Bateria");
        jButtonBateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBateriaActionPerformed(evt);
            }
        });

        jButtonSockets.setText("Sockets");
        jButtonSockets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSocketsActionPerformed(evt);
            }
        });

        jButtonRed.setText("Interfaces de Red");
        jButtonRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonBateria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSockets)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRed)))
                .addGap(0, 271, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBateria)
                    .addComponent(jButtonSockets)
                    .addComponent(jButtonRed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jRadioButtonVS.addTab("Dispositivos", jPanel2);

        jTextAreaDistribucion.setColumns(20);
        jTextAreaDistribucion.setRows(5);
        jScrollPane8.setViewportView(jTextAreaDistribucion);

        jButton2.setText("Distribución");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Bateria");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Dispositivos USB");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(686, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jRadioButtonVS.addTab("Distribución", jPanel1);

        jMenuArchivo.setText("Archivo");

        jMenuItemGUuardar.setText("Guardar como...");
        jMenuItemGUuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGUuardarActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemGUuardar);

        jMenuItemCerrar_Aplicacion.setText("Cerrar Aplicacion");
        jMenuItemCerrar_Aplicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCerrar_AplicacionActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemCerrar_Aplicacion);

        jMenuBar1.add(jMenuArchivo);

        jMenu2.setText("Opciones");

        jMenuOpciones.setText("Temas");

        jMenuItemMetal.setText("Metal");
        jMenuItemMetal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMetalActionPerformed(evt);
            }
        });
        jMenuOpciones.add(jMenuItemMetal);

        jMenuItemNimbus.setText("Nimbus");
        jMenuItemNimbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNimbusActionPerformed(evt);
            }
        });
        jMenuOpciones.add(jMenuItemNimbus);

        jMenuItemCDE_Motif.setText("CDE/Motif");
        jMenuItemCDE_Motif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCDE_MotifActionPerformed(evt);
            }
        });
        jMenuOpciones.add(jMenuItemCDE_Motif);

        jMenuItemGTK.setText("GTK+(Por Defecto)");
        jMenuItemGTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGTKActionPerformed(evt);
            }
        });
        jMenuOpciones.add(jMenuItemGTK);

        jMenu2.add(jMenuOpciones);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jRadioButtonVS, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jRadioButtonVS, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemMetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMetalActionPerformed
        // TODO add your handling code here:
        setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        lookAdFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
    }//GEN-LAST:event_jMenuItemMetalActionPerformed

    private void jMenuItemNimbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNimbusActionPerformed
        // TODO add your handling code here:
        setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        lookAdFeel = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
    }//GEN-LAST:event_jMenuItemNimbusActionPerformed

    private void jMenuItemCDE_MotifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCDE_MotifActionPerformed
        // TODO add your handling code here:
        setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        lookAdFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    }//GEN-LAST:event_jMenuItemCDE_MotifActionPerformed

    private void jMenuItemGTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGTKActionPerformed
        // TODO add your handling code here:
        setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        lookAdFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    }//GEN-LAST:event_jMenuItemGTKActionPerformed

    private void jMenuItemGUuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGUuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_jMenuItemGUuardarActionPerformed

    private void jMenuItemCerrar_AplicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCerrar_AplicacionActionPerformed
        // TODO add your handling code here:
         int ax = JOptionPane.showConfirmDialog(null,"Quiere salir de la aplicacion","Salir",0);
     
        if(ax == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        else if(ax == JOptionPane.NO_OPTION)
        {
        }
    }//GEN-LAST:event_jMenuItemCerrar_AplicacionActionPerformed

    private void jButtonFinalizarProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarProcesosActionPerformed
        MatarprocesoPID();
    }//GEN-LAST:event_jButtonFinalizarProcesosActionPerformed

    private void jRadioButtonAvanzadaRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAvanzadaRAMActionPerformed
        // TODO add your handling code here:
        jTextAreaRAM.setText("");
        mostrarmeminfoavanzada();
        jRadioButtonSImpleRAM.setSelected(false);
    }//GEN-LAST:event_jRadioButtonAvanzadaRAMActionPerformed

    private void jRadioButtonSImpleRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSImpleRAMActionPerformed
        // TODO add your handling code here:
        jTextAreaRAM.setText("");
        mostrarmeminfosimple();
        jRadioButtonAvanzadaRAM.setSelected(false);
    }//GEN-LAST:event_jRadioButtonSImpleRAMActionPerformed

    private void jButtonBateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBateriaActionPerformed
        // TODO add your handling code here:
        
         jTextAreaDispositivos.setText("");
        comandoconsolaActualizacion("cat /sys/class/power_supply/BAT1/uevent ");
         mostrarSockets(); 
    }//GEN-LAST:event_jButtonBateriaActionPerformed

    private void jButtonSocketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSocketsActionPerformed
      jTextAreaDispositivos.setText("");
        comandoconsolaActualizacion("lsof -i ");
        //ps aux
    mostrarSockets(); 
      
    }//GEN-LAST:event_jButtonSocketsActionPerformed

    private void jButtonRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRedActionPerformed
       jTextAreaDispositivos.setText("");
        comandoconsolaActualizacion("ifconfig ");
         mostrarSockets(); 
    }//GEN-LAST:event_jButtonRedActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = new DefaultListModel();
       jListProcesos.setModel(model);
        comandoconsolaActualizacion("ps aux");
        //ps aux lsof -i 
        mostrarActualizacion();
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // CON ESTE COMANDO VISUALIZAMOS CLARAMENTE LA DISTRIBUCIÒN DE LINUX
        jTextAreaDistribucion.setText("");
        comandoconsolaSockets("lsb_release -idc");
        mostrarSockets();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       //MUESTRA INFORMACION RAPIDA SOBRE LA BATERIA
        jTextAreaDistribucion.setText("");
        comandoconsolaSockets("acpi -bi");
        mostrarSockets();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
       //NOS MUESTRA LOS DISPOSITIVOS USB
        jTextAreaDistribucion.setText("");
        comandoconsolaSockets("lsusb");
        mostrarSockets();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void guardar() {
        String title = jRadioButtonVS.getTitleAt(jRadioButtonVS.getSelectedIndex());
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como...");
            fileChooser.setSelectedFile(new File("*.txt"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Texto Plano", "txt"));
            fileChooser.showSaveDialog(this);
            File Guardar = fileChooser.getSelectedFile();
            if (Guardar != null) {
                PrintWriter Guardx = new PrintWriter(Guardar);
                elegirJText(title, Guardx);
                Guardx.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void elegirJText(String title, PrintWriter Guardx) throws IOException {
        if (title.equals("Informacion General")) {
            Guardx.print(jTextAreaInfoGeneral.getText());
        } else if (title.equals("RAM")) {
            Guardx.print(jTextAreaRAM.getText());
        } else if (title.equals("CPU")) {
            //Guardx.print(jTextAreaCPU.getText());
        } else if (title.equals("Particiones")) {
            Guardx.print(jTextAreaParticion.getText());
        }
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VetanaPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBateria;
    private javax.swing.JButton jButtonFinalizarProcesos;
    private javax.swing.JButton jButtonRed;
    private javax.swing.JButton jButtonSockets;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelipdireccion1;
    private javax.swing.JLabel jLabelmemcache1;
    private javax.swing.JLabel jLabelmemtotal1;
    private javax.swing.JLabel jLabelmodcpu1;
    private javax.swing.JLabel jLabelusername1;
    private javax.swing.JLabel jLabelvelocpu1;
    private javax.swing.JList jListProcesos;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemCDE_Motif;
    private javax.swing.JMenuItem jMenuItemCerrar_Aplicacion;
    private javax.swing.JMenuItem jMenuItemGTK;
    private javax.swing.JMenuItem jMenuItemGUuardar;
    private javax.swing.JMenuItem jMenuItemMetal;
    private javax.swing.JMenuItem jMenuItemNimbus;
    private javax.swing.JMenu jMenuOpciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelCPU2;
    private javax.swing.JPanel jPanelInfoSis;
    private javax.swing.JPanel jPanelInicio;
    private javax.swing.JPanel jPanelParticiones;
    private javax.swing.JPanel jPanelProcesos;
    private javax.swing.JPanel jPanelRAM;
    private javax.swing.JRadioButton jRadioButtonAvanzadaRAM;
    private javax.swing.JRadioButton jRadioButtonSImpleRAM;
    private javax.swing.JTabbedPane jRadioButtonVS;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextAreaDispositivos;
    private javax.swing.JTextArea jTextAreaDistribucion;
    private javax.swing.JTextArea jTextAreaInfoGeneral;
    private javax.swing.JTextArea jTextAreaParticion;
    private javax.swing.JTextArea jTextAreaRAM;
    private javax.swing.JTextField jTextFieldNumeroProceso;
    // End of variables declaration//GEN-END:variables
}
