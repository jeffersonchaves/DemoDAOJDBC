package br.edu.ifpr.foz;

import br.edu.ifpr.foz.dao.DaoFactory;
import br.edu.ifpr.foz.dao.SellerDao;
import br.edu.ifpr.foz.entities.Department;
import br.edu.ifpr.foz.entities.Seller;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        List<Seller> sellers;

        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n=== TEST 2: Seller findByDepartment ===");
        sellers = sellerDao.findByDepartment(new Department(1, null));

        for (Seller obj: sellers ) {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 3: Seller findAll ===");
        sellers = sellerDao.findAll();

        for (Seller obj: sellers ) {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 4: Seller insert ===");

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date birthDate = simpleDateFormat.parse("26/04/1989");
//
//        Seller newSeller = new Seller();
//        newSeller.setName("Crhis Yellow");
//        newSeller.setEmail("crhisyellow@email.com");
//        newSeller.setBirthDate(birthDate);
//        newSeller.setBaseSalary(8000.0);
//        newSeller.setDepartment(new Department(1, null));
//
//        sellerDao.insert(newSeller);
//
//        System.out.println(newSeller);

        System.out.println("\n=== TEST 5: Seller insert ===");


        Seller sellerUpdated = sellerDao.findById(1);
        sellerUpdated.setName("Bruce Whayne");
        sellerUpdated.setEmail("bruce@email.com");

        sellerDao.update(sellerUpdated);

        System.out.println(sellerUpdated);

    }
}
