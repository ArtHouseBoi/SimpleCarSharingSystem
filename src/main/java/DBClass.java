import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBClass {

    static final String JDBC_DRIVER = "org.h2.Driver";
    private  String DBUrl;


    public DBClass(String url) {

        DBUrl = url;

    }

    public void createTable  () {

        Connection con = null;
        Statement stm = null;


        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);

            //Execute a query
            stm = con.createStatement();
            String company = "CREATE TABLE IF NOT EXISTS company  " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    " name VARCHAR(255) NOT NULL UNIQUE)";
            stm.executeUpdate(company);

            String car = " CREATE TABLE IF NOT EXISTS car " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL UNIQUE, " +
                    "company_id INT NOT NULL, " +
                    "isRented BOOLEAN DEFAULT false, "+
                    "FOREIGN KEY (company_id) REFERENCES company(id)) ";
            stm.executeUpdate(car);

            String alter = "ALTER TABLE company ALTER COLUMN id RESTART WITH 1";
            stm.executeUpdate(alter);

            String cus = "CREATE TABLE IF NOT EXISTS customer " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL UNIQUE, " +
                    "rented_car_id INT DEFAULT NULL, " +
                    "FOREIGN KEY (rented_car_id) REFERENCES car(id)" +
                    "ON UPDATE SET NULL)";

            stm.executeUpdate(cus);

            String alter2 = "ALTER TABLE customer ALTER COLUMN id RESTART WITH 1";
            stm.executeUpdate(alter2);

            // Clean-up environment
            stm.close();
            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
    }

    public Map<Integer, String> getCompanyList() {

        Connection con = null;
        Statement stm = null;
        Map<Integer, String> list = new HashMap<>();

        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);


            try (Statement statement = con.createStatement()) {


                try (ResultSet company = statement.executeQuery("SELECT * FROM company")) {
                    while (company.next()){
                        list.put(company.getInt("id"), company.getString("name"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
        return list;
    }

    public void addCompany (String name) {

        Connection con = null;
        Statement stm = null;


        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);

            //Execute a query
            stm = con.createStatement();
            String sql ="INSERT INTO company(name) " +
                    "VALUES ('"+ name + "');";


            stm.executeUpdate(sql);

            // Clean-up environment
            stm.close();
            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }

    }

    public void addCar( String name, int id) {
        Connection con = null;
        Statement stm = null;


        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);

            //Execute a query
            stm = con.createStatement();
            String sql ="INSERT INTO car(name, company_id) " +
                    "VALUES ('"+ name + "', "+ id + ");";


            stm.executeUpdate(sql);

            // Clean-up environment
            stm.close();
            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }

    }

    public List<String> getCarList(int id) {

        Connection con = null;
        Statement stm = null;
        List<String> list = new ArrayList<>();

        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);


            try (Statement statement = con.createStatement()) {


                try (ResultSet car = statement.executeQuery("SELECT * FROM car WHERE company_id = " + id)) {
                    while (car.next()){
                        list.add(car.getString("name"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
        return list;
    }

    public void addCustomer(String name) {
        Connection con = null;
        Statement stm = null;


        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);

            //Execute a query
            stm = con.createStatement();
            String sql ="INSERT INTO customer(name) " +
                    "VALUES ('"+ name + "');";


            stm.executeUpdate(sql);

            // Clean-up environment
            stm.close();
            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
    }

    public Map<Integer, String> getCustomerList() {

        Connection con = null;
        Statement stm = null;
        Map<Integer, String> list = new HashMap<>();

        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);


            try (Statement statement = con.createStatement()) {


                try (ResultSet customer = statement.executeQuery("SELECT * FROM customer")) {
                    while (customer.next()){
                        list.put(customer.getInt("id"), customer.getString("name"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
        return list;

    }

    public List<String> getAvailableCarList(int id) {
        Connection con = null;
        Statement stm = null;
        List<String> list = new ArrayList<>();

        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);


            try (Statement statement = con.createStatement()) {


                try (ResultSet car = statement.executeQuery("SELECT * FROM car WHERE company_id = " + id + " AND isRented = false")) {
                    while (car.next()){
                        list.add(car.getString("name"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
        return list;
    }

    public void rent(String name, String nameCust) {
        Connection con = null;
        Statement stm = null;


        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);

            //Execute a query
            stm = con.createStatement();
            String sql ="UPDATE car SET isRented = true WHERE name = '" + name + "'";
            stm.executeUpdate(sql);
            int id = 0;

            try (Statement statement = con.createStatement()) {


                try (ResultSet car = statement.executeQuery("SELECT * FROM car WHERE name = '" + name + "'")) {
                    while (car.next()){
                        id =car.getInt("id");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String udt = "UPDATE customer SET rented_car_id =" + id + " WHERE name = '" + nameCust +"'" ;
            stm.executeUpdate(udt);

            // Clean-up environment
            stm.close();
            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
    }

    public boolean isRented(String name) {
        Connection con = null;
        Statement stm = null;
        boolean isRent = false;
        String x ="";

        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);


            try (Statement statement = con.createStatement()) {


                try (ResultSet customer = statement.executeQuery("SELECT * FROM customer WHERE name = '" + name + "'")) {
                    while (customer.next()){
                       x = customer.getString("rented_car_id");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (x != null) {
                isRent = true;
            }

            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
        return isRent;
    }

    public void returnCar(String name) {
        Connection con = null;
        Statement stm = null;
        int id = 0;


        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);


            try (Statement statement = con.createStatement()) {


                try (ResultSet customer = statement.executeQuery("SELECT * FROM customer WHERE name = '" + name + "'")) {
                    while (customer.next()){
                        id = customer.getInt("rented_car_id");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            stm = con.createStatement();
            String delt ="UPDATE customer SET rented_car_id = null " +
                    "WHERE name = '" + name + "'";
            stm.executeUpdate(delt);

            String sql ="UPDATE car SET isRented = false " +
                    "WHERE id = " + id;
            stm.executeUpdate(sql);

            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
    }

    public List<String> myCar (String name) {
        Connection con = null;
        Statement stm = null;
        int idCar = 0;
        int idCom = 0;
        List<String> list = new ArrayList<>();


        try {

            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            con = DriverManager.getConnection(DBUrl);

            con.setAutoCommit(true);


            try (Statement statement = con.createStatement()) {


                try (ResultSet customer = statement.executeQuery("SELECT * FROM customer WHERE name = '" + name + "'")) {
                    while (customer.next()){
                        idCar = customer.getInt("rented_car_id");
                    }
                }
                try (ResultSet car = statement.executeQuery("SELECT * FROM car WHERE id = " + idCar)) {
                    while (car.next()){
                         list.add(car.getString("name"));
                         idCom = car.getInt("company_id");
                    }
                }
                try (ResultSet company = statement.executeQuery("SELECT * FROM company WHERE id = " + idCom)) {
                    while (company.next()){
                        list.add(company.getString("name"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            con.close();

        } catch (SQLException se){
            // Handle errors for JDBC
            se.printStackTrace();
        }  catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (con != null ) con.close();
            } catch (SQLException se ){
                se.printStackTrace();
            }
        }
        return list;
    }


}




