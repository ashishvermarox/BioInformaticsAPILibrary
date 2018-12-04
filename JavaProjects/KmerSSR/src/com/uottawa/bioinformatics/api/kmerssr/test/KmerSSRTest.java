/**
 * 
 */
package com.uottawa.bioinformatics.api.kmerssr.test;

import com.uottawa.bioinformatics.api.kmerssr.KmerSSR;

/**
 * @author Ashish
 * Student ID : 300059114
 * email : averm019@uottawa.ca
 *
 */
public class KmerSSRTest {

	/**
	 * @param args
	 */
	
	static String dna = "TAAAATTAAAATTAAAATAAAAAA";
	static String dna1 = "ATATATATATATATATA";
	static String dna2 = "ABDKJYTEVKLFDSCVT";
	static int periodSizeFrom = 2;
	static int periodSizeTo = 6;
	static int minRepeats = 2;
	static int sleepTime = 15000;
	static String fileName = "abc.txt";
	static String filePath = "C:/University/AlgoInBio/Project/FolderZone/kmerssr/ProcessZone/";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(KmerSSR.implementSSR(dna, periodSizeFrom, periodSizeTo, minRepeats, filePath, fileName, sleepTime));
		
	}

}
