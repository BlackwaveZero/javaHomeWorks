import java.util.*;
import java.io.IOException;

public class RandomGuess{
    public static void main(String[] args) {
        RandomGuess3 game=new RandomGuess3();
        game.run();
    }
}
class RandomGuess3{
    //import java.util.HashMap;
    private int limitOfAttempts=5;
    private int []range={0,1000};

    private final String[] messages={
            "too low",
            "very low",
            "low",
            "not that far , but still low",
            "close , but still low",
            "too close , but still low",
            "too close , but still high",
            "close , but still high",
            "not that far , but still high",
            "high",
            "very high",
            "too high",
    };
    private final int []messagesIndexes={-500,-100,-50,-25,-10,-5,5,10,25,50,100,500};

    private final Scanner input=new Scanner(System.in);

    private void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
        System.out.print("\033\143");
        System.out.flush();
    }

    private void pressAnyKey(){
        System.out.print("Press any enter to continue...");
        this.input.nextLine();
    }

    RandomGuess3(){
    }

    private int randNum(int min,int max){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    private int getGuess(){
        System.out.println("Your guess : ");
        try{
            String command=this.input.nextLine().toLowerCase();
            if(command.equals("back"))
                if(this.areYouSure())
                    return -2;
            int num=Integer.parseInt(command);
            if(num>=this.range[1] || num<=this.range[0]){
                System.out.println("Invalid number !");
                return -1;
            }
            return num;
        }
        catch (Exception e){
            System.out.println("Invalid number !");
            return -1;
        }
    }

    private String estimate(int guess,int number){
        int estimation=guess-number;
        for (int i=0;i<this.messages.length;i++)
            if(this.messagesIndexes[i]>=estimation)
                return this.messages[i];
        return "";

    }

    private boolean checkAttempts(int attempts){
        if(attempts==this.limitOfAttempts){
            System.out.println("Sorry but your attempts number reached to the limit");
            return true;
        }
        return false;
    }

    private boolean wins(int guessed,int  computerNumber){
        if(guessed==computerNumber){
            System.out.println("Congrats !!!");
            return true;
        }
        return false;
    }

    private boolean areYouSure(){
        System.out.print("Are you sure : ");
        String command=this.input.nextLine().toLowerCase();
        switch (command){
            case "yes":
            case "y":
            case "ok":
            case"1":
                return true;
            default:
                return false;
        }


    }
    private void play(){
        this.clearScreen();
        int computerNumber=this.randNum(this.range[0],this.range[1]);

        System.out.println("All numbers are between "+this.range[0]+" and "+this.range[1]);
        System.out.println("-----------------------------------------------------\n");

        int guessed;
        int attempts=0;

        while (true){
            guessed=this.getGuess();
            if (guessed==-1)
                continue;
            if(guessed==-2)
                break;
            if(this.checkAttempts(attempts) || this.wins(guessed,computerNumber))
                break;
            System.out.println(this.estimate(guessed,computerNumber));
            System.out.println("************");
            attempts++;

        }
        pressAnyKey();
    }

    private boolean setField(String text){
        text.strip();
        String command="";
        String value="";
        int status=0;
        for(int i =0;i<text.length();i++){
            if (status==0){
                if (text.charAt(i)=='=')
                    status++;
                else
                    command+=text.charAt(i);
            }
            else if(status==1)    {
                if(text.charAt(i)<'0' || text.charAt(i)>'9')
                    return false;
                status++;
                value+=text.charAt(i);
            }
            else {
                if(text.charAt(i)<'0' ||text.charAt(i)>'9')
                    return false;
                value+=text.charAt(i);
            }
        }
        if(status==0)
            return false;
        int val;
        try {
            val=Integer.parseInt(value);
        }
        catch (Exception e){
            return false;
        }
        if (val<0)
            return false;
        switch (command.toLowerCase()){
            case "limit":
                return this.changeLimit(val);
            case "from":
                return this.changeRangeFrom(val);
            case "to":
                return this.changeRangeTo(val);
        }
        return false;

    }
    private boolean changeLimit(int limit){
        if (limit<0)
            return false;
        this.limitOfAttempts=limit;
        return true;
    }
    private boolean changeRangeFrom(int value){
        if (value>this.range[1] || value<0)
            return false;
        this.range[0]=value;
        return true;
    }
    private boolean changeRangeTo(int value){
        if (value>this.range[0] || value<0)
            return false;
        this.range[1]=value;
        return true;
    }

    private void showHelp(){
        this.clearScreen();
        System.out.println("Note : In anywhere type back to go back to the sub menu");
        System.out.println("*********************************************************");
        System.out.println("Note : In menu you can type numbers or menu items name and they are not case-sensitive");
        System.out.println("*********************************************************");
        System.out.println("In setting for changing any field use this syntax :");
        System.out.println("filedName=value");
        System.out.println("Note : Field names are not case-sensitive!!!");
    }
    private void showMenu(){
        System.out.println("Note : Please read help first");
        System.out.println("Menu : ");
        System.out.println("   1.play");
        System.out.println("   2.settings");
        System.out.println("   3.help");
        System.out.println("   4.exit");
        System.out.print("$:>");
    }
    private void showLimit(){

        System.out.println("Attemptslimit : ");
        System.out.println("   Limit : "+this.limitOfAttempts);
    }
    private void showRange(){
        System.out.println("Range of number : ");
        System.out.println("  From="+this.range[0]);
        System.out.println("  To="+this.range[1]);
    }
    private void showSettings(){
        this.showLimit();
        this.showRange();
        System.out.print("$:>");
    }

    private void settings(){
        this.clearScreen();
        String command;
        while (true){
            this.clearScreen();
            this.showSettings();
            command=this.input.nextLine();
            command.toLowerCase();
            if (command.equals("back"))
                break;
            System.out.println(command);
            if(!this.setField(command)){
                System.out.println("Invalid syntax or command or value.values must be greatan than zero and (from) must be less than (to)");
                this.pressAnyKey();
            }

        }
    }
    private void menu(){
        String command="";
        while (true){
            this.clearScreen();
            this.showMenu();
            command=this.input.nextLine();
            switch (command.toLowerCase()){
                case "play":
                case "1":
                    this.play();
                    break;
                case "settings":
                case "2":
                    this.settings();
                    break;
                case "3":
                case "help":
                    this.showHelp();
                    this.pressAnyKey();
                    break;
                case "4":
                case "exit":
                    return;
                case "back":
                    continue;
                default:
                    System.out.println("Invalid command");
                    this.pressAnyKey();
            }
        }
    }
    public void run(){
        this.menu();
    }

}

class Random{
    public static int randNum(int min,int max){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
}
