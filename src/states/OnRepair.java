package states;

import exceptions.StateException;
import models.Driver;
import models.Truck;

import java.util.Random;

public class OnRepair implements State {

    @Override
    public void startDriving(Truck truck) throws StateException {
        Random rnd = new Random();
        truck.setStateObj(new OnBase());
        truck.setState("On Base");

        if (rnd.nextInt(2) + 1 == 1) {
            if (truck.getDriverObj() == null)
                throw new StateException("Грузовик не может поехать без водителя и был возвращен на базу");
            truck.setStateObj(new OnRoute());
            truck.setState("On route");
        } else {

            throw new StateException("Грузовик был возвращен на базу");
        }
    }

    @Override
    public void startRepair(Truck truck) throws StateException {
        throw new StateException("Грузовик уже в ремонте");
    }

    @Override
    public void changeDriver(Truck truck, Driver[] drivers) throws StateException {
        throw new StateException("Грузовик на ремонте сменить водителя невозможно");
    }
}
