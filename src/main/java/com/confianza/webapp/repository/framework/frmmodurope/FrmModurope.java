package com.confianza.webapp.repository.framework.frmmodurope;

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
@Table(name = "FRM_MODUROPE")
public class FrmModurope {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_MODUROPE_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_MODUROPE_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_MODUROPE_GEN")  
	@Column(name = "MOROCONS")
	protected Long morocons; 
	 
	@Column(name = "MOROMODU")
	protected Long moromodu; 
	 
	@Column(name = "MOROROPE")
	protected Long mororope; 

	public FrmModurope(){
	
	}

	public Long getMorocons(){
		return morocons;
	}
	
	public void setMorocons(Long morocons){
		this.morocons = morocons;
	}

	public Long getMoromodu(){
		return moromodu;
	}
	
	public void setMoromodu(Long moromodu){
		this.moromodu = moromodu;
	}

	public Long getMororope(){
		return mororope;
	}
	
	public void setMororope(Long mororope){
		this.mororope = mororope;
	}


	static public String[] getNames(){
		return new String[]{ "morocons", "moromodu", "mororope" };
	}		
	
	public String toString(){
		return " MOROCONS: "+ this.morocons 
			+" MOROMODU: "+ this.moromodu 
			+" MOROROPE: "+ this.mororope ;
	}
}
