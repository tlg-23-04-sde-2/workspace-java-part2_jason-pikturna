package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class SalariedEmployeeTest {
    // object under test, called a "fixture"
    private SalariedEmployee emp1;
    private SalariedEmployee emp2;

    @Before
    public void setUp() {
        emp1 = new SalariedEmployee("Mary", Date.valueOf("2020-10-10"), 1500.0);
        emp2 = new SalariedEmployee("Mary", Date.valueOf("2020-10-10"), 1500.0);
    }

    @Test
    public void equals_shouldReturnFalse_differentName_sameHireDate_sameSalary() {
        emp2.setName("DIFFERENT");
        assertNotEquals(emp1,emp2);
    }

    @Test
    public void equals_shouldReturnFalse_sameName_differentHireDate_sameSalary() {
        emp2.setHireDate(Date.valueOf("2021-11-11"));
        assertNotEquals(emp1, emp2);
    }

    @Test
    public void equals_shouldReturnFalse_sameName_sameHireDate_differentSalary() {
        emp2.setSalary(1600.0);
        assertNotEquals(emp1, emp2);
    }

    @Test
    public void equals_shouldReturnTrue_allPropertiesSame() {
        assertEquals(emp1, emp2);
    }

    @Test
    public void testPay() {
        System.out.println("testPay");
        assertEquals(1500.0, emp1.pay(), .001);
    }

    @Test
    public void testPayTaxes() {
        System.out.println("testPayTaxes");
        assertEquals(450.0, emp1.payTaxes(), .001);
    }

}