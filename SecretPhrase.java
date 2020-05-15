import java.util.*;
import java.io.IOException;

public class SecretPhrase{
    public static void main(String[] args) {
        Game game=new Game();
        game.run();
    }
}
class Random{
    public static int randNum(int min,int max){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
}
class Game{
    protected final Scanner input=new Scanner(System.in);
    private String []words={
            "lavor",
            "program",
            "head",
            "frighten",
            "airplane",
            "cloistered",
            "closed",
            "pear",
            "escape",
            "bare",
            "impulse",
            "bit",
            "serious",
            "frightened",
            "tub",
            "earth",
            "acrid",
            "measly",
            "ossified",
            "show",
            "overt",
            "waggish",
            "beds",
            "doubt",
            "toys",
            "gaze",
            "smart",
            "boast",
            "comparison",
            "disillusioned",
            "cap",
            "mice",
            "colorful",
            "test",
            "stage",
            "endurable",
            "psychedelic",
            "magenta",
            "flag",
            "beginner",
            "thundering",
            "adjustment",
            "boy",
            "tail",
            "day",
            "uneven",
            "tiny",
            "successful",
            "peel",
            "building",
            "chickens",
            "pushy",
            "adhesive",
            "listen",
            "grouchy",
            "attractive",
            "economic",
            "belong",
            "structure",
            "snails",
            "peep",
            "physical",
            "scene",
            "unbiased",
            "private",
            "fax",
            "truthful",
            "mother",
            "rose",
            "uffy",
            "unite",
            "start",
            "cool",
            "squirrel",
            "planes",
            "tight",
            "fry",
            "memorise",
            "ruin",
            "sock",
            "guide",
            "ordinary",
            "part",
            "frogs",
            "heat",
            "remember",
            "offbeat",
            "lackadaisical",
            "meeting",
            "wonder",
            "window",
            "annoy",
            "statement",
            "knock",
            "land",
            "gullible",
            "call",
            "painstaking",
            "ceaseless",
            "judge"
    };
    private void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
        System.out.print("\033\143");
        System.out.flush();
    }
    private void sleep(int time){
        try {
            Thread.sleep(time);
        }catch (Exception e){
        }
    }
    private void showLoading(int interval,int count,String data,String prefix){
        System.out.print(prefix);
        for(int i =0;i<count;i++){
            this.sleep(interval);
            System.out.print(data);
        }
        System.out.print("\n");
    }

    private int limitOfAttempts;
    private String getRandWord(){
        return this.words[Random.randNum(0,this.words.length-1)];
    }
    private String getEmptyWord(int len){
        char [] text=new char[len];
        for(int i=0;i<len;i++)
            text[i]='_';
        return String.valueOf(text);
    }
    private void setLimit(int len){
        this.limitOfAttempts=len*2;
    }

    private String attempt(String userInput,String word,char ch,int attempt){

        char[] tmp = userInput.toCharArray();
        int len=word.length();
        char lowered=Character.toLowerCase(ch);
        int qwe=0;
        for (int i=0;i<len;i++){
            if(lowered==word.charAt(i) && tmp[i]=='_'){
                tmp[i]=lowered;
                qwe=13;
                break;
            }
        }
        String newUserInput= new String(tmp);
        clearScreen();
        this.show(newUserInput,attempt);
        this.showLoading(400,7,".","Computing");
        if(newUserInput.equals(word))
            return "1";//win
        if(attempt+this.remaining(newUserInput)>=this.limitOfAttempts)
            return "0";//lost
        return newUserInput;

    }
    private char ask(){
        String tmp;
        while (true){

            System.out.print("Please enter your char : ");
            tmp=this.input.nextLine();
            if(tmp.length()!=1)
                System.out.println("Please enter one char at once ");
            else
                return tmp.charAt(0);
        }
    }
    private int remaining(String userInp){
        int count=0;
        for(char c:userInp.toCharArray())
        {
            if(c=='_')
                count++;
        }
        return count;
    }
    private void show(String userInp,int attempts){
        System.out.println("Word :("+userInp+") \nnumber of remaining char:"+this.remaining(userInp)+"\nnumber of remaining attempts:"+(this.limitOfAttempts-attempts));
    }
    public void run(){
        clearScreen();
        String word=this.getRandWord();
        String userInp=this.getEmptyWord(word.length());
        this.setLimit(word.length());
        int attemps=0;
        System.out.println(word);
        this.show(userInp,0);
        while (!(userInp=this.attempt(userInp,word,ask(),++attemps)).equals("1") && !userInp.equals("0"));
        if(userInp.equals("1"))
            System.out.println("Congrats you guessed ("+word+") right!!");
        else
            System.out.println("Sorry but you have lost!!(number of remaining chars must be greater than remaining attempts)");
    }
}
