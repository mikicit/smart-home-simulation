package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.DeviceFactory;
import dev.mikita.sh.entity.device.ariConditioner.AirConditioner;
import dev.mikita.sh.entity.device.fridge.Fridge;
import dev.mikita.sh.entity.device.heater.Heater;
import dev.mikita.sh.entity.device.light.Light;
import dev.mikita.sh.entity.device.microwave.Microwave;
import dev.mikita.sh.entity.device.oven.Oven;
import dev.mikita.sh.entity.device.tv.TV;
import dev.mikita.sh.entity.device.washingMachine.WashingMachine;
import dev.mikita.sh.entity.location.Room;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory {
    private static ItemFactory instance;
    private final List<AItem> items = new ArrayList<>();

    private ItemFactory() {}

    public static ItemFactory getInstance() {
        if (instance == null) {
            instance = new ItemFactory();
        }

        return instance;
    }

    public AItem create(String type, Room room, String name) throws IllegalArgumentException {
        AItem item = switch (type) {
            case "BIKE" -> new Bike(room, name);
            case "CAR" -> new Car(room, name);
            case "GUITAR" -> new Guitar(room, name);
            case "SKIS" -> new Skis(room, name);
            default -> throw new IllegalArgumentException("Type of item " + type + " was not found.");
        };

        this.items.add(item);
        return item;
    }

    public List<AItem> getItems() {
        return this.items;
    }
}
