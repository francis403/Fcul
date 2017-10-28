import java.io.IOException;
import java.io.Writer;

/**
 * 
 * @author fc45701
 *
 */
public class Producer extends Thread {
	Writer stream;
	Producer (Writer p) {
		stream = p;
	}
	public void run() {
		System.out.println ("Producer started... ");
		try {
			for (int i = 0; i < 9; i++) {
				char c = (char)(Math.random()*26 + 'a');
				stream.write (c); // producing
				System.out.println (i + ". produced: " + c);
				try {
					sleep ((int)(Math.random()*1000));
				} catch (InterruptedException e) {
				}
			}
			stream.close(); // reader can continue reading
		}
		catch (IOException e) {
			System.out.println ("Input/output error: " + e);
		}
		System.out.println ("Producer finished...");
	}
}
