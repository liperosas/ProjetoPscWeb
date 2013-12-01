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
import java.text.ParseException;
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
            generos = (ArrayList<Genero>) fachada.consultarTodosGenero();
            codGeneros = new long[generos.size()];
            int i = 0;
            for (Genero genero : generos) {
                ComboGeneroProva.addItem(genero.getGenero());
                codGeneros[i] = genero.getId();
                i++;
            }
            questoesMultiplaEscolha = (ArrayList<QuestaoMultiplaEscolha>) fachada.consultarTodosPorGeneroQuestaoMultiplaEscolha(generos.get(ComboGeneroProva.getSelectedIndex()));
            questoesDiscursiva = (ArrayList<QuestaoDiscursiva>) fachada.consultarTodosPorGeneroQuestaoDiscursiva(generos.get(ComboGeneroProva.getSelectedIndex()));
            this.carregarListaQuestoesMultiplaEscolha();
            this.carregarListaQuestoesDiscursiva();
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
            Concurso concurso = new Concurso();
            concurso = fachada.consultarConcursoPorId(codConcurso[ComboConcursoProva.getSelectedIndex()]);
            codAreaConcurso = new long[concurso.getAreasConcurso().size()];
            i = 0;
            boxModel = new DefaultComboBoxModel();
            for (AreaConcurso areaConcurso : concurso.getAreasConcurso()) {
                boxModel.addElement(areaConcurso.getNome());
                codAreaConcurso[i] = areaConcurso.getId();
                i++;
            }
            ComboAreaConcursoProva.setModel(boxModel);
            AreaConcurso areaConcurso = new AreaConcurso();
            areaConcurso = fachada.consultarAreaConcursoPorId(codAreaConcurso[ComboAreaConcursoProva.getSelectedIndex()]);
            codFase = new long[areaConcurso.getFases().size()];
            i = 0;
            int x = 1;
            boxModel = new DefaultComboBoxModel();
            for (Fase fase : areaConcurso.getFases()) {
                boxModel.addElement(x + "ª Fase");
                codFase[i] = fase.getId();
                i++;
                x++;
            }
            ComboFaseProva.setModel(boxModel);
            Fase fase = new Fase();
            fase = fachada.consultarFasePorId(codFase[ComboFaseProva.getSelectedIndex()]);
            codDiaFase = new long[fase.getDiasFase().size()];
            i = 0;
            boxModel = new DefaultComboBoxModel();
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
    }

    public void carregarListaQuestoesMultiplaEscolha() {
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new String[]{"Questao", "Referencia", "Genero"});
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
            modelo.setColumnIdentifiers(new String[]{"Questao", "Referencia", "Genero"});
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
            modelo.setColumnIdentifiers(new String[]{"Questao", "Referencia", "Genero"});
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
            provaPDF.add(new Paragraph("Area do Concurso: " + diaFase.getFase().getAreaconcurso().getNome(), font));
            provaPDF.add(new Paragraph("Inicio da Prova: " + formatoHora.format(diaFase.getHoraInicial().getTime()).toString() + "     Termino da Prova: " + formatoHora.format(diaFase.getHoraFinal().getTime()).toString(), font));
            provaPDF.add(Chunk.NEWLINE);
            provaPDF.add(nomeConcurso);
            provaPDF.add(Chunk.NEWLINE);
            provaPDF.add(Chunk.NEWLINE);
            int numQuestao = 1;
            for (Questao questao : prova.getQuestoes()) {
                String referencia = "";
                if (questao.getReferencia() != null && !questao.getReferencia().trim().equals("")) {
                    referencia = "(" + questao.getReferencia() + ")";
                }
                provaPDF.add(new Paragraph(15, numQuestao++ + "ª) " + referencia + questao.getTexto(), font));
                if (questao instanceof QuestaoMultiplaEscolha) {
                    int i = 0;
                    QuestaoMultiplaEscolha questaoMultiplaEscolha = (QuestaoMultiplaEscolha) questao;
                    for (Alternativa alternativa : questaoMultiplaEscolha.getAlternativas()) {
                        provaPDF.add(new Paragraph(15, letras.charAt(i) + ") " + alternativa.getTexto(), font));
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
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textPesoMultiplaProva = new javax.swing.JTextField();
        textPesoDiscursoProva = new javax.swing.JTextField();
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
        setTitle("Cadastro de Provas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Genero");

        ComboGeneroProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboGeneroProvaActionPerformed(evt);
            }
        });

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

        ButtonLimparProva.setText("Cancelar");
        ButtonLimparProva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLimparProvaActionPerformed(evt);
            }
        });

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

        jLabel9.setText("Peso Multipla");

        jLabel10.setText("Peso Discursiva");

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
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel9)
                                        .addGap(10, 10, 10)
                                        .addComponent(textPesoMultiplaProva, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addGap(7, 7, 7)
                                        .addComponent(textPesoDiscursoProva, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(textPesoMultiplaProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPesoDiscursoProva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonLimparProva)
                    .addComponent(ButtonSalvarProva)
                    .addComponent(ButtonCadastrarQuestoesProva))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            if (fachada.consultarTodosQuestaoDiscursiva().size() <= 0 && fachada.consultarTodosQuestaoMultiplaEscolha().size() <= 0) {
                JOptionPane.showMessageDialog(rootPane, "Nao existem questoes Cadastradas");
            } else {
                prova = new Prova();
                Genero genero = new Genero();
                genero.setId(codGeneros[ComboGeneroProva.getSelectedIndex()]);
                ArrayList<QuestaoDiscursiva> questoesDiscursivas = new ArrayList<QuestaoDiscursiva>();
                ArrayList<QuestaoMultiplaEscolha> questoesMultiplaEscolhas = new ArrayList<QuestaoMultiplaEscolha>();
                questoesDiscursivas = (ArrayList<QuestaoDiscursiva>) fachada.consultarTodosPorGeneroQuestaoDiscursiva(genero);
                questoesMultiplaEscolhas = (ArrayList<QuestaoMultiplaEscolha>) fachada.consultarTodosPorGeneroQuestaoMultiplaEscolha(genero);
                int questMult = 0;
                int questDisc = 0;
                boolean teste = true;
                while (teste) {
                    teste = false;
                    questMult = Integer.parseInt(JOptionPane.showInputDialog("Informe o número de questoes de multipla escolha: "));
                    if (questMult > questoesMultiplaEscolhas.size()) {
                        teste = true;
                        JOptionPane.showMessageDialog(rootPane, "O número de Questões informado Excede o número de questões cadastradas com este gênero");
                    }
                }
                teste = true;
                while (teste) {
                    teste = false;
                    questDisc = Integer.parseInt(JOptionPane.showInputDialog("Informe o número de questoes discussivas escolha: "));
                    if (questDisc > questoesDiscursivas.size()) {
                        teste = true;
                        JOptionPane.showMessageDialog(rootPane, "O número de Questões informado Excede o número de questões cadastradas com este gênero");
                    }
                }
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
            }
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
        if (TableQuestoesMultiplaEscolhaProva.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione a Questao multipla a ser inserida");
        } else {

            questoesEscolhidas.add(questoesMultiplaEscolha.get(TableQuestoesMultiplaEscolhaProva.getSelectedRow()));
            questoesMultiplaEscolha.remove(TableQuestoesMultiplaEscolhaProva.getSelectedRow());
            this.carregarListaQuestoesEscolhidas();
            this.carregarListaQuestoesMultiplaEscolha();
    }//GEN-LAST:event_jButton5ActionPerformed
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (TableQuestoesDiscursivaProva.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione a Questao Discursiva a ser inserida");
        } else {

            questoesEscolhidas.add(questoesDiscursiva.get(TableQuestoesDiscursivaProva.getSelectedRow()));
            questoesDiscursiva.remove(TableQuestoesDiscursivaProva.getSelectedRow());
            this.carregarListaQuestoesEscolhidas();
            this.carregarListaQuestoesDiscursiva();
    }//GEN-LAST:event_jButton3ActionPerformed
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (TableQuestoesEscolhidasprova.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione a Questao a ser descartada");
        } else {
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
    }
    private void ButtonSalvarProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalvarProvaActionPerformed
        // TODO add your handling code here:

        try {
            if (ComboGeneroProva.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(rootPane, "Selecione Genero");
                finalize();
                return;
            }
            if (ComboConcursoProva.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(rootPane, "Selecione Concurso");
                finalize();
                return;
            }
            if (ComboAreaConcursoProva.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(rootPane, "Selecione Area do Concurso");
                finalize();
                return;
            }
            if (ComboFaseProva.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(rootPane, "Selecione Fase");
                finalize();
                return;
            }
            if (ComboDiaFaseProva.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(rootPane, "Selecione DiaFase");
                finalize();
                return;
            }
            if (textPesoMultiplaProva.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Peso Multiplo esta vazio, favor informar valor valido.");
                return;
            }

            boolean flag = false;
            for (int i = 0; i < textPesoMultiplaProva.getText().length(); i++) {
                char c = textPesoMultiplaProva.getText().charAt(i);
                if (!Character.isDigit(c)) {
                    JOptionPane.showMessageDialog(rootPane, "Peso da questao Multipla invalido");
                    flag = true;
                    break;
                }
            }

            for (int i = 0; i < textPesoDiscursoProva.getText().length(); i++) {
                char c = textPesoDiscursoProva.getText().charAt(i);
                if (!Character.isDigit(c)) {
                    JOptionPane.showMessageDialog(rootPane, "Peso da questao Discurso invalido");
                    flag = true;
                    break;
                }
            }

            if (questoesEscolhidas.size() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Selecione questoes para esta prova");
                return;
            }

            if (!flag) {
                prova.setPesoMultipla(Integer.parseInt(textPesoMultiplaProva.getText()));
                prova.setPesoDiscursiva(Integer.parseInt(textPesoDiscursoProva.getText()));
                prova.setQuestoes(questoesEscolhidas);
                this.gerarProva();
            }

    }//GEN-LAST:event_ButtonSalvarProvaActionPerformed
         catch (Exception ex) {
        } catch (Throwable ex) {
            Logger.getLogger(CRUDProva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void ButtonCadastrarQuestoesProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCadastrarQuestoesProvaActionPerformed
        // TODO add your handling code here:
        try {
            CRUDQuestao crudq;
            crudq = new CRUDQuestao(null);
            crudq.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_ButtonCadastrarQuestoesProvaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            List<Concurso> concursos = new ArrayList<Concurso>();
            concursos = fachada.consultarTodosConcurso();
            if (concursos.size() <= 0) {
                JOptionPane.showMessageDialog(rootPane, "Nao ha Concursos cadastrados, cadastre um concurso para prosseguir");
                this.dispose();
    }//GEN-LAST:event_formWindowOpened
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "");
        }
    }
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
        JOptionPane.showMessageDialog(rootPane, "'EOC' Empresa Organizadora de Concurso\n dispoe de diversas ferramentas de gerenciamento\n"
                + "Para adequar-se ao uso da ferramenta oferecemos o treinamento necessario\n.Duvidas ligue para fone:Telefone de Antonio ");
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void ButtonLimparProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLimparProvaActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_ButtonLimparProvaActionPerformed

    private void ComboGeneroProvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboGeneroProvaActionPerformed
        try {
            // TODO add your handling code here:

            questoesMultiplaEscolha = (ArrayList<QuestaoMultiplaEscolha>) fachada.consultarTodosPorGeneroQuestaoMultiplaEscolha(generos.get(ComboGeneroProva.getSelectedIndex()));
            questoesDiscursiva = (ArrayList<QuestaoDiscursiva>) fachada.consultarTodosPorGeneroQuestaoDiscursiva(generos.get(ComboGeneroProva.getSelectedIndex()));
            questoesEscolhidas = new ArrayList<Questao>();
            this.carregarListaQuestoesMultiplaEscolha();
            this.carregarListaQuestoesDiscursiva();
            this.carregarListaQuestoesEscolhidas();
        } catch (Exception ex) {
            Logger.getLogger(CRUDProva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ComboGeneroProvaActionPerformed

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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTextField textPesoDiscursoProva;
    private javax.swing.JTextField textPesoMultiplaProva;
    // End of variables declaration//GEN-END:variables
}
