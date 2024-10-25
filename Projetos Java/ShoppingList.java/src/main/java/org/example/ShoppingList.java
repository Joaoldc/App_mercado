package org.example;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Classe responsável pela lógica da lista de compras
public class ShoppingList {
    private DefaultTableModel model; // Modelo da tabela para gerenciar os dados
    private String currentMonth; // Mês atual para verificar mudanças

    // Construtor que recebe o modelo da tabela
    public ShoppingList(DefaultTableModel model) {
        this.model = model;
        this.currentMonth = getCurrentMonth();
    }

    // Método para adicionar um item à lista de compras
    public void addItem(String item, int quantity, double value) {
        int rowCount = model.getRowCount() + 1; // Obtém a numeração do próximo item
        // Adiciona uma nova linha ao modelo da tabela
        model.addRow(new Object[]{rowCount, item, quantity, value * quantity});
        updateTotal(); // Atualiza o valor total
        saveToFile(); // Salva a lista no arquivo
    }

    // Método para excluir um item da lista de compras
    public void deleteItem(int selectedRow) {
        if (selectedRow != -1) { // Verifica se uma linha foi selecionada
            model.removeRow(selectedRow); // Remove a linha selecionada
            updateItemNumbers(); // Atualiza a numeração dos itens
            updateTotal(); // Atualiza o valor total
            saveToFile(); // Salva a lista no arquivo
        }
    }

    // Método para calcular o valor total da lista de compras
    public double getTotal() {
        double total = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            total += (double) model.getValueAt(i, 3); // Soma o valor de cada item
        }
        return total;
    }

    // Método para atualizar o valor total exibido
    private void updateTotal() {
        double total = getTotal(); // Calcula o valor total
        System.out.println("Valor Total: " + String.format("%.2f", total)); // Exibe o valor total no console (pode ser removido)
    }

    // Método para atualizar a numeração dos itens na tabela
    private void updateItemNumbers() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0); // Atualiza a numeração de cada item
        }
    }

    // Método para obter o mês atual como uma string
    private String getCurrentMonth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        return dateFormat.format(new Date());
    }

    // Método para salvar a lista de compras no arquivo do mês atual
    private void saveToFile() {
        String newMonth = getCurrentMonth();
        if (!newMonth.equals(currentMonth)) {
            currentMonth = newMonth; // Atualiza o mês atual se mudou
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(currentMonth + ".txt", false))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.println(model.getValueAt(i, 0) + "," + model.getValueAt(i, 1) + "," + model.getValueAt(i, 2) + "," + model.getValueAt(i, 3));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar a lista de compras do arquivo do mês atual
    public void loadFromFile() {
        String fileName = getCurrentMonth() + ".txt";
        File file = new File(fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                model.setRowCount(0); // Limpa a tabela antes de carregar os dados
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    model.addRow(new Object[]{Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3])});
                }
                updateTotal(); // Atualiza o valor total após carregar os dados
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
