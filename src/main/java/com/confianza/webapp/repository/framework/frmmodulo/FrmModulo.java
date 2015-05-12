package com.confianza.webapp.repository.framework.frmmodulo;

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
@Table(name = "FRM_MODULO")
public class FrmModulo {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_MODULO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_MODULO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_MODULO_GEN")  
	@Column(name = "MODUCONS")
	protected Long moducons; 
	 
	@Column(name = "MODUAPLI")
	protected Long moduapli; 
	 
	@Column(name = "MODUNOMB")
	protected String modunomb; 
	 
	@Column(name = "MODUNEMO")
	protected String modunemo; 
	 
	@Column(name = "MODUDURL")
	protected String modudurl; 

	public FrmModulo(){
	
	}

	public Long getModucons(){
		return moducons;
	}
	
	public void setModucons(Long moducons){
		this.moducons = moducons;
	}

	public Long getModuapli(){
		return moduapli;
	}
	
	public void setModuapli(Long moduapli){
		this.moduapli = moduapli;
	}

	public String getModunomb(){
		return modunomb;
	}
	
	public void setModunomb(String modunomb){
		this.modunomb = modunomb;
	}

	public String getModunemo(){
		return modunemo;
	}
	
	public void setModunemo(String modunemo){
		this.modunemo = modunemo;
	}

	public String getModudurl(){
		return modudurl;
	}
	
	public void setModudurl(String modudurl){
		this.modudurl = modudurl;
	}


	static public String[] getNames(){
		return new String[]{ "moducons", "moduapli", "modunomb", "modunemo", "modudurl" };
	}		
	
	public String toString(){
		return " MODUCONS: "+ this.moducons 
			+" MODUAPLI: "+ this.moduapli 
			+" MODUNOMB: "+ this.modunomb 
			+" MODUNEMO: "+ this.modunemo 
			+" MODUDURL: "+ this.modudurl ;
	}
}
