package Filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Filtro AverageColAGroupByColB pedido em a)
 * @author João Marques de Barros Mendes Leal, 46394;
 * @author Francisco João Guimarães Coimbra de Almeida Araújo, 45701;
 * @author João Pedro Pereira Becho, 42103
 *
 */
public class AverageColAGroupByColB extends Thread{

	private Reader input;
	private Writer output;
	private String key;
	private int colA, colB;

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

			BufferedReader bfReader = new BufferedReader(input);
			String l;
			double media = 0;
			int count = 0;

			try{
				String prev_comp = null;
				double val;

				while((l = bfReader.readLine()) != null) {
					String[] elems = l.split(",");

					//se o componente anterior for diferente do anterior
					if(!elems[colB].equals(prev_comp) && prev_comp != null){

						//mostramos a media anterior
						output.write(prev_comp + "," + key +"=" + media/count + "\n");
						media = 0;
						count = 0;
					}

					val = Double.parseDouble(elems[colA]);
					media += val;
					count++;
					prev_comp = elems[colB]; // o componente seguinte vai ser o anterior
				}

				output.write(prev_comp + "," + key +"=" + media/count + "\n");

			}catch(NumberFormatException e){
				System.out.println("Nao eh possivel calcular a media nos valores dessa coluna");
			}

			input.close();
			output.close();
			bfReader.close();
		}
		catch (IOException e) {
			System.out.println ("Input/output error: " + e);
		}
	}
}