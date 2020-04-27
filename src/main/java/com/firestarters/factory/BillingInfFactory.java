package com.firestarters.factory;

import com.firestarters.models.BillingInf;

import static com.firestarters.tools.utils.Utils.getRandomNumber;
import static com.firestarters.tools.utils.Utils.getRandomString;

public class BillingInfFactory {
    public static BillingInf getBillingInfInstance(){
        BillingInf billingInf=new BillingInf();
        billingInf.setFirstN(getRandomString(5));
        billingInf.setMiddleN(getRandomString(5));
        billingInf.setLastN(getRandomString(5));
        billingInf.setEmailAdr(getRandomString(7)+"@yahoo.com");
        billingInf.setAddress(getRandomString(10));
        billingInf.setCity("Blaj");
        billingInf.setZip(getRandomNumber(6));
        billingInf.setTelephone(getRandomNumber(10));
        billingInf.setCountry("Romania");
        billingInf.setState("Alba");
        return billingInf;
    }
}
