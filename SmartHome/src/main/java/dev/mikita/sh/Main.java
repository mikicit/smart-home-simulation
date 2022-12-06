package dev.mikita.sh;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.EventDispatcher;
import dev.mikita.sh.event.SmokeInRoom;

public class Main {
    public static void main(String[] args) {
        SHSystem system = SHSystem.getInstance();
        system.init();
        EventDispatcher ed = system.getEventDispatcher();

        ed.addEventHandler(SmokeInRoom.class, "Room1", event -> System.out.println("Жопа"));
        ed.dispatchEvent(new SmokeInRoom(), "Room1");

        ed.addEventHandler(SmokeInRoom.class, "Room1", event -> System.out.println("Полная жопа"));
        ed.addEventHandler(SmokeInRoom.class, "Room1", event -> System.out.println("СЕССИЯ БЛЕАААААААААААТЬ"));

        ed.addEventHandler(SmokeInRoom.class, "Room4", event -> System.out.println("Дарина душка :>"));

        ed.dispatchEvent(new SmokeInRoom(), "Room4");
    }
}
