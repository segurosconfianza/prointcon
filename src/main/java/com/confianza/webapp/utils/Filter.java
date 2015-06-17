package com.confianza.webapp.utils;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

public class Filter {
	
	protected String campo; 
	 
	protected String tipo; 
	 
	protected String val1; 
	 
	protected String val2; 
	
	protected String tipodato;
	 
	public String getTipodato() {
		return tipodato;
	}

	public void setTipodato(String tipodato) {
		this.tipodato = tipodato;
	}

	public Filter(){
	
	}

	public String getCampo() {
		return campo;
	}


	public void setCampo(String campo) {
		this.campo = campo;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getVal1() {
		return val1;
	}


	public void setVal1(String val1) {
		this.val1 = val1;
	}


	public String getVal2() {
		return val2;
	}


	public void setVal2(String val2) {
		this.val2 = val2;
	}


	static public String[] getNames(){
		return new String[]{ "campo", "tipo", "val1", "val2", "tipodato"};
	}
	
	static public String getColumnNames(){
		return "campo, tipo, val1, val2, tipodato ";
	}
	
	public String toString(){
		return " CAMPOS: "+ this.campo 
			+" TIPO: "+ this.tipo
			+" VAL1: "+ this.val1 
			+" VAL2: "+ this.val2
			+" TIPODATO: "+ this.tipodato;
	}
}
