/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.configuration;


/**
 *
 * @author blinov_is
 *

 */
public abstract class kk_DefaultConfig {
    public static ODB2Config MakeDefaultConfig() {
        
        ODB2Config DefConf = new ODB2Config();
        
      //  DefConf.ODBAdapter=ODB2Config.AdapterTypes.ELM327_RS232;
      //  DefConf.RS232AutoFind=true;
       // DefConf.RS232ConnectPort="//dev//ttyUSB0"; // CHANGE!!
        DefConf.ODBAdapter=ODB2Config.AdapterTypes.ODB2_Emulator;
        DefConf.RS232AutoFind=true;
        DefConf.RS232ConnectPort="none"; // CHANGE!!
        return DefConf;
    }
}
    
