import java.io.IOException;
import java.io.Reader;

/**
 * The Consumer of the 
 * @author fc45701
 * 
 */
public class Consumer extends Thread {
	private Reader stream;
	private char[] chars;
	Consumer (Reader p) {
		stream = p;
		chars = new char[100];
	}
	public void run() {
		System.out.println ("Consumer started... ");
		try {
			int i = 0, r;
			while ((r = stream.read()) != -1) {
				System.out.println (i++ + ". consumed: " + (char)r);
				if (i<100)
					chars[i]=(char)r;
				try {
					sleep ((int)(Math.random()*1000));
				} catch (InterruptedException e) {}
			}
			stream.close();
		}
		catch (IOException e) {
			System.out.println ("Input/output error: " + e);
		}
		System.out.println ("Consumer finished...");
	}

	public char[] getChars(){
		return chars;
	}
} 