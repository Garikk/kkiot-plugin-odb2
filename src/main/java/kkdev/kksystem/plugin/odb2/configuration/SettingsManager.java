/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.configuration;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import kkdev.kksystem.base.constants.SystemConsts;



/**
 *
 * @author blinov_is
 */
public abstract class SettingsManager {
   public static final String ODB_CONF="kk_plugin_odb2connector.json";

    
    public static ODB2Config MainConfiguration;
    
    public static void InitConfig()
    {
        System.out.println("[ODB2][CONFIG] Load configuration");
        LoadConfig();
        
        if (MainConfiguration==null)
        {
            System.out.println("[ODB2][CONFIG] Error Load configuration, try create default config");
            kk_DefaultConfig.MakeDefaultConfig();
            LoadConfig();
        }
        if (MainConfiguration==null)
        {
            System.out.println("[ODB2][CONFIG] Load configuration, fatal");
            return;
        }
       //
    }
    
    private static void LoadConfig() 
    {
       try {
           Gson gson=new Gson();
           BufferedReader br = new BufferedReader(  
                 new FileReader(SystemConsts.KK_BASE_FORPLUGINS_CONFPATH + "/"+ODB_CONF));  

           MainConfiguration = (ODB2Config)gson.fromJson(br, ODB2Config.class);
           
           
       } catch (FileNotFoundException ex) {
           Logger.getLogger(SettingsManager.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
