package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder receipt = new StringBuilder();

        receipt.append("======Printing Orders======\n");
        receipt.append(order.getCustomerName());
        receipt.append(order.getCustomerAddress());

        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            receipt.append(String.format("%s\t%s\t%s\t%s\n",
                    lineItem.getDescription(), lineItem.getPrice(),
                    lineItem.getQuantity(), lineItem.totalAmount()));

            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount += lineItem.totalAmount() + salesTax;
        }

        // prints the state tax
        receipt.append("Sales Tax").append('\t').append(totalSalesTax);

        // print total amount
        receipt.append("Total Amount").append('\t').append(totalAmount);
        return receipt.toString();
    }
}