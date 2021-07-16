package com.cryptographie;

import RSA.RSAPerson;
import java.math.BigInteger;

public class Main {

    //function modPow need to be optimized (ModularNum.multiply() in particular)!!

    public static void main(String[] args){


        RSAPerson nico = new RSAPerson("nico");
        RSAPerson elliot = new RSAPerson("elliot");
        String message = "hel";
        BigInteger cryptTest = nico.encryptTextTo(message, elliot);
        System.out.println("Message crypté : " + cryptTest);
        String decyptTest = elliot.decrypteText(cryptTest);
        System.out.println("Message décrypté : " + decyptTest);

    }
}
