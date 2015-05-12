package com.confianza.webapp.repository.framework.frmsesion;

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
@Table(name = "FRM_SESION")
public class FrmSesion {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_SESION_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_SESION_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_SESION_GEN")  
	@Column(name = "SESICONS")
	protected Long sesicons; 
	 
	@Column(name = "SESIUSUA")
	protected String sesiusua; 
	 
	@Column(name = "SESIFECR")
	protected Date sesifecr; 
	 
	@Column(name = "SESISESI")
	protected String sesisesi; 

	public FrmSesion(){
	
	}

	public Long getSesicons(){
		return sesicons;
	}
	
	public void setSesicons(Long sesicons){
		this.sesicons = sesicons;
	}

	public String getSesiusua(){
		return sesiusua;
	}
	
	public void setSesiusua(String sesiusua){
		this.sesiusua = sesiusua;
	}

	public Date getSesifecr(){
		return sesifecr;
	}
	
	public void setSesifecr(Date sesifecr){
		this.sesifecr = sesifecr;
	}

	public String getSesisesi(){
		return sesisesi;
	}
	
	public void setSesisesi(String sesisesi){
		this.sesisesi = sesisesi;
	}


	static public String[] getNames(){
		return new String[]{ "sesicons", "sesiusua", "sesifecr", "sesisesi" };
	}		
	
	public String toString(){
		return " SESICONS: "+ this.sesicons 
			+" SESIUSUA: "+ this.sesiusua 
			+" SESIFECR: "+ this.sesifecr 
			+" SESISESI: "+ this.sesisesi ;
	}
}
