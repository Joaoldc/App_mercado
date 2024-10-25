package org.example;

// src/Main.java

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

// Classe principal que inicia a aplicação
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShoppingListView view = new ShoppingListView(); // Inicializa a visão
            DefaultTableModel model = view.getModel(); // Obtém o modelo da visão
            ShoppingList shoppingList = new ShoppingList(model); // Inicializa o modelo com o modelo da visão
            new ShoppingListController(shoppingList, view); // Inicializa o controlador com o modelo e a visão
        });
    }
}
