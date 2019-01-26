package janelas;

import escritorArquivo.EscritorArquivoCARTUtils;
import java.awt.FileDialog;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import leitorArquivo.CoordCartesiana;
import leitorArquivo.CoordGeodesica;
import leitorArquivo.LeitorArquivoGEOUtils;
import leitorArquivo.MyFileFilter;
import novo.CalculadoraGRS80;
import utils.MyMathUtils;

public class JanelaGeodesicaParaCartesiana extends javax.swing.JFrame {
    
    
    CalculadoraGRS80 calculadoraGRS80 = new CalculadoraGRS80();

    List<CoordCartesiana> coordenadasCartesianas = new ArrayList<CoordCartesiana>();
  

    public JanelaGeodesicaParaCartesiana() {
        initComponents();
        
        
        jFileChooser1.setFileFilter(new MyFileFilter());
        escolhaElipsoide.removeAllItems();
        escolhaElipsoide.addItem("Escolha um elipsoide");
        escolhaElipsoide.addItem("GRS80");
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
        escolhaElipsoide = new javax.swing.JComboBox<>();
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
                "ID", "X(m)", "Y(m)", "Z(m)"
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

        escolhaElipsoide.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jMenu1.setText("Arquivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(escolhaElipsoide, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoCalcular)
                        .addGap(18, 18, 18)
                        .addComponent(botaoLimpar)
                        .addGap(28, 28, 28)
                        .addComponent(jButton3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar)
                    .addComponent(jButton3)
                    .addComponent(botaoCalcular)
                    .addComponent(escolhaElipsoide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(221, 221, 221))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        int returnVal = jFileChooser1.showOpenDialog(this);
        if (returnVal == jFileChooser1.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();

            List<CoordGeodesica> coordenadasDoArquivo = LeitorArquivoGEOUtils.lerCoordenadasGeodesicas(file);

            for (CoordGeodesica coordenada : coordenadasDoArquivo) {
                adicionarNaTabelaGeodesica(coordenada);
            }

        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        FileDialog fDialog = new FileDialog(this, "Save As ...", FileDialog.SAVE);
        fDialog.setFile("coord-cartesiana.txt");
        fDialog.setVisible(true);

        String caminhoArquivo = fDialog.getDirectory() + fDialog.getFile();

        EscritorArquivoCARTUtils.escreverCoordenadasCartesianas(coordenadasCartesianas, caminhoArquivo);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void botaoCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        Vector linhas = dtm.getDataVector();

        removerTodosElementosDaTabelaCartesiana();
        
        String elipsoideSelecionado = (String) escolhaElipsoide.getSelectedItem();
        
         if (elipsoideSelecionado.equals("Escolha um elipsoide")) {
                JOptionPane.showMessageDialog(null,
                        "Elipsoide não selecionado!",
                        "ERRO",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        Iterator linhasIterator = linhas.iterator();
        while (linhasIterator.hasNext()) {

            Vector valoresLinhaAtual = (Vector) linhasIterator.next();
            CoordGeodesica coordenadaGeodesica = new CoordGeodesica(valoresLinhaAtual);

            BigDecimal latitude = MyMathUtils.createBigDecimal(coordenadaGeodesica.latitude);
            BigDecimal longitude = MyMathUtils.createBigDecimal(coordenadaGeodesica.longitude);
            BigDecimal altitude = MyMathUtils.createBigDecimal(coordenadaGeodesica.altitude);

            
            CoordCartesiana coordenadaCartesiana = calculadoraGRS80.realizarCalculoCARTGRS80(latitude, longitude, altitude);
            
            coordenadaCartesiana.id = coordenadaGeodesica.id;
            adicionarTabelaCartesiana(coordenadaCartesiana);

        }
    }//GEN-LAST:event_botaoCalcularActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        removerTodosElementosDaTabelaGeodesica();
        removerTodosElementosDaTabelaCartesiana();
        escolhaElipsoide.setSelectedIndex(0);
    }//GEN-LAST:event_botaoLimparActionPerformed
    
    
    
    private void removerTodosElementosDaTabelaGeodesica() {
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"ID", "Latitude", "Longitude", "Altitude"}));
    }

    private void removerTodosElementosDaTabelaCartesiana() {
        coordenadasCartesianas.clear();
        jTable2.setModel(new DefaultTableModel(null, new Object[]{"ID", "X(m)", "Y(m)", "Z(m)"}));
    }

    private void adicionarNaTabelaGeodesica(CoordGeodesica coordenada) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        defaultTableModel.addRow(new Object[]{coordenada.id, coordenada.latitude, coordenada.longitude, coordenada.altitude});
    }

    private void adicionarTabelaCartesiana(CoordCartesiana coordenadaCartesiana) {
        coordenadasCartesianas.add(coordenadaCartesiana);
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
        defaultTableModel.addRow(new Object[]{coordenadaCartesiana.id, coordenadaCartesiana.x, coordenadaCartesiana.y, coordenadaCartesiana.z});
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(JanelaGeodesicaParaCartesiana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaGeodesicaParaCartesiana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaGeodesicaParaCartesiana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaGeodesicaParaCartesiana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
      

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaGeodesicaParaCartesiana().setVisible(true);
            }
        });
    } 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCalcular;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox<String> escolhaElipsoide;
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

