package math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class MathUtils {

    public static BigInteger PGCD(BigInteger nb1, BigInteger nb2){

        try{
            //Cas nombres négatifs
            if(nb1.compareTo(new BigInteger("0")) < 0 || nb2.compareTo(new BigInteger("0")) < 0){
                throw new NegativeNumberException();
            }

            //Cas ou l'un des deux nombre ou les deux sont égal à 0
            if(nb1.compareTo(new BigInteger("0")) == 0 && nb2.compareTo(new BigInteger("0")) == 0){
                return new BigInteger("0");
            }
            else if(nb1.compareTo(new BigInteger("0")) == 0){
                return nb2;
            }
            else if(nb2.compareTo(new BigInteger("0")) == 0){
                return nb1;
            }

            while (nb1.compareTo(nb2) != 0) {
                if(nb1.compareTo(nb2) > 0)
                    nb1 = nb1.subtract(nb2);
                else
                    nb2 = nb2.subtract(nb1);
            }
            return nb2;
        }
        catch (NegativeNumberException e){
            e.printStackTrace();
        }
        return null;
    }

    public static BigInteger pow(String a, int exp, int base){
        BigInteger result = new BigInteger("1", base);
        BigInteger aBigInt = new BigInteger(a, base);
        for(int i = 0; i < exp; i ++){
            result = result.multiply(aBigInt);
        }
        return new BigInteger(result.toString(), base);
    }

    public static int[] decompExp(int exp){
        ArrayList<Integer> decomp = new ArrayList<>();
        int currentPow = 1;
        decomp.add(currentPow);
        while (currentPow < exp){
            currentPow *= 2;
            decomp.add(currentPow);
        }
        Collections.reverse(decomp);

        ArrayList<Integer> finalDecomp = new ArrayList<>();
        int totalPow = 0;
        for(Integer pow : decomp){
            if(totalPow + pow <= exp){
                totalPow += pow;
                finalDecomp.add(pow);
            }
        }

        Collections.reverse(finalDecomp);
        int[] result = new int[finalDecomp.size()];
        for(int i = 0; i < finalDecomp.size(); i++){
            result[i] = finalDecomp.get(i);
        }
        return result;
    }
}
