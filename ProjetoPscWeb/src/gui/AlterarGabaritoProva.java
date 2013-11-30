/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import classes.Alternativa;
import classes.CartaoResposta;
import classes.Fase;
import classes.Gabarito;
import classes.Prova;
import classes.Questao;
import classes.QuestaoDiscursiva;
import classes.QuestaoMultiplaEscolha;
import classes.RespostasProva;
import fachada.Fachada;
import fachada.IFachada;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Antônio
 */
public class AlterarGabaritoProva extends javax.swing.JFrame {

    /**
     * Creates new form AlterarGabarito
     */
    Prova prova;
    List<QuestaoMultiplaEscolha> questoesMult;
    List<QuestaoDiscursiva> questoesDisc;
    RespostasProva respostasProva;
    IFachada fachada = Fachada.obterInstancia();

    public AlterarGabaritoProva(Prova prova, Gabarito gabarito) {
        initComponents();
        this.prova = prova;
        this.respostasProva = gabarito;
        questoesMult = new ArrayList<QuestaoMultiplaEscolha>();
        questoesDisc = new ArrayList<QuestaoDiscursiva>();
        gabarito.setProva(prova);
        for (Questao questao : prova.getQuestoes()) {
            if (questao instanceof QuestaoMultiplaEscolha) {
                questoesMult.add((QuestaoMultiplaEscolha) questao);
            } else {
                questoesDisc.add((QuestaoDiscursiva) questao);
            }
        }
        LabelEmpresa.setText("Empresa: " + prova.getDiaFase().getFase().getAreaconcurso().getConcurso().getEmpresa().getNome());
        LabelConcurso.setText("Concurso: " + prova.getDiaFase().getFase().getAreaconcurso().getConcurso().getNomeConcurso());
        LabelAreaConcurso.setText("Area do Concurso: " + prova.getDiaFase().getFase().getAreaconcurso().getNome());
        int numFase = 0;
        for (Fase fase : prova.getDiaFase().getFase().getAreaconcurso().getFases()) {
            numFase++;
            if (fase.getId() == prova.getDiaFase().getFase().getId()) {
                break;
            }
        }
        LabelFase.setText(numFase + "ª Fase");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        LabelDiaFase.setText("Dia :" + format.format(prova.getDiaFase().getDataDia().getTime()));
        this.carregarQuestoesMultipla();
        this.carregarQuestoesDiscursivas();
    }

    public AlterarGabaritoProva(Prova prova, CartaoResposta cartaoResposta) {
        try {
            initComponents();
            this.prova = fachada.consultarProvaPorId(prova.getId());           
            this.respostasProva = fachada.consultarCartaoRespostaPorId(cartaoResposta.getId());
            questoesMult = new ArrayList<QuestaoMultiplaEscolha>();
            questoesDisc = new ArrayList<QuestaoDiscursiva>();
            respostasProva.setProva(prova);
            for (Questao questao : prova.getQuestoes()) {
                if (questao instanceof QuestaoMultiplaEscolha) {
                    questoesMult.add((QuestaoMultiplaEscolha) questao);
                } else {
                    questoesDisc.add((QuestaoDiscursiva) questao);
                }
            }
            LabelEmpresa.setText("Empresa: " + prova.getDiaFase().getFase().getAreaconcurso().getConcurso().getEmpresa().getNome());
            LabelConcurso.setText("Concurso: " + prova.getDiaFase().getFase().getAreaconcurso().getConcurso().getNomeConcurso());
            LabelAreaConcurso.setText("Area do Concurso: " + prova.getDiaFase().getFase().getAreaconcurso().getNome());
            int numFase = 0;
            for (Fase fase : prova.getDiaFase().getFase().getAreaconcurso().getFases()) {
                numFase++;
                if (fase.getId() == prova.getDiaFase().getFase().getId()) {
                    break;
                }
            }
            LabelFase.setText(numFase + "ª Fase");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            LabelDiaFase.setText("Dia :" + format.format(prova.getDiaFase().getDataDia().getTime()));
            this.carregarQuestoesMultipla();
            this.carregarQuestoesDiscursivas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(LabelEmpresa, ex.getMessage());
        }
    }

