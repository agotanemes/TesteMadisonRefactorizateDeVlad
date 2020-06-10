package com.firestarters.models;

import java.util.List;

public class Wishlist {
    List<Product> products;

    public Wishlist(List<Product> products) {
        this.products = products;
    }

    public Wishlist() {
    }

    public List<Product> getWishlistProducts() {
        return products;
    }

    public void setWishlistProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((products == null) ? 0 : products.hashCode());
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
        Wishlist other = (Wishlist)obj;
        if (products == null) {
            if (other.products != null)
                return false;
        } else if (!products.equals(other.products))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Wishlist [products=" + products + "]";
    }
}
