package br.ucsal.app.todo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.app.todo.dao.TarefaDAO;

/**
 * Servlet implementation class TarefasCompartilhadasController
 */
@WebServlet("/tarefacompartilhada")
public class TarefasCompartilhadasController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TarefaDAO dao = new TarefaDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String index = request.getParameter("usuariopage");

		System.out.println("SERVLET /tarefacompartilhada" + index);
		if (index != null) {
			long id = Long.parseLong(index);

			request.setAttribute("tarefas", dao.selectCompartilhamentoByID(id));

		}

		request.getRequestDispatcher("/private/compartilhamentopage.jsp").forward(request, response);

	}

}
