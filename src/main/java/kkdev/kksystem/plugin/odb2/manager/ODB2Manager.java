package kkdev.kksystem.plugin.odb2.manager;

import kkdev.kksystem.base.classes.odb2.ODBConstants.KK_ODB_DATAPACKET;
import kkdev.kksystem.base.classes.odb2.PinOdb2Command;
import kkdev.kksystem.base.classes.plugins.simple.managers.PluginManagerODB;
import kkdev.kksystem.base.constants.PluginConsts;
import static kkdev.kksystem.base.constants.SystemConsts.KK_BASE_FEATURES_SYSTEM_MULTIFEATURE_UID;
import kkdev.kksystem.plugin.odb2.KKPlugin;
import kkdev.kksystem.plugin.odb2.adapters.IODB2Adapter;
import kkdev.kksystem.plugin.odb2.adapters.elm327.ELM327HW;
import kkdev.kksystem.plugin.odb2.adapters.odb2emulator.ODB2EMULATOR;
import kkdev.kksystem.plugin.odb2.configuration.ODB2Config;
import kkdev.kksystem.plugin.odb2.configuration.PluginSettings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author blinov_is
 */
public class ODB2Manager extends PluginManagerODB {

    static IODB2Adapter ODBAdapter;

    public void InitODB2(KKPlugin PConnector) {
        CurrentFeature=KK_BASE_FEATURES_SYSTEM_MULTIFEATURE_UID;
        Connector=PConnector;
        //
        PluginSettings.InitConfig();
        //
        InitAdapters();
    }
    
    private void InitAdapters() {
        if (PluginSettings.MainConfiguration.ODBAdapter == ODB2Config.AdapterTypes.ELM327_RS232) {
            ODBAdapter = new ELM327HW();
        } else if (PluginSettings.MainConfiguration.ODBAdapter==ODB2Config.AdapterTypes.ODB2_Emulator) {
            ODBAdapter = new ODB2EMULATOR();
        }
    }

    public void ReceivePin(String PinName, Object PinData) {

        switch (PinName) {
            case PluginConsts.KK_PLUGIN_BASE_ODB2_COMMAND:
                PinOdb2Command CMD;
                CMD = (PinOdb2Command) PinData;
                ProcessCommand(CMD);
                break;
            case PluginConsts.KK_PLUGIN_BASE_PIN_COMMAND:
                //This plugin is not use changes of currentfeature
                break;
        }

    }

    private void ProcessCommand(PinOdb2Command CMD) {
        switch (CMD.Command) {
            case ODB_KKSYS_ADAPTER_CONNECT:    //connect to car diag system
                ConnectToCar();
                break;
            case ODB_KKSYS_ADAPTER_DISCONNECT:    //connect to car diag system
                break;
            case ODB_KKSYS_CAR_GETINFO:   //request pid info
                RequestInfo(CMD);
                break;
            case ODB_KKSYS_CAR_EXEC_COMMAND:   //Exec ODB command
                break;
        }

    }
    private void RequestInfo(PinOdb2Command CMD) {
        if (CMD.CommandData==KK_ODB_DATAPACKET.ODB_PIDDATA)
        {
            ODBAdapter.RequestODBInfo(CMD.RequestPIDs);
        }
    }
    
    //
    //Adapter operations
    //
    private void ConnectToCar()
    {
        ODBAdapter.ConnectToVehicle();
    }
}
