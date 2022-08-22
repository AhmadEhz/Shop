package shop.repository.customer;

import shop.entity.Customer;
import shop.exception.NotFoundException;
import shop.util.DbConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public Customer read(Customer customer) {
        String query = """
                select id, name from customer
                where username = ? and password = ?;
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query);
        ) {
            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());
            ResultSet rs = ps.executeQuery();
            customer.setId(rs.getLong("id"));
            customer.setName(rs.getString("name"));
            rs.close();
        } catch (SQLException e) {
            throw new NotFoundException("User not found!");
        }
        return customer;
    }

    @Override
    public void create(Customer customer) {
        String query = """
                insert into customer (name,username,password) 
                values (?,?,?)
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getUsername());
            ps.setString(3,customer.getPassword());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can't create customer.");
        }
    }

    @Override
    public void update(Customer customer) {
        String query = """
                update customer 
                set name = ? , username = ? , password = ?
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getUsername());
            ps.setString(3,customer.getPassword());
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException("can't update customer.");
        }
    }

    @Override
    public void delete(Customer customer) {
        String query = """
                delete from customer
                where id = ? and username = ? and password = ?
                """;
        try (PreparedStatement ps = DbConfig.getConnection().prepareStatement(query)) {
            ps.setLong(1,customer.getId());
            ps.setString(2,customer.getUsername());
            ps.setString(3,customer.getPassword());
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException("can't delete customer.");
        }
    }
}
