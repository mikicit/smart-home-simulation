package dev.mikita.sh.core.event;

import java.util.*;

public class EventDispatcher {
    private final Map<String, LinkedList<BaseTestHandler>> eventHandlers = new HashMap<>();

    public void addEventHandler(Class<? extends AEvent> event, String context, BaseTestHandler handler) {
        String key = event + context;

        if (eventHandlers.containsKey(key)) {
            handler.setNext(eventHandlers.get(key).getLast());
            eventHandlers.get(key).add(handler);
        } else {
            LinkedList<BaseTestHandler> handlers = new LinkedList<>(Collections.singletonList(handler));
            eventHandlers.put(key, handlers);
        }
    }

    public void removeEventHandler(Class<? extends AEvent> event, String context, BaseTestHandler handler) {
        String key = event + context;

        if (eventHandlers.containsKey(key)) {
            eventHandlers.get(key).remove(handler);

            if (eventHandlers.get(key).size() == 0) {
                eventHandlers.remove(key);
            }
        }
    }

    public void dispatchEvent(AEvent e, String context) {
        String key = e.getClass() + context;

//        if (eventHandlers.containsKey(key)) {
//            for (IEventHandler handler : eventHandlers.get(key)) {
//                handler.handle(e);
//            }
//        } else {
//            List<IEventHandler> handlers = new ArrayList<>();
//            eventHandlers.put(key, handlers);
//        }

        if (eventHandlers.containsKey(key)) {
            Objects.requireNonNull(eventHandlers.get(key).peekLast()).handle(e);
        }
    }
}
