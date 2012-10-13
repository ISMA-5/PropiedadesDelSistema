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
 * @author kenia
 */
public class LlenarListas {
    
    private ArrayList<String> listmeminfo;
    private ArrayList<String> listcpuinfo;
    private ArrayList<String> listcpufreq;
    
    public LlenarListas() {
        listcpufreq = new ArrayList<String>();
        comandoconsolameminfo("cat /proc/meminfo");
        comandoconsolacpuinfo("cat /proc/cpuinfo");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_transition_latency");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_available_governors");
    }
    
    private void comandoconsolameminfo(String pcmd) {
        listmeminfo = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listmeminfo.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    private void comandoconsolacpuinfo(String pcmd) {
        listcpuinfo = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listcpuinfo.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    private void comandoconsolacpufreq(String pcmd) {
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = buf.readLine();
            listcpufreq.add(linea.toString());
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    public ArrayList<String> getListmeminfo() {
        return listmeminfo;
    }
    
    public ArrayList<String> getListcpuinfo() {
        return listcpuinfo;
    }
    
    public ArrayList<String> getListcpufreq() {
        return listcpufreq;
    }
}
