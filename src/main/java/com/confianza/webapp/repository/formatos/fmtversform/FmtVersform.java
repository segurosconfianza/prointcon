package com.confianza.webapp.repository.formatos.fmtversform;

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
@Table(name = "FMT_VERSFORM")
public class FmtVersform {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FMT_VERSFORM_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FMT_VERSFORM_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FMT_VERSFORM_GEN")  
	@Column(name = "VEFOCONS")
	protected Long vefocons; 
	 
	@Column(name = "VEFOFORM")
	protected Long vefoform; 
	 
	@Column(name = "VEFONOMB")
	protected String vefonomb; 
	 
	@Column(name = "VEFOFEIN")
	protected Date vefofein; 
	 
	@Column(name = "VEFOFEFI")
	protected Date vefofefi; 
	 
	@Column(name = "VEFOESTA")
	protected String vefoesta; 

	public FmtVersform(){
	
	}

	public Long getVefocons(){
		return vefocons;
	}
	
	public void setVefocons(Long vefocons){
		this.vefocons = vefocons;
	}

	public Long getVefoform(){
		return vefoform;
	}
	
	public void setVefoform(Long vefoform){
		this.vefoform = vefoform;
	}

	public String getVefonomb(){
		return vefonomb;
	}
	
	public void setVefonomb(String vefonomb){
		this.vefonomb = vefonomb;
	}

	public Date getVefofein(){
		return vefofein;
	}
	
	public void setVefofein(Date vefofein){
		this.vefofein = vefofein;
	}

	public Date getVefofefi(){
		return vefofefi;
	}
	
	public void setVefofefi(Date vefofefi){
		this.vefofefi = vefofefi;
	}

	public String getVefoesta(){
		return vefoesta;
	}
	
	public void setVefoesta(String vefoesta){
		this.vefoesta = vefoesta;
	}


	static public String[] getNames(){
		return new String[]{ "vefocons", "vefoform", "vefonomb", "vefofein", "vefofefi", "vefoesta" };
	}		
	
	static public String getColumnNames(){
		return " VEFOCONS, VEFOFORM, VEFONOMB, VEFOFEIN, VEFOFEFI, VEFOESTA ";
	}
	
	public String toString(){
		return " VEFOCONS: "+ this.vefocons 
			+" VEFOFORM: "+ this.vefoform 
			+" VEFONOMB: "+ this.vefonomb 
			+" VEFOFEIN: "+ this.vefofein 
			+" VEFOFEFI: "+ this.vefofefi 
			+" VEFOESTA: "+ this.vefoesta ;
	}
}
