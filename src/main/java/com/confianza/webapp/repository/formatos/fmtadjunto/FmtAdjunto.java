package com.confianza.webapp.repository.formatos.fmtadjunto;

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
@Table(name = "FMT_ADJUNTO")
public class FmtAdjunto {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FMT_ADJUNTO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FMT_ADJUNTO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FMT_ADJUNTO_GEN")  
	@Column(name = "ADJUCONS")
	protected Long adjucons; 
	 
	@Column(name = "ADJUARCH")
	protected Long adjuarch; 
	 
	@Column(name = "ADJUFORE")
	protected Long adjufore; 
	 
	@Column(name = "ADJUNOMB")
	protected String adjunomb; 
	 
	@Column(name = "ADJUUSER")
	protected String adjuuser; 
	 
	@Column(name = "ADJUFECH")
	protected Date adjufech; 
	 
	@Column(name = "ADJUESTA")
	protected String adjuesta; 

	public FmtAdjunto(){
	
	}

	public Long getAdjucons(){
		return adjucons;
	}
	
	public void setAdjucons(Long adjucons){
		this.adjucons = adjucons;
	}

	public Long getAdjuarch(){
		return adjuarch;
	}
	
	public void setAdjuarch(Long adjuarch){
		this.adjuarch = adjuarch;
	}

	public Long getAdjufore(){
		return adjufore;
	}
	
	public void setAdjufore(Long adjufore){
		this.adjufore = adjufore;
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


	static public String[] getNames(){
		return new String[]{ "adjucons", "adjuarch", "adjufore", "adjunomb", "adjuuser", "adjufech", "adjuesta" };
	}		
	
	static public String getColumnNames(){
		return " ADJUCONS, ADJUARCH, ADJUFORE, ADJUNOMB, ADJUUSER, ADJUFECH, ADJUESTA ";
	}
	
	public String toString(){
		return " ADJUCONS: "+ this.adjucons 
			+" ADJUARCH: "+ this.adjuarch 
			+" ADJUFORE: "+ this.adjufore 
			+" ADJUNOMB: "+ this.adjunomb 
			+" ADJUUSER: "+ this.adjuuser 
			+" ADJUFECH: "+ this.adjufech 
			+" ADJUESTA: "+ this.adjuesta ;
	}
}
