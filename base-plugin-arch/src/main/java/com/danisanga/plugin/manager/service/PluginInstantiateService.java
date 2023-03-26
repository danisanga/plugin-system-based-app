package com.danisanga.plugin.manager.service;

import com.danisanga.plugin.manager.entity.Plugin;

import java.util.List;

public interface PluginInstantiateService {

    List<Plugin> loadActivePlugins();
    Plugin instantiatePlugin();
    boolean installPlugin(final Plugin plugin);
    boolean uninstallPlugin(final Plugin plugin);

}
