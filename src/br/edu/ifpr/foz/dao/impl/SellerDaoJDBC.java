package br.edu.ifpr.foz.dao.impl;

import br.edu.ifpr.foz.entities.Department;
import br.edu.ifpr.foz.entities.Seller;
import br.edu.ifpr.foz.dao.SellerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        try {

            String sql = "select seller.*, department.Name as depName " +
                    "from seller INNER JOIN department  " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){

                Department department = instantiateDepartment(resultSet);
                Seller seller = instantiateSeller(resultSet, department);

                return seller;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {

        Seller seller = new Seller();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString(2));
        seller.setEmail(resultSet.getString(3));
        seller.setBirthDate(resultSet.getDate(4));
        seller.setBaseSalary(resultSet.getDouble(5));
        seller.setDepartment(department);
        return seller;
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {

        return new Department(resultSet.getInt("DepartmentId"), resultSet.getString("depName"));

    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

}
