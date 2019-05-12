package com.involves.selecao.domain.pesquisa.vo;
import java.util.List;

public class PesquisaVO {
	private int id;
	private String rotulo;
	private String notificante;
	private String ponto_de_venda;
	private String produto;
	private String preco_estipulado;
	private String categoria;
	private String participacao_estipulada;
	private List<RespostaVO> respostas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRotulo() {
		return rotulo;
	}
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
	public String getNotificante() {
		return notificante;
	}
	public void setNotificante(String notificante) {
		this.notificante = notificante;
	}
	public String getPonto_de_venda() {
		return ponto_de_venda;
	}
	public void setPonto_de_venda(String ponto_de_venda) {
		this.ponto_de_venda = ponto_de_venda;
	}
	public List<RespostaVO> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<RespostaVO> respostas) {
		this.respostas = respostas;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getPreco_estipulado() {
		return preco_estipulado;
	}
	public void setPreco_estipulado(String preco_estipulado) {
		this.preco_estipulado = preco_estipulado;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getParticipacaoEstipulada() {
		return participacao_estipulada;
	}
	public void setParticipacaoEstipulada(String participacaoEstipulada) {
		this.participacao_estipulada = participacaoEstipulada;
	}
}