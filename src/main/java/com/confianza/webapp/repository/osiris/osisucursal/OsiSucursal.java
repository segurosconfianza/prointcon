package com.confianza.webapp.repository.osiris.osisucursal;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		osiris  
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
@Table(name = "OSI_SUCURSAL")
public class OsiSucursal {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "OSI_SUCURSAL_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="OSI_SUCURSAL_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "OSI_SUCURSAL_GEN")   
	@Column(name = "SUCUCONS")
	protected Long sucucons; 
	 
	@Column(name = "SUCUNOMB")
	protected String sucunomb; 

	public OsiSucursal(){
	
	}

	public Long getSucucons(){
		return sucucons;
	}
	
	public void setSucucons(Long sucucons){
		this.sucucons = sucucons;
	}

	public String getSucunomb(){
		return sucunomb;
	}
	
	public void setSucunomb(String sucunomb){
		this.sucunomb = sucunomb;
	}


	static public String[] getNames(){
		return new String[]{ "sucucons", "sucunomb" };
	}		
	
	static public String getColumnNames(){
		return " sucucons, sucunomb ";
	}
	
	public String toString(){
		return " SUCUCONS: "+ this.sucucons 
			+" SUCUNOMB: "+ this.sucunomb ;
	}
}
