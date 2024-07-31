package dev.mikita.sh.core.event;

/**
 * Interface representing event handler.
 */
public interface IEventHandler {
    /**
     * Sets next event handler.
     * @param handler the next event handler
     */
    void setNext(IEventHandler handler);

    /**
     * Handles event.
     * @param e event to handle
     */
    void handle(AEvent e);
}