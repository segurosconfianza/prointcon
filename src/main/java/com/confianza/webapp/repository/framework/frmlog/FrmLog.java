package com.confianza.webapp.repository.framework.frmlog;

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
@Table(name = "FRM_LOG")
public class FrmLog {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_LOG_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_LOG_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_LOG_GEN")  
	@Column(name = "SLOGCONS")
	protected Long slogcons; 
	 
	@Column(name = "SLOGTRAN")
	protected Long slogtran; 
	 
	@Column(name = "SLOGTABL")
	protected String slogtabl; 
	 
	@Column(name = "SLOGACCI")
	protected String slogacci; 
	 
	@Column(name = "SLOGREGI")
	protected String slogregi; 

	public FrmLog(){
	
	}

	public Long getSlogcons(){
		return slogcons;
	}
	
	public void setSlogcons(Long slogcons){
		this.slogcons = slogcons;
	}

	public Long getSlogtran(){
		return slogtran;
	}
	
	public void setSlogtran(Long slogtran){
		this.slogtran = slogtran;
	}

	public String getSlogtabl(){
		return slogtabl;
	}
	
	public void setSlogtabl(String slogtabl){
		this.slogtabl = slogtabl;
	}

	public String getSlogacci(){
		return slogacci;
	}
	
	public void setSlogacci(String slogacci){
		this.slogacci = slogacci;
	}

	public String getSlogregi(){
		return slogregi;
	}
	
	public void setSlogregi(String slogregi){
		this.slogregi = slogregi;
	}


	static public String[] getNames(){
		return new String[]{ "slogcons", "slogtran", "slogtabl", "slogacci", "slogregi" };
	}		
	
	static public String getColumnNames(){
		return " SLOGCONS, SLOGTRAN, SLOGTABL, SLOGACCI, SLOGREGI ";
	}
	
	public String toString(){
		return " SLOGCONS: "+ this.slogcons 
			+" SLOGTRAN: "+ this.slogtran 
			+" SLOGTABL: "+ this.slogtabl 
			+" SLOGACCI: "+ this.slogacci 
			+" SLOGREGI: "+ this.slogregi ;
	}
}
