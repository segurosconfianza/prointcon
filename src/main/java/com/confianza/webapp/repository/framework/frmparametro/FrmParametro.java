package com.confianza.webapp.repository.framework.frmparametro;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "FRM_PARAMETRO")
public class FrmParametro {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_PARAMETRO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_PARAMETRO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_PARAMETRO_GEN")  
	@Column(name = "PARACONS")
	protected Long paracons; 
	 
	@Column(name = "PARACOSU")
	protected Long paracosu; 
	 
	@Column(name = "PARANOMB")
	protected String paranomb; 
	 
	@Column(name = "PARATIPO")
	protected String paratipo; 
	 
	@Column(name = "PARATIDA")
	protected String paratida; 
	 
	@Column(name = "PARACOMB")
	protected String paracomb; 
	
	@Column(name = "PARAREQU")
	protected Long pararequ;
	
	@Column(name = "PARALABE")
	protected String paralabe; 
	
	public FrmParametro(){
	
	}

	public Long getParacons(){
		return paracons;
	}
	
	public void setParacons(Long paracons){
		this.paracons = paracons;
	}

	public Long getParacosu(){
		return paracosu;
	}
	
	public void setParacosu(Long paracosu){
		this.paracosu = paracosu;
	}

	public String getParanomb(){
		return paranomb;
	}
	
	public void setParanomb(String paranomb){
		this.paranomb = paranomb;
	}

	public String getParatipo(){
		return paratipo;
	}
	
	public void setParatipo(String paratipo){
		this.paratipo = paratipo;
	}

	public String getParatida(){
		return paratida;
	}
	
	public void setParatida(String paratida){
		this.paratida = paratida;
	}

	public String getParacomb(){
		return paracomb;
	}
	
	public void setParacomb(String paracomb){
		this.paracomb = paracomb;
	}

	public Long getPararequ() {
		return pararequ;
	}

	public void setPararequ(Long pararequ) {
		this.pararequ = pararequ;
	}

	public String getParalabe() {
		return paralabe;
	}

	public void setParalabe(String paralabe) {
		this.paralabe = paralabe;
	}

	static public String[] getNames(){
		return new String[]{ "PARACONS", "PARACOSU", "PARANOMB", "PARATIPO", "PARATIDA", "PARACOMB", "PARAREQU", "PARALABE" };
	}		
	
	static public String getColumnNames(){
		return " PARACONS, PARACOSU, PARANOMB, PARATIPO, PARATIDA, PARACOMB, PARAREQU, PARALABE ";
	}
	
	public String toString(){
		return " PARACONS: "+ this.paracons 
			+" PARACOSU: "+ this.paracosu 
			+" PARANOMB: "+ this.paranomb 
			+" PARATIPO: "+ this.paratipo 
			+" PARATIDA: "+ this.paratida 
			+" PARACOMB: "+ this.paracomb 
			+" PARAREQU: "+ this.pararequ
			+" PARALABE: "+ this.paralabe;
	}
}
