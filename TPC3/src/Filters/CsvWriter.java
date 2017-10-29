package Filters;

import java.io.Reader;

/**
 * Filtro Writer pedido na alinea a), tem nome diferente porque jah existia uma classe com esse nome
 * Le de um pipe e escreve no standard output
 * @author João Marques de Barros Mendes Leal, 46394;
 * @author Francisco João Guimarães Coimbra de Almeida Araújo, 45701;
 * @author João Pedro Pereira Becho, 42103
 *
 */
public class CsvWriter extends Thread{

	private Reader stream;
	private char[] chars;

	public CsvWriter(Reader stream){
		this.stream = stream;
	}

	public void run() {
		try {

			int r;

			while ((r = stream.read()) != -1) {
				System.out.print ((char)r);
			}
			stream.close();

		}
		catch (Exception e) {
			System.out.println ("Input/output error: " + e);
		}
	}

	public char[] getChars(){
		return chars;
	}
}