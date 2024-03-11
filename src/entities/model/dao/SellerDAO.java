package entities.model.dao;


import entities.Department;
import entities.Seller;

import java.util.List;

public interface SellerDAO {
    void insert(Seller pessoa);

    void update(Seller pessoa);

    void deleteById(Integer id);
    Seller findById(Integer id);

    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);
}
