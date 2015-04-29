/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.configuration;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kkdev.kksystem.base.constants.SystemConsts;
import static kkdev.kksystem.plugin.odb2.configuration.SettingsManager.ODB_CONF;


/**
 *
 * @author blinov_is
 *

 */
public abstract class kk_DefaultConfig {

    public static void MakeDefaultConfig() {
       
        try {
            ODB2Config DefConf=GetDefaultconfig();
            
            Gson gson=new Gson();
            
            String Res=gson.toJson(DefConf);
            
            FileWriter fw;
            fw = new FileWriter(SystemConsts.KK_BASE_CONFPATH + "/"+ODB_CONF);
            fw.write(Res);
            fw.flush();
            fw.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(kk_DefaultConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static ODB2Config GetDefaultconfig() {
        
        ODB2Config DefConf = new ODB2Config();
        
      //  DefConf.ODBAdapter=ODB2Config.AdapterTypes.ELM327_RS232;
      //  DefConf.RS232AutoFind=false;
       // DefConf.RS232ConnectPort="//dev//ttyUSB0"; // CHANGE!!
        DefConf.ODBAdapter=ODB2Config.AdapterTypes.ODB2_Emulator;
        DefConf.RS232AutoFind=false;
        DefConf.RS232ConnectPort="none"; // CHANGE!!
        return DefConf;
    }
}
    
