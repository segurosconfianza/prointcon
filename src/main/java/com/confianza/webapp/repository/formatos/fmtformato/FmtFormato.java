package com.confianza.webapp.repository.formatos.fmtformato;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
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
@Table(name = "FMT_FORMATO")
public class FmtFormato {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FMT_FORMATO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FMT_FORMATO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FMT_FORMATO_GEN")  
	@Column(name = "FORMCONS")
	protected Long formcons; 
	 
	@Column(name = "FORMNOMB")
	protected String formnomb; 
	 
	@Column(name = "FORMDESC")
	protected String formdesc; 
	 
	@Column(name = "FORMESTA")
	protected String formesta; 

	public FmtFormato(){
	
	}

	public Long getFormcons(){
		return formcons;
	}
	
	public void setFormcons(Long formcons){
		this.formcons = formcons;
	}

	public String getFormnomb(){
		return formnomb;
	}
	
	public void setFormnomb(String formnomb){
		this.formnomb = formnomb;
	}

	public String getFormdesc(){
		return formdesc;
	}
	
	public void setFormdesc(String formdesc){
		this.formdesc = formdesc;
	}

	public String getFormesta(){
		return formesta;
	}
	
	public void setFormesta(String formesta){
		this.formesta = formesta;
	}


	static public String[] getNames(){
		return new String[]{ "formcons", "formnomb", "formdesc", "formesta" };
	}		
	
	static public String getColumnNames(){
		return " FORMCONS, FORMNOMB, FORMDESC, FORMESTA ";
	}
	
	public String toString(){
		return " FORMCONS: "+ this.formcons 
			+" FORMNOMB: "+ this.formnomb 
			+" FORMDESC: "+ this.formdesc 
			+" FORMESTA: "+ this.formesta ;
	}
}
