/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.adapters;


/**
 *
 * @author blinov_is
 */
public interface IODB2Adapter {
    public void ConnectToVehicle();
    public void CheckState();
    
    public void RequestODBInfo(int[] REQ_PID,boolean Stop);
    public void RequestCEErrors(String FeatureID);
    public void ClearCEErrors(String FeatureID);
    
}
