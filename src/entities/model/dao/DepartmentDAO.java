package entities.model.dao;

import entities.Department;

import java.util.List;

public interface DepartmentDAO {

    void insert(Department obj);

    void update(Department obj);

    void deleteById(Integer id);
    Department findBYId(Integer id);

    List<Department> findAll();
}
