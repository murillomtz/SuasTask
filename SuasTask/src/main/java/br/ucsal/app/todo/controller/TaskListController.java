package br.ucsal.app.todo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import br.ucsal.app.todo.dao.TarefaDAO;
import br.ucsal.app.todo.model.Tarefa;

/**
 * Servlet implementation class TaskListController
 */
@WebServlet("/tarefas")
public class TaskListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	private TarefaDAO dao = new TarefaDAO();

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {



		request.setAttribute("tarefas",	dao.listAll());
		request.getRequestDispatcher("/private/page.jsp").forward(request, response);
	}

}