package math.modular.number;

import java.math.BigInteger;

public class ModularNum {

    private BigInteger val;
    private BigInteger modulo;

    public ModularNum(BigInteger val, BigInteger modulo){
        try{
            if(modulo.compareTo(new BigInteger("0")) < 0){
                throw new InvalidModuloException();
            }
            while(val.compareTo(new BigInteger("0")) < 0){
                val = val.add(modulo);
            }
            while(val.compareTo(modulo) > 0){
                val = val.subtract(modulo);
            }
            this.val = val;
            this.modulo = modulo;
        }
        catch (InvalidModuloException e){
            e.printStackTrace();
        }
    }

    public BigInteger getVal() {
        return val;
    }
    public BigInteger getModulo() {
        return modulo;
    }

    public ModularNum multiply(ModularNum num2){
        try{
            if(this.modulo.compareTo(num2.getModulo()) != 0){
                throw new InvalidModuloException();
            }
            return new ModularNum(this.val.multiply(num2.getVal()), this.getModulo());
        }
        catch(InvalidModuloException e){
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString(){
        return  val + "[" + modulo + "]";
    }
}
