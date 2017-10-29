import java.io.File;
import java.io.PipedReader;
import java.io.PipedWriter;

import Filters.AverageColAGroupByColB;
import Filters.CsvPipeMerge;
import Filters.CsvReader;
import Filters.CsvSplit;
import Filters.CsvWriter;
import Filters.MergeColAGroupByColB;
import Filters.SortByCol;

/**
 * Sistema pedido na alinea b)
 * @author João Marques de Barros Mendes Leal, 46394;
 * @author Francisco João Guimarães Coimbra de Almeida Araújo, 45701;
 * @author João Pedro Pereira Becho, 42103
 *
 */
public class SimpleSystem {
	private File file;

	private PipedWriter pWriter,pWriter2,pWriter3,pWriter4,pWriter5,pWriter6,pWriter7,pWriter8;
	private PipedReader pReader,pReader2,pReader3,pReader4,pReader5,pReader6,pReader7,pReader8;

	public SimpleSystem(File file){
		this.file = file;

		//Pipe 1
		pWriter =new PipedWriter();
		pReader = new PipedReader();

		//Pipe 2
		pWriter2 = new PipedWriter();
		pReader2 = new PipedReader();

		//Pipe 3
		pWriter3 = new PipedWriter();
		pReader3 = new PipedReader();

		//Pipe 4
		pWriter4 = new PipedWriter();
		pReader4 = new PipedReader();

		//Pipe 5
		pWriter5 = new PipedWriter();
		pReader5 = new PipedReader();

		//Pipe 6
		pWriter6 = new PipedWriter();
		pReader6 = new PipedReader();

		//Pipe 7
		pWriter7 = new PipedWriter();
		pReader7 = new PipedReader();

		//Pipe 8
		pWriter8 = new PipedWriter();
		pReader8 = new PipedReader();

	}

	public void printer(){

		
		try {
			pWriter.connect(pReader);
			pWriter2.connect(pReader2);
			pWriter3.connect(pReader3);
			pWriter4.connect(pReader4);
			pWriter5.connect(pReader5);
			pWriter6.connect(pReader6);
			pWriter7.connect(pReader7);
			pWriter8.connect(pReader8);
			//vamos ler do ficheiro CSV
			CsvReader reader = new CsvReader(pWriter, file);

			//vamos fazer sort por departamento
			SortByCol sortByCol = new SortByCol(pReader, pWriter2,2);

			//vamos fazer split do sort
			CsvSplit split = new CsvSplit(pReader2, pWriter3, pWriter4);

			//vamos fazer as average da idade e ordenado por departamento
			AverageColAGroupByColB aIdade = new AverageColAGroupByColB(pReader4, pWriter6, "idade", 1, 2)
					,aOrdenado = new AverageColAGroupByColB(pReader3, pWriter5, "ordenado", 6, 2);

			//vamos juntar os pipes de output das medias num so
			CsvPipeMerge merge = new CsvPipeMerge(pReader5, pReader6, pWriter7);

			//vamos juntar o output das medias num so output
			MergeColAGroupByColB mCol = new MergeColAGroupByColB(pReader7,pWriter8,1,0);

			//vamos escrever para o standard output
			CsvWriter writer = new CsvWriter(pReader8);

			reader.run();
			sortByCol.run();
			split.run(); 
			aOrdenado.run();
			aIdade.run();
			merge.run();
			mCol.run();
			writer.run();
			
		}catch(NumberFormatException e){
			System.out.println("Nao eh possivel calcular a media nos valores dessa coluna");
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Ficheiro nao vai ter tantas categorias! Erro: " + e);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Erro: " + e);
		}

	}
}