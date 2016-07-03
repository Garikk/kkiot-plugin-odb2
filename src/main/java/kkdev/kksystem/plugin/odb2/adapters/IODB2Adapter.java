/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.adapters;

import java.util.Set;


/**
 *
 * @author blinov_is
 */
public interface IODB2Adapter {
    public void ConnectToVehicle();
    public void CheckState();
    
    public void RequestODBInfo(int[] REQ_PID,boolean Stop);
    public void RequestCEErrors(Set<String> FeatureID);
    public void ClearCEErrors(Set<String> FeatureID);
    
}
