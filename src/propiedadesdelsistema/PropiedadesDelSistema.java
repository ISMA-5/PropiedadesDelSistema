/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package propiedadesdelsistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author rider
 */
public class PropiedadesDelSistema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        new PropiedadesDelSistema().comandoconsola("cat /proc/meminfo");

    }
    
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
