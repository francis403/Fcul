package Filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class MergeColAGroupByColB extends Thread{
	
	private Reader input;
	private Writer output;
	private int colA,colB;
	
	public MergeColAGroupByColB(Reader input,
			Writer output,int colA,int colB){
		this.input = input;
		this.output = output;
		this.colA = colA;
		this.colB = colB;
	}
	
	public void run(){
		BufferedReader bf = new BufferedReader(input);
		String line;
		try {
			String prev_comp = null;
			while((line = bf.readLine()) != null) {
				String[] elems = line.split(",");
				if(!elems[colB].equals(prev_comp))
					output.write("\n"+ elems[colB]);
				
				output.write(","+elems[colA]);
				prev_comp = elems[colB];
				
			}
			output.close();
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
