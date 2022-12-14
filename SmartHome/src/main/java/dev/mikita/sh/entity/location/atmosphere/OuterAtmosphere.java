package dev.mikita.sh.entity.location.atmosphere;

public class OuterAtmosphere extends AAtmosphere {
    private double windSpeed;

    public double getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public void update(long time) {

    }
}
