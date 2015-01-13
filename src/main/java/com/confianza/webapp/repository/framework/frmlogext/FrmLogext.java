package com.confianza.webapp.repository.framework.frmlogext;

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
@Table(name = "FRM_LOGEXT")
public class FrmLogext {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_LOGEXT_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_LOGEXT_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_LOGEXT_GEN")  
	@Column(name = "LOEXCONS")
	protected Long loexcons; 
	 
	@Column(name = "LOEXTABL")
	protected String loextabl; 
	 
	@Column(name = "LOEXACCI")
	protected String loexacci; 
	 
	@Column(name = "LOEXDACO")
	protected String loexdaco; 
	 
	@Column(name = "LOEXDATA")
	protected String loexdata; 
	 
	@Column(name = "LOEXFECR")
	protected Date loexfecr; 

	public FrmLogext(){
	
	}

	public Long getLoexcons(){
		return loexcons;
	}
	
	public void setLoexcons(Long loexcons){
		this.loexcons = loexcons;
	}

	public String getLoextabl(){
		return loextabl;
	}
	
	public void setLoextabl(String loextabl){
		this.loextabl = loextabl;
	}

	public String getLoexacci(){
		return loexacci;
	}
	
	public void setLoexacci(String loexacci){
		this.loexacci = loexacci;
	}

	public String getLoexdaco(){
		return loexdaco;
	}
	
	public void setLoexdaco(String loexdaco){
		this.loexdaco = loexdaco;
	}

	public String getLoexdata(){
		return loexdata;
	}
	
	public void setLoexdata(String loexdata){
		this.loexdata = loexdata;
	}

	public Date getLoexfecr(){
		return loexfecr;
	}
	
	public void setLoexfecr(Date loexfecr){
		this.loexfecr = loexfecr;
	}


	static public String[] getNames(){
		return new String[]{ "LOEXCONS", "LOEXTABL", "LOEXACCI", "LOEXDACO", "LOEXDATA", "LOEXFECR" };
	}		
	
	static public String getColumnNames(){
		return " LOEXCONS, LOEXTABL, LOEXACCI, LOEXDACO, LOEXDATA, LOEXFECR ";
	}
	
	public String toString(){
		return " LOEXCONS: "+ this.loexcons 
			+" LOEXTABL: "+ this.loextabl 
			+" LOEXACCI: "+ this.loexacci 
			+" LOEXDACO: "+ this.loexdaco 
			+" LOEXDATA: "+ this.loexdata 
			+" LOEXFECR: "+ this.loexfecr ;
	}
}
