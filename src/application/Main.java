package application;

import entities.Department;
import entities.Seller;
import entities.model.dao.DAOFactory;
import entities.model.dao.SellerDAO;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SellerDAO sellerDAO = DAOFactory.createSellerDAO();

        System.out.println("====  TEST 1 - Seller findById  ======");

        Seller seller = sellerDAO.findById(3);

        System.out.println("================");
        System.out.println(seller);
        System.out.println();
        System.out.println("=================");
        System.out.println("====  TEST 2 - Seller findByDepartment  ======");
        Department department = new Department(2,null);
        List<Seller> list = sellerDAO.findByDepartment(department);
        for (Seller pessoa : list){
            System.out.println(pessoa);
        }
        System.out.println();
        System.out.println("====  TEST 3 - Seller findAll  ======");
        list = sellerDAO.findAll();
        for (Seller pessoa : list){
            System.out.println(pessoa);
        }

        System.out.println();
        System.out.println("====  TEST 4 - Seller Insert ======");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);

        sellerDAO.insert(newSeller);

        System.out.println("Inserted new iD = " + newSeller.getId());

        System.out.println();
        System.out.println("====  TEST 5 - Seller Update  ======");
        seller = sellerDAO.findById(1);
        seller.setName("Marta Waine");
        sellerDAO.update(seller);
        System.out.println("Update Completed!");

        System.out.println();
        System.out.println("====  TEST 6 - Seller Delete  ======");
        System.out.println("Enter Id to be deleted: ");
        int id = sc.nextInt();
        sellerDAO.deleteById(id);
        System.out.println("Delete Completed");


        sc.close();
    }
}