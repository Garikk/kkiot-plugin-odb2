package kkdev.kksystem.plugin.odb2.manager;

import kkdev.kksystem.base.classes.odb2.PinOdb2Command;
import kkdev.kksystem.base.constants.PluginConsts;
import kkdev.kksystem.plugin.odb2.adapters.IODB2Adapter;
import kkdev.kksystem.plugin.odb2.configuration.SettingsManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author blinov_is
 */
public abstract class ODB2Manager {
    IODB2Adapter ODBAdapter;
    
    public static void InitODB2()
    {
        System.out.println("[ODB2][INIT] ODB adapter initialising");
        System.out.println("[ODB2][CONFIG] Load configuration");
        SettingsManager.InitConfig();
        System.out.println("[ODB2][CONFIG] Connect adapters");
        InitAdapters();
    }
    
    private static void InitAdapters()
    {
        
    
    }
    
    
    
    
    
     public static void ReceivePin(String PinName, Object PinData)
    {
        
        switch (PinName)
        { case PluginConsts.KK_PLUGIN_BASE_PIN_ODB2_COMMAND:
                PinOdb2Command CMD;
                CMD=(PinOdb2Command)PinData;
                ProcessCommand(CMD);
                break;
        }
    }
     
     private static void ProcessCommand(PinOdb2Command CMD)
     {
     
     
     }
    
}
