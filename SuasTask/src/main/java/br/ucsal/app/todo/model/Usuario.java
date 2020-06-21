package br.ucsal.app.todo.model;

public class Usuario {
	private Long id;

	private String login;
	private String nome;
	private String email;
	private String senha;
	private Boolean ativo;
	// private TipoEnum perfil;
	private Boolean adm;

	public Usuario() {
		super();
	}

	public Usuario(Long id, String login, String senha, Boolean adm) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.adm = adm;
	}

	public Usuario(Long id, String login, String nome, String email, String senha, Boolean ativo, Boolean adm) {
		super();
		this.id = id;
		this.login = login;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
		this.adm = adm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getAdm() {
		return adm;
	}

	public void setAdm(Boolean adm) {
		this.adm = adm;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", ativo=" + ativo + ", adm=" + adm + "]";
	}

}
