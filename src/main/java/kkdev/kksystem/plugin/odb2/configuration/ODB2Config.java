/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.odb2.configuration;

import kkdev.kksystem.base.classes.plugins.ExternalConfiguration;

/**
 *
 * @author blinov_is
 */
public class ODB2Config  extends ExternalConfiguration {
    public enum AdapterTypes {
        ELM327_RS232,
        ODB2_Emulator
    }
    
    public AdapterTypes ODBAdapter;
    public String RS232ConnectPort;
    public int RS232Speed;
    public boolean RS232AutoFind;
}
