package com.confianza.webapp.service.gobiernodatos.carga;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;

public interface GodConsultaService {
	
	public String loadFiles(String conscons, String params, ArrayList<MultipartFile> file);

	public Map<String, Object> loadProcedure(FrmConsulta frmConsulta, List<FrmParametro> parametros, Map<String, Object> parameters, Map<String, Object> parametersData);

}
