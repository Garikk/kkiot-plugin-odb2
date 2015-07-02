/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.adapters.odb2emulator;

import java.util.Random;
import kkdev.kksystem.base.classes.odb2.ODB2Data;
import kkdev.kksystem.base.classes.odb2.PinOdb2ConnectorInfo;
import kkdev.kksystem.plugin.odb2.adapters.IODB2Adapter;

/**
 *
 * @author blinov_is
 */
public class ODB2EMULATOR implements IODB2Adapter {

    //

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

    @Override
    public ODB2Data GetSimpleInfo() {
        ODB2Data Ret;

        Ret = new ODB2Data();
/*
        Ret.PID_CAR_Speed = SPEED_VAL;
        Ret.PID_DIAG_MIL_State = true;
        Ret.PID_ELECTRIC_Voltage = VOLTAGE_VAL;
        Ret.PID_ENGINE_EngineRPM = RPM_VAL;
        Ret.PID_ENGINE_EngineTemp = TEMP_VAL;
        */
        //
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
        return Ret;
    }

    @Override
    public ODB2Data GetExtendedInfo(int[] REQ_PID) {
        return null;
    }

    @Override
    public PinOdb2ConnectorInfo ConnectToVehicle() {
        PinOdb2ConnectorInfo Ret;
        Ret=new PinOdb2ConnectorInfo();
        Ret.OdbAdapterState=PinOdb2ConnectorInfo.ODB_State.ODB_CONNECTOR_READY;
        Ret.OdbAdapterDescripton="Debug adapter";
        return Ret;
                
       
    }

    @Override
    public PinOdb2ConnectorInfo CheckState() {
        PinOdb2ConnectorInfo Ret;
        Ret=new PinOdb2ConnectorInfo();
        Ret.OdbAdapterState=PinOdb2ConnectorInfo.ODB_State.ODB_CONNECTOR_READY;
        Ret.OdbAdapterDescripton="Debug adapter";
        return Ret;
    }

}
