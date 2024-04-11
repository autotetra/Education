/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vendingmachineapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author christosstylidis
 */



public class VendingMachineApp {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Soda", 1.50, 10));
        products.add(new Product("Chips", 1.00, 10));
        products.add(new Product("Chocolate", 2.00, 0));
        products.add(new Product("Popcorn", 1.30, 10));
        

        VendingMachine vendingMachine = new VendingMachine(products);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Vending Machine!");
        System.out.println("Select an option:");
        System.out.println("1. User");
        System.out.println("2. Admin");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                userMode(vendingMachine, scanner);
                break;
            case 2:
                adminMode(vendingMachine);
                break;
            default:
                System.out.println("Invalid option. Exiting.");
        }
    }

    private static void userMode(VendingMachine vendingMachine, Scanner scanner) {
        vendingMachine.startTransaction();
        vendingMachine.displayProducts();

        System.out.println("Enter the product ID to make a selection:");
        int productId = scanner.nextInt();

        vendingMachine.processUserSelection(productId, scanner);

        vendingMachine.endTransaction();
    }

    private static void adminMode(VendingMachine vendingMachine) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the admin code:");
        String adminCode = scanner.next();

        // For simplicity, let's assume the admin code is "admin123"
        if (adminCode.equals("admin123")) {
            System.out.println("Admin mode activated.");
            // Add admin functionalities here
        } else {
            System.out.println("Invalid admin code. Exiting.");
        }
    }
}
