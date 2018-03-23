package com.framework.Package.reader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelClass {

	String path;
	FileInputStream fis;
	FileOutputStream fos;
	Workbook wb;
	Sheet sh;
	Row row;
	Cell cell;

	//constructor
	public ExcelClass(String path){
		this.path=path;


		try {
			fis=new FileInputStream(path);
			wb=WorkbookFactory.create(fis);
			sh=wb.getSheetAt(0);
			fis.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getNumberOfSheets(){

		int sheetsTotal=wb.getNumberOfSheets();

		return sheetsTotal;
	}
	
	public int totalNumberOfRowsInSheet(String sheetName){
		
		if(isSheetExists(sheetName)){
			
			//int sheetIndex=sheetIndex(sheetName);
			sh=wb.getSheet(sheetName);
			
			return sh.getLastRowNum()+1;
			
		}
		else{
			return -1;			
		}
		
	}
	
	public int totalNumberOfColumns(String sheetName,int rowNum){
		
		if(!(totalNumberOfRowsInSheet(sheetName)==-1)){
			
			sh=wb.getSheet(sheetName);
			row=sh.getRow(rowNum-1);
			if(row==null){
				return -1;
			}
			return row.getLastCellNum();
		}
		else{			
			return -1;
		}
		
	}
	
	public boolean isSheetExists(String sheetName){
		
		int indexSheet=wb.getSheetIndex(sheetName);
		//System.out.println("indexSheet="+indexSheet);
		if(indexSheet==-1){
			return false;
		}
		else{
			return true;			
		}
		
	}
	
	public int sheetIndex(String sheetName){
		
		if(isSheetExists(sheetName)){			
			return wb.getSheetIndex(sheetName);			
		}
		else{
			return -1;
		}
		
		
	}

	public String getCellData(String sheetName,String colName,int rowNum) throws Exception{

		if(rowNum<=0){
			return "";
		}

		int index=wb.getSheetIndex(sheetName);
		int colNum=-1;		

		if(index==-1){
			return "";
		}

		sh=wb.getSheetAt(index);

		for(int i=0;i<sh.getRow(0).getLastCellNum();i++){

			String compareValue=sh.getRow(0).getCell(i).getStringCellValue().trim();
			if(compareValue.equalsIgnoreCase(colName.trim())){
				colNum=i;
				break;
			}
		}

		if(colNum==-1){
			return "";
		}

		row=sh.getRow(rowNum-1);
		if(row==null){
			return "";
		}
		cell=row.getCell(colNum);
		if(cell==null){
			return "";
		}
		/*//System.out.println("cell.getCellType()="+cell.getCellType());
		System.out.println("cell.getCellType()="+cell.CELL_TYPE_NUMERIC);
		System.out.println("cell.getCellType()="+cell.CELL_TYPE_STRING);
		System.out.println("cell.getCellType()="+cell.CELL_TYPE_FORMULA);
		System.out.println("cell.getCellType()="+cell.CELL_TYPE_BLANK);
		System.out.println("cell.getCellType()="+cell.CELL_TYPE_BOOLEAN);
		System.out.println("cell.getCellType()="+cell.CELL_TYPE_ERROR);*/
		//System.out.println("cell.getCellTypeEnum()="+cell.getCellTypeEnum());
		switch(cell.getCellType()){		
		case 0:
			return String.valueOf(cell.getNumericCellValue());
		case 1:
			return cell.getStringCellValue();
		case 2:
			/*//HSSFDate    Have to work on this further
			Calendar cal=Calendar.getInstance();
			cal.getTime();*/
			return cell.getCellFormula();
		case 3:
			return "";
		case 4:
			return String.valueOf(cell.getBooleanCellValue());
		case 5:
			return String.valueOf(cell.getErrorCellValue());
		default:
			return "";
		}
		
	}

	public String getCellData(String sheetName,int rowNum,int colNum) throws Exception{
		//String path=System.getProperty("user.dir")+"/src/main/resources/excelFiles/login.xlsx";
		fis=new FileInputStream(path);
		wb=WorkbookFactory.create(fis);
		//wb.getNumberOfSheets();
		sh=wb.getSheet(sheetName);
		row=sh.getRow(rowNum-1);
		cell=row.getCell(colNum-1);
		String excelValue=cell.getStringCellValue();		

		return excelValue;
	}

	public void setCellData(String sheetName,int rowNum,int colNum,String dataToPut) throws Exception{
		//String path=System.getProperty("user.dir")+"/src/main/resources/excelFiles/login.xlsx";
		fis=new FileInputStream(path);
		wb=WorkbookFactory.create(fis);
		sh=wb.getSheet(sheetName);
		if(sh==null){
			sh=wb.createSheet(sheetName);
		}
		row=sh.getRow(rowNum-1);
		if(row==null){
			row=sh.createRow(rowNum-1);
		}
		cell=row.getCell(colNum-1);
		if(cell==null){
			cell=row.createCell(colNum-1);
		}
		cell.setCellValue(dataToPut);

		fos=new FileOutputStream(path);
		wb.write(fos);
		fos.close();

	}
	
	public void setCellData(String sheetName,String colName,int rowNum,String dataToPut) throws Exception{
		//String path=System.getProperty("user.dir")+"/src/main/resources/excelFiles/login.xlsx";
		int colNum=100;
		fis=new FileInputStream(path);
		wb=WorkbookFactory.create(fis);
		sh=wb.getSheet(sheetName);
		if(sh==null){
			sh=wb.createSheet(sheetName);
		}
		row=sh.getRow(rowNum-1);
		if(row==null){
			row=sh.createRow(rowNum-1);
		}
		
		for(int i=0;i<sh.getRow(0).getLastCellNum();i++){

			String compareValue=sh.getRow(0).getCell(i).getStringCellValue().trim();
			if(compareValue.equalsIgnoreCase(colName.trim())){
				colNum=i;
				break;
			}
		}
		
		cell=row.getCell(colNum);
		if(cell==null){
			cell=row.createCell(colNum);
		}
		cell.setCellValue(dataToPut);

		FileOutputStream fos=new FileOutputStream(path);
		wb.write(fos);
		fos.close();

	}
	
	public Object[][] dataProviderGetData(String sheetName){
		
		sh=wb.getSheet(sheetName);
		int totalRows=totalNumberOfRowsInSheet(sheetName);
		int totalCols=totalNumberOfColumns(sheetName,1);
		System.out.println("totalRows="+totalRows+" totalCols="+totalCols);
		Object[][] data=new Object[totalRows-1][totalCols];
		
		for(int rows=0;rows<totalRows-1;rows++){
			
			for(int cols=0;cols<totalCols;cols++){
				
				data[rows][cols]=sh.getRow(rows+1).getCell(cols).toString();
			}
			
		}
		
		return data;
		
	}

	/*public static void main(String args[]) throws Exception{

		ExcelClass data=new ExcelClass(System.getProperty("user.dir")+"/src/main/resources/excelFiles/login.xlsx");
		//System.out.println(data.getNumberOfSheets());
		System.out.println("rowsTotal="+data.totalNumberOfRowsInSheet("InvalidCredentials"));
		System.out.println("columnsTotal="+data.totalNumberOfColumns("InvalidCredentials", 2));
		//System.out.println(data.getCellData("InvalidCredentials", 3, 1));
		System.out.println(data.getCellData("InvalidCredentials", "Password", 5));
		//data.setCellData("OwnSheet", 1, 2, "MyValue");
		System.out.println("Done");

	}*/

}
