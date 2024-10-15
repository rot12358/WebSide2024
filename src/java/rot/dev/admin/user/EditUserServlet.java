/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package rot.dev.admin.user;

import binh.dev.data.dao.DatabaseDao;
import binh.dev.data.dao.UserDAO;
import binh.dev.data.model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rot.dev.admin.BaseAdminServlet;

/**
 *
 * @author Admin
 */
public class EditUserServlet extends BaseAdminServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        UserDAO userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.find(userId);
        
        request.setAttribute("user", user);
        request.getRequestDispatcher("admin/user/edit.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        UserDAO userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.find(userId);
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        
        userDao.update(user);
        response.sendRedirect("IndexUserServlet");
    }
}