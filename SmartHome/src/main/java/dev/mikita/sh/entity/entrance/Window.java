package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.NormalWindEvent;
import dev.mikita.sh.event.StrongWindEvent;

import java.util.logging.Logger;

public class Window extends AEntrance {
    // Logger
    private static final Logger log = Logger.getLogger(Window.class.getName());

    public Window(Room room) {
        super(room);
        initEventHandlers();
    }

    private void initEventHandlers() {
        SHSystem.getInstance().getEventDispatcher().addEventHandler(NormalWindEvent.class, "global", this::normalWindEventHandler);
        SHSystem.getInstance().getEventDispatcher().addEventHandler(StrongWindEvent.class, "global", this::strongWindEventHandler);
    }

    private void normalWindEventHandler(AEvent event) {
        if (!isOpen()) {
            open();
        }
    }

    private void strongWindEventHandler(AEvent event) {
        if (isOpen()) {
            close();
        }
    }

    @Override
    public void open() {
        state = EntranceState.OPEN;

        log.info(String.format("The window was open in room \"%s\" [%s]",
                room.getName(), SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void close() {
        state = EntranceState.CLOSE;

        log.info(String.format("The window was close in room \"%s\" [%s]",
                room.getName(), SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
