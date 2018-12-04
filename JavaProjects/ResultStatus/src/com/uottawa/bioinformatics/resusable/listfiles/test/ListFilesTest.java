/**
 * 
 */
package com.uottawa.bioinformatics.resusable.listfiles.test;

import com.uottawa.bioinformatics.resusable.listfiles.ListFiles;

/**
 * @author Ashish
 *
 */
public class ListFilesTest {

	/**
	 * @param args
	 */
	
	static String processingPath = "C:/University/AlgoInBio/Project/FolderZone/kmerssr/ProcessZone/";
	static String archivePath = "C:/University/AlgoInBio/Project/FolderZone/kmerssr/ArchiveZone/Output/";
	static String sessionID = "1543754689932";
	static String displayResultURL = "http://localhost:7003/soa/uottawa/displayresult?file=";
	static String processText = "Processing...";
	static String fileName = "abc.txt";
	
	static String statusComplete = "complete";
	static String statusProcessing = "processing";
	static String status = statusProcessing;
	
	static String moveFile = fileName;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(ListFiles.listFiles(processingPath, archivePath, sessionID, status, displayResultURL, processText));
		System.out.println(ListFiles.moveFiles(processingPath, archivePath, moveFile));
	}

}
