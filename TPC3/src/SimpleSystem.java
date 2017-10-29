import java.io.File;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import Filters.AverageColAGroupByColB;
import Filters.CsvPipeMerge;
import Filters.CsvReader;
import Filters.CsvSplit;
import Filters.CsvWriter;
import Filters.MergeColAGroupByColB;
import Filters.SortByCol;

public class SimpleSystem {
	private File file;
	
	private PipedWriter pWriter,pWriter2,pWriter3,pWriter4,pWriter5,pWriter6,pWriter7,pWriter8;
	private PipedReader pReader,pReader2,pReader3,pReader4,pReader5,pReader6,pReader7,pReader8;
	
	public SimpleSystem(File file){
		this.file = file;
		//o pwriter eh o input de um pipe
		pWriter =new PipedWriter();
		pReader = new PipedReader();
		
		//pipe2
		pWriter2 = new PipedWriter();
		pReader2 = new PipedReader();
		
		pWriter3 = new PipedWriter();
		pReader3 = new PipedReader();
		
		pWriter4 = new PipedWriter();
		pReader4 = new PipedReader();
		
		pWriter5 = new PipedWriter();
		pReader5 = new PipedReader();
		pWriter6 = new PipedWriter();
		pReader6 = new PipedReader();
		
		pWriter7 = new PipedWriter();
		pReader7 = new PipedReader();
		pWriter8 = new PipedWriter();
		pReader8 = new PipedReader();
		
	}
	
	public void printer() throws IOException, InterruptedException{
		//pipe1
		//o pwriter eh o input de um pipe


		pWriter.connect(pReader);
		pWriter2.connect(pReader2);
		pWriter3.connect(pReader3);
		pWriter4.connect(pReader4);
		pWriter5.connect(pReader5);
		pWriter6.connect(pReader6);
		pWriter7.connect(pReader7);
		pWriter8.connect(pReader8);

		//vamos ler
		CsvReader reader = new CsvReader(pWriter, file);
		//vamos fazer sort por departamento
		SortByCol sortByCol = new SortByCol(pReader, pWriter2,2);

		//vamos fazer split do sort
		CsvSplit split = new CsvSplit(pReader2, pWriter3, pWriter4);
		AverageColAGroupByColB aIdade = new AverageColAGroupByColB(pReader4, pWriter6, "idade", 1, 2)
				,aOrdenado = new AverageColAGroupByColB(pReader3, pWriter5, "ordenado", 3, 2);

		CsvPipeMerge merge = new CsvPipeMerge(pReader5, pReader6, pWriter7);

		MergeColAGroupByColB mCol = new MergeColAGroupByColB(pReader7,pWriter8,1,0); //o erro tem de estar aqui

		CsvWriter writer = new CsvWriter(pReader8);

		reader.run();
		sortByCol.run();
		split.run(); 
		aOrdenado.run();
		aIdade.run();
		merge.run();
		mCol.run();
		writer.run();
		
	}
	
}
