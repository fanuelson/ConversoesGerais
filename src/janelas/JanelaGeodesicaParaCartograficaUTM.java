package janelas;

//import escritorArquivo.EscritorArquivoCartUTMUtils;
import escritorArquivo.EscritorArquivoCARTUtils;
import java.awt.FileDialog;
import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import leitorArquivo.CoordCartesiana;
import leitorArquivo.CoordGeodesica;
import leitorArquivo.CoordCartograficaUTM;
import leitorArquivo.LeitorArquivoGEOUtils;
import leitorArquivo.LeitorArquivoGEOUtilsNovo;
import leitorArquivo.MyFileFilter;
import novo.CalculadoraGEOparaUTM;
import utils.MyMathUtils;

public class JanelaGeodesicaParaCartograficaUTM extends javax.swing.JFrame {

    BigDecimal nZero;
    String hemisferioEscolhido;
    BigDecimal kZero;
    BigDecimal falsoNorteHN;
    BigDecimal falsoNorteHS;
    BigDecimal falsoLeste;

    CalculadoraGEOparaUTM calculadoraUTM = new CalculadoraGEOparaUTM();

    List<CoordCartograficaUTM> coordenadasCartograficasUTM = new ArrayList<CoordCartograficaUTM>();

    public JanelaGeodesicaParaCartograficaUTM() {
        initComponents();

        jFileChooser1.setFileFilter(new MyFileFilter());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        botaoCalcular = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Latitude", "Longitude", "Altitude"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "N (UTM)", "E  (UTM)", "MC"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        botaoCalcular.setText("Calcular");
        botaoCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCalcularActionPerformed(evt);
            }
        });

        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenuBar1.setToolTipText("");
        jMenuBar1.setName(""); // NOI18N

        jMenu1.setText("Arquivo");

        jMenuItem1.setText("Abrir...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Salvar como");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(botaoCalcular)
                        .addGap(18, 18, 18)
                        .addComponent(botaoLimpar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCalcular)
                    .addComponent(botaoLimpar)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        jTable1.setModel(new DefaultTableModel(null, new String[]{"ID", "Latitude", "Longitude", "Altitude"}));
        jTable2.setModel(new DefaultTableModel(null, new String[]{"ID", "N (UTM)", "E (UTM)" ,  "MC"}));
        
    }//GEN-LAST:event_botaoLimparActionPerformed
    private void removerTodosElementosDaTabelaGeodesica() {
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"ID", "Latitude", "Longitude", "Altitude"}));
    }

    private void removerTodosElementosDaTabelaCartografica() {
        coordenadasCartograficasUTM.clear();
        jTable2.setModel(new DefaultTableModel(null, new Object[]{"ID", "N (UTM)", "E (UTM)",  "MC"}));
    }

    private void adicionarNaTabelaGeodesica(CoordGeodesica coordenada) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        defaultTableModel.addRow(new Object[]{coordenada.id, coordenada.latitude, coordenada.longitude, coordenada.altitude});
    }

    private void adicionarTabelaCartografica(CoordCartograficaUTM coordenadaCartografica) {
        coordenadasCartograficasUTM.add(coordenadaCartografica);
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
        defaultTableModel.addRow(new Object[]{coordenadaCartografica.id, coordenadaCartografica.norteUTM, coordenadaCartografica.lesteUTM, coordenadaCartografica.mc});
    }
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int returnVal = jFileChooser1.showOpenDialog(this);
        if (returnVal == jFileChooser1.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();

            List<CoordGeodesica> coordenadasDoArquivo = LeitorArquivoGEOUtilsNovo.lerCoordenadasGeodesicas(file);

            for (CoordGeodesica coordenada : coordenadasDoArquivo) {
                adicionarNaTabelaGeodesica(coordenada);
            }

        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        FileDialog fDialog = new FileDialog(this, "Save As ...", FileDialog.SAVE);
        fDialog.setFile("coord-cartograficaUTM.txt");
        fDialog.setVisible(true);

        String caminhoArquivo = fDialog.getDirectory() + fDialog.getFile();

//        EscritorArquivoCartUTMUtils.escreverCoordenadasCartograficasUTM(coordenadasCartograficasUTM, caminhoArquivo);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void botaoCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularActionPerformed
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            Vector linhas = dtm.getDataVector();

            removerTodosElementosDaTabelaCartografica();

            calculadoraUTM.atribuirHemisferioEscolhido(hemisferioEscolhido);
            
            

            /*if (hemisferioEscolhido.equals("Escolha um Hemisfério")) {
                JOptionPane.showMessageDialog(null,
                        "Hemisfério não selecionado!",
                        "ERRO",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } */
            
            /* System.out.println("HEMISFERIO ESCOLHIDO " + hemisferioEscolhido );
            calculadoraUTM.atribuirHemisferioEscolhido(hemisferioEscolhido); */

            Iterator linhasIterator = linhas.iterator();
            while (linhasIterator.hasNext()) {

                Vector valoresLinhaAtual = (Vector) linhasIterator.next();
                CoordGeodesica coordenadaGeodesica = new CoordGeodesica(valoresLinhaAtual);

                BigDecimal latitude = MyMathUtils.createBigDecimal(coordenadaGeodesica.latitude);
                BigDecimal longitude = MyMathUtils.createBigDecimal(coordenadaGeodesica.longitude);
                BigDecimal altitude = MyMathUtils.createBigDecimal(coordenadaGeodesica.altitude);

                CoordCartograficaUTM coordenadaCartografica = null;
                

                coordenadaCartografica = calculadoraUTM.realizarCalculoCartUTM(latitude, longitude);

                coordenadaCartografica.id = coordenadaGeodesica.id;
                adicionarTabelaCartografica(coordenadaCartografica);

            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,
                    "Número inválido inserido!",
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_botaoCalcularActionPerformed

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
            java.util.logging.Logger.getLogger(JanelaGeodesicaParaCartograficaUTM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaGeodesicaParaCartograficaUTM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaGeodesicaParaCartograficaUTM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaGeodesicaParaCartograficaUTM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaGeodesicaParaCartograficaUTM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCalcular;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JButton jButton3;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
