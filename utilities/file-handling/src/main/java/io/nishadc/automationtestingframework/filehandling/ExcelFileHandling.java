package io.nishadc.automationtestingframework.filehandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.nishadc.automationtestingframework.filehandling.exceptions.ExcelFileHandlingException;
import io.nishadc.automationtestingframework.logging.LoggerFactory;

public class ExcelFileHandling {
	private static final Logger logger = LoggerFactory.create(ExcelFileHandling.class);
	
	private ExcelFileHandling() {
		
	}
	
	private static ExcelFileType getWorkbookType(String fullWorkbookPath) throws ExcelFileHandlingException {
		try {
			//parse workbook type from file extension name. Currently restricted to .xls and .xlsx
			return ExcelFileType
					.valueOf(fullWorkbookPath.substring(fullWorkbookPath.lastIndexOf(".") + 1).toUpperCase());
		} catch (IllegalArgumentException e) {
			//in case of file extension other than .xls and .xlsx
			throw (ExcelFileHandlingException) new ExcelFileHandlingException(
					"Invalid file type. Only .xls and .xlsx format is supported.").initCause(e);
		}
	}
	
	private static List<Map<String, Object>> getExcelSheetContent
		(FileInputStream workbookFileInputStream,ExcelFileType workbookType,String worksheetName) 
				throws IOException, ExcelFileHandlingException {
		List<Map<String, Object>> excelData=null;
		if(workbookType==ExcelFileType.XLS) {
			try(Workbook workbook=new HSSFWorkbook(workbookFileInputStream)) {
				excelData=getExcelSheetContent(workbook,worksheetName);
			}
		} else {
			try(Workbook workbook=new XSSFWorkbook(workbookFileInputStream)) {
				excelData=getExcelSheetContent(workbook,worksheetName);
			}
		}
		return excelData;
	}
	
	public static List<Map<String, Object>> getExcelSheetContent(String fullWorkbookPath,String worksheetName) throws IOException, ExcelFileHandlingException {
		ExcelFileHandling.logger.debug("Reading Excel from {} workbook ans sheet {}", fullWorkbookPath,worksheetName);
		
		File workbookFile = new File(fullWorkbookPath);
		try(FileInputStream workbookFileInputStream = new FileInputStream(workbookFile)) {
			ExcelFileType workbookType =ExcelFileHandling.getWorkbookType(fullWorkbookPath);
			return ExcelFileHandling.getExcelSheetContent(workbookFileInputStream, workbookType, worksheetName);
		} catch (FileNotFoundException e) {
			ExcelFileHandling.logger.error("Excel file [{}] doesn't exist.", fullWorkbookPath,e);
			throw (ExcelFileHandlingException) new ExcelFileHandlingException(
					String.format("Excel file [%s] doesn't exist.", fullWorkbookPath)).initCause(e);
		} catch(IllegalArgumentException e) {
			ExcelFileHandling.logger.error("Corrupted excel file: {}",e.getMessage(),e);
			throw (ExcelFileHandlingException) new ExcelFileHandlingException(
					"Error reading excel file: " + e.getMessage()).initCause(e);
		}
	}
	
	private static List<Map<String, Object>> getExcelSheetContent(Workbook workbook,String worksheetName) throws ExcelFileHandlingException {
		Sheet sheet=workbook.getSheet(worksheetName);
		if(sheet==null) {
			ExcelFileHandling.logger.error("Cannot find sheet {}", worksheetName);
			throw new ExcelFileHandlingException(String.format("Sheet %s dpoesn't exist.", worksheetName));
		}
		
		List<Map<String, Object>> rows = new ArrayList<>();
		Iterator<Row> rowIterator = sheet.iterator();
      String[] headers = {""};
      //process all rows
      while (rowIterator.hasNext()) {
          Row currentRow = rowIterator.next();
          if (currentRow.getRowNum() == 0) {
              headers = getHeaders(currentRow);
              continue;
          }
          Map<String, Object> rowMap = new HashMap<>();
          for (int i = 0; i < headers.length; i++) {
              Cell currentCell = currentRow.getCell(i);
              rowMap.put(headers[i],ExcelFileHandling.getCellValue(currentCell) );
          }
          rows.add(rowMap);
      }
      return rows;
	}
	
	private static Object getCellValue(Cell cell) {
		if (cell == null) {
            return "";
        } else {
        	//process each type of excel cell
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return cell.getNumericCellValue();
                case BOOLEAN:
                    return cell.getBooleanCellValue();
                //process formula cell type
                case FORMULA:
                	switch(cell.getCachedFormulaResultType()) {
                	case STRING:
                		return cell.getStringCellValue();
                	case NUMERIC:
                        return cell.getNumericCellValue();
                    case BOOLEAN:
                        return cell.getBooleanCellValue();
						default:
							return "";
                	}
                default:
                    return "";
            }
        }
	}
	
	/**
	 * <b>Method Name</b>: getHeaders<br>
	 * <b>Description</b>: Returns Excel row contents as String Array. Meant to extract  <br>
	 * <b>Returns</b>: String[] as array of strings in an excel row.<br>
	 * @since v1.0.0
	 * @param row Excel row as {@link org.apache.poi.ss.usermodel.Row Row}
	 * @return String[] as array of strings in an excel row
	 */
	private static String[] getHeaders(Row row) {
      List<String> headers = new ArrayList<>();
      Iterator<Cell> cellIterator = row.cellIterator();
      while (cellIterator.hasNext()) {
          Cell currentCell = cellIterator.next();
          headers.add(currentCell.getStringCellValue());
      }
      return headers.toArray(new String[headers.size()]);
  }
}
