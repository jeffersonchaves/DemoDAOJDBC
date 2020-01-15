package br.edu.ifpr.foz.dao;

import br.edu.ifpr.foz.dao.impl.SellerDaoImpl;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoImpl();
    }

}
