/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package rot.dev;

import binh.dev.data.dao.DatabaseDao;
import binh.dev.data.dao.ProductDao;
import binh.dev.data.model.Product;
import binh.dev.util.Constants;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ShopServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();

        // Lấy danh sách tất cả sản phẩm
        List<Product> productList = productDao.findAll();

        // Xác định trang hiện tại
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Tổng số sản phẩm
        int total = productList.size();

        // Số lượng trang (làm tròn lên nếu cần)
        int numberPage = (int) Math.ceil((double) total / Constants.PER_PAGE);

        // Lấy sản phẩm cho trang hiện tại
        productList = productDao.getProducts((page - 1) * Constants.PER_PAGE, Constants.PER_PAGE);

        // Đặt các thuộc tính cho request
        request.setAttribute("page", page);
        request.setAttribute("total", total);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("productList", productList);

        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("shop.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
