package dev.mikita.sh.entity.sensor.factories;

import dev.mikita.sh.entity.location.ILocation;
import dev.mikita.sh.entity.sensor.ASensor;

import java.util.List;

public interface ISensorFactory {
    ASensor create(String type, ILocation location);
    List<ASensor> getSensors();
}
