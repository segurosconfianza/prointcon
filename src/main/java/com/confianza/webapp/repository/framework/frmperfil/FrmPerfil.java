package com.confianza.webapp.repository.framework.frmperfil;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "FRM_PERFIL")
public class FrmPerfil {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_PERFIL_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_PERFIL_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_PERFIL_GEN")  
	@Column(name = "PEFICONS")
	protected Long peficons; 
	 
	@Column(name = "PEFINOMB")
	protected String pefinomb; 
	 
	@Column(name = "PEFIDESC")
	protected String pefidesc; 
	 
	@Column(name = "PEFIFECR")
	protected Date pefifecr; 

	@Column(name = "PEFIESTA")
	protected String pefiesta;
	
	public FrmPerfil(){
	
	}

	public Long getPeficons(){
		return peficons;
	}
	
	public void setPeficons(Long peficons){
		this.peficons = peficons;
	}

	public String getPefinomb(){
		return pefinomb;
	}
	
	/**
	 * @return the pefiesta
	 */
	public String getPefiesta() {
		return pefiesta;
	}

	/**
	 * @param pefiesta the pefiesta to set
	 */
	public void setPefiesta(String pefiesta) {
		this.pefiesta = pefiesta;
	}

	public void setPefinomb(String pefinomb){
		this.pefinomb = pefinomb;
	}

	public String getPefidesc(){
		return pefidesc;
	}
	
	public void setPefidesc(String pefidesc){
		this.pefidesc = pefidesc;
	}

	public Date getPefifecr(){
		return pefifecr;
	}
	
	public void setPefifecr(Date pefifecr){
		this.pefifecr = pefifecr;
	}


	static public String[] getNames(){
		return new String[]{ "peficons", "peficons", "pefidesc", "pefifecr", "pefiesta" };
	}		
	
	public String toString(){
		return " PEFICONS: "+ this.peficons 
			+" PEFINOMB: "+ this.pefinomb 
			+" PEFIDESC: "+ this.pefidesc 
			+" PEFIFECR: "+ this.pefifecr 
			+" PEFIESTA: "+ this.pefiesta ;
	}
}
