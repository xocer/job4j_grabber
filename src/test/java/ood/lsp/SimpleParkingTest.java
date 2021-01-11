package ood.lsp;

import org.junit.Test;
import ru.job4j.ood.lsp.parking.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleParkingTest {

    @Test
    public void whenAddCarsNormal() throws Exception {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new Car(321));
        parking.add(new Car(2));
        parking.add(new Car(44));
        assertThat(parking.getFreeCarsPlaces(), is(7));
    }

    @Test
    public void whenAddTruckCarsNormal() throws Exception {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new TruckCar(321, 2));
        parking.add(new TruckCar(2, 3));
        parking.add(new TruckCar(44, 4));
        assertThat(parking.getFreeTruckCarsPlaces(), is(2));
    }

    @Test
    public void whenRemoveCarsNormal() throws Exception {
        Parking parking = new SimpleParking(10, 5);
        Auto car = new Car(321);
        Auto car1 = new Car(2);
        Auto car2 = new Car(44);
        parking.add(car);
        parking.add(car1);
        parking.add(car2);
        parking.remove(car2);
        assertThat(parking.getFreeCarsPlaces(), is(8));
    }

    @Test
    public void whenRemoveTruckCarsNormal() throws Exception {
        Parking parking = new SimpleParking(10, 5);
        Auto truckCar = new TruckCar(321, 4);
        Auto truckCar1 = new TruckCar(2, 5);
        Auto truckCar2 = new TruckCar(44, 3);
        parking.add(truckCar);
        parking.add(truckCar1);
        parking.add(truckCar2);
        parking.remove(truckCar1);
        assertThat(parking.getFreeTruckCarsPlaces(), is(3));
    }

    @Test(expected = Exception.class)
    public void whenAddFail() throws Exception {
        Parking parking = new SimpleParking(1, 0);
        parking.add(new Car(44));
        parking.add(new TruckCar(4, 2));
    }

    @Test(expected = Exception.class)
    public void whenRemoveFail() throws Exception {
        Parking parking = new SimpleParking(10, 5);
        Auto car = new Car(22);
        Auto truckCar = new TruckCar(6, 5);
        Auto truckCar2 = new TruckCar(3, 5);
        parking.add(car);
        parking.add(truckCar);
        parking.remove(truckCar2);
    }

    @Test
    public void whenTruckCarAddToCarsParking() throws Exception {
        Parking parking = new SimpleParking(10, 0);
        parking.add(new Car(22));
        parking.add(new TruckCar(6, 5));
        assertThat(parking.getFreeCarsPlaces(), is(4));
    }
}