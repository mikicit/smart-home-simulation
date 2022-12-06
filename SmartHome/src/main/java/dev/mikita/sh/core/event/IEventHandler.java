package dev.mikita.sh.core.event;

@FunctionalInterface
public interface IEventHandler {
    void handle(AEvent e);
}