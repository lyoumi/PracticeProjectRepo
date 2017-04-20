package model;

import java.util.List;

/**
 * Created by lyoumi on 24.12.2016.
 */
public interface EmployeeDao {
    void save(Employee employee);

    Employee getEmployeeById(Long id);

    List finalAll();

    void remove (Employee employee);

    void update (Employee employee);
}
