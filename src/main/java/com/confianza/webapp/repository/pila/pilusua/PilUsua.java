package com.confianza.webapp.repository.pila.pilusua;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "PIL_USUA")
public class PilUsua {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "PIL_USUA_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="PIL_USUA_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PIL_USUA_GEN")  
	@Column(name = "USUACONS")
	protected Long usuacons; 
	 
	@Column(name = "USUAUNIT")
	protected String usuaunit; 
	 
	@Column(name = "USUADIVE")
	protected String usuadive; 
	 
	@Column(name = "USUATIIN")
	protected String usuatiin; 
	 
	@Column(name = "USUARAZO")
	protected String usuarazo; 
	 
	@Column(name = "USUANOMB")
	protected String usuanomb; 
	 
	@Column(name = "USUAAPEL")
	protected String usuaapel; 
	 
	@Column(name = "USUAEMAI")
	protected String usuaemai; 
	 
	@Column(name = "USUATELE")
	protected String usuatele; 
	 
	@Column(name = "USUAPECO")
	protected String usuapeco; 
	 
	@Column(name = "USUAUSUA")
	protected String usuausua; 
	 
	@Column(name = "USUAPASS")
	protected String usuapass; 
	 
	@Column(name = "USUATIPO")
	protected Long usuatipo; 
	 
	@Column(name = "USUASUCU")
	protected Long usuasucu; 
	 
	@Column(name = "USUAESTA")
	protected String usuaesta; 

	public PilUsua(){
	
	}

	public Long getUsuacons(){
		return usuacons;
	}
	
	public void setUsuacons(Long usuacons){
		this.usuacons = usuacons;
	}

	public String getUsuaunit(){
		return usuaunit;
	}
	
	public void setUsuaunit(String usuaunit){
		this.usuaunit = usuaunit;
	}

	public String getUsuadive(){
		return usuadive;
	}
	
	public void setUsuadive(String usuadive){
		this.usuadive = usuadive;
	}

	public String getUsuatiin(){
		return usuatiin;
	}
	
	public void setUsuatiin(String usuatiin){
		this.usuatiin = usuatiin;
	}

	public String getUsuarazo(){
		return usuarazo;
	}
	
	public void setUsuarazo(String usuarazo){
		this.usuarazo = usuarazo;
	}

	public String getUsuanomb(){
		return usuanomb;
	}
	
	public void setUsuanomb(String usuanomb){
		this.usuanomb = usuanomb;
	}

	public String getUsuaapel(){
		return usuaapel;
	}
	
	public void setUsuaapel(String usuaapel){
		this.usuaapel = usuaapel;
	}

	public String getUsuaemai(){
		return usuaemai;
	}
	
	public void setUsuaemai(String usuaemai){
		this.usuaemai = usuaemai;
	}

	public String getUsuatele(){
		return usuatele;
	}
	
	public void setUsuatele(String usuatele){
		this.usuatele = usuatele;
	}

	public String getUsuapeco(){
		return usuapeco;
	}
	
	public void setUsuapeco(String usuapeco){
		this.usuapeco = usuapeco;
	}

	public String getUsuausua(){
		return usuausua;
	}
	
	public void setUsuausua(String usuausua){
		this.usuausua = usuausua;
	}

	public String getUsuapass(){
		return usuapass;
	}
	
	public void setUsuapass(String usuapass){
		if(usuapass.isEmpty())
			this.usuapass=usuapass;
		else
			this.usuapass = codeMd5(usuapass);
	}

	private String codeMd5(String usuapass) {
		MessageDigest md;
		//convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(usuapass.getBytes());
			byte byteData[] = md.digest();
			 
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return sb.toString();
	}

	public Long getUsuatipo(){
		return usuatipo;
	}
	
	public void setUsuatipo(Long usuatipo){
		this.usuatipo = usuatipo;
	}

	public Long getUsuasucu(){
		return usuasucu;
	}
	
	public void setUsuasucu(Long usuasucu){
		this.usuasucu = usuasucu;
	}

	public String getUsuaesta(){
		return usuaesta;
	}
	
	public void setUsuaesta(String usuaesta){
		this.usuaesta = usuaesta;
	}


	static public String[] getNames(){
		return new String[]{ "usuacons", "usuaunit", "usuadive", "usuatiin", "usuarazo", "usuanomb", "usuaapel", "usuaemai", "usuatele", "usuapeco", "usuausua", "usuapass", "usuatipo", "usuasucu", "usuaesta" };
	}		
	
	static public String getColumnNames(){
		return " usuacons, usuaunit, usuadive, usuatiin, usuarazo, usuanomb, usuaapel, usuaemai, usuatele, usuapeco, usuausua, usuapass, usuatipo, usuasucu, usuaesta ";
	}
	
	public String toString(){
		return " USUACONS: "+ this.usuacons 
			+" USUAUNIT: "+ this.usuaunit 
			+" USUADIVE: "+ this.usuadive 
			+" USUATIIN: "+ this.usuatiin 
			+" USUARAZO: "+ this.usuarazo 
			+" USUANOMB: "+ this.usuanomb 
			+" USUAAPEL: "+ this.usuaapel 
			+" USUAEMAI: "+ this.usuaemai 
			+" USUATELE: "+ this.usuatele 
			+" USUAPECO: "+ this.usuapeco 
			+" USUAUSUA: "+ this.usuausua 
			+" USUAPASS: "+ this.usuapass 
			+" USUATIPO: "+ this.usuatipo 
			+" USUASUCU: "+ this.usuasucu 
			+" USUAESTA: "+ this.usuaesta ;
	}
}
