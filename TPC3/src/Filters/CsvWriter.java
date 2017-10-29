package Filters;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

/**
 * Coloca num pipe as linhas lidas de uma dado ficheiro CSV
 * num formato pre-definido
 * @author Utilizador
 * acho que vamos mesmo de ter aqui um Pipe
 */
public class CsvWriter extends Thread{
	private Reader stream;
	private char[] chars;
	//private Pipe input; //nao sei se vai ser um PipedInputStream
	/**
	public CsvWriter (Reader p) {
		stream = p;
		chars = new char[100];
	}
	**/
	
	public CsvWriter(Reader stream){
		this.stream = stream;
	}
	
	/**
	public CsvWriter(Pipe input){
		this.input = input;
	}
	**/
	
	public void run() {
		ByteBuffer buffer = ByteBuffer.allocate(100);
		try {
			//input.source()
			int i = 0, r;
			
			/**
			while(input.source().read(buffer) != -1){
				System.out.println(StandardCharsets.UTF_8.decode(buffer)); //not working
				buffer.clear();
			}
			**/
			
			while ((r = stream.read()) != -1) {
				System.out.print ((char)r);
			}
			stream.close(); //temos de fechar isto???
	
		}
		catch (Exception e) {
			System.out.println ("Input/output error: " + e);
		}
		//System.out.println ("Writer finished...");
	}

	public char[] getChars(){
		return chars;
	}
}
