package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.filechooser.FileFilter;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.GeraArquivo;
import model.Lexico;
import model.LexicalError;
import model.SemanticError;
import model.Semantico;
import model.Sintatico;
import model.SyntaticError;

/**
 *
 * @author Sara Helena Régis Theiss
 */
public class MainFrame extends javax.swing.JFrame {

    private boolean possuiAlteracao = false;

    public MainFrame() {
        initComponents();
        AjustarFrame();
        btnNovo();
        btnAbrir();
        btnSalvar();
        btnCopiar();
        btnColar();
        btnRecortar();
        btnCompilar();
        btnEquipe();
        AjustarToolbar();
        DefineConfiguracoesEditor();
        taEditor.requestFocus();
    }

    private void alterar(boolean possuiAlteracao) {
        Font font = lblArquivo.getFont();
        if (possuiAlteracao && !lblArquivo.getText().isEmpty()) {
            lblArquivo.setFont(font.deriveFont(font.getStyle() | Font.BOLD));
        } else {
            lblArquivo.setFont(font.deriveFont(font.getStyle() & ~Font.BOLD));
        }
        this.possuiAlteracao = possuiAlteracao;
    }

    private void DefineConfiguracoesEditor() {
        taEditor.setFont(Font.decode("UTF-8"));
        taEditor.setFont(taEditor.getFont().deriveFont(12f));

        taEditor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent de) {
                if (!possuiAlteracao) {
                    alterar(true);
                }
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent de) {
                if (!possuiAlteracao) {
                    alterar(true);
                }
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent de) {
                if (!possuiAlteracao) {
                    alterar(true);
                }
            }
        });
    }

    private void LimparTela() {
        taEditor.setText("");
        taMensagens.setText("");
        lblArquivo.setText("");
        if (possuiAlteracao) {
            alterar(false);
        }
    }

    private boolean salvarArquivo(String file) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            for (String linha : taEditor.getText().split(System.lineSeparator())) {
                fw.write(linha + System.lineSeparator());
            }
            fw.flush();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu durante execução da ação.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Um erro ocorreu durante execução da ação.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }

        if (possuiAlteracao) {
            alterar(false);
        }
        return true;
    }

    private void btnNovo() {
        Action btnActionNew = new AbstractAction("novo [ctrl + n]") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                LimparTela();
            }
        };
        btnNew.setAction(btnActionNew);
        btnNew.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK), "");
        btnNew.getActionMap().put("", btnActionNew);
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons/edit.png")));
    }

    private void btnAbrir() {
        Action btnActionOpen = new AbstractAction("abrir [ctrl + o]") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JFileChooser fileChooser = new JFileChooser();
                for (FileFilter ff : fileChooser.getChoosableFileFilters()) {
                    fileChooser.removeChoosableFileFilter(ff);
                }
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto", "txt", "text");
                fileChooser.setFileFilter(filter);

                int resultado = fileChooser.showOpenDialog(MainFrame.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        Scanner reader = new Scanner(file);
                        LimparTela();
                        lblArquivo.setText(file.getAbsolutePath());
                        while (reader.hasNext()) {
                            taEditor.append(reader.nextLine() + System.lineSeparator());
                        }
                        alterar(false);
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Arquivo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (resultado == JFileChooser.ERROR_OPTION) {
                    JOptionPane.showMessageDialog(null, "Um erro ocorreu durante execução da ação.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        btnOpen.setAction(btnActionOpen);
        btnOpen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), "");
        btnOpen.getActionMap().put("", btnActionOpen);
        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons/open.png")));
    }

    private void btnSalvar() {
        Action btnActionSave = new AbstractAction("salvar [ctrl + s]") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (lblArquivo.getText().isEmpty()) {
                    JFileChooser fileChooser = new JFileChooser();
                    for (FileFilter ff : fileChooser.getChoosableFileFilters()) {
                        fileChooser.removeChoosableFileFilter(ff);
                    }
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto", "txt", "text");
                    fileChooser.setFileFilter(filter);
                    int resultado = fileChooser.showSaveDialog(MainFrame.this);
                    if (resultado == JFileChooser.APPROVE_OPTION) {
                        String selFile = fileChooser.getSelectedFile().getAbsolutePath();
                        if (!selFile.endsWith(".txt")) {
                            selFile += ".txt";
                        }
                        if (salvarArquivo(selFile)) {
                            taMensagens.setText("");
                            lblArquivo.setText(selFile);
                        }
                    } else if (resultado == JFileChooser.ERROR_OPTION) {
                        JOptionPane.showMessageDialog(null, "Um erro ocorreu durante execução da ação.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (salvarArquivo(lblArquivo.getText())) {
                        taMensagens.setText("");
                    }
                }
            }
        };
        btnSave.setAction(btnActionSave);
        btnSave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "");
        btnSave.getActionMap().put("", btnActionSave);
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons/save.png")));
    }

    private void btnCopiar() {
        Action btnActionCopy = new AbstractAction("copiar [ctrl + c]") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                taEditor.copy();
            }
        };

        btnCopy.setAction(btnActionCopy);
        btnCopy.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), "");
        btnCopy.getActionMap().put("", btnActionCopy);
        btnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons/copy.png")));
    }

    private void btnColar() {
        Action btnActionPaste = new AbstractAction("colar [ctrl + v]") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                taEditor.paste();
            }
        };

        btnPaste.setAction(btnActionPaste);
        btnPaste.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK), "");
        btnPaste.getActionMap().put("", btnActionPaste);
        btnPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons/paste.png")));
    }

    private void btnRecortar() {
        Action btnActionCut = new AbstractAction("recortar [ctrl + x]") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                taEditor.cut();
            }
        };

        btnCut.setAction(btnActionCut);
        btnCut.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), "");
        btnCut.getActionMap().put("", btnActionCut);
        btnCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons/cut.png")));
    }

    private void btnCompilar() {
        Action btnActionCompile = new AbstractAction("compilar [F9]") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String entrada = taEditor.getText();

                // remove todos os caracteres de formatação
                String auxEntrada = entrada.replace("\n", "");
                auxEntrada = auxEntrada.replace("\t", "");
                auxEntrada = auxEntrada.replace(" ", "");

                if (auxEntrada.isEmpty()) {
                    taMensagens.setText("Nenhum programa para compilar!");
                } else {
                    Lexico lexico = new Lexico(entrada);
                    Sintatico sintatico = new Sintatico();
                    Semantico semantico = new Semantico();

                    try {
                        sintatico.parse(lexico, semantico);
                        
                        GeraArquivo.gerarArquivo(lblArquivo.getText(), semantico.getCodigoGerado());
                        taMensagens.setText("Programa compilado com sucesso!");
                    } catch (LexicalError | SyntaticError | SemanticError e) {
                        taMensagens.setText(e.toString());
                    } catch (FileNotFoundException e) {
                        taMensagens.setText("Erro ao gerar arquivo de código! (" + e.getMessage() + ")");
                    }
                }
            }
        };

        btnCompile.setAction(btnActionCompile);
        btnCompile.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "");
        btnCompile.getActionMap().put("", btnActionCompile);
        btnCompile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons/compile.png")));
    }

    private void btnEquipe() {
        Action btnActionTeam = new AbstractAction("equipe [F1]") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                taMensagens.setText("Equipe de desenvolvimento: Sara Helena Régis Theiss");
            }
        };

        btnTeam.setAction(btnActionTeam);
        btnTeam.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "");
        btnTeam.getActionMap().put("", btnActionTeam);
        btnTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icons/team.png")));
    }

    private void AjustarFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }

    private void AjustarToolbar() {
        btnNew.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnNew.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCompile.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCompile.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCopy.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCopy.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCut.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCut.setHorizontalTextPosition(SwingConstants.CENTER);
        btnOpen.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnOpen.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPaste.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnPaste.setHorizontalTextPosition(SwingConstants.CENTER);
        btnTeam.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnTeam.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jPanelToolbar = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCopy = new javax.swing.JButton();
        btnPaste = new javax.swing.JButton();
        btnCut = new javax.swing.JButton();
        btnCompile = new javax.swing.JButton();
        btnTeam = new javax.swing.JButton();
        jPanelMessages = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        taMensagens = new javax.swing.JTextArea();
        jPanelStatus = new javax.swing.JPanel();
        lblArquivo = new javax.swing.JLabel();
        jPanelEditor = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taEditor = new javax.swing.JTextArea();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador");
        setBounds(new java.awt.Rectangle(100, 50, 920, 600));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(920, 600));
        setSize(new java.awt.Dimension(920, 600));

        jPanelToolbar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelToolbar.setToolTipText("");
        jPanelToolbar.setFocusCycleRoot(true);
        jPanelToolbar.setMinimumSize(new java.awt.Dimension(900, 70));
        jPanelToolbar.setName("toolbar"); // NOI18N
        jPanelToolbar.setPreferredSize(new java.awt.Dimension(900, 70));

        btnNew.setBackground(java.awt.Color.lightGray);
        btnNew.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNew.setText("novo [ctrl + n]");
        btnNew.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNew.setBorderPainted(false);
        btnNew.setDefaultCapable(false);
        btnNew.setMinimumSize(new java.awt.Dimension(112, 70));
        btnNew.setPreferredSize(new java.awt.Dimension(112, 70));

        btnOpen.setBackground(java.awt.Color.lightGray);
        btnOpen.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnOpen.setText("abrir [ctrl + o]");
        btnOpen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnOpen.setBorderPainted(false);
        btnOpen.setDefaultCapable(false);
        btnOpen.setMinimumSize(new java.awt.Dimension(112, 70));
        btnOpen.setPreferredSize(new java.awt.Dimension(112, 70));

        btnSave.setBackground(java.awt.Color.lightGray);
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSave.setText("salvar [ctrl + s]");
        btnSave.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSave.setBorderPainted(false);
        btnSave.setDefaultCapable(false);
        btnSave.setMinimumSize(new java.awt.Dimension(112, 70));
        btnSave.setPreferredSize(new java.awt.Dimension(112, 70));
        btnSave.setVerifyInputWhenFocusTarget(false);

        btnCopy.setBackground(java.awt.Color.lightGray);
        btnCopy.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCopy.setText("copiar [ctrl + c]");
        btnCopy.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCopy.setBorderPainted(false);
        btnCopy.setDefaultCapable(false);
        btnCopy.setMinimumSize(new java.awt.Dimension(112, 70));
        btnCopy.setPreferredSize(new java.awt.Dimension(112, 70));

        btnPaste.setBackground(java.awt.Color.lightGray);
        btnPaste.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnPaste.setText("colar [ctrl + v]");
        btnPaste.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPaste.setBorderPainted(false);
        btnPaste.setDefaultCapable(false);
        btnPaste.setMinimumSize(new java.awt.Dimension(112, 70));
        btnPaste.setPreferredSize(new java.awt.Dimension(112, 70));

        btnCut.setBackground(java.awt.Color.lightGray);
        btnCut.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCut.setText("recortar [ctrl + x]");
        btnCut.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCut.setBorderPainted(false);
        btnCut.setDefaultCapable(false);
        btnCut.setMinimumSize(new java.awt.Dimension(112, 70));
        btnCut.setPreferredSize(new java.awt.Dimension(112, 70));

        btnCompile.setBackground(java.awt.Color.lightGray);
        btnCompile.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCompile.setText("compilar [F9]");
        btnCompile.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCompile.setBorderPainted(false);
        btnCompile.setDefaultCapable(false);
        btnCompile.setMinimumSize(new java.awt.Dimension(112, 70));
        btnCompile.setPreferredSize(new java.awt.Dimension(112, 70));

        btnTeam.setBackground(java.awt.Color.lightGray);
        btnTeam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTeam.setText("equipe [F1]");
        btnTeam.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnTeam.setBorderPainted(false);
        btnTeam.setDefaultCapable(false);
        btnTeam.setMinimumSize(new java.awt.Dimension(112, 70));
        btnTeam.setPreferredSize(new java.awt.Dimension(112, 70));

        javax.swing.GroupLayout jPanelToolbarLayout = new javax.swing.GroupLayout(jPanelToolbar);
        jPanelToolbar.setLayout(jPanelToolbarLayout);
        jPanelToolbarLayout.setHorizontalGroup(
            jPanelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelToolbarLayout.createSequentialGroup()
                .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPaste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCompile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanelToolbarLayout.setVerticalGroup(
            jPanelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCopy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnPaste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCompile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelMessages.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelMessages.setMinimumSize(new java.awt.Dimension(900, 125));
        jPanelMessages.setPreferredSize(new java.awt.Dimension(900, 125));

        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        taMensagens.setEditable(false);
        taMensagens.setColumns(20);
        taMensagens.setRows(5);
        taMensagens.setFocusable(false);
        taMensagens.setRequestFocusEnabled(false);
        jScrollPane.setViewportView(taMensagens);

        javax.swing.GroupLayout jPanelMessagesLayout = new javax.swing.GroupLayout(jPanelMessages);
        jPanelMessages.setLayout(jPanelMessagesLayout);
        jPanelMessagesLayout.setHorizontalGroup(
            jPanelMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane)
        );
        jPanelMessagesLayout.setVerticalGroup(
            jPanelMessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanelStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelStatus.setMinimumSize(new java.awt.Dimension(900, 30));
        jPanelStatus.setPreferredSize(new java.awt.Dimension(900, 30));

        javax.swing.GroupLayout jPanelStatusLayout = new javax.swing.GroupLayout(jPanelStatus);
        jPanelStatus.setLayout(jPanelStatusLayout);
        jPanelStatusLayout.setHorizontalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelStatusLayout.setVerticalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanelEditor.setMinimumSize(new java.awt.Dimension(900, 100));
        jPanelEditor.setPreferredSize(new java.awt.Dimension(900, 363));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        taEditor.setColumns(20);
        taEditor.setRows(5);
        taEditor.setBorder(new NumeredBorder());
        jScrollPane1.setViewportView(taEditor);

        javax.swing.GroupLayout jPanelEditorLayout = new javax.swing.GroupLayout(jPanelEditor);
        jPanelEditor.setLayout(jPanelEditorLayout);
        jPanelEditorLayout.setHorizontalGroup(
            jPanelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanelEditorLayout.setVerticalGroup(
            jPanelEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelMessages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanelMessages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompile;
    private javax.swing.JButton btnCopy;
    private javax.swing.JButton btnCut;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnPaste;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTeam;
    private javax.swing.JPanel jPanelEditor;
    private javax.swing.JPanel jPanelMessages;
    private javax.swing.JPanel jPanelStatus;
    private javax.swing.JPanel jPanelToolbar;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblArquivo;
    private javax.swing.JTextArea taEditor;
    private javax.swing.JTextArea taMensagens;
    // End of variables declaration//GEN-END:variables
}
