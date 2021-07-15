package math.modular.calcul;

import math.MathUtils;
import math.modular.number.ModularNum;

import java.math.BigInteger;

public class ModularProcess {

   public static ModularNum modularInv(ModularNum num){
       try {
        if(ModularProcess.isInvertible(num)){
            BigInteger r1 = num.getModulo();
            BigInteger r2 = num.getVal();
            BigInteger newR;
            BigInteger q;
            BigInteger u1 = new BigInteger("1"), u2 = new BigInteger("0"), newU;
            BigInteger v1 = new BigInteger("0"), v2 = new BigInteger("1"), newV;
            while ((r1.remainder(r2)).compareTo(new BigInteger("0")) != 0){
                newR = r1.remainder(r2);
                q = r1.divide(r2);
                newU = u1.subtract(u2.multiply(q));
                newV = v1.subtract(v2.multiply(q));
                r1 = r2;
                r2 = newR;
                u1 = u2;
                u2 = newU;
                v1 = v2;
                v2 = newV;
            }
            return new ModularNum(v2, num.getModulo());
        }
        else{
          throw new NotInvertibleNumber();
        }
       }
       catch (NotInvertibleNumber e) {
           e.printStackTrace();
       }
       return null;
    }

    public static boolean isInvertible (ModularNum num){
       if ((MathUtils.PGCD(num.getModulo(), num.getVal())).compareTo(new BigInteger("1")) != 0){
           return false;
       }
       return true;
    }

    public static ModularNum modPow(ModularNum num, BigInteger exp){
        int [] decomp = MathUtils.decompExp(exp.intValue());
        int currentPow = 1;
        ModularNum currentPowNumber = new ModularNum(num.getVal(), num.getModulo());
        ModularNum result = new ModularNum(new BigInteger("1"), num.getModulo());
        for(Integer decompPow : decomp){
            while(currentPow < decompPow){
                currentPow *= 2;
                currentPowNumber = currentPowNumber.multiply(currentPowNumber);
            }
            result = result.multiply(currentPowNumber);
        }

        return result;
    }

    public static BigInteger euler(BigInteger primeNumber1, BigInteger primeNumber2){
        return (primeNumber1.subtract(new BigInteger("1"))).multiply(primeNumber2.subtract(new BigInteger("1")));
    }
}
