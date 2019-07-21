/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.adapters.odb2emulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import kkdev.kksystem.base.classes.odb2.ODB2Data;
import static kkdev.kksystem.base.classes.odb2.ODB2_SAE_J1979_PID_MODE_1.*;
import kkdev.kksystem.base.classes.odb2.PinOdb2ConnectorInfo;
import kkdev.kksystem.base.constants.SystemConsts;
import kkdev.kksystem.plugin.odb2.Global;
import kkdev.kksystem.plugin.odb2.adapters.IODB2Adapter;

/**
 *
 * @author blinov_is
 */
public class ODB2EMULATOR implements IODB2Adapter {

    //
    boolean Running = false;
    Map<Integer, Integer> RequestPIDCounter = new HashMap<>();

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
    double RPM_VAL = 3000;
    double VOLTAGE_VAL = 12;

    //
    int RPM_STEP = 200;
    double VOLT_STEP = 0.1;
    double OTH_STEP = 4;
    //
    Random R = new Random();
    Timer tmrODBEmulator = new Timer();
    Map<Integer, List<Byte>> TestErrors;

    public ODB2EMULATOR() {
        TestErrors = new HashMap<>();
        TestErrors.put(2, new ArrayList<>());
        TestErrors.get(2).add((byte) 40);
        TestErrors.get(2).add((byte) 21);

//        TestErrors.get(04).add((byte) 22);
//        TestErrors.get(04).add((byte) 04);
//        TestErrors.get(04).add((byte) 22);

    }

    public ODB2Data GetSimpleInfo() {
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
        if (TEMP_VAL + VOLT_STEP >= TEMP_MAX | TEMP_VAL + VOLT_STEP <= TEMP_MIN) {
            VOLT_STEP = -VOLT_STEP;
        }
        TEMP_VAL = TEMP_VAL + VOLT_STEP;
        //
        //VOLT
        if (VOLTAGE_VAL + VOLT_STEP >= VOLTAGE_MAX | VOLTAGE_VAL + VOLT_STEP <= VOLTAGE_MIN) {
            VOLT_STEP = -VOLT_STEP;
        }
        //
        VOLTAGE_VAL = VOLTAGE_VAL + VOLT_STEP;
        //

        if (RequestPIDCounter.containsKey(PID_0C_ENGINE_RPM) && RequestPIDCounter.get(PID_0C_ENGINE_RPM) > 0) {
            Ret.addPID(PID_0C_ENGINE_RPM, RPM_VAL);
        }

        if (RequestPIDCounter.containsKey(PID_05_COLIANT_TEMP) && RequestPIDCounter.get(PID_05_COLIANT_TEMP) > 0) {
            Ret.addPID(PID_05_COLIANT_TEMP, TEMP_VAL);
        }

        if (RequestPIDCounter.containsKey(PID_42_CONTROL_MODULE_VOLTAGE) && RequestPIDCounter.get(PID_42_CONTROL_MODULE_VOLTAGE) > 0) {
            Ret.addPID(PID_42_CONTROL_MODULE_VOLTAGE, VOLTAGE_VAL);
        }

        if (RequestPIDCounter.containsKey(PID_0D_VEHICLE_SPEED) && RequestPIDCounter.get(PID_0D_VEHICLE_SPEED) > 0) {
            Ret.addPID(PID_0D_VEHICLE_SPEED, SPEED_VAL);
        }

        if (!Ret.getHT().isEmpty()) {
            return Ret;
        } else {
            return null;
        }
    }

    private final TimerTask TTask = new TimerTask() {
        @Override
        public void run() {
            SendODBData();
        }
    };

    @Override
    public void ConnectToVehicle() {

        Global.PM.ODB_SendConnectionState(Global.PM.currentFeature.get(SystemConsts.KK_BASE_UICONTEXT_DEFAULT), GetConnectorInfo());

    }

    @Override
    public void CheckState() {
        PinOdb2ConnectorInfo Ret = GetConnectorInfo();
        //
        Global.PM.ODB_SendConnectionState(Global.PM.currentFeature.get(SystemConsts.KK_BASE_UICONTEXT_DEFAULT), GetConnectorInfo());
    }

    public PinOdb2ConnectorInfo GetConnectorInfo() {
        PinOdb2ConnectorInfo Ret;
        Ret = new PinOdb2ConnectorInfo();
        Ret.odbAdapterState = PinOdb2ConnectorInfo.ODB_State.ODB_CONNECTOR_READY;
        Ret.odbAdapterDescripton = "Debug adapter";
        return Ret;
    }

    @Override
    public void RequestCEErrors(Set<String> FeatureID) {
        ODB2Data Dat = new ODB2Data();
        for (Integer Pfx : TestErrors.keySet()) {
            for (Byte Val : TestErrors.get(Pfx)) {
                Dat.addError(Pfx, Val);
            }
        }
        Global.PM.ODB_SendODBErrors(FeatureID, GetConnectorInfo(), Dat);
    }

    @Override
    public void RequestODBInfo(int[] REQ_PID, boolean Stop) {

        if (!Stop) {

            for (int i : REQ_PID) {
                if (RequestPIDCounter.containsKey(i)) {
                    int t = RequestPIDCounter.get(i);//=RequestPIDCounter.get(i)+1;
                    RequestPIDCounter.put(i, t + 1);
                } else {
                    RequestPIDCounter.put(i, 1);
                }
            }

            if (!Running) {
                tmrODBEmulator.schedule(TTask, 1000, 1000);
            }

            Running = true;
        } else {
            for (int i : REQ_PID) {
                if (RequestPIDCounter.containsKey(i)) {
                    int t = RequestPIDCounter.get(i);
                    RequestPIDCounter.put(i, t - 1);
                }
            }
        }
    }

    private void SendODBData() {
        ODB2Data D = GetSimpleInfo();
        if (D != null) {
            Global.PM.ODB_SendODBInfo(Global.PM.currentFeature.get(SystemConsts.KK_BASE_UICONTEXT_DEFAULT), GetConnectorInfo(), D);
        }
    }

    @Override
    public void ClearCEErrors(Set<String> FeatureID) {
        TestErrors = new HashMap<>();
        RequestCEErrors(FeatureID);
    }
}