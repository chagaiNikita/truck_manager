
import models.Driver;
import models.Truck;
import utils.FileUtil;

import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        run();


    }


    public static void run() {
        Truck[] trucks = FileUtil.readFile(Paths.get("src/data/trucks.json"));
        Driver[] drivers = FileUtil.readFileDr(Paths.get("src/data/drivers.json"));
        printDrivers(drivers);
        while (true) {

            printTrucksStart(trucks);
            start(pickTruck(trucks), drivers);
        }



    }

    public static void printDrivers(Driver[] drivers) {
        System.out.printf(" %-3s| %-10s | %-15s%n", "#", "Driver", "Truck");
        System.out.println("-".repeat(4) + "|" + "-".repeat(12) + "|" + "-".repeat(15));
        for (int i = 0; i < drivers.length; i++) {
            System.out.printf(" %-3s| %-10s | %-15s%n", i+1, drivers[i].getName(), drivers[i].getTruck());
        }
        System.out.println();
    }

    public static void printTrucksStart(Truck[] trucks) {
        System.out.printf(" %-3s| %-16s | %11s | %-7s%n", "#", "Truck", "Driver", "State");
        System.out.println("-".repeat(4) + "|" + "-".repeat(18) + "|" + "-".repeat(13) + "|" + "-".repeat(11));
        for (int i = 0; i < trucks.length; i++) {
            System.out.printf(" %-3s| %-16s | %11s | %-7s%n", i + 1, trucks[i].getName(), trucks[i].getDriver(), trucks[i].getState());
        }
    }

    public static Truck pickTruck(Truck[] trucks) throws InputMismatchException {

        while (true) {
            System.out.print("Выберите грузовик по порядковому номеру: ");
            try {
                int truck = sc.nextInt();
                if (truck < 1 || truck > trucks.length) throw new NumberFormatException();
                return trucks[truck - 1];
            } catch (InputMismatchException e) {
                System.out.println("Номер не может содержать букв повторите попытку");
            } catch (NumberFormatException e) {
                System.out.println("Грузовик не найден повторите попытку");
            }
            sc.nextLine();
        }

    }

    public static void start(Truck truck, Driver[] drivers) {
        System.out.println(truck);
        System.out.println("Выберите действие\n" +
                "1 - Сменить водителя\n" +
                "2 - Отправить на маршрут\n" +
                "3 - Отправить на ремонт");
        while (true) {
            try {
                int makeAction = sc.nextInt();
                if (makeAction == 1) {
                    truck.changeDriver(drivers);
                    break;
                } else if (makeAction == 2) {
                    truck.startDriving();
                    break;
                } else if (makeAction == 3) {
                    truck.startRepair();
                    break;
                } else {
                    System.out.println("Действие не обнаружено повторите попытку");
                }
            } catch (InputMismatchException e) {
                System.out.println("Строка не должна содержать букв повторите попытку");
            }
            sc.nextLine();
        }


    }
}




