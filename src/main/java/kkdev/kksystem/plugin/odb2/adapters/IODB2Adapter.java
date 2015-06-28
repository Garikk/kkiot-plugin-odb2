/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.adapters;

import kkdev.kksystem.base.classes.odb2.PinOdb2ConnectorInfo;
import kkdev.kksystem.base.classes.odb2.PinOdb2Data_ExtendedMonitoringInfo;
import kkdev.kksystem.base.classes.odb2.PinOdb2Data_SimpleMonitoringInfo;

/**
 *
 * @author blinov_is
 */
public interface IODB2Adapter {
    public PinOdb2ConnectorInfo ConnectToVehicle();
    public PinOdb2ConnectorInfo CheckState();
    
    public PinOdb2Data_SimpleMonitoringInfo GetSimpleInfo();
    public PinOdb2Data_ExtendedMonitoringInfo GetExtendedInfo(int[] REQ_PID);
    
}
