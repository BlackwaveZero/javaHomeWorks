import java.util.*;

public class Sum50{
    public static void main(String[] args) {
        System.out.println(GetSum.getSum(50));
    }
}
class GetSum{
//    public static int getSum(int num){
//        return (num*(1+num))/2;
//    }//this is the faster way
    public static int getSum(int num){
        int sum=0;
        for(int i=1;i<=num;sum+=(i++));
        return sum;
    }
}
