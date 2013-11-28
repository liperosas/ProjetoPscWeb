/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import classes.Alternativa;
import classes.QuestaoMultiplaEscolha;
import fachada.Fachada;
import fachada.IFachada;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAFAELSANTIAGO
 */
public class AlterarQuestaoMultiplaEscolha extends javax.swing.JFrame {
     ListarQuestao lquestao;
    QuestaoMultiplaEscolha questaoMultiplaEscolha;
    IFachada fachada = Fachada.obterInstancia();
    long codigo;

    /**
     * Creates new form AlterarQuestaoMultiplaEscolha
     */
    public AlterarQuestaoMultiplaEscolha(ListarQuestao lquestao,QuestaoMultiplaEscolha questaoMultiplaEscolha) {
        initComponents();
        this.lquestao=lquestao;
        this.questaoMultiplaEscolha = questaoMultiplaEscolha;
        this.carregarAlternativas();   
        codigo= questaoMultiplaEscolha.getId();
        textAreaTextoQuestao.setText(questaoMultiplaEscolha.getTexto());
        textElaboradorQuestao.setText(questaoMultiplaEscolha.getElaborador().getNome());
        textGeneroQuestao.setText(questaoMultiplaEscolha.getGenero().getGenero());
        textReferenciaQuestao.setText(questaoMultiplaEscolha.getReferencia());
    }

    private AlterarQuestaoMultiplaEscolha() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      public void carregarAlternativas() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new Object[]{"Texto"});
            for (Alternativa alternativa : questaoMultiplaEscolha.getAlternativas()) {
                modelo.addRow(new String[]{alternativa.getTexto()});
            }
            TableAlternativas.setModel(modelo);
            // TODO add your handling code here:
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

        ButtonRemoverQuestao = new javax.swing.JButton();
        ButtonCancelarQuestao = new javax.swing.JButton();
        textElaboradorQuestao = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableAlternativas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaTextoQuestao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        textReferenciaQuestao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textGeneroQuestao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ButtonRemoverQuestao.setText("Alterar");
        ButtonRemoverQuestao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoverQuestaoActionPerformed(evt);
            }
        });

        ButtonCancelarQuestao.setText("Cancelar");
        ButtonCancelarQuestao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelarQuestaoActionPerformed(evt);
            }
        });

        textElaboradorQuestao.setEnabled(false);

        TableAlternativas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableAlternativas.setEnabled(false);
        jScrollPane2.setViewportView(TableAlternativas);

        jLabel3.setText("Genero");

        jLabel1.setText("Texto Questao");

        textAreaTextoQuestao.setColumns(20);
        textAreaTextoQuestao.setRows(5);
        textAreaTextoQuestao.setWrapStyleWord(true);
        jScrollPane1.setViewportView(textAreaTextoQuestao);

        jLabel4.setText("Referência");

        jLabel2.setText("Elaborador");

        textGeneroQuestao.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(ButtonCancelarQuestao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonRemoverQuestao)
                        .addGap(96, 96, 96))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textGeneroQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(textElaboradorQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel3))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(textReferenciaQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textElaboradorQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(textGeneroQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textReferenciaQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonRemoverQuestao)
                    .addComponent(ButtonCancelarQuestao))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonRemoverQuestaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRemoverQuestaoActionPerformed
        try {
            // TODO add your handling code here:
            
            questaoMultiplaEscolha.setId(codigo);
            questaoMultiplaEscolha.setReferencia(textReferenciaQuestao.getText());
            questaoMultiplaEscolha.setTexto(textAreaTextoQuestao.getText());
                                                  
            if (JOptionPane.showConfirmDialog(rootPane, "Você reamente quer Atualizar esta questão?", "Atualizar Questão", JOptionPane.YES_NO_OPTION) == 0) {
                fachada.atualizarQuestaoMultiplaEscolha(questaoMultiplaEscolha);
                JOptionPane.showMessageDialog(rootPane, "Questão Atualiza com sucesso");
                lquestao.carregarListaQuestoesMultiplaEscolha();
               this.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_ButtonRemoverQuestaoActionPerformed

    private void ButtonCancelarQuestaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelarQuestaoActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_ButtonCancelarQuestaoActionPerformed
    
   
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
            java.util.logging.Logger.getLogger(AlterarQuestaoMultiplaEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarQuestaoMultiplaEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarQuestaoMultiplaEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarQuestaoMultiplaEscolha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlterarQuestaoMultiplaEscolha().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCancelarQuestao;
    private javax.swing.JButton ButtonRemoverQuestao;
    private javax.swing.JTable TableAlternativas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textAreaTextoQuestao;
    private javax.swing.JTextField textElaboradorQuestao;
    private javax.swing.JTextField textGeneroQuestao;
    private javax.swing.JTextField textReferenciaQuestao;
    // End of variables declaration//GEN-END:variables
}