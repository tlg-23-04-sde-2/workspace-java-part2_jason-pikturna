/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.personnel;

import java.sql.Date;
import java.util.Map;

public class EmployeeFactory {
  
  // prevent direct instantiation - this is an all-static factory class
  private EmployeeFactory() {
  }
  
  /**
   * TODO: given the input map, create and return the correct object (with its properties set).
   * If the input map's "type" value is not "HE" or "SE", throw IllegalArgumentException with a suitable message.
   */
  public static Employee createEmployee(Map<String,String> inputMap) throws IllegalArgumentException {
    // return value
    Employee emp = null;

    // read the "type" out of the map
    String type =  inputMap.get("type");

    if (!"SE".equals(type) && !"HE".equals(type)) {
      throw new IllegalArgumentException("INVALID EMPLOYEE TYPE: " + type
              + ".  Valid types are: HE,SE.");
    }

    if("SE".equals(type)) {
      String name = inputMap.get("name");
      Date hireDate = Date.valueOf(inputMap.get("hireDate"));
      Double salary =Double.valueOf(inputMap.get("salary"));
      emp = new SalariedEmployee(name, hireDate, salary);

    }
    else if("HE".equals(type)){
      String name = inputMap.get("name");
      Date hireDate = Date.valueOf(inputMap.get("hireDate"));
      Double rate = Double.valueOf(inputMap.get("rate"));
      Double hours = Double.valueOf(inputMap.get("hours"));
      emp = new HourlyEmployee(name, hireDate, rate, hours);
    }
    return emp;
  }
}