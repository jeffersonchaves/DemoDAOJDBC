package br.edu.ifpr.foz.DAO;

import br.edu.ifpr.foz.Entities.Department;
import br.edu.ifpr.foz.Entities.Seller;

import java.util.List;

public interface SellerDAO {

    void insert(Seller seller);
    void update(Seller seller);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findAll();

}
