package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class AccReport implements Report {
    private final Store store;
    private final Currency value;

    public AccReport(Store store, Currency value) {
        this.store = store;
        this.value = value;
    }

    public Currency getValue() {
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
                    .append(employee.getSalary() / value.getCost());
        }
        return text.toString();
    }
}
