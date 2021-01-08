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
    public void whenAddNormal() {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new Car());
        parking.add(new TruckCar());
        assertThat(parking.getFreePlaces(), is(13));
    }

    @Test
    public void whenRemoveNormal() {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new Car());
        parking.add(new TruckCar());
        parking.remove(2);
        assertThat(parking.getFreePlaces(), is(14));
    }

    @Test(expected = Exception.class)
    public void whenAddFail() {
        Parking parking = new SimpleParking(10, 0);
        parking.add(new Car());
        parking.add(new TruckCar());
    }

    @Test(expected = Exception.class)
    public void whenRemoveFail() {
        Parking parking = new SimpleParking(10, 5);
        parking.add(new Car());
        parking.add(new TruckCar());
        parking.remove(2);
    }
}
