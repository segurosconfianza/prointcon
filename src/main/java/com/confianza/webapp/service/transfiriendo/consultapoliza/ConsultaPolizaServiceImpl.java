package com.confianza.webapp.service.transfiriendo.consultapoliza;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;
import com.confianza.webapp.repository.transfiriendo.consultapoliza.Campo;
import com.confianza.webapp.repository.transfiriendo.consultapoliza.Certificado;
import com.confianza.webapp.repository.transfiriendo.consultapoliza.Poliza;
import com.confianza.webapp.service.framework.frmconsulta.FrmConsultaService;
import com.confianza.webapp.service.framework.frmparametro.FrmParametroService;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.thoughtworks.xstream.XStream;

@Service
public class ConsultaPolizaServiceImpl implements ConsultaPolizaService{
	
	@Autowired
	private XStream xstream;
	
	@Autowired
	private FrmTablasService frmTablasService;
	
	@Autowired
	private FrmConsultaService frmConsultaService;
	
	@Autowired
	private FrmParametroService frmParametroService;			
	
	@Override
	public String listPoliza(String SUCURSAL, String PRODUCTO, String POLIZA, String CERTIFICADO,HttpServletRequest request){
		
		FrmConsulta query = frmConsultaService.listName("PRODUCTO "+PRODUCTO);
		List<FrmParametro> parametersQuery=this.frmParametroService.listParamsCosuType(query.getConscons());
		
		Map<String, Object> mapParameters=createParametersQuery(query.getConslsql(), SUCURSAL, PRODUCTO, POLIZA, CERTIFICADO);
		List<Object[]> data=frmConsultaService.loadListData(query, mapParameters, parametersQuery);
		Poliza poliza=new Poliza(PRODUCTO);

		if(CERTIFICADO!=null){	
			poliza = lookForCertif(SUCURSAL, PRODUCTO, POLIZA, CERTIFICADO, query, mapParameters, data, poliza);
		} 
		else{
			poliza=createPoliza(query, data, mapParameters, poliza, SUCURSAL, PRODUCTO, POLIZA, CERTIFICADO);				
		}
		
		xstream.alias("Campo", Campo.class);
		xstream.alias("Certificado", Certificado.class);
	    xstream.alias("Poliza", Poliza.class);	    
	    
		return xstream.toXML(poliza);
		
	}

	private Poliza lookForCertif(String SUCURSAL, String PRODUCTO, String POLIZA, String CERTIFICADO, FrmConsulta query, Map<String, Object> mapParameters, List<Object[]> data, Poliza poliza) {
		for(Object[] objCertificado:data){				
			if(objCertificado[3].toString().equals(CERTIFICADO)){
				List<Object[]> dataOne=new ArrayList<Object[]>();
				dataOne.add(objCertificado);
				poliza=createPoliza(query, dataOne, mapParameters, poliza, SUCURSAL, PRODUCTO, POLIZA, CERTIFICADO);	
			}
		}
		return poliza;
	}

	private Map<String, Object> createParametersQuery( String sql, String SUCURSAL, String PRODUCTO, String POLIZA, String CERTIFICADO) {
		Map<String, Object> parametersChild=new HashMap<String, Object>();			
		
		if(sql.contains(":SUCURSAL"))
			parametersChild.put("SUCURSAL", SUCURSAL);
		if(sql.contains(":PRODUCTO"))
			parametersChild.put("PRODUCTO", PRODUCTO);
		if(sql.contains(":POLIZA"))
			parametersChild.put("POLIZA", POLIZA);
		if(sql.contains(":CERTIFICADO"))
			parametersChild.put("CERTIFICADO", CERTIFICADO);
		
		return parametersChild;
	}
	
	private Poliza createPoliza(FrmConsulta query, List<Object[]> data, Map<String, Object> mapParameters, Poliza poliza, String SUCURSAL, String PRODUCTO, String POLIZA, String CERTIFICADO) {
		poliza=createCertificados(query, data, poliza, SUCURSAL, PRODUCTO, POLIZA, CERTIFICADO);
		return poliza;
	}

	private Poliza createCertificados(FrmConsulta query, List<Object[]> data, Poliza poliza, String SUCURSAL, String PRODUCTO, String POLIZA, String CERTIFICADO) {
		
		for(Object[] objCertificado:data){
			Certificado certificado=new Certificado();		
			CERTIFICADO=objCertificado[3].toString();
			certificado=mapForTypeConsult(query, objCertificado, certificado, PRODUCTO, null, null);
			List<FrmConsulta> hijosConsulta = frmConsultaService.listQueryChilds(query.getConscons().toString());
			for(FrmConsulta consulta:hijosConsulta){
				List<FrmParametro> parametersQuery=this.frmParametroService.listParamsCosuType(query.getConscons());
				Map<String, Object> mapParameters=createParametersQuery(consulta.getConslsql(), SUCURSAL, PRODUCTO, POLIZA, CERTIFICADO);
				List<Object[]> dataQ=frmConsultaService.loadListData(consulta, mapParameters, parametersQuery);
				for(Object[] objData:dataQ) 
					certificado=mapForTypeConsult(consulta, objData, certificado, PRODUCTO, parametersQuery, mapParameters);
			}
			poliza.addCertificado(certificado);
		}	
		return poliza;
	}

	private Certificado mapForTypeConsult(FrmConsulta query, Object[] objCertificado, Certificado certificado, String PRODUCTO, List<FrmParametro> parametersQuery,Map<String, Object> mapParameters) {
		
		if(query.getConstico().equals("1"))
			certificado=matchSqlWithColu(query, objCertificado, certificado);		
		else if(query.getConstico().equals("2"))//especifica para AMPAROS
			certificado=matchSqlWithHeaders(objCertificado, certificado, PRODUCTO, query.getConscolu());
		else if(query.getConstico().equals("3"))
			certificado=matchFieldWithValue(objCertificado, certificado);
		
		return certificado;
	}

	private Certificado matchFieldWithValue(Object[] objCertificado, Certificado certificado) {
		try{
			certificado.addCampo(objCertificado[1].toString(), objCertificado[0].toString());
		}catch(NullPointerException e){
			certificado.addCampo(objCertificado[1].toString(), null);
		}
		return certificado;
	}

	private Certificado matchSqlWithHeaders(Object[] objCertificado, Certificado certificado, String PRODUCTO, String numberOfQuery) {
		
		FrmConsulta query = frmConsultaService.listId(numberOfQuery);
		List<Object[]> identificadores=frmConsultaService.loadListData(query, null, null);
								
		for(Object[] obj:identificadores){
			if(obj[8].equals(objCertificado[8]))
				for(int i=0; i<objCertificado.length-1;i++){
					try{
						certificado.addCampo(obj[i].toString(), objCertificado[i].toString());
					}catch(NullPointerException e){
						certificado.addCampo(obj[i].toString(), null);
					}
				}
		}
		return certificado;
	}

	private Certificado matchSqlWithColu(FrmConsulta query, Object[] objCertificado, Certificado certificado) {
		String[] identificadores=query.getConscolu().split(",");
		
		for(int i=0; i<objCertificado.length;i++){
			try{
				certificado.addCampo(identificadores[i], objCertificado[i].toString());
			}catch(NullPointerException e){
				certificado.addCampo(identificadores[i], null);
			}
		}
		
		return certificado;
	}	

}