    private AlterarGabaritoProva() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void carregarQuestoesMultipla() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new String[]{"Questão", "Referência"});
            for (QuestaoMultiplaEscolha questMult : questoesMult) {
                modelo.addRow(new Object[]{questMult.getTexto(), questMult.getReferencia()});
            }
            TableQuestoesMultipla.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    public void carregarQuestoesDiscursivas() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new String[]{"Questão", "Referência"});
            for (QuestaoDiscursiva questDisc : questoesDisc) {
                modelo.addRow(new Object[]{questDisc.getTexto(), questDisc.getReferencia()});
            }
            TableQuestoesDiscursivas.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    public void carregarAlternativas(QuestaoMultiplaEscolha questao) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new String[]{"Alternativa", ""});
            for (Alternativa alternativa : questao.getAlternativas()) {
                String x = "";
                for (Alternativa a : respostasProva.getAlternativas()) {
                    if (a.getId() == alternativa.getId()) {
                        x = "X";
                    }
                }
                modelo.addRow(new Object[]{alternativa.getTexto(), x});
            }
            TableAlternativas.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TableQuestoesMultipla = new javax.swing.JTable();
        ButtonAlternativaCorreta = new javax.swing.JButton();
        LabelEmpresa = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableAlternativas = new javax.swing.JTable();
        LabelDiaFase = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableQuestoesDiscursivas = new javax.swing.JTable();
        LabelFase = new javax.swing.JLabel();
        LabelConcurso = new javax.swing.JLabel();
        LabelAreaConcurso = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TextAreaResposta = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TableQuestoesMultipla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TableQuestoesMultipla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableQuestoesMultiplaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableQuestoesMultipla);

        ButtonAlternativaCorreta.setText("Marcar Como Correta");
        ButtonAlternativaCorreta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAlternativaCorretaActionPerformed(evt);
            }
        });

        LabelEmpresa.setText("Empresa");

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");

        TableAlternativas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(TableAlternativas);

        LabelDiaFase.setText("Dia da Fase");

        TableQuestoesDiscursivas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TableQuestoesDiscursivas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableQuestoesDiscursivasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableQuestoesDiscursivas);

        LabelFase.setText("Fase");

        LabelConcurso.setText("Concurso");

        LabelAreaConcurso.setText("Area do Concurso");

        TextAreaResposta.setEditable(false);
        TextAreaResposta.setColumns(20);
        TextAreaResposta.setLineWrap(true);
        TextAreaResposta.setRows(5);
        TextAreaResposta.setWrapStyleWord(true);
        TextAreaResposta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextAreaRespostaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TextAreaResposta);

        jMenu1.setText("Menu");

        jMenu3.setMnemonic('p');
        jMenu3.setText("Prova");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Cadastro");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem5.setText("Lista");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenu1.add(jMenu3);

        jMenu4.setMnemonic('C');
        jMenu4.setText("Concurso");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Cadastro");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem7.setText("Lista");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenu1.add(jMenu4);

        jMenu14.setMnemonic('Q');
        jMenu14.setText("Questão");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Cadastro");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem9);

        jMenuItem24.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem24.setText("Lista");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem24);

        jMenu1.add(jMenu14);

        jMenu6.setMnemonic('G');
        jMenu6.setText("Gênero");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Cadastro");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem11.setText("Lista");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenu1.add(jMenu6);
        jMenu1.add(jSeparator3);

        jMenu7.setMnemonic('L');
        jMenu7.setText("Local");

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setText("Cadastro");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem13.setText("Lista");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenu1.add(jMenu7);

        jMenu10.setMnemonic('E');
        jMenu10.setText("Empresa");

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem14.setText("Cadastro");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem14);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem15.setText("Lista");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem15);

        jMenu1.add(jMenu10);
        jMenu1.add(jSeparator2);

        jMenu8.setMnemonic('b');
        jMenu8.setText("Elaborador");

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setText("Cadastro");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem16);

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem17.setText("Lista");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem17);

        jMenu1.add(jMenu8);

        jMenu9.setMnemonic('F');
        jMenu9.setText("Funcionário");

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setText("Cadastro");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem18);

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem19.setText("Lista");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem19);

        jMenu1.add(jMenu9);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opcoes");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Home");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Sair");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Ajuda");

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        jMenuItem20.setText("Sobre a Empresa");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem20);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonAlternativaCorreta)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jButton1)
                        .addGap(101, 101, 101)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LabelFase)
                            .addComponent(LabelEmpresa))
                        .addGap(25, 25, 25)
                        .addComponent(LabelConcurso)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelAreaConcurso)
                            .addComponent(LabelDiaFase))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEmpresa)
                    .addComponent(LabelConcurso)
                    .addComponent(LabelAreaConcurso))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelFase)
                    .addComponent(LabelDiaFase))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ButtonAlternativaCorreta)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableQuestoesMultiplaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableQuestoesMultiplaMouseClicked
        // TODO add your handling code here:
        if (TableQuestoesMultipla.getSelectedRow() != -1) {
            this.carregarAlternativas(questoesMult.get(TableQuestoesMultipla.getSelectedRow()));
        }
    }//GEN-LAST:event_TableQuestoesMultiplaMouseClicked

    private void ButtonAlternativaCorretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAlternativaCorretaActionPerformed
        // TODO add your handling code here:
        if (TableQuestoesMultipla.getSelectedRow() != -1) {
            if (TableAlternativas.getSelectedRow() != -1) {
                for (Alternativa alternativa : respostasProva.getAlternativas()) {
                    if (alternativa.getQuestao().getId() == questoesMult.get(TableQuestoesMultipla.getSelectedRow()).getAlternativas().get(TableAlternativas.getSelectedRow()).getQuestao().getId()) {
                        respostasProva.getAlternativas().remove(alternativa);
                        break;
                    }

                }
                respostasProva.getAlternativas().add(questoesMult.get(TableQuestoesMultipla.getSelectedRow()).getAlternativas().get(TableAlternativas.getSelectedRow()));
            }
            this.carregarAlternativas(questoesMult.get(TableQuestoesMultipla.getSelectedRow()));
        }
    }//GEN-LAST:event_ButtonAlternativaCorretaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            int qntdQuestoes = 0;
            for (QuestaoMultiplaEscolha quest : questoesMult) {
                for (Alternativa alternativaGabarito : respostasProva.getAlternativas()) {
                    if (alternativaGabarito.getQuestao().getId() == quest.getId()) {
                        qntdQuestoes++;
                    }
                }
            }
            if (qntdQuestoes < questoesMult.size()) {
                JOptionPane.showMessageDialog(rootPane, "Existe uma ou mais questões que não foram respondidas.");
            } else {
                if (respostasProva instanceof Gabarito) {
                    fachada.atualizarGabarito((Gabarito) respostasProva);
                } else {
                    fachada.atualizarCartaoResposta((CartaoResposta) respostasProva);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TextAreaRespostaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextAreaRespostaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TextAreaRespostaMouseClicked

    private void TableQuestoesDiscursivasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableQuestoesDiscursivasMouseClicked
        // TODO add your handling code here:
        if (TableQuestoesDiscursivas.getSelectedRow() != -1) {
            TextAreaResposta.setText(questoesDisc.get(TableQuestoesDiscursivas.getSelectedRow()).getResposta());
        }
    }//GEN-LAST:event_TableQuestoesDiscursivasMouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        CRUDProva crudP = new CRUDProva();
        crudP.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        ListarProva ltsP = new ListarProva();
        ltsP.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        CRUDConcurso crudC = new CRUDConcurso(null);
        crudC.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        ListarConcurso lstC = new ListarConcurso();
        lstC.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        CRUDQuestao crudQ = new CRUDQuestao(null);
        crudQ.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        ListarQuestao listarq = new ListarQuestao();
        listarq.setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        CRUDGenero crudG = new CRUDGenero(null);
        crudG.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        ListarGenero lstG = new ListarGenero();
        lstG.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        CRUDLocal crudL = new CRUDLocal(null);
        crudL.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        ListarLocal lst = new ListarLocal();
        lst.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        CRUDEmpresa crudE = new CRUDEmpresa(null);
        crudE.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        ListarEmpresa lstE = new ListarEmpresa();
        lstE.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        CRUDElaborador el = new CRUDElaborador(null);
        el.setVisible(true);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        ListarElaborador lstEl = new ListarElaborador();
        lstEl.setVisible(true);

    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        CRUDFuncionario crudF = new CRUDFuncionario(null);
        crudF.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        ListarFuncionario lstF = new ListarFuncionario();
        lstF.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        TelaInicial tl = new TelaInicial();
        tl.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane, "'EOC' Empresa Organizadora de Concurso\n dispõe de diversas ferramentas de gerenciamento\n"
                + "Para adequar-se ao uso da ferramenta oferecemos o treinamento necessário\n.Dúvidas ligue para fone:Telefone de Antônio ");
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AlterarGabaritoProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarGabaritoProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarGabaritoProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarGabaritoProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlterarGabaritoProva().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAlternativaCorreta;
    private javax.swing.JLabel LabelAreaConcurso;
    private javax.swing.JLabel LabelConcurso;
    private javax.swing.JLabel LabelDiaFase;
    private javax.swing.JLabel LabelEmpresa;
    private javax.swing.JLabel LabelFase;
    private javax.swing.JTable TableAlternativas;
    private javax.swing.JTable TableQuestoesDiscursivas;
    private javax.swing.JTable TableQuestoesMultipla;
    private javax.swing.JTextArea TextAreaResposta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
