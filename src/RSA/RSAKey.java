package RSA;

import math.MathUtils;
import math.modular.calcul.ModularProcess;
import math.modular.number.ModularNum;
import math.primenumbers.PrimeNumber;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class RSAKey {

    private final ModularNum publicKey;
    private final ModularNum privateKey;
    private static final BigInteger MIN_INDEX_PRIME_NUMBER = new BigInteger("1");
    private static final BigInteger MAX_INDEX_PRIME_NUMBER = new BigInteger("5");


    public RSAKey(){
        PrimeNumber primeNumber = new PrimeNumber();

        //Generation du modulo
        BigInteger firstElemMod = new BigInteger(Integer.toString(primeNumber.getPRIME_NUMBERByIndex(randomNumberBetween(MIN_INDEX_PRIME_NUMBER, MAX_INDEX_PRIME_NUMBER))));
        BigInteger secondElemMod;
        do{
            secondElemMod = new BigInteger(Integer.toString(primeNumber.getPRIME_NUMBERByIndex(randomNumberBetween(MIN_INDEX_PRIME_NUMBER, MAX_INDEX_PRIME_NUMBER))));
        }while(secondElemMod.compareTo(firstElemMod) == 0);
        BigInteger mod = firstElemMod.multiply(secondElemMod);

        //Modulo pour la puissance de chiffrement et de dechiffrement
        BigInteger eulerMod = ModularProcess.euler(firstElemMod, secondElemMod);

        //Generation de la cle public
        this.publicKey = new ModularNum(generateRandomPow(eulerMod), mod);

        //Generation de la cle privee
        ModularNum publicKeyInvert = ModularProcess.modularInv(new ModularNum(this.publicKey.getVal(), eulerMod));
        this.privateKey = new ModularNum(publicKeyInvert.getVal(), mod);
    }

    public ModularNum getPublicKey() {
        return this.publicKey;
    }
    public ModularNum getPrivateKey(){return this.privateKey;}

    private BigInteger randomNumberBetween(BigInteger min, BigInteger max){
        SecureRandom random = new SecureRandom();
        BigInteger bigInteger = max.subtract(min);
        int len = max.bitLength();
        BigInteger res = new BigInteger(len, random);
        if (res.compareTo(min) < 0)
            res = res.add(min);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(min);
        return res;
    }

    public BigInteger generateRandomPow(BigInteger eulerMod){
        BigInteger pow;
        do{
           pow = randomNumberBetween(BigInteger.ZERO, eulerMod);
        }while(MathUtils.PGCD(pow, eulerMod).compareTo(new BigInteger("1")) != 0);

        return pow;
    }

    @Override
    public String toString(){
        return "public key : " + this.publicKey + "\nprivate key : " + this.privateKey;
    }
}
