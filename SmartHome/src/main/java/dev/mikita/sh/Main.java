package dev.mikita.sh;

import dev.mikita.sh.core.SHSystem;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) throws Exception {
        // Logging Config
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logger/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e);
        }

        SHSystem system = SHSystem.getInstance();

        system.init("config/config1.json");
        system.start(10000,9000000000000000000L);
    }
}
