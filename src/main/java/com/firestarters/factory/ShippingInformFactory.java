package com.firestarters.factory;

import com.firestarters.models.ShippingInform;

import static com.firestarters.tools.utils.Utils.getRandomNumber;
import static com.firestarters.tools.utils.Utils.getRandomString;

public class ShippingInformFactory {
    public static ShippingInform getShippingInformInstance(){
        ShippingInform shippingInform=new ShippingInform();
        shippingInform.setFirstName(getRandomString(5));
        shippingInform.setLastName(getRandomString(5));
        shippingInform.setStreetAddr(getRandomString(10));
        shippingInform.setCity("Blaj");
        shippingInform.setZip(getRandomNumber(6));
        shippingInform.setTelephone("07"+getRandomNumber(8));
        shippingInform.setCountry("Romania");
        shippingInform.setState("Alba");
        return shippingInform;
    }
}
