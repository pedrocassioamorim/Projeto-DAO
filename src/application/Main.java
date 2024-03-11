package application;

import entities.Department;
import entities.Seller;
import entities.model.dao.DAOFactory;
import entities.model.dao.SellerDAO;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

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
        System.out.println("====  TEST 4 - Seller findAll  ======");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);

        sellerDAO.insert(newSeller);

        System.out.println("Inserted new iD = " + newSeller.getId());


    }
}