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
import br.ucsal.app.todo.model.Usuario;

/**
 * Servlet implementation class AddCompartilhamentoControler
 */
@WebServlet("/compartilhar/add")
public class AddCompartilhamentoControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TarefaDAO dao = new TarefaDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String index = request.getParameter("page");
		String idTask = request.getParameter("idtask");

		long idUser = Long.parseLong(index);
		long idT = Long.parseLong(idTask);
		if (index != null) {

			dao.saveCompartilhamento(idUser, idT);

			request.setAttribute("userCompartilhado", dao.selectUserOnCompartilhamento(idT));
			request.setAttribute("tarefas", dao.selectCompartilhamentoByID(idUser));
			request.setAttribute("tarefa", dao.findById(idT));

		}

		request.getRequestDispatcher("/compartilhamento").forward(request, response);
	}

}
