package dev.mikita.sh.entity;

import dev.mikita.sh.entity.inhabitant.person.adult.Adult;

public interface UsableObject {
    void use(Adult person);
    void unUse(Adult person);
}
