package com.confianza.webapp.repository.framework.frmpkid;

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
@Table(name = "FRM_PKID")
public class FrmPkid {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_PKID_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_PKID_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_PKID_GEN")  
	@Column(name = "PKIDCONS")
	protected Long pkidcons; 
	 
	@Column(name = "PKIDNOMB")
	protected String pkidnomb; 
	 
	@Column(name = "PKIDVALU")
	protected Long pkidvalu; 

	public FrmPkid(){
	
	}

	public Long getPkidcons(){
		return pkidcons;
	}
	
	public void setPkidcons(Long pkidcons){
		this.pkidcons = pkidcons;
	}

	public String getPkidnomb(){
		return pkidnomb;
	}
	
	public void setPkidnomb(String pkidnomb){
		this.pkidnomb = pkidnomb;
	}

	public Long getPkidvalu(){
		return pkidvalu;
	}
	
	public void setPkidvalu(Long pkidvalu){
		this.pkidvalu = pkidvalu;
	}


	static public String[] getNames(){
		return new String[]{ "pkidcons", "pkidnomb", "pkidvalu" };
	}		
	
	public String toString(){
		return " PKIDCONS: "+ this.pkidcons 
			+" PKIDNOMB: "+ this.pkidnomb 
			+" PKIDVALU: "+ this.pkidvalu ;
	}
}
