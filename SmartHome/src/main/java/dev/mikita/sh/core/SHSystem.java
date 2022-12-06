package dev.mikita.sh.core;

import dev.mikita.sh.core.event.EventDispatcher;

public class SHSystem {
    private static SHSystem instance;
    private EventDispatcher eventDispatcher;

    private SHSystem() {}

    public static SHSystem getInstance() {
        if (instance == null) {
            instance = new SHSystem();
        }
        return instance;
    }

    public void init() {
        eventDispatcher = new EventDispatcher();
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }
}
