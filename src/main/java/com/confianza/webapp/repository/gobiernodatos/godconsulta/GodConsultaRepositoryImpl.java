package com.confianza.webapp.repository.gobiernodatos.godconsulta;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ObjectType;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;

@Repository
public class GodConsultaRepositoryImpl {
	/*
	@Override
	@Transactional(readOnly=true)
	public FrmConsulta list(Long id){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from FrmConsulta "
					   + "where conscons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmConsulta.class)					
					     .setParameter("id", id);
			return (FrmConsulta)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}*/

}
