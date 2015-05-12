package com.confianza.webapp.repository.framework.frmestaproc;

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
@Table(name = "FRM_ESTAPROC")
public class FrmEstaproc {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_ESTAPROC_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_ESTAPROC_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_ESTAPROC_GEN")  
	@Column(name = "ESPRCONS")
	protected Long esprcons; 
	 
	@Column(name = "ESPRPROC")
	protected String esprproc; 
	 
	@Column(name = "ESPRPORC")
	protected Long esprporc; 
	 
	@Column(name = "ESPRDESC")
	protected String esprdesc; 
	 
	@Column(name = "ESPRERRO")
	protected String esprerro; 
	 
	@Column(name = "ESPRUSER")
	protected String espruser; 
	 
	@Column(name = "ESPRFECR")
	protected Date esprfecr; 
	 
	@Column(name = "ESPRESTA")
	protected String espresta; 

	public FrmEstaproc(){
	
	}

	public Long getEsprcons(){
		return esprcons;
	}
	
	public void setEsprcons(Long esprcons){
		this.esprcons = esprcons;
	}

	public String getEsprproc(){
		return esprproc;
	}
	
	public void setEsprproc(String esprproc){
		this.esprproc = esprproc;
	}

	public Long getEsprporc(){
		return esprporc;
	}
	
	public void setEsprporc(Long esprporc){
		this.esprporc = esprporc;
	}

	public String getEsprdesc(){
		return esprdesc;
	}
	
	public void setEsprdesc(String esprdesc){
		this.esprdesc = esprdesc;
	}

	public String getEsprerro(){
		return esprerro;
	}
	
	public void setEsprerro(String esprerro){
		this.esprerro = esprerro;
	}

	public String getEspruser(){
		return espruser;
	}
	
	public void setEspruser(String espruser){
		this.espruser = espruser;
	}

	public Date getEsprfecr(){
		return esprfecr;
	}
	
	public void setEsprfecr(Date esprfecr){
		this.esprfecr = esprfecr;
	}

	public String getEspresta(){
		return espresta;
	}
	
	public void setEspresta(String espresta){
		this.espresta = espresta;
	}


	static public String[] getNames(){
		return new String[]{ "esprcons", "esprproc", "esprporc", "esprdesc", "esprerro", "espruser", "esprfecr", "espresta" };
	}		
	
	public String toString(){
		return " ESPRCONS: "+ this.esprcons 
			+" ESPRPROC: "+ this.esprproc 
			+" ESPRPORC: "+ this.esprporc 
			+" ESPRDESC: "+ this.esprdesc 
			+" ESPRERRO: "+ this.esprerro 
			+" ESPRUSER: "+ this.espruser 
			+" ESPRFECR: "+ this.esprfecr 
			+" ESPRESTA: "+ this.espresta ;
	}
}
