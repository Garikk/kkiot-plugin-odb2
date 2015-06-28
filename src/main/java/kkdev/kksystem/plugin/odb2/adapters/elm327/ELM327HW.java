/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.adapters.elm327;

import kkdev.kksystem.base.classes.odb2.PinOdb2ConnectorInfo;
import kkdev.kksystem.base.classes.odb2.PinOdb2Data_ExtendedMonitoringInfo;
import kkdev.kksystem.base.classes.odb2.PinOdb2Data_SimpleMonitoringInfo;
import kkdev.kksystem.plugin.odb2.adapters.IODB2Adapter;

/**
 *
 * @author blinov_is
 */
public class ELM327HW implements IODB2Adapter {
    PinOdb2ConnectorInfo CurrentState;
    @Override
    public PinOdb2Data_SimpleMonitoringInfo GetSimpleInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PinOdb2Data_ExtendedMonitoringInfo GetExtendedInfo(int[] REQ_PID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PinOdb2ConnectorInfo ConnectToVehicle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PinOdb2ConnectorInfo CheckState() {
        return CurrentState;
    }
    
}
