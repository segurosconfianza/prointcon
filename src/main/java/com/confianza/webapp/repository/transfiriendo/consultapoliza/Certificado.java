package com.confianza.webapp.repository.transfiriendo.consultapoliza;

import java.util.ArrayList;
import java.util.List;

public class Certificado {
	
	public List<Campo> Campos;

	public Certificado(List<Campo> Campo) {
		this.Campos = Campo;
	}

	public Certificado() {
		Campos = new ArrayList<Campo>();
	}

	public List<Campo> getCampo() {
		return Campos;
	}

	public void setCampo(List<Campo> Campo) {
		this.Campos = Campo;
	}
	
	public void addCampo(String identificador, String Campo){
		Campo newCampo=new Campo(identificador, Campo);
		this.Campos.add(newCampo);
	}

	@Override
	public String toString() {
		String toString = "[";
		for(Campo campo:Campos)
			toString += campo + ", ";
		return toString+" ] ";
	}
	
	
	
}
