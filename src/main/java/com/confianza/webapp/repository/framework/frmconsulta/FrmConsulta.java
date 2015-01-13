package com.confianza.webapp.repository.framework.frmconsulta;

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
@Table(name = "FRM_CONSULTA")
public class FrmConsulta {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_CONSULTA_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_CONSULTA_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_CONSULTA_GEN")  
	@Column(name = "CONSCONS")
	protected Long conscons; 
	 
	@Column(name = "CONSMODU")
	protected Long consmodu; 
	 
	@Column(name = "CONSNOMB")
	protected String consnomb; 
	 
	@Column(name = "CONSDESC")
	protected String consdesc; 
	 
	@Column(name = "CONSCACO")
	protected String conscaco; 
	 
	@Column(name = "CONSTIPO")
	protected String constipo; 
	 
	@Column(name = "CONSLSQL")
	protected String conslsql; 
	 
	@Column(name = "CONSCOLU")
	protected String conscolu; 
	 
	@Column(name = "CONSTICO")
	protected String constico; 

	public FrmConsulta(){
	
	}

	public Long getConscons(){
		return conscons;
	}
	
	public void setConscons(Long conscons){
		this.conscons = conscons;
	}

	public Long getConsmodu(){
		return consmodu;
	}
	
	public void setConsmodu(Long consmodu){
		this.consmodu = consmodu;
	}

	public String getConsnomb(){
		return consnomb;
	}
	
	public void setConsnomb(String consnomb){
		this.consnomb = consnomb;
	}

	public String getConsdesc(){
		return consdesc;
	}
	
	public void setConsdesc(String consdesc){
		this.consdesc = consdesc;
	}

	public String getConscaco(){
		return conscaco;
	}
	
	public void setConscaco(String conscaco){
		this.conscaco = conscaco;
	}

	public String getConstipo(){
		return constipo;
	}
	
	public void setConstipo(String constipo){
		this.constipo = constipo;
	}

	public String getConslsql(){
		return conslsql;
	}
	
	public void setConslsql(String conslsql){
		this.conslsql = conslsql;
	}

	public String getConscolu(){
		return conscolu;
	}
	
	public void setConscolu(String conscolu){
		this.conscolu = conscolu;
	}

	public String getConstico(){
		return constico;
	}
	
	public void setConstico(String constico){
		this.constico = constico;
	}


	static public String[] getNames(){
		return new String[]{ "CONSCONS", "CONSMODU", "CONSNOMB", "CONSDESC", "CONSCACO", "CONSTIPO", "CONSLSQL", "CONSCOLU", "CONSTICO" };
	}		
	
	static public String getColumnNames(){
		return " CONSCONS, CONSMODU, CONSNOMB, CONSDESC, CONSCACO, CONSTIPO, CONSLSQL, CONSCOLU, CONSTICO ";
	}
	
	public String toString(){
		return " CONSCONS: "+ this.conscons 
			+" CONSMODU: "+ this.consmodu 
			+" CONSNOMB: "+ this.consnomb 
			+" CONSDESC: "+ this.consdesc 
			+" CONSCACO: "+ this.conscaco 
			+" CONSTIPO: "+ this.constipo 
			+" CONSLSQL: "+ this.conslsql 
			+" CONSCOLU: "+ this.conscolu 
			+" CONSTICO: "+ this.constico ;
	}
}
