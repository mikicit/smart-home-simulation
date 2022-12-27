package dev.mikita.sh.core.event;

public abstract class BaseTestHandlerA implements BaseTestHandler {
    protected BaseTestHandler nextHandler;

    @Override
    public void setNext(BaseTestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
