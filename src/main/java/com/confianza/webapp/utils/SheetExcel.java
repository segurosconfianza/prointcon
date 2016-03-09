package com.confianza.webapp.utils;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          


public class SheetExcel {
	
	private List<Object[]> listAll;
	private List<Object[]> listAllExtra;
	private String[] headers;
	private String[] typeData;
	private String nameSheet;
	private short colorBackground;
	private short colorBackgroundExtra;
	private HSSFCellStyle CellStyleHeader;
	private HSSFCellStyle CellStyle;
	private HSSFCellStyle CellStyleDate;
	private HSSFCellStyle CellStyleNumberInt;
	private HSSFCellStyle CellStyleNumberDouble;
	
	public SheetExcel(List<Object[]> listAll, String[] headers,String[] typeData, short colorBackground, String nameSheet) {
		this.listAll = listAll;
		this.headers = headers;
		this.typeData = typeData;
		this.colorBackground = colorBackground;
		this.nameSheet = nameSheet;
	}
	
	public SheetExcel(List<Object[]> listAll, String[] headers,String[] typeData, short colorBackground, String nameSheet, List<Object[]> listAllExtra, short colorBackgroundExtra) {
		this.listAll = listAll;
		this.headers = headers;
		this.typeData = typeData;
		this.colorBackground = colorBackground;
		this.nameSheet = nameSheet;
		this.listAllExtra = listAllExtra;
		this.colorBackgroundExtra = colorBackgroundExtra;
	}
	
	public void initStyles(HSSFWorkbook workbook) {
		
		setCellStyleHeader(workbook);
		setCellStyle(workbook);
		setCellStyleDate(workbook);
		setCellStyleInt(workbook);
		setCellStyleDouble(workbook);
	}
	
	private HSSFCellStyle getBasicCellStyle(HSSFWorkbook workbook) {
		//Estilo de las celdas normales
		HSSFCellStyle CellStyle;
		CellStyle = workbook.createCellStyle();		
		CellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		// También, podemos establecer bordes...
		CellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		CellStyle.setBottomBorderColor((short)8);
		CellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		CellStyle.setLeftBorderColor((short)8);
		CellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		CellStyle.setRightBorderColor((short)8);
		CellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		CellStyle.setTopBorderColor((short)8);
		
		// Establecemos el tipo de sombreado de nuestra celda
		CellStyle.setFillForegroundColor(this.colorBackground);
		CellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		return CellStyle;
	}
	
	public String getNameSheet() {
		return nameSheet;
	}

	public void setNameSheet(String nameSheet) {
		this.nameSheet = nameSheet;
	}

	public void setCellStyle(HSSFWorkbook workbook) {
		//Estilo de las celdas normales
		CellStyle = getBasicCellStyle(workbook);
	}
	
	public void setCellStyleExtra(HSSFWorkbook workbook) {
		//Estilo de las celdas normales
		CellStyle = getBasicCellStyle(workbook);
		CellStyle.setFillForegroundColor(this.colorBackgroundExtra);	
		this.colorBackground=this.colorBackgroundExtra;
		setCellStyleDate(workbook);
		setCellStyleInt(workbook);
		setCellStyleDouble(workbook);
	}
		
	private void setCellStyleDate(HSSFWorkbook workbook) {
		
		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyleDate = getBasicCellStyle(workbook);
		CellStyleDate.setDataFormat( createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
	}
	
	private void setCellStyleInt(HSSFWorkbook workbook) {
		
		CellStyleNumberInt = getBasicCellStyle(workbook);
		CellStyleNumberInt.setDataFormat( HSSFDataFormat.getBuiltinFormat("#,##0"));
	}
	
	private void setCellStyleDouble(HSSFWorkbook workbook) {
		
		CellStyleNumberDouble = getBasicCellStyle(workbook);
		CellStyleNumberDouble.setDataFormat( HSSFDataFormat.getBuiltinFormat("#,##0.00"));
	}

	private void setCellStyleHeader(HSSFWorkbook workbook) {
		CellStyleHeader = getBasicCellStyle(workbook);
		CellStyleHeader.setAlignment(HSSFCellStyle. ALIGN_JUSTIFY);
		
		// Establecemos el tipo de sombreado de nuestra celda
		CellStyleHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	}
	
	public HSSFCellStyle getCellStyleHeader() {
		return CellStyleHeader;
	}

	public void setCellStyleHeader(HSSFCellStyle cellStyleHeader) {
		CellStyleHeader = cellStyleHeader;
	}

	public HSSFCellStyle getCellStyle() {
		return CellStyle;
	}

	public void setCellStyle(HSSFCellStyle cellStyle) {
		CellStyle = cellStyle;
	}

	public HSSFCellStyle getCellStyleDate() {
		return CellStyleDate;
	}

	public void setCellStyleDate(HSSFCellStyle cellStyleDate) {
		CellStyleDate = cellStyleDate;
	}

	public HSSFCellStyle getCellStyleNumberInt() {
		return CellStyleNumberInt;
	}

	public void setCellStyleNumberInt(HSSFCellStyle cellStyleNumberInt) {
		CellStyleNumberInt = cellStyleNumberInt;
	}

	public HSSFCellStyle getCellStyleNumberDouble() {
		return CellStyleNumberDouble;
	}

	public void setCellStyleNumberDouble(HSSFCellStyle cellStyleNumberDouble) {
		CellStyleNumberDouble = cellStyleNumberDouble;
	}

	public List<Object[]> getListAll() {
		return listAll;
	}
	
	public void setListAll(List<Object[]> listAll) {
		this.listAll = listAll;
	}
	
	public String[] getHeaders() {
		return headers;
	}
	
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	
	public String[] getTypeData() {
		return typeData;
	}
	
	public void setTypeData(String[] typeData) {
		this.typeData = typeData;
	}
	
	public short getColorBackground() {
		return colorBackground;
	}
	
	public void setColorBackground(short colorBackground) {
		this.colorBackground = colorBackground;
	}

	public List<Object[]> getListAllExtra() {
		return listAllExtra;
	}

	public void setListAllExtra(List<Object[]> listAllExtra) {
		this.listAllExtra = listAllExtra;
	}

	public short getColorBackgroundExtra() {
		return colorBackgroundExtra;
	}

	public void setColorBackgroundExtra(short colorBackgroundExtra) {
		this.colorBackgroundExtra = colorBackgroundExtra;
	}
}
