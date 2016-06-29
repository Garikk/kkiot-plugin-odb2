package kkdev.kksystem.plugin.odb2.manager;

import java.util.HashMap;
import kkdev.kksystem.base.classes.odb2.ODBConstants.KK_ODB_DATACOMMANDINFO;
import kkdev.kksystem.base.classes.odb2.PinDataOdb2;
import kkdev.kksystem.base.classes.plugins.simple.managers.PluginManagerODB;
import kkdev.kksystem.base.constants.PluginConsts;
import kkdev.kksystem.base.constants.SystemConsts;
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
        currentFeature=new HashMap<>();
        currentFeature.put(SystemConsts.KK_BASE_UICONTEXT_DEFAULT, KK_BASE_FEATURES_SYSTEM_MULTIFEATURE_UID);
        setPluginConnector(PConnector);
        //
        PluginSettings.InitConfig(PConnector.globalConfID,PConnector.pluginInfo.getPluginInfo().PluginUUID);
        //
        InitAdapters();
    }

    private void InitAdapters() {
        if (PluginSettings.MainConfiguration.ODBAdapter == ODB2Config.AdapterTypes.ELM327_RS232) {
            ODBAdapter = new ELM327HW();
        } else if (PluginSettings.MainConfiguration.ODBAdapter == ODB2Config.AdapterTypes.ODB2_Emulator) {
            ODBAdapter = new ODB2EMULATOR();
        }
    }

    public void ReceivePin(String FeatureID,String PinName, Object PinData) {
        switch (PinName) {
            case PluginConsts.KK_PLUGIN_BASE_ODB2_COMMAND:
                PinDataOdb2 CMD;
                CMD = (PinDataOdb2) PinData;
                ProcessCommand(FeatureID,CMD);
                break;
            case PluginConsts.KK_PLUGIN_BASE_PIN_COMMAND:
                //This plugin is not use changes of currentfeature
                break;
        }

    }

    private void ProcessCommand(String FeatureID,PinDataOdb2 CMD) {
 
        switch (CMD.command) {
            case ODB_KKSYS_ADAPTER_CONNECT:    //connect to car diag system
                ConnectToCar();
                break;
            case ODB_KKSYS_ADAPTER_DISCONNECT:    //connect to car diag system
                break;
            case ODB_KKSYS_CAR_GETINFO:   //request pid info
                RequestInfo(FeatureID,CMD, false);
                break;
            case ODB_KKSYS_CAR_GETINFO_STOP:   //request pid info
                RequestInfo(FeatureID,CMD, true);
                break;
            case ODB_KKSYS_CAR_EXEC_COMMAND:   //Exec ODB command
                ExecCarCommand(FeatureID,CMD);
                break;
        }

    }

    private void RequestInfo(String FeatureID,PinDataOdb2 CMD, boolean Stop) {
        if (CMD.commandData == KK_ODB_DATACOMMANDINFO.ODB_GETINFO_PIDDATA) {
            ODBAdapter.RequestODBInfo(CMD.requestPIDs, Stop);
        } else if (CMD.commandData == KK_ODB_DATACOMMANDINFO.ODB_GETINFO_CE_ERRORS) {
         
            RequestCEErrors(FeatureID,CMD);

        }
    }

    private void RequestCEErrors(String FeatureID,PinDataOdb2 CMD) {
                     
        ODBAdapter.RequestCEErrors(FeatureID);
    }

    private void ExecCarCommand(String FeatureID,PinDataOdb2 CMD) {

        if (CMD.commandData == KK_ODB_DATACOMMANDINFO.ODB_CMD_CLEAR_CE_DATA) {
            ODBAdapter.ClearCEErrors(FeatureID);
        }
    }

    //
    //Adapter operations
    //
    private void ConnectToCar() {
        ODBAdapter.ConnectToVehicle();
    }
}
