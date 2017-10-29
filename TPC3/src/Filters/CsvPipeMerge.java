package Filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Filtro que junta dois pipes num soh
 * @author João Marques de Barros Mendes Leal, 46394;
 * @author Francisco João Guimarães Coimbra de Almeida Araújo, 45701;
 * @author João Pedro Pereira Becho, 42103
 *
 */
public class CsvPipeMerge extends Thread{
	private Reader input1;
	private Reader input2;
	private Writer output;

	public CsvPipeMerge(Reader input1, Reader input2,Writer output){
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
	}

	public void run(){

		BufferedReader bfReader1 = new BufferedReader(input1);
		BufferedReader bfReader2 = new BufferedReader(input2);

		String line,line2;

		try {
			while((line = bfReader1.readLine()) != null && (line2 = bfReader2.readLine()) != null){
				if(line != null)
					output.write(line + "\n");
				if(line2 != null)
					output.write(line2 + "\n");
			}

			input1.close();
			input2.close();
			output.close();
			bfReader1.close();
			bfReader2.close();

		}
		catch (IOException e) {
			System.out.println ("Input/output error: " + e);
		}
	}
}