package com.danisanga.plugin.manager;

import com.danisanga.plugin.manager.entity.Plugin;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

@Component
public class PluginManager {

    public Plugin readJarFileAndCreatesInstance(String path) throws Exception {
        File jarFile = new File(path);

        String className;
        try (JarInputStream jarStream = new JarInputStream(new FileInputStream(jarFile))) {
            Manifest manifest = jarStream.getManifest();

            // Get Main-Class attribute from MANIFEST.MF
            className = manifest.getMainAttributes().getValue("event-class");
        }

        System.out.println("Class name found in manifest is: " + className);

        // Here, we initialize the class loader with the path to the jar file.
        //
        // We use the jar protocol here. The !/ is used to separate between the URL to the actual
        // jar resource and the path within the jar. For example, I could download a whole jar by
        // calling jar:http://something.com/myjar!/ or I could download only 1 class file by calling
        // jar:http://something.com/myjar!/com/gabdavid/myjar/File.class
        // See https://docs.oracle.com/javase/7/docs/api/java/net/JarURLConnection.html for more details
        URLClassLoader classLoader = new URLClassLoader(
                new URL[] {new URL("jar:" + jarFile.toURI().toURL() + "!/")},
                Thread.currentThread().getContextClassLoader()
        );

        Class<?> pluginClass = classLoader.loadClass(className); // Load the class

        System.out.println("Package of plugin class: " + pluginClass.getPackage());
        System.out.println("Creating a new instance now....!");

        // Create an instance of the loaded class and cast it from an Object
        // to a Runnable. We assume that this will succeed because that is the
        // contract we've agreed to (see theory section).
        Plugin plugin = (Plugin) pluginClass.newInstance();

        System.out.println("Exiting successfully!");

        return plugin;

    }
}
