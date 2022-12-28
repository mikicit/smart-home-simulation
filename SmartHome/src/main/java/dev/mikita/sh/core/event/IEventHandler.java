package dev.mikita.sh.core.event;

public interface IEventHandler {
    void setNext(IEventHandler handler);
    void handle(AEvent e);
}