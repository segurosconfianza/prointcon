package com.confianza.webapp.repository.framework.frmroleperm;

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
@Table(name = "FRM_ROLEPERM")
public class FrmRoleperm {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_ROLEPERM_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_ROLEPERM_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_ROLEPERM_GEN")  
	@Column(name = "ROPECONS")
	protected Long ropecons; 
	 
	@Column(name = "ROPENOMB")
	protected String ropenomb; 
	 
	@Column(name = "ROPEDESC")
	protected String ropedesc; 
	 
	@Column(name = "ROPETIPO")
	protected String ropetipo; 

	public FrmRoleperm(){
	
	}

	public Long getRopecons(){
		return ropecons;
	}
	
	public void setRopecons(Long ropecons){
		this.ropecons = ropecons;
	}

	public String getRopenomb(){
		return ropenomb;
	}
	
	public void setRopenomb(String ropenomb){
		this.ropenomb = ropenomb;
	}

	public String getRopedesc(){
		return ropedesc;
	}
	
	public void setRopedesc(String ropedesc){
		this.ropedesc = ropedesc;
	}

	public String getRopetipo(){
		return ropetipo;
	}
	
	public void setRopetipo(String ropetipo){
		this.ropetipo = ropetipo;
	}


	static public String[] getNames(){
		return new String[]{ "ropecons", "ropenomb", "ropedesc", "ropetipo" };
	}		
	
	public String toString(){
		return " ROPECONS: "+ this.ropecons 
			+" ROPENOMB: "+ this.ropenomb 
			+" ROPEDESC: "+ this.ropedesc 
			+" ROPETIPO: "+ this.ropetipo ;
	}
}
