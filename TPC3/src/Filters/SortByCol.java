package Filters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

//TODO
public class SortByCol extends Thread{
	//private PipedInputStream input;
	//private PipedOutputStream output;
	
	private Reader input;
	private Writer output;	
	private int indice;

	/**
	public SortByCol(PipedInputStream input, PipedOutputStream output,int indice) {
		this.input = input;
		this.output = output;
		this.indice = indice;
	}
	**/
	public SortByCol(Reader input, Writer output,int indice){
		this.input = input;
		this.output = output;
		this.indice = indice;
	}
	public void run(){
		
		/**
		byte[] buffer = new byte[100]; //vamos ter isto como buffer
		int r;
		StringBuilder sb = new StringBuilder();
		try {
			while((r = input.read()) != -1){
				sb.append((char) r);
			}
			System.out.println(sb); //teste
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader bfReader = new BufferedReader(input);
		String line;

		try {
			if(!bfReader.ready())
				System.out.println("Not ready");
			sort(list,bfReader,indice);
			for(String l : list){
				output.write(l+ "\n");
				//System.out.println(l);
			}
			input.close();
			output.close();
			bfReader.close();

		}
		catch (IOException e) {
			System.out.println ("Input/output error: " + e);
		}
		

		//System.out.println ("SortByCol finished...");
	}

	public void sort(List<String> list,BufferedReader bf,int indice)
			throws IOException{
		String line;
		while((line = bf.readLine()) != null) {
			list.add(line);
		}
		list.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				String[] lines = o1.split(","),lines2 = o2.split(",");
				return lines[indice].compareTo(lines2[indice]);
			}
		});
	}
	

}
