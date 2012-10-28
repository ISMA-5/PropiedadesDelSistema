/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package propiedadesdelsistema;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    Font fuente;
    int tamañofuente;  
    int estilo;
    String nombrefuente;
    JColorChooser colores;

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
        mostrarmeminfoavanzada();
        tamañofuente = 12;
        fuente = new Font(nombrefuente, estilo, tamañofuente);
    }

    private void setEditablejText() {
        jTextAreaInfoGeneral.setEditable(false);
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
        String meminfo = listmeminfo.get(0);
        String meminfo1 = listmeminfo.get(1);
        String meminfo2 = listmeminfo.get(2);
        String meminfo3 = listmeminfo.get(3);
        String meminfo4 = listmeminfo.get(4);
        String meminfo5 = listmeminfo.get(5);
        String meminfo6 = listmeminfo.get(6);
        TableModel modelo = jTableRAMSIMPLE.getModel();
        modelo.setValueAt(valorDepurado(meminfo), 0, 1);
        modelo.setValueAt(valorDepurado(meminfo1), 1, 1);
        modelo.setValueAt(valorDepurado(meminfo2), 2, 1);
        modelo.setValueAt(valorDepurado(meminfo3), 3, 1);
        modelo.setValueAt(valorDepurado(meminfo4), 4, 1);
        modelo.setValueAt(valorDepurado(meminfo5), 5, 1);
        modelo.setValueAt(valorDepurado(meminfo6), 6, 1);
    }

    private void mostrarmeminfoavanzada() {
        ArrayList<String> listmeminfo = listas.getListmeminfo();
        for (int i = 0; i < listmeminfo.size(); i++) {
            String valorDepurados = valorDepurado(listmeminfo.get(i)+ "\n");
            TableModel modelo = jTableRAM.getModel();
            modelo.setValueAt(valorDepurados, i, 0);
        }
    }
    
    private void llenarinfocpusimple(){
        ArrayList<String> listcpuinfo = listas.getListcpuinfo();
        String cpu = listcpuinfo.get(1);
        String cpu1 = listcpuinfo.get(4);
        String cpu2 = listcpuinfo.get(7);
        String cpu3 = listcpuinfo.get(8);
        String cpu4 = listcpuinfo.get(23);
        TableModel model = jTableCPUdos.getModel();
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
            TableModel model = jTableCPUuno.getModel();
            model.setValueAt(valorDepurado, i, 1);
        }
        String cpu = listcpufreq.get(0);
        String cpu1 = listcpufreq.get(1);
        String cpu2 = listcpufreq.get(2);
        String cpu3 = listcpufreq.get(3);
        String cpu4 = listcpufreq.get(4);
        TableModel model = jTableCPUuno.getModel();
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

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jRadioButtonVS = new javax.swing.JTabbedPane();
        jPanelInicio = new javax.swing.JPanel();
        jLabelusername1 = new javax.swing.JLabel();
        jLabelipdireccion1 = new javax.swing.JLabel();
        jLabelmemtotal1 = new javax.swing.JLabel();
        jLabelmemcache1 = new javax.swing.JLabel();
        jLabelmodcpu1 = new javax.swing.JLabel();
        jLabelvelocpu1 = new javax.swing.JLabel();
        jButtonSalir = new javax.swing.JButton();
        jLabelInfoPrinciSist = new javax.swing.JLabel();
        jLabelLogoTec = new javax.swing.JLabel();
        jLabelLogoNetbeans = new javax.swing.JLabel();
        jPanelInfoSis = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInfoGeneral = new javax.swing.JTextArea();
        jPanelRAM = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableRAM = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableRAMSIMPLE = new javax.swing.JTable();
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
        jTableCPUuno = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableCPUdos = new javax.swing.JTable();
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
        jMenuFormato = new javax.swing.JMenu();
        jMenuTamanio = new javax.swing.JMenu();
        jMenuItemTam13 = new javax.swing.JMenuItem();
        jMenuItemTam14 = new javax.swing.JMenuItem();
        jMenuItemTam15 = new javax.swing.JMenuItem();
        jMenuItemTam16 = new javax.swing.JMenuItem();
        jMenuItemTam18 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuPersonalizar = new javax.swing.JMenu();
        jMenuOpciones = new javax.swing.JMenu();
        jMenuItemMetal = new javax.swing.JMenuItem();
        jMenuItemNimbus = new javax.swing.JMenuItem();
        jMenuItemCDE_Motif = new javax.swing.JMenuItem();
        jMenuItemGTK = new javax.swing.JMenuItem();
        jMenuFondo = new javax.swing.JMenu();
        jMenuItemNegro1 = new javax.swing.JMenuItem();
        jMenuItemMocasin = new javax.swing.JMenuItem();
        jMenuItemRojoObscuro1 = new javax.swing.JMenuItem();
        jMenuItemCardo = new javax.swing.JMenuItem();
        jMenuItemBeige = new javax.swing.JMenuItem();
        jMenuItemAzulClaro1 = new javax.swing.JMenuItem();
        jMenuItemAqua = new javax.swing.JMenuItem();
        jMenuItemGris = new javax.swing.JMenuItem();
        jMenuItemVerdeOliva = new javax.swing.JMenuItem();
        jMenuItemMasColores2 = new javax.swing.JMenuItem();
        jMenuColorTexto = new javax.swing.JMenu();
        jMenuItemNegroText = new javax.swing.JMenuItem();
        jMenuItemRojo1 = new javax.swing.JMenuItem();
        jMenuItemAqua1 = new javax.swing.JMenuItem();
        jMenuItemNaranja1 = new javax.swing.JMenuItem();
        jMenuItemAmarillo1 = new javax.swing.JMenuItem();
        jMenuItemCafeClaro = new javax.swing.JMenuItem();
        jMenuItemMarronRosaseo = new javax.swing.JMenuItem();
        jMenuItemVerde1 = new javax.swing.JMenuItem();
        jMenuItemVerdeClaro1 = new javax.swing.JMenuItem();
        jMenuItemSalmon = new javax.swing.JMenuItem();
        jMenuItemMasColores1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenu2.setText("jMenu2");

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelInicio.setBackground(java.awt.Color.white);
        jPanelInicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelInicio.setForeground(new java.awt.Color(102, 0, 51));
        jPanelInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelusername1.setBackground(new java.awt.Color(204, 204, 255));
        jLabelusername1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jLabelusername1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelusername1.setText("Nombre");
        jLabelusername1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelInicio.add(jLabelusername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 970, 38));

        jLabelipdireccion1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jLabelipdireccion1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelipdireccion1.setText("Dirección");
        jLabelipdireccion1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelInicio.add(jLabelipdireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 970, 34));

        jLabelmemtotal1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jLabelmemtotal1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelmemtotal1.setText("Mem.Total");
        jLabelmemtotal1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelInicio.add(jLabelmemtotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 970, 36));

        jLabelmemcache1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jLabelmemcache1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelmemcache1.setText("Mem.Caché");
        jLabelmemcache1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelInicio.add(jLabelmemcache1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 970, 38));

        jLabelmodcpu1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jLabelmodcpu1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelmodcpu1.setText("Model.Cpu");
        jLabelmodcpu1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelInicio.add(jLabelmodcpu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 970, 33));

        jLabelvelocpu1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jLabelvelocpu1.setForeground(new java.awt.Color(0, 0, 51));
        jLabelvelocpu1.setText("Velocidad");
        jLabelvelocpu1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelInicio.add(jLabelvelocpu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 970, 36));

        jButtonSalir.setBackground(new java.awt.Color(204, 204, 255));
        jButtonSalir.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        jButtonSalir.setText("Salir");
        jButtonSalir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jButtonSalir.setMargin(new java.awt.Insets(6, 14, 2, 14));
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jPanelInicio.add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 320, 66, 35));

        jLabelInfoPrinciSist.setFont(new java.awt.Font("Nimbus Sans L", 1, 18)); // NOI18N
        jLabelInfoPrinciSist.setForeground(new java.awt.Color(1, 1, 1));
        jLabelInfoPrinciSist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInfoPrinciSist.setText("INFORMACIÓN PRINCIPAL DEL SISTEMA");
        jPanelInicio.add(jLabelInfoPrinciSist, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 970, 29));

        jLabelLogoTec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/propiedadesdelsistema/portadaaa4.jpg"))); // NOI18N
        jPanelInicio.add(jLabelLogoTec, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 410));

        jLabelLogoNetbeans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/propiedadesdelsistema/logo.jpg"))); // NOI18N
        jPanelInicio.add(jLabelLogoNetbeans, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 260, 100, 110));

        jRadioButtonVS.addTab("Inicio", jPanelInicio);

        jTextAreaInfoGeneral.setColumns(20);
        jTextAreaInfoGeneral.setRows(5);
        jScrollPane1.setViewportView(jTextAreaInfoGeneral);

        javax.swing.GroupLayout jPanelInfoSisLayout = new javax.swing.GroupLayout(jPanelInfoSis);
        jPanelInfoSis.setLayout(jPanelInfoSisLayout);
        jPanelInfoSisLayout.setHorizontalGroup(
            jPanelInfoSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1643, Short.MAX_VALUE)
        );
        jPanelInfoSisLayout.setVerticalGroup(
            jPanelInfoSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );

        jRadioButtonVS.addTab("Informacion General", jPanelInfoSis);

        jTableRAM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"MemTotal:", ""},
                {"MemFree:", null},
                {"Buffers:", null},
                {"Cached:", null},
                {"SwapCached:", null},
                {"Active:", null},
                {"Inactive:", null},
                {"Active(anon):", null},
                {"Inactive(anon):", null},
                {"Active(file):", null},
                {"Inactive(file):", null},
                {"Unevictable:", null},
                {"Mlocked:", null},
                {"HighTotal:", null},
                {"HighFree:", null},
                {"LowTotal:", null},
                {"LowFree:", null},
                {"SwapTotal:", null},
                {"SwapFree:", null},
                {"Dirty:", null},
                {"Writeback:", null},
                {"AnonPages:", null},
                {"Mapped:", null},
                {"Shmem:", null},
                {"Slab:", null},
                {"SReclaimable:", null},
                {"SUnreclaim:", null},
                {"KernelStack:", null},
                {"PageTables:", null},
                {"NFS_Unstable:", null},
                {"Bounce:", null},
                {"WritebackTmp:", null},
                {"CommitLimit:", null},
                {"Committed_AS:", null},
                {"VmallocTotal:", null},
                {"VmallocUsed:", null},
                {"VmallocChunk:", null},
                {"HardwareCorrupted:", null},
                {"AnonHugePages:", null},
                {"HugePages_Total:", null},
                {"HugePages_Free:", null},
                {"HugePages_Rsvd:", null},
                {"HugePages_Surp:", null},
                {"Hugepagesize:", null},
                {"DirectMap4k:", null},
                {"DirectMap4M:", null}
            },
            new String [] {
                "INFORMACION", "AVANZADA"
            }
        ));
        jTableRAM.setFocusable(false);
        jScrollPane9.setViewportView(jTableRAM);

        jTableRAMSIMPLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"MemTotal:", null},
                {"MemFree:", null},
                {"Buffers:", null},
                {"Cached:", null},
                {"SwapCached:", null},
                {"Active:", null},
                {"Inactive:", null}
            },
            new String [] {
                "INFORMACION", "SIMPLE"
            }
        ));
        jScrollPane2.setViewportView(jTableRAMSIMPLE);

        javax.swing.GroupLayout jPanelRAMLayout = new javax.swing.GroupLayout(jPanelRAM);
        jPanelRAM.setLayout(jPanelRAMLayout);
        jPanelRAMLayout.setHorizontalGroup(
            jPanelRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRAMLayout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 510, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelRAMLayout.setVerticalGroup(
            jPanelRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRAMLayout.createSequentialGroup()
                .addGroup(jPanelRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jRadioButtonVS.addTab("RAM", jPanelRAM);

        jTextAreaParticion.setColumns(20);
        jTextAreaParticion.setRows(5);
        jScrollPane4.setViewportView(jTextAreaParticion);

        javax.swing.GroupLayout jPanelParticionesLayout = new javax.swing.GroupLayout(jPanelParticiones);
        jPanelParticiones.setLayout(jPanelParticionesLayout);
        jPanelParticionesLayout.setHorizontalGroup(
            jPanelParticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1643, Short.MAX_VALUE)
        );
        jPanelParticionesLayout.setVerticalGroup(
            jPanelParticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
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
                .addGap(0, 993, Short.MAX_VALUE))
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
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jRadioButtonVS.addTab("Procesos", jPanelProcesos);

        jTableCPUuno.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTableCPUuno);

        jTableCPUdos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTableCPUdos);
        jTableCPUdos.getColumnModel().getColumn(0).setResizable(false);
        jTableCPUdos.getColumnModel().getColumn(1).setResizable(false);

        javax.swing.GroupLayout jPanelCPU2Layout = new javax.swing.GroupLayout(jPanelCPU2);
        jPanelCPU2.setLayout(jPanelCPU2Layout);
        jPanelCPU2Layout.setHorizontalGroup(
            jPanelCPU2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCPU2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 582, Short.MAX_VALUE)
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
                .addComponent(jButtonBateria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSockets)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRed)
                .addGap(0, 1351, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBateria)
                    .addComponent(jButtonSockets)
                    .addComponent(jButtonRed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
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
                .addContainerGap(1304, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jMenuFormato.setText("Formato");

        jMenuTamanio.setText("Tamaño");

        jMenuItemTam13.setBackground(new java.awt.Color(247, 250, 251));
        jMenuItemTam13.setText("13");
        jMenuItemTam13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTam13ActionPerformed(evt);
            }
        });
        jMenuTamanio.add(jMenuItemTam13);

        jMenuItemTam14.setText("14");
        jMenuItemTam14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTam14ActionPerformed(evt);
            }
        });
        jMenuTamanio.add(jMenuItemTam14);

        jMenuItemTam15.setText("15");
        jMenuItemTam15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTam15ActionPerformed(evt);
            }
        });
        jMenuTamanio.add(jMenuItemTam15);

        jMenuItemTam16.setText("16");
        jMenuItemTam16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTam16ActionPerformed(evt);
            }
        });
        jMenuTamanio.add(jMenuItemTam16);

        jMenuItemTam18.setText("18");
        jMenuItemTam18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTam18ActionPerformed(evt);
            }
        });
        jMenuTamanio.add(jMenuItemTam18);

        jMenuFormato.add(jMenuTamanio);

        jMenu3.setText("Fuente");

        jMenuItem3.setText("SERIF");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("DIALOG_INPUT");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("MONOSPACED");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem7.setText("SANS_SERIF");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuFormato.add(jMenu3);

        jMenuBar1.add(jMenuFormato);

        jMenuPersonalizar.setText("Personalizar");
        jMenuPersonalizar.setFocusable(false);

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

        jMenuPersonalizar.add(jMenuOpciones);

        jMenuFondo.setText("Color de Fondo");

        jMenuItemNegro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/negro.png"))); // NOI18N
        jMenuItemNegro1.setText("Negro");
        jMenuItemNegro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNegro1ActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemNegro1);

        jMenuItemMocasin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/mocasin.png"))); // NOI18N
        jMenuItemMocasin.setText("Mocasin");
        jMenuItemMocasin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMocasinActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemMocasin);

        jMenuItemRojoObscuro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/rojo_os.png"))); // NOI18N
        jMenuItemRojoObscuro1.setText("Rojo Obscuro");
        jMenuItemRojoObscuro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRojoObscuro1ActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemRojoObscuro1);

        jMenuItemCardo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/cardio.png"))); // NOI18N
        jMenuItemCardo.setText("Cardo");
        jMenuItemCardo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCardoActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemCardo);

        jMenuItemBeige.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/beige.png"))); // NOI18N
        jMenuItemBeige.setText("Beige");
        jMenuItemBeige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBeigeActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemBeige);

        jMenuItemAzulClaro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/azul.png"))); // NOI18N
        jMenuItemAzulClaro1.setText("Azul Obscuro");
        jMenuItemAzulClaro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAzulClaro1ActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemAzulClaro1);

        jMenuItemAqua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/aqua.png"))); // NOI18N
        jMenuItemAqua.setText("Aqua");
        jMenuItemAqua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAquaActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemAqua);

        jMenuItemGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/gris.png"))); // NOI18N
        jMenuItemGris.setText("Gris");
        jMenuItemGris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGrisActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemGris);

        jMenuItemVerdeOliva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/verde_oliva.png"))); // NOI18N
        jMenuItemVerdeOliva.setText("Verde Oliva");
        jMenuItemVerdeOliva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVerdeOlivaActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemVerdeOliva);

        jMenuItemMasColores2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/pick_color.png"))); // NOI18N
        jMenuItemMasColores2.setText("Más Colores");
        jMenuItemMasColores2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMasColores2ActionPerformed(evt);
            }
        });
        jMenuFondo.add(jMenuItemMasColores2);

        jMenuPersonalizar.add(jMenuFondo);

        jMenuColorTexto.setText("Color de Texto");

        jMenuItemNegroText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/negro.png"))); // NOI18N
        jMenuItemNegroText.setText("Negro");
        jMenuItemNegroText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNegroTextActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemNegroText);

        jMenuItemRojo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/rojo.png"))); // NOI18N
        jMenuItemRojo1.setText("Rojo");
        jMenuItemRojo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRojo1ActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemRojo1);

        jMenuItemAqua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/aqua.png"))); // NOI18N
        jMenuItemAqua1.setText("Aqua");
        jMenuItemAqua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAqua1ActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemAqua1);

        jMenuItemNaranja1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/naranja.png"))); // NOI18N
        jMenuItemNaranja1.setText("Naranja");
        jMenuItemNaranja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNaranja1ActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemNaranja1);

        jMenuItemAmarillo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/amarillo.png"))); // NOI18N
        jMenuItemAmarillo1.setText("Amarillo");
        jMenuItemAmarillo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAmarillo1ActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemAmarillo1);

        jMenuItemCafeClaro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/cafe.png"))); // NOI18N
        jMenuItemCafeClaro.setText("Cafe Claro");
        jMenuItemCafeClaro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCafeClaroActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemCafeClaro);

        jMenuItemMarronRosaseo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/marron_rosaseo.png"))); // NOI18N
        jMenuItemMarronRosaseo.setText("Marron Rosaseo");
        jMenuItemMarronRosaseo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMarronRosaseoActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemMarronRosaseo);

        jMenuItemVerde1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/verde.png"))); // NOI18N
        jMenuItemVerde1.setText("Verde");
        jMenuItemVerde1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVerde1ActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemVerde1);

        jMenuItemVerdeClaro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/verde_cl.png"))); // NOI18N
        jMenuItemVerdeClaro1.setText("Verde Claro");
        jMenuItemVerdeClaro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVerdeClaro1ActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemVerdeClaro1);

        jMenuItemSalmon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/salmon.png"))); // NOI18N
        jMenuItemSalmon.setText("Salmon");
        jMenuItemSalmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalmonActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemSalmon);

        jMenuItemMasColores1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconosColores/pick_color.png"))); // NOI18N
        jMenuItemMasColores1.setText("Más Colores");
        jMenuItemMasColores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMasColores1ActionPerformed(evt);
            }
        });
        jMenuColorTexto.add(jMenuItemMasColores1);

        jMenuPersonalizar.add(jMenuColorTexto);

        jMenuBar1.add(jMenuPersonalizar);

        jMenu1.setText("Acerca de...");

        jMenuItem1.setText("Informacion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jRadioButtonVS)
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

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);

    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // CON ESTE COMANDO VISUALIZAMOS CLARAMENTE LA DISTRIBUCIÒN DE LINUX
        jTextAreaDistribucion.setText("");
        ArrayList<String> listadistribucion = listas.getListdistribucion();
        for (int i = 0; i < listadistribucion.size(); i++) {
            jTextAreaDistribucion.append(listadistribucion.get(i) + '\n');
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       //MUESTRA INFORMACION RAPIDA SOBRE LA BATERIA
         jTextAreaDistribucion.setText("");
         jTextAreaDistribucion.append("Usuario" + ':'  + "      "       + '\t' + listas.comandoconsolagenerico("hostname") + '\n');
         jTextAreaDistribucion.append("Bateria" + ':'  + "      "       + '\t' + listas.comandoconsolagenerico("acpi -bi") + '\n');
         jTextAreaDistribucion.append("Fecha" + ':'  + "      "       + '\t' + listas.comandoconsolagenerico("date") + '\n');
       
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
       //NOS MUESTRA LOS DISPOSITIVOS USB
        jTextAreaDistribucion.setText("");
        ArrayList<String> listadistribucion = listas.getListusb();
        for (int i = 0; i < listadistribucion.size(); i++) {
            jTextAreaDistribucion.append(listadistribucion.get(i) + '\n');
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Software: Propiedades del Sistema\n"+"Elaborado por:ISMA-5\n"+"Version del Software: 1.0\n"+"Formacion legal: Software Libre\n"+"Numero de Colaboradores: 20\n","Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemTam14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTam14ActionPerformed
        // TODO add your handling code here:
        tamañofuente = 14;
        fuente = new Font(nombrefuente, estilo, tamañofuente);
        jTextAreaInfoGeneral.setFont(fuente);
        jTableCPUuno.setFont(fuente);
        jTableCPUdos.setFont(fuente);
        jTextAreaParticion.setFont(fuente);
        jTableRAM.setFont(fuente);
        jScrollPane3.setFont(fuente);
        jScrollPane1.setFont(fuente);
        jTableRAMSIMPLE.setFont(fuente);
         jLabelusername1.setFont(fuente);
        jLabelipdireccion1.setFont(fuente);
        jLabelInfoPrinciSist.setFont(fuente);
        jListProcesos.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemcache1.setFont(fuente);
        jLabelmodcpu1.setFont(fuente);
        jLabelvelocpu1.setFont(fuente);
        jButtonSalir.setFont(fuente);   
        jListProcesos.setFont(fuente);
        jTextAreaDistribucion.setFont(fuente);
        jScrollPane3.setFont(fuente);
        jPanel2.setFont(fuente);
        jTextAreaDispositivos.setFont(fuente);
        

    }//GEN-LAST:event_jMenuItemTam14ActionPerformed

    private void jMenuItemTam15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTam15ActionPerformed
        // TODO add your handling code here:
        tamañofuente = 15;
        fuente = new Font(nombrefuente, estilo, tamañofuente);
        jTextAreaInfoGeneral.setFont(fuente);
         jScrollPane3.setFont(fuente);
        jPanel2.setFont(fuente);
        jTextAreaDispositivos.setFont(fuente);
        jTableCPUuno.setFont(fuente);
        jListProcesos.setFont(fuente);
        jTableCPUdos.setFont(fuente);
        jTableRAMSIMPLE.setFont(fuente);
        jScrollPane1.setFont(fuente);
        jTextAreaParticion.setFont(fuente);
        jScrollPane3.setFont(fuente);
        jTableRAM.setFont(fuente);
         jLabelusername1.setFont(fuente);
         jListProcesos.setFont(fuente);
        jLabelipdireccion1.setFont(fuente);
        jLabelInfoPrinciSist.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemcache1.setFont(fuente);
        jLabelmodcpu1.setFont(fuente);
        jLabelvelocpu1.setFont(fuente);
        jButtonSalir.setFont(fuente);

    }//GEN-LAST:event_jMenuItemTam15ActionPerformed

    private void jMenuItemTam16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTam16ActionPerformed
        // TODO add your handling code here:
        tamañofuente = 16;
        fuente = new Font(nombrefuente, estilo, tamañofuente);
        jTextAreaInfoGeneral.setFont(fuente);
         jTableCPUuno.setFont(fuente);
        jTableCPUdos.setFont(fuente);
        jTableRAMSIMPLE.setFont(fuente);
         jScrollPane3.setFont(fuente);
        jPanel2.setFont(fuente);
        jTextAreaDispositivos.setFont(fuente);
        jTextAreaParticion.setFont(fuente);
        jScrollPane1.setFont(fuente);
        jTableRAM.setFont(fuente);
        jScrollPane3.setFont(fuente);
        jListProcesos.setFont(fuente);
         jLabelusername1.setFont(fuente);
        jLabelipdireccion1.setFont(fuente);
        jLabelInfoPrinciSist.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemcache1.setFont(fuente);
        jLabelmodcpu1.setFont(fuente);
        jLabelvelocpu1.setFont(fuente);
        jButtonSalir.setFont(fuente);
        jListProcesos.setFont(fuente);
        jTextAreaDistribucion.setFont(fuente);

    }//GEN-LAST:event_jMenuItemTam16ActionPerformed

    private void jMenuItemTam18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTam18ActionPerformed
        // TODO add your handling code here:
        tamañofuente = 18;
        fuente = new Font(nombrefuente, estilo, tamañofuente);
        jTextAreaInfoGeneral.setFont(fuente);
         jTableCPUuno.setFont(fuente);
         jListProcesos.setFont(fuente);
        jTableCPUdos.setFont(fuente);
        jScrollPane1.setFont(fuente);
         jScrollPane3.setFont(fuente);
        jPanel2.setFont(fuente);
        jTextAreaDispositivos.setFont(fuente);
        jTextAreaParticion.setFont(fuente);
        jTableRAM.setFont(fuente);
        jTableRAMSIMPLE.setFont(fuente);
         jLabelusername1.setFont(fuente);
         jScrollPane3.setFont(fuente);
        jLabelipdireccion1.setFont(fuente);
        jLabelInfoPrinciSist.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemcache1.setFont(fuente);
        jLabelmodcpu1.setFont(fuente);
        jLabelvelocpu1.setFont(fuente);
        jButtonSalir.setFont(fuente);
        jTextAreaDistribucion.setFont(fuente);
        

    }//GEN-LAST:event_jMenuItemTam18ActionPerformed

    private void jMenuItemTam13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTam13ActionPerformed
        // TODO add your handling code here:
        tamañofuente = 13;
        fuente = new Font(nombrefuente, estilo, tamañofuente);
        jTextAreaInfoGeneral.setFont(fuente);
         jTableCPUuno.setFont(fuente);
         jTableCPUdos.setFont(fuente);
        jTextAreaParticion.setFont(fuente);
        jTableRAM.setFont(fuente);
        jScrollPane3.setFont(fuente);
        jTableRAMSIMPLE.setFont(fuente);
        jListProcesos.setFont(fuente);
        jLabelusername1.setFont(fuente);
        jLabelipdireccion1.setFont(fuente);
        jLabelInfoPrinciSist.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemcache1.setFont(fuente);
        jLabelmodcpu1.setFont(fuente);
        jLabelvelocpu1.setFont(fuente);
        jButtonSalir.setFont(fuente);
        jScrollPane1.setFont(fuente);
        jTextAreaDispositivos.setFont(fuente);
        jTextAreaDistribucion.setFont(fuente);
        

    }//GEN-LAST:event_jMenuItemTam13ActionPerformed

    private void jMenuItemNegro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNegro1ActionPerformed
        // TODO add your handling code here:
        jTextAreaInfoGeneral.setBackground(Color.decode("000000"));
        
        jTextAreaDistribucion.setBackground(Color.decode("000000"));
        jTextAreaDispositivos.setBackground(Color.decode("000000"));
        jPanelInicio.setBackground(Color.decode("000000"));
        
        jTextAreaInfoGeneral.setBackground(Color.decode("000000"));
        jTableCPUuno.setBackground(Color.decode("000000"));
        jTableCPUdos.setBackground(Color.decode("000000"));
        jTextAreaParticion.setBackground(Color.decode("000000"));
        jTableRAM.setBackground(Color.decode("000000"));
        jTableRAMSIMPLE.setBackground(Color.decode("000000"));
        jScrollPane3.setBackground(Color.decode("000000"));
        jListProcesos.setBackground(Color.decode("000000"));
        jLabelusername1.setBackground(Color.decode("000000"));
        jLabelipdireccion1.setBackground(Color.decode("000000"));
        jLabelInfoPrinciSist.setBackground(Color.decode("000000"));
        jLabelmemtotal1.setBackground(Color.decode("000000"));
        jLabelmemtotal1.setBackground(Color.decode("000000"));
        jLabelmemcache1.setBackground(Color.decode("000000"));
        jLabelmodcpu1.setBackground(Color.decode("000000"));
        jLabelvelocpu1.setBackground(Color.decode("000000"));
    }//GEN-LAST:event_jMenuItemNegro1ActionPerformed

    private void jMenuItemMocasinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMocasinActionPerformed
        // TODO add your handling code here:
        Color mocasin = new Color(255, 228, 181);
        jTextAreaInfoGeneral.setBackground(mocasin);
        jTextAreaDispositivos.setBackground(mocasin);
        jPanelInicio.setBackground(mocasin);
        jTextAreaDistribucion.setBackground(mocasin);
        jTextAreaInfoGeneral.setBackground(mocasin);
        jTableCPUuno.setBackground(mocasin);
        jTableCPUdos.setBackground(mocasin);
        jTextAreaParticion.setBackground(mocasin);
        jTableRAM.setBackground(mocasin);
        jTableRAMSIMPLE.setBackground(mocasin);
        jScrollPane3.setBackground(mocasin);
        jListProcesos.setBackground(mocasin);
        jLabelusername1.setBackground(mocasin);
        jLabelipdireccion1.setBackground(mocasin);
        jLabelInfoPrinciSist.setBackground(mocasin);
        jLabelmemtotal1.setBackground(mocasin);
        jLabelmemtotal1.setBackground(mocasin);
        jLabelmemcache1.setBackground(mocasin);
        jLabelmodcpu1.setBackground(mocasin);
        jLabelvelocpu1.setBackground(mocasin);
       
    }//GEN-LAST:event_jMenuItemMocasinActionPerformed

    private void jMenuItemRojoObscuro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRojoObscuro1ActionPerformed
        // TODO add your handling code here:
        Color rojo_obs = new Color(0xc00000);
        jTextAreaInfoGeneral.setBackground(rojo_obs);
         
        jTextAreaDispositivos.setBackground(rojo_obs);
        jPanelInicio.setBackground(rojo_obs);
        jTextAreaDistribucion.setBackground(rojo_obs);
        jTextAreaInfoGeneral.setBackground(rojo_obs);
        jTableCPUuno.setBackground(rojo_obs);
        jTableCPUdos.setBackground(rojo_obs);
        jTextAreaParticion.setBackground(rojo_obs);
        jTableRAM.setBackground(rojo_obs);
        jTableRAMSIMPLE.setBackground(rojo_obs);
        jScrollPane3.setBackground(rojo_obs);
        jListProcesos.setBackground(rojo_obs);
        jLabelusername1.setBackground(rojo_obs);
        jLabelipdireccion1.setBackground(rojo_obs);
        jLabelInfoPrinciSist.setBackground(rojo_obs);
        jLabelmemtotal1.setBackground(rojo_obs);
        jLabelmemtotal1.setBackground(rojo_obs);
        jLabelmemcache1.setBackground(rojo_obs);
        jLabelmodcpu1.setBackground(rojo_obs);
        jLabelvelocpu1.setBackground(rojo_obs);
        
    }//GEN-LAST:event_jMenuItemRojoObscuro1ActionPerformed

    private void jMenuItemCardoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCardoActionPerformed
        // TODO add your handling code here:
        Color cardo = new Color(216, 191, 216);
       jTextAreaInfoGeneral.setBackground(cardo);
        jTextAreaInfoGeneral.setBackground(cardo);
        jLabelvelocpu1.setBackground(cardo);
        jTableCPUuno.setBackground(cardo);
        jTextAreaDispositivos.setBackground(cardo);
        jPanelInicio.setBackground(cardo);
        jTextAreaDistribucion.setBackground(cardo);
        jTableCPUdos.setBackground(cardo);
        jTextAreaParticion.setBackground(cardo);
        jTableRAM.setBackground(cardo);
        jTableRAMSIMPLE.setBackground(cardo);
        jScrollPane3.setBackground(cardo);
        jListProcesos.setBackground(cardo);
        jLabelusername1.setBackground(cardo);
        jLabelipdireccion1.setBackground(cardo);
        jLabelInfoPrinciSist.setBackground(cardo);
        jLabelmemtotal1.setBackground(cardo);
        jLabelmemtotal1.setBackground(cardo);
        jLabelmemcache1.setBackground(cardo);
        jLabelmodcpu1.setBackground(cardo);
        
    }//GEN-LAST:event_jMenuItemCardoActionPerformed

    private void jMenuItemBeigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBeigeActionPerformed
        // TODO add your handling code here:
        Color beige = new Color(255, 255, 204);
       jTextAreaInfoGeneral.setBackground(beige);
        jTextAreaInfoGeneral.setBackground(beige);
        jTextAreaDispositivos.setBackground(beige);
        jPanelInicio.setBackground(beige);
        jTextAreaDistribucion.setBackground(beige);
        jTableCPUuno.setBackground(beige);
        jTableCPUdos.setBackground(beige);
        jTextAreaParticion.setBackground(beige);
        jTableRAM.setBackground(beige);
        jTableRAMSIMPLE.setBackground(beige);
        jScrollPane3.setBackground(beige);
        jListProcesos.setBackground(beige);
        jLabelusername1.setBackground(beige);
        jLabelipdireccion1.setBackground(beige);
        jLabelInfoPrinciSist.setBackground(beige);
        jLabelmemtotal1.setBackground(beige);
        jLabelvelocpu1.setBackground(beige);
        jLabelmemtotal1.setBackground(beige);
        jLabelmemcache1.setBackground(beige);
        jLabelmodcpu1.setBackground(beige);
        
    }//GEN-LAST:event_jMenuItemBeigeActionPerformed

    private void jMenuItemAzulClaro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAzulClaro1ActionPerformed
        // TODO add your handling code here:
        Color azul = new Color(0x0070c0);
            jTextAreaInfoGeneral.setBackground(azul);
        jTextAreaInfoGeneral.setBackground(azul);
        jTableCPUuno.setBackground(azul);
        jTableCPUdos.setBackground(azul);
         
        jTextAreaDispositivos.setBackground(azul);
        jPanelInicio.setBackground(azul);
        jTextAreaDistribucion.setBackground(azul);
        jTextAreaParticion.setBackground(azul);
        jTableRAM.setBackground(azul);
        jTableRAMSIMPLE.setBackground(azul);
        jScrollPane3.setBackground(azul);
        jListProcesos.setBackground(azul);
        jLabelusername1.setBackground(azul);
        jLabelipdireccion1.setBackground(azul);
        jLabelInfoPrinciSist.setBackground(azul);
        jLabelmemtotal1.setBackground(azul);
        jLabelmemtotal1.setBackground(azul);
        jLabelmemcache1.setBackground(azul);
        jLabelmodcpu1.setBackground(azul);
