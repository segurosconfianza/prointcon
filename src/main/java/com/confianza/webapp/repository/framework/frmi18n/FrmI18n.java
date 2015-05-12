package com.confianza.webapp.repository.framework.frmi18n;

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
@Table(name = "FRM_I18N")
public class FrmI18n {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_I18N_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_I18N_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_I18N_GEN")  
	@Column(name = "ETINCONS")
	protected Long etincons; 
	 
	@Column(name = "ETINMODU")
	protected Long etinmodu; 
	 
	@Column(name = "ETINCAMP")
	protected String etincamp; 
	 
	@Column(name = "ETINETIQ")
	protected String etinetiq; 

	public FrmI18n(){
	
	}

	public Long getEtincons(){
		return etincons;
	}
	
	public void setEtincons(Long etincons){
		this.etincons = etincons;
	}

	public Long getEtinmodu(){
		return etinmodu;
	}
	
	public void setEtinmodu(Long etinmodu){
		this.etinmodu = etinmodu;
	}

	public String getEtincamp(){
		return etincamp;
	}
	
	public void setEtincamp(String etincamp){
		this.etincamp = etincamp;
	}

	public String getEtinetiq(){
		return etinetiq;
	}
	
	public void setEtinetiq(String etinetiq){
		this.etinetiq = etinetiq;
	}


	static public String[] getNames(){
		return new String[]{ "etincons", "etinmodu", "etincamp", "etinetiq" };
	}		
	
	public String toString(){
		return " ETINCONS: "+ this.etincons 
			+" ETINMODU: "+ this.etinmodu 
			+" ETINCAMP: "+ this.etincamp 
			+" ETINETIQ: "+ this.etinetiq ;
	}
}
