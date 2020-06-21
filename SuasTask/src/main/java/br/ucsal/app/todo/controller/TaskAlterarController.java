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

@WebServlet("/editartask")
public class TaskAlterarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TarefaDAO dao = new TarefaDAO();
	private UsuarioDAO udao = new UsuarioDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String index = request.getParameter("usuariopage");

		Usuario dono = null;
		if (index != null) {
			long id = Long.parseLong(index);
			Tarefa tarefa = dao.findById(id);

			request.setAttribute("tarefa", tarefa);
			dono = udao.findById(tarefa.getDono().getId());
		}
		System.out.println("ID USER: " + dono);
		request.setAttribute("tarefas", udao.listTask(dono.getId()));
		request.getRequestDispatcher("private/usuariopage.jsp").forward(request, response);

	}

}
