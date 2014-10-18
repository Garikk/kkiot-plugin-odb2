/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugins.odb2.elm372;

import kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_TYPE;
import kkdev.kksystem.base.classes.PluginInfo;

/**
 *
 * @author blinov_is
 */
public final class ODBPluginInfo  {
    public static PluginInfo GetPluginInfo()
    {
        PluginInfo Ret=new PluginInfo();
        
        Ret.PluginName="KKODB2Reader";
        Ret.PluginDescription="Basic ELM327 ODB2 Reader plugin";
        Ret.PluginType = KK_PLUGIN_TYPE.PLUGIN_INPUT;
        Ret.PluginVersion=1;
        return Ret;
    }
}
