package com.firestarters.models;

import com.firestarters.tools.utils.Constants;

import java.util.Objects;

public class CartProduct {
    String color;
    String size;
    int qty;
    String name;
    double price;
    double subtotal;
    double tax;

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = this.getSubtotal() + this.getSubtotal() * Constants.CART_TAX_RATE;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal() {
        this.subtotal = this.price * this.qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CartProduct [color=" + color + ", size=" + size + ", qty=" + qty + ", name=" + name + ", price=" + price + ", subtotal=" + subtotal + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CartProduct that = (CartProduct)o;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.subtotal, subtotal) == 0 &&
                Objects.equals(color, that.color) &&
                Objects.equals(size, that.size) &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, qty, name, price, subtotal);
    }
}
