package com.confianza.webapp.repository.transfiriendo.consultapoliza;

public class Campo {

	public String identificador;
	public String valor;
	
	public Campo(String identificador, String valor) {
		this.identificador = identificador;
		this.valor = valor;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Campo [identificador=" + identificador + ", valor=" + valor + "]";
	}
		
}
