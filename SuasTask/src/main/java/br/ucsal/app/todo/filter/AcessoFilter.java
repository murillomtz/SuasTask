package br.ucsal.app.todo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.ucsal.app.todo.model.Usuario;

/**
 * Servlet Filter implementation class AcessoFilter
 */
@WebFilter("/private/*")
public class AcessoFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AcessoFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		System.out.println("FILTREI: " + httpServletRequest.getRequestURL());

		Object object = httpServletRequest.getSession().getAttribute("usuario");

		if (object != null) {
			Usuario usuario = (Usuario) object;
			System.out.println(usuario.getLogin());

			chain.doFilter(request, response);

		} else {
			System.out.println("SEM USUARIO NA SESSION");
			request.setAttribute("erro", "VOCE PRECISO ESTAR LOGADO!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
