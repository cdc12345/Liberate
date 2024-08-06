package org.cdc.liberate;

import net.mcreator.io.OS;
import net.mcreator.plugin.JavaPlugin;
import net.mcreator.plugin.Plugin;
import net.mcreator.plugin.PluginLoader;
import net.mcreator.plugin.events.ApplicationLoadedEvent;
import net.mcreator.util.DesktopUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class MCreatorPluginMain extends JavaPlugin {

    public MCreatorPluginMain(Plugin plugin) {
        super(plugin);
        final var liberate = Liberate.getInstance();
        if (!liberate.isAgentMod()){
            if (OS.getOS() == OS.WINDOWS){
                liberate.getMcreatorPluginLogger().info("Working Directory: "+new File("").getAbsolutePath());
                this.addListener(ApplicationLoadedEvent.class,a->{
                    try {
                        if (liberate.isDevEnv()){
                            liberate.getMcreatorPluginLogger().warn("Running in the dev environment,sleep");
                        } else {
                            Files.copy(Objects.requireNonNull(PluginLoader.INSTANCE.getResourceAsStream("mcreatorA.bat")),Path.of("mcreatorA.bat"),StandardCopyOption.REPLACE_EXISTING);
                            Files.copy(Objects.requireNonNull(PluginLoader.INSTANCE.getResourceAsStream("mcreator.bat")),Path.of("mcreator.bat"),StandardCopyOption.REPLACE_EXISTING);
                            Files.copy(plugin.getFile().toPath(),Path.of("agent.jar"), StandardCopyOption.REPLACE_EXISTING);
                            DesktopUtils.openSafe(new File("mcreatorA.bat"));
                            System.exit(-1);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                liberate.getMcreatorPluginLogger().warn("Running in not supported system,sleep!");
            }
        }
    }
}
