package com.rest.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

@WebServlet(name = "ServletUserRegister")
public class ServletUserRegister extends HttpServlet {
    private final String SERVICE_URL = "http://localhost:8080/ProjetRest_war_exploded/rest/userService/";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("nom");
        String firstName = request.getParameter("prenom");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Form form = new Form();
        form.param("lastName", lastName);
        form.param("firstName", firstName);
        form.param("username", username);
        form.param("password", password);

        Client client = ClientBuilder.newClient();
        String callResult = client.target(SERVICE_URL+"register")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(form,
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                        String.class);


        if (callResult.contains("true")) {
            request.setAttribute("registerSuccess", "Inscription réussie, veuillez vous connecter");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("message", "Erreur lors de l'enrgeristrement de votre compte");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "");
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }
}
