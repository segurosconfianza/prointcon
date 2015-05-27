package com.confianza.webapp.repository.formatos.fmtcampo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
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
@Table(name = "FMT_CAMPO")
public class FmtCampo {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FMT_CAMPO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FMT_CAMPO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FMT_CAMPO_GEN")  
	@Column(name = "CAMPCONS")
	protected Long campcons; 
	 
	@Column(name = "CAMPVEFO")
	protected Long campvefo; 
	 
	@Column(name = "CAMPAUTO")
	protected Long campauto; 
	 
	@Column(name = "CAMPCOMB")
	protected Long campcomb; 
	 
	@Column(name = "CAMPNOMB")
	protected String campnomb; 
	 
	@Column(name = "CAMPORDEN")
	protected Long camporden; 
	 
	@Column(name = "CAMPTIPO")
	protected String camptipo; 
	 
	@Column(name = "CAMPREQU")
	protected Long camprequ; 
	 
	@Column(name = "CAMPVALI")
	protected Long campvali; 
	 
	@Column(name = "CAMPESTA")
	protected String campesta; 

	public FmtCampo(){
	
	}

	public Long getCampcons(){
		return campcons;
	}
	
	public void setCampcons(Long campcons){
		this.campcons = campcons;
	}

	public Long getCampvefo(){
		return campvefo;
	}
	
	public void setCampvefo(Long campvefo){
		this.campvefo = campvefo;
	}

	public Long getCampauto(){
		return campauto;
	}
	
	public void setCampauto(Long campauto){
		this.campauto = campauto;
	}

	public Long getCampcomb(){
		return campcomb;
	}
	
	public void setCampcomb(Long campcomb){
		this.campcomb = campcomb;
	}

	public String getCampnomb(){
		return campnomb;
	}
	
	public void setCampnomb(String campnomb){
		this.campnomb = campnomb;
	}

	public Long getCamporden(){
		return camporden;
	}
	
	public void setCamporden(Long camporden){
		this.camporden = camporden;
	}

	public String getCamptipo(){
		return camptipo;
	}
	
	public void setCamptipo(String camptipo){
		this.camptipo = camptipo;
	}

	public Long getCamprequ(){
		return camprequ;
	}
	
	public void setCamprequ(Long camprequ){
		this.camprequ = camprequ;
	}

	public Long getCampvali(){
		return campvali;
	}
	
	public void setCampvali(Long campvali){
		this.campvali = campvali;
	}

	public String getCampesta(){
		return campesta;
	}
	
	public void setCampesta(String campesta){
		this.campesta = campesta;
	}


	static public String[] getNames(){
		return new String[]{ "campcons", "campvefo", "campauto", "campcomb", "campnomb", "camporden", "camptipo", "camprequ", "campvali", "campesta" };
	}		
	
	static public String getColumnNames(){
		return " CAMPCONS, CAMPVEFO, CAMPAUTO, CAMPCOMB, CAMPNOMB, CAMPORDEN, CAMPTIPO, CAMPREQU, CAMPVALI, CAMPESTA ";
	}
	
	public String toString(){
		return " CAMPCONS: "+ this.campcons 
			+" CAMPVEFO: "+ this.campvefo 
			+" CAMPAUTO: "+ this.campauto 
			+" CAMPCOMB: "+ this.campcomb 
			+" CAMPNOMB: "+ this.campnomb 
			+" CAMPORDEN: "+ this.camporden 
			+" CAMPTIPO: "+ this.camptipo 
			+" CAMPREQU: "+ this.camprequ 
			+" CAMPVALI: "+ this.campvali 
			+" CAMPESTA: "+ this.campesta ;
	}
}
