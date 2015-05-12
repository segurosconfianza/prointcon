package com.confianza.webapp.repository.framework.frmperfmodu;

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
@Table(name = "FRM_PERFMODU")
public class FrmPerfmodu {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_PERFMODU_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_PERFMODU_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_PERFMODU_GEN")  
	@Column(name = "PEMOCONS")
	protected Long pemocons; 
	 
	@Column(name = "PEMOPEFI")
	protected Long pemopefi; 
	 
	@Column(name = "PEMOMORO")
	protected Long pemomoro; 

	public FrmPerfmodu(){
	
	}

	public Long getPemocons(){
		return pemocons;
	}
	
	public void setPemocons(Long pemocons){
		this.pemocons = pemocons;
	}

	public Long getPemopefi(){
		return pemopefi;
	}
	
	public void setPemopefi(Long pemopefi){
		this.pemopefi = pemopefi;
	}

	public Long getPemomoro(){
		return pemomoro;
	}
	
	public void setPemomoro(Long pemomoro){
		this.pemomoro = pemomoro;
	}


	static public String[] getNames(){
		return new String[]{ "pemocons", "pemopefi", "pemomoro" };
	}		
	
	public String toString(){
		return " PEMOCONS: "+ this.pemocons 
			+" PEMOPEFI: "+ this.pemopefi 
			+" PEMOMORO: "+ this.pemomoro ;
	}
}
