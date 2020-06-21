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

@WebServlet(urlPatterns = { "/salvar", "/editar" })
public class TaskSaveUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TarefaDAO dao = new TarefaDAO();
	private UsuarioDAO udao = new UsuarioDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String index = request.getParameter("page");

		if (index != null) {
			long id = Long.parseLong(index);
			Tarefa tarefa = dao.findById(id);
			request.setAttribute("tarefa", tarefa);
		}

		request.setAttribute("tarefas", dao.listAll());
		request.getRequestDispatcher("private/page.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String idUser = request.getParameter("id_user");
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String concluida = request.getParameter("concluida");

		Tarefa tarefa = new Tarefa();
		if (id != null && !id.trim().isEmpty()) {
			long i = Long.parseLong(id);
			tarefa.setId(i);
		}
		tarefa.setTitulo(titulo);
		tarefa.setDescricao(descricao);
		tarefa.setConcluida(concluida != null ? true : false);

		UsuarioDAO udao = new UsuarioDAO();
		long id_user = Long.parseLong(idUser);
		Usuario dono = udao.findById(id_user);

		tarefa.setDono(dono);

		System.out.println("Dono: " + dono);
		dao.saveOrUpdate(tarefa);

		if (dono.getAdm()) {
			response.sendRedirect("/tarefas");
		} else {

			request.setAttribute("tarefas", udao.listTask(dono.getId()));
			request.getRequestDispatcher("private/usuariopage.jsp").forward(request, response);

		}

	}

}
