package com.uottawa.bioinformatics.resusable.listfiles;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ListFiles {
	
	public static String listFiles(String processingPath, String archivePath, String sessionID, String status, String displayResultURL, String processText){
		String filelist = "";
		
		if(status.equals("complete")){
			
			File[] files = listFilesFromDir(archivePath, sessionID);
			filelist = listCompletedFiles(files, displayResultURL);
			
		}else if (status.equals("processing")){
			
			File[] files = listFilesFromDir(processingPath, sessionID);
			filelist = listProcessingFiles(files, processText);
			
		}else{
			
			File[] filesprocess = listFilesFromDir(processingPath, sessionID);
			String filelistProcess = listProcessingFiles(filesprocess, processText);
			
			File[] filescomplete = listFilesFromDir(archivePath, sessionID);
			String filelistComplete = listCompletedFiles(filescomplete, displayResultURL);
			
			filelist = filelistProcess + filelistComplete;
		}
		
		return filelist;
		
	}
	
	protected static File[] listFilesFromDir(String path, String sessionID){
		
		File directory = new File(path);
		File[] files = directory.listFiles(new FilenameFilter() {
			//apply a filter
			@Override
			public boolean accept(File dir, String name) {
				boolean result;
				if(name.startsWith(sessionID)){
					result=true;
				}
				else{
					result=false;
				}
				return result;
			}
		});
		
		return files;
		
	}
	
	protected static String listCompletedFiles(File[] files, String displayResultURL){
		
		String completedResult = "";
		String fileName = null;
		String apiName = null;
		
		for(File f:files){
			try {
				
				fileName = f.getName();
				apiName = fileName.split("-")[1];
				
				completedResult += "<tr><td>" + apiName + "</td><td><a href =\"" + displayResultURL + fileName + "\">" + fileName + "</a></td></tr>\n";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return completedResult;
		
	}
	
	protected static String listProcessingFiles(File[] files, String processText){
		
		String processingResult = "";
		String fileName = null;
		String apiName = null;
		
		for(File f:files){
			try {
				
				fileName = f.getName();
				apiName = fileName.split("-")[1];
				
				processingResult += "<tr><td>" + apiName + "</td><td>" + processText + "</td></tr>\n";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return processingResult;
		
	}
	
	public static boolean moveFiles(String srcPath, String destPath, String sessionID) throws Exception{
		
		boolean moveFlag = true;
		
		File[] files = listFilesFromDir(srcPath, sessionID);
		moveFlag = moveFileToDest(files, srcPath, destPath);
		
		return moveFlag;
	
	}
	
	protected static boolean moveFileToDest(File[] files, String srcPath, String destPath) throws Exception{
		
		String fileName = null;
		boolean filesMoved = true;
		
		for(File f:files){
			try {
				
				fileName = f.getName();
				Files.move(Paths.get(srcPath+fileName), Paths.get(destPath+fileName), StandardCopyOption.REPLACE_EXISTING);
				
			} catch (Exception e) {
				
				filesMoved = false;
		        throw e;
		        
			}
		}
		
		return filesMoved;
		
	}
	

}
