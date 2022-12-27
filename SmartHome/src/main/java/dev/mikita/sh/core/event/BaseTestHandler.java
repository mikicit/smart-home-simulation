package dev.mikita.sh.core.event;

public interface BaseTestHandler {
    void setNext(BaseTestHandler handler);
    void handle(AEvent e);
}
