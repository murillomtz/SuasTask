package br.ucsal.app.todo.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.app.todo.dao.UsuarioDAO;
import br.ucsal.app.todo.model.Usuario;

@WebServlet(urlPatterns = { "/usuarios/salvar", "/usuarios/editarUser" })
public class UserSaveUpdateControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO udao = new UsuarioDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String index = request.getParameter("page");

		if (index != null) {
			long id = Long.parseLong(index);
			Usuario usuario = udao.findById(id);
			request.setAttribute("usuario", usuario);
		}

		request.setAttribute("usuarios", udao.listAll());
		request.getRequestDispatcher("/usuarios").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = null;
		try {
			senha = toPassword(request.getParameter("senha"));
			System.out.println("Senha Crip " + senha);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String ativo = request.getParameter("ativo");

		Usuario usuario = new Usuario();
		if (id != null && !id.trim().isEmpty()) {
			long i = Long.parseLong(id);
			usuario.setId(i);
		}
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setAtivo(ativo != null ? true : false);

		udao.saveOrUpdate(usuario);

		response.sendRedirect("/usuarios");
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
