import java.io.File;
import java.io.IOException;

public class SimpleClient {
	private static final String NOME_FICHEIRO = "empregados.txt";
	public static void main(String[] args){
		SimpleSystem ss = new SimpleSystem(new File(NOME_FICHEIRO));
		try {
			ss.printer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
