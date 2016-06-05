package gcc.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

import gcc.dao.DaoBase;
import gcc.dao.UserDao;
import gcc.po.UserBean;

/**
 * @author ธ๐ดิดิ
 * @version 1.0*/
public class RegisterBase2 extends HttpServlet{
	private static final long serialVersionUID = 1L;       
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        // TODO Auto-generated method stub      
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    	
    	String username= request.getParameter("username");
        String email= request.getParameter("email");
        String account= request.getParameter("account");
        String password= request.getParameter("password");
        UserBean user = new UserBean();
        user.setAccount(account);
        user.setPassword(password);
        user.setUserName(username);
        user.setEmail(email);
        
        System.out.println("username: " + account );
        System.out.println("email: " + password );
        
        Connection conn = DaoBase.getConnection(true);
        UserDao userDao = new UserDao(conn);
        boolean result = userDao.registerUpdate(user);
		if(result){
			System.out.println("succeed");
			response.getOutputStream().write("succeed".getBytes());
		}
		else{
			System.out.println("failed");
			response.getOutputStream().write("failed".getBytes());
		}
    }
}
