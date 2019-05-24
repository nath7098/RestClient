package com.rest.user;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

@WebServlet(name = "ServletUserLogin")
public class ServletUserLogin extends HttpServlet {
    private final String SERVICE_URL = "http://localhost:8080/ProjetRest_war_exploded/rest/userService/";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Form form = new Form();
        form.param("username", username);
        form.param("password", password);

        Client client = ClientBuilder.newClient();
        String callResult = client.target(SERVICE_URL+"login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(form,
                        MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                        String.class);


        JSONParser jsonParser = new JSONParser();
        String username2, password2, firstName, lastName;
        username2 = password2 = firstName = lastName = "";
        boolean isOk = false;
        try {
            JSONObject json = (JSONObject) jsonParser.parse(callResult);
            username2 = (String)json.get("username");
            password2 = (String)json.get("password");
            firstName = (String)json.get("firstName");
            lastName = (String)json.get("lastName");
            isOk = (boolean) json.get("response");
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
        }

        User user = new User(username2, password2, firstName, lastName);

        if (isOk) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("message", "Erreur lors de l'enregistrement de votre compte");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
