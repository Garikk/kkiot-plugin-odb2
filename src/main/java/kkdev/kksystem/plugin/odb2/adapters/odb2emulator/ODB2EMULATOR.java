/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.adapters.odb2emulator;

import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import kkdev.kksystem.base.classes.odb2.ODB2Data;
import kkdev.kksystem.base.classes.odb2.PinOdb2ConnectorInfo;
import kkdev.kksystem.base.classes.odb2.PinOdb2ConnectorInfo.ODB_State;
import kkdev.kksystem.plugin.odb2.Global;
import kkdev.kksystem.plugin.odb2.adapters.IODB2Adapter;

/**
 *
 * @author blinov_is
 */
public class ODB2EMULATOR implements IODB2Adapter {

    //
    boolean Running=false;
    Map<Integer,Integer> RequestPIDCounter;
    
    double TEMP_MAX = 130;
    double TEMP_MIN = 0;
    double SPEED_MAX = 200;
    double SPEED_MIN = 0;
    double RPM_MAX = 6000;
    double RPM_MIN = 0;
    double VOLTAGE_MAX = 15;
    double VOLTAGE_MIN = 8;
    //
    double TEMP_VAL = 98;
    double SPEED_VAL = 15;
    int RPM_VAL = 3000;
    double VOLTAGE_VAL = 12;

    //
    int RPM_STEP = 200;
    double VOLT_STEP = 0.1;
    double OTH_STEP = 4;
    //
    Random R;
    Timer tmrODBEmulator;


    public void GetSimpleInfo() {
        ODB2Data Ret;
        
        Ret = new ODB2Data();

        if (R.nextInt(1) == 1) {
            RPM_STEP = -RPM_STEP;
            VOLT_STEP = -VOLT_STEP;
            OTH_STEP = -OTH_STEP;
        }
        // Speed;
        if ((SPEED_VAL + OTH_STEP >= SPEED_MAX) | (SPEED_VAL + OTH_STEP <= SPEED_MIN)) {
            OTH_STEP = -OTH_STEP;
        }
        SPEED_VAL = SPEED_VAL + OTH_STEP;
        //RPM
        if (RPM_VAL + RPM_STEP >= RPM_MAX | RPM_VAL + RPM_STEP <= RPM_MIN) {
            RPM_STEP = -RPM_STEP;
        }
        RPM_VAL = RPM_VAL + RPM_STEP;
        //TEMP
        if (TEMP_VAL + VOLT_STEP >= VOLTAGE_MAX | TEMP_VAL + VOLT_STEP <= VOLTAGE_MIN) {
            VOLT_STEP = -VOLT_STEP;
        }
        TEMP_VAL = TEMP_VAL + VOLT_STEP;
        //
       // Ret.AddPID(RPM_VAL, RPM_VAL);
    }

    private final TimerTask TTask = new TimerTask() {
        @Override
        public void run() {
            SendODBData();
        }
    };

    @Override
    public void ConnectToVehicle() {
        
        Global.PM.ODB_SendConnectionState(Global.PM.CurrentFeature,ODB_State.ODB_CONNECTOR_READY,"Debug Ok");      
       
    }

    @Override
    public void CheckState() {
        PinOdb2ConnectorInfo Ret;
        Ret=new PinOdb2ConnectorInfo();
        Ret.OdbAdapterState=PinOdb2ConnectorInfo.ODB_State.ODB_CONNECTOR_READY;
        Ret.OdbAdapterDescripton="Debug adapter";
        //
        Global.PM.ODB_SendConnectionState(Global.PM.CurrentFeature,ODB_State.ODB_CONNECTOR_READY,"Debug Ok");
    }

    @Override
    public void RequestODBInfo(int[] REQ_PID) {
        
       for (int i:REQ_PID)
       {
           if (RequestPIDCounter.containsKey(i)){
               int t = RequestPIDCounter.get(i);//=RequestPIDCounter.get(i)+1;
               RequestPIDCounter.put(i, t+1);
           }
           else
           {
               RequestPIDCounter.put(i, 1);
           }
       }
       
       if (!Running)
           tmrODBEmulator.schedule(TTask,1000,1000);
       
       Running=true;
    }
    
    private void SendODBData()
    {
        
        
      //  Global.PM.ODB_SendODBInfo(Global.PM.CurrentFeature, Dat);
    }


}
