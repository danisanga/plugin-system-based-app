package com.danisanga.plugin.manager.service.impl;

import com.danisanga.plugin.manager.entity.Plugin;
import com.danisanga.plugin.manager.service.PluginInstantiateService;

import java.util.List;

public class PluginInstantiateServiceImpl implements PluginInstantiateService {
    @Override
    public List<Plugin> loadActivePlugins() {
        return null;
    }

    @Override
    public Plugin instantiatePlugin() {
        return null;
    }

    @Override
    public boolean installPlugin(Plugin plugin) {
        return false;
    }

    @Override
    public boolean uninstallPlugin(Plugin plugin) {
        return false;
    }
}
