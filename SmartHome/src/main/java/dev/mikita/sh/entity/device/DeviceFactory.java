package dev.mikita.sh.entity.device;

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

public class DeviceFactory {
    private static DeviceFactory instance;
    private final List<ADevice> devices = new ArrayList<>();

    private DeviceFactory() {}

    public static DeviceFactory getInstance() {
        if (instance == null) {
            instance = new DeviceFactory();
        }

        return instance;
    }

    public ADevice create(String type, Room room, String name) {
        ADevice device = switch (type) {
            case "AIR_CONDITIONER" -> new AirConditioner(room, name);
            case "FRIDGE" -> new Fridge(room, name);
            case "HEATER" -> new Heater(room, name);
            case "LIGHT" -> new Light(room, name);
            case "MICROWAVE" -> new Microwave(room, name);
            case "OVEN" -> new Oven(room, name);
            case "TV" -> new TV(room, name);
            case "WASHING_MACHINE" -> new WashingMachine(room, name);
            default -> throw new IllegalArgumentException("Type of device " + type + " was not found.");
        };

        this.devices.add(device);
        return device;
    }

    public List<ADevice> getDevices() {
        return this.devices;
    }
}
