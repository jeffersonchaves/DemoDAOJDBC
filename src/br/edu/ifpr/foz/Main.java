package br.edu.ifpr.foz;

import br.edu.ifpr.foz.entities.Department;
import br.edu.ifpr.foz.entities.Seller;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Department department = new Department();
        department.setId(1);
        department.setName("Boks");

        System.out.println(department);

        Seller seller = new Seller(1, "Bob", "Bob@gmail.com", new Date(), 3000.00, department);

        System.out.println(seller);
    }
}