jLabelvelocpu1.setBackground(azul);
    
    }//GEN-LAST:event_jMenuItemAzulClaro1ActionPerformed

    private void jMenuItemAquaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAquaActionPerformed
        // TODO add your handling code here:
        Color aqua = new Color(0, 255, 255);
        jTextAreaInfoGeneral.setBackground(aqua);
        jTextAreaInfoGeneral.setBackground(aqua);
        jTableCPUuno.setBackground(aqua);
        jTableCPUdos.setBackground(aqua);
        jTextAreaParticion.setBackground(aqua);
        jTextAreaDispositivos.setBackground(aqua);
        jPanelInicio.setBackground(aqua);
        jTextAreaDistribucion.setBackground(aqua);
        jTableRAM.setBackground(aqua);
        jTableRAMSIMPLE.setBackground(aqua);
        jScrollPane3.setBackground(aqua);
        jListProcesos.setBackground(aqua);
        jLabelusername1.setBackground(aqua);
        jLabelipdireccion1.setBackground(aqua);
        jLabelInfoPrinciSist.setBackground(aqua);
        jLabelmemtotal1.setBackground(aqua);
        jLabelmemtotal1.setBackground(aqua);
        jLabelmemcache1.setBackground(aqua);
        jLabelvelocpu1.setBackground(aqua);
        jLabelmodcpu1.setBackground(aqua);
        
    }//GEN-LAST:event_jMenuItemAquaActionPerformed

    private void jMenuItemGrisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGrisActionPerformed
        // TODO add your handling code here:
        Color gris = new Color(217, 217, 217);
                jTextAreaInfoGeneral.setBackground(gris);
        jTextAreaInfoGeneral.setBackground(gris);
        jTableCPUuno.setBackground(gris);
        jTableCPUdos.setBackground(gris);
        jTextAreaParticion.setBackground(gris);
         
        jTextAreaDispositivos.setBackground(gris);
        jPanelInicio.setBackground(gris);
        jTextAreaDistribucion.setBackground(gris);
        jTableRAM.setBackground(gris);
        jTableRAMSIMPLE.setBackground(gris);
        jScrollPane3.setBackground(gris);
        jListProcesos.setBackground(gris);
        jLabelusername1.setBackground(gris);
        jLabelipdireccion1.setBackground(gris);
        jLabelInfoPrinciSist.setBackground(gris);
        jLabelvelocpu1.setBackground(gris);
        jLabelmemtotal1.setBackground(gris);
        jLabelmemtotal1.setBackground(gris);
        jLabelmemcache1.setBackground(gris);
        jLabelmodcpu1.setBackground(gris);
        

    }//GEN-LAST:event_jMenuItemGrisActionPerformed

    private void jMenuItemVerdeOlivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVerdeOlivaActionPerformed
        // TODO add your handling code here:
        Color verdeOliva = new Color(202, 255, 112);
        jTextAreaInfoGeneral.setBackground(verdeOliva);
        jTableCPUuno.setBackground(verdeOliva);
        jTableCPUdos.setBackground(verdeOliva);
        jLabelvelocpu1.setBackground(verdeOliva);
        jTextAreaDispositivos.setBackground(verdeOliva);
        jPanelInicio.setBackground(verdeOliva);
        jTextAreaDistribucion.setBackground(verdeOliva);
        jTextAreaParticion.setBackground(verdeOliva);
        jTableRAM.setBackground(verdeOliva);
        jTableRAMSIMPLE.setBackground(verdeOliva);
        jScrollPane3.setBackground(verdeOliva);
        jListProcesos.setBackground(verdeOliva);
        jLabelusername1.setBackground(verdeOliva);
        jLabelipdireccion1.setBackground(verdeOliva);
        jLabelInfoPrinciSist.setBackground(verdeOliva);
        jLabelmemtotal1.setBackground(verdeOliva);
        jLabelmemtotal1.setBackground(verdeOliva);
        jLabelmemcache1.setBackground(verdeOliva);
        jLabelmodcpu1.setBackground(verdeOliva);
    }//GEN-LAST:event_jMenuItemVerdeOlivaActionPerformed

    private void jMenuItemMasColores2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMasColores2ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Color newColor = JColorChooser.showDialog(this, "Choose Background Color", jTextAreaInfoGeneral.getBackground());

        if (newColor != null) {
           jTextAreaInfoGeneral.setBackground(newColor);
        jTextAreaInfoGeneral.setBackground(newColor);
        jTextAreaInfoGeneral.setBackground(newColor);
        jTableCPUuno.setBackground(newColor);
        jTableCPUdos.setBackground(newColor);
        jTextAreaParticion.setBackground(newColor);
        jTextAreaDispositivos.setBackground(newColor);
        jPanelInicio.setBackground(newColor);
        jTextAreaDistribucion.setBackground(newColor);
        jTableRAM.setBackground(newColor);
        jTableRAMSIMPLE.setBackground(newColor);
        jScrollPane3.setBackground(newColor);
        jListProcesos.setBackground(newColor);
        jLabelusername1.setBackground(newColor);
        jLabelipdireccion1.setBackground(newColor);
        jLabelInfoPrinciSist.setBackground(newColor);
        jLabelmemtotal1.setBackground(newColor);
        jLabelmemtotal1.setBackground(newColor);
        jLabelmemcache1.setBackground(newColor);
        jLabelmodcpu1.setBackground(newColor);
        jLabelvelocpu1.setBackground(newColor);
        }
        colores.getSelectionModel().addChangeListener(
            new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    Color newColor = colores.getBackground();
                    jTextAreaInfoGeneral.setBackground(newColor);

                }
            });
    }//GEN-LAST:event_jMenuItemMasColores2ActionPerformed

    private void jMenuItemNegroTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNegroTextActionPerformed
        // TODO add your handling code here:
        
        jTextAreaInfoGeneral.setForeground(Color.decode("000000"));
        jTableCPUuno.setForeground(Color.decode("000000"));
        jTextAreaDispositivos.setForeground(Color.decode("000000"));
        jTableCPUdos.setForeground(Color.decode("000000"));
         jPanel2.setForeground(Color.decode("000000"));
        jTextAreaDistribucion.setForeground(Color.decode("000000"));
        jTextAreaParticion.setForeground(Color.decode("000000"));
        jTableRAM.setForeground(Color.decode("000000"));
        jTableRAMSIMPLE.setForeground(Color.decode("000000"));
        jScrollPane3.setForeground(Color.decode("000000"));
        jListProcesos.setForeground(Color.decode("000000"));
        jLabelusername1.setForeground(Color.decode("000000"));
        jLabelipdireccion1.setForeground(Color.decode("000000"));
        jLabelInfoPrinciSist.setForeground(Color.decode("000000"));
        jLabelmemtotal1.setForeground(Color.decode("000000"));
        jLabelmemtotal1.setForeground(Color.decode("000000"));
        jLabelmemcache1.setForeground(Color.decode("000000"));
        jLabelmodcpu1.setForeground(Color.decode("000000"));
        jLabelvelocpu1.setForeground(Color.decode("000000"));
    }//GEN-LAST:event_jMenuItemNegroTextActionPerformed

    private void jMenuItemRojo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRojo1ActionPerformed
        // TODO add your handling code here:
        Color rojo = new Color(0xff0000);
        jTextAreaInfoGeneral.setForeground(rojo);
        jTextAreaDispositivos.setForeground(rojo);
        jLabelvelocpu1.setForeground(rojo);
        jPanel2.setForeground(rojo);
        jTextAreaDistribucion.setForeground(rojo);
        jTableCPUuno.setForeground(rojo);
        jTableCPUdos.setForeground(rojo);
        jTextAreaParticion.setForeground(rojo);
        jTableRAM.setForeground(rojo);
        jTableRAMSIMPLE.setForeground(rojo);
        jScrollPane3.setForeground(rojo);
        jListProcesos.setForeground(rojo);
        jLabelusername1.setForeground(rojo);
        jLabelipdireccion1.setForeground(rojo);
        jLabelInfoPrinciSist.setForeground(rojo);
        jLabelmemtotal1.setForeground(rojo);
        jLabelmemtotal1.setForeground(rojo);
        jLabelmemcache1.setForeground(rojo);
        jLabelmodcpu1.setForeground(rojo);
    }//GEN-LAST:event_jMenuItemRojo1ActionPerformed

    private void jMenuItemAqua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAqua1ActionPerformed
        // TODO add your handling code here:
        Color aqua = new Color(0, 255, 255);
          jTextAreaInfoGeneral.setForeground(aqua);
        jTableCPUuno.setForeground(aqua);
        jTextAreaDispositivos.setForeground(aqua);
        jPanel2.setForeground(aqua);
        jTextAreaDistribucion.setForeground(aqua);
        jTableCPUdos.setForeground(aqua);
        jTextAreaParticion.setForeground(aqua);
        jTableRAM.setForeground(aqua);
        jTableRAMSIMPLE.setForeground(aqua);
        jScrollPane3.setForeground(aqua);
        jLabelvelocpu1.setForeground(aqua);
        jListProcesos.setForeground(aqua);
        jLabelusername1.setForeground(aqua);
        jLabelipdireccion1.setForeground(aqua);
        jLabelInfoPrinciSist.setForeground(aqua);
        jLabelmemtotal1.setForeground(aqua);
        jLabelmemtotal1.setForeground(aqua);
        jLabelmemcache1.setForeground(aqua);
        jLabelmodcpu1.setForeground(aqua);
    }//GEN-LAST:event_jMenuItemAqua1ActionPerformed

    private void jMenuItemNaranja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNaranja1ActionPerformed
        // TODO add your handling code here:
        Color naranja = new Color(0xffc000);
           jTextAreaInfoGeneral.setForeground(naranja);
        jTableCPUuno.setForeground(naranja);
        jTableCPUdos.setForeground(naranja);
        jTextAreaDispositivos.setForeground(naranja);
        jTextAreaParticion.setForeground(naranja);
        jPanel2.setForeground(naranja);
        jTextAreaDistribucion.setForeground(naranja);
        jTableRAM.setForeground(naranja);
        jTableRAMSIMPLE.setForeground(naranja);
        jScrollPane3.setForeground(naranja);
        jListProcesos.setForeground(naranja);
        jLabelvelocpu1.setForeground(naranja);
        jLabelusername1.setForeground(naranja);
        jLabelipdireccion1.setForeground(naranja);
        jLabelInfoPrinciSist.setForeground(naranja);
        jLabelmemtotal1.setForeground(naranja);
        jLabelmemtotal1.setForeground(naranja);
        jLabelmemcache1.setForeground(naranja);
        jLabelmodcpu1.setForeground(naranja);
    }//GEN-LAST:event_jMenuItemNaranja1ActionPerformed

    private void jMenuItemAmarillo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAmarillo1ActionPerformed
        // TODO add your handling code here:
        jTextAreaInfoGeneral.setForeground(Color.YELLOW);
        jTableCPUuno.setForeground(Color.YELLOW);
        jTableCPUdos.setForeground(Color.YELLOW);
        jPanel2.setForeground(Color.YELLOW);
        jTextAreaDispositivos.setForeground(Color.YELLOW);
        jTextAreaDistribucion.setForeground(Color.YELLOW);
        jTextAreaParticion.setForeground(Color.YELLOW);
        jTableRAM.setForeground(Color.YELLOW);
        jTableRAMSIMPLE.setForeground(Color.YELLOW);
        jScrollPane3.setForeground(Color.YELLOW);
        jListProcesos.setForeground(Color.YELLOW);
        jLabelvelocpu1.setForeground(Color.YELLOW);
        jLabelusername1.setForeground(Color.YELLOW);
        jLabelipdireccion1.setForeground(Color.YELLOW);
        jLabelInfoPrinciSist.setForeground(Color.YELLOW);
        jLabelmemtotal1.setForeground(Color.YELLOW);
        jLabelmemtotal1.setForeground(Color.YELLOW);
        jLabelmemcache1.setForeground(Color.YELLOW);
        jLabelmodcpu1.setForeground(Color.YELLOW);
    }//GEN-LAST:event_jMenuItemAmarillo1ActionPerformed

    private void jMenuItemCafeClaroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCafeClaroActionPerformed
        // TODO add your handling code here:
        Color cafeClaro = new Color(205, 102, 29);
           jTextAreaInfoGeneral.setForeground(cafeClaro);
        jTableCPUuno.setForeground(cafeClaro);
        jTableCPUdos.setForeground(cafeClaro);
        jTextAreaDispositivos.setForeground(cafeClaro);
        jPanel2.setForeground(cafeClaro);
        jTextAreaDistribucion.setForeground(cafeClaro);
        jTextAreaParticion.setForeground(cafeClaro);
        jTableRAM.setForeground(cafeClaro);
        jTableRAMSIMPLE.setForeground(cafeClaro);
        jScrollPane3.setForeground(cafeClaro);
        jListProcesos.setForeground(cafeClaro);
        jLabelusername1.setForeground(cafeClaro);
        jLabelvelocpu1.setForeground(cafeClaro);
        jLabelipdireccion1.setForeground(cafeClaro);
        jLabelInfoPrinciSist.setForeground(cafeClaro);
        jLabelmemtotal1.setForeground(cafeClaro);
        jLabelmemtotal1.setForeground(cafeClaro);
        jLabelmemcache1.setForeground(cafeClaro);
        jLabelmodcpu1.setForeground(cafeClaro);
    }//GEN-LAST:event_jMenuItemCafeClaroActionPerformed

    private void jMenuItemMarronRosaseoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMarronRosaseoActionPerformed
        // TODO add your handling code here:
        Color marronRosaseo = new Color(255, 193, 193);
        jTextAreaInfoGeneral.setForeground(marronRosaseo);
        jPanel2.setForeground(marronRosaseo);
        jTextAreaDistribucion.setForeground(marronRosaseo);
        jTextAreaDispositivos.setForeground(marronRosaseo);
        jTableCPUuno.setForeground(marronRosaseo);
        jTableCPUdos.setForeground(marronRosaseo);
        jTextAreaParticion.setForeground(marronRosaseo);
        jTableRAM.setForeground(marronRosaseo);
        jTableRAMSIMPLE.setForeground(marronRosaseo);
        jLabelvelocpu1.setForeground(marronRosaseo);
        jScrollPane3.setForeground(marronRosaseo);
        jListProcesos.setForeground(marronRosaseo);
        jLabelusername1.setForeground(marronRosaseo);
        jLabelipdireccion1.setForeground(marronRosaseo);
        jLabelInfoPrinciSist.setForeground(marronRosaseo);
        jLabelmemtotal1.setForeground(marronRosaseo);
        jLabelmemtotal1.setForeground(marronRosaseo);
        jLabelmemcache1.setForeground(marronRosaseo);
        jLabelmodcpu1.setForeground(marronRosaseo);
    }//GEN-LAST:event_jMenuItemMarronRosaseoActionPerformed

    private void jMenuItemVerde1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVerde1ActionPerformed
        // TODO add your handling code here:
        Color verde = new Color(0x00b050);
        jTextAreaInfoGeneral.setForeground(verde);
        jTableCPUuno.setForeground(verde);
        jTextAreaDispositivos.setForeground(verde);
        jPanel2.setForeground(verde);
        jTextAreaDistribucion.setForeground(verde);
        jTableCPUdos.setForeground(verde);
        jTextAreaParticion.setForeground(verde);
        jTableRAM.setForeground(verde);
        jLabelvelocpu1.setForeground(verde);
        jTableRAMSIMPLE.setForeground(verde);
        jScrollPane3.setForeground(verde);
        jListProcesos.setForeground(verde);
        jLabelusername1.setForeground(verde);
        jLabelipdireccion1.setForeground(verde);
        jLabelInfoPrinciSist.setForeground(verde);
        jLabelmemtotal1.setForeground(verde);
        jLabelmemtotal1.setForeground(verde);
        jLabelmemcache1.setForeground(verde);
        jLabelmodcpu1.setForeground(verde);
    }//GEN-LAST:event_jMenuItemVerde1ActionPerformed

    private void jMenuItemVerdeClaro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVerdeClaro1ActionPerformed
        // TODO add your handling code here:
        Color verde_c = new Color(0xc92d050);
           jTextAreaInfoGeneral.setForeground(verde_c);
        jTableCPUuno.setForeground(verde_c);
        jTableCPUdos.setForeground(verde_c);
        jTextAreaDispositivos.setForeground(verde_c);
        jPanel2.setForeground(verde_c);
        jTextAreaDistribucion.setForeground(verde_c);
        jTextAreaParticion.setForeground(verde_c);
        jTableRAM.setForeground(verde_c);
        jTableRAMSIMPLE.setForeground(verde_c);
        jLabelvelocpu1.setForeground(verde_c);
        jScrollPane3.setForeground(verde_c);
        jListProcesos.setForeground(verde_c);
        jLabelusername1.setForeground(verde_c);
        jLabelipdireccion1.setForeground(verde_c);
        jLabelInfoPrinciSist.setForeground(verde_c);
        jLabelmemtotal1.setForeground(verde_c);
        jLabelmemtotal1.setForeground(verde_c);
        jLabelmemcache1.setForeground(verde_c);
        jLabelmodcpu1.setForeground(verde_c);
    }//GEN-LAST:event_jMenuItemVerdeClaro1ActionPerformed

    private void jMenuItemSalmonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalmonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Color salmon = new Color(255, 160, 122);
        jTextAreaInfoGeneral.setForeground(salmon);
        jTextAreaInfoGeneral.setForeground(salmon);
        jPanel2.setForeground(salmon);
        jTextAreaDispositivos.setForeground(salmon);
        jTextAreaDistribucion.setForeground(salmon);
        jTableCPUuno.setForeground(salmon);
        jTableCPUdos.setForeground(salmon);
        jTextAreaParticion.setForeground(salmon);
        jTableRAM.setForeground(salmon);
        jTableRAMSIMPLE.setForeground(salmon);
        jLabelvelocpu1.setForeground(salmon);
        jScrollPane3.setForeground(salmon);
        jListProcesos.setForeground(salmon);
        jLabelusername1.setForeground(salmon);
        jLabelipdireccion1.setForeground(salmon);
        jLabelInfoPrinciSist.setForeground(salmon);
        jLabelmemtotal1.setForeground(salmon);
        jLabelmemtotal1.setForeground(salmon);
        jLabelmemcache1.setForeground(salmon);
        jLabelmodcpu1.setForeground(salmon);
    }//GEN-LAST:event_jMenuItemSalmonActionPerformed

    private void jMenuItemMasColores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMasColores1ActionPerformed
        // TODO add your handling code here:
        Color newColor = JColorChooser.showDialog(this, "Choose Background Color", jTextAreaInfoGeneral.getForeground());

        if (newColor != null) {
        jTextAreaInfoGeneral.setForeground(newColor);
        jTextAreaInfoGeneral.setForeground(newColor);
        jPanel2.setForeground(newColor);
        jTextAreaDispositivos.setForeground(newColor);
        jTextAreaDistribucion.setForeground(newColor);
        jTextAreaInfoGeneral.setForeground(newColor);
        jTableCPUuno.setForeground(newColor);
        jLabelvelocpu1.setForeground(newColor);
        jTableCPUdos.setForeground(newColor);
        jTextAreaParticion.setForeground(newColor);
        jTableRAM.setForeground(newColor);
        jTableRAMSIMPLE.setForeground(newColor);
        jScrollPane3.setForeground(newColor);
        jListProcesos.setForeground(newColor);
        jLabelusername1.setForeground(newColor);
        jLabelipdireccion1.setForeground(newColor);
        jLabelInfoPrinciSist.setForeground(newColor);
        jLabelmemtotal1.setForeground(newColor);
        jLabelmemtotal1.setForeground(newColor);
        jLabelmemcache1.setForeground(newColor);
        jLabelmodcpu1.setForeground(newColor);
        }
        colores.getSelectionModel().addChangeListener(
            new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    Color newColor = colores.getForeground();
                    jTextAreaInfoGeneral.setForeground(newColor);

                }
            });
    }//GEN-LAST:event_jMenuItemMasColores1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        
         nombrefuente  = Font.MONOSPACED;
        fuente = new Font(nombrefuente, estilo, tamañofuente);

      
             jTextAreaInfoGeneral.setFont(fuente);
        jTextAreaInfoGeneral.setFont(fuente);
        jPanel2.setFont(fuente);
        jTextAreaDispositivos.setFont(fuente);
        jTextAreaDistribucion.setFont(fuente);
        jTextAreaInfoGeneral.setFont(fuente);
        jTableCPUuno.setFont(fuente);
        jLabelvelocpu1.setFont(fuente);
        jTableCPUdos.setFont(fuente);
        jTextAreaParticion.setFont(fuente);
        jTableRAM.setFont(fuente);
        jTableRAMSIMPLE.setFont(fuente);
        jScrollPane3.setFont(fuente);
        jListProcesos.setFont(fuente);
        jLabelusername1.setFont(fuente);
        jLabelipdireccion1.setFont(fuente);
        jLabelInfoPrinciSist.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemcache1.setFont(fuente);
        jLabelmodcpu1.setFont(fuente);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        
        nombrefuente  = Font.SERIF;
      fuente = new Font(nombrefuente, estilo, tamañofuente);
     
      
             jTextAreaInfoGeneral.setFont(fuente);
        jTextAreaInfoGeneral.setFont(fuente);
        jPanel2.setFont(fuente);
        jTextAreaDispositivos.setFont(fuente);
        jTextAreaDistribucion.setFont(fuente);
        jTextAreaInfoGeneral.setFont(fuente);
        jTableCPUuno.setFont(fuente);
        jLabelvelocpu1.setFont(fuente);
        jTableCPUdos.setFont(fuente);
        jTextAreaParticion.setFont(fuente);
        jTableRAM.setFont(fuente);
        jTableRAMSIMPLE.setFont(fuente);
        jScrollPane3.setFont(fuente);
        jListProcesos.setFont(fuente);
        jLabelusername1.setFont(fuente);
        jLabelipdireccion1.setFont(fuente);
        jLabelInfoPrinciSist.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemcache1.setFont(fuente);
        jLabelmodcpu1.setFont(fuente);
     
      
     
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        
         nombrefuente  = Font.DIALOG_INPUT;
        fuente = new Font(nombrefuente, estilo, tamañofuente);
     
        
             jTextAreaInfoGeneral.setFont(fuente);
        jTextAreaInfoGeneral.setFont(fuente);
        jPanel2.setFont(fuente);
        jTextAreaDispositivos.setFont(fuente);
        jTextAreaDistribucion.setFont(fuente);
        jTextAreaInfoGeneral.setFont(fuente);
        jTableCPUuno.setFont(fuente);
        jLabelvelocpu1.setFont(fuente);
        jTableCPUdos.setFont(fuente);
        jTextAreaParticion.setFont(fuente);
        jTableRAM.setFont(fuente);
        jTableRAMSIMPLE.setFont(fuente);
        jScrollPane3.setFont(fuente);
        jListProcesos.setFont(fuente);
        jLabelusername1.setFont(fuente);
        jLabelipdireccion1.setFont(fuente);
        jLabelInfoPrinciSist.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemcache1.setFont(fuente);
        jLabelmodcpu1.setFont(fuente);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        
               nombrefuente  = Font.SANS_SERIF;
        fuente = new Font(nombrefuente, estilo, tamañofuente);
      
        
             jTextAreaInfoGeneral.setFont(fuente);
        jTextAreaInfoGeneral.setFont(fuente);
        jPanel2.setFont(fuente);
        jTextAreaDispositivos.setFont(fuente);
        jTextAreaDistribucion.setFont(fuente);
        jTextAreaInfoGeneral.setFont(fuente);
        jTableCPUuno.setFont(fuente);
        jLabelvelocpu1.setFont(fuente);
        jTableCPUdos.setFont(fuente);
        jTextAreaParticion.setFont(fuente);
        jTableRAM.setFont(fuente);
        jTableRAMSIMPLE.setFont(fuente);
        jScrollPane3.setFont(fuente);
        jListProcesos.setFont(fuente);
        jLabelusername1.setFont(fuente);
        jLabelipdireccion1.setFont(fuente);
        jLabelInfoPrinciSist.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemtotal1.setFont(fuente);
        jLabelmemcache1.setFont(fuente);
        jLabelmodcpu1.setFont(fuente);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            //Guardx.print(jTextAreaRAM.getText());
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBateria;
    private javax.swing.JButton jButtonFinalizarProcesos;
    private javax.swing.JButton jButtonRed;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonSockets;
    private javax.swing.JLabel jLabelInfoPrinciSist;
    private javax.swing.JLabel jLabelLogoNetbeans;
    private javax.swing.JLabel jLabelLogoTec;
    private javax.swing.JLabel jLabelipdireccion1;
    private javax.swing.JLabel jLabelmemcache1;
    private javax.swing.JLabel jLabelmemtotal1;
    private javax.swing.JLabel jLabelmodcpu1;
    private javax.swing.JLabel jLabelusername1;
    private javax.swing.JLabel jLabelvelocpu1;
    private javax.swing.JList jListProcesos;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuColorTexto;
    private javax.swing.JMenu jMenuFondo;
    private javax.swing.JMenu jMenuFormato;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItemAmarillo1;
    private javax.swing.JMenuItem jMenuItemAqua;
    private javax.swing.JMenuItem jMenuItemAqua1;
    private javax.swing.JMenuItem jMenuItemAzulClaro1;
    private javax.swing.JMenuItem jMenuItemBeige;
    private javax.swing.JMenuItem jMenuItemCDE_Motif;
    private javax.swing.JMenuItem jMenuItemCafeClaro;
    private javax.swing.JMenuItem jMenuItemCardo;
    private javax.swing.JMenuItem jMenuItemCerrar_Aplicacion;
    private javax.swing.JMenuItem jMenuItemGTK;
    private javax.swing.JMenuItem jMenuItemGUuardar;
    private javax.swing.JMenuItem jMenuItemGris;
    private javax.swing.JMenuItem jMenuItemMarronRosaseo;
    private javax.swing.JMenuItem jMenuItemMasColores1;
    private javax.swing.JMenuItem jMenuItemMasColores2;
    private javax.swing.JMenuItem jMenuItemMetal;
    private javax.swing.JMenuItem jMenuItemMocasin;
    private javax.swing.JMenuItem jMenuItemNaranja1;
    private javax.swing.JMenuItem jMenuItemNegro1;
    private javax.swing.JMenuItem jMenuItemNegroText;
    private javax.swing.JMenuItem jMenuItemNimbus;
    private javax.swing.JMenuItem jMenuItemRojo1;
    private javax.swing.JMenuItem jMenuItemRojoObscuro1;
    private javax.swing.JMenuItem jMenuItemSalmon;
    private javax.swing.JMenuItem jMenuItemTam13;
    private javax.swing.JMenuItem jMenuItemTam14;
    private javax.swing.JMenuItem jMenuItemTam15;
    private javax.swing.JMenuItem jMenuItemTam16;
    private javax.swing.JMenuItem jMenuItemTam18;
    private javax.swing.JMenuItem jMenuItemVerde1;
    private javax.swing.JMenuItem jMenuItemVerdeClaro1;
    private javax.swing.JMenuItem jMenuItemVerdeOliva;
    private javax.swing.JMenu jMenuOpciones;
    private javax.swing.JMenu jMenuPersonalizar;
    private javax.swing.JMenu jMenuTamanio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCPU2;
    private javax.swing.JPanel jPanelInfoSis;
    private javax.swing.JPanel jPanelInicio;
    private javax.swing.JPanel jPanelParticiones;
    private javax.swing.JPanel jPanelProcesos;
    private javax.swing.JPanel jPanelRAM;
    private javax.swing.JTabbedPane jRadioButtonVS;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableCPUdos;
    private javax.swing.JTable jTableCPUuno;
    private javax.swing.JTable jTableRAM;
    private javax.swing.JTable jTableRAMSIMPLE;
    private javax.swing.JTextArea jTextAreaDispositivos;
    private javax.swing.JTextArea jTextAreaDistribucion;
    private javax.swing.JTextArea jTextAreaInfoGeneral;
    private javax.swing.JTextArea jTextAreaParticion;
    private javax.swing.JTextField jTextFieldNumeroProceso;
    // End of variables declaration//GEN-END:variables
}
