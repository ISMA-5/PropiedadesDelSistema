/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package propiedadesdelsistema;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author rubi
 */
public class LlenarListas {
    private ArrayList<Integer> listmeminfo;
    private ArrayList<Integer> listcpuinfo;
    private ArrayList<Integer> listcpufreq;
    
    
    private void comandoconsola(String pcmd){
 try{
    Process cmd = Runtime.getRuntime().exec(pcmd);
    cmd.waitFor();
    BufferedReader buf=new BufferedReader(new InputStreamReader(cmd.getInputStream()));
    String linea="";
    while ((linea=buf.readLine())!=null){
      //Aqui se añadira codigo
      }
    }
 catch(Exception e){
    javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion",javax.swing.JOptionPane.PLAIN_MESSAGE );
 }
}
    
}
