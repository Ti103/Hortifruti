import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DemoJFileChooser {
	
	static File file, file2;


	public void createWindow(String texto) {
		JFrame frame = new JFrame("Swing Tester");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createUI(frame, texto);
		frame.setSize(560, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void createUI(final JFrame frame, String texto) {
		JPanel panel = new JPanel();
		LayoutManager layout = new FlowLayout();
		panel.setLayout(layout);

		JButton button = new JButton("Buscar diretório");
		final JLabel label = new JLabel();
		
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					file2 = new File(file.getAbsolutePath() + "/nota fiscal.txt");
					FileWriter arq;
					try {
						if(file2.exists()) {
							arq = new FileWriter(file2, true);							
						}else {
							arq = new FileWriter(file2);
						}
						BufferedWriter bw = new BufferedWriter(arq);
						bw.write(texto);
						bw.close();
						arq.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					label.setText("Folder Selected: " + file.getName());
					frame.setVisible(false);
				} else {
					label.setText("Open command canceled");
				}
			}
		});

		panel.add(button);
		panel.add(label);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
}