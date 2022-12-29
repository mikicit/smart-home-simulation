package dev.mikita.sh.entity;

import dev.mikita.sh.entity.inhabitant.AInhabitant;

public interface IUsableObject {
    String getName();
    void use(AInhabitant inhabitant);
    void unUse(AInhabitant inhabitant);
    double getUsageTimeInHour();
    int getOperatingTimeInHours();
    boolean isOn();
    boolean isOff();
    boolean isUsing();
    boolean isBroken();
    boolean isFixing();
}
