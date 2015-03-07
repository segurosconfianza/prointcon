package com.confianza.webapp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {
	/*
	 *  Matrix de objetos 
	 */
	public static List<Map<String, Object>> toNameList(String[] heads, List<Object[]> matrix){
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		
		if( heads != null && heads.length > 0 && matrix != null && matrix.size() > 0 ){
			int len = heads.length; 
			Map<String, Object> mapa;		
			
			for( Object[] row : matrix ){
				mapa = new HashMap<String, Object>();
				 
				for( int i=0; i<len; i++ ){
					mapa.put(heads[i], row[i]);
				}
				
				lista.add(mapa);
			}
			return lista;
		}
		
		return lista;
	}
	
	public static List<Map<String, Object>> toNameListObject(String[] heads, List<Object[]> matrix){
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		
		if( heads != null && heads.length > 0 && matrix != null && matrix.size() > 0 ){
			int len = heads.length; 
			Map<String, Object> mapa;		
			
			for( int i=0; i<len; i++ ){
				mapa = new HashMap<String, Object>();
				mapa.put(heads[i], matrix.get(0));
				lista.add(mapa);
			}
			
			return lista;
		}
		
		return lista;
	}
	
	public static List<Map<String, Object>> toNameList2(String[] heads, List<Object[]> matrix){
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		
		if( heads != null && heads.length > 0 && matrix != null && matrix.size() > 0 ){
			int len = heads.length; 
			Map<String, Object> mapa;		
			
			try{
				for( Object[] row : matrix ){
					mapa = new HashMap<String, Object>();
					 
					for( int i=0; i<len; i++ ){
						mapa.put(heads[i], row[i]);
					}
					
					lista.add(mapa);
				}
			}catch(Exception e){
				for( int i=0; i<len; i++ ){
					mapa = new HashMap<String, Object>();
					mapa.put(heads[i], matrix.get(0));
					lista.add(mapa);
				}
			}
			
			return lista;
		}
		
		return lista;
	}
}
