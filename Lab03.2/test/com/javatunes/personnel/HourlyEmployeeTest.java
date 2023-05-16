package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class HourlyEmployeeTest {
   private HourlyEmployee emp;

    @Before
    public void setUp() {
        emp = new HourlyEmployee("James", Date.valueOf("2020-11-11"), 25.0, 30.0);
    }

    @Test
    public void testPay() {
        System.out.println("testPay");
        assertEquals(750.0, emp.pay(), .001);
    }

    @Test
    public void testPayTaxes() {
        System.out.println("testPayTaxes");
        assertEquals(187.5, emp.payTaxes(), .001);
    }
}