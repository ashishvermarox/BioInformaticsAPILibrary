/**
 * Used to perform unit test on File Utility modules
 */
package com.uottawa.bioinformatics.resusable.fileutilities.test;

import com.uottawa.bioinformatics.resusable.fileutilities.FileUtilities;

/**
 * @author Ashish
 * Student ID : 300059114
 * email : averm019@uottawa.ca
 *
 */
public class FileUtilitiesTest {

	/**
	 * Unit testing for FileUtilities Class
	 */
	
	static String srcPath = "C:/University/AlgoInBio/Project/FolderZone/kmerssr/ProcessZone/abc.txt";
	static String destPath = "C:/University/AlgoInBio/Project/FolderZone/kmerssr/ArchiveZone/Output/abc.txt";
	static String content = "this is sample content";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		// write to file
		//System.out.println(FileUtilities.writeToFile(content, destPath));
		
		//Copy Files
		//System.out.println(FileUtilities.writeToFile(content+" extra content", srcPath));
		//System.out.println(FileUtilities.copyFile(srcPath, destPath));
		
		//Move Files
		System.out.println(FileUtilities.moveFile(srcPath, destPath));
	}

}
