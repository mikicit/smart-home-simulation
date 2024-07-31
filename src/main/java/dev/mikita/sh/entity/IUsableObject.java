package dev.mikita.sh.entity;

import dev.mikita.sh.entity.inhabitant.AInhabitant;

/**
 * Interface representing usable object.
 */
public interface IUsableObject {
    /**
     * Returns name of the object.
     * @return name
     */
    String getName();

    /**
     * Use the object.
     * @param inhabitant inhabitant that uses object
     */
    void use(AInhabitant inhabitant);

    /**
     * UnUse the object.
     * @param inhabitant inhabitant that unUses object
     */
    void unUse(AInhabitant inhabitant);

    /**
     * Returns time that object is supposed to be used.
     * @return time
     */
    double getUsageTimeInHour();

    /**
     * Checks if object is being used.
     * @return true if being used
     */
    boolean isUsing();

}
