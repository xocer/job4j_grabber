package ood.lsp;

import org.junit.Test;
import ru.job4j.ood.lsp.parking.Car;
import ru.job4j.ood.lsp.parking.Parking;
import ru.job4j.ood.lsp.parking.SimpleParking;
import ru.job4j.ood.lsp.parking.TruckCar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleParkingTest {

    @Test
    public void whenAddCarsNormal() {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new Car(321));
        parking.add(new Car(2));
        parking.add(new Car(44));
        assertThat(parking.getFreeCarsPlaces(), is(7));
    }

    @Test
    public void whenAddTruckCarsNormal() {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new Car(321));
        parking.add(new Car(2));
        parking.add(new Car(44));
        assertThat(parking.getFreeCarsPlaces(), is(2));
    }

    @Test
    public void whenRemoveCarsNormal() {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new Car(321));
        parking.add(new Car(2));
        parking.add(new Car(44));
        parking.remove(2);
        assertThat(parking.getFreeCarsPlaces(), is(8));
    }

    @Test
    public void whenRemoveTruckCarsNormal() {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new TruckCar(321, 4));
        parking.add(new TruckCar(2, 5));
        parking.add(new TruckCar(44, 3));
        parking.remove(2);
        assertThat(parking.getFreeCarsPlaces(), is(3));
    }

    @Test(expected = Exception.class)
    public void whenAddFail() {
        Parking parking = new SimpleParking(10, 0);
        parking.add(new Car(44));
        parking.add(new TruckCar(4, 2));
    }

    @Test(expected = Exception.class)
    public void whenRemoveFail() {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new Car(22));
        parking.add(new TruckCar(6, 5));
        parking.remove(22);
    }

    @Test
    public void whenTruckCarAddToCarsParking() {
        Parking parking = new SimpleParking(10, 0);
        parking.add(new Car(22));
        parking.add(new TruckCar(6, 5));
        assertThat(parking.getFreeCarsPlaces(), is(4));
    }
}
