package com.confianza.webapp.repository.framework.frmtransaccion;

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
@Table(name = "FRM_TRANSACCION")
public class FrmTransaccion {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_TRANSACCION_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_TRANSACCION_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_TRANSACCION_GEN")  
	@Column(name = "TRANCONS")
	protected Long trancons; 
	 
	@Column(name = "TRANSESI")
	protected Long transesi; 
	 
	@Column(name = "TRANFECR")
	protected Date tranfecr; 

	public FrmTransaccion(){
	
	}

	public Long getTrancons(){
		return trancons;
	}
	
	public void setTrancons(Long trancons){
		this.trancons = trancons;
	}

	public Long getTransesi(){
		return transesi;
	}
	
	public void setTransesi(Long transesi){
		this.transesi = transesi;
	}

	public Date getTranfecr(){
		return tranfecr;
	}
	
	public void setTranfecr(Date tranfecr){
		this.tranfecr = tranfecr;
	}


	static public String[] getNames(){
		return new String[]{ "trancons", "transesi", "tranfecr" };
	}		
	
	public String toString(){
		return " TRANCONS: "+ this.trancons 
			+" TRANSESI: "+ this.transesi 
			+" TRANFECR: "+ this.tranfecr ;
	}
}
