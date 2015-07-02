package com.confianza.webapp.utils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class SqlFunctions {

	@Autowired  
	Gson gson;
	
	public String completeSQL(String order, List<Filter> filters, String sql, String columns) {
		String where = "";
		where = generateWhere(filters, where, columns);
		
		sql+= where;
		
		if(order!=null)
			sql+=" "+order;			
		
		return sql;
	}
	
	private String generateWhere(List<Filter> filters, String where, String columns) {

		if(filters!=null)
		for(Filter filter:filters){
			if((columns).matches("(.*)"+filter.getCampo()+"(.*)")){
				if(where.isEmpty()){
					where+=" WHERE ";
					where+= generateCondition(filter);
				}
				else{
					where+=" AND ";
					where+= generateCondition(filter);
				}
			}			
		}
		return where;
	}

	private String generateCondition(Filter filter) {
		if(filter.getTipo().equals("BETWEEN"))
			return filter.getCampo()+" "+filter.getTipo()+" :"+filter.getCampo()+" AND :"+filter.getCampo()+"2";
		if(filter.getTipo().equals("IN"))
			return filter.getCampo()+" IN(:"+filter.getCampo()+")";
		else
			return filter.getCampo()+" "+filter.getTipo()+" :"+filter.getCampo();
	}
	
	public Query setParameters(List<Filter> filters, Query query) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		if(filters!=null)
		for(Filter filter:filters){
			    if(filter.getTipo().equals("IN")){
			    	if(filter.getTipodato().equals("Number")){
				    	Type listOfTestObject = new TypeToken<List<Long>>(){}.getType();
						List<Long> listin = gson.fromJson("["+filter.getVal1()+"]", listOfTestObject);
				    	query.setParameterList(filter.getCampo(), listin);
			    	}
			    	else if(filter.getTipodato().equals("Stirng")){
				    	Type listOfTestObject = new TypeToken<List<String>>(){}.getType();
						List<String> listin = gson.fromJson("["+filter.getVal1()+"]", listOfTestObject);
				    	query.setParameterList(filter.getCampo(), listin);
			    	}
			    }
			    else if(filter.getTipodato().equals("Date")){
					Date parsed=null;
					try {
						parsed = format.parse(filter.getVal1());
						query.setDate(filter.getCampo(), parsed);
					} catch (ParseException e) {
						query.setDate(filter.getCampo(), parsed);
					}
					if(filter.getVal2()!=null){
						try {
							parsed = format.parse(filter.getVal2());
							query.setDate(filter.getCampo()+"2", parsed);
						} catch (ParseException e) {
							query.setDate(filter.getCampo()+"2", parsed);
						}
					}
				}
				else if(filter.getTipodato().equals("Number")){
					query.setDouble(filter.getCampo(), new Double(filter.getVal1()));
					if(filter.getVal2()!=null)	{
						query.setDouble(filter.getCampo()+"2", new Double(filter.getVal2()));
					}
				}
				else{
					query.setParameter(filter.getCampo(), filter.getVal1());
				}
		}
		
		return query;
	}
}
