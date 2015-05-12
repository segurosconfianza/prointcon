package com.confianza.webapp.repository.framework.frmarchivo;

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
@Table(name = "FRM_ARCHIVO")
public class FrmArchivo {
	@Id 
	// Habilite este codigo para generar automaticamente la llave primaria esta depende de una tabla
	@TableGenerator(name = "FRM_ARCHIVO_GEN", //<= nombre de la tabla con el cual se va a identificar la llave
	                 table = "FRM_PKID",              //<= Este string define la tabla dond se almacenan el consecutivo
	                 pkColumnName="PKIDNOMB",         //<= NOMBRE DE LA LLAVE PRIMARIA DE LA TABLA    
	                 valueColumnName="PKIDVALU",      //<= Valor del consecutivo en el que va la llave primaria
	                 pkColumnValue="FRM_ARCHIVO_PK", 
	                 initialValue = 1,                //<= Valor inicial de la llave primario
	                 allocationSize = 1)              //<= Valor a buscar por medio de la llave primaria
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "FRM_ARCHIVO_GEN")  
	@Column(name = "ARCHCODI")
	protected Long archcodi; 
	 
	@Column(name = "ARCHRUTA")
	protected String archruta; 
	 
	@Column(name = "ARCHNOMB")
	protected String archnomb; 
	 
	@Column(name = "ARCHMD5")
	protected String archmd5; 
	 
	@Column(name = "ARCHFECH")
	protected Date archfech; 
	 
	@Column(name = "ARCHMIME")
	protected String archmime; 
	 
	@Column(name = "ARCHSIZE")
	protected Long archsize; 

	public FrmArchivo(){
	
	}

	public Long getArchcodi(){
		return archcodi;
	}
	
	public void setArchcodi(Long archcodi){
		this.archcodi = archcodi;
	}

	public String getArchruta(){
		return archruta;
	}
	
	public void setArchruta(String archruta){
		this.archruta = archruta;
	}

	public String getArchnomb(){
		return archnomb;
	}
	
	public void setArchnomb(String archnomb){
		this.archnomb = archnomb;
	}

	public String getArchmd5(){
		return archmd5;
	}
	
	public void setArchmd5(String archmd5){
		this.archmd5 = archmd5;
	}

	public Date getArchfech(){
		return archfech;
	}
	
	public void setArchfech(Date archfech){
		this.archfech = archfech;
	}

	public String getArchmime(){
		return archmime;
	}
	
	public void setArchmime(String archmime){
		this.archmime = archmime;
	}

	public Long getArchsize(){
		return archsize;
	}
	
	public void setArchsize(Long archsize){
		this.archsize = archsize;
	}


	static public String[] getNames(){
		return new String[]{ "archvodi", "archruta", "archnomb", "archmd5", "archfech", "archmime", "archsize" };
	}		
	
	static public String getColumnNames(){
		return " ARCHCODI, ARCHRUTA, ARCHNOMB, ARCHMD5, ARCHFECH, ARCHMIME, ARCHSIZE ";
	}
	
	public String toString(){
		return " ARCHCODI: "+ this.archcodi 
			+" ARCHRUTA: "+ this.archruta 
			+" ARCHNOMB: "+ this.archnomb 
			+" ARCHMD5: "+ this.archmd5 
			+" ARCHFECH: "+ this.archfech 
			+" ARCHMIME: "+ this.archmime 
			+" ARCHSIZE: "+ this.archsize ;
	}
}
