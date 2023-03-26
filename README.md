# Plugin system-based App 
Let’s build a Plugin system-based app! 🥳

### 🏋🏼 Motivation

- Learn about different design patterns.

- Build extensible software. The idyllic purpose of the side project is to create a **framework**.

---

### 😍 General APP flow
![app-skeleton-pre-dev.jpg](spike-time%2Fapp-skeleton-pre-dev.jpg)
---

### 🔌 Plugins

Plugins are compressed as **regular JAR files**, not as executable JAR files because Plugins are not stand-alone
applications.

Each JAR file includes a `**MANIFEST.MF**` file with different information about the plugin.
The Manifest file structure is not the final one, but it should be something as:

```makefile
Manifest-Version: 1.0
Created-By: Maven JAR Plugin 3.3.0
Build-Jdk-Spec: 17
Implementation-Title: demo-plugin
Implementation-Version: 0.0.1-SNAPSHOT
event-class: jimmy.plugin.demo.DemoPlugin
event-listeners: DemoEventListener1,DemoEventListener2
```

**🪖 Plugin system**

The **Plugin System** has 2 components:

1. Plugin Manager
2. Event system

The **responsibility** of the **Plugin Manager** is to load, instantiate, and manage plugins. The
plugin manager will read the Manifest file and register listeners to the correct event
publishers.

The **responsibility** of the **Event System** is to notify Plugins when certain events occur. Such events could be when
data is saved to the database or when certain data is ready for the plugin to consume.

**🗽 Plugin structure. A Common interface for all plugins.**

*TODO*

---

### 🍻 Event System

Let’s focus on these software design patterns:

- Prototype pattern
- Abstract Factory pattern
- Observable pattern

All of them are really helpful for event systems!

*TODO*

---

### 🧑🏼‍💻 Developer Info

Take care of the **`pom.xml`** content. We need to take a look to improve/understand this content.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <mainClass>jimmy.plugin.demo.DemoPluginApplication</mainClass>
                <layout>JAR</layout>
                <classifier>exec</classifier>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <configuration>
                <archive>
                    <manifestEntries>
                        <event-listeners>DemoEventListener1,DemoEventListener2</event-listeners>
                        <event-class>jimmy.plugin.demo.DemoPlugin</event-class>
                    </manifestEntries>
                </archive>
            </configuration>
        </plugin>
    </plugins>
</build>
```

To generate Plugin JAR files use the command below:

```bash
mvn package spring-boot:repackage
```