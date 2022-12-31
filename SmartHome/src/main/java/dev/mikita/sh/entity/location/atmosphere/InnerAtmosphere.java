package dev.mikita.sh.entity.location.atmosphere;

public class InnerAtmosphere extends AAtmosphere {
    private final double DECREASE_TEMP_RATE_PER_HOUR = 2;

    public InnerAtmosphere() {
        this.temperature = 22;
    }

    @Override
    public void update(long time) {
        temperature -= time * (DECREASE_TEMP_RATE_PER_HOUR / (3600D * 1000000000));
    }
}
