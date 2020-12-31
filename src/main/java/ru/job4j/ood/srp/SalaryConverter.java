package ru.job4j.ood.srp;

public class SalaryConverter implements Converter {

    @Override
    public double convert(double value) {
        return value / 70;
    }
}
