package org.cdc.liberate;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;

@Getter
public class Liberate {
    private static Liberate instance;
    public static Liberate getInstance(){
        if (instance == null) instance = new Liberate();
        return instance;
    }
    @Setter
    private boolean agentMod;
    @Setter
    private boolean safeMod;

    private Logger mcreatorPluginLogger;


    private Liberate(){
    }

    public boolean isDevEnv(){
        return !Files.exists(Path.of("mcreator.exe"));
    }

    public Logger getMcreatorPluginLogger() {
        if (mcreatorPluginLogger == null) mcreatorPluginLogger = LogManager.getLogger("Liberate-Plugin");
        return mcreatorPluginLogger;
    }
}
