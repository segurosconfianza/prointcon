package com.confianza.webapp.repository.cierre.cieestaproc;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		cierre  
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
@Table(name = "CIE_ESTAPROC")
public class CieEstaproc {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "CIE_ESTAPROC_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="CIE_ESTAPROC_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CIE_ESTAPROC_GEN")  
	@Column(name = "ESPRCONS")
	protected Long esprcons; 
	 
	@Column(name = "ESPRNOMB")
	protected String esprnomb; 
	 
	@Column(name = "ESPRPORC")
	protected Long esprporc; 
	 
	@Column(name = "ESPRDESC")
	protected String esprdesc; 
	 
	@Column(name = "ESPREROR")
	protected String espreror; 
	 
	@Column(name = "ESPRUSER")
	protected String espruser; 
	 
	@Column(name = "ESPRESTA")
	protected String espresta; 
	 
	@Column(name = "ESPRDUHO")
	protected Double esprduho; 
	 
	@Column(name = "ESPRFEIN")
	protected Date esprfein; 
	 
	@Column(name = "ESPRFEFI")
	protected Date esprfefi; 

	public CieEstaproc(){
	
	}

	public Long getEsprcons(){
		return esprcons;
	}
	
	public void setEsprcons(Long esprcons){
		this.esprcons = esprcons;
	}

	public String getEsprnomb(){
		return esprnomb;
	}
	
	public void setEsprnomb(String esprnomb){
		this.esprnomb = esprnomb;
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

	public String getEspreror(){
		return espreror;
	}
	
	public void setEspreror(String espreror){
		this.espreror = espreror;
	}

	public String getEspruser(){
		return espruser;
	}
	
	public void setEspruser(String espruser){
		this.espruser = espruser;
	}

	public String getEspresta(){
		return espresta;
	}
	
	public void setEspresta(String espresta){
		this.espresta = espresta;
	}

	public Double getEsprduho(){
		return esprduho;
	}
	
	public void setEsprduho(Double esprduho){
		this.esprduho = esprduho;
	}

	public Date getEsprfein(){
		return esprfein;
	}
	
	public void setEsprfein(Date esprfein){
		this.esprfein = esprfein;
	}

	public Date getEsprfefi(){
		return esprfefi;
	}
	
	public void setEsprfefi(Date esprfefi){
		this.esprfefi = esprfefi;
	}


	static public String[] getNames(){
		return new String[]{ "esprcons", "esprnomb", "esprporc", "esprdesc", "espreror", "espruser", "espresta", "esprduho", "esprfein", "esprfefi" };
	}		
	
	static public String getColumnNames(){
		return " esprcons, esprnomb, esprporc, esprdesc, espreror, espruser, espresta, esprduho, esprfein, esprfefi ";
	}
	
	public String toString(){
		return " ESPRCONS: "+ this.esprcons 
			+" ESPRNOMB: "+ this.esprnomb 
			+" ESPRPORC: "+ this.esprporc 
			+" ESPRDESC: "+ this.esprdesc 
			+" ESPREROR: "+ this.espreror 
			+" ESPRUSER: "+ this.espruser 
			+" ESPRESTA: "+ this.espresta 
			+" ESPRDUHO: "+ this.esprduho 
			+" ESPRFEIN: "+ this.esprfein 
			+" ESPRFEFI: "+ this.esprfefi ;
	}
}
