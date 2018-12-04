/**
 * 
 */
package com.uottawa.bioinformatics.api.kmerssr;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * @author Ashish
 * Student ID : 300059114
 * email : averm019@uottawa.ca
 *
 */
public class KmerSSR implements Runnable{
	
	String dna = null;
	int[] periodSize = null;
	boolean[] boolFilter = null;
	int minRepeats = 2;
	String filePath = null;
	String fileName = null;
	int sleepTime = 0;
	
	public KmerSSR(String dna, int pFrom, int pTo, int minRepeats, String filePath, String fileName, int sleepTime) {
        this.dna = dna;
        
        this.periodSize = new int[pTo-pFrom];
		this.periodSize = getPeriodRangeAndSort(pFrom, pTo);
		
		this.boolFilter = new boolean[dna.length()];
		this.minRepeats = minRepeats;
		this.filePath = filePath;
		this.fileName = fileName;
		this.sleepTime = sleepTime;
		
     }
	
	public void run(){
		
		String result = mainImplementSSR(dna, periodSize, boolFilter, minRepeats);
		String content = ">DNA Sequence\n"+dna+"\n\n";
		content += result;
		
		try {
			writeToFile(content, (filePath+fileName), sleepTime);
				
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static boolean implementSSR(String dna, int pFrom, int pTo, int minRepeats, String filePath, String fileName, int sleepTime) throws Exception{
		//String result = null;
		
		//Getting range of period sizes and sorting it in descending order
		//int[] periodSize = new int[pTo-pFrom];
		//periodSize = getPeriodRangeAndSort(pFrom, pTo);
		
		//By default the boolFilter is set to False
		//boolean[] boolFilter = new boolean[dna.length()];
		//System.out.println(boolFilter[2]);
		
		Thread th = new Thread(new KmerSSR(dna, pFrom, pTo, minRepeats, filePath, fileName, sleepTime));
		th.start();
		
//		result = mainImplementSSR(dna, periodSize, boolFilter, minRepeats);
//		
//		String content = ">DNA Sequence\n"+dna+"\n\n";
//		content += result;
//		
//		if(writeToFile(content, (filePath+fileName)))
//			return true;
//		else
			return true;
	}
	
	
	protected static int[] getPeriodRangeAndSort(int from, int to){
		
		int[] periodSize = IntStream.rangeClosed(from, to).toArray();
		
		for (int i = 0; i < periodSize.length/2; i++) {
	        int temp = periodSize[i];
	        periodSize[i] = periodSize[periodSize.length - 1 - i];
	        periodSize[periodSize.length - 1 - i] = temp;
		}
		
//		for (int i=0; i<periodSize.length; i++)
//			System.out.println(periodSize[i]);
		
		return periodSize;
		
	}
	
	protected static String mainImplementSSR(String dna, int[] periodSize, boolean[] boolFilter, int minRepeats){
		
		String result = "";
		int ssrStart = 0;
		int ssrEnd = 0;
		ArrayList<String> subssr = new ArrayList<String>(3); 
		
		for(int searchPeriod = 0; searchPeriod < periodSize.length; searchPeriod++){
			
			int dnaStart = 0;
			
			while(dnaStart < dna.length()){
				
				subssr = searchForSSR(periodSize[searchPeriod], dna, dnaStart);
				
				ssrStart = getSSRStartPos(dna, subssr);
				ssrEnd = getSSREndPos(dna, subssr);
				
				if(checkBooleanFilterSet(boolFilter, ssrStart, ssrEnd) && (ssrStart !=ssrEnd) && (Integer.parseInt(subssr.get(1)) >= minRepeats)){
					//System.out.println("SSRStart : "+ssrStart+"SSREnd : "+ssrEnd);
					result += "SSR String : "+subssr.get(0) +"\n";
					result += "SSR Period Size : "+periodSize[searchPeriod] +"\n";
					result += "SSR Repeats : "+subssr.get(1) +"\n";
					result += "SSR Starting Index : "+subssr.get(2)+"\n";
					result += "SSR Ending Index : "+(Integer.parseInt(subssr.get(2))+(periodSize[searchPeriod]*Integer.parseInt(subssr.get(1)))-1)+"\n";
					result += "----------------------------------------- \n\n";
					
					//System.out.println(ssrStart);
					//System.out.println(ssrEnd);
					
					for(int i = ssrStart; i <= ssrEnd; i++)
						boolFilter[i] = true;
					
					dnaStart = dnaStart + ((subssr.get(0).length()) - (periodSize[searchPeriod]) - 1) ;
					
				}
				dnaStart++;
				//System.out.println("dnastart : "+dnaStart+" subssr length "+subssr.get(0).length()+" periodsize "+periodSize[searchPeriod]);
				
			}
		}
		
		if(result.equals(""))
			result = "No SSR found";
		
		return result;
		
	}
	
	protected static ArrayList<String> searchForSSR(int period, String seq, int startIndex){
		
		String baseString = null;
		String nextString = null;
		ArrayList<String> ssr = new ArrayList<String>(3); 
		
		ssr.add(""); //SSR String
		ssr.add("0"); //SSR number of repeats
		ssr.add("0"); //SSR Starting Index
		
		//System.out.println("period : " +period + "stidx : "+startIndex);
		
		int repeats = 0;
		int pos = startIndex;
		
		baseString = getSubSequence(pos, period, seq);
		nextString = getSubSequence(pos, period, seq);
		
		//pos = pos + period;
		
		//System.out.println("Intital String : base : "+baseString +" next : "+nextString);
		
		while (baseString.equals(nextString) && (pos <= (seq.length() - period))){
			//System.out.println("loop check -- base : "+baseString+" nextstr : "+nextString+" pos : "+pos);
			//System.out.println("seq length : "+seq.length());
			//System.out.println("base : "+baseString);
			//System.out.println(nextString);
			repeats++;
			
			pos = pos + period;

			nextString = getSubSequence(pos, period, seq);
			
			//System.out.println("next str in loop : "+nextString);
		}
		
		if(repeats > 1){
			ssr.set(0, baseString); //SSR String
			ssr.set(1, Integer.toString(repeats)); //SSR number of repeats
			ssr.set(2, Integer.toString(startIndex)); //SSR Starting Index
		}
		
		return ssr;
		
	}
	
	protected static String getSubSequence(int pos, int period, String seq){
		//System.out.println("substr next pos start "+pos+" end "+(pos + period));
		if ((pos + period) <= seq.length())
			return seq.substring(pos, (pos + period));
		else
			return "";
		
	}
	
	protected static int getSSRStartPos(String seq, ArrayList<String> ssr){
		
		int start = seq.indexOf(ssr.get(0), Integer.parseInt(ssr.get(2)));
		
		return start;
	}
	
	protected static int getSSREndPos(String seq, ArrayList<String> ssr){
		
		int end = seq.lastIndexOf(ssr.get(0), seq.length());
		
		return (end+ssr.get(0).length() - 1);
	}
	
	protected static boolean checkBooleanFilterSet(boolean[] boolFilter, int startPos, int endPos){
		
		boolean boolChkFlag = true;
		
		for (int index = startPos; index < endPos; index++){
			if(boolFilter[index] == true)
				boolChkFlag = false;
		}
		
		return boolChkFlag;
	}
	
	protected static boolean writeToFile(String content, String destPath, int sleepTime) throws Exception{
		
		boolean writeStatus = true;
		
		try {
			
			Thread.sleep(sleepTime);
            Files.write(Paths.get(destPath), content.getBytes());
            
        } catch (Exception e) {
        	
        	writeStatus = false;
        	throw e;
            
        }
		
		return writeStatus;
	}


}
