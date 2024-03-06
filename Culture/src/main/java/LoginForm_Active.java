

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;

/**
 * Servlet implementation class LoginForm_Actice
 */
@WebServlet("/LoginForm_Active")
public class LoginForm_Active extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		System.out.println(session);
		System.out.println("LoginForm_Active");
		response.sendRedirect("loginForm.html");
	
	}


}
