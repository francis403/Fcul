package Filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class CsvSplit  extends Thread{
	private Reader input;
	private Writer output1;
	private Writer output2;
	
	public CsvSplit(Reader input, Writer output1,Writer output2){
		this.input = input;
		this.output1 = output1;
		this.output2 = output2;
	}
	public void run(){
		
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader bfReader = new BufferedReader(input);
		
		String line;

		try {
			while((line = bfReader.readLine()) != null){
				output1.write(line + "\n");
				output2.write(line + "\n");
			}
			
			input.close();
			output1.close();
			output2.close();
			bfReader.close();

		}
		catch (IOException e) {
			System.out.println ("Input/output error: " + e);
		}
		

		//System.out.println ("SortByCol finished...");
	}
}
