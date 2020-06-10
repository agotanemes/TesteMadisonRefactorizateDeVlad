package com.firestarters.models;

import org.decimal4j.util.DoubleRounder;

import java.util.List;

public class Cart {
    private double subtotal, tax, grandTotal, taxRate;

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    List<Product> products;

    public Cart() {
    }

    public Cart(List<Product> products) {
        this.products = products;
    }

    public Cart(List<Product> products, double taxRate) {
        this.products = products;
        this.taxRate = taxRate;
        this.subtotal = getSubtotal();
        this.tax = getTax();
        this.grandTotal = getGrandTotal();
    }

    public List<Product> getCartProducts() {
        return products;
    }

    public void setCartProducts(List<Product> products) {
        this.products = products;
    }

    public double getSubtotal() {
        this.subtotal = 0;
        products.forEach(cartProduct -> this.subtotal = this.subtotal + cartProduct.getPrice() * cartProduct.getQty());
        return this.subtotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((products == null) ? 0 : products.hashCode());
        long temp;
        temp = Double.doubleToLongBits(grandTotal);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(subtotal);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tax);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cart other = (Cart)obj;
        if (products == null) {
            if (other.products != null)
                return false;
        } else if (!products.equals(other.products))
            return false;
        if (Double.doubleToLongBits(grandTotal) != Double.doubleToLongBits(other.grandTotal))
            return false;
        if (Double.doubleToLongBits(subtotal) != Double.doubleToLongBits(other.subtotal))
            return false;
        if (Double.doubleToLongBits(tax) != Double.doubleToLongBits(other.tax))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cart [subtotal=" + subtotal + ", tax=" + tax + ", grandTotal=" + grandTotal +  ", products=" + products + "]";
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        //return Math.round(this.taxRate * this.subtotal);
        return  DoubleRounder.round(this.taxRate * this.subtotal,2);
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getGrandTotal() {
        return this.subtotal + this.tax;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

}
