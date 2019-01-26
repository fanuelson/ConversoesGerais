package janelas;

import escritorArquivo.EscritorArquivoGEODUtils;
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
import leitorArquivo.LeitorArquivoCARTUtils;
import leitorArquivo.MyFileFilter;
import novo.CalculadoraGRS80;
import utils.MyMathUtils;

public class JanelaCartesianaParaGeodesica extends javax.swing.JFrame {

    CalculadoraGRS80 calculadoraGRS80 = new CalculadoraGRS80();
    
    List<CoordGeodesica> coordenadasGeodesicas = new ArrayList<CoordGeodesica>();
    
    public JanelaCartesianaParaGeodesica() {
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

        jFileChooser1.setDialogTitle("Minha caixa de diálogo aberta");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ConvertCart2Geo");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "X(m)", "Y(m)", "Z(m)"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Latitude", "Longitude", "Altitude"
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
        escolhaElipsoide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escolhaElipsoideActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Abrir");
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

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(escolhaElipsoide, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(botaoCalcular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCalcular)
                    .addComponent(botaoLimpar)
                    .addComponent(jButton3)
                    .addComponent(escolhaElipsoide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        int returnVal = jFileChooser1.showOpenDialog(this);
        if(returnVal == jFileChooser1.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();
            
            List<CoordCartesiana> coordenadasDoArquivo = LeitorArquivoCARTUtils.lerCoordenadasCartesianas(file);
            
            for(CoordCartesiana coordenada : coordenadasDoArquivo) {
                adicionarNaTabelaCartesiana(coordenada);
            }
            
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        removerTodosElementosDaTabelaCartesiana();
        removerTodosElementosDaTabelaGeodesica();
        escolhaElipsoide.setSelectedIndex(0);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private void removerTodosElementosDaTabelaCartesiana() {
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"ID","X(m)", "Y(m)", "Z(m)"}));
    }
    
    private void removerTodosElementosDaTabelaGeodesica() {
        coordenadasGeodesicas.clear();
        jTable2.setModel(new DefaultTableModel(null, new Object[]{"ID","Latitude", "Longitude", "Altitude"}));
    }
    
    private void adicionarNaTabelaCartesiana(CoordCartesiana coordenada) {
        
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable1.getModel();
        defaultTableModel.addRow(new Object[]{coordenada.id, coordenada.x, coordenada.y, coordenada.z});
    }
    
    private void adicionarTabelaGeodesica(CoordGeodesica coordenadaGeodesica) {
        coordenadasGeodesicas.add(coordenadaGeodesica);
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2.getModel();
        defaultTableModel.addRow(new Object[]{coordenadaGeodesica.id, coordenadaGeodesica.latitude, coordenadaGeodesica.longitude, coordenadaGeodesica.altitude});
    }
    
    private void botaoCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularActionPerformed
        DefaultTableModel dtm = (DefaultTableModel)jTable1.getModel();
        Vector linhas = dtm.getDataVector();
        
        String elipsoideSelecionado = (String) escolhaElipsoide.getSelectedItem();
        
        if (elipsoideSelecionado.equals("Escolha um elipsoide")) {
            JOptionPane.showMessageDialog(null,
                    "Elipsoide não selecionado!",
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        removerTodosElementosDaTabelaGeodesica();
        
        Iterator linhasIterator = linhas.iterator();
        while(linhasIterator.hasNext()) {
            
            Vector valoresLinhaAtual = (Vector) linhasIterator.next();
            CoordCartesiana coordenadaCartesiana = new CoordCartesiana(valoresLinhaAtual);
            
            BigDecimal x = MyMathUtils.createBigDecimal(coordenadaCartesiana.x);
            BigDecimal y = MyMathUtils.createBigDecimal(coordenadaCartesiana.y);
            BigDecimal z = MyMathUtils.createBigDecimal(coordenadaCartesiana.z);
            
            CoordGeodesica coordenadaGeodesica = calculadoraGRS80.realizarCalculoGEODGRS80(x, y, z);
            coordenadaGeodesica.id = coordenadaCartesiana.id;
            adicionarTabelaGeodesica(coordenadaGeodesica);
            
        }
        
        
    }//GEN-LAST:event_botaoCalcularActionPerformed

    private void escolhaElipsoideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escolhaElipsoideActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_escolhaElipsoideActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        FileDialog fDialog = new FileDialog(this, "Save As ...", FileDialog.SAVE);
        fDialog.setFile("coord-geodésica.txt");
        fDialog.setVisible(true);
        
        String caminhoArquivo = fDialog.getDirectory() + fDialog.getFile();
        
        EscritorArquivoGEODUtils.escreverCoordenadasGeodesicas(coordenadasGeodesicas, caminhoArquivo);
        
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    

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
            java.util.logging.Logger.getLogger(JanelaCartesianaParaGeodesica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaCartesianaParaGeodesica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaCartesianaParaGeodesica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaCartesianaParaGeodesica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaCartesianaParaGeodesica().setVisible(true);
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
