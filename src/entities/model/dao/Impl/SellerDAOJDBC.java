package entities.model.dao.Impl;

import db.DB;
import db.DBException;
import entities.Department;
import entities.Seller;
import entities.model.dao.SellerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDAOJDBC implements SellerDAO {

    private Connection conn;

    public SellerDAOJDBC (Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(Seller pessoa) {

    }

    @Override
    public void update(Seller pessoa) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName\n" +
                            "FROM seller INNER JOIN department\n" +
                            "ON seller.DepartmentId = department.Id\n" +
                            "WHERE seller.Id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                Seller pessoa = instantiateSeller(rs, dep);

                return pessoa;
            }
            return null;

        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {

        Seller pessoa = new Seller();
        pessoa.setId(rs.getInt("Id"));
        pessoa.setName(rs.getString("Name"));
        pessoa.setEmail(rs.getString("Email"));
        pessoa.setBaseSalary(rs.getDouble("BaseSalary"));
        pessoa.setBirthDate(rs.getDate("BirthDate"));
        pessoa.setDepartment(dep);
        return pessoa;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
