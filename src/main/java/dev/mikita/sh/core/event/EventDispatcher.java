package dev.mikita.sh.core.event;
import dev.mikita.sh.core.SHSystem;

import java.io.IOException;
import java.util.*;

/**
 * Class for working with events and event handlers.
 */
public class EventDispatcher {
    private final Map<String, LinkedList<IEventHandler>> eventHandlers = new HashMap<>();

    /**
     * Adds new event handler.
     * @param event event to be handled
     * @param context event's context
     * @param handler handler to add
     */
    public void addEventHandler(Class<? extends AEvent> event, String context, IEventHandler handler) {
        String key = event + context;

        if (eventHandlers.containsKey(key)) {
            handler.setNext(eventHandlers.get(key).getLast());
            eventHandlers.get(key).add(handler);
        } else {
            LinkedList<IEventHandler> handlers = new LinkedList<>(Collections.singletonList(handler));
            eventHandlers.put(key, handlers);
        }
    }

    /**
     * Removes existing event handler.
     * @param event handled event
     * @param context event's context
     * @param handler handler to remove
     */
    public void removeEventHandler(Class<? extends AEvent> event, String context, IEventHandler handler) {
        String key = event + context;

        if (eventHandlers.containsKey(key)) {
            eventHandlers.get(key).remove(handler);

            if (eventHandlers.get(key).size() == 0) {
                eventHandlers.remove(key);
            }
        }
    }

    /**
     * Dispatches event.
     * @param e event to dispatch
     * @param context event's context (e.g. location)
     */
    public void dispatchEvent(AEvent e, String context) {
        String key = e.getClass() + context;

        try {
            SHSystem.getInstance().getReportSystem().getEventReport().generateReport(e);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if (eventHandlers.containsKey(key)) {
            Objects.requireNonNull(eventHandlers.get(key).peekLast()).handle(e);
        }
    }
}
