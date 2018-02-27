package webUtility;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.activation.MimetypesFileTypeMap;

public class ReadFiles {
	
    public List<FileDetails> getFilesDetailsFromPath(String[] filetypes,String dir_location) throws IOException  {
        
    	List<File> filesPath ;
    	if (filetypes==null) 
    		filesPath= readFilesFromDirectory(dir_location);
    	else 
    		filesPath= readFilesFromDirectory(filetypes,dir_location);
    	
        List<FileDetails> filesDetails = new ArrayList<>();
        
        for(int i = 0; i < filesPath.size(); i++) {
            File filePath = filesPath.get(i);
            filesDetails.add(new FileDetails(
            	    filePath,
                    getMimeType(filePath),
                    String.valueOf(filePath.length()), 
                    filePath.getName().split("\\.")[0],
                    filePath.getName().split("\\.")[1])); 
        }
        return filesDetails;
       
    }


    public List<File> readFilesFromDirectory(String dir_location) {

       File f = new File(dir_location);
       List<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
         return files;
    }

    public List<File> readFilesFromDirectory(String[] filetypes,String dir_location) throws IOException {

		//find the given extension file types in the directory and returns files information
    	 List<File> files;
		 File dir = new File(dir_location);
		 files = (List<File>) FileUtils.listFiles(dir,filetypes, true);
		 return files;
     }
    private String getMimeType(File filePath ) {
        String absolutePath = filePath.getAbsolutePath();
        
        Tika mimeTika = new Tika();
        String fileType;
        try {
            final File file = new File(absolutePath);
            fileType = mimeTika.detect(file);
        } catch (IOException exp) {
            fileType = "Unknown";
        }
        return fileType;
    }

    public List<FileDetails>getFilesDetailsByMimeType (String[] filetypes, String mimeType, String dir_location) throws IOException {
      
    	List<FileDetails> filesDetails = getFilesDetailsFromPath(null,dir_location);
        List<FileDetails> filteredList = new ArrayList<>();
        for (int i = 0; i < filesDetails.size(); i++) {
            if (filesDetails.get(i).getFileMimeType().equalsIgnoreCase(mimeType)) {
                filteredList.add(filesDetails.get(i));
            }
        }
        return filteredList;
    }

    public void displayFileDetails(List<FileDetails> files)
	{
		System.out.println("Total number of files found:  " + files.size());
		for (FileDetails file : files) {
			System.out.println("file name:  " + file.getFilename());
			
			System.out.println("file mime type:  " + file.getFileMimeType());
			System.out.println("file size:  " + file.getFileSize());
			System.out.println("file Extension:  " + file.getFileExtension());
			
		}
		
	}

}
