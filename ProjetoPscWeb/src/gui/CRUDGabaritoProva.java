/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import classes.Alternativa;
import classes.CartaoResposta;
import classes.Concursando;
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
public class CRUDGabaritoProva extends javax.swing.JFrame {

    /**
     * Creates new form CadastrarGabaritoProva
     */
    Prova prova;
    List<QuestaoMultiplaEscolha> questoesMult;
    List<QuestaoDiscursiva> questoesDisc;
    RespostasProva respostasProva;
    IFachada fachada = Fachada.obterInstancia();

    public CRUDGabaritoProva(Prova prova) {
        initComponents();
        respostasProva = new Gabarito();
        this.prova = prova;
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
    }

    public CRUDGabaritoProva(Concursando concursando, Prova prova) {
        initComponents();
        respostasProva = new CartaoResposta();
        CartaoResposta c = new CartaoResposta();
        c.setConcursando(concursando);
        respostasProva = c;
        this.prova = prova;
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

    private CRUDGabaritoProva() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        LabelEmpresa = new javax.swing.JLabel();
        LabelConcurso = new javax.swing.JLabel();
        LabelAreaConcurso = new javax.swing.JLabel();
        LabelFase = new javax.swing.JLabel();
        LabelDiaFase = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableQuestoesDiscursivas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableAlternativas = new javax.swing.JTable();
        ButtonAlternativaCorreta = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TextAreaResposta = new javax.swing.JTextArea();

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

        LabelEmpresa.setText("Empresa");

        LabelConcurso.setText("Concurso");

        LabelAreaConcurso.setText("Area do Concurso");

        LabelFase.setText("Fase");

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
        TableQuestoesDiscursivas.setEnabled(false);
        TableQuestoesDiscursivas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableQuestoesDiscursivasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableQuestoesDiscursivas);

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

        ButtonAlternativaCorreta.setText("Marcar Como Correta");
        ButtonAlternativaCorreta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAlternativaCorretaActionPerformed(evt);
            }
        });

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        TextAreaResposta.setColumns(20);
        TextAreaResposta.setLineWrap(true);
        TextAreaResposta.setRows(5);
        TextAreaResposta.setWrapStyleWord(true);
        jScrollPane4.setViewportView(TextAreaResposta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4)))
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(483, Short.MAX_VALUE)
                .addComponent(ButtonAlternativaCorreta)
                .addGap(61, 61, 61))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonAlternativaCorreta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
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
                    fachada.inserirGabarito((Gabarito) respostasProva);
                } else {
                    fachada.inserirCartaoResposta((CartaoResposta) respostasProva);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TableQuestoesDiscursivasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableQuestoesDiscursivasMouseClicked
        // TODO add your handling code here:
        if (TableQuestoesDiscursivas.getSelectedRow() != -1) {
            TextAreaResposta.setText(questoesDisc.get(TableQuestoesDiscursivas.getSelectedRow()).getResposta());
        }
    }//GEN-LAST:event_TableQuestoesDiscursivasMouseClicked

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
            java.util.logging.Logger.getLogger(CRUDGabaritoProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CRUDGabaritoProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CRUDGabaritoProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CRUDGabaritoProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CRUDGabaritoProva().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
