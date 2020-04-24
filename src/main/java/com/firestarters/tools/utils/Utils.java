package com.firestarters.tools.utils;

import org.apache.commons.lang3.RandomStringUtils;

import static org.apache.commons.lang3.RandomStringUtils.*;
//import org.jcp.xml.dsig.internal.SignerOutputStream;

public class Utils {
    //generate random string
    public static String getRandomString(){
        String s= RandomStringUtils.randomAlphabetic(6);
        return s;
    };


    public static String stringReplace(String s){
        //System.out.println(s);
        String s1=s.replace(" $US","").replace("$", "");
       //System.out.println(s);
        String s2=s1.replace(",","");
        return s2;

    }
    public static double convertStringToDouble(String s){
        //System.out.println(s);
        Double doubleVal = Double.parseDouble(s);
        return doubleVal;
   }
   public static double extractNumberFromString(String str){
        double nr=0;
       String stripedValue = (str.replaceAll("[\\s+a-zA-Z :]",""));
       String stringValue1=stringReplace(stripedValue);
       nr = Double.parseDouble(stringValue1);
        return nr;
   }
   public static String extractNumberFromStrinAsString(String str){
       String stripedValue = (str.replaceAll("[\\s+a-zA-Z :]",""));
       String stringValue1=stripedValue.replace(":","");
       return stringValue1;
   }
   public static String[] splitStringBySpace(String str){
       String[] splited = str.split("\\s+");
       return splited;
   }
   public static String[] splitStringByComma(String str){
        String[] splited=str.split(",");
        return splited;
   }
   public static String eliminateStaces(String s){
        return s.replace(" ","");
   }

    public static void main(String[] args) {
        String stringToStrip="radius: -0.118,$211 zone";
        extractNumberFromString(stringToStrip);
        String str = "Hello I'm your String";
        String [] splited=splitStringBySpace(str);
        for(int i=0;i<splited.length;i++){
            System.out.println(splited[i]);
        }
        String[] splitEnter=splitByEnter(str);
        System.out.println(splitEnter.length);
        String tel=extractNumberFromStrinAsString("T: 0755096274");
        System.out.println(tel);
        System.out.println(tel.length());
        String bycomma="agi ,bla ,bla ,bla";
        System.out.println((splitStringByComma(bycomma)).length);
        String[] splitcomma=splitStringByComma(bycomma);
        System.out.println(splitcomma[0].length());

    }
    public static String[] splitByEnter(String s){
        String lines[] = s.split("\\r?\\n");
        return lines;
    }



}
