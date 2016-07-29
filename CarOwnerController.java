package com.carMax.servlets;

import com.carMax.data.DataJson;
import com.carMax.vehicles.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by perrythomson on 7/29/16.
 */
public class CarOwnerController extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {


        String uri = request.getRequestURI();
        System.out.println("Requested URI: "+uri);

        String jspName = uri.substring(uri.lastIndexOf('/')+1);
        System.out.println("JSP Name: "+jspName);

        if(jspName.equalsIgnoreCase("viewAllCars")) {
            ArrayList<Car> cars = DataJson.getCars();
            request.setAttribute("cars",cars);
        }  else if(jspName.equalsIgnoreCase("viewCar")) {
            String carManu = request.getParameter("carmanu");
            Car car = DataJson.getCar(carManu);
            request.setAttribute("car",car);
        } else if(jspName.equalsIgnoreCase("editCar")) {
            String carManu = request.getParameter("carmanu");
            Car car = DataJson.getCar(carManu);
            request.setAttribute("car",car);
        } else if(jspName.equalsIgnoreCase("addNewCar")) {
            Car saveNewCar = new Car();
            saveNewCar.setCarManu(request.getParameter("carmanu"));
            saveNewCar.setMake(request.getParameter("make"));
            saveNewCar.setModel(request.getParameter("model"));
            DataJson.setCar(saveNewCar);
            jspName = "viewAllCars";
        }
        RequestDispatcher view = request.getRequestDispatcher("/customers/"+jspName+".jsp");
        view.forward(request, response);
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request,response);
    }

}
