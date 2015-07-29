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
    public void PluginInit(IPluginBaseInterface BaseConnector, String GlobalConfUID, String[] FeaturesUID) {
        super.PluginInit(BaseConnector, GlobalConfUID, FeaturesUID); //To change body of generated methods, choose Tools | Templates.
        Global.PM.InitODB2(this);
    }

    
    @Override
    public PluginMessage ExecutePin(PluginMessage Pin) {
        super.ExecutePin(Pin);
       Global.PM.ReceivePin(Pin.PinName, Pin.PinData);
        return null;
    }
}
