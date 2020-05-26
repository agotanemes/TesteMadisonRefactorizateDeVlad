package com.firestarters.models;

import com.firestarters.tools.constants.Constants;
import org.decimal4j.util.DoubleRounder;

import java.util.List;

public class Cart {
    private double subtotal, tax, grandTotal,taxRate;
    List<CartProduct> cartProducts;

    public Cart() {
    }

    public Cart(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
        this.subtotal = getSubtotal();
        this.tax = getTax();
        this.grandTotal = getGrandTotal();
    }
    public Cart(List<CartProduct> cartProducts, double taxRate) {
        this.cartProducts = cartProducts;
        this.taxRate = taxRate;
        this.subtotal = getSubtotal();
        this.tax = getTax();
        this.grandTotal = getGrandTotal();
    }


    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public double getSubtotal() {
        this.subtotal = 0;
        for (CartProduct cartProduct : cartProducts) {
            this.subtotal = this.subtotal + cartProduct.getSubtotal();
        }
        System.out.println("Subtotal !!!!" + subtotal);
        return this.subtotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cartProducts == null) ? 0 : cartProducts.hashCode());
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
        if (cartProducts == null) {
            if (other.cartProducts != null)
                return false;
        } else if (!cartProducts.equals(other.cartProducts))
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
        return "Cart [subtotal=" + subtotal + ", tax=" + tax + ", grandTotal=" + grandTotal + ", cartProducts=" + cartProducts + "]";
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

  public double getTax() {
      return DoubleRounder.round(this.taxRate * this.subtotal,2);
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
