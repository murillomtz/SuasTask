package br.ucsal.app.todo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.app.todo.dao.TarefaDAO;
import br.ucsal.app.todo.dao.UsuarioDAO;
import br.ucsal.app.todo.model.Usuario;

/**
 * Servlet implementation class TarefaBarController
 */
@WebServlet("/tarefa/user")
public class TarefaBarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userLogin = request.getParameter("usuariopage");

		UsuarioDAO dao = new UsuarioDAO();

		Long id = Long.parseLong(userLogin);
		Usuario usuario = dao.findById(id);

		request.getSession().setAttribute("usuario", usuario);

		request.setAttribute("tarefas", dao.listTask(usuario.getId()));

		request.getRequestDispatcher("private/usuariopage.jsp").forward(request, response);

	}

}
