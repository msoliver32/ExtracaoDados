package model;

import java.util.ArrayList;

public class NCM {

	private ArrayList<String> codigo;
	private String codigoCapitulo;
	private String Capitulo;
	private ArrayList<String> descricao;
	
	public NCM(){
		
		this.codigo = new ArrayList<String>();
		this.descricao = new ArrayList<String>(); 
	}

	public ArrayList<String> getCodigo() {
		return codigo;
	}

	public void setCodigo(ArrayList<String> codigo) {
		this.codigo = codigo;
	}

	public String getCodigoCapitulo() {
		return codigoCapitulo;
	}

	public void setCodigoCapitulo(String codigoCapitulo) {
		this.codigoCapitulo = codigoCapitulo;
	}

	public String getCapitulo() {
		return Capitulo;
	}

	public void setCapitulo(String capitulo) {
		Capitulo = capitulo;
	}

	public ArrayList<String> getDescricao() {
		return descricao;
	}

	public void setDescricao(ArrayList<String> descricao) {
		this.descricao = descricao;
	}
			
}