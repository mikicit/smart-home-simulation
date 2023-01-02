package dev.mikita.sh.core.config;

import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.FloorBuilder;
import dev.mikita.sh.entity.location.builder.HouseBuilder;
import dev.mikita.sh.entity.location.builder.RoomBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type Config builder.
 * <p>
 * An auxiliary static class for parsing and initialising the configuration.
 */
public class ConfigBuilder {
    private static JSONObject jo;

    /**
     * Build house.
     *
     * @param path the path
     * @return the house
     */
    public static House build(String path) {
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            jo = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HouseBuilder houseBuilder = new HouseBuilder();

        // External Sensors
        JSONArray externalSensors = null;

        try {
            externalSensors = jo.getJSONArray("sensors");
        } catch (Exception ignored) {}

        if (externalSensors != null) {
            for (Object externalSensor : externalSensors) {
                houseBuilder.addSensor(externalSensor.toString());
            }
        }

        // Floors
        JSONArray floors = null;

        try {
            floors = jo.getJSONArray("floors");
        } catch (Exception ignored) {}

        if (floors != null) {
            for (Object floorObject : floors) {
                JSONObject floor = (JSONObject) floorObject;
                FloorBuilder floorBuilder = houseBuilder.addFloor(floor.getInt("level"));

                // Rooms
                JSONArray rooms = null;

                try {
                    rooms = floor.getJSONArray("rooms");
                } catch (Exception ignored) {}

                if (rooms != null) {
                    for (Object roomObject : rooms) {
                        JSONObject room = (JSONObject) roomObject;
                        RoomBuilder roomBuilder = floorBuilder.addRoom(room.getString("name"));

                        // Entrances
                        JSONArray entrances = null;

                        try {
                            entrances = room.getJSONArray("entrances");
                        } catch (Exception ignored) {}

                        if (entrances != null) {
                            for (Object entranceObject : entrances) {
                                JSONObject entrance = (JSONObject) entranceObject;

                                roomBuilder.addEntrance(entrance.getString("type"), entrance.getInt("quantity"));
                            }
                        }

                        // Internal Sensors
                        JSONArray sensors = null;

                        try {
                            sensors = room.getJSONArray("sensors");
                        } catch (Exception ignored) {}

                        if (sensors != null) {
                            for (Object sensorObject : sensors) {
                                roomBuilder.addSensor(sensorObject.toString());
                            }
                        }

                        // Items
                        JSONArray items = null;

                        try {
                            items = room.getJSONArray("items");
                        } catch (Exception ignored) {}

                        if (items != null) {
                            for (Object itemObject : items) {
                                JSONObject item = (JSONObject) itemObject;

                                roomBuilder.addItem(item.getString("type"), item.getString("name"));
                            }
                        }

                        // Devices
                        JSONArray devices = null;

                        try {
                            devices = room.getJSONArray("devices");
                        } catch (Exception ignored) {}

                        if (devices != null) {
                            for (Object deviceObject : devices) {
                                JSONObject device = (JSONObject) deviceObject;

                                roomBuilder.addDevice(device.getString("type"), device.getString("name"));
                            }
                        }

                        // Persons
                        JSONArray persons = null;

                        try {
                            persons = room.getJSONArray("persons");
                        } catch (Exception ignored) {}

                        if (persons != null) {
                            for (Object personObject : persons) {
                                JSONObject person = (JSONObject) personObject;
                                PersonGender gender;

                                if (person.getString("gender").equals("MALE")) {
                                    gender = PersonGender.MALE;
                                } else if (person.getString("gender").equals("FEMALE")) {
                                    gender = PersonGender.FEMALE;
                                } else {
                                    throw new IllegalArgumentException("There is no such gender.");
                                }

                                roomBuilder.addPerson(person.getString("type"), person.getString("name"), gender);
                            }
                        }

                        // Pets
                        JSONArray pets = null;

                        try {
                            pets = room.getJSONArray("pets");
                        } catch (Exception ignored) {}

                        if (pets != null) {
                            for (Object petObject : pets) {
                                JSONObject pet = (JSONObject) petObject;

                                roomBuilder.addPet(pet.getString("type"), pet.getString("name"));
                            }
                        }
                    }
                }
            }
        }

        return houseBuilder.getResult();
    }
}
