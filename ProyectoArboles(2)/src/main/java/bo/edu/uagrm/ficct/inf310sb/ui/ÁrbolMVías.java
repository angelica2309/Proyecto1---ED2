/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310sb.ui;


import bo.edu.uagrm.ficct.inf310sb.arboles.ArbolMViasBusqueda;
import bo.edu.uagrm.ficct.inf310sb.arboles.IArbolBusqueda;


public class ÁrbolMVías extends javax.swing.JFrame  {

    /**
     * Creates new form ÁrbolMVías
     */
    public ÁrbolMVías() {
        initComponents();
        setLocationRelativeTo(null);
   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jDialog1 = new javax.swing.JDialog();
        jButton1 = new javax.swing.JButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        significado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recorridos = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        eliminar2 = new javax.swing.JButton();
        cargar2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        InOrden = new javax.swing.JMenuItem();
        PreOrden = new javax.swing.JMenuItem();
        PostOrden = new javax.swing.JMenuItem();
        niveles = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));

        jPanel1.setBackground(new java.awt.Color(12, 23, 26));

        nombre.setBackground(new java.awt.Color(153, 153, 153));
        nombre.setForeground(new java.awt.Color(2, 26, 28));

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jhoselin\\Downloads\\ProyectoArboles(2) (2)\\ProyectoArboles(2)\\src\\iconos\\usuario.png")); // NOI18N
        jLabel2.setText("Nombre:");

        significado.setBackground(new java.awt.Color(153, 153, 153));
        significado.setForeground(new java.awt.Color(0, 25, 36));

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jhoselin\\Downloads\\ProyectoArboles(2) (2)\\ProyectoArboles(2)\\src\\iconos\\sig.png")); // NOI18N
        jLabel3.setText("Significado:");

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jhoselin\\Downloads\\ProyectoArboles(2) (2)\\ProyectoArboles(2)\\src\\iconos\\recorrido.png")); // NOI18N
        jLabel4.setText("Recorrido:");

        recorridos.setBackground(new java.awt.Color(153, 153, 153));
        recorridos.setColumns(20);
        recorridos.setForeground(new java.awt.Color(0, 33, 35));
        recorridos.setRows(5);
        jScrollPane1.setViewportView(recorridos);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("SIGNIFICADO DE NOMBRES");

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jhoselin\\Downloads\\ProyectoArboles(2) (2)\\ProyectoArboles(2)\\src\\iconos\\buscar.png")); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jhoselin\\Downloads\\ProyectoArboles(2) (2)\\ProyectoArboles(2)\\src\\iconos\\insertar.png")); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        eliminar2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jhoselin\\Downloads\\ProyectoArboles(2) (2)\\ProyectoArboles(2)\\src\\iconos\\trash_26px.png")); // NOI18N
        eliminar2.setContentAreaFilled(false);
        eliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar2ActionPerformed(evt);
            }
        });

        cargar2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jhoselin\\Downloads\\ProyectoArboles(2) (2)\\ProyectoArboles(2)\\src\\iconos\\cargar.png")); // NOI18N
        cargar2.setContentAreaFilled(false);
        cargar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(significado, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cargar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eliminar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(eliminar2)
                    .addComponent(cargar2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)))
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jButton2))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(significado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(102, 102, 102));

        jMenu4.setText("Recorridos");

        InOrden.setText("Orden Alfabético(RecorridoInOrden)");
        InOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InOrdenActionPerformed(evt);
            }
        });
        jMenu4.add(InOrden);

        PreOrden.setText("RecorridoPreOrden");
        PreOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreOrdenActionPerformed(evt);
            }
        });
        jMenu4.add(PreOrden);

        PostOrden.setText("RecorridoPostOrden");
        PostOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostOrdenActionPerformed(evt);
            }
        });
        jMenu4.add(PostOrden);

        niveles.setText("Recorrido Por Niveles");
        niveles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelesActionPerformed(evt);
            }
        });
        jMenu4.add(niveles);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     IArbolBusqueda<String, String> arbol = new ArbolMViasBusqueda<>();
    private void InOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InOrdenActionPerformed
        // TODO add your handling code here:
        
        recorridos.setText("" + arbol.recorridoEnInOrden()+"");
    }//GEN-LAST:event_InOrdenActionPerformed

    private void PreOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreOrdenActionPerformed
        // TODO add your handling code here:
        recorridos.setText("" + arbol.recorridoEnPreOrden() + "");
    }//GEN-LAST:event_PreOrdenActionPerformed

    private void PostOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostOrdenActionPerformed
        // TODO add your handling code here:
        recorridos.setText("" +arbol.recorridoEnPostOrden() +"");
    }//GEN-LAST:event_PostOrdenActionPerformed

    private void nivelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nivelesActionPerformed
        // TODO add your handling code here:
        recorridos.setText("" +arbol.recorridoPorNiveles() + "");
    }//GEN-LAST:event_nivelesActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         arbol.insertar(nombre.getText(), significado.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cargar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargar2ActionPerformed
        // TODO add your handling code here:
                arbol.insertar("Gabriel", "El que tiene la fuerza de Dios");
        arbol.insertar("Eloy","El elegido");
        arbol.insertar("Esteban","Victorioso");
        arbol.insertar("Alina","Amiga noble");
        arbol.insertar("Roberto","De fama brillante");
        arbol.insertar("Juan","Pleno de gracia");
        arbol.insertar("Sheila","Celestial");
        arbol.insertar("Leonardo","Fuerte como león ");
        arbol.insertar("María","Señora");
        arbol.insertar("Enzo","Amo de la casa");
        arbol.insertar("Moisés","Salvado de las aguas");
        arbol.insertar("Lucía","Luminosa");
        arbol.insertar("Mateo","Don del señor");
        arbol.insertar("Emma","La que es fuerte");
        arbol.insertar("Fabricio","Hijo de artesano");
        arbol.insertar("Inés","La pura");
        arbol.insertar("Jairo","El que es iluminado");
        arbol.insertar("Daniel","Justicia de Dios");
        arbol.insertar("Carlos","Hombre libre");
        arbol.insertar("Sebastián","Venerable");
        arbol.insertar("Martín","Guerrero");
        arbol.insertar("Angélica","Enviada de Dios");
        arbol.insertar("Rocio","Lágrimas de flor");    
        arbol.insertar("Edmundo","Paz bendita");     
        arbol.insertar("Alejandro","Protector de los hombres");
        arbol.insertar("Eva","La que da vida");
        arbol.insertar("Emanuel","Dios con nosotros");
    }//GEN-LAST:event_cargar2ActionPerformed

    private void eliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar2ActionPerformed
        // TODO add your handling code here:
        String valor = arbol.eliminar(nombre.getText());
    }//GEN-LAST:event_eliminar2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        significado.setText(arbol.buscar(nombre.getText()));
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ÁrbolMVías.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ÁrbolMVías.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ÁrbolMVías.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ÁrbolMVías.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ÁrbolMVías().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem InOrden;
    private javax.swing.JMenuItem PostOrden;
    private javax.swing.JMenuItem PreOrden;
    private javax.swing.JButton cargar2;
    private javax.swing.JButton eliminar2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem niveles;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextArea recorridos;
    private javax.swing.JTextField significado;
    // End of variables declaration//GEN-END:variables
}
