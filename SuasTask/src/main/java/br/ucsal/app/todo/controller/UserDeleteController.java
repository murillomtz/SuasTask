package br.ucsal.app.todo.controller;

import br.ucsal.app.todo.dao.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/usuarios/excluir")
public class UserDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO udao = new UsuarioDAO();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String index = request.getParameter("page");
        if( index != null ) {
            try {
                long id = Long.parseLong(index);
                udao.delete(id);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        response.sendRedirect("/usuarios");


    }
}