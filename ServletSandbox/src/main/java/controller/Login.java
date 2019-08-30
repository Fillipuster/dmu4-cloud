package controller;

import model.User;
import sun.security.timestamp.TSRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username").trim().toLowerCase();
        String pass = request.getParameter("password");

        if (user == null || pass == null) {
            request.setAttribute("error", "Invalid information");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        User login = User.verify(user, pass);
        if (login != null) {
            request.getSession().setAttribute("user", user);
            request.setAttribute("username", user);
            request.getRequestDispatcher("user.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            Object user = request.getSession().getAttribute("user");
            if (user != null) {
                request.setAttribute("username", user.toString());
                request.getRequestDispatcher("user.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void init() throws ServletException {
        new User("bucket", "123");
    }
}
