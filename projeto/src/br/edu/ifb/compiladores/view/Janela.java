package br.edu.ifb.compiladores.view;

import br.edu.ifb.compiladores.lexico.Lexico;
import br.edu.ifb.compiladores.sintatico.Sintatico;
import br.edu.ifb.compiladores.sintatico.Sym;
import java_cup.runtime.Symbol;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Scanner;

public class Janela {

    private JFrame frmCompilador;
    private JTable tToken;
    private JTextField tfPath;
    private static JTextArea taLexico, taSintatico, taCode;

    private DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"L", "C", "Token", "Valor"}
    ){
        Class[] columnTypes = new Class[]{Integer.class, Integer.class, String.class, String.class};
        public Class getColumnClass(int columnIndex){
            return columnTypes[columnIndex];
        }
    };

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Janela window = new Janela();
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
        frmCompilador.setLocationRelativeTo(null);
        frmCompilador.setVisible(true);

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
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(pLogSintatico, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(pCode, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pTabela, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(pTabela, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addComponent(pCode, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(pLogLexico, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pLogSintatico, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        pLogSintatico.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        pLogSintatico.add(scrollPane_1, BorderLayout.CENTER);

        taSintatico = new JTextArea();
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

        taLexico = new JTextArea();
        taLexico.setBackground(Color.BLACK);
        taLexico.setForeground(Color.WHITE);
        scrollPane.setViewportView(taLexico);
        pCode.setLayout(new BorderLayout(0, 0));

        JScrollPane spCode = new JScrollPane();
        pCode.add(spCode);

        taCode = new JTextArea();
        taCode.setTabSize(2);
        taCode.setLineWrap(true);//quebra de linhas
        spCode.setViewportView(taCode);
        pTabela.setLayout(new BorderLayout(0, 0));

        JScrollPane spTabela = new JScrollPane();
        spTabela.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pTabela.add(spTabela, BorderLayout.CENTER);

        tToken = new JTable();

        tToken.setModel(tableModel);
//        tToken.getColumnModel().getColumn(0).setPreferredWidth(10);
        tToken.getColumnModel().getColumn(0).setMaxWidth(30);
//        tToken.getColumnModel().getColumn(1).setPreferredWidth(10);
        tToken.getColumnModel().getColumn(1).setMaxWidth(30);
        tToken.getColumnModel().getColumn(2).setPreferredWidth(100);

        spTabela.setViewportView(tToken);
        frmCompilador.getContentPane().setLayout(groupLayout);


        btCarregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                carrega();
            }
        });

        btTestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executa();
            }
        });

        // TODO: REMOVER ESSAS CHAMACAS DE FUNÇÃO
//        carrega();
//        executa();
    }

    private void carrega(){
        try {
            clearAreas();
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
            e.printStackTrace();
        }
    }

    private void executa(){
        try {
            tableModel.setRowCount(0);
            Lexico lexico = new Lexico(new StringReader(taCode.getText()));

            Symbol token;
            while(true) {
                token = lexico.next_token();
                tableModel.addRow(new Object[]{token.left, token.right, Sym.terminalNames[token.sym], token.value});
                if(token.sym == Sym.EOF)
                    break;
            }

            lexico = new Lexico(new StringReader(taCode.getText()));
            Sintatico sintatico = new Sintatico(lexico);
            Object result = sintatico.parse().value;

            System.out.println("Compilacao concluida com sucesso...");
            Janela.addTextTASintatico("Compilacao concluida com sucesso...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearAreas(){
        tableModel.setRowCount(0);
        this.taLexico.setText(null);
        this.taSintatico.setText(null);
        this.taCode.setText(null);
    }

    public static void addTextTALexico(String texto){
        if(taLexico.getText().equals("")) {
//            taLexico.setText("L\tC\tTipo\n" +"------------------------+--------------------+----------------");
            taLexico.setText(texto);
        }
        taLexico.setText(taLexico.getText() + '\n' + texto);
    }

    public static void addTextTASintatico(String texto){
        if(taSintatico.getText().equals(""))
            taSintatico.setText(texto);
        else
            taSintatico.setText(taSintatico.getText() + '\n' + texto);
    }

}
