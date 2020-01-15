package br.edu.ifpr.foz.DAO;

import br.edu.ifpr.foz.Entities.Department;

import java.util.List;

public interface DepartmentDAO {

    void insert(Department department);
    void update(Department department);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();

}
