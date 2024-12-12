package states;

import exceptions.StateException;
import models.Driver;
import models.Truck;

public class OnBase implements State {
    @Override
    public void startDriving(Truck truck) throws StateException {

        if (truck.getDriverObj() == null) throw new StateException("Грузовик не может поехать без водителя");
        truck.setStateObj(new OnRoute());
        truck.setState("On route");


    }

    @Override
    public void startRepair(Truck truck) {
        truck.setStateObj(new OnRepair());
        truck.setState("On repair");
    }

    @Override
    public void changeDriver(Truck truck, Driver[] drivers) throws StateException {
        boolean driverCheck = false;
        for (Driver driver : drivers) {
            if (driver.isFree()) {
                driverCheck = true;
            }
        }
        if (driverCheck) {
            for (int i = 0; i < drivers.length; i++) {
                if (truck.getDriverObj() != null && !drivers[i].isFree()) {
                    drivers[i].setFree(true);
                    truck.setDriverObj(null);
                }
                if (drivers[i].isFree()) {
                    truck.setDriverObj(drivers[i]);
                    truck.setDriver(drivers[i].getName());
                    drivers[i].setFree(false);
                    drivers[i].setTruck(truck.getName());
                    break;
                }


            }
        } else {
            throw new StateException("Нет свободных водителей");
        }


    }
}
