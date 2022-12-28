package dev.mikita.sh.entity;

import dev.mikita.sh.entity.inhabitant.AInhabitant;

public interface IUsableObject {
    String getName();
    void use(AInhabitant inhabitant);
    void unUse(AInhabitant inhabitant);
    double getUsageTimeInHour();
    int getOperatingTimeInHours();
    int getHungerPerHour();
    int getLeisurePerHour();
    boolean isOn();
    boolean isOff();
    boolean isUsing();
    boolean isBroken();
    boolean isFixing();
}
