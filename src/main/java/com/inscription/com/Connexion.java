package com.inscription.com;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Connexion
 */

@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		/**
		 * 	L'appel et initialisation de la classe connexion
		 */
		Connection conex = SingleConnexion.getConnexion();
		try {
			
			PreparedStatement prepare = conex.prepareStatement("select * from users where login = ? and password = ?");
			prepare.setString(1, login);
			prepare.setString(2, password);
			ResultSet result = prepare.executeQuery();
			if(result.next()) {
				session.setAttribute("name"	, result.getString("prenom"));	
				dispatcher = request.getRequestDispatcher("index.jsp");
			}else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("connexion.jsp");
			}
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
