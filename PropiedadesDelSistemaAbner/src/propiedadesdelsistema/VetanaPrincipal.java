/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package propiedadesdelsistema;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author kenia
 */
public class VetanaPrincipal extends javax.swing.JFrame {

    LlenarListas listas;
    Font font = new Font(Font.DIALOG, Font.BOLD, 16);
    String goverorselected;
    int times;

    /**
     * Creates new form VetanaPrincipal
     */
    public VetanaPrincipal() {
        setLookAndFeel();
        initComponents();
        listas = new LlenarListas();
        setEditablejText();
        informacionGeneral();
        mostrarmeminfosimple();
        mostrarcpuinfosimple();
        informacionparticiones();
        times=0;
        obtenerListaGoverors();
        //System.out.println(listas.comandoconsolagenerico("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_available_governors"));
    }

    private void obtenerListaGoverors(){
        String governors = listas.comandoconsolagenerico("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_available_governors");
        separarGovernors(governors);    
    }
    
    private void separarGovernors(String governors){
        ArrayList<String> listgoveros = new ArrayList<>();
        String governor="";
        char c=' ';
        for(int i=0;i<governors.length();i++){
            c=governors.charAt(i);
            if(!Character.isSpaceChar(c)){
                governor+=c;
            }else{
                listgoveros.add(governor);
                governor="";
            }
        }
        llenarComboBox(listgoveros);
    }
    
    
    private void llenarComboBox(ArrayList<String> listgovernors){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
            for(int i=0;i<listgovernors.size();i++){
                model.addElement(listgovernors.get(i));
            }
        jComboBoxGovernors.setModel(model);
        goverorselected=listas.comandoconsolagenerico("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor");
    }
    private void setEditablejText() {
        jTextAreaInfoGeneral.setEditable(false);
        jTextAreaRAM.setEditable(false);
        jTextAreaCPU.setEditable(false);
        jTextAreaParticion.setEditable(false);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

        } catch (Exception e) {
            System.out.println(e);
        }
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
        jTextAreaInfoGeneral.append("Nombre de Usuario" + ':' + '\t' + listas.comandoconsolagenerico("hostname") + '\n');
        jTextAreaInfoGeneral.append("Dirección de IP" + ':' + '\t' + listas.comandoconsolagenerico("hostname -i") + '\n');
        jTextAreaInfoGeneral.append("Versión de Kernel" + ':' + '\t' + listas.comandoconsolagenerico("uname -a") + '\n');
        jTextAreaInfoGeneral.append("Memoria Total" + ':' + '\t' + listmeminfo.get(0) + '\n');
        jTextAreaInfoGeneral.append("Memoria Cache" + ':' + '\t' + listmeminfo.get(3) + '\n');
        jTextAreaInfoGeneral.append("Modelo del CPU" + ':' + '\t' + listcpuinfo.get(4) + '\n');
        jTextAreaInfoGeneral.append("Velocidad del CPU" + ':' + '\t' + listcpuinfo.get(7) + '\n');
    }

    private void mostrarmeminfosimple() {
        ArrayList<String> listmeminfo = listas.getListmeminfo();
        jTextAreaRAM.append(listmeminfo.get(0) + '\n');
        jTextAreaRAM.append(listmeminfo.get(1) + '\n');
        jTextAreaRAM.append(listmeminfo.get(2) + '\n');
        jTextAreaRAM.append(listmeminfo.get(3) + '\n');
        jTextAreaRAM.append(listmeminfo.get(4) + '\n');
        jTextAreaRAM.append(listmeminfo.get(13) + '\n');
        jTextAreaRAM.append(listmeminfo.get(14) + '\n');
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
        jComboBoxGovernors = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaCPU = new javax.swing.JTextArea();
        jPanelParticiones = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaParticion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaInfoGeneral.setColumns(20);
        jTextAreaInfoGeneral.setRows(5);
        jScrollPane1.setViewportView(jTextAreaInfoGeneral);

        javax.swing.GroupLayout jPanelInfoSisLayout = new javax.swing.GroupLayout(jPanelInfoSis);
        jPanelInfoSis.setLayout(jPanelInfoSisLayout);
        jPanelInfoSisLayout.setHorizontalGroup(
            jPanelInfoSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
        jPanelInfoSisLayout.setVerticalGroup(
            jPanelInfoSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jComboBoxGovernors.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxGovernors.setEnabled(false);
        jComboBoxGovernors.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxGovernorsItemStateChanged(evt);
            }
        });
        jComboBoxGovernors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGovernorsActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Seleccionar Governador");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxGovernors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButtonSimpleCPU)
                .addComponent(jRadioButtonAvanzadaCPU)
                .addComponent(jComboBoxGovernors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCheckBox1))
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CPU", jPanelCPU);

        jTextAreaParticion.setColumns(20);
        jTextAreaParticion.setRows(5);
        jScrollPane4.setViewportView(jTextAreaParticion);

        javax.swing.GroupLayout jPanelParticionesLayout = new javax.swing.GroupLayout(jPanelParticiones);
        jPanelParticiones.setLayout(jPanelParticionesLayout);
        jPanelParticionesLayout.setHorizontalGroup(
            jPanelParticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelParticionesLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelParticionesLayout.setVerticalGroup(
            jPanelParticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Particiones", jPanelParticiones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()){
            jComboBoxGovernors.setEnabled(true);
        }
        else{
            jComboBoxGovernors.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBoxGovernorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxGovernorsItemStateChanged
        // TODO add your handling code here:
        String newGovernor = (String) jComboBoxGovernors.getSelectedItem();
        if(newGovernor!=goverorselected){
            int selecion=JOptionPane.showConfirmDialog(this, "Esta Seguro de Cambiar El Governador", null, JOptionPane.OK_CANCEL_OPTION);
            if(selecion==0){
                goverorselected=newGovernor;
                String comandoconsolagenerico = listas.comandoconsolagenerico("echo conservative > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor");
                System.out.println(comandoconsolagenerico);
            }
        }
    }//GEN-LAST:event_jComboBoxGovernorsItemStateChanged

    private void jComboBoxGovernorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGovernorsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxGovernorsActionPerformed

    private boolean ispar(int i){
        if(i%2==0){
            return true;
        }
        return false;
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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBoxGovernors;
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
