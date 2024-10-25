package org.example;

// src/controller/ShoppingListController.java

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe responsável por gerenciar a interação entre o modelo e a visão
public class ShoppingListController {
    private ShoppingList model;
    private ShoppingListView view;

    public ShoppingListController(ShoppingList model, ShoppingListView view) {
        this.model = model;
        this.view = view;

        this.view.addAddButtonListener(new AddButtonListener());
        this.view.addDeleteButtonListener(new DeleteButtonListener());
        this.view.addLoadButtonListener(new LoadButtonListener());

        this.model.loadFromFile();
        updateTotalLabel();
    }

    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String item = view.getItem();
                int quantity = Integer.parseInt(view.getQuantity());
                double value = Double.parseDouble(view.getValue());
                model.addItem(item, quantity, value);
                view.clearFields();
                updateTotalLabel();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.");
            }
        }
    }

    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getSelectedRow();
            if (selectedRow != -1) {
                model.deleteItem(selectedRow);
                updateTotalLabel();
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha para excluir.");
            }
        }
    }

    class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.loadFromFile();
            updateTotalLabel();
        }
    }

    private void updateTotalLabel() {
        double total = model.getTotal();
        view.setTotalLabel("Valor Total: " + String.format("%.2f", total));
    }
}

