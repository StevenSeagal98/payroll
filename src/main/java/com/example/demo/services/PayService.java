package com.example.demo.services;

import com.example.demo.models.Deductions;
import com.example.demo.models.Employee;
import com.example.demo.models.HoursWorked;
import com.example.demo.repositories.HoursWorkedRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayService {

    private final double stateTaxRate = 0.0315;
    private final double federalTaxEmployeeRate = 0.0765;
    private final double federalTaxEmployerRate = 0.0765;
    private final double socialSecurityTaxEmployeeRate = 0.062;
    private final double socialSecurityTaxEmployerRate = 0.062;
    private final double medicareTaxEmployeeRate = 0.0145;
    private final double medicareTaxEmployerRate = 0.0145;

    private final HoursWorkedRepository hoursWorkedRepository;

    public PayService(HoursWorkedRepository hoursWorkedRepository) {
        this.hoursWorkedRepository = hoursWorkedRepository;
    }

    public Deductions calculatePayroll(Employee employee) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(7);
        List<HoursWorked> hoursWorkedList = hoursWorkedRepository.findAllByEmployeeAndDateBetween(employee, startOfWeek, today);

        double grossPay = calculateGrossPay(hoursWorkedList, employee);
        double deductions = calculateDeductions(grossPay);
        double netPay = grossPay - deductions;

        double stateTax = grossPay * stateTaxRate;
        double federalTaxEmployee = grossPay * federalTaxEmployeeRate;
        double socialSecurityTaxEmployee = grossPay * socialSecurityTaxEmployeeRate;
        double medicareTaxEmployee = grossPay * medicareTaxEmployeeRate;
        double regularHours = hoursWorkedList.stream().filter(h -> h.getDate().getDayOfWeek().getValue() < 6).mapToDouble(HoursWorked::getHours).sum();
        double overtimeHours = hoursWorkedList.stream().filter(h -> h.getHours() > 8 || h.getDate().getDayOfWeek().getValue() == 6).mapToDouble(HoursWorked::getHours).sum() - regularHours;
        double overtimePay = overtimeHours > 0 ? overtimeHours * calculateOvertimeRate(employee) : 0;

        return new Deductions(
                roundToTwoDecimalPlaces(grossPay),
                roundToTwoDecimalPlaces(netPay),
                roundToTwoDecimalPlaces(deductions),
                roundToTwoDecimalPlaces(stateTax),
                roundToTwoDecimalPlaces(federalTaxEmployee),
                roundToTwoDecimalPlaces(socialSecurityTaxEmployee),
                roundToTwoDecimalPlaces(medicareTaxEmployee),
                roundToTwoDecimalPlaces(regularHours),
                roundToTwoDecimalPlaces(overtimeHours),
                roundToTwoDecimalPlaces(overtimePay)
        );
    }

    private double calculateGrossPay(List<HoursWorked> hoursWorkedList, Employee employee) {
        double totalHours = hoursWorkedList.stream().mapToDouble(HoursWorked::getHours).sum();
        double hourlyRate = employee.getPayType().equals("Hourly") ? employee.getSalary() : (employee.getSalary() / 52) / 40;
        return totalHours * hourlyRate;
    }

    private double calculateDeductions(double grossPay) {
        double stateTax = grossPay * stateTaxRate;
        double federalTaxEmployee = grossPay * federalTaxEmployeeRate;
        double socialSecurityTaxEmployee = grossPay * socialSecurityTaxEmployeeRate;
        double medicareTaxEmployee = grossPay * medicareTaxEmployeeRate;

        double totalEmployeeDeductions = stateTax + federalTaxEmployee + socialSecurityTaxEmployee + medicareTaxEmployee;
        double totalEmployerDeductions = grossPay * (federalTaxEmployerRate + socialSecurityTaxEmployerRate + medicareTaxEmployerRate);

        return totalEmployeeDeductions + totalEmployerDeductions;
    }

    private double calculateOvertimeRate(Employee employee) {
        double hourlyRate = employee.getPayType().equals("Hourly") ? employee.getSalary() / 2080 : (employee.getSalary() / 52) / 40;
        return hourlyRate * 1.5;
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
