package com.confianza.webapp.repository.framework.frmmenu;

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
@Table(name = "FRM_MENU")
public class FrmMenu {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_MENU_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_MENU_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_MENU_GEN")  
	@Column(name = "MENUCONS")
	protected Long menucons; 
	 
	@Column(name = "MENUPADR")
	protected Long menupadr; 
	 
	@Column(name = "MENUMODU")
	protected Long menumodu; 
	 
	@Column(name = "MENUICON")
	protected String menuicon; 
	 
	@Column(name = "MENUTITU")
	protected String menutitu; 
	 
	@Column(name = "MENUORDE")
	protected Long menuorde; 
	 
	@Column(name = "MENUESTA")
	protected String menuesta; 

	public FrmMenu(){
	
	}

	public Long getMenucons(){
		return menucons;
	}
	
	public void setMenucons(Long menucons){
		this.menucons = menucons;
	}

	public Long getMenupadr(){
		return menupadr;
	}
	
	public void setMenupadr(Long menupadr){
		this.menupadr = menupadr;
	}

	public Long getMenumodu(){
		return menumodu;
	}
	
	public void setMenumodu(Long menumodu){
		this.menumodu = menumodu;
	}

	public String getMenuicon(){
		return menuicon;
	}
	
	public void setMenuicon(String menuicon){
		this.menuicon = menuicon;
	}

	public String getMenutitu(){
		return menutitu;
	}
	
	public void setMenutitu(String menutitu){
		this.menutitu = menutitu;
	}

	public Long getMenuorde(){
		return menuorde;
	}
	
	public void setMenuorde(Long menuorde){
		this.menuorde = menuorde;
	}

	public String getMenuesta(){
		return menuesta;
	}
	
	public void setMenuesta(String menuesta){
		this.menuesta = menuesta;
	}


	static public String[] getNames(){
		return new String[]{ "menucons", "menupadr", "menumodu", "menuicon", "menutitu", "menuorde", "menuesta" };
	}		
	
	public String toString(){
		return " MENUCONS: "+ this.menucons 
			+" MENUPADR: "+ this.menupadr 
			+" MENUMODU: "+ this.menumodu 
			+" MENUICON: "+ this.menuicon 
			+" MENUTITU: "+ this.menutitu 
			+" MENUORDE: "+ this.menuorde 
			+" MENUESTA: "+ this.menuesta ;
	}
}
