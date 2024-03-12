package application;

import entities.Department;
import entities.model.dao.DAOFactory;
import entities.model.dao.DepartmentDAO;
import entities.model.dao.Impl.DepartmentDAOJDBC;

import java.util.List;
import java.util.Scanner;

public class ProgramTeste {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDAO departmentDAO = DAOFactory.createDepartmentDAO();


        // Testando o INSERT do Department: FUNCIONANDO!
        // Department Cama = new Department(40, "Cama, Mesa & Banho");
        // departmentDAO.insert(Cama);
        // System.out.println("Inserted Department: " + Cama.getId());

        // Testando o FindById do Department: FUNCIONANDO!
        //Department department = departmentDAO.findBYId(8);
        //System.out.println(department);

        // Testando o UPDATE do Department: FUNCIONANDO
        // Department department = departmentDAO.findBYId(5);
        // department.setName("Novo Departamento");
        // departmentDAO.update(department);
        // System.out.println("Update Completed!");

        // Testando o FINDALL do Department: FUNCIONANDO
        // List<Department> list = departmentDAO.findAll();
        // for (Department dpt : list){
        //     System.out.println(dpt);
        // }

        // Testando o DELETE do Department: FUNCIONANDO
        System.out.println("Enter Id of Department to be deleted: ");
        int n = sc.nextInt();
        departmentDAO.deleteById(n);
        System.out.println("Delete Completed!");


        sc.close();
    }
}
