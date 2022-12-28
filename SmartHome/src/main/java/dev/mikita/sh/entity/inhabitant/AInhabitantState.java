package dev.mikita.sh.entity.inhabitant;

public abstract class AInhabitantState {
    // References
    protected AInhabitant inhabitant;

    // State
    protected long time = 0;

    public AInhabitantState(AInhabitant inhabitant) {
        this.inhabitant = inhabitant;
    }

    public abstract void update(long time);
}