package br.ucsal.app.todo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.app.todo.dao.TarefaDAO;
import br.ucsal.app.todo.dao.UsuarioDAO;
import br.ucsal.app.todo.model.Tarefa;

/**
 * Servlet implementation class CompartilhamentoController
 */
@WebServlet("/compartilhamento")
public class CompartilhamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TarefaDAO dao = new TarefaDAO();
	private UsuarioDAO udao = new UsuarioDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String index = request.getParameter("usuariopage");

		if (index != null) {
			long id = Long.parseLong(index);
			Tarefa tarefa = dao.findById(id);

			request.setAttribute("userCompartilhado", dao.selectUserOnCompartilhamento(id));
			request.setAttribute("tarefa", dao.findById(id));

		}

		request.setAttribute("usuarios", udao.listAll());

		request.getRequestDispatcher("/private/compartilhadas.jsp").forward(request, response);
	}

}
