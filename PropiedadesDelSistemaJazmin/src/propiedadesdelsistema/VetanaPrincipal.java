/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package propiedadesdelsistema;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rider
 */
public class VetanaPrincipal extends javax.swing.JFrame {

    LlenarListas listas;
    String goverorselected;
    private static String lookAdFeel = UIManager.getSystemLookAndFeelClassName();

    /**
     * Creates new form VetanaPrincipal
     */
    public VetanaPrincipal() {
        setLookAndFeel(lookAdFeel);
        initComponents();
        setLocationRelativeTo(this);
        listas = new LlenarListas();
        setEditablejText();
        informacionGeneral();
        mostrarmeminfosimple();
        mostrarcpuinfosimple();
        informacionparticiones();
    }

    private void setEditablejText() {
        jTextAreaInfoGeneral.setEditable(false);
        jTextAreaRAM.setEditable(false);
        jTextAreaCPU.setEditable(false);
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

    private void informacionparticiones() {
        ArrayList<String> listparticiones = listas.getListparticiones();
        for (int i = 0; i < listparticiones.size(); i++) {
            jTextAreaParticion.append(listparticiones.get(i) + '\n');
        }
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

    private void mostrarcpuinfosimple() {
        ArrayList<String> listcpuinfo = listas.getListcpuinfo();
        jTextAreaCPU.append(listcpuinfo.get(1) + '\n');
        jTextAreaCPU.append(listcpuinfo.get(4) + '\n');
        jTextAreaCPU.append(listcpuinfo.get(7) + '\n');
        jTextAreaCPU.append(listcpuinfo.get(8) + '\n');
        jTextAreaCPU.append(listcpuinfo.get(23) + '\n');
    }

    private void mostrarcpuinfoavanzada() {
        ArrayList<String> listcpuinfo = listas.getListcpuinfo();
        ArrayList<String> listcpufreq = listas.getListcpufreq();
        for (int i = 0; i < listcpuinfo.size() - 1; i++) {
            jTextAreaCPU.append(listcpuinfo.get(i) + '\n');
        }
        jTextAreaCPU.append("Max Frequencie" + '\t' + ':' + listcpufreq.get(0) + '\n');
        jTextAreaCPU.append("Min Frequencie" + '\t' + ':' + listcpufreq.get(1) + '\n');
        jTextAreaCPU.append("Transition Latency" + '\t' + ':' + listcpufreq.get(2) + '\n');
        jTextAreaCPU.append("Governor" + '\t' + ':' + listcpufreq.get(3) + '\n');
        jTextAreaCPU.append("Available Governors" + '\t' + ':' + listcpufreq.get(4) + '\n');
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelInfoSis = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInfoGeneral = new javax.swing.JTextArea();
        jPanelRAM = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaRAM = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jRadioButtonSImpleRAM = new javax.swing.JRadioButton();
        jRadioButtonAvanzadaRAM = new javax.swing.JRadioButton();
        jPanelCPU = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jRadioButtonSimpleCPU = new javax.swing.JRadioButton();
        jRadioButtonAvanzadaCPU = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaCPU = new javax.swing.JTextArea();
        jPanelParticiones = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaParticion = new javax.swing.JTextArea();
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

        jTextAreaInfoGeneral.setColumns(20);
        jTextAreaInfoGeneral.setRows(5);
        jScrollPane1.setViewportView(jTextAreaInfoGeneral);

        javax.swing.GroupLayout jPanelInfoSisLayout = new javax.swing.GroupLayout(jPanelInfoSis);
        jPanelInfoSis.setLayout(jPanelInfoSisLayout);
        jPanelInfoSisLayout.setHorizontalGroup(
            jPanelInfoSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
        );
        jPanelInfoSisLayout.setVerticalGroup(
            jPanelInfoSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Informacion General", jPanelInfoSis);

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
                .addGap(0, 445, Short.MAX_VALUE))
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
        jPanelRAMLayout.setVerticalGroup(
            jPanelRAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRAMLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("RAM", jPanelRAM);

        jRadioButtonSimpleCPU.setSelected(true);
        jRadioButtonSimpleCPU.setText("Vista Simple");
        jRadioButtonSimpleCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSimpleCPUActionPerformed(evt);
            }
        });

        jRadioButtonAvanzadaCPU.setText("Vista Avanzada");
        jRadioButtonAvanzadaCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAvanzadaCPUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jRadioButtonSimpleCPU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonAvanzadaCPU)
                .addGap(0, 445, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButtonSimpleCPU)
                .addComponent(jRadioButtonAvanzadaCPU))
        );

        jTextAreaCPU.setColumns(20);
        jTextAreaCPU.setRows(5);
        jScrollPane3.setViewportView(jTextAreaCPU);

        javax.swing.GroupLayout jPanelCPULayout = new javax.swing.GroupLayout(jPanelCPU);
        jPanelCPU.setLayout(jPanelCPULayout);
        jPanelCPULayout.setHorizontalGroup(
            jPanelCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
        jPanelCPULayout.setVerticalGroup(
            jPanelCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCPULayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CPU", jPanelCPU);

        jTextAreaParticion.setColumns(20);
        jTextAreaParticion.setRows(5);
        jScrollPane4.setViewportView(jTextAreaParticion);

        javax.swing.GroupLayout jPanelParticionesLayout = new javax.swing.GroupLayout(jPanelParticiones);
        jPanelParticiones.setLayout(jPanelParticionesLayout);
        jPanelParticionesLayout.setHorizontalGroup(
            jPanelParticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
        );
        jPanelParticionesLayout.setVerticalGroup(
            jPanelParticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Particiones", jPanelParticiones);

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
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonAvanzadaRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAvanzadaRAMActionPerformed
        // TODO add your handling code here:
        jTextAreaRAM.setText("");
        mostrarmeminfoavanzada();
        jRadioButtonSImpleRAM.setSelected(false);

    }//GEN-LAST:event_jRadioButtonAvanzadaRAMActionPerformed

    private void jRadioButtonSimpleCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSimpleCPUActionPerformed
        // TODO add your handling code here:
        jTextAreaCPU.setText("");
        mostrarcpuinfosimple();
        jRadioButtonAvanzadaCPU.setSelected(false);

    }//GEN-LAST:event_jRadioButtonSimpleCPUActionPerformed

    private void jRadioButtonAvanzadaCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAvanzadaCPUActionPerformed
        // TODO add your handling code here:
        jTextAreaCPU.setText("");
        mostrarcpuinfoavanzada();
        jRadioButtonSimpleCPU.setSelected(false);

    }//GEN-LAST:event_jRadioButtonAvanzadaCPUActionPerformed

    private void jRadioButtonSImpleRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSImpleRAMActionPerformed
        // TODO add your handling code here:
        jTextAreaRAM.setText("");
        mostrarmeminfosimple();
        jRadioButtonAvanzadaRAM.setSelected(false);

    }//GEN-LAST:event_jRadioButtonSImpleRAMActionPerformed

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

    private void guardar() {
        String title = jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());
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
            Guardx.print(jTextAreaCPU.getText());
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelCPU;
    private javax.swing.JPanel jPanelInfoSis;
    private javax.swing.JPanel jPanelParticiones;
    private javax.swing.JPanel jPanelRAM;
    private javax.swing.JRadioButton jRadioButtonAvanzadaCPU;
    private javax.swing.JRadioButton jRadioButtonAvanzadaRAM;
    private javax.swing.JRadioButton jRadioButtonSImpleRAM;
    private javax.swing.JRadioButton jRadioButtonSimpleCPU;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaCPU;
    private javax.swing.JTextArea jTextAreaInfoGeneral;
    private javax.swing.JTextArea jTextAreaParticion;
    private javax.swing.JTextArea jTextAreaRAM;
    // End of variables declaration//GEN-END:variables
}
