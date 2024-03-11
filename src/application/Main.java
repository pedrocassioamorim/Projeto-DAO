package application;

import entities.Department;
import entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Department obj = new Department(1, "Books");

        Seller pessoa = new Seller(01,"Jorge", "jorge@gmail.com", new Date(), 3000.0, obj);

        System.out.println(obj);
        System.out.println(pessoa);

    }
}