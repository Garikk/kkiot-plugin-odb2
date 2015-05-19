package kkdev.kksystem.plugin.odb2.manager;

import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.odb2.ODBConstants;
import kkdev.kksystem.base.classes.odb2.ODBConstants.KK_ODB_DATAPACKET;
import kkdev.kksystem.base.classes.odb2.PinOdb2Command;
import kkdev.kksystem.base.classes.odb2.PinOdb2ConnectorInfo;
import kkdev.kksystem.base.classes.odb2.PinOdb2Data;
import kkdev.kksystem.base.constants.PluginConsts;
import static kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_BASE_ODB2_DATA;
import kkdev.kksystem.plugin.odb2.KKPlugin;
import kkdev.kksystem.plugin.odb2.adapters.IODB2Adapter;
import kkdev.kksystem.plugin.odb2.adapters.elm327.ELM327HW;
import kkdev.kksystem.plugin.odb2.adapters.odb2emulator.ODB2EMULATOR;
import kkdev.kksystem.plugin.odb2.configuration.ODB2Config;
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
    static KKPlugin Connector;
    static IODB2Adapter ODBAdapter;


    public static void InitODB2(KKPlugin PConnector) {
        Connector=PConnector;
        System.out.println("[ODB2][INIT] ODB adapter initialising");
        System.out.println("[ODB2][CONFIG] Load configuration");
        SettingsManager.InitConfig();
        System.out.println("[ODB2][CONFIG] Connect adapters");
        InitAdapters();
    }
    
    

    private static void InitAdapters() {
        if (SettingsManager.MainConfiguration.ODBAdapter == ODB2Config.AdapterTypes.ELM327_RS232) {
            ODBAdapter = new ELM327HW();
        } else if (ODB2Config.AdapterTypes.ODB2_Emulator == SettingsManager.MainConfiguration.ODBAdapter) {
            ODBAdapter = new ODB2EMULATOR();
        }

    }

    public static void ReceivePin(String PinName, Object PinData) {
 System.out.println("[DEBUG][ODB2][PROCREC] " + PinName);
        switch (PinName) {
            case PluginConsts.KK_PLUGIN_BASE_ODB2_COMMAND:
                PinOdb2Command CMD;
                CMD = (PinOdb2Command) PinData;
                ProcessCommand(CMD);
                break;
        }
    }

    private static void ProcessCommand(PinOdb2Command CMD) {
        System.out.println("[DEBUG][ODB2][PROCCMD] " + CMD.Command);
        switch (CMD.Command) {
            case ODB_KKSYS_ADAPTER_CONNECT:    //connect to car diag system
                ConnectToCar(CMD);
                break;
            case ODB_KKSYS_ADAPTER_DISCONNECT:    //connect to car diag system
                DisconnectFromCar(CMD);
                break;
            case ODB_KKSYS_CAR_GETINFO:   //request pid info
                RequestInfo(CMD);
                break;
            case ODB_KKSYS_CAR_EXEC_COMMAND:   //Exec ODB command
                ExecCommand(CMD);
                break;
        }

    }

    private static void ConnectToCar(PinOdb2Command CMD) {
        
        PluginMessage Msg = new PluginMessage();
        Msg.PinName = KK_PLUGIN_BASE_ODB2_DATA;
        //
        PinOdb2Data PData = new PinOdb2Data();
        PData.DataType=ODBConstants.KK_ODB_DATATYPE.ODB_BASE_CONNECTOR;
        PData.AdapterInfo=new PinOdb2ConnectorInfo();
        PData.AdapterInfo.OdbAdapterConnected=true; //Dummy
        
         Msg.PinData=PData;
        //Msg.PinData = PData;
        //
        Connector.TransmitPinMessage(Msg);
    }

    private static void DisconnectFromCar(PinOdb2Command CMD) {
    }

    private static void RequestInfo(PinOdb2Command CMD) {
        if (CMD.CommandData==KK_ODB_DATAPACKET.ODB_SIMPLEDATA)
        {
        
        }
    }

    private static void ExecCommand(PinOdb2Command CMD) {

    }
}
