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
 * @author Rider
 */
public class LlenarListas {

    private ArrayList<String> listmeminfo;
    private ArrayList<String> listcpuinfo;
    private ArrayList<String> listcpufreq;
    private ArrayList<String> listparticiones;
    private ArrayList<String> listprocesos;
    private ArrayList<String> listactualizacion;
    private ArrayList<String> listadistribucion;
    private ArrayList<String> listusb;
    private ArrayList<String> listxrandr;
    private ArrayList<String> listaceleracion;
    String linea;

    public LlenarListas() {
        listcpufreq = new ArrayList<String>();
        listxrandr = new ArrayList<String>();
        listaceleracion = new ArrayList<String>();
        comandoconsolaprocesos("ps aux"); //axf
        comandoconsolameminfo("cat /proc/meminfo");
        comandoconsolacpuinfo("cat /proc/cpuinfo");
        comandoconsolaparticiones("df");
        comandoconsoladistribucion("lsb_release -idc");
        comandoconsolausb("lsusb");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_transition_latency");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor");
        comandoconsolacpufreq("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_available_governors");
        comandocosolaxrandr("xrandr");
            comandoconsolaceleracion("lspci -k");

    }
    
    private void comandoconsolaceleracion(String pcmd){
        listaceleracion= new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listaceleracion.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
         }
        }
    
    private void comandocosolaxrandr(String pcmd){
        listxrandr = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listxrandr.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void comandoconsoladistribucion(String pcmd) {
        listadistribucion = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listadistribucion.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void comandoconsolausb(String pcmd) {
        listusb = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listusb.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void comandoconsolaprocesos(String pcmd) {
        listprocesos = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listprocesos.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void comandoconsolaactualizacion(String pcmd) {
        listactualizacion = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listactualizacion.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void comandoconsolaparticiones(String pcmd) {
        listparticiones = new ArrayList<String>();
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            String linea = "";
            while ((linea = buf.readLine()) != null) {
                listparticiones.add(linea.toString());
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
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

    public String comandoconsolagenerico(String pcmd) {
        try {
            Process cmd = Runtime.getRuntime().exec(pcmd);
            cmd.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
            linea = buf.readLine();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Excepcion", javax.swing.JOptionPane.PLAIN_MESSAGE);
        }
        return linea.toString();
    }

    public ArrayList<String> getListdistribucion() {
        return listadistribucion;
    }

    public ArrayList<String> getListusb() {
        return listusb;
    }

    public ArrayList<String> getListmeminfo() {
        return listmeminfo;
    }

    public ArrayList<String> getListcpuinfo() {
        return listcpuinfo;
    }

    public ArrayList<String> getListparticiones() {
        return listparticiones;
    }

    public ArrayList<String> getListcpufreq() {
        return listcpufreq;
    }

    ArrayList<String> getListprocesos() {
        return listprocesos;
    }

    public ArrayList<String> getListActualizacion() {
        return listactualizacion;
    }
    
    public ArrayList<String> getlistxrandr() {
        return listxrandr;
    }

    public ArrayList<String> getListaceleracion() {
        return listaceleracion;
    }
    
    
}
