package dev.mikita.sh.entity.inhabitant;

/**
 * Abstract class representing inhabitant's state
 */
public abstract class AInhabitantState {
    // References
    protected AInhabitant inhabitant;

    // State
    protected long time = 0;

    public AInhabitantState(AInhabitant inhabitant) {
        this.inhabitant = inhabitant;
    }

    /**
     * Update
     * @param time the time
     */
    public abstract void update(long time);
}