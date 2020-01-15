package br.edu.ifpr.foz;

import br.edu.ifpr.foz.Entities.Department;

public class Main {

    public static void main(String[] args) {
        Department department = new Department();
        department.setId(1);
        department.setName("Boks");

        System.out.println(department);
    }
}
