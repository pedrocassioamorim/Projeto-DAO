package application;

import entities.Department;
import entities.Seller;
import entities.model.dao.DAOFactory;
import entities.model.dao.SellerDAO;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Department obj = new Department(1, "Books");

        Seller pessoa = new Seller(01,"Jorge", "jorge@gmail.com", new Date(), 3000.0, obj);

        SellerDAO sellerDAO = DAOFactory.createSellerDAO();

        Seller seller = sellerDAO.findById(3);

        System.out.println("================");
        System.out.println(seller);
        System.out.println("=================");

        System.out.println(obj);
        System.out.println(pessoa);

    }
}