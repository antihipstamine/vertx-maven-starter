package com.antihipstamine;

import net.sourceforge.openstego.OpenStego;
import net.sourceforge.openstego.OpenStegoException;
import net.sourceforge.openstego.OpenStegoPlugin;
import net.sourceforge.openstego.util.PluginManager;
import net.sourceforge.openstego.util.cmd.CmdLineOption;
import net.sourceforge.openstego.util.cmd.CmdLineOptions;
import net.sourceforge.openstego.util.cmd.CmdLineParser;

import java.io.File;
import java.util.List;

/**
 * Created by vish on 25/04/2017.
 */
public class OpenStegoWrapper {

  private String msgFileName = null;
  private String sigFileName = null;
  private String coverFileName = null;
  private String stegoFileName = null;
  private String extractDir = null;
  private String extractFileName = null;
  private String signatureFileName = null;
  private String command = null;
  private String pluginName = null;
  private List<?> msgData = null;
  private List<File> coverFileList = null;
  private List<File> stegoFileList = null;
  private OpenStego stego = null;
  private CmdLineParser parser = null;
  private CmdLineOptions options = null;
  private CmdLineOption option = null;
  private List<CmdLineOption> optionList = null;
  private OpenStegoPlugin plugin = null;
  private List<OpenStegoPlugin> plugins = null;

  public OpenStegoWrapper() {
    execute();
  }

  public void execute() {
    try {
      PluginManager.loadPlugins();
    } catch (OpenStegoException e) {
      e.printStackTrace();
    }

    plugins = PluginManager.getPlugins();
    if (plugins.size() == 1) {
      plugin = plugins.get(0);
    } else if (plugins.size() > 1) {
      autoselectWatermarkingPlugin();
      autoselectDataHidingPlugin();
    }
  }

    private void autoselectWatermarkingPlugin() {
    plugins = PluginManager.getWatermarkingPlugins();
    if (plugins.size() == 1) {
      plugin = plugins.get(0);
    }
  }

  private void autoselectDataHidingPlugin() {
    plugins = PluginManager.getDataHidingPlugins();
    if (plugins.size() == 1) {
      plugin = plugins.get(0);
    }
  }

}
