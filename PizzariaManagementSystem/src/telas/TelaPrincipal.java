/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import conexaodatabase.ModuloConexao;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author Joao Victor
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     *
     * @param usu
     * @param cargo
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaPrincipal(String usu, String perfil) {
        initComponents();
        LocalDate data = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dataformatada = data.format(myFormatObj);
        dataLabel.setText(String.valueOf(dataformatada));
        conexao = ModuloConexao.connector();
        LocalDateTime datahora = LocalDateTime.now();
        DateTimeFormatter Obj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String datahoraformatada = datahora.format(Obj);
        System.out.println(conexao);
        try {
            String sql = "insert into horarios values(?,?)";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usu);
            pst.setString(2, datahoraformatada);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    private void gerarPdf() {
        Document document = new Document();

        //gerar o documento pdf
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Relatorio.pdf"));
            document.open();
            Date data = new Date();
            DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
            document.add(new Paragraph(formatador.format(data)));
            document.add(new Paragraph("Relátorio de vendas, últimos 30 dias"));
            document.add(new Paragraph(" "));
            // tabela
            PdfPTable tabela = new PdfPTable(6);
            PdfPCell col1 = new PdfPCell(new Paragraph("ID PEDIDO"));
            tabela.addCell(col1);
            PdfPCell col2 = new PdfPCell(new Paragraph("DATA"));
            tabela.addCell(col2);
            PdfPCell col3 = new PdfPCell(new Paragraph("PRODUTO"));
            tabela.addCell(col3);
            PdfPCell col4 = new PdfPCell(new Paragraph("QUANTIDADE"));
            tabela.addCell(col4);
            PdfPCell col5 = new PdfPCell(new Paragraph("PRE UNITÁRIO"));
            tabela.addCell(col5);
            PdfPCell col6 = new PdfPCell(new Paragraph("PRE TOTAL"));
            tabela.addCell(col6);

            double somaValores = 0.0; //variável para armazenar a soma dos valores

            String readLista = "SELECT \n"
                    + "    tbpedido.idPedido,\n"
                    + "    tbpedido.datapedido,\n"
                    + "    tbproduto.nome,\n"
                    + "    itempedido.quantidade,\n"
                    + "    itempedido.precoUnitario,\n"
                    + "    itempedido.valorTotal\n"
                    + "FROM \n"
                    + "    tbpedido\n"
                    + "INNER JOIN \n"
                    + "    itempedido ON tbpedido.idPedido = itempedido.idPedido\n"
                    + "INNER JOIN \n"
                    + "    tbproduto ON itempedido.idProduto = tbproduto.idProduto\n"
                    + "WHERE \n"
                    + "    tbpedido.datapedido >= NOW() - INTERVAL 30 DAY;";

            try {
                conexao = ModuloConexao.connector();
                pst = conexao.prepareStatement(readLista);
                rs = pst.executeQuery();
                while (rs.next()) {
                    tabela.addCell(rs.getString(1));
                    tabela.addCell(rs.getString(2));
                    tabela.addCell(rs.getString(3));
                    tabela.addCell(rs.getString(4));
                    tabela.addCell(rs.getString(5));
                    tabela.addCell(rs.getString(6));
                    //soma os valores totais (coluna 6)
                    somaValores += rs.getDouble(6);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            document.add(tabela);

            //segunda tabela: Resumo com o total de vendas
            PdfPTable resumoTabela = new PdfPTable(2);
            resumoTabela.addCell(new PdfPCell(new Paragraph("VALOR TOTAL DE VENDAS")));
            resumoTabela.addCell(new PdfPCell(new Paragraph(String.format("R$ %.2f", somaValores))));

            document.add(resumoTabela);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            document.close();
        }

        //abrir o documento pdf no leitor padrão do sistema
        try {
            Desktop.getDesktop().open(new File("Relatorio.pdf"));
        } catch (Exception e2) {
            System.out.println(e2);
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

        jDesktopPane1 = new javax.swing.JDesktopPane();
        desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        usuLabel = new javax.swing.JLabel();
        dataLabel = new javax.swing.JLabel();
        cargoLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCliente = new javax.swing.JMenuItem();
        menuUser = new javax.swing.JMenuItem();
        menuEstoque = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuReceitas = new javax.swing.JMenu();
        menuCriarPizza = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuAjuda = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuRelatorio = new javax.swing.JMenuItem();
        menuTrocUsuario = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TelaPrincipal");
        setResizable(false);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 863, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );

        jDesktopPane1.setLayer(desktop, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/2427861_fast food_food_italian food_junk food_pizza_icon (2).png"))); // NOI18N

        usuLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        usuLabel.setText("Usuário");

        dataLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        dataLabel.setText("Data");

        cargoLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        cargoLabel.setText("Cargo");

        jMenu1.setText("Cadastro");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        menuCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuCliente.setText("Clientes");
        menuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClienteActionPerformed(evt);
            }
        });
        jMenu1.add(menuCliente);

        menuUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuUser.setText("Usuários");
        menuUser.setEnabled(false);
        menuUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUserActionPerformed(evt);
            }
        });
        jMenu1.add(menuUser);

        menuEstoque.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuEstoque.setText("Estoque");
        menuEstoque.setEnabled(false);
        menuEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEstoqueActionPerformed(evt);
            }
        });
        jMenu1.add(menuEstoque);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem2.setText("Horários");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Pedidos");

        jMenuItem1.setText("Pedido");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        menuReceitas.setText("Receitas");

        menuCriarPizza.setText("Criar Pizza");
        menuCriarPizza.setEnabled(false);
        menuCriarPizza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCriarPizzaActionPerformed(evt);
            }
        });
        menuReceitas.add(menuCriarPizza);

        jMenuBar1.add(menuReceitas);

        jMenu5.setText("Ajuda");

        menuAjuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuAjuda.setText("Ajuda");
        menuAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAjudaActionPerformed(evt);
            }
        });
        jMenu5.add(menuAjuda);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Opções");

        menuRelatorio.setText("Relatório");
        menuRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioActionPerformed(evt);
            }
        });
        jMenu6.add(menuRelatorio);

        menuTrocUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuTrocUsuario.setText("Trocar usuário");
        menuTrocUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTrocUsuarioActionPerformed(evt);
            }
        });
        jMenu6.add(menuTrocUsuario);

        menuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuSair.setText("Sair");
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });
        jMenu6.add(menuSair);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 53, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cargoLabel)
                            .addComponent(usuLabel)
                            .addComponent(dataLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(127, 127, 127)
                .addComponent(usuLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cargoLabel)
                .addGap(18, 18, 18)
                .addComponent(dataLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1100, 734));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Horarios hora = new Horarios();
        desktop.add(hora);
        hora.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }//GEN-LAST:event_menuSairActionPerformed

    private void menuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClienteActionPerformed
        Clientes cliente = new Clientes();
        cliente.setVisible(true);
        desktop.add(cliente);
    }//GEN-LAST:event_menuClienteActionPerformed

    private void menuUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUserActionPerformed
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        desktop.add(usuario);
    }//GEN-LAST:event_menuUserActionPerformed

    private void menuAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAjudaActionPerformed
        Ajuda ajudar = new Ajuda();
        desktop.add(ajudar);
        ajudar.setVisible(true);
    }//GEN-LAST:event_menuAjudaActionPerformed

    private void menuEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEstoqueActionPerformed
        TelaEstoque estoque = new TelaEstoque();
        desktop.add(estoque);
        estoque.setVisible(true);
    }//GEN-LAST:event_menuEstoqueActionPerformed

    private void menuTrocUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTrocUsuarioActionPerformed
        Login newlogin = new Login();
        this.dispose();
        newlogin.setVisible(true);
    }//GEN-LAST:event_menuTrocUsuarioActionPerformed

    private void menuCriarPizzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCriarPizzaActionPerformed
        CriarPizza criar = new CriarPizza();
        desktop.add(criar);
        criar.setVisible(true);
    }//GEN-LAST:event_menuCriarPizzaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // Chama a tela pedido
        TelaPedido pedido = new TelaPedido();
        desktop.add(pedido);
        pedido.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioActionPerformed
        // emite um relátorio dos últimos 30 dias
        gerarPdf();
    }//GEN-LAST:event_menuRelatorioActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel cargoLabel;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem menuAjuda;
    private javax.swing.JMenuItem menuCliente;
    public static javax.swing.JMenuItem menuCriarPizza;
    public static javax.swing.JMenuItem menuEstoque;
    private javax.swing.JMenu menuReceitas;
    private javax.swing.JMenuItem menuRelatorio;
    private javax.swing.JMenuItem menuSair;
    private javax.swing.JMenuItem menuTrocUsuario;
    public static javax.swing.JMenuItem menuUser;
    public static javax.swing.JLabel usuLabel;
    // End of variables declaration//GEN-END:variables
}
