package entities.model.dao.Impl;

import db.DB;
import db.DBException;
import entities.Department;
import entities.Seller;
import entities.model.dao.SellerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDAOJDBC implements SellerDAO {

    private Connection conn;

    public SellerDAOJDBC (Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(Seller pessoa) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("INSERT INTO seller " +
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, pessoa.getName());
            st.setString(2, pessoa.getEmail());
            st.setDate(3, new java.sql.Date(pessoa.getBirthDate().getTime()));
            st.setDouble(4, pessoa.getBaseSalary());
            st.setInt(5, pessoa.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    pessoa.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else{
                throw new DBException("Unexpected Error! No rows affected! ");
            }


        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);

        }



    }

    @Override
    public void update(Seller pessoa) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("UPDATE seller " +
                            "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
                            "WHERE  Id = ?");

            st.setString(1, pessoa.getName());
            st.setString(2, pessoa.getEmail());
            st.setDate(3, new java.sql.Date(pessoa.getBirthDate().getTime()));
            st.setDouble(4, pessoa.getBaseSalary());
            st.setInt(5, pessoa.getDepartment().getId());
            st.setInt(6, pessoa.getId());

            st.executeUpdate();

        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);

        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
            st.setInt(1, id);

            st.executeUpdate();


        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }

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
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName " +
                            "FROM seller INNER JOIN department " +
                            "ON seller.DepartmentId  = department.Id " +
                            "ORDER BY Name");


            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller pessoa = instantiateSeller(rs, dep);
                list.add(pessoa);
            }
            return list;

        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId  = department.Id " +
                    "WHERE DepartmentId = ? " +
                    "ORDER BY Name");

            st.setInt(1, department.getId());

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller pessoa = instantiateSeller(rs, dep);
                list.add(pessoa);
            }
            return list;

        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }
}
