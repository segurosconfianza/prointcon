package com.confianza.webapp.service.osiris.osisucursal;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		osiris  
  */                          

import java.util.List;

import com.confianza.webapp.repository.osiris.osisucursal.OsiSucursal;
import com.confianza.webapp.utils.Filter;

public interface OsiSucursalService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(OsiSucursal osisucursal);
	
	public String update(OsiSucursal osisucursal);
	
	public void delete(OsiSucursal osisucursal);	
	
	public int getCount(List<Filter> filters);
	
}
