package kkdev.kksystem.plugin.odb2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import kkdev.kksystem.base.classes.plugins.PluginInfo;
import kkdev.kksystem.base.classes.plugins.simple.IPluginInfoRequest;
import kkdev.kksystem.base.constants.PluginConsts;

/**
 *
 * @author blinov_is e
 */
public class ODBPluginInfo  implements IPluginInfoRequest {
    @Override
    public PluginInfo getPluginInfo()
    {
        PluginInfo Ret=new PluginInfo();
        
        Ret.PluginName="KKODB2Reader";
        Ret.PluginDescription="Basic ELM327 ODB2 Reader plugin";
        Ret.PluginVersion=1;
        Ret.Enabled=true;
        Ret.ReceivePins = GetMyReceivePinInfo();
        Ret.TransmitPins = GetMyTransmitPinInfo();
        Ret.PluginUUID="44b5dab1-f596-458a-b09b-d9565b91464e";
        return Ret;
    }
    
    
    private String[] GetMyReceivePinInfo(){
    
        String[] Ret=new String[2];
    

        Ret[0]=PluginConsts.KK_PLUGIN_BASE_PIN_COMMAND;
        Ret[1]=PluginConsts.KK_PLUGIN_BASE_ODB2_COMMAND;

        
        return Ret;
    }
    private String[] GetMyTransmitPinInfo(){
    
        String[] Ret=new String[2];
    

        Ret[0]=PluginConsts.KK_PLUGIN_BASE_ODB2_DATA;
        Ret[1]=PluginConsts.KK_PLUGIN_BASE_ODB2_RAW;

        
        return Ret;
    }
    
}
