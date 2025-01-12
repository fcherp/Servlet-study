package org.example.dao;
import org.example.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/cities?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "root";

    private static final String INSERT_CITIES_SQL = "INSERT INTO cities" + "  (cityname, population, country) VALUES "
            + " (?, ?, ?);";

    private static final String SELECT_CITY_BY_ID = "select id,cityname,population,country from cities where id =?";
    private static final String SELECT_ALL_CITIES = "select * from cities";
    private static final String DELETE_CITIES_SQL = "delete from cities where id = ?;";
    private static final String UPDATE_CITIES_SQL = "update cities set cityname = ?,population= ?, country =? where id = ?;";

    public CityDAO() {
    }

    protected Connection getConnection() throws SQLException{
        Connection connection1 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection1 = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection1;
    }

    public void insertCity(City city) throws SQLException {
        System.out.println(INSERT_CITIES_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITIES_SQL)) {
            preparedStatement.setString(1, city.getCityName());
            preparedStatement.setInt(2, city.getPopulation());
            preparedStatement.setString(3, city.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public City selectCity(int id) {
        City city1 = null;

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String cityname = rs.getString("cityname");
                int population = rs.getInt("population");
                String country = rs.getString("country");
                city1 = new City(id, cityname, population, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return city1;
    }

    public List<City> selectAllCities() {


        List<City> cities = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cityname = rs.getString("cityname");
                int population = rs.getInt("population");
                String country = rs.getString("country");
                cities.add(new City(id, cityname, population, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cities;
    }

    public boolean deleteCity(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CITIES_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public boolean updateCity(City city) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CITIES_SQL)) {
            statement.setString(1, city.getCityName());
            statement.setInt(2, city.getPopulation());
            statement.setString(3, city.getCountry());
            statement.setInt(4, city.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }

        }
    }
}
