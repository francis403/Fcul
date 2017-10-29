package Filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Filtro pedido na alinea a), que ordena o conteudo de um pipe por uma determinada coluna
 * @author João Marques de Barros Mendes Leal, 46394;
 * @author Francisco João Guimarães Coimbra de Almeida Araújo, 45701;
 * @author João Pedro Pereira Becho, 42103
 *
 */
public class SortByCol extends Thread{

	private Reader input;
	private Writer output;	
	private int indice;

	public SortByCol(Reader input, Writer output,int indice){
		this.input = input;
		this.output = output;
		this.indice = indice;
	}
	public void run(){

		ArrayList<String> list = new ArrayList<String>();
		BufferedReader bfReader = new BufferedReader(input);

		try {
			sort(list,bfReader,indice);

			for(String l : list){
				output.write(l+ "\n");
			}
			input.close();
			output.close();
			bfReader.close();

		}
		catch (IOException e) {
			System.out.println ("Input/output error: " + e);
		}
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