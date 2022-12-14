package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.entity.location.House;

public abstract class AExternalSensor extends ASensor {
    protected House house;

    public AExternalSensor(House house) {
        this.house = house;
    }

    public House getHouse() {
        return house;
    }
}
