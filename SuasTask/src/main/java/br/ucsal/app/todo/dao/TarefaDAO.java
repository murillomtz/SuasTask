package br.ucsal.app.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.ucsal.app.todo.model.Tarefa;
import br.ucsal.app.todo.model.Usuario;

public class TarefaDAO {

	private Context ctx = null;
	private DataSource ds = null;
	private Connection con = null;

	private static final String SELECT_ALL = "SELECT tarefa_id, titulo, descricao, concluida , usuario_id FROM tarefas";
	private static final String SAVE_COMPARTILHAMENTO = "INSERT INTO COMPARTILHAMENTOS (USUARIO_ID, tarefa_id) VALUES (?,?);";
	private static final String SHOW_USER_COMPARTILHAMENTO = "Select usuarios.USUARIO_ID, login, nome, email, ativo\n"
			+ "from usuarios\n" + "inner join COMPARTILHAMENTOS\n"
			+ "on usuarios.usuario_id = COMPARTILHAMENTOS.usuario_id\n" + "where COMPARTILHAMENTOS.tarefa_id = ?";
	private static final String SELECT_COMPARTILHAMENTO = "SELECT tarefas.tarefa_id, tarefas.titulo,"
			+ " tarefas.descricao, tarefas.concluida, tarefas.usuario_id,COMPARTILHAMENTOS.usuario_id "
			+ "from tarefas " + "inner join COMPARTILHAMENTOS " + "on tarefas.tarefa_id = COMPARTILHAMENTOS.tarefa_id "
			+ "where COMPARTILHAMENTOS.usuario_id = ?";

	private static final String SELECT_TASK_USER = "SELECT tarefa_id, titulo, descricao, concluida , usuario_id FROM tarefas WHERE usuario_id=?";
	private static final String UPDATE = "UPDATE tarefas SET titulo=?, descricao=?, concluida=? where tarefa_id=?";
	private static final String INSERT = "INSERT INTO tarefas ( titulo, descricao, concluida, usuario_id ) VALUES ( ?,?,?,? )";
	private static final String DELETE = "DELETE FROM tarefas where tarefa_id=?";
	private static final String DELETE_COMPARTILHAMENTO = "DELETE FROM COMPARTILHAMENTOS where tarefa_id=? and usuario_id=?";
	private static final String SELECT_BY_ID = "SELECT tarefa_id, titulo,descricao, concluida ,usuario_id FROM tarefas WHERE tarefa_id=?";

	public void open() throws Exception {
		ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("jdbc/tarefasDS");
		con = ds.getConnection();
	}

	private void close(Statement stmt) {
		close(stmt, null);
	}

	private void close(Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
			if (ctx != null)
				ctx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public List<Tarefa> listAll() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			this.open();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("tarefa_id"));
				tarefa.setTitulo(rs.getString("titulo"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setConcluida(rs.getBoolean("concluida"));

				Usuario userDono = new Usuario();
				userDono.setId(rs.getLong("usuario_id"));
				tarefa.setDono(userDono);

				tarefas.add(tarefa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(stmt, rs);
		}

		return tarefas;
	}

	public List<Tarefa> listTarefasUser() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			this.open();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_TASK_USER);
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("tarefa_id"));
				tarefa.setTitulo(rs.getString("titulo"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setConcluida(rs.getBoolean("concluida"));

				Usuario userDono = new Usuario();
				userDono.setId(rs.getLong("usuario_id"));
				tarefa.setDono(userDono);

				tarefas.add(tarefa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(stmt, rs);
		}

		return tarefas;
	}

	public void update(Tarefa tarefa) {

		PreparedStatement stmt = null;
		try {
			this.open();
			stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, tarefa.getTitulo());
			stmt.setString(2, tarefa.getDescricao());
			stmt.setBoolean(3, tarefa.getConcluida());
			stmt.setLong(4, tarefa.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void save(Tarefa tarefa) {
		PreparedStatement stmt = null;
		try {
			open();
			stmt = con.prepareStatement(INSERT);
			stmt.setString(1, tarefa.getTitulo());
			stmt.setString(2, tarefa.getDescricao());
			stmt.setBoolean(3, tarefa.getConcluida());
			stmt.setLong(4, tarefa.getDono().getId());
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void delete(Long id) {
		Tarefa tarefa = new Tarefa();
		tarefa.setId(id);
		this.delete(tarefa);
	}

	public void delete(Tarefa tarefa) {
		PreparedStatement stmt = null;

		try {
			open();
			stmt = con.prepareStatement(DELETE);
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public Tarefa findById(Long id) {
		Tarefa tarefa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			open();
			stmt = con.prepareStatement(SELECT_BY_ID);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				tarefa = new Tarefa();
				tarefa.setId(rs.getLong("tarefa_id"));
				tarefa.setTitulo(rs.getString("titulo"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setConcluida(rs.getBoolean("concluida"));
				UsuarioDAO udao = new UsuarioDAO();

				Usuario userDono = udao.findById(rs.getLong("usuario_id"));

				tarefa.setDono(userDono);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return tarefa;
	}

	public List<Tarefa> selectCompartilhamentoByID(Long idUser) {

		List<Tarefa> compartilhamentos = new ArrayList<Tarefa>();

		Tarefa tarefa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			open();
			stmt = con.prepareStatement(SELECT_COMPARTILHAMENTO);

			stmt.setLong(1, idUser);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tarefa = new Tarefa();
				tarefa.setId(rs.getLong("tarefas.tarefa_id"));
				tarefa.setTitulo(rs.getString("tarefas.titulo"));
				tarefa.setDescricao(rs.getString("tarefas.descricao"));
				tarefa.setConcluida(rs.getBoolean("tarefas.concluida"));
				compartilhamentos.add(tarefa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}

		return compartilhamentos;
	}

	public void saveCompartilhamento(Long idUser, Long idTask) {
		PreparedStatement stmt = null;

		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = dao.findById(idUser);
		Tarefa task = findById(idTask);

		System.out.println("Usuario ENCONTRADO> " + user);
		try {
			open();
			stmt = con.prepareStatement(SAVE_COMPARTILHAMENTO);

			stmt.setLong(1, idUser);
			stmt.setLong(2, idTask);

			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}

		task.setCompartilhamento(selectUserOnCompartilhamento(idTask));
	}

	public List<Usuario> selectUserOnCompartilhamento(Long id) {

		List<Usuario> compartilhamentos = new ArrayList<Usuario>();

		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			open();
			stmt = con.prepareStatement(SHOW_USER_COMPARTILHAMENTO);

			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getLong("usuarios.USUARIO_ID"));
				usuario.setLogin(rs.getString("login"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAtivo(rs.getBoolean("ativo"));

				compartilhamentos.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}

		return compartilhamentos;
	}

	public void deleteCompartilhamento(Long idUser, Long IdTask) {
		PreparedStatement stmt = null;
		Tarefa tarefa = findById(IdTask);
		UsuarioDAO udao = new UsuarioDAO();
		Usuario usuario = udao.findById(idUser);
		try {
			open();
			stmt = con.prepareStatement(DELETE_COMPARTILHAMENTO);
			stmt.setLong(1, IdTask);
			stmt.setLong(2, idUser);
			stmt.execute();

			// tarefa.getCompartilhamento().remove(index)
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	public void saveOrUpdate(Tarefa tarefa) {
		if (tarefa.getId() == null) {
			save(tarefa);
		} else {
			update(tarefa);
		}
	}

}
