package kkdev.kksystem.plugin.odb2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import kkdev.kksystem.base.classes.plugins.PluginConfiguration;
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.KKPluginBase;
import kkdev.kksystem.plugin.odb2.manager.ODB2Manager;
import kkdev.kksystem.base.interfaces.IPluginBaseConnection;
import kkdev.kksystem.plugin.odb2.configuration.PluginSettings;

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
    public void pluginInit(IPluginBaseConnection BaseConnector, String GlobalConfUID) {
        super.pluginInit(BaseConnector, GlobalConfUID); //To change body of generated methods, choose Tools | Templates.
        Global.PM.InitODB2(this);
    }

    
    @Override
    public void executePin(PluginMessage Pin) {
        super.executePin(Pin);
       Global.PM.ReceivePin(Pin.FeatureID,Pin.pinName, Pin.getPinData());
    }
     @Override
    public PluginConfiguration getPluginSettings() {
       return PluginSettings.MainConfiguration;
    }
}
