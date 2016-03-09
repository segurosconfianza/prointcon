package com.confianza.webapp.repository.osiris.osisucursal;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		osiris  
  */                          

import java.util.List;
import com.confianza.webapp.utils.Filter;

public interface OsiSucursalRepository {
	
	public OsiSucursal list(Long id);
	
	public List<OsiSucursal> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public OsiSucursal update(OsiSucursal osisucursal);
	
	public void delete(OsiSucursal osisucursal);
	
	public OsiSucursal insert(OsiSucursal osisucursal);
	
	public int getCount(List<Filter> filters);
}
