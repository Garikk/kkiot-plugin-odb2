/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.elm372;

import kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_TYPE;
import kkdev.kksystem.base.classes.PluginInfo;
import kkdev.kksystem.base.constants.PluginConsts;

/**
 *
 * @author blinov_is e
 */
public final class ODBPluginInfo  {
    public static PluginInfo GetPluginInfo()
    {
        PluginInfo Ret=new PluginInfo();
        
        Ret.PluginName="KKODB2Reader";
        Ret.PluginDescription="Basic ELM327 ODB2 Reader plugin";
        Ret.PluginType = KK_PLUGIN_TYPE.PLUGIN_INPUT;
        Ret.PluginVersion=1;
        Ret.Enabled=true;
        Ret.ReceivePins = GetMyReceivePinInfo();
        Ret.TransmitPins = GetMyTransmitPinInfo();
        return Ret;
    }
    
    
    private static String[] GetMyReceivePinInfo(){
    
        String[] Ret=new String[2];
    
        Ret[0]=PluginConsts.KK_PLUGIN_BASE_PLUGIN_DEF_PIN_TARGET_COMMAND;
        Ret[1]=PluginConsts.KK_PLUGIN_BASE_PLUGIN_DEF_PIN_TARGET_ODB2_COMMAND;
        
        return Ret;
    }
    private static String[] GetMyTransmitPinInfo(){
    
        String[] Ret=new String[2];
    
        Ret[0]=PluginConsts.KK_PLUGIN_BASE_PLUGIN_DEF_PIN_SOURCE_ODB2_DATA;
        Ret[1]=PluginConsts.KK_PLUGIN_BASE_PLUGIN_DEF_PIN_SOURCE_ODB2_RAW;
        
        return Ret;
    }
    
}
