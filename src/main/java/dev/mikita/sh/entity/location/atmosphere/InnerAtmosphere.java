package dev.mikita.sh.entity.location.atmosphere;

/**
 * Class representing the inner atmosphere (inside the house).
 */
public class InnerAtmosphere extends AAtmosphere {
    private final double DECREASE_TEMP_RATE_PER_HOUR = 2;

    public InnerAtmosphere() {
        this.temperature = 22;
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        temperature -= time * (DECREASE_TEMP_RATE_PER_HOUR / (3600D * 1000000000));
    }
}
