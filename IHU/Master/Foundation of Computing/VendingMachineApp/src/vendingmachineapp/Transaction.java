/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingmachineapp;

/**
 *
 * @author christosstylidis
 */
class Transaction {
    private Product selectedProduct;
    private double change;

    public Transaction(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
        this.change = 0;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public double getChange() {
        return change;
    }

    public void insertCoin(double coin) {
        // Summarize the inserted coins and inform the user dynamically
        change += coin;
    }

    public double calculateChange(double productPrice) {
        double remainingChange = change - productPrice;
        System.out.println("Change: $" + roundToTwoDecimalPlaces(remainingChange));
        return remainingChange;
    }

    public void printAmountInserted() {
        System.out.println("Amount Inserted: $" + roundToTwoDecimalPlaces(change));
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
