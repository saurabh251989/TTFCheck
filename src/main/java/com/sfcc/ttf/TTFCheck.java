package com.sfcc.ttf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author saurabh.k
 *
 */
public class TTFCheck {
	public static void main(String[] args) {

		ExecutedFile executedFile = new ExecutedFile();
		String ptuFolder = "PTU-RES";
		String currentDir = System.getProperty("user.dir");

		executedFile.TTFList();
		executedFile.PTUList(currentDir + "\\" + ptuFolder);

		HashMap<String, List<String>> sourceandptu = executedFile.getSourceAndPtu();

		executedFile.XRDList(currentDir + "\\" + ptuFolder);
		executedFile.TIOList(currentDir + "\\" + ptuFolder);
		executedFile.FDCList(currentDir + "\\" + ptuFolder);
		GroupFile gFile = new GroupFile(executedFile.getPtuList(), executedFile.getXrdList(), executedFile.getTioList(),
				executedFile.getFdcList(), executedFile.getTtfList());
		gFile.genereateTTF(sourceandptu);
		gFile.sortTTF(gFile.genereateTTF(sourceandptu));

		gFile.genereateTTF(sourceandptu);
		gFile.sortTTF(gFile.genereateTTF(sourceandptu));

		for (HashMap.Entry<String, List<String>> entry : gFile.sortTTF(gFile.genereateTTF(sourceandptu)).entrySet()) {


			System.out.println(entry.getKey());
			for (Iterator<String> iterator = entry.getValue().iterator(); iterator.hasNext();) {
				String type = (String) iterator.next();
				System.out.println("\t"+type);
			}
			
		}	
		
		gFile.groupTTF();
		gFile.sortTTF(gFile.groupTTF());

		HashMap<String, String> comment = ValidateTTF.getCommnet(gFile.sortTTF(gFile.genereateTTF(sourceandptu)),
				gFile.sortTTF(gFile.groupTTF()));
		HTMLReport.generateHTMLReport(comment);

	}

}
