package com.confianza.webapp.repository.formatos.fmtestado;

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
@Table(name = "FMT_ESTADO")
public class FmtEstado {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FMT_ESTADO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FMT_ESTADO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FMT_ESTADO_GEN")  
	@Column(name = "ESTACONS")
	protected Long estacons; 
	 
	@Column(name = "ESTAFORE")
	protected Long estafore; 
	 
	@Column(name = "ESTAFECH")
	protected Date estafech; 
	 
	@Column(name = "ESTAUSER")
	protected String estauser; 
	 
	@Column(name = "ESTAESTA")
	protected String estaesta; 

	public FmtEstado(){
	
	}

	public Long getEstacons(){
		return estacons;
	}
	
	public void setEstacons(Long estacons){
		this.estacons = estacons;
	}

	public Long getEstafore(){
		return estafore;
	}
	
	public void setEstafore(Long estafore){
		this.estafore = estafore;
	}

	public Date getEstafech(){
		return estafech;
	}
	
	public void setEstafech(Date estafech){
		this.estafech = estafech;
	}

	public String getEstauser(){
		return estauser;
	}
	
	public void setEstauser(String estauser){
		this.estauser = estauser;
	}

	public String getEstaesta(){
		return estaesta;
	}
	
	public void setEstaesta(String estaesta){
		this.estaesta = estaesta;
	}


	static public String[] getNames(){
		return new String[]{ "estacons", "estafore", "estafech", "estauser", "estaesta" };
	}		
	
	static public String getColumnNames(){
		return " estacons, estafore, estafech, estauser, estaesta ";
	}
	
	public String toString(){
		return " ESTACONS: "+ this.estacons 
			+" ESTAFORE: "+ this.estafore 
			+" ESTAFECH: "+ this.estafech 
			+" ESTAUSER: "+ this.estauser 
			+" ESTAESTA: "+ this.estaesta ;
	}
}
