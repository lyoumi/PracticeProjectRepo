package controller;

import model.Employee;
import model.EmployeeDao;
import model.Position;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lyoumi on 24.12.2016.
 */


public class EmployeeController {

    private EmployeeDao employeeDao;

    @Transactional
    public void createEmployee(JTextField fieldID, JTextField fieldN, JTextField fieldSN, JTextField fieldPN, JComboBox fieldP, JTextField fieldS){

        Employee employee = new Employee();
        employee.setId(Long.parseLong(fieldID.getText()));
        employee.setName(fieldN.getText());
        employee.setSurname(fieldSN.getText());
        employee.setPhoneNumber(fieldPN.getText());
        employee.setPosition(Position.valueOf(fieldP.getSelectedItem().toString()));
        employee.setSalary(Float.parseFloat(fieldS.getText()));

        employeeDao.save(employee);
    }

    public void removeEmployee(Long id){
        employeeDao.remove(employeeDao.getEmployeeById(id));
    }

    public List<Employee> findAll(){
        return employeeDao.finalAll();
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee  findEmployee(Long id){
        return employeeDao.getEmployeeById(id);
    }

    public void updateEmployee (JTextField fieldID, JTextField fieldN, JTextField fieldSN, JTextField fieldPN, JComboBox fieldP, JTextField fieldS){

        Employee employee = employeeDao.getEmployeeById(Long.parseLong(fieldID.getText()));

        employee.setId(Long.parseLong(fieldID.getText()));
        employee.setName(fieldN.getText());
        employee.setSurname(fieldSN.getText());
        employee.setPhoneNumber(fieldPN.getText());
        employee.setPosition(Position.valueOf(fieldP.getSelectedItem().toString()));
        employee.setSalary(Float.parseFloat(fieldS.getText()));

        employeeDao.update(employee);

    }
}
