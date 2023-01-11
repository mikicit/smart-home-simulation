package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.AEventHandler;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.NormalWindEvent;
import dev.mikita.sh.event.StrongWindEvent;
import java.util.logging.Logger;

/**
 * Class representing the window.
 */
public class Window extends AEntrance {
    // Logger
    private static final Logger log = Logger.getLogger(Window.class.getName());

    public Window(Room room) {
        super(room);
        initEventHandlers();
    }

    /**
     * Initiates event handlers.
     */
    private void initEventHandlers() {
        SHSystem.getInstance().getEventDispatcher().addEventHandler(NormalWindEvent.class, "global", new AEventHandler() {
            @Override
            public void handle(AEvent e) {
                if (!Window.this.isOpen()) {
                    Window.this.open();
                }

                if (nextHandler != null) {
                    nextHandler.handle(e);
                }
            }
        });

        SHSystem.getInstance().getEventDispatcher().addEventHandler(StrongWindEvent.class, "global", new AEventHandler() {
            @Override
            public void handle(AEvent e) {
                if (Window.this.isOpen()) {
                    Window.this.close();
                }

                if (nextHandler != null) {
                    nextHandler.handle(e);
                }
            }
        });
    }

    /**
     * Opens the window.
     */
    @Override
    public void open() {
        state = EntranceState.OPEN;

        log.info(String.format("The window was opened in room \"%s\" [%s]",
                room.getName(), SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Closes the window.
     */
    @Override
    public void close() {
        state = EntranceState.CLOSE;

        log.info(String.format("The window was closed in room \"%s\" [%s]",
                room.getName(), SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
