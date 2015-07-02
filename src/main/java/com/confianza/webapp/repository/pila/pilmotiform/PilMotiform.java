package com.confianza.webapp.repository.pila.pilmotiform;

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
@Table(name = "PIL_MOTIFORM")
public class PilMotiform {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "PIL_MOTIFORM_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="PIL_MOTIFORM_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PIL_MOTIFORM_GEN")  
	@Column(name = "MOFOCONS")
	protected Long mofocons; 
	 
	@Column(name = "MOFOFORE")
	protected Long mofofore; 
	 
	@Column(name = "MOFODEVO")
	protected Long mofodevo; 
	 
	@Column(name = "MOFODESC")
	protected String mofodesc; 
	 
	@Column(name = "MOFOFECH")
	protected Date mofofech; 
	 
	@Column(name = "MOFOUSER")
	protected String mofouser; 

	public PilMotiform(){
	
	}

	public Long getMofocons(){
		return mofocons;
	}
	
	public void setMofocons(Long mofocons){
		this.mofocons = mofocons;
	}

	public Long getMofofore(){
		return mofofore;
	}
	
	public void setMofofore(Long mofofore){
		this.mofofore = mofofore;
	}

	public Long getMofodevo(){
		return mofodevo;
	}
	
	public void setMofodevo(Long mofodevo){
		this.mofodevo = mofodevo;
	}

	public String getMofodesc(){
		return mofodesc;
	}
	
	public void setMofodesc(String mofodesc){
		this.mofodesc = mofodesc;
	}

	public Date getMofofech(){
		return mofofech;
	}
	
	public void setMofofech(Date mofofech){
		this.mofofech = mofofech;
	}

	public String getMofouser(){
		return mofouser;
	}
	
	public void setMofouser(String mofouser){
		this.mofouser = mofouser;
	}


	static public String[] getNames(){
		return new String[]{ "mofocons", "mofofore", "mofodevo", "mofodesc", "mofofech", "mofouser" };
	}		
	
	static public String getColumnNames(){
		return " mofocons, mofofore, mofodevo, mofodesc, mofofech, mofouser ";
	}
	
	public String toString(){
		return " MOFOCONS: "+ this.mofocons 
			+" MOFOFORE: "+ this.mofofore 
			+" MOFODEVO: "+ this.mofodevo 
			+" MOFODESC: "+ this.mofodesc 
			+" MOFOFECH: "+ this.mofofech 
			+" MOFOUSER: "+ this.mofouser ;
	}
}
