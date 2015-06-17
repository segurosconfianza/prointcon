package com.confianza.webapp.repository.formatos.fmtformregi;

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
@Table(name = "FMT_FORMREGI")
public class FmtFormregi {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FMT_FORMREGI_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FMT_FORMREGI_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FMT_FORMREGI_GEN")  
	@Column(name = "FORECONS")
	protected Long forecons; 
	 
	@Column(name = "FOREVEFO")
	protected Long forevefo; 
	 
	@Column(name = "FOREFECH")
	protected Date forefech; 
	 
	@Column(name = "FOREUSER")
	protected String foreuser; 
	 
	@Column(name = "FOREESTA")
	protected String foreesta; 

	public FmtFormregi(){
	
	}

	public Long getForecons(){
		return forecons;
	}
	
	public void setForecons(Long forecons){
		this.forecons = forecons;
	}

	public Long getForevefo(){
		return forevefo;
	}
	
	public void setForevefo(Long forevefo){
		this.forevefo = forevefo;
	}

	public Date getForefech(){
		return forefech;
	}
	
	public void setForefech(Date forefech){
		this.forefech = forefech;
	}

	public String getForeuser(){
		return foreuser;
	}
	
	public void setForeuser(String foreuser){
		this.foreuser = foreuser;
	}

	public String getForeesta(){
		return foreesta;
	}
	
	public void setForeesta(String foreesta){
		this.foreesta = foreesta;
	}


	static public String[] getNames(){
		return new String[]{ "forecons", "forevefo", "forefech", "foreuser", "foreesta" };
	}		
	
	static public String getColumnNames(){
		return " forecons, forevefo, forefech, foreuser, foreesta ";
	}
	
	public String toString(){
		return " FORECONS: "+ this.forecons 
			+" FOREVEFO: "+ this.forevefo 
			+" FOREFECH: "+ this.forefech 
			+" FOREUSER: "+ this.foreuser 
			+" FOREESTA: "+ this.foreesta ;
	}
}
