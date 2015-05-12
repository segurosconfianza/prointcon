package com.confianza.webapp.repository.framework.frmtablas;

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
@Table(name = "FRM_TABLAS")
public class FrmTablas {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_TABLAS_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_TABLAS_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_TABLAS_GEN")  
	@Column(name = "TABLCONS")
	protected Long tablcons; 
	 
	@Column(name = "TABLMODU")
	protected Long tablmodu; 
	 
	@Column(name = "TABLCODI")
	protected String tablcodi; 
	 
	@Column(name = "TABLCLAV")
	protected String tablclav; 
	 
	@Column(name = "TABLVAST")
	protected String tablvast; 
	 
	@Column(name = "TABLVAIN")
	protected Long tablvain; 
	 
	@Column(name = "TABLFECR")
	protected Date tablfecr; 
	 
	@Column(name = "TABLEXTR")
	protected String tablextr; 

	public FrmTablas(){
	
	}

	public Long getTablcons(){
		return tablcons;
	}
	
	public void setTablcons(Long tablcons){
		this.tablcons = tablcons;
	}

	public Long getTablmodu(){
		return tablmodu;
	}
	
	public void setTablmodu(Long tablmodu){
		this.tablmodu = tablmodu;
	}

	public String getTablcodi(){
		return tablcodi;
	}
	
	public void setTablcodi(String tablcodi){
		this.tablcodi = tablcodi;
	}

	public String getTablclav(){
		return tablclav;
	}
	
	public void setTablclav(String tablclav){
		this.tablclav = tablclav;
	}

	public String getTablvast(){
		return tablvast;
	}
	
	public void setTablvast(String tablvast){
		this.tablvast = tablvast;
	}

	public Long getTablvain(){
		return tablvain;
	}
	
	public void setTablvain(Long tablvain){
		this.tablvain = tablvain;
	}

	public Date getTablfecr(){
		return tablfecr;
	}
	
	public void setTablfecr(Date tablfecr){
		this.tablfecr = tablfecr;
	}

	public String getTablextr(){
		return tablextr;
	}
	
	public void setTablextr(String tablextr){
		this.tablextr = tablextr;
	}


	static public String[] getNames(){
		return new String[]{ "tablcons", "tablmodu", "tablcodi", "tablclav", "tablvast", "tablvain", "tablfecr", "tablextr" };
	}		
	
	public String toString(){
		return " TABLCONS: "+ this.tablcons 
			+" TABLMODU: "+ this.tablmodu 
			+" TABLCODI: "+ this.tablcodi 
			+" TABLCLAV: "+ this.tablclav 
			+" TABLVAST: "+ this.tablvast 
			+" TABLVAIN: "+ this.tablvain 
			+" TABLFECR: "+ this.tablfecr 
			+" TABLEXTR: "+ this.tablextr ;
	}
}
