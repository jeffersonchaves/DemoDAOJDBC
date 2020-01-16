package br.edu.ifpr.foz;

import br.edu.ifpr.foz.dao.DaoFactory;
import br.edu.ifpr.foz.dao.SellerDao;
import br.edu.ifpr.foz.entities.Department;
import br.edu.ifpr.foz.entities.Seller;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

    }
}
