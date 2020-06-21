package br.ucsal.app.todo.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userLogin = request.getParameter("user_login");
		String userSenha = null;

		try {
			userSenha = toPassword(request.getParameter("user_senha"));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		UsuarioDAO dao = new UsuarioDAO();
		TarefaDAO tdao = new TarefaDAO();

		Usuario usuario = dao.autenticar(userLogin, userSenha);

		RequestDispatcher dispatcher = null;

		if (usuario != null && usuario.getAdm()) {

			System.out.println("LOGOU ADM: " + usuario);
			request.getSession().setAttribute("usuario", usuario);
			request.setAttribute("tarefas", tdao.listAll());
			dispatcher = request.getRequestDispatcher("private/page.jsp");
			dispatcher.forward(request, response);

		} else if (usuario != null && !usuario.getAdm()) {

			System.out.println("LOGOU USER: " + usuario.getLogin());
			request.getSession().setAttribute("usuario", usuario);

			request.setAttribute("tarefas", dao.listTask(usuario.getId()));
			dispatcher = request.getRequestDispatcher("private/usuariopage.jsp");
			dispatcher.forward(request, response);

		} else {

			request.setAttribute("erro", "Login ou Senha Invalidos");

			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}

	}

	private static String bytesToHex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; ++i) {
			sb.append((Integer.toHexString((b[i] & 0xFF) | 0x100)).substring(1, 3));
		}
		return sb.toString();
	}

	public static String toPassword(String data) throws NoSuchAlgorithmException {
		byte[] mybytes = data.getBytes();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] md5digest = md5.digest(mybytes);
		return bytesToHex(md5digest);
	}
}
