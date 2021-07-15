package afine;

import math.NegativeNumberException;

public class AfineKey {
    private final int A;
    private final int B;

    public AfineKey(int A, int B) throws NegativeNumberException {
        if(A < 0 || B < 0){
            throw new NegativeNumberException();
        }
        this.A = A;
        this.B = B;
    }
}
