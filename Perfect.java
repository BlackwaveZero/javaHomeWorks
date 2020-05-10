import java.util.Scanner;
public class Perfect{
    public static void main(String[] args) {
        findPerfectNum perfect=new findPerfectNum();
        perfect.run();
    }

}

class findPerfectNum{
    private Scanner input;
    private int from=1;
    private int to=1;
    findPerfectNum(){
        this.input=new Scanner(System.in);
    }
    private boolean askRange(){
        int from,to;
        try{
            System.out.print("From:");
            from=this.input.nextInt();
            System.out.print("To:");
            to=this.input.nextInt();
            if (to<1 || from<1 || from>to){
                System.out.println("From must be euqal or less than to and to and from must be greater than zero");
                this.from=1;
                this.to=1;
                return false;
            }
            this.from=from;
            this.to=to;
            return true;
        }
        catch (Exception e){
            System.out.println("Please enter valid numbers!!");
            this.from=1;
            this.to=1;
            return false;
        }
    }
    private boolean isPerfect(int number){
        if (number==1)
            return false;
        int sum=1;
        for (int i=2;i<number;i++)
            if (number%i==0)
                sum+=i;
        if (sum==number)
            return true;
        return false;
    }
    private void show(){
        if(this.from==1 && this.to==1){
            System.out.println("please open program again and enter valid number");
            return;
        }
        System.out.println("Finding perfect numbers from "+String.valueOf(from)+" to "+String.valueOf(to)+" : ");
        boolean flag=false;
        for (int i = from;i<=to;i++){
            if (this.isPerfect(i)){
                System.out.println("  Number "+String.valueOf(i)+" is a perfect number");
                flag=true;
            }
        }
        if (!flag)
            System.out.println("  * There are no perfect numbers from "+String.valueOf(from)+" to "+String.valueOf(to));
    }
    public void run(){
        this.askRange();
        this.show();
    }
}
