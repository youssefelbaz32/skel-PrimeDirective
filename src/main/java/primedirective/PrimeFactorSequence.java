package primedirective;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorSequence {
    private List<Integer> primes;
    private int upperBound;

    /**
     * Create a PrimeFactorSequence object with a defined upperbound.
     *
     * @param _upperBound the upper bound for sequences and primes in this instance,
     * {@code upperBound > 0}.
     */
    public PrimeFactorSequence(int _upperBound) {
        upperBound = _upperBound;
        primes = Primes.getPrimes(upperBound);
    }

    /**
     * Obtain a sequence L[0 .. upper bound] where L[i] represents the number of prime factors i
     * has, including repeated factors
     *
     * @return sequence L[0 .. upper bound] where L[i] represents the number of prime factors i
     * has, including repeated factors
     */
    public List<Integer> primeFactorSequence() {
        List<Integer> seq = new ArrayList<>();
        seq.add(0);
        seq.add(0);
        for (int i = 2; i < upperBound + 1; i++) {
            int counter = 0;
            int num = i;
            for (int prime: primes) {
                while (num % prime == 0 && num != 1) {
                    num = num/prime;
                    counter++;
                }
            }
            seq.add(counter);
        }
        return seq;
    }

    /**
     * Obtain a sequence L that is sorted in ascending order and where L[i] has exactly m
     * prime factors (including repeated factors) and L[i] <= upper bound
     *
     * @param m the number of prime factors that each element of the output sequence has
     * @return a sequence L that is sorted in ascending order and where L[i] has exactly
     * m prime factors (including repeated factors) and L[i] <= upper bound
     */
    public List<Integer> numbersWithMPrimeFactors(int m) {
        List<Integer> seq = new ArrayList<>();
        List<Integer> seq2 = new ArrayList<>();
        seq2 = primeFactorSequence();

        for (int i = 0; i <= upperBound; i++) {
            if (seq2.get(i).equals(m) ) {
                seq.add(i);
            }
        }
        return seq;
    }

    /**
     * Obtain a sequence of integer pairs [(Sa, Sb)] where Sa and Sb have exactly m prime factors
     * (including repeated factors), and where |Sa - Sb| <= gap and where Sa and Sb are
     * adjacent each other in the output of {@code numbersWithMPrimeFactors(m)}
     *
     * @param m   the number of prime factors that each element in the output sequence has
     * @param gap the maximum gap between two adjacent entries of interest in the output
     *            of {@code numbersWithMPrimeFactors(m)}
     * @return a sequence of integer pairs [(Sa, Sb)] where Sa and Sb have exactly
     * m prime factors (including repeated factors), and where |Sa - Sb| <= gap and where
     * Sa and Sb are adjacent each other in the output of {@code numbersWithMPrimeFactors(m)}
     */
    public List<IntPair> numbersWithMPrimeFactorsAndSmallGap(int m, int gap) {
        List<IntPair> listOfPairs = new ArrayList<>();
        List <Integer> seq = new ArrayList<>();
        seq = numbersWithMPrimeFactors(m);
        for (int i = 0; i < seq.size() - 1; i++) {
            if ( (seq.get(i+1) - seq.get(i)) <= gap ) {
                listOfPairs.add(new IntPair(seq.get(i+1),seq.get(i)));
            }
        }
        return listOfPairs;
    }

    /**
     * Transform n to a larger prime (unless n is already prime) using the following steps:
     * <p>
     *     <ul>
     *         <li>A 0-step where we obtain 2 * n + 1</li>,
     *         <li>or a 1-step where we obtain n + 1</li>.
     *     </ul>
     *      We can apply these steps repeatedly, with more details in the problem statement.
     * </p>
     * @param n the number to transform
     * @return a string representation of the smallest transformation sequence
     */




    public String changeToPrime(int n) {

        if (isPrime(n) && n <= upperBound) {
            return "";
        }

        if (n >= upperBound) {
            return "-";
        }

//        for (int i = 0; i < ??????????????????????????////)


        String path0 = "0" + changeToPrime(2*n + 1);

        String path1 = "1" + changeToPrime(n + 1) ;

            if (path0.contains("-") && path1.contains("-")) {
                return "-";
            } else if (path0.contains("-")) {
                return path1;
            } else if (path1.contains("-")) {
                return path0;
            }

            if (path0.length() < path1.length() ) {
                return path0;
            } else if (path1.length() < path0.length() ) {
                return path1;
            } else {
                return (stringsum(path1) >= stringsum(path0) ? path0:path1);
                }
            }






        private int stringsum (String n) {
        int decimalNumber = Integer.parseInt(n, 2);
        return decimalNumber;
        }



    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
