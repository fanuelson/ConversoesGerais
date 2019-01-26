
package janelas;

import java.awt.FileDialog;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import leitorArquivo.CoordCartograficaUTM;
import leitorArquivo.CoordGeodesica;
import leitorArquivo.LeitorArquivoGEOUtils;
import leitorArquivo.LeitorArquivoUTMUtils;
import leitorArquivo.MyFileFilter;
import novo.CalculadoraUTMparaGEO;
import utils.MyMathUtils;



public class JanelaCartograficaUTMParaGeodesica extends javax.swing.JFrame {
    
    BigDecimal nZero;
    String hemisferioEscolhido;
    BigDecimal kZero;
    BigDecimal falsoNorteHN;
    BigDecimal falsoNorteHS;
    BigDecimal falsoLeste;
    
    CalculadoraUTMparaGEO calculadoraUTMparaGEO = new CalculadoraUTMparaGEO();

    List<CoordGeodesica> coordenadasGeodesicas = new ArrayList<CoordGeodesica>();

   
    public JanelaCartograficaUTMParaGeodesica() {
        initComponents();
        
        jFileChooser1.setFileFilter(new MyFileFilter());
        /*escolhaHemisferio.removeAllItems();
        escolhaHemisferio.addItem("Escolha um Hemisfério");
        escolhaHemisferio.addItem("Hemisfério Norte");
        escolhaHemisferio.addItem("Hemisfério Sul");*/
    }

    private void removerTodosElementosDaTabelaUTM() {
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"ID", "N (UTM)", "E (UTM)", "MC", "Hemisferio"}));
    }
    private void removerTodosElementosDaTabelaGeodesica() {
        coordenadasGeodesicas.clear();
        jTable2.setModel(new DefaultTableModel(null, new Object[]{"ID", "Latitude", "Longitude"}));
    }
    private void adicionarNaTabelaUTM(CoordCartograficaUTM coordenadaUTM) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        defaultTableModel.addRow(new Object[]{coordenadaUTM.id, coordenadaUTM.norteUTM, coordenadaUTM.lesteUTM, coordenadaUTM.mc, coordenadaUTM.hemisferio});
    }
    private void adicionarTabelaGeodesica(CoordGeodesica coordGeodesica) {
        coordenadasGeodesicas.add(coordGeodesica);
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
        defaultTableModel.addRow(new Object[]{coordGeodesica.id, coordGeodesica.latitude, coordGeodesica.longitude, coordGeodesica.altitude});
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "N (UTM)", "E (UTM)", "MC", "Hemisferio"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Latitude", "Longitude"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("Calcular");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenu1.setText("Arquivo");

        jMenuItem3.setText("Abrir...");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Salvar como");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int returnVal = jFileChooser1.showOpenDialog(this);
        if (returnVal == jFileChooser1.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();

            List<CoordCartograficaUTM> coordenadasDoArquivo = LeitorArquivoUTMUtils.lerCoordenadasUTM(file);

            for (CoordCartograficaUTM coordenada : coordenadasDoArquivo) {
                adicionarNaTabelaUTM(coordenada);
            }

        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    jTable1.setModel(new DefaultTableModel(null,new String[]{"ID", "N (UTM)", "E (UTM)", "MC", "Hemisferio"}));
    jTable2.setModel(new DefaultTableModel(null,new String[]{"ID", "Latitude", "Longitude"}));
   // escolhaHemisferio.setSelectedIndex(0);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            Vector linhas = dtm.getDataVector();

            removerTodosElementosDaTabelaGeodesica();

            calculadoraUTMparaGEO.atribuirHemisferioEscolhido(hemisferioEscolhido);

          /*  hemisferioEscolhido = (String) escolhaHemisferio.getSelectedItem();

            if (hemisferioEscolhido.equals("Escolha um Hemisfério")) {
                JOptionPane.showMessageDialog(null,
                        "Hemisfério não selecionado!",
                        "ERRO",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
             */
            //System.out.println("HEMISFERIO ESCOLHIDO " + hemisferioEscolhido);
            //calculadoraUTMparaGEO.atribuirHemisferioEscolhido(hemisferioEscolhido);

            Iterator linhasIterator = linhas.iterator();
            while (linhasIterator.hasNext()) {

                Vector valoresLinhaAtual = (Vector) linhasIterator.next();
                CoordCartograficaUTM coordenadaCartograficaUTM = new CoordCartograficaUTM(valoresLinhaAtual);

                BigDecimal norteUTM = MyMathUtils.createBigDecimal(coordenadaCartograficaUTM.norteUTM);
                BigDecimal lesteUTM = MyMathUtils.createBigDecimal(coordenadaCartograficaUTM.lesteUTM);
                BigDecimal mc = MyMathUtils.createBigDecimal(coordenadaCartograficaUTM.mc);
                String hemisferio = (coordenadaCartograficaUTM.hemisferio);  
                
                
                CoordGeodesica coordenadaGeodesica = null;

                coordenadaGeodesica = calculadoraUTMparaGEO.realizarCalculoGEO(norteUTM, lesteUTM, mc, hemisferio);
                coordenadaGeodesica.id=coordenadaCartograficaUTM.id;
                adicionarTabelaGeodesica(coordenadaGeodesica);

            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,
                    "Número inválido inserido!",
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        
        FileDialog fDialog = new FileDialog(this, "Save As ...", FileDialog.SAVE);
        fDialog.setFile("coord-geodesica.txt");
        fDialog.setVisible(true);

        String caminhoArquivo = fDialog.getDirectory() + fDialog.getFile();

//        EscritorArquivoCartUTMUtils.escreverCoordenadasCartograficasUTM(coordenadasCartograficasUTM, caminhoArquivo);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(JanelaCartograficaUTMParaGeodesica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCartograficaUTMParaGeodesica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCartograficaUTMParaGeodesica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCartograficaUTMParaGeodesica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaCartograficaUTMParaGeodesica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
