package dev.mikita.sh.core.event;

/**
 * Abstract class representing event handler.
 */
public abstract class AEventHandler implements IEventHandler {
    protected IEventHandler nextHandler;

    /**
     * Sets the next event handler.
     * @param nextHandler next handler
     */
    @Override
    public void setNext(IEventHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
