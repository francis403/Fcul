import java.io.File;
import java.io.IOException;

/**
 * Executa o sistema pedido na alinea b)
 * @author João Marques de Barros Mendes Leal, 46394;
 * @author Francisco João Guimarães Coimbra de Almeida Araújo, 45701;
 * @author João Pedro Pereira Becho, 42103
 *
 */
public class SimpleClient {

	private static final String NOME_FICHEIRO = "empregados.csv";

	public static void main(String[] args){

		SimpleSystem ss = new SimpleSystem(new File(NOME_FICHEIRO));

		
		ss.printer();
		
	}
}