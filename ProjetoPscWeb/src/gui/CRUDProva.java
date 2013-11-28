/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import classes.Alternativa;
import classes.AreaConcurso;
import classes.CartaoResposta;
import classes.Concursando;
import classes.Concurso;
import classes.DiaFase;
import classes.Fase;
import classes.Genero;
import classes.Prova;
import classes.Questao;
import classes.QuestaoDiscursiva;
import classes.QuestaoMultiplaEscolha;
import com.itextpdf.text.Chunk;

import fachada.Fachada;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import fachada.IFachada;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author RAFAELSANTIAGO
 */
public class CRUDProva extends javax.swing.JFrame {

    /**
     * Creates new form CRUDProva
     */
    IFachada fachada = Fachada.obterInstancia();
    ArrayList<QuestaoMultiplaEscolha> questoesMultiplaEscolha;
    ArrayList<QuestaoDiscursiva> questoesDiscursiva;
    Prova prova = new Prova();
    List<Questao> questoesEscolhidas = new ArrayList<Questao>();
    ArrayList<Genero> generos = new ArrayList<Genero>();
    long[] codGeneros;
    long[] codConcurso;
    long[] codFase;
    long[] codDiaFase;
    long[] codAreaConcurso;

    public CRUDProva() {
        try {
            initComponents();
            questoesMultiplaEscolha = (ArrayList<QuestaoMultiplaEscolha>) fachada.consultarTodosQuestaoMultiplaEscolha();
            questoesDiscursiva = (ArrayList<QuestaoDiscursiva>) fachada.consultarTodosQuestaoDiscursiva();
            this.carregarListaQuestoesMultiplaEscolha();
            this.carregarListaQuestoesDiscursiva();

            generos = (ArrayList<Genero>) fachada.consultarTodosGenero();
            codGeneros = new long[generos.size()];
            int i = 0;
            for (Genero genero : generos) {
                ComboGeneroProva.addItem(genero.getGenero());
                codGeneros[i] = genero.getId();
                i++;
            }
            ArrayList<Concurso> concursos = new ArrayList<Concurso>();
            concursos = (ArrayList<Concurso>) fachada.consultarTodosConcurso();
            codConcurso = new long[concursos.size()];
            i = 0;
            DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
            for (Concurso concurso : concursos) {
                boxModel.addElement(concurso.getNomeConcurso().toString());
                codConcurso[i] = concurso.getId();
                i++;
            }
            ComboConcursoProva.setModel(boxModel);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    public void carregarListaQuestoesMultiplaEscolha() {
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new String[]{"Questão", "Referência", "Gênero"});
            for (QuestaoMultiplaEscolha questMult : questoesMultiplaEscolha) {
                modelo.addRow(new Object[]{questMult.getTexto(), questMult.getReferencia(), questMult.getGenero().getGenero()});
            }
            TableQuestoesMultiplaEscolhaProva.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    public void carregarListaQuestoesDiscursiva() {
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new String[]{"Questão", "Referência", "Gênero"});
            for (QuestaoDiscursiva questDisc : questoesDiscursiva) {
                modelo.addRow(new Object[]{questDisc.getTexto(), questDisc.getReferencia(), questDisc.getGenero().getGenero()});
            }
            TableQuestoesDiscursivaProva.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    public void carregarListaQuestoesEscolhidas() {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new String[]{"Questão", "Referência", "Gênero"});
            for (Questao questao : questoesEscolhidas) {
                modelo.addRow(new Object[]{questao.getTexto(), questao.getReferencia(), questao.getGenero().getGenero()});
            }
            TableQuestoesEscolhidasprova.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void gerarProva() {
        try {
            String letras = "abcdefghijklmnopqrstuvxz";
            DiaFase diaFase = new DiaFase();
            diaFase = fachada.consultarDiaFasePorId(codDiaFase[ComboDiaFaseProva.getSelectedIndex()]);
            prova.setGenero(generos.get(ComboGeneroProva.getSelectedIndex()));
            prova.setDiaFase(diaFase);
            Document provaPDF = new Document(PageSize.A4, 72, 72, 72, 72);
            Font font = FontFactory.getFont("Arial", 10);
            OutputStream os = new FileOutputStream("ProvaConcurso.pdf");
            PdfWriter.getInstance(provaPDF, os);
            provaPDF.addAuthor("Empresa Elaboradora de Concursos");
            provaPDF.open();
            Image image = Image.getInstance("Unibratec.png");
            image.setAbsolutePosition(10f, 780f);
            provaPDF.add(image);
            provaPDF.add(new Paragraph("Nome do Concursando:_____________________________________________________________", font));
            SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
            provaPDF.add(new Paragraph("Matéria sendo Avaliada: " + fachada.consultarGeneroPorId(prova.getGenero().getId()).getGenero() + "                                      Data: " + formatoData.format(diaFase.getDataDia().getTime()).toString(), font));
            Paragraph nomeConcurso = new Paragraph(new Paragraph("Concurso " + diaFase.getFase().getAreaconcurso().getConcurso().getNomeConcurso(), font));
            nomeConcurso.setAlignment(Element.ALIGN_CENTER);
            provaPDF.add(new Paragraph("Área do Concurso: " + diaFase.getFase().getAreaconcurso().getNome(), font));
            provaPDF.add(new Paragraph("Início da Prova: " + formatoHora.format(diaFase.getHoraInicial().getTime()).toString() + "     Término da Prova: " + formatoHora.format(diaFase.getHoraFinal().getTime()).toString(), font));
            provaPDF.add(Chunk.NEWLINE);
            provaPDF.add(nomeConcurso);
            provaPDF.add(Chunk.NEWLINE);
            provaPDF.add(Chunk.NEWLINE);
            int numQuestao = 1;
                for (Questao questao : prova.getQuestoes()) {
                    String referencia = "";
                    if (questao.getReferencia() != null &&  !questao.getReferencia().trim().equals("") ) {
                        referencia = "(" + questao.getReferencia() + ")";
                    }
                    provaPDF.add(new Paragraph(4, numQuestao++ + "ª) " + referencia + questao.getTexto(), font));
                    if (questao instanceof QuestaoMultiplaEscolha) {
                        int i = 0;
                        QuestaoMultiplaEscolha questaoMultiplaEscolha = (QuestaoMultiplaEscolha) questao;
                        for (Alternativa alternativa : questaoMultiplaEscolha.getAlternativas()) {
                            provaPDF.add(new Paragraph(letras.charAt(i) + ") " + alternativa.getTexto(), font));
                            i++;
                        }
                    } else if (questao instanceof QuestaoDiscursiva) {
                        provaPDF.add(Chunk.NEWLINE);
                        QuestaoDiscursiva questaoDiscursiva = (QuestaoDiscursiva) questao;
                        for (int i = 0; i < questaoDiscursiva.getLinhas(); i++) {
                            provaPDF.add(new Paragraph("_______________________________________________________________________________", font));
                        }
                    }

                    provaPDF.add(Chunk.NEWLINE);
                    provaPDF.add(Chunk.NEWLINE);
                }
            
            provaPDF.close();
            /*for (CartaoResposta cr : prova.getCartoesResposta()) {
                cr.setProva(prova);
                /*Concursando concursando = new Concursando();
                concursando.setId(2);
                cr.setConcursando(concursando);*/
            //}            
            //prova.getCartoesResposta().get(0).getConcursando().setId(2);
            
            
            //fachada.inserirCartaoResposta(prova.getCartoesResposta().get(0));
            fachada.inserirProva(prova);
            if (JOptionPane.showConfirmDialog(rootPane, "Prova Gerada com Sucesso. \n Gostaria de visualiza-la?", "Visualizar Prova", JOptionPane.YES_NO_OPTION) == 0) {
                ProvaPDFView ppdfv = new ProvaPDFView();
                ppdfv.setVisible(true);
            } else {
                this.dispose();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ComboGeneroProva = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableQuestoesDiscursivaProva = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableQuestoesEscolhidasprova = new javax.swing.JTable();
        ButtonCadastrarQuestoesProva = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        ComboConcursoProva = new javax.swing.JComboBox();
        ButtonLimparProva = new javax.swing.JButton();
        ButtonSalvarProva = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableQuestoesMultiplaEscolhaProva = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        ComboFaseProva = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        ComboDiaFaseProva = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        ComboAreaConcursoProva = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Provas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Genero");

        jLabel2.setText("Questoes Discursivas");

        TableQuestoesDiscursivaProva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TableQuestoesDiscursivaProva.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(TableQuestoesDiscursivaProva);

        jLabel3.setText("Questoes Escolhidas");

        TableQuestoesEscolhidasprova.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TableQuestoesEscolhidasprova.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(TableQuestoesEscolhidasprova);

        ButtonCadastrarQuestoesProva.setText("Cadastrar Questoes");
        ButtonCadastrarQuestoesProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCadastrarQuestoesProvaActionPerformed(evt);
            }
        });

        jButton3.setText("-->");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("<--");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setText("Concurso");

        ComboConcursoProva.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboConcursoProvaItemStateChanged(evt);
            }
        });
        ComboConcursoProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboConcursoProvaActionPerformed(evt);
            }
        });

        ButtonLimparProva.setText("Limpar");

        ButtonSalvarProva.setText("Salvar");
        ButtonSalvarProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSalvarProvaActionPerformed(evt);
            }
        });

        TableQuestoesMultiplaEscolhaProva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TableQuestoesMultiplaEscolhaProva.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(TableQuestoesMultiplaEscolhaProva);

        jButton1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jButton1.setText("Gerar Prova Automaticamente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ComboFaseProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboFaseProvaActionPerformed(evt);
            }
        });

        jLabel5.setText("Fase");

        jLabel6.setText("Dia da Fase");

        ComboAreaConcursoProva.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboAreaConcursoProvaItemStateChanged(evt);
            }
        });
        ComboAreaConcursoProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboAreaConcursoProvaActionPerformed(evt);
            }
        });

        jLabel7.setText("Area Concurso");

        jButton5.setText("-->");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setText("Questoes Multipla Escolha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ButtonCadastrarQuestoesProva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonLimparProva, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonSalvarProva, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ComboConcursoProva, 0, 163, Short.MAX_VALUE)
                                    .addComponent(ComboGeneroProva, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ComboFaseProva, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addComponent(jLabel6)
                                        .addGap(20, 20, 20)
                                        .addComponent(ComboDiaFaseProva, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 46, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ComboAreaConcursoProva, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4)
                                    .addComponent(jButton5)
                                    .addComponent(jButton3))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(268, 268, 268))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(ComboGeneroProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(ComboConcursoProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboAreaConcursoProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboFaseProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(ComboDiaFaseProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(7, 7, 7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addGap(44, 44, 44)
                                .addComponent(jButton4)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jButton3)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonLimparProva)
                    .addComponent(ButtonSalvarProva)
                    .addComponent(ButtonCadastrarQuestoesProva))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            prova = new Prova();
            Genero genero = new Genero();
            genero.setId(codGeneros[ComboGeneroProva.getSelectedIndex()]);

            int questMult = Integer.parseInt(JOptionPane.showInputDialog("Informe o número de questões de multipla escolha: "));
            int questDisc = Integer.parseInt(JOptionPane.showInputDialog("Informe o número de questões discussivas escolha: "));
            ArrayList<QuestaoDiscursiva> questoesDiscursivas = new ArrayList<QuestaoDiscursiva>();
            ArrayList<QuestaoMultiplaEscolha> questoesMultiplaEscolhas = new ArrayList<QuestaoMultiplaEscolha>();
            questoesDiscursivas = (ArrayList<QuestaoDiscursiva>) fachada.consultarTodosPorGeneroQuestaoDiscursiva(genero);
            questoesMultiplaEscolhas = (ArrayList<QuestaoMultiplaEscolha>) fachada.consultarTodosPorGeneroQuestaoMultiplaEscolha(genero);
            for (int i = 0; i < questDisc; i++) {
                QuestaoDiscursiva questaoDiscursiva = new QuestaoDiscursiva();
                questaoDiscursiva = questoesDiscursivas.get(new Random().nextInt(questoesDiscursivas.size()));
                questoesDiscursivas.remove(questaoDiscursiva);
                prova.getQuestoes().add(questaoDiscursiva);
            }

            for (int i = 0; i < questMult; i++) {
                QuestaoMultiplaEscolha questaoMultiplaEscolha = new QuestaoMultiplaEscolha();
                questaoMultiplaEscolha = questoesMultiplaEscolhas.get(new Random().nextInt(questoesMultiplaEscolhas.size()));
                questoesMultiplaEscolhas.remove(questaoMultiplaEscolha);
                prova.getQuestoes().add(questaoMultiplaEscolha);
            }
            this.gerarProva();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void ComboConcursoProvaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboConcursoProvaItemStateChanged
    }//GEN-LAST:event_ComboConcursoProvaItemStateChanged

    private void ComboConcursoProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboConcursoProvaActionPerformed
        // TODO add your handling code here:

        try {
            // TODO add your handling code here:
            Concurso concurso = new Concurso();
            concurso = fachada.consultarConcursoPorId(codConcurso[ComboConcursoProva.getSelectedIndex()]);
            codAreaConcurso = new long[concurso.getAreasConcurso().size()];
            int i = 0;
            DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
            for (AreaConcurso areaConcurso : concurso.getAreasConcurso()) {
                boxModel.addElement(areaConcurso.getNome());
                codAreaConcurso[i] = areaConcurso.getId();
                i++;
            }
            ComboAreaConcursoProva.setModel(boxModel);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_ComboConcursoProvaActionPerformed

    private void ComboAreaConcursoProvaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboAreaConcursoProvaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboAreaConcursoProvaItemStateChanged

    private void ComboAreaConcursoProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboAreaConcursoProvaActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            AreaConcurso areaConcurso = new AreaConcurso();
            areaConcurso = fachada.consultarAreaConcursoPorId(codAreaConcurso[ComboAreaConcursoProva.getSelectedIndex()]);
            codFase = new long[areaConcurso.getFases().size()];
            int i = 0;
            int x = 1;
            DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
            for (Fase fase : areaConcurso.getFases()) {
                boxModel.addElement(x + "ª Fase");
                codFase[i] = fase.getId();
                i++;
                x++;
            }
            ComboFaseProva.setModel(boxModel);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_ComboAreaConcursoProvaActionPerformed

    private void ComboFaseProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboFaseProvaActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Fase fase = new Fase();
            fase = fachada.consultarFasePorId(codFase[ComboFaseProva.getSelectedIndex()]);
            codDiaFase = new long[fase.getDiasFase().size()];
            int i = 0;
            DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
            for (DiaFase diafase : fase.getDiasFase()) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                boxModel.addElement(format.format(diafase.getDataDia().getTime()));
                codDiaFase[i] = diafase.getId();
                i++;
            }
            ComboDiaFaseProva.setModel(boxModel);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_ComboFaseProvaActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        questoesEscolhidas.add(questoesMultiplaEscolha.get(TableQuestoesMultiplaEscolhaProva.getSelectedRow()));
        questoesMultiplaEscolha.remove(TableQuestoesMultiplaEscolhaProva.getSelectedRow());
        this.carregarListaQuestoesEscolhidas();
        this.carregarListaQuestoesMultiplaEscolha();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        questoesEscolhidas.add(questoesDiscursiva.get(TableQuestoesDiscursivaProva.getSelectedRow()));
        questoesDiscursiva.remove(TableQuestoesDiscursivaProva.getSelectedRow());
        this.carregarListaQuestoesEscolhidas();
        this.carregarListaQuestoesDiscursiva();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Questao questao = new Questao();
        questao = questoesEscolhidas.get(TableQuestoesEscolhidasprova.getSelectedRow());
        if (questao instanceof QuestaoDiscursiva) {
            questoesDiscursiva.add((QuestaoDiscursiva) questao);
        } else {
            questoesMultiplaEscolha.add((QuestaoMultiplaEscolha) questao);
        }
        questoesEscolhidas.remove(questao);
        this.carregarListaQuestoesEscolhidas();
        this.carregarListaQuestoesDiscursiva();
        this.carregarListaQuestoesMultiplaEscolha();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ButtonSalvarProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalvarProvaActionPerformed
        // TODO add your handling code here:
        prova.setQuestoes(questoesEscolhidas);
        this.gerarProva();
    }//GEN-LAST:event_ButtonSalvarProvaActionPerformed

    private void ButtonCadastrarQuestoesProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCadastrarQuestoesProvaActionPerformed
        // TODO add your handling code here:
        try{
        CRUDQuestao crudq;
        crudq = new CRUDQuestao(null);
        crudq.setVisible(true);
        }catch(Exception e){
        JOptionPane.showMessageDialog(rootPane,e.getMessage());
        }
    }//GEN-LAST:event_ButtonCadastrarQuestoesProvaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try{
         List<Concurso> concursos = new ArrayList<Concurso>();
            concursos = fachada.consultarTodosConcurso();
            if (concursos.size() <= 0) {
                JOptionPane.showMessageDialog(rootPane, "Não há Concursos cadastrados, cadastre um concurso para prosseguir");
                this.dispose();
    }//GEN-LAST:event_formWindowOpened
        }catch(Exception ex){
             Logger.getLogger(CRUDProva.class.getName()).log(Level.SEVERE, null, ex);
            
        }}
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
            java.util.logging.Logger.getLogger(CRUDProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CRUDProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CRUDProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CRUDProva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CRUDProva().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCadastrarQuestoesProva;
    private javax.swing.JButton ButtonLimparProva;
    private javax.swing.JButton ButtonSalvarProva;
    private javax.swing.JComboBox ComboAreaConcursoProva;
    private javax.swing.JComboBox ComboConcursoProva;
    private javax.swing.JComboBox ComboDiaFaseProva;
    private javax.swing.JComboBox ComboFaseProva;
    private javax.swing.JComboBox ComboGeneroProva;
    private javax.swing.JTable TableQuestoesDiscursivaProva;
    private javax.swing.JTable TableQuestoesEscolhidasprova;
    private javax.swing.JTable TableQuestoesMultiplaEscolhaProva;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
