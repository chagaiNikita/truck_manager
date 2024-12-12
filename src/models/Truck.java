package models;

import exceptions.StateException;
import states.OnBase;
import states.OnRepair;
import states.OnRoute;
import states.State;

public class Truck {

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Truck() {
        stateObj = new OnBase();
    }

    private int id;

    private String name;

    private String driver;

    private transient Driver driverObj;

    private String state;

    private transient State stateObj;
    private void initStateObj() {
        switch (state) {
            case "On Base":
                stateObj = new OnBase();
                break;
            case "On route":
                stateObj = new OnRoute();
                break;
            case "On repair":
                stateObj = new OnRepair();
                break;
            default:
                stateObj = new OnBase();
                state = "On Base";
                break;
        }
    }
    @Override
    public String toString() {
        return String.format("%-12s : %s%n%-12s : %s%n%-12s : %s%n%-12s : %s%n", "#", id, "Truck", name, "Driver", driver, "Truck state", state);
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStateObj(State stateObj) {
        this.stateObj = stateObj;
    }

    public void startDriving() {
        try {
            stateObj.startDriving(this);
            System.out.println("Грузовик отправился в путь");
        } catch (StateException e) {
            System.out.println(e.getMessage());
        }


    }

    public void startRepair() {
        try {
            stateObj.startRepair(this);
            System.out.println("Грузовик отправился на ремонт");
        } catch (StateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeDriver(Driver[] drivers) {
        try {
            stateObj.changeDriver(this, drivers);
            System.out.printf("Теперь грузовик %s, ведет водитель %s%n", name, driver);

        } catch (StateException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getDriver() {
        return driver;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public void setDriverObj(Driver driverObj) {
        this.driverObj = driverObj;
    }

    public Driver getDriverObj() {
        return driverObj;
    }

}
