package org.example.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.CityDAO;
import org.example.model.City;

import java.io.IOException;

import java.io.Serial;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/main/*")
public class MainServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private CityDAO cityDAO;
    public void init() {
        cityDAO = new CityDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getRequestURI();
    //String action = request.getServletPath();
        System.out.println(action);
        action = action.substring(action.lastIndexOf("/")).toLowerCase();
        System.out.println(action);
        try {
            switch (action) {
                case "/new" -> {showNewForm(request, response);  System.out.println("point2reached");}
                case "/insert" -> insertCity(request, response);
                case "/delete" -> deleteCity(request, response);
                case "/edit" -> showEditForm(request, response);
                case "/update" -> updateCity(request, response);
               case "/test" -> test(request,response);
                default -> listCity(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<City> listCity = cityDAO.selectAllCities();
        request.setAttribute("listCity", listCity);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/city-list.jsp");
        dispatcher.forward(request, response);
    }
    private void test(HttpServletRequest request, HttpServletResponse response)
        throws  IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
        dispatcher.forward(request,response);
    }
    private void insertCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        int population = Integer.parseInt(request.getParameter("population"));
        String country = request.getParameter("country");
        City newCity = new City(name, population, country);
        cityDAO.insertCity(newCity);
        response.sendRedirect("list");
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/city-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        City existingCity = cityDAO.selectCity(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/city-form.jsp");
        request.setAttribute("city", existingCity);
        dispatcher.forward(request, response);

    }

    private void updateCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int population = Integer.parseInt(request.getParameter("population"));
        String country = request.getParameter("country");

        City book = new City(id, name, population, country);
        cityDAO.updateCity(book);
        response.sendRedirect("list");
    }

    private void deleteCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cityDAO.deleteCity(id);
        response.sendRedirect("list");

    }

}
