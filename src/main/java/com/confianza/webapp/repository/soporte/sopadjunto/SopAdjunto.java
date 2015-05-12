package com.confianza.webapp.repository.soporte.sopadjunto;

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
@Table(name = "SOP_ADJUNTO")
public class SopAdjunto {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "SOP_ADJUNTO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="SOP_ADJUNTO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SOP_ADJUNTO_GEN")  
	@Column(name = "ADJUCODI")
	protected Long adjucodi; 
	 
	@Column(name = "ADJUARCH")
	protected Long adjuarch; 
	 
	@Column(name = "ADJUNOMB")
	protected String adjunomb; 
	 
	@Column(name = "ADJUUSER")
	protected String adjuuser; 
	 
	@Column(name = "ADJUFECH")
	protected Date adjufech; 
	 
	@Column(name = "ADJUESTA")
	protected String adjuesta; 
	 
	@Column(name = "ADJUMOTI")
	protected Long adjumoti; 

	public SopAdjunto(){
	
	}

	public Long getAdjucodi(){
		return adjucodi;
	}
	
	public void setAdjucodi(Long adjucodi){
		this.adjucodi = adjucodi;
	}

	public Long getAdjuarch(){
		return adjuarch;
	}
	
	public void setAdjuarch(Long adjuarch){
		this.adjuarch = adjuarch;
	}

	public String getAdjunomb(){
		return adjunomb;
	}
	
	public void setAdjunomb(String adjunomb){
		this.adjunomb = adjunomb;
	}

	public String getAdjuuser(){
		return adjuuser;
	}
	
	public void setAdjuuser(String adjuuser){
		this.adjuuser = adjuuser;
	}

	public Date getAdjufech(){
		return adjufech;
	}
	
	public void setAdjufech(Date adjufech){
		this.adjufech = adjufech;
	}

	public String getAdjuesta(){
		return adjuesta;
	}
	
	public void setAdjuesta(String adjuesta){
		this.adjuesta = adjuesta;
	}

	public Long getAdjumoti(){
		return adjumoti;
	}
	
	public void setAdjumoti(Long adjumoti){
		this.adjumoti = adjumoti;
	}


	static public String[] getNames(){
		return new String[]{ "adjucodi", "adjuarch", "adjunomb", "adjuuser", "adjufech", "adjuesta", "adjumoti" };
	}		
	
	static public String getColumnNames(){
		return " ADJUCODI, ADJUARCH, ADJUNOMB, ADJUUSER, ADJUFECH, ADJUESTA, ADJUMOTI ";
	}
	
	public String toString(){
		return " ADJUCODI: "+ this.adjucodi 
			+" ADJUARCH: "+ this.adjuarch 
			+" ADJUNOMB: "+ this.adjunomb 
			+" ADJUUSER: "+ this.adjuuser 
			+" ADJUFECH: "+ this.adjufech 
			+" ADJUESTA: "+ this.adjuesta 
			+" ADJUMOTI: "+ this.adjumoti ;
	}
}
