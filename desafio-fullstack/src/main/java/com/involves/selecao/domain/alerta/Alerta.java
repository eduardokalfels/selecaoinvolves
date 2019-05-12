package com.involves.selecao.domain.alerta;

public class Alerta {
	
	private String pontoDeVenda;
	private String descricao;
	private String tipo;
	private String produto;
	private String categoria;
	private Integer margem;
	
	public String getPontoDeVenda() {
		return pontoDeVenda;
	}
	public void setPontoDeVenda(String pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getMargem() {
		return margem;
	}
	public void setMargem(Integer margem) {
		this.margem = margem;
	}
	
}
