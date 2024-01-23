
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;



public class Laptop {

    private String brand;
    private int ram;
    private int hardDrive;
    private String os;
    private String color;

    public Laptop(String brand, int ram, int hardDrive, String os, String color) {

        this.brand = brand;
        this.ram = ram;
        this.hardDrive = hardDrive;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getHardDrive() {
        return hardDrive;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public static void main(String[] args) {

        // Создаем множество ноутбуков

        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Brand 1", 8, 512, "Windows", "Black"));
        laptops.add(new Laptop("Brand 2", 16, 1024, "Windows", "Silver"));
        laptops.add(new Laptop("Brand 3", 8, 256, "Linux", "White"));
        laptops.add(new Laptop("Brand 4", 8, 512, "MacOS", "Silver"));
        laptops.add(new Laptop("Brand 5", 16, 1024, "Windows", "Black"));

        // Запрашиваем критерии фильтрации

        Map<Integer, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("Введите номер критерия:");
        int criteria = scanner.nextInt();

        // Запрашиваем минимальное значение для указанного критерия

        switch (criteria) {

            case 1:
                System.out.println("Введите минимальное значение ОЗУ:");
                int minRam = scanner.nextInt();
                filters.put(criteria, minRam);
                break;

            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int minHardDrive = scanner.nextInt();
                filters.put(criteria, minHardDrive);
                break;

            case 3:
                System.out.println("Введите операционную систему:");
                String os = scanner.next();
                filters.put(criteria, os);
                break;

            case 4:
                System.out.println("Введите цвет:");
                String color = scanner.next();
                filters.put(criteria, color);
                break;

            default:
                System.out.println("Неверный номер критерия");
                return;
        }

        // Отфильтровываем ноутбуки и выводим результаты

        for (Laptop laptop : laptops) {

            boolean passFilter = true;

            for (Map.Entry<Integer, Object> entry : filters.entrySet()) {

                int filterCriteria = entry.getKey();
                Object filterValue = entry.getValue();
                switch (filterCriteria) {

                    case 1:
                        if (laptop.getRam() < (int)filterValue) {
                            passFilter = false;
                        }
                        break;

                    case 2:
                        if (laptop.getHardDrive() < (int)filterValue) {
                            passFilter = false;
                        }
                        break;

                    case 3:
                        if (!laptop.getOs().equals(filterValue)) {
                            passFilter = false;
                        }
                        break;

                    case 4:
                        if (!laptop.getColor().equals(filterValue)) {
                            passFilter = false;
                        }
                        break;
                }
            }

            if (passFilter) {
                System.out.println(laptop.getBrand());
            }
        }
    }
}
