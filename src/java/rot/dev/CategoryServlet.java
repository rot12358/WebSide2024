/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package rot.dev;

import binh.dev.data.dao.CategoryDao;
import binh.dev.data.dao.DatabaseDao;
import binh.dev.data.dao.ProductDao;
import binh.dev.data.model.Category;
import binh.dev.data.model.Product;
import binh.dev.util.Constants;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CategoryServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        
        List<Product> hotProductList = productDao.hot(Constants.NUMBER_LIMIT);
        List<Product> productList = new ArrayList<>();
        if(request.getParameter("property") != null && request.getParameter("order") != null){
            String property = request.getParameter("property");
            String order = request.getParameter("order");
            productList = productDao.filter(categoryId, property, order);
        }else{
          productList = productDao.findByCategory(categoryId); 
        }
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        Category category = categoryDao.find(categoryId);
        
        request.setAttribute("productList", productList);
        request.setAttribute("hotProductList", hotProductList);
        
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("category", category);
        request.getRequestDispatcher("category.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
