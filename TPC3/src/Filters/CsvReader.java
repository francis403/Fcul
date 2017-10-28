package Filters;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 
 * @author fc45701
 * TODO -> Se nao for necessario estar a ler e a escrever ao mesmo tempo
 *        Podemos tirar isto das thread
 */
public class CsvReader extends Thread{
	private Writer stream; //vamos precisar de isto?
	private File file;
	//private Writer output;
	//private byte []buffer;
	//Pipe output;
	
	public CsvReader (Writer p, File file) throws FileNotFoundException {
		stream = p;
		this.file = file;
	}
	
	/**
	public CsvReader (PipedWriter output, File file){
		this.output = output;
		this.file = file;
	}
	**/
	public void run(){
		try {
			//System.out.println ("CsvReader started... ");
			
			FileReader flReader = new FileReader(file);
			BufferedReader bfReader = new BufferedReader(flReader);
			String line;
			
			try {
				ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
				while((line = bfReader.readLine()) != null) {
					stream.write(line + "\n");
					//output.write((line + "\n").getBytes());
					buffer.put(line.getBytes());
				}
				//output.sink().write(buffer);
				
				//output.close();
				//output.sink().close();
				bfReader.close();
				flReader.close();
				stream.close(); // reader can continue reading
				//output.close(); //nao sei se vamos precisar disto
			}
			catch (IOException e) {
				System.out.println ("Input/output error: " + e);
			}
			
			
			//System.out.println ("CsvReader finished...");
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
}
