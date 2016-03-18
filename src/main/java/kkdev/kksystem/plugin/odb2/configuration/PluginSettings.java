/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.configuration;

import kkdev.kksystem.base.classes.plugins.simple.SettingsManager;

/**
 *
 * @author blinov_is
 */
public abstract class PluginSettings {

   public static  String ODB_CONF;
   private static SettingsManager Settings;

    public static ODB2Config MainConfiguration;

    public static void InitConfig(String GlobalConfigUID, String MyUID) {
         ODB_CONF=GlobalConfigUID+"_"+MyUID + ".json";
        
        Settings=new SettingsManager(ODB_CONF,ODB2Config.class);
        
        
      //  System.out.println("[ODB2][CONFIG] Load configuration");
        MainConfiguration=(ODB2Config)Settings.LoadConfig();

        if (MainConfiguration == null) {
            System.out.println("[ODB2][CONFIG] Error Load configuration, try create default config");
            Settings.SaveConfig(kk_DefaultConfig.MakeDefaultConfig());
            MainConfiguration=(ODB2Config)Settings.LoadConfig();
        }
        if (MainConfiguration == null) {
            System.out.println("[ODB2][CONFIG] Load configuration, fatal");
            return;
        }
        //
    }
}
