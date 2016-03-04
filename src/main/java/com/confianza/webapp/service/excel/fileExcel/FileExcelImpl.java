package com.confianza.webapp.service.excel.fileExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.springframework.stereotype.Service;

import com.confianza.webapp.utils.CFile;
import com.confianza.webapp.utils.SheetExcel;

@Service
public class FileExcelImpl implements FileExcel { 
	
	int numRow;
	int numRowHeader;
	short numCell;
	
	HSSFRow row;
	HSSFCell cell;
	HSSFCellStyle CellStyleHeader;
	HSSFCellStyle CellStyle;
	HSSFCellStyle CellStyleDate;
	HSSFCellStyle CellStyleNumberInt;
	HSSFCellStyle CellStyleNumberDouble;
	
	@Override
	public boolean generateExcel(String url, String nameFile, List<Object[]> listAll, String[] headers, String[] typeData, HttpServletRequest request) {
		 
		HSSFWorkbook workbook = createExcel(headers,typeData,listAll);
				
		return generateFile(url, nameFile, workbook);
        
    }
	
	@Override
	public boolean generateExcelManySheets(String url, String nameFile, List<SheetExcel> sheets, HttpServletRequest request) {
		 
		HSSFWorkbook workbook = createExcelManySheets(sheets);
				
		return generateFile(url, nameFile, workbook);
        
    }
	
	private HSSFWorkbook createExcel(String[] headers, String[] typeData, List<Object[]> listAll){
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		try{
		initCellStyleHeader(workbook);
		
		//crear la hoja de excel
		HSSFSheet sheet = workbook.createSheet("Hoja1");
		
		row = sheet.createRow(numRowHeader);
		for(String column:headers)
			createCellHeader(column);			
		
		for(Object[] objRow:listAll){			
			row = sheet.createRow(numRow++);
			numCell=0;
			for(Object objCell:objRow)
				createRow(typeData, objCell);	
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return workbook;
	}
	
	private HSSFWorkbook createExcelManySheets(List<SheetExcel> sheets){		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		for(SheetExcel sheetExcel:sheets){
			System.out.println(sheetExcel.getNameSheet()+"/*/*/*/*/*//**//*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
			sheetExcel.initStyles(workbook);
			initOwnCellStyleHeader(sheetExcel);
			//crear la hoja de excel
			HSSFSheet sheet = workbook.createSheet(sheetExcel.getNameSheet());
			
			row = sheet.createRow(numRowHeader);
			for(String column:sheetExcel.getHeaders())
				createCellHeader(column);			
			
			for(Object[] objRow:sheetExcel.getListAll()){			
				row = sheet.createRow(numRow++);
				numCell=0;
				for(Object objCell:objRow)
					createRow(sheetExcel.getTypeData(), objCell);
			}
			row = sheet.createRow(numRow++);
			row = sheet.createRow(numRow++);
			
			if(sheetExcel.getListAllExtra()!=null){
				sheetExcel.setCellStyleExtra(workbook);
				CellStyle=sheetExcel.getCellStyle();
				CellStyleDate=sheetExcel.getCellStyleDate();
				CellStyleNumberInt=sheetExcel.getCellStyleNumberInt();
				CellStyleNumberDouble=sheetExcel.getCellStyleNumberDouble();
				
				for(Object[] objRow:sheetExcel.getListAllExtra()){			
					row = sheet.createRow(numRow++);
					numCell=0;
					for(Object objCell:objRow)
						createRow(sheetExcel.getTypeData(), objCell);	
				}
			}
		}
		return workbook;
	}
	
	private void initCellStyleHeader(HSSFWorkbook workbook) {
		numRowHeader=1;
		numRow=2;
		numCell=0;
		
		setCellStyleHeader(workbook);
		setCellStyle(workbook);
		setCellStyleDate(workbook);
		setCellStyleInt(workbook);
		setCellStyleDouble(workbook);
	}
	
	private void initOwnCellStyleHeader(SheetExcel sheetExcel) {
		numRowHeader=1;
		numRow=2;
		numCell=0;
		
		CellStyleHeader=sheetExcel.getCellStyleHeader();
		CellStyle=sheetExcel.getCellStyle();
		CellStyleDate=sheetExcel.getCellStyleDate();
		CellStyleNumberInt=sheetExcel.getCellStyleNumberInt();
		CellStyleNumberDouble=sheetExcel.getCellStyleNumberDouble();
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
		CellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		CellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		return CellStyle;
	}
	
	private void setCellStyle(HSSFWorkbook workbook) {
		//Estilo de las celdas normales
		CellStyle = getBasicCellStyle(workbook);
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

	private boolean generateFile(String url, String nameFile, HSSFWorkbook workbook) {
		File dir = new File(url); 
		if (!dir.exists())
            dir.mkdirs();			
		try {
			CFile obj=new CFile(nameFile+".xls", "application/vnd.ms-excel", workbook.getBytes());
			File serverFile = new File(url + File.separator + obj.getName() );
			createFile(obj, serverFile, workbook);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void createFile(CFile obj, File serverFile, HSSFWorkbook workbook) throws FileNotFoundException, IOException {
		FileOutputStream fileOut =  new FileOutputStream(serverFile.getAbsoluteFile());
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	
	private void createRow(String[] typeData, Object objCell) {
		cell = row.createCell(numCell);
		if(typeData[numCell].equals("integer"))
			generateCellNumberInteger(objCell);
		else if(typeData[numCell].equals("double"))
			generateCellNumberDouble(objCell);
		else if(typeData[numCell].equals("date"))
			generateCellDate(objCell);
		else
			generateCellString(objCell);
		numCell++;
	}

	private void generateCellString(Object objCell) {
		cell.setCellStyle(CellStyle);
		if(objCell!=null)
			try{
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(objCell.toString());
			}catch(Exception  e){ 
				cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
				cell.setCellValue("");
			}
		else{
			cell.setCellValue("");
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		}
	}

	private void generateCellDate(Object objCell) {
		if(objCell!=null)
			try {
				SimpleDateFormat myFormat=new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(myFormat.parse(objCell.toString()));
				cell.setCellStyle(CellStyleDate);
				cell.setCellValue(calendar.getTime());
			} catch (ParseException e) {
				cell.setCellValue("");
				cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
				e.printStackTrace();
			}
		else{
			cell.setCellStyle(CellStyle);
			cell.setCellValue("");
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		}
	}

	private void generateCellNumberDouble(Object objCell) {
		if(objCell!=null)
			try {
				cell.setCellStyle(CellStyleNumberDouble);
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
				cell.setCellValue(Double.parseDouble(objCell.toString()));   
			} catch (NullPointerException e) {
				cell.setCellStyle(CellStyle);
				cell.setCellValue("");
				cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
				e.printStackTrace();
			}
		else{
			cell.setCellStyle(CellStyle);
			cell.setCellValue("");
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		}
	}

	private void generateCellNumberInteger(Object objCell) {
		if(objCell!=null)
			try {
				cell.setCellStyle(CellStyleNumberInt);
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(Long.parseLong(objCell.toString()));
			} catch (Exception e) {
				cell.setCellValue("");
				cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
				e.printStackTrace();
			}
		else{
			cell.setCellStyle(CellStyle);
			cell.setCellValue("");
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
		}
	}

	private void createCellHeader(String column) {
		cell = row.createCell(numCell++);
		cell.setCellStyle(CellStyleHeader);
		cell.setCellValue(column);
	}
	
}
