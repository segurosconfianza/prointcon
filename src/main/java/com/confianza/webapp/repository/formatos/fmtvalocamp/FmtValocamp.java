package com.confianza.webapp.repository.formatos.fmtvalocamp;

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
@Table(name = "FMT_VALOCAMP")
public class FmtValocamp {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FMT_VALOCAMP_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FMT_VALOCAMP_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FMT_VALOCAMP_GEN")  
	@Column(name = "VACACONS")
	protected Long vacacons; 
	 
	@Column(name = "VACACAMP")
	protected Long vacacamp; 
	 
	@Column(name = "VACAFORE")
	protected Long vacafore; 
	 
	@Column(name = "VACAVALO")
	protected String vacavalo; 

	public FmtValocamp(){
	
	}

	public Long getVacacons(){
		return vacacons;
	}
	
	public void setVacacons(Long vacacons){
		this.vacacons = vacacons;
	}

	public Long getVacacamp(){
		return vacacamp;
	}
	
	public void setVacacamp(Long vacacamp){
		this.vacacamp = vacacamp;
	}

	public Long getVacafore(){
		return vacafore;
	}
	
	public void setVacafore(Long vacafore){
		this.vacafore = vacafore;
	}

	public String getVacavalo(){
		return vacavalo;
	}
	
	public void setVacavalo(String vacavalo){
		this.vacavalo = vacavalo;
	}


	static public String[] getNames(){
		return new String[]{ "vacacons", "vacacamp", "vacafore", "vacavalo" };
	}		
	
	static public String getColumnNames(){
		return " VACACONS, VACACAMP, VACAFORE, VACAVALO ";
	}
	
	public String toString(){
		return " VACACONS: "+ this.vacacons 
			+" VACACAMP: "+ this.vacacamp 
			+" VACAFORE: "+ this.vacafore 
			+" VACAVALO: "+ this.vacavalo ;
	}
}
