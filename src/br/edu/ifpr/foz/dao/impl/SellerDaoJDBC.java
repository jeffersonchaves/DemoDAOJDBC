package br.edu.ifpr.foz.dao.impl;

import br.edu.ifpr.foz.dbexceptions.DBException;
import br.edu.ifpr.foz.entities.Department;
import br.edu.ifpr.foz.entities.Seller;
import br.edu.ifpr.foz.dao.SellerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {

        try {

            String sql = "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, seller.getName());
            statement.setString(2, seller.getEmail());
            statement.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            statement.setDouble(4, seller.getBaseSalary());
            statement.setInt(5, seller.getDepartment().getId());

            Integer rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0){
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                seller.setId(resultSet.getInt(1));
            } else {
                throw new DBException("Unexpect Exception! No rows affected");
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Seller> findAll() {
        List<Seller> sellers = new ArrayList<>();


        try {

            String sql = "select seller.*, department.Name as depName " +
                    "from seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "ORDER BY seller.Name";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            Map<Integer, Department> map = new HashMap<>();

            while (resultSet.next()){

                Department department = map.get(resultSet.getInt("DepartmentId"));

                if (department == null){
                    department = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), department);
                }

                Seller seller = instantiateSeller(resultSet, department);

                sellers.add(seller);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sellers;
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

    public List<Seller> findByDepartment(Department department){

        List<Seller> sellers = new ArrayList<>();

        try {
            String sql = "select seller.*, department.Name as depName " +
                    "from seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.DepartmentId = ? " +
                    "ORDER BY seller.Name";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, department.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Seller seller = instantiateSeller(resultSet, department);
                sellers.add(seller);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sellers;

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
}
