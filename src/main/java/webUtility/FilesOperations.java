package webUtility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import webUtility.VechileDetails;
public class FilesOperations {
    public List<List<String>> readExcellFile(File filePath) {
        List<List<String>> rowsData = new ArrayList<>();
        XSSFWorkbook myWorkBook = null;
        try {
            myWorkBook = new XSSFWorkbook(new FileInputStream(filePath)); // Read xlsx file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get first Sheet
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        // Traversing over each row of XLSX file
        Iterator<Row> rowIterator = mySheet.iterator();

        rowIterator.next(); //skip first row which contains table header
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next(); 
            Iterator<Cell> cellIterator = row.cellIterator();
            List<String> cellsData= new ArrayList<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cellsData.add(cell.getStringCellValue()); 
            }
            rowsData.add(cellsData);
        }
        return rowsData;
    }

    public List<VechileDetails> getVechileDetailsFromExcellFile(File filePath) {
        List<List<String>> rowsList = readExcellFile(filePath);
    	List<VechileDetails> vehicleDetails = new ArrayList<>();
          for (int i = 0; i < rowsList.size(); i++) {
        	  vehicleDetails.add(new VechileDetails(
                      rowsList.get(i).get(0),
                      rowsList.get(i).get(1),
                      rowsList.get(i).get(2)));
          }
          
        return vehicleDetails;
       }


}
