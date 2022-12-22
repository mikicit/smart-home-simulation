package dev.mikita.sh.entity;

import dev.mikita.sh.entity.inhabitant.AInhabitant;

public interface UsableObject {
    // TODO Add check on broken device
    void use(AInhabitant inhabitant);
    void unUse(AInhabitant inhabitant);
    double getUsageTimeInHour();
    int getOperatingTimeInHours();
    int getHungerPerHour();
    int getLeisurePerHour();
    boolean isUsing();
    String getName();
}
