import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.nio.channels.Pipe;

import Filters.AverageColAGroupByColB;
import Filters.CsvReader;
import Filters.SortByCol;
import Filters.CsvWriter;
import Filters.MergeColAGroupByColB;

/**
 * TODO -> provavelmente vamos meter isto dividido bem
 * @author grupo 2
 * Systema onde vamos fazer as coisas
 * NOTE: Ate agora completamente copiado
 * EStou a pensar deixar isto como o main
 */
public class CSVFilter {
	
	private static final String NOME_FICHEIRO = "empregados.txt";
	
	public static void main(String[] args) {
		
		//talvez tenhamos de usar isto em vez de pipeReader e pipeWriters
		PipedInputStream input = new PipedInputStream();
		PipedOutputStream output = new PipedOutputStream();
		
		
		//pipe1
		//o pwriter eh o input de um pipe
		PipedWriter pWriter =new PipedWriter();
		//o preader eh o output de um pipe
		PipedReader pReader = new PipedReader();
		
		//pipe2
		PipedWriter pWriter2 = new PipedWriter();
		PipedReader pReader2 = new PipedReader();
		
		//pipe3
		PipedWriter pWriter3 =new PipedWriter();
		PipedReader pReader3 = new PipedReader();
		
		//pipe3
		PipedWriter pWriter4 = new PipedWriter();
		PipedReader pReader4 = new PipedReader();
		
		//pWriter = Pipe.open();
		//pReader = Pipe.open();
		
		File file = new File(NOME_FICHEIRO);
		
		try {
			
			pWriter.connect(pReader);
			pWriter2.connect(pReader2);
			pWriter3.connect(pReader3);
			pWriter4.connect(pReader4);
			
			//pWriter = Pipe.open();
			CsvReader reader = new CsvReader(pWriter, file); //ler de um ficheiro para um pipe
			SortByCol sortByCol = new SortByCol(pReader, pWriter2,2);
			//AverageColAGroupByColB aCb = new AverageColAGroupByColB(pReader, pWriter2, "Ordenado", 3, 2);
			CsvWriter write = new CsvWriter(pReader3); // porque tinha que passar pelo SortByCol
			MergeColAGroupByColB mCol = new MergeColAGroupByColB(pReader2, pWriter3, 3, 2);
			
			
			reader.start();
			sortByCol.start(); 
			//aCb.start();
			mCol.start();
			write.start();
			
			
		} catch (FileNotFoundException e) {
			System.err.println("Nao encontramos o ficheiro: " + NOME_FICHEIRO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
