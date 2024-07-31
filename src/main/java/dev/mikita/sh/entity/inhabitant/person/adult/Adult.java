package dev.mikita.sh.entity.inhabitant.person.adult;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.AEventHandler;
import dev.mikita.sh.core.task.Task;
import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.inhabitant.person.adult.state.*;
import dev.mikita.sh.entity.inhabitant.person.child.Child;
import dev.mikita.sh.entity.inhabitant.pet.APet;
import dev.mikita.sh.entity.item.AItem;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.entity.sensor.ASensor;
import dev.mikita.sh.entity.sensor.SmokeSensor;
import dev.mikita.sh.entity.sensor.WaterSensor;
import dev.mikita.sh.event.*;
import java.util.logging.Logger;

/**
 * Class representing the adult.
 */
public class Adult extends APerson {
    // Logger
    private static final Logger log = Logger.getLogger(Adult.class.getName());

    public Adult(Room room, String name, PersonGender gender) {
        super(room, name);
        this.state = new AdultWaitingState(this);
        this.deviceBreakingChance = 0.1;
        this.gender = gender;

        initEventHandlers();
    }

    /**
     * Feeds the child.
     * @param child the child
     */
    public void feedChild(Child child) {
        if (!room.equals(child.getRoom())) {
            moveTo(child.getRoom());
        }

        log.info(String.format("Adult \"%s\" fed the child \"%s\" [%s]",
                name,
                child.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Changes child's diapers.
     * @param child the child
     */
    public void changeDiapers(Child child) {
        if (!room.equals(child.getRoom())) {
            moveTo(child.getRoom());
        }

        log.info(String.format("Adult \"%s\" changed \"%s's\" diaper [%s]",
                name,
                child.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Feed the pet.
     * @param pet the pet
     */
    public void feedPet(APet pet) {
        if (!room.equals(pet.getRoom())) {
            moveTo(pet.getRoom());
        }

        log.info(String.format("Adult \"%s\" fed the pet \"%s\" [%s]",
                name,
                pet.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Plays with pet.
     * @param pet the pet
     */
    public void playWithPet(APet pet) {
        if (!room.equals(pet.getRoom())) {
            moveTo(pet.getRoom());
        }

        log.info(String.format("Adult \"%s\" played with pet \"%s\" [%s]",
                name,
                pet.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Fixes water leak.
     * @param room the room
     */
    public void fixWaterLeak(Room room) {
        if (!this.room.equals(room)) {
            moveTo(room);
        }

        for (ASensor sensor : room.getSensors()) {
            if (sensor instanceof WaterSensor) {
                sensor.resetState();
            }
        }

        log.info(String.format("Adult \"%s\" fixed a water leak in the room \"%s\" [%s]",
                name,
                room.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Puts out the fire.
     * @param room the room
     */
    public void putOutTheFire(Room room) {
        if (!this.room.equals(room)) {
            moveTo(room);
        }

        for (ASensor sensor : room.getSensors()) {
            if (sensor instanceof SmokeSensor) {
                sensor.resetState();
            }
        }

        log.info(String.format("Adult \"%s\" put out the fire in the room \"%s\" [%s]",
                name,
                room.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Fixes the device.
     * @param device the device
     */
    public void fixDevice(ADevice device) {
        Documentation doc = device.getDocumentation();
        usingObject = device;
        changeState(new AdultDeviceFixingState(this));
        doc.fixDevice(device);
    }

    /**
     * Completes fixing the device.
     * @param device the device
     */
    public void completeFixingDevice(ADevice device) {
        device.setTime(0);
        usingObject = null;
        changeState(new AdultWaitingState(this));

        log.info(String.format("%s \"%s\" fixed the object \"%s\" [%s]",
                this.getClass().getSimpleName(),
                this.getName(),
                device.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Use the object (device, item).
     * @param object the object
     */
    public void useObject(IUsableObject object) {
        this.usingObject = object;

        if (object instanceof ADevice) {
            SHSystem.getInstance().getReportSystem().getActivityAndUsageReport().deviceCount(this, (ADevice) object);
        } else {
            SHSystem.getInstance().getReportSystem().getActivityAndUsageReport().itemCount(this, (AItem) object);
        }

        changeState(new AdultDeviceUsingState(this));
    }

    /**
     * UnUse the object.
     * @param object the object
     */
    public void unUseObject(IUsableObject object) {
        this.usingObject = null;
        changeState(new AdultWaitingState(this));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.state.update(time);
    }

    /**
     * Initiates event handlers.
     */
    private void initEventHandlers() {
        SHSystem.getInstance().getEventDispatcher().addEventHandler(DeviceIsBrokenEvent.class, "global", new AEventHandler() {
            @Override
            public void handle(AEvent e) {
                if (Adult.this.state instanceof AdultWaitingState) {
                    if (!Adult.this.getRoom().equals(e.getLocation())) {
                        Adult.this.moveTo((Room) e.getLocation());
                    }
                    ((ADevice) e.getSource()).fix(Adult.this);
                }

                if (nextHandler != null) {
                    nextHandler.handle(e);
                } else {
                    SHSystem.getInstance().getTaskSystem().addTask(new Task(e));
                }
            }
        });

        SHSystem.getInstance().getEventDispatcher().addEventHandler(PoopedChildEvent.class, "global", new AEventHandler() {
            @Override
            public void handle(AEvent e) {
                    if (Math.random() <= 0.40 && !(Adult.this.state instanceof AdultWorkingState)) {
                        log.info(String.format("Adult \"%s\" doesn't want to change \"%s's\" diaper [%s]",
                                name,
                                e.getSource().getName(),
                                SHSystem.getInstance().getSimulation().getFormattedTime()));

                        if (nextHandler != null) {
                            nextHandler.handle(e);
                        } else {
                            SHSystem.getInstance().getTaskSystem().addTask(new Task(e));
                        }

                        return;
                    }


                    if (!(Adult.this.state instanceof AdultWaitingState)
                            && !(Adult.this.state instanceof AdultWorkingState)
                            && !(Adult.this.state instanceof AdultDeviceFixingState)
                            && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                        Adult.this.changeState(new AdultWaitingState(Adult.this));
                    }

                    if (!(Adult.this.state instanceof AdultWorkingState)
                            && !(Adult.this.state instanceof AdultDeviceFixingState)
                            && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                        ((Child) e.getSource()).changeDiaper(Adult.this);
                    }
                }
        });

        SHSystem.getInstance().getEventDispatcher().addEventHandler(HungryChildEvent.class, "global", new AEventHandler() {
            @Override
            public void handle(AEvent e) {
                    if (Math.random() <= 0.40 && !(Adult.this.state instanceof AdultWorkingState)) {
                        log.info(String.format("Adult \"%s\" doesn't want to feed the baby \"%s\" [%s]",
                                name,
                                e.getSource().getName(),
                                SHSystem.getInstance().getSimulation().getFormattedTime()));

                        if (nextHandler != null) {
                            nextHandler.handle(e);
                        } else {
                            SHSystem.getInstance().getTaskSystem().addTask(new Task(e));
                        }

                        return;
                    }


                    if (!(Adult.this.state instanceof AdultWaitingState)
                            && !(Adult.this.state instanceof AdultWorkingState)
                            && !(Adult.this.state instanceof AdultDeviceFixingState)
                            && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                        Adult.this.changeState(new AdultWaitingState(Adult.this));
                    }

                    if (!(Adult.this.state instanceof AdultWorkingState)
                            && !(Adult.this.state instanceof AdultDeviceFixingState)
                            && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                        ((Child) e.getSource()).feed(Adult.this);
                    }
                }
        });

        SHSystem.getInstance().getEventDispatcher().addEventHandler(HungryPetEvent.class, "global", new AEventHandler() {
            @Override
            public void handle(AEvent e) {
                    if (Math.random() <= 0.40 && !(Adult.this.state instanceof AdultWorkingState)) {
                        log.info(String.format("Adult \"%s\" doesn't want to feed the pet \"%s\" [%s]",
                                name,
                                e.getSource().getName(),
                                SHSystem.getInstance().getSimulation().getFormattedTime()));

                        if (nextHandler != null) {
                            nextHandler.handle(e);
                        } else {
                            SHSystem.getInstance().getTaskSystem().addTask(new Task(e));
                        }

                        return;
                    }


                    if (!(Adult.this.state instanceof AdultWaitingState)
                            && !(Adult.this.state instanceof AdultWorkingState)
                            && !(Adult.this.state instanceof AdultDeviceFixingState)
                            && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                        Adult.this.changeState(new AdultWaitingState(Adult.this));
                    }

                    if (!(Adult.this.state instanceof AdultWorkingState)
                            && !(Adult.this.state instanceof AdultDeviceFixingState)
                            && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                        ((APet) e.getSource()).feed(Adult.this);
                    }
                }
        });

        SHSystem.getInstance().getEventDispatcher().addEventHandler(BoredPetEvent.class, "global", new AEventHandler() {
            @Override
            public void handle(AEvent e) {
                    if (Math.random() <= 0.20 && !(Adult.this.state instanceof AdultWorkingState)) {
                        log.info(String.format("Adult \"%s\" doesn't want to play with pet \"%s\" [%s]",
                                name,
                                e.getSource().getName(),
                                SHSystem.getInstance().getSimulation().getFormattedTime()));

                        if (nextHandler != null) {
                            nextHandler.handle(e);
                        } else {
                            SHSystem.getInstance().getTaskSystem().addTask(new Task(e));
                        }

                        return;
                    }

                    if (!(Adult.this.state instanceof AdultWaitingState)
                            && !(Adult.this.state instanceof AdultWorkingState)
                            && !(Adult.this.state instanceof AdultDeviceFixingState)
                            && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                        Adult.this.changeState(new AdultWaitingState(Adult.this));
                    }

                    if (!(Adult.this.state instanceof AdultWorkingState
                            && !(Adult.this.state instanceof AdultDeviceFixingState)
                            && !(Adult.this.state instanceof AdultDeviceUsingState))) {
                        ((APet) e.getSource()).play(Adult.this);
                    }
                }
        });

        SHSystem.getInstance().getEventDispatcher().addEventHandler(WaterInRoomEvent.class, "global", new AEventHandler() {
            @Override
            public void handle(AEvent e) {
                    if (Adult.this.state instanceof AdultSleepingState) {
                        if (Math.random() <= 0.20) {
                            log.info(String.format("Adult \"%s\" didn't wake up during a water leak in the room \"%s\" [%s]",
                                    name,
                                    e.getLocation().getName(),
                                    SHSystem.getInstance().getSimulation().getFormattedTime()));

                            if (nextHandler != null) {
                                nextHandler.handle(e);
                            } else {
                                SHSystem.getInstance().getTaskSystem().addTask(new Task(e));
                            }

                            return;
                        }
                    }

                    if (!(Adult.this.state instanceof AdultWaitingState)
                            && !(Adult.this.state instanceof AdultWorkingState)) {
                        Adult.this.changeState(new AdultWaitingState(Adult.this));
                    }

                    if (!(Adult.this.state instanceof AdultWorkingState)) {
                        ((Room) e.getLocation()).fixWaterLeak(Adult.this);
                        ((ASensor) e.getSource()).resetState();
                    }
                }
        });

        SHSystem.getInstance().getEventDispatcher().addEventHandler(SmokeInRoomEvent.class, "global", new AEventHandler() {
            @Override
            public void handle(AEvent e) {
                    if (Adult.this.state instanceof AdultSleepingState) {
                        if (Math.random() <= 0.20) {
                            log.info(String.format("Adult \"%s\" didn't wake up during a fire in the room \"%s\" [%s]",
                                    name,
                                    e.getLocation().getName(),
                                    SHSystem.getInstance().getSimulation().getFormattedTime()));

                            if (nextHandler != null) {
                                nextHandler.handle(e);
                            } else {
                                SHSystem.getInstance().getTaskSystem().addTask(new Task(e));
                            }

                            return;
                        }
                    }

                    if (!(Adult.this.state instanceof AdultWaitingState)
                            && !(Adult.this.state instanceof AdultWorkingState)) {
                        Adult.this.changeState(new AdultWaitingState(Adult.this));
                    }

                    if (!(Adult.this.state instanceof AdultWorkingState)) {
                        ((Room) e.getLocation()).putOutTheFire(Adult.this);
                        ((ASensor) e.getSource()).resetState();
                    }
                }
        });
    }
}