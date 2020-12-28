package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class ReportEngine  {
    private final Report report;

    public ReportEngine(Report report) {
        this.report = report;
    }

    public String generate(Predicate<Employee> filter) {
        return report.generate(filter);
    }
}
