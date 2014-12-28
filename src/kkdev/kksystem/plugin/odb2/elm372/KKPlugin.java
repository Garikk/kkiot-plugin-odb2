/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.elm372;

import kkdev.kksystem.base.classes.PluginInfo;
import kkdev.kksystem.base.classes.PluginPin;
import kkdev.kksystem.base.interfaces.IKKConnector;
import kkdev.kksystem.base.interfaces.IPluginKKConnector;

/**     
 *
 * @author blinov_is
 */
public class KKPlugin implements IPluginKKConnector   {

    IKKConnector Connector;
    

    @Override
    public PluginInfo GetPluginInfo() {
         return ODBPluginInfo.GetPluginInfo();
    }

    @Override
    public PluginPin[] GetPinsForRegister() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PluginInit(IKKConnector BaseConnector) {
       Connector=BaseConnector;
    }

    @Override
    public void PluginStart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PluginStop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PluginPin ExecutePin(PluginPin Pin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}

