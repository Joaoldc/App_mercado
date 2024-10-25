package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

// Classe responsável pela interface gráfica da lista de compras
public class ShoppingListView {
    private JFrame frame; // Janela principal
    private JTable table; // Tabela para exibir os itens
    private DefaultTableModel model; // Modelo da tabela para gerenciar os dados
    private JTextField itemField; // Campo de texto para o nome do item
    private JTextField quantityField; // Campo de texto para a quantidade do item
    private JTextField valueField; // Campo de texto para o valor do item
    private JLabel totalLabel; // Rótulo para exibir o valor total
    private JButton addButton; // Botão para adicionar item
    private JButton deleteButton; // Botão para excluir item
    private JButton loadButton; // Botão para carregar lista

    // Construtor que inicializa a interface gráfica
    public ShoppingListView() {
        frame = new JFrame("Lista de Compras");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2400, 1800);

        // Cria o modelo da tabela com as colunas necessárias
        model = new DefaultTableModel(new Object[]{"Nº", "Item", "Quantidade", "Valor"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Desabilita edição da coluna de numeração
            }
        };
        table = new JTable(model); // Cria a tabela com o modelo
        JScrollPane scrollPane = new JScrollPane(table); // Adiciona a tabela a um painel de rolagem

        // Cria os campos de texto para entrada de dados
        itemField = new JTextField(15);
        quantityField = new JTextField(5);
        valueField = new JTextField(7);

        // Cria os botões
        addButton = new JButton("Adicionar");
        deleteButton = new JButton("Excluir");
        loadButton = new JButton("Carregar Lista");

        totalLabel = new JLabel("Valor Total: 0.00"); // Cria o rótulo para exibir o valor total

        // Cria o painel de entrada e adiciona os componentes
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Item:"));
        inputPanel.add(itemField);
        inputPanel.add(new JLabel("Quantidade:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Valor:"));
        inputPanel.add(valueField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(loadButton);

        // Cria o painel para o rótulo de valor total e adiciona o rótulo
        JPanel totalPanel = new JPanel();
        totalPanel.add(totalLabel);

        // Adiciona os painéis e o painel de rolagem à janela principal
        frame.getContentPane().add(scrollPane, "Center");
        frame.getContentPane().add(inputPanel, "South");
        frame.getContentPane().add(totalPanel, "North");

        frame.setVisible(true);
    }

    // Métodos para acessar os componentes da interface gráfica
    public String getItem() {
        return itemField.getText();
    }

    public String getQuantity() {
        return quantityField.getText();
    }

    public String getValue() {
        return valueField.getText();
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public void clearFields() {
        itemField.setText("");
        quantityField.setText("");
        valueField.setText("");
    }

    public void setTotalLabel(String text) {
        totalLabel.setText(text);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addLoadButtonListener(ActionListener listener) {
        loadButton.addActionListener(listener);
    }
}
