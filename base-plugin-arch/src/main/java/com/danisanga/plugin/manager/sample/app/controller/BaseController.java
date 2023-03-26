package com.danisanga.plugin.manager.sample.app.controller;

import com.danisanga.plugin.manager.PluginManager;
import com.danisanga.plugin.manager.entity.Plugin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    private final PluginManager pluginManager;

    public BaseController(final PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @GetMapping("/plugin/demo")
    public void loadPlugins() throws Exception {
        final Plugin plugin =
                pluginManager.readJarFileAndCreatesInstance(
                        "/Users/danisanga/Desktop/personal-projects/osgi-like-plugin-system/plugin-manager/base-plugin-arch/src/main/resources/plugins/demo-0.0.1-SNAPSHOT.jar"
                );
        System.out.println("Running instance now....!");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome!";
    }
}
