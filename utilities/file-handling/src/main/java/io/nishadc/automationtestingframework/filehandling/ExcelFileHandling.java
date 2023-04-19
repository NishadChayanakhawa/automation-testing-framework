package io.nishadc.automationtestingframework.filehandling;

//logger
import org.apache.logging.log4j.Logger;
//Excel Apache POI
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.nishadc.automationtestingframework.filehandling.exceptions.ExcelFileHandlingException;
import io.nishadc.automationtestingframework.logging.LoggerFactory;

//custom exceptions
//java util
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
//java io
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
* <b>Class Name</b>: ExcelFileHandling<br>
* <b>Description</b>: Provides an interface to read with Excel files.<br>
* @author Nishad Chayanakhawa &lt;nishad.chayanakhawa@gmail.com&gt;
*/
public class ExcelFileHandling {
	//logger
	private static final Logger logger = LoggerFactory.create(ExcelFileHandling.class);

	private ExcelFileHandling() {
		//Do Nothing. Object will never be instantiated.
	}
	
	/**
	 * <b>Method Name</b>: getExcelWorkbook<br>
	 * <b>Description</b>: Returns excel workbook.<br>
	 * <b>Returns</b>: Excel workbook as {@link org.apache.poi.ss.usermodel.Workbook Workbook}<br>
	 * @since v1.0.0
	 * @param fullWorkbookPath Full Excel workbook path as {@link java.lang.String String}
	 * @return Excel workbook as {@link org.apache.poi.ss.usermodel.Workbook Workbook}
	 * @throws ExcelFileHandlingException in case of issues reading from excel file
	 */
	private static Workbook getExcelWorkbook(String fullWorkbookPath) throws ExcelFileHandlingException {
		//open workbook file stream
		File workbookFile = new File(fullWorkbookPath);
		FileInputStream workbookFileInputStream = null;
		try {
			workbookFileInputStream = new FileInputStream(workbookFile);
		} catch (FileNotFoundException e) {
			//In case file is not found
			throw (ExcelFileHandlingException) new ExcelFileHandlingException(
					String.format("Excel file [%s] doesn't exist.", fullWorkbookPath)).initCause(e);
		}
		ExcelFileType workbookType = null;
		try {
			try {
				//parse workbook type from file extension name. Currently restricted to .xls and .xlsx
				workbookType = ExcelFileType
						.valueOf(fullWorkbookPath.substring(fullWorkbookPath.lastIndexOf(".") + 1).toUpperCase());
			} catch (IllegalArgumentException e) {
				//in case of file extension other than .xls and .xlsx
				workbookFileInputStream.close();
				throw (ExcelFileHandlingException) new ExcelFileHandlingException(
						"Invalid file type. Only .xls and .xlsx format is supported.").initCause(e);
			}
			
			//Assign workbook based on xls and xlsx type
			Workbook workbook=null;
			switch (workbookType) {
			case XLS:
				workbook=new HSSFWorkbook(workbookFileInputStream);
				workbookFileInputStream.close();
				break;
			case XLSX:
				workbook=new XSSFWorkbook(workbookFileInputStream);
				workbookFileInputStream.close();
				break;
			}
			//return workbook
			return workbook;
		} catch(IllegalArgumentException | IOException e) {
			//in case excel file is corrupted or any other issue reading excel
			ExcelFileHandling.logger.error("Corrupted excel file: " + e.getMessage(),e);
			throw (ExcelFileHandlingException) new ExcelFileHandlingException(
					"Error reading excel file: " + e.getMessage()).initCause(e);
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
	
	/**
	 * <b>Method Name</b>: getExcelSheetContent<br>
	 * <b>Description</b>: Get excel sheet contents as List&lt;Map&lt;String, Object&gt;&gt;<br>
	 * <b>Returns</b>: List&lt;Map&lt;String, Object&gt;&gt; <br>
	 * @since v1.0.0
	 * @param fullWorkbookPath Full Excel workbook path as {@link java.lang.String String}
	 * @param worksheetName as {@link java.lang.String String}
	 * @return Excel sheet contents as List&lt;Map&lt;String, Object&gt;&gt;
	 * @throws ExcelFileHandlingException in case of issues in reading file.
	 */
	public static List<Map<String, Object>> getExcelSheetContent(String fullWorkbookPath,String worksheetName) throws ExcelFileHandlingException {
		ExcelFileHandling.logger.debug("Reading Excel from {} workbook ans sheet {}", fullWorkbookPath,worksheetName);
		Workbook workbook=ExcelFileHandling.getExcelWorkbook(fullWorkbookPath);
		Sheet sheet=workbook.getSheet(worksheetName);
		if(sheet==null) {
			ExcelFileHandling.logger.error("In workbook {}, cannot find sheet {}", fullWorkbookPath,worksheetName);
			throw new ExcelFileHandlingException(String.format("Sheet %s dpoesn't exist in workbook %s", worksheetName,fullWorkbookPath));
		}
		List<Map<String, Object>> rows = new ArrayList<>();
		Iterator<Row> rowIterator = sheet.iterator();
      String[] headers = null;
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
              if (currentCell == null) {
                  rowMap.put(headers[i], "");
              } else {
              	//process each type of excel cell
                  switch (currentCell.getCellType()) {
                      case STRING:
                          rowMap.put(headers[i], currentCell.getStringCellValue());
                          break;
                      case NUMERIC:
                          rowMap.put(headers[i], currentCell.getNumericCellValue());
                          break;
                      case BOOLEAN:
                          rowMap.put(headers[i], currentCell.getBooleanCellValue());
                          break;
                      //process formula cell type
                      case FORMULA:
                      	switch(currentCell.getCachedFormulaResultType()) {
                      	case STRING:
                      		rowMap.put(headers[i], currentCell.getStringCellValue());
                              break;
                      	case NUMERIC:
                              rowMap.put(headers[i], currentCell.getNumericCellValue());
                              break;
                          case BOOLEAN:
                              rowMap.put(headers[i], currentCell.getBooleanCellValue());
                              break;
							default:
								rowMap.put(headers[i], "");
	                            break;
                      	}
                      	break;
                      default:
                          rowMap.put(headers[i], "");
                          break;
                  }
              }
          }
          try {
          	//close workbook
				workbook.close();
			} catch (IOException e) {
				ExcelFileHandling.logger.error("Error closing excel file: " + e.getMessage(),e);
				throw (ExcelFileHandlingException) new ExcelFileHandlingException(
						"Error closing excel file: " + e.getMessage()).initCause(e);
			}
          rows.add(rowMap);
      }
      return rows;
	}
}