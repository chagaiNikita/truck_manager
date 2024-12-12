package models;

public class Driver {
    private String id;
    private String name;
    private String truck;
    private boolean isFree;

    public void setFree(boolean free) {
        isFree = free;
    }

    public boolean isFree() {
        return isFree;
    }

    public Driver() {
        truck = "";
        isFree = true;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getName() {
        return name;
    }
}
