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
 * Servlet implementation class TaskDeleteController
 */
@WebServlet("/excluir")
public class TaskDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TarefaDAO dao = new TarefaDAO();
	private UsuarioDAO udao = new UsuarioDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String index = request.getParameter("page");
		String index2 = request.getParameter("usuariopage");
		//String user_id = request.getParameter("user");

		

		if (index != null) {
			try {
				long id = Long.parseLong(index);
				dao.delete(id);
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
			}
			response.sendRedirect("/tarefas");
		} else if (index2 != null) {

			try {
				Long id = Long.parseLong(index2);

				Tarefa tarefa = dao.findById(id);
				System.out.println("ID tarefa: " + tarefa);
				System.out.println("ID DONO: " + tarefa.getDono().getId());
				dao.delete(id);

				request.setAttribute("tarefas", udao.listTask(tarefa.getDono().getId()));
				request.getRequestDispatcher("private/usuariopage.jsp").forward(request, response);
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();

			}

		}

	}

}
