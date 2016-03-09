package com.confianza.webapp.repository.pila.pilususucu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
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
@Table(name = "PIL_USUSUCU")
public class PilUsusucu {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "PIL_USUSUCU_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="PIL_USUSUCU_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PIL_USUSUCU_GEN")  
	@Column(name = "USSUCONS")
	protected Long ussucons; 
	 
	@Column(name = "USSUUSUA")
	protected Long ussuusua; 
	 
	@Column(name = "USSUSUCU")
	protected Long ussusucu; 
	 
	@Column(name = "USSUESTA")
	protected String ussuesta; 

	public PilUsusucu(){
	
	}

	public Long getUssucons(){
		return ussucons;
	}
	
	public void setUssucons(Long ussucons){
		this.ussucons = ussucons;
	}

	public Long getUssuusua(){
		return ussuusua;
	}
	
	public void setUssuusua(Long ussuusua){
		this.ussuusua = ussuusua;
	}

	public Long getUssusucu(){
		return ussusucu;
	}
	
	public void setUssusucu(Long ussusucu){
		this.ussusucu = ussusucu;
	}

	public String getUssuesta(){
		return ussuesta;
	}
	
	public void setUssuesta(String ussuesta){
		this.ussuesta = ussuesta;
	}


	static public String[] getNames(){
		return new String[]{ "ussucons", "ussuusua", "ussusucu", "ussuesta" };
	}		
	
	static public String getColumnNames(){
		return " ussucons, ussuusua, ussusucu, ussuesta "; 
	}
	
	public String toString(){
		return " USSUCONS: "+ this.ussucons 
			+" USSUUSUA: "+ this.ussuusua 
			+" USSUSUCU: "+ this.ussusucu 
			+" USSUESTA: "+ this.ussuesta ;
	}
}
