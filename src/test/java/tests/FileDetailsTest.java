package tests;

import webUtility.FileDetails;
import webUtility.ReadFiles;
import webUtility.VechileDetails;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class FileDetailsTest {
	String dir_location = System.getProperty("user.dir") + "/src/test/resources/TestData/";

	//Test to check no of files

	@Test
    public void CheckNumberOfFiles () throws IOException {
        ReadFiles readFiles = new ReadFiles();

        List<FileDetails> filesDetails = readFiles.getFilesDetailsFromPath(null,dir_location);
        assertTrue(filesDetails.size() >=10);
    }

	//scan the directory and display file info the each of the file in directory

    @Test
    public void displayVechileInfoFromAFile() throws IOException {
    		
    	ReadFiles readFiles = new ReadFiles();
    	String[] file_types = {"xlsx","csv"};
        List<FileDetails> filesDetails = readFiles.getFilesDetailsFromPath(file_types,dir_location);
        readFiles.displayFileDetails(filesDetails);
        }

	//scan the directory and display file info for the supported mime type files

    
    @Test
    public void displayFilesDetailsByMimeType() throws IOException {
    		
    	ReadFiles readFiles = new ReadFiles();
    //	String[] mime_types = {"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","text/csv"};
        List<FileDetails> files = readFiles.getFilesDetailsByMimeType(null,"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", dir_location);
        readFiles.displayFileDetails(files);

        files = readFiles.getFilesDetailsByMimeType(null,"text/csv", dir_location);
        readFiles.displayFileDetails(files);
    }

  
}
