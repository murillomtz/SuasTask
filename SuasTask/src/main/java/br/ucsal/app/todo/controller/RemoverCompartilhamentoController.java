package br.ucsal.app.todo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.app.todo.dao.TarefaDAO;

/**
 * Servlet implementation class RemoverCompartilhamentoController
 */
@WebServlet("/remove/compartilhamento")
public class RemoverCompartilhamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TarefaDAO dao = new TarefaDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String indexout = request.getParameter("out");
		String idTaskout = request.getParameter("outtask");

		if (indexout != null || idTaskout != null) {
			long idUserout = Long.parseLong(indexout);
			long idTout = Long.parseLong(idTaskout);
			dao.deleteCompartilhamento(idUserout, idTout);
			request.setAttribute("userCompartilhado", dao.selectUserOnCompartilhamento(idTout));
			request.setAttribute("tarefas", dao.selectCompartilhamentoByID(idUserout));
			request.setAttribute("tarefa", dao.findById(idTout));
		}
		request.getRequestDispatcher("/compartilhamento").forward(request, response);
	}

}
