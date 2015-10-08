package com.confianza.webapp.service.excel.fileExcel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.confianza.webapp.utils.SheetExcel;

public interface FileExcel {

	public boolean generateExcel(String url, String nameFile, List<Object[]> listAll, String[] headers, String[] tipeData, HttpServletRequest request);

	public boolean generateExcelManySheets(String url, String nameFile, List<SheetExcel> sheets, HttpServletRequest request);

}
