package dev.mikita.sh.entity.inhabitant.person.adult;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.AEventHandler;
import dev.mikita.sh.core.task.Task;
import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultDeviceFixingState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultDeviceUsingState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;
import dev.mikita.sh.entity.inhabitant.person.child.Child;
import dev.mikita.sh.entity.inhabitant.pet.APet;
import dev.mikita.sh.entity.item.AItem;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.DeviceIsBrokenEvent;
import java.util.logging.Logger;

public class Adult extends APerson {
    // Logger
    private static final Logger log = Logger.getLogger(Adult.class.getName());

    public Adult(Room room, String name) {
        super(room, name);
        this.state = new AdultWaitingState(this);
        this.hungerIndicator = 100;
        this.leisureIndicator = 100;
        this.hungerPerHour = 10;
        this.leisurePerHour = 5;
        this.deviceBreakingChance = 0.1;

        initEventHandlers();
    }

    public void feedChild(Child child) {
        moveTo(child.getRoom());
        child.setHungerIndicator(100);
    }

    public void feedPet(APet pet) {
        moveTo(pet.getRoom());
        pet.setHungerIndicator(100);
    }

    public void playWithPet(APet pet) {
        pet.setLeisureIndicator(85);
    }

    public void fixDevice(ADevice device) {
        usingObject = device;
        changeState(new AdultDeviceFixingState(this));
    }

    public void completeFixingDevice(ADevice device) {
        usingObject = null;
        changeState(new AdultWaitingState(this));
    }

    @Override
    public void useObject(IUsableObject object) {
        this.usingObject = object;

        if (object instanceof ADevice) {
            SHSystem.getInstance().getReportSystem().getActivityAndUsageReport().deviceCount(this, (ADevice) object);
        } else {
            SHSystem.getInstance().getReportSystem().getActivityAndUsageReport().itemCount(this, (AItem) object);
        }

        changeState(new AdultDeviceUsingState(this));
    }

    @Override
    public void unUseObject(IUsableObject object) {
        this.usingObject = null;
        changeState(new AdultWaitingState(this));
    }

    @Override
    public void toBreakDevice(ADevice device) {
        this.usingObject = device;
        changeState(new AdultWaitingState(this));
    }

    @Override
    public void update(long time) {
        this.state.update(time);
    }

    @Override
    public void changeState(AInhabitantState state) {
        this.state = state;
    }

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

//        SHSystem.getInstance().getEventDispatcher().addEventHandler(NormalTemperatureEvent.class, room.getName(), new AEventHandler() {
//            @Override
//            public void handle(AEvent e) {
//
//
//                if (nextHandler != null) {
//                    nextHandler.handle(e);
//                }
//            }
//        });
    }
}