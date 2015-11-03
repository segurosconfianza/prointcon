package com.confianza.webapp.repository.transfiriendo.consultapoliza;

import java.util.ArrayList;
import java.util.List;

public class Poliza {

	public String idpro;
	
	public List<Certificado> Certificados; 
	
	public Poliza(String idpro, List<Certificado> certificados) {
		this.idpro = idpro;
		this.Certificados = certificados;
	}

	public Poliza(String idpro) {
		this.idpro = idpro;
		Certificados = new ArrayList<Certificado>();
	}

	public String getIdpro() {
		return idpro;
	}

	public void setIdpro(String idpro) {
		this.idpro = idpro;
	}

	public List<Certificado> getCertificados() {
		return Certificados;
	}

	public void setcertificado(List<Certificado> certificados) {
		this.Certificados = certificados;
	}
	
	public void addCertificado(Certificado certificado){
		this.Certificados.add(certificado);
	}

	@Override
	public String toString() {
		return "Poliza [idpro=" + idpro + ", Certificado=" + Certificados + "]";
	}
		
}
