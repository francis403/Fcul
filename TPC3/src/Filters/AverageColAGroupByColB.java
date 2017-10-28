package Filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;

public class AverageColAGroupByColB extends Thread{
	
	private Reader input;
	private Writer output;
	private String key;
	private int colA,colB;
	
	public AverageColAGroupByColB(Reader input,
			Writer output,String key, int colA,int colB){
		this.input = input;
		this.output = output;
		this.key = key;
		this.colA = colA;
		this.colB = colB;
	}
	
	public void run(){
		try {
			System.out.println("AverageColAGroupByColB is starting...");
			/**
			PipedWriter pw = new PipedWriter(); // vai escrever aqui o que nos queremos ver
			// o pw vai escrever para o pr
			PipedReader pr = new PipedReader(); // vamos ler o que eu receber do sort
			pw.connect(pr);
			**/
			//SortByCol s = new SortByCol(input, output, colB); // sort pela coluna b
			BufferedReader bfReader = new BufferedReader(input);
			ArrayList<String> list = new ArrayList<String>();
			String l;
			double media = 0;
			int count = 0;
			
			try{
				if(!bfReader.ready())
					System.out.println("Not ready");
				//s.sort(list, bfReader, colB);
				String prev_comp = null;
				double val;
				output.write("\n\t" + key + "\n");
				while((l = bfReader.readLine()) != null) {
					//list.add(l);
					//output.write(l + "\n");
					String[] elems = l.split(",");
					//se o componente anterior for diferente do anterior
					if(!elems[colB].equals(prev_comp) && prev_comp != null){
						//mostramos a media anterior
						output.write(prev_comp + ": " + media/count + "\n");
						media = 0;
						count = 0;
					}
					val = Double.parseDouble(elems[colA]);
					media += val;
					count++;
					prev_comp = elems[colB]; // o componente seguinte vai ser o anterior
				}
				output.write(prev_comp + ": " + media/count);
			}catch(NumberFormatException e){
				System.out.println("Nao eh possivel calcular a media nos valores dessa coluna");
			}
			input.close();
			output.close();
			bfReader.close();
			System.out.println("AverageColAByGroupByColB is closing...");
		}
		
		catch (IOException e) {
			System.out.println ("Input/output error: " + e);
		}
	}

}
