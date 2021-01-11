package ru.job4j.ood.lsp.parking;

import java.util.NoSuchElementException;

public class SimpleParking implements Parking {
    private final Auto[] cars;
    private final Auto[] truckCars;
    private int freeCarsPlaces;
    private int freeTruckCarsPlaces;

    public SimpleParking(int carsParkingSize, int truckCarsParkingSize) {
        this.cars = new Auto[carsParkingSize];
        this.truckCars = new Auto[truckCarsParkingSize];
        freeCarsPlaces = carsParkingSize;
        freeTruckCarsPlaces = truckCarsParkingSize;
    }

    @Override
    public boolean add(Auto auto) throws Exception {
        if (auto.size() > 1) {
            if (freeTruckCarsPlaces == 0) {
                return addTruckCarToCarsParking(auto, auto.size());
            } else {
                return addTruckCar(auto);
            }
        } else if (freeCarsPlaces != 0) {
            return addCar(auto);
        }
        return false;
    }

    //*** Добавляем грузовой автомобиль ***
    private boolean addTruckCar(Auto auto) {
        for (int i = 0; i < truckCars.length; i++) {
            if (truckCars[i] == null) {
                truckCars[i] = auto;
                freeTruckCarsPlaces--;
                return true;
            }
        }
        return false;
    }

    //*** Добавляем легковой автомобиль ***
    private boolean addCar(Auto auto) throws Exception {
        if (freeCarsPlaces == 0) {
            throw new Exception("Извините, мест нет");
        }
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                cars[i] = auto;
                freeCarsPlaces--;
                return true;
            }
        }
        return false;
    }

    //*** Метод для добавления грузового автомобиля на парковку с легковыми
    private boolean addTruckCarToCarsParking(Auto auto, int size) throws Exception {
        if (freeCarsPlaces < size) {
            throw new Exception("Извините, мест нет");
        }

        int count = 0;
        for (int i = 0; i < cars.length; i++) {
            if (count == size) {
                for (int j = i - 1; j > i - size; j--) {
                    cars[j] = auto;
                }
                freeCarsPlaces -= size;
                return true;
            } else if (cars[i] == null) {
                count++;
            } else {
                count = 0;
            }
        }
        return false;
    }

    //*** Если грузовик -> проходимся по массиву, если там нет, идем проверять массив легковых ***
    @Override
    public void remove(Auto auto) {
        Auto tmp = null;
        if (auto.size() > 1) {
            for (int i = 0; i < truckCars.length; i++) {
                if (auto.getId() == truckCars[i].getId()) {
                    tmp = truckCars[i];
                    truckCars[i] = null;
                    freeTruckCarsPlaces++;
                    break;
                }
            }
            if (tmp == null) {
                for (int i = 0; i < cars.length; i++) {
                    if (auto.getId() == cars[i].getId()) {
                        for (int j = i; j < i + auto.size(); j++) {
                            cars[j] = null;
                        }
                        freeTruckCarsPlaces += auto.size();
                        break;
                    }
                }
                if (tmp == null) {
                    throw new NoSuchElementException("Такого автомобиля на парковке нет");
                }
            }
        } else {
            for (int i = 0; i < cars.length; i++) {
                if (auto.getId() == cars[i].getId()) {
                    tmp = cars[i];
                    cars[i] = null;
                    freeCarsPlaces++;
                    break;
                }
            }
            if (tmp == null) {
                throw new NoSuchElementException("Такого автомобиля на парковке нет");
            }
        }
    }

    @Override
    public int getFreeTruckCarsPlaces() {
        return freeTruckCarsPlaces;
    }

    @Override
    public int getFreeCarsPlaces() {
        return freeCarsPlaces;
    }

}
