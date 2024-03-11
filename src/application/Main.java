package application;

import entities.Department;
import entities.Seller;
import entities.model.dao.DAOFactory;
import entities.model.dao.SellerDAO;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        SellerDAO sellerDAO = DAOFactory.createSellerDAO();

        System.out.println("====  TEST 1 - Seller findById  ======");

        Seller seller = sellerDAO.findById(3);

        System.out.println("================");
        System.out.println(seller);
        System.out.println("=================");


    }
}