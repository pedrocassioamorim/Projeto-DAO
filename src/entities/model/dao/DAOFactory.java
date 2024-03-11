package entities.model.dao;

import db.DB;
import entities.model.dao.Impl.DepartmentDAOJDBC;
import entities.model.dao.Impl.SellerDAOJDBC;

public class DAOFactory {
    public static SellerDAO createSellerDAO(){
        return new SellerDAOJDBC(DB.getConnection());
    }

    public static DepartmentDAO createDepartmentDAO(){
        return new DepartmentDAOJDBC();
    }
}
