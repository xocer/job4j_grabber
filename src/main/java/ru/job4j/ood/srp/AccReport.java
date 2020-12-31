package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class AccReport implements Report {
    private final Store store;
    private final Converter value;

    public AccReport(Store store, Converter value) {
        this.store = store;
        this.value = value;
    }

    public Converter getValue() {
        return value;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(value.convert(employee.getSalary()));
        }
        return text.toString();
    }
}
