package com.confianza.webapp.repository.soporte.sopmotivo;

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
@Table(name = "SOP_MOTIVO")
public class SopMotivo {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "SOP_MOTIVO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="SOP_MOTIVO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SOP_MOTIVO_GEN")  
	@Column(name = "MOTICONS")
	protected Long moticons; 
	 
	@Column(name = "MOTITRAN")
	protected Long motitran; 
	 
	@Column(name = "MOTIDESC")
	protected String motidesc; 

	public SopMotivo(){
	
	}

	public Long getMoticons(){
		return moticons;
	}
	
	public void setMoticons(Long moticons){
		this.moticons = moticons;
	}

	public Long getMotitran(){
		return motitran;
	}
	
	public void setMotitran(Long motitran){
		this.motitran = motitran;
	}

	public String getMotidesc(){
		return motidesc;
	}
	
	public void setMotidesc(String motidesc){
		this.motidesc = motidesc;
	}


	static public String[] getNames(){
		return new String[]{ "MOTICONS", "MOTITRAN", "MOTIDESC" };
	}		
	
	static public String getColumnNames(){
		return " MOTICONS, MOTITRAN, MOTIDESC ";
	}
	
	public String toString(){
		return " MOTICONS: "+ this.moticons 
			+" MOTITRAN: "+ this.motitran 
			+" MOTIDESC: "+ this.motidesc ;
	}
}
