package dev.mikita.sh.entity.inhabitant;

public abstract class AInhabitantState {
    protected long time;
    protected AInhabitant inhabitant;

    public AInhabitantState(AInhabitant inhabitant) {
        this.inhabitant = inhabitant;
    }

    public abstract void update(long time);
}