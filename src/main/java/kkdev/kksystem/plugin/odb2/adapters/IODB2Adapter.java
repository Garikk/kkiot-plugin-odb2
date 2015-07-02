/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.adapters;

import kkdev.kksystem.base.classes.odb2.ODB2Data;
import kkdev.kksystem.base.classes.odb2.PinOdb2ConnectorInfo;


/**
 *
 * @author blinov_is
 */
public interface IODB2Adapter {
    public PinOdb2ConnectorInfo ConnectToVehicle();
    public PinOdb2ConnectorInfo CheckState();
    
    public ODB2Data GetSimpleInfo();
    public ODB2Data GetExtendedInfo(int[] REQ_PID);
    
}
