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
    ArrayList<String> listmeminfo;
    ArrayList<String> listcpuinfo;
    ArrayList<String> listcpufreq;
    
    
    private void avisador(String v){
        System.out.println(v);
}
    
    private void comandoconsola(String pcmd){
 try{
    Process cmd = Runtime.getRuntime().exec(pcmd);
    cmd.waitFor();
    BufferedReader buf=new BufferedReader(new InputStreamReader(cmd.getInputStream()));
    String linea="";
    while ((linea=buf.readLine())!=null){
      //System.out.println(linea);
      avisador(linea.toString());
      } //fin while
    } //fin try
 catch(Exception e){
    javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion",javax.swing.JOptionPane.PLAIN_MESSAGE );
 }
}
}
    
    
    
    
    
    
    

