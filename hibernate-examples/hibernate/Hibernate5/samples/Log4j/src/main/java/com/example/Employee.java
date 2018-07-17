package com.example;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private long id;

   @Column(name = "emp_name", nullable = false)
   private String empName;

   @Column(name = "emp_address")
   private String empAddress;

   @Column(name = "emp_mobile_nos")
   private String empMobileNum;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getEmpName() {
	return empName;
}

public void setEmpName(String empName) {
	this.empName = empName;
}

public String getEmpAddress() {
	return empAddress;
}

public void setEmpAddress(String empAddress) {
	this.empAddress = empAddress;
}

public String getEmpMobileNum() {
	return empMobileNum;
}

public void setEmpMobileNum(String empMobileNum) {
	this.empMobileNum = empMobileNum;
}
   

   
}
