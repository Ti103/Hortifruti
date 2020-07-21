import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {

	public static void start(String texto) {
		DemoJFileChooser d = new DemoJFileChooser();
		d.createWindow(texto);
	}

	public static void write(String texto) {
		FileWriter arq;
		
		try {
			if(DemoJFileChooser.file2.exists()) {
				arq = new FileWriter(DemoJFileChooser.file2, true);							
			}else {
				arq = new FileWriter(DemoJFileChooser.file2);
			}
			BufferedWriter bw = new BufferedWriter(arq);
			bw.write(texto);
			bw.close();
			arq.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}