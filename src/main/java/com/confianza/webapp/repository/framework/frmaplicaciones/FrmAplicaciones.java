package com.confianza.webapp.repository.framework.frmaplicaciones;

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
@Table(name = "FRM_APLICACIONES")
public class FrmAplicaciones {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_APLICACIONES_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_APLICACIONES_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_APLICACIONES_GEN")  
	@Column(name = "APLICONS")
	protected Long aplicons; 
	 
	@Column(name = "APLINOMB")
	protected String aplinomb; 
	 
	@Column(name = "APLIDESC")
	protected String aplidesc; 
	 
	@Column(name = "APLIESTA")
	protected String apliesta; 
	 
	@Column(name = "APLIFECR")
	protected Date aplifecr; 

	public FrmAplicaciones(){
	
	}

	public Long getAplicons(){
		return aplicons;
	}
	
	public void setAplicons(Long aplicons){
		this.aplicons = aplicons;
	}

	public String getAplinomb(){
		return aplinomb;
	}
	
	public void setAplinomb(String aplinomb){
		this.aplinomb = aplinomb;
	}

	public String getAplidesc(){
		return aplidesc;
	}
	
	public void setAplidesc(String aplidesc){
		this.aplidesc = aplidesc;
	}

	public String getApliesta(){
		return apliesta;
	}
	
	public void setApliesta(String apliesta){
		this.apliesta = apliesta;
	}

	public Date getAplifecr(){
		return aplifecr;
	}
	
	public void setAplifecr(Date aplifecr){
		this.aplifecr = aplifecr;
	}


	static public String[] getNames(){
		return new String[]{ "aplicons", "aplinomb", "aplidesc", "apliesta", "aplifecr" };
	}		
	
	public String toString(){
		return " APLICONS: "+ this.aplicons 
			+" APLINOMB: "+ this.aplinomb 
			+" APLIDESC: "+ this.aplidesc 
			+" APLIESTA: "+ this.apliesta 
			+" APLIFECR: "+ this.aplifecr ;
	}
}
