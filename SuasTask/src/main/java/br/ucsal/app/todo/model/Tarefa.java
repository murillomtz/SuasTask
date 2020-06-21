package br.ucsal.app.todo.model;

import java.util.List;

public class Tarefa {

	private Long id;

	private String titulo;
	private String descricao;
	private Boolean concluida;
	private Usuario dono;
	private List<Usuario> compartilhamento;

	

	public List<Usuario> getCompartilhamento() {
		return compartilhamento;
	}

	public void setCompartilhamento(List<Usuario> compartilhamento) {
		this.compartilhamento = compartilhamento;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concluida) {
		this.concluida = concluida;
	}

	@Override
	public String toString() {
		return "Tarefa [titulo=" + titulo + ", descricao=" + descricao + ", concluida=" + concluida + ", dono="
				+ dono.getLogin() + "]";
	}

}
