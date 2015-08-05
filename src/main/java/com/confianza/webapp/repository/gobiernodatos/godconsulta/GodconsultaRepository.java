package com.confianza.webapp.repository.gobiernodatos.godconsulta;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;

public interface GodconsultaRepository {	
	
public void uploadFiles(ArrayList<MultipartFile> file);

}
