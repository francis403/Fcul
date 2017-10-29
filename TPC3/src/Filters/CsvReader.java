package Filters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

/**
 * Filtro que le um ficheiro e coloca os conteudos num pipe, alinea a)
 * @author João Marques de Barros Mendes Leal, 46394;
 * @author Francisco João Guimarães Coimbra de Almeida Araújo, 45701;
 * @author João Pedro Pereira Becho, 42103
 *
 */
public class CsvReader extends Thread{
	private Writer stream;
	private File file;
	
	public CsvReader (Writer p, File file) throws FileNotFoundException {
		stream = p;
		this.file = file;
	}
	
	public void run(){
		
		try {
			
			FileReader flReader = new FileReader(file);
			BufferedReader bfReader = new BufferedReader(flReader);
			String line;
			
			try {
				while((line = bfReader.readLine()) != null) {
					stream.write(line + "\n");
				}
				bfReader.close();
				flReader.close();
				stream.close();
			}
			catch (IOException e) {
				System.out.println ("Input/output error: " + e);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}