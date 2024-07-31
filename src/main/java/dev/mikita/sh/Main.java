package dev.mikita.sh;

import dev.mikita.sh.core.SHSystem;

public class Main {
    public static void main(String[] args) throws Exception {
        SHSystem system = SHSystem.getInstance();

        system.init("config/config2.json");
        system.start(1000,9000000000000000000L);
    }
}
