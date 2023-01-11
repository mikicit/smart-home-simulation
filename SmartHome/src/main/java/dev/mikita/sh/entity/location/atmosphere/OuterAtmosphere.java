package dev.mikita.sh.entity.location.atmosphere;

/**
 * Class representing the outer atmosphere (outside the house).
 */
public class OuterAtmosphere extends AAtmosphere {
    // State
    private double windSpeed = 8;
    private boolean windSpeedIsRising = true;

    // Constants
    private final double WIND_SPEED_INCREASE_PER_HOUR = 1;
    private final double MIN_WIND_SPEED = 6;
    private final double MAX_WIND_SPEED = 15;

    public OuterAtmosphere() {
        this.windSpeed = 8;
    }

    /**
     * Returns the wind speed.
     * @return speed
     */
    public double getWindSpeed() {
        return this.windSpeed;
    }

    /**
     * Sets the wind speed.
     * @param windSpeed speed to set
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        if (windSpeedIsRising) {
            if (windSpeed >= MAX_WIND_SPEED) {
                windSpeedIsRising = false;
            } else {
                windSpeed += time * (WIND_SPEED_INCREASE_PER_HOUR / (3600D * 1000000000));
            }
        } else {
            if (windSpeed <= MIN_WIND_SPEED) {
                windSpeedIsRising = true;
            } else {
                windSpeed -= time * (WIND_SPEED_INCREASE_PER_HOUR / (3600D * 1000000000));
            }
        }
    }
}
