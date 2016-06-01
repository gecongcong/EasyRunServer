package gcc.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gcc.dao.DaoBase;
import gcc.dao.UserDao;
import gcc.po.UserBean;

/**
 * @author ธ๐ดิดิ
 * @version 1.0*/
public class LoginServer extends HttpServlet{
	private static final long serialVersionUID = 1L;       
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub      
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    	
    	String account= request.getParameter("username");
        String password= request.getParameter("password");
        
        System.out.println("account: " + account );
        System.out.println("password: " + password );
        Connection conn = DaoBase.getConnection(true);
        UserDao userDao = new UserDao(conn);
        response.getOutputStream().write("succeed".getBytes());
        try {
        	UserBean user = userDao.findUser(account,password);
        	System.out.println("user account"+user.getAccount());
        	System.out.println("user password"+user.getPassword());
			if(user!=null)
				response.getOutputStream().write("succeed".getBytes());
			else response.getOutputStream().write("failed".getBytes());
		} catch (SQLException e) {
			e.printStackTrace();
			response.getOutputStream().write("failed".getBytes());
		}
    }
}
