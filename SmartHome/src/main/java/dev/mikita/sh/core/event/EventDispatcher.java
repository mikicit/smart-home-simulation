package dev.mikita.sh.core.event;

import java.util.*;

public class EventDispatcher {
    private final Map<String, List<IEventHandler>> eventHandlers = new HashMap<>();

    public void addEventHandler(Class<? extends AEvent> event, String context, IEventHandler handler) {
        String key = event + context;

        if (eventHandlers.containsKey(key)) {
            eventHandlers.get(key).add(handler);
        } else {
            List<IEventHandler> handlers = new ArrayList<>(Collections.singletonList(handler));
            eventHandlers.put(key, handlers);
        }
    }

    public void removeEventHandler(Class<? extends AEvent> event, String context, IEventHandler handler) {
        String key = event + context;

        if (eventHandlers.containsKey(key)) {
            eventHandlers.get(key).remove(handler);
        }
    }

    public void dispatchEvent(AEvent e, String context) {
        String key = e.getClass() + context;

        if (eventHandlers.containsKey(key)) {
            for (IEventHandler handler : eventHandlers.get(key)) {
                handler.handle(e);
            }
        } else {
            List<IEventHandler> handlers = new ArrayList<>();
            eventHandlers.put(key, handlers);
        }
    }
}
