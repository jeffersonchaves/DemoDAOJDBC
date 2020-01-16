package br.edu.ifpr.foz.dao;

import br.edu.ifpr.foz.dao.impl.SellerDaoJDBC;
import br.edu.ifpr.foz.dbconnection.DBConnection;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DBConnection.getConnection());
    }

}
