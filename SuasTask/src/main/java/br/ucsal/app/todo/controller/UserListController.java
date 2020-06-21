package br.ucsal.app.todo.controller;

import br.ucsal.app.todo.dao.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/usuarios")
public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO udao = new UsuarioDAO();


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("usuarios", udao.listAll());

        request.getRequestDispatcher("/private/usuarios.jsp").forward(request, response);
    }
}
