/**
 * FileUtility Class, used to move, copy and write to files.
 */
package com.uottawa.bioinformatics.resusable.fileutilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Ashish
 * Student ID : 300059114
 * email : averm019@uottawa.ca
 *
 */


public class FileUtilities {
	
	/**
	 * 
	 * @param srcPath : Source path of file including filename and extension
	 * @param destPath: Destination path of file including filename and extension
	 * @return boolean value indicating result of execution
	 * @throws Exception
	 */
	public static boolean moveFile(String srcPath, String destPath) throws Exception{
		boolean fileMoved = true;

	    try {
	    	if(Files.exists(Paths.get(srcPath)))
	    		Files.move(Paths.get(srcPath), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
	    	else
	    		fileMoved = false;
	    
	    } catch (Exception e) {

	        fileMoved = false;
	        throw e;
	    }

		return fileMoved;
	}
	
	/**
	 * 
	 * @param content : Content to be written in file
	 * @param destPath : Destination path of file including filename and extension
	 * @return boolean value indicating result of execution
	 * @throws Exception
	 */
	public static boolean writeToFile(String content, String destPath) throws Exception{
		
		boolean writeStatus = true;
		
		try {
			
            Files.write(Paths.get(destPath), content.getBytes());
            
        } catch (Exception e) {
        	
        	writeStatus = false;
        	throw e;
            
        }
		return writeStatus;
	}
	
	/**
	 * 
	 * @param srcPath: Source path of file including filename and extension
	 * @param destPath: Destination path of file including filename and extension
	 * @return boolean value indicating result of execution
	 * @throws Exception 
	 * 
	 */
	public static boolean copyFile(String srcPath, String destPath) throws Exception{
		boolean copyStatus = true;
		
		try{
			
			Files.copy(Paths.get(srcPath), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
			
		}catch(Exception e){
			
			copyStatus = false;
        	throw e;
		}
		
		return copyStatus;
	}
	
	/**
	 * 
	 * @param filePath : path of file including filename and extension
	 * @return boolean value indicating result of execution
	 * @throws Exception
	 */
	public static boolean deleteFile(String filePath) throws Exception{
		boolean deleteStatus = true;
		
		try {
			
			Files.deleteIfExists(Paths.get(filePath));
			
		} catch (Exception e) {

			deleteStatus = false;
        	throw e;
		} 
		
		return deleteStatus;
	}
	

}
