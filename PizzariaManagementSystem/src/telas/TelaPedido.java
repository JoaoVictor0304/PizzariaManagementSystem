/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package telas;

import java.sql.*;
import conexaodatabase.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

//importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Joao Victor
 */
public class TelaPedido extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;

    /**
     * Creates new form TelaPedido
     */
    public TelaPedido() {
        initComponents();
        conexao = ModuloConexao.connector();

        txtPedidoId.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                pesquisarItemPedido();  // Chama o método quando um valor é inserido
            }

            public void removeUpdate(DocumentEvent e) {
                pesquisarItemPedido();  // Chama o método quando um valor é removido
            }

            public void changedUpdate(DocumentEvent e) {
                pesquisarItemPedido();  // Chama o método quando há mudanças (ex.: estilo)
            }
        });
    }

    //método para pesquisar clientes pelo nome com filtro
    private void pesquisarCliente() {
        String sql = "select idcliente as id, nomecli as nome, endcli as endereço, fonecli as fone, emailcli as email from cliente where nomecli like ? or fonecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteúdo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da String sql
            pst.setString(1, txtCliPesquisar.getText() + "%");
            pst.setString(2, txtCliPesquisar.getText() + "%");

            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2cml.jar para preencher a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //métodos para setar os campos do formulário com o conteúdo da tabela
    private void setarCamposCliente() {
        int setar = tblClientes.getSelectedRow();
        txtIdCliente.setText(tblClientes.getModel().getValueAt(setar, 0).toString());

    }

    // método para setar os campos id e nome com o usuário atual logado
    private void setarCamposUsuario() {
        UsuarioAtual session = UsuarioAtual.getSession();
        if (session != null) {
            txtIdUsuario.setText(String.valueOf(session.getIdUsuario()));
            txtNomeUsuario.setText(session.getUsername());
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum usuário logado");
        }
    }

    //método para criar um pedido
    private void criarPedido() {
        String sql = "insert into tbpedido(tipoPedido, status, idcliente, idUsuarios, metodoPagamento, observacoes) values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboTipoPedido.getSelectedItem().toString());
            pst.setString(2, cboStatus.getSelectedItem().toString());
            pst.setString(3, txtIdCliente.getText());
            pst.setString(4, txtIdUsuario.getText());
            pst.setString(5, cboPagamento.getSelectedItem().toString());
            pst.setString(6, txtObs.getText());

            if ((txtIdCliente.getText().isEmpty()) || txtIdUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos do pedido");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Pedido criado");
                    txtIdCliente.setText("");
                    txtObs.setText("");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para alterar pedido
    private void alterarPedido() {
        String sql = "update tbpedido set tipoPedido = ?, status = ?, idcliente = ?, idUsuarios = ?, metodoPagamento = ?, observacoes = ? where idPedido = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cboTipoPedido.getSelectedItem().toString());
            pst.setString(2, cboStatus.getSelectedItem().toString());
            pst.setString(3, txtIdCliente.getText());
            pst.setString(4, txtIdUsuario.getText());
            pst.setString(5, cboPagamento.getSelectedItem().toString());
            pst.setString(6, txtObs.getText());
            pst.setString(7, txtIdPedido.getText());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados do pedido alterado");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para excluir pedido
    private void excluirPedido() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este pedido ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbpedido where idPedido = ?";
            String consulta = "SELECT COUNT(*) AS total FROM itempedido WHERE idPedido = ?;";
            try {
                pst2 = conexao.prepareStatement(consulta);
                pst2.setString(1, txtIdPedido.getText());
                rs = pst2.executeQuery();

                if (rs.next() && rs.getInt("total") > 0) {
                    JOptionPane.showMessageDialog(null, "É necessário excluir os itens do pedido antes de excluir o pedido!");
                } else {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtIdPedido.getText());

                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Pedido removido");
                        txtIdPedido.setText(null);
                        txtDataHora.setText(null);
                        txtIdCliente.setText(null);
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //método para pesquisar pedido
    private void pesquisarPedido() {
        String sql = "select idPedido as id, datapedido as DataHora, tipoPedido as Tipo, status as Status, idcliente as idCliente, idUsuarios as idUsuario, metodoPagamento as MetodoPagamento, observacoes as Observações from tbpedido where idPedido like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteúdo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da String sql
            pst.setString(1, txtPesqPedido.getText() + "%");

            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2cml.jar para preencher a tabela
            tblPesqPedido.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para setar os campo da tabela pedido para o formulário
    private void setarCamposPedido() {
        int setar = tblPesqPedido.getSelectedRow();
        txtIdPedido.setText(tblPesqPedido.getModel().getValueAt(setar, 0).toString());
        txtDataHora.setText(tblPesqPedido.getModel().getValueAt(setar, 1).toString());
        cboTipoPedido.setSelectedItem(tblPesqPedido.getModel().getValueAt(setar, 2).toString());
        cboStatus.setSelectedItem(tblPesqPedido.getModel().getValueAt(setar, 3).toString());
        txtIdCliente.setText(tblPesqPedido.getModel().getValueAt(setar, 4).toString());
        txtIdUsuario.setText(tblPesqPedido.getModel().getValueAt(setar, 5).toString());
        cboPagamento.setSelectedItem(tblPesqPedido.getModel().getValueAt(setar, 6).toString());
        txtObs.setText(tblPesqPedido.getModel().getValueAt(setar, 7).toString());
        txtPedidoId.setText(tblPesqPedido.getModel().getValueAt(setar, 0).toString());
    }

    //método para pesquisar pizza pelo nome com filtro
    private void pesquisarPizza() {
        String sql = "select idProduto as id, nome as Nome, descricao as Descrição, precoBrotinho as PreçoBrotinho, precoGrande as PreçoGrande from tbproduto where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteúdo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da String sql
            pst.setString(1, txtPesqPizza.getText() + "%");

            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2cml.jar para preencher a tabela
            tblPizza.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setarCamposProduto() {
        int setar = tblPizza.getSelectedRow();
        txtIdProduto.setText(tblPizza.getModel().getValueAt(setar, 0).toString());
        txtNomeProduto.setText(tblPizza.getModel().getValueAt(setar, 1).toString());
    }

    private void incluirItem() {
        String sql = "insert into itempedido (idPedido, idProduto, tamanho, quantidade, precoUnitario) values (?, ?, ?, ?, ?)";
        String ingrediente = "SELECT pi.idIngrediente, pi.quantidade AS quantidade_necessaria, e.quantidade AS quantidade_estoque FROM tbprodutoingrediente pi JOIN estoque e ON pi.idIngrediente = e.idIngrediente WHERE pi.idProduto = ? AND pi.quantidade > e.quantidade;";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPedidoId.getText());
            pst.setString(2, txtIdProduto.getText());
            pst.setString(3, cboTamanho.getSelectedItem().toString());
            pst.setString(4, txtQtd.getText());
            pst.setString(5, txtPrecoUni.getText());

            pst2 = conexao.prepareStatement(ingrediente);
            pst2.setString(1, txtIdProduto.getText());

            if ((txtPedidoId.getText().isEmpty()) || (txtIdProduto.getText().isEmpty()) || (txtQtd.getText().isEmpty()) || (txtPrecoUni.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            } else {
                rs = pst2.executeQuery();
                //verifica se tem ingrediente suficiente no estoque para inserir o produto ao pedido
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Estoque insuficiente para o ingrediente ID: " + rs.getString("idIngrediente"));
                } else {
                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Item incluido no pedido");
                        atualizaEstoque();//método para atualizar estoque
                        txtIdProduto.setText("");
                        txtNomeProduto.setText("");
                        txtQtd.setText("");
                        txtPrecoUni.setText("");
                        pesquisarItemPedido();//chama o método de pesquisar o item do pedido
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterarItemPedido() {
        String sql = "update itempedido set idPedido = ?, idProduto = ?, tamanho = ?, quantidade = ?, precoUnitario = ? where idItemPedido = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPedidoId.getText());
            pst.setString(2, txtIdProduto.getText());
            pst.setString(3, cboTamanho.getSelectedItem().toString());
            pst.setString(4, txtQtd.getText());
            pst.setString(5, txtPrecoUni.getText());
            pst.setString(6, txtItemPedido.getText());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados do item do pedido alterado");
                pesquisarItemPedido();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluirItemPedido() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este item do pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from itempedido where idItemPedido = ?";

            try {

                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtItemPedido.getText());

                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Item excluído do pedido");
                    txtIdProduto.setText("");
                    txtItemPedido.setText("");
                    txtNomeProduto.setText("");
                    txtQtd.setText("");
                    txtPrecoUni.setText("");
                    pesquisarItemPedido();

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //método para pesquisar item pedido
    private void pesquisarItemPedido() {
        String sql = "SELECT itemPedido.idItemPedido, itemPedido.idPedido, itemPedido.idProduto, tbproduto.nome, itemPedido.tamanho, itemPedido.quantidade, itemPedido.precoUnitario, itemPedido.valorTotal FROM itempedido INNER JOIN tbproduto ON itemPedido.idProduto = tbproduto.idProduto WHERE itemPedido.idPedido = ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteúdo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da String sql
            pst.setString(1, txtPedidoId.getText());
            if (!txtPedidoId.getText().trim().isEmpty()) {

                rs = pst.executeQuery();
                //a linha abaixo usa a biblioteca rs2cml.jar para preencher a tabela
                tblItemPedido.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setarCamposItemPedido() {
        int setar = tblItemPedido.getSelectedRow();
        txtItemPedido.setText(tblItemPedido.getModel().getValueAt(setar, 0).toString());
        txtPedidoId.setText(tblItemPedido.getModel().getValueAt(setar, 1).toString());
        txtIdProduto.setText(tblItemPedido.getModel().getValueAt(setar, 2).toString());
        txtNomeProduto.setText(tblItemPedido.getModel().getValueAt(setar, 3).toString());
        cboTamanho.setSelectedItem(tblItemPedido.getModel().getValueAt(setar, 4).toString());
        txtQtd.setText(tblItemPedido.getModel().getValueAt(setar, 5).toString());
        txtPrecoUni.setText(tblItemPedido.getModel().getValueAt(setar, 6).toString());
    }

    //método para atualizar estoque assim que um produto por inserido ao pedido
    private void atualizaEstoque() {
        String sql = "UPDATE estoque e JOIN tbprodutoingrediente pi ON e.idIngrediente = pi.idIngrediente SET e.quantidade = e.quantidade - (pi.quantidade * ?) WHERE pi.idProduto = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtQtd.getText());
            pst.setString(2, txtIdProduto.getText());

            pst.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdPedido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDataHora = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboTipoPedido = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboStatus = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        txtNomeUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboPagamento = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtPesqPedido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesqPedido = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtObs = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtPesqPizza = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        txtNomeProduto = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cboTamanho = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtQtd = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtPrecoUni = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPizza = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        txtPedidoId = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblItemPedido = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtItemPedido = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnAlterarPedido = new javax.swing.JButton();
        btnExcluirPedido = new javax.swing.JButton();

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Pedido");
        setMaximumSize(new java.awt.Dimension(863, 666));
        setMinimumSize(new java.awt.Dimension(863, 666));
        setPreferredSize(new java.awt.Dimension(863, 666));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("id pedido");

        txtIdPedido.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Data/Hora");

        txtDataHora.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtIdPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Tipo pedido");

        cboTipoPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Delivery", "Balcão" }));

        jLabel4.setText("Status pedido");

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aguardando", "Em Preparação", "Pronto", "Entregue" }));

        jLabel5.setText("id Cliente");

        txtIdCliente.setEnabled(false);

        jLabel6.setText("Usuário");

        txtIdUsuario.setEnabled(false);

        txtNomeUsuario.setEnabled(false);

        jLabel7.setText("Pagamento");

        cboPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Cartão", "Pix" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Pesquisar pedido");

        txtPesqPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqPedidoKeyReleased(evt);
            }
        });

        tblPesqPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "DataHora", "TipoPedido", "Status", "idCliente", "idUsuário", "MétodoPagamento", "Observações"
            }
        ));
        tblPesqPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPesqPedidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPesqPedido);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/find.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtPesqPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesqPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setText("Observações");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Pesquisar cliente");

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/find.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "Nome", "Endereço", "Fone", "Email"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Items do pedido");

        txtPesqPizza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesqPizzaActionPerformed(evt);
            }
        });
        txtPesqPizza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqPizzaKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Pesquisar produto");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/find.png"))); // NOI18N

        jLabel16.setText("id produto");

        txtIdProduto.setEnabled(false);

        txtNomeProduto.setEnabled(false);

        jLabel17.setText("Tamanho");

        cboTamanho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Brotinho", "Grande" }));

        jLabel18.setText("Quantidade");

        jLabel19.setText("Preço unitário R$");

        tblPizza.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "Nome", "Descrição", "PreçoBroto", "PreçoGrande"
            }
        ));
        tblPizza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPizzaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPizza);

        jLabel21.setText("id pedido");

        txtPedidoId.setEnabled(false);

        jButton4.setText("Adicionar item");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Alterar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Excluir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tblItemPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idItemProduto", "idPedido", "idProduto", "Nome", "Tamanho", "Quantidade", "PreçoUnitário", "Preçototal"
            }
        ));
        tblItemPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemPedidoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblItemPedido);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Itens do pedido");
        jLabel22.setToolTipText("");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/printer.png"))); // NOI18N
        jButton7.setToolTipText("Imprimir pedido");

        jLabel20.setText("id Item Pedido");

        txtItemPedido.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecoUni, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7))
                    .addComponent(jLabel13)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPedidoId, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtItemPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesqPizza, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtPedidoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(txtItemPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPesqPizza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(cboTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtPrecoUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton4)
                                    .addComponent(jButton5)
                                    .addComponent(jButton6)))
                            .addComponent(jButton7))))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jButton1.setText("Criar Pedido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAlterarPedido.setText("Alterar");
        btnAlterarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarPedidoActionPerformed(evt);
            }
        });

        btnExcluirPedido.setText("Excluir");
        btnExcluirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtObs))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterarPedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluirPedido)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cboPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(btnAlterarPedido)
                            .addComponent(btnExcluirPedido)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        setBounds(0, 0, 863, 655);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesqPizzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesqPizzaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesqPizzaActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // método para pesquisar cliente
        pesquisarCliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // método para setar o campo id cliente para o formulário
        setarCamposCliente();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // método para criar pedido
        criarPedido();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // método para setar os campos id e nome com o usuário atual logado
        setarCamposUsuario();
    }//GEN-LAST:event_formInternalFrameActivated

    private void tblPesqPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPesqPedidoMouseClicked
        // método para setar os campos da tabela pedido e colocar no formulário
        setarCamposPedido();
    }//GEN-LAST:event_tblPesqPedidoMouseClicked

    private void txtPesqPizzaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqPizzaKeyReleased
        // método para pesquisar pizza
        pesquisarPizza();
    }//GEN-LAST:event_txtPesqPizzaKeyReleased

    private void tblPizzaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPizzaMouseClicked
        // método para setar os campos do produto
        setarCamposProduto();
    }//GEN-LAST:event_tblPizzaMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // método para inserir item ao pedido
        incluirItem();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtPesqPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqPedidoKeyReleased
        // método para pesquisar pedido
        pesquisarPedido();
    }//GEN-LAST:event_txtPesqPedidoKeyReleased

    private void btnAlterarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarPedidoActionPerformed
        // método para alterar os dados do pedido
        alterarPedido();
    }//GEN-LAST:event_btnAlterarPedidoActionPerformed

    private void btnExcluirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirPedidoActionPerformed
        // método para excluir pedido
        excluirPedido();
    }//GEN-LAST:event_btnExcluirPedidoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // método para alterar os dados do item do pedido
        alterarItemPedido();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblItemPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemPedidoMouseClicked
        // método para setar os campos da tabela item pedido com o formulário
        setarCamposItemPedido();
    }//GEN-LAST:event_tblItemPedidoMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // método para excluir um item do pedido
        excluirItemPedido();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarPedido;
    private javax.swing.JButton btnExcluirPedido;
    private javax.swing.JComboBox<String> cboPagamento;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JComboBox<String> cboTamanho;
    private javax.swing.JComboBox<String> cboTipoPedido;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblItemPedido;
    private javax.swing.JTable tblPesqPedido;
    private javax.swing.JTable tblPizza;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtDataHora;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtItemPedido;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JTextField txtObs;
    private javax.swing.JTextField txtPedidoId;
    private javax.swing.JTextField txtPesqPedido;
    private javax.swing.JTextField txtPesqPizza;
    private javax.swing.JTextField txtPrecoUni;
    private javax.swing.JTextField txtQtd;
    // End of variables declaration//GEN-END:variables
}
