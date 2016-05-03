package model;

import java.util.ArrayList;

public class NCM {

	private String codigo;
	private String codigoCapitulo;
	private String Capitulo;
	private ArrayList<String> codigoPosicao;
	private ArrayList<String> Posicao;
	private String codigoSubPosicao1;
	private String subPosicao1;
	private String codigoSubPosicao2;
	private String subPosicao2;
	private String descricao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
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

	public ArrayList<String> getCodigoPosicao() {
		return codigoPosicao;
	}

	public void setCodigoPosicao(ArrayList<String> codigoPosicao) {
		this.codigoPosicao = codigoPosicao;
	}	

	public ArrayList<String> getPosicao() {
		return Posicao;
	}

	public void setPosicao(ArrayList<String> posicao) {
		Posicao = posicao;
	}

	public String getCodigoSubPosicao1() {
		return codigoSubPosicao1;
	}

	public void setCodigoSubPosicao1(String codigoSubPosicao1) {
		this.codigoSubPosicao1 = codigoSubPosicao1;
	}

	public String getSubPosicao1() {
		return subPosicao1;
	}

	public void setSubPosicao1(String subPosicao1) {
		this.subPosicao1 = subPosicao1;
	}

	public String getCodigoSubPosicao2() {
		return codigoSubPosicao2;
	}

	public void setCodigoSubPosicao2(String codigoSubPosicao2) {
		this.codigoSubPosicao2 = codigoSubPosicao2;
	}

	public String getSubPosicao2() {
		return subPosicao2;
	}

	public void setSubPosicao2(String subPosicao2) {
		this.subPosicao2 = subPosicao2;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
