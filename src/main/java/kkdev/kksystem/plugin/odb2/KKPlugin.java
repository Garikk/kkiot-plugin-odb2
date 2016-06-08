package kkdev.kksystem.plugin.odb2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.KKPluginBase;
import kkdev.kksystem.base.interfaces.IPluginBaseInterface;
import kkdev.kksystem.plugin.odb2.manager.ODB2Manager;

/**
 *
 * @author blinov_is
 */
public final class KKPlugin extends KKPluginBase {
    public KKPlugin() {
        super(new ODBPluginInfo());
        Global.PM=new ODB2Manager();
    }

    @Override
    public void pluginInit(IPluginBaseInterface BaseConnector, String GlobalConfUID) {
        super.pluginInit(BaseConnector, GlobalConfUID); //To change body of generated methods, choose Tools | Templates.
        Global.PM.InitODB2(this);
    }

    
    @Override
    public PluginMessage executePin(PluginMessage Pin) {
        super.executePin(Pin);
       Global.PM.ReceivePin(Pin.FeatureID,Pin.PinName, Pin.PinData);
        return null;
    }
}
