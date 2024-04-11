/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingmachineapp;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author christosstylidis
 */



class VendingMachine {
    private List<Product> products;
    private double moneyHeld;
    private boolean transactionInProgress;
    //constructor
    public VendingMachine(List<Product> products) {
        this.products = products;
        this.moneyHeld = 0;
        this.transactionInProgress = false;
    }

    public void startTransaction() {
        if (!transactionInProgress) {
            transactionInProgress = true;
            System.out.println("Transaction started. Select a product.");
        } else {
            System.out.println("Another transaction is already in progress.");
        }
    }

    public void endTransaction() {
        if (transactionInProgress) {
            transactionInProgress = false;
            System.out.println("Transaction ended.");
            if (moneyHeld > 100) {
                notifyOwner();
            }
            moneyHeld = 0;
        } else {
            System.out.println("No transaction in progress.");
        }
    }

    private void notifyOwner() {
        System.out.println("Money held exceeds the limit. Notify the owner.");
    }

    public void displayProducts() {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getQuantity() > 0) {
                System.out.println(i + ". " + product.getProductName() + " - $" + product.getPrice());
            } else {
                System.out.println(i + ". " + product.getProductName() + " - Not Available");
            }
        }
    }

    public void processUserSelection(int productId, Scanner scanner) {
        if (transactionInProgress) {
            if (productId >= 0 && productId < products.size()) {
                Product selectedProduct = products.get(productId);
                if (selectedProduct.getQuantity() > 0) {
                    double productPrice = selectedProduct.getPrice();
                    System.out.println("Selected product: " + selectedProduct.getProductName() +
                            " - Price: $" + productPrice);
                    Transaction transaction = new Transaction(selectedProduct);
                    processTransaction(transaction, scanner);
                } else {
                    System.out.println("Product not available. Please select another product.");
                    displayProducts();
                    processUserSelection(scanner.nextInt(), scanner);
                }
            } else {
                System.out.println("Invalid product ID. Please try again.");
                displayProducts();
                processUserSelection(scanner.nextInt(), scanner);
            }
        } else {
            System.out.println("No transaction in progress. Please start a transaction.");
        }
    }

    private void processTransaction(Transaction transaction, Scanner scanner) {
        double productPrice = transaction.getSelectedProduct().getPrice();

        while (transaction.getChange() < productPrice) {
            double remainingAmount = productPrice - transaction.getChange();
            transaction.printAmountInserted();
            System.out.println("Remaining Amount: $" + roundToTwoDecimalPlaces(remainingAmount));

            try {
                double coin = scanner.nextDouble();
                transaction.insertCoin(coin);
            } catch (Exception e) {
                System.out.println("Invalid coin. Try again with a valid number.");
                scanner.next(); // Consume invalid input
            }
        }

        // Print the "Amount Inserted" message once
        transaction.printAmountInserted();

        // Check if the user inserted enough money and the rest of the method remains unchanged
        if (transaction.getChange() >= productPrice) {
            System.out.println("Transaction successful. Enjoy your " +
                    transaction.getSelectedProduct().getProductName() + "!");
            transaction.getSelectedProduct().reduceQuantity();
            transaction.calculateChange(productPrice);
        } else {
            System.out.println("Insufficient amount. Transaction canceled.");
        }
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
