package com.inscription.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InscriptionServelet
 */
@WebServlet("/inscription")
public class InscriptionServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = null;
		
		/**
		 * 	L'appel et initialisation de la classe connexion
		 */
		Connection conex = SingleConnexion.getConnexion();
		try {
			PreparedStatement prepared = conex.prepareStatement("insert into users(prenom,nom,login,password) values(?,?,?,?)");
			prepared.setString(1, prenom);
			prepared.setString(2, nom);
			prepared.setString(3, login);
			prepared.setString(4, password);
			
			int row = prepared.executeUpdate();
			if(row > 0) {
				dispatcher = request.getRequestDispatcher("inscription.jsp");
				request.setAttribute("status", "success");
			}else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conex.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
