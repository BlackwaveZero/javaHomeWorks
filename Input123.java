import java.util.Scanner;
public class Input123{
    public static void main(String[] args) {
        Input inp=new Input(true);
    }
}

class Input{
    private Scanner input;
    Input(boolean init){
        this.input=new Scanner(System.in);
        if (init)
            this.init();
    }
    private int filter(String data){
        switch (data){
            case "4"://end
                return 0;
            case "3":
            case "2":
            case "1":
                return 1;//good job
            default:
                return -1;//error
        }
    }
    private String userInput(String prefix,boolean newLine){
        if (prefix.length()>0)
            System.out.print(prefix+(newLine?"\n":""));
        return this.input.nextLine();
    }
    private void showMessage(){
        System.out.println("Good job!");
    }
    private void showError(){
        System.out.println("Invalid user input!");
    }
    public void init(){
        int status;
        while (true){
            status=this.filter(this.userInput("$: ",false));
            switch (status){
                case 0:
                    return;
                case 1:
                    this.showMessage();
                    break;
                case -1:
                    this.showError();
                    break;
            }
        }
    }
}
