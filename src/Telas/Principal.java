package Telas;

import Objetos.MatrizTableModel;
import RMI.Connect4Interface;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

/**
 *
 * @author Allan.Amaral
 */
public class Principal extends javax.swing.JFrame {
    
    Connect4Interface connect4;
    Integer jogador;
    MatrizTableModel model;
    
    public Principal(Connect4Interface connect4, Integer jogador) {
        try {
            this.connect4 = connect4;
            this.jogador = jogador;
            this.model = new MatrizTableModel(this.connect4.obtemGrade(this.jogador));

            initComponents();
            
            esperaVez();
        
        } catch(RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter grade: " + e.getMessage(), "ERRO", JOptionPane.ERROR);
        }
    }
    
    private void esperaVez() {
        try {
            Integer vez = 0;
        
            while (vez < 1) {
                String msg = "";
                vez = this.connect4.ehMinhaVez(this.jogador);
                
                switch(vez) {
                    case 0:
                        jButton1.setEnabled(false);
                        jLabel2.setText("Aguardando oponente...");
                    break;

                    case 1:
                        jButton1.setEnabled(true);
                        msg = "Minha vez!";
                    break;

                    case 2:
                        jButton1.setEnabled(false);
                        jLabel2.setText("Vencedor :D");
                        msg = "Parabéns, você é o vencedor!!!";
                    break;

                    case 3:
                        jButton1.setEnabled(false);
                        jLabel2.setText("Perdeu :(");
                        msg = "Que pena, você perdeu.";
                    break;

                    case 4:
                        jButton1.setEnabled(false);
                        jLabel2.setText("Empate");
                        msg = "Bom jogo, acabou empatado.";
                    break;

                    case 5:
                        jButton1.setEnabled(false);
                        jLabel2.setText("Vencedor por WO :D");
                        msg = "Parabéns, você venceu por WO!!!";
                    break;

                    case 6:
                        jButton1.setEnabled(false);
                        jLabel2.setText("Perdeu :(");
                        msg = "Que pena, você perdeu por WO.";
                    break;
                }
                JOptionPane.showMessageDialog(null, msg, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            }
        
        } catch(RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter grade: " + e.getMessage(), "ERRO", JOptionPane.ERROR);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Jogar na Coluna: ");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("JOGAR");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setModel(this.model);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Integer coluna = (Integer) this.jFormattedTextField1.getValue();

            this.connect4.enviaJogada(this.jogador, coluna);
            
            this.model = new MatrizTableModel(this.connect4.obtemGrade(this.jogador));
            
            esperaVez();
            
        } catch(RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter grade: " + e.getMessage(), "ERRO", JOptionPane.ERROR);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
