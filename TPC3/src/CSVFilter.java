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
import Filters.CsvSplit;
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

		PipedWriter pWriter5 = new PipedWriter();
		PipedReader pReader5 = new PipedReader();

		//pipe3
		PipedWriter pWriter6 =new PipedWriter();
		PipedReader pReader6 = new PipedReader();

		//pipe3
		PipedWriter pWriter7 = new PipedWriter();
		PipedReader pReader7 = new PipedReader();

		PipedWriter pWriter8 = new PipedWriter();
		PipedReader pReader8 = new PipedReader();

		//pWriter = Pipe.open();
		//pReader = Pipe.open();

		File file = new File(NOME_FICHEIRO);

		try {

			pWriter.connect(pReader);
			pWriter2.connect(pReader2);
			pWriter3.connect(pReader3);
			pWriter4.connect(pReader4);
			pWriter5.connect(pReader5);
			pWriter6.connect(pReader6);
			pWriter7.connect(pReader7);
			pWriter8.connect(pReader8);

			//pWriter = Pipe.open();
			CsvReader reader = new CsvReader(pWriter, file); //ler de um ficheiro para um pipe

			CsvReader reader1 = new CsvReader(pWriter, file);
			//vamos fazer sort por departamento
			SortByCol sortByCol = new SortByCol(pReader, pWriter2,2);

			//vamos fazer split do sort
			CsvSplit split = new CsvSplit(pReader2, pWriter3, pWriter4);
			AverageColAGroupByColB aIdade = new AverageColAGroupByColB(pReader3, pWriter5, "idade", 1, 2)
					,aOrdenado = new AverageColAGroupByColB(pReader4, pWriter6, "ordenado", 3, 2);

			//AverageColAGroupByColB aCb = new AverageColAGroupByColB(pReader2, pWriter3, "Ordenado", 3, 2);
			CsvWriter write = new CsvWriter(pReader5); // porque tinha que passar pelo SortByCol
			CsvWriter write2 = new CsvWriter(pReader6);

			reader.start();
			sortByCol.start();
			split.start();
			//aCb.start();
			//mCol.start();
			write.start();
			
			while(write.isAlive()){
				Thread.sleep(100);
			}
			
			write2.start();

		} catch (FileNotFoundException e) {
			System.err.println("Nao encontramos o ficheiro: " + NOME_FICHEIRO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
