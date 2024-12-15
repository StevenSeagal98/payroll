//package com.example.demo.models;
//
//public class Deductions {
//    private double grossPay;
//    private double netPay;
//    private double deductions;
//
//    public Deductions(double grossPay, double netPay, double deductions) {
//        this.grossPay = grossPay;
//        this.netPay = netPay;
//        this.deductions = deductions;
//    }
//
//    public double getGrossPay() {
//        return grossPay;
//    }
//
//    public void setGrossPay(double grossPay) {
//        this.grossPay = grossPay;
//    }
//
//    public double getNetPay() {
//        return netPay;
//    }
//
//    public void setNetPay(double netPay) {
//        this.netPay = netPay;
//    }
//
//    public double getDeductions() {
//        return deductions;
//    }
//
//    public void setDeductions(double deductions) {
//        this.deductions = deductions;
//    }
//}
package com.example.demo.models;

public class Deductions {
    private double grossPay;
    private double netPay;
    private double deductions;
    private double stateTax;
    private double federalTaxEmployee;
    private double socialSecurityTaxEmployee;
    private double medicareTaxEmployee;
    private double regularHours;
    private double overtimeHours;
    private double overtimePay;

    public Deductions(double grossPay, double netPay, double deductions, double stateTax, double federalTaxEmployee,
                      double socialSecurityTaxEmployee, double medicareTaxEmployee, double regularHours,
                      double overtimeHours, double overtimePay) {
        this.grossPay = grossPay;
        this.netPay = netPay;
        this.deductions = deductions;
        this.stateTax = stateTax;
        this.federalTaxEmployee = federalTaxEmployee;
        this.socialSecurityTaxEmployee = socialSecurityTaxEmployee;
        this.medicareTaxEmployee = medicareTaxEmployee;
        this.regularHours = regularHours;
        this.overtimeHours = overtimeHours;
        this.overtimePay = overtimePay;
    }

    // Getters and setters
    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getStateTax() {
        return stateTax;
    }

    public void setStateTax(double stateTax) {
        this.stateTax = stateTax;
    }

    public double getFederalTaxEmployee() {
        return federalTaxEmployee;
    }

    public void setFederalTaxEmployee(double federalTaxEmployee) {
        this.federalTaxEmployee = federalTaxEmployee;
    }

    public double getSocialSecurityTaxEmployee() {
        return socialSecurityTaxEmployee;
    }

    public void setSocialSecurityTaxEmployee(double socialSecurityTaxEmployee) {
        this.socialSecurityTaxEmployee = socialSecurityTaxEmployee;
    }

    public double getMedicareTaxEmployee() {
        return medicareTaxEmployee;
    }

    public void setMedicareTaxEmployee(double medicareTaxEmployee) {
        this.medicareTaxEmployee = medicareTaxEmployee;
    }

    public double getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(double regularHours) {
        this.regularHours = regularHours;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(double overtimePay) {
        this.overtimePay = overtimePay;
    }
}


