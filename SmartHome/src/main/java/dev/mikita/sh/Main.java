package dev.mikita.sh;

import dev.mikita.sh.core.SHSystem;

public class Main {
    public static void main(String[] args) {
        SHSystem system = SHSystem.getInstance();
        system.init();
    }
}
