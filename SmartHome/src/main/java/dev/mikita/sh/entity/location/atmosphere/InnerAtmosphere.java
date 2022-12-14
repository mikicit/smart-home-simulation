package dev.mikita.sh.entity.location.atmosphere;

public class InnerAtmosphere extends AAtmosphere {
    private final double DECREASE_TEMP_RATE_PER_HOUR = 2;

    public InnerAtmosphere() {
        temperature = 22;
    }

    @Override
    public void update(long time) {
        temperature -= DECREASE_TEMP_RATE_PER_HOUR / 3600;

        System.out.println("Актуальная температура в комнате " + temperature);
    }
}