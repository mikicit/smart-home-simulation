package dev.mikita.sh.core.event;

public abstract class AEventHandler implements IEventHandler {
    protected IEventHandler nextHandler;

    @Override
    public void setNext(IEventHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
