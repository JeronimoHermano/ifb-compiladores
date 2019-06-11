package pacote;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


public class Janela {

	private JFrame frmCompilador;
	private JTable tToken;
	private JTextField tfPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela window = new Janela();
					window.frmCompilador.setLocationRelativeTo(null);
					window.frmCompilador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCompilador = new JFrame();
		frmCompilador.setTitle("Compilador");
		frmCompilador.setBounds(100, 100, 800, 600);
		frmCompilador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JPanel pLogLexico = new JPanel();
		pLogLexico.setBackground(Color.BLACK);
		pLogLexico.setForeground(Color.WHITE);
		
		JPanel pLogSintatico = new JPanel();
		pLogSintatico.setForeground(Color.WHITE);
		pLogSintatico.setBackground(Color.BLACK);
		
		JPanel pCode = new JPanel();
		
		JPanel pTabela = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmCompilador.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pLogLexico, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pLogSintatico, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pCode, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pTabela, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pTabela, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
						.addComponent(pCode, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pLogLexico, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addComponent(pLogSintatico, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		pLogSintatico.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		pLogSintatico.add(scrollPane_1, BorderLayout.CENTER);
		
		JTextArea taSintatico = new JTextArea();
		taSintatico.setForeground(Color.WHITE);
		taSintatico.setBackground(Color.BLACK);
		scrollPane_1.setViewportView(taSintatico);
		panel.setLayout(new BorderLayout(0, 0));
		
		tfPath = new JTextField("/media/jeronimo/DADOS/Documentos/Git/ifb-compiladores/projeto/src/br/edu/ifb/compiladores/exemplo1.txt");
		panel.add(tfPath, BorderLayout.CENTER);
		tfPath.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JButton btCarregar = new JButton("Carregar");
		panel_1.add(btCarregar);
		
		JButton btTestar = new JButton("Testar");
		panel_1.add(btTestar);
		pLogLexico.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pLogLexico.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea taLexico = new JTextArea();
		taLexico.setBackground(Color.BLACK);
		taLexico.setForeground(Color.WHITE);
		scrollPane.setViewportView(taLexico);
		pCode.setLayout(new BorderLayout(0, 0));
		
		JScrollPane spCode = new JScrollPane();
		pCode.add(spCode);
		
		JTextArea taCode = new JTextArea();
		taCode.setTabSize(2);
		spCode.setViewportView(taCode);
		pTabela.setLayout(new BorderLayout(0, 0));
		
		JScrollPane spTabela = new JScrollPane();
		pTabela.add(spTabela, BorderLayout.CENTER);
		
		tToken = new JTable();
		spTabela.setViewportView(tToken);
		frmCompilador.getContentPane().setLayout(groupLayout);
		

		btCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Scanner in = new Scanner(new FileReader(tfPath.getText()));
					StringBuilder sb = new StringBuilder();
					while(true) {
						sb.append(in.nextLine());
						if(in.hasNext())
							sb.append('\n');
						else 
							break;
					}
					in.close();
					taCode.setText(sb.toString());
					in.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
