package kkdev.kksystem.plugin.odb2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import kkdev.kksystem.base.classes.PluginInfo;
import kkdev.kksystem.base.classes.PluginMessage;
import kkdev.kksystem.base.interfaces.IPluginBaseInterface;
import kkdev.kksystem.base.interfaces.IPluginKKConnector;
import kkdev.kksystem.plugin.odb2.manager.ODB2Manager;

/**
 *
 * @author blinov_is
 */
public final class KKPlugin implements IPluginKKConnector {

    IPluginBaseInterface Connector;
    String MyUID;

    public KKPlugin() {
        MyUID = GetPluginInfo().PluginUUID;
    }

    @Override
    public PluginInfo GetPluginInfo() {
        return ODBPluginInfo.GetPluginInfo();
    }

    @Override
    public void PluginInit(IPluginBaseInterface BaseConnector) {
        Connector = BaseConnector;
        ODB2Manager.InitODB2(this);
    }

    @Override
    public void PluginStart() {
        //not needed in this plugin
    }

    @Override
    public void PluginStop() {
        //not needed in this plugin
    }

    @Override
    public PluginMessage ExecutePin(PluginMessage Pin) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    public void TransmitPinMessage(PluginMessage Pin) {
        Pin.SenderUID = MyUID;
        Connector.ExecutePinCommand(Pin);
    }

}
