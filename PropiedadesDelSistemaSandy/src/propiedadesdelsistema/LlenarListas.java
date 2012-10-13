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
    private ArrayList<String> listmeminfo;
    private ArrayList<String> listcpuinfo;
    private ArrayList<String> listcpufreq;

    public LlenarListas() {
        comandoconsolameminfo("cat /proc/meminfo");
    }
    
    
    
    private void comandoconsolameminfo(String pcmd){
        listmeminfo = new ArrayList<String>();
 try{
    Process cmd = Runtime.getRuntime().exec(pcmd);
    cmd.waitFor();
    BufferedReader buf=new BufferedReader(new InputStreamReader(cmd.getInputStream()));
    String linea="";
    while ((linea=buf.readLine())!=null){
        listmeminfo.add(linea.toString());
      }
    }
 catch(Exception e){
    javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion",javax.swing.JOptionPane.PLAIN_MESSAGE );
 }
}

    public ArrayList<String> getListmeminfo() {
        return listmeminfo;
    }

    
}
