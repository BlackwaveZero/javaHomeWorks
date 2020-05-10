import java.util.*;
import java.io.IOException;

public class PigDiceGameHomeWork{
    public static void main(String[] args) {
        PigDiceGameMenu game=new PigDiceGameMenu(true);
    }
}

class PigDiceGameMenu {
    private HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
    private HashMap<String, Double> scoreBoard = new HashMap<String, Double>();
    private PigDiceGame gamePlay=new PigDiceGame(false);
    protected final Scanner input=new Scanner(System.in);
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

    PigDiceGameMenu(boolean open){
        if(open)
            this.run();
    }

    private void addScores(){
        ArrayList<String> players=this.gamePlay.getPlayersList();
        ArrayList<Double> scores=this.gamePlay.getScoresList();
        for(int i=0;i<players.size();i++)
            this.scoreBoard.put(players.get(i),scores.get(i));
    }
    private void play(){
        this.clearScreen();
        this.gamePlay.start();
        this.addScores();
    }

    private void showMenu(){
        System.out.println("Note : Please read help first");
        System.out.println("Menu : ");
        System.out.println("   1.Play");
        System.out.println("   2.ScoreBoard");
        System.out.println("   3.Help");
        System.out.println("   4.Exit");
        System.out.print("$:>");
    }
    private void showHelp(){
        System.out.println("*********************************************************");
        System.out.println("Note : In menu you can type numbers or menu items name and they are not case-sensitive");
        System.out.println("*********************************************************");
        System.out.println("Anyone who reaches 100points first is winner");
        System.out.print("press enter to continue...");
        this.input.nextLine();

    }
    private void showScoreBorad(){
        HashMap<String, Double> scoreBoard=this.sortByValues(this.scoreBoard);
        int counter=0;
        for (String player : scoreBoard.keySet()) {
            System.out.println((++counter)+". "+player+" : "+scoreBoard.get(player).intValue());
        }
        System.out.println("--------------------------------");
        System.out.print("press enter to continue...");
        this.input.nextLine();
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
                case "scoreboard":
                case "2":
                    this.showScoreBorad();
                    break;
                case "3":
                case "help":
                    this.showHelp();
                    break;
                case "4":
                case "exit":
                    System.exit(0);
                default:
                    System.out.print("Invalid command.press enter to continue...");
                    this.input.nextLine();
            }
        }
    }
    public void run(){
        this.menu();
    }

}

class PigDiceGame extends NumberGames{
    PigDiceGame(boolean start){
        if(start)
            this.start();
    }
    private boolean computerChoice(){
        return Random.randNum(0,1)==1;
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
    private void showTurn(){
        if(this.player().equals("Computer")){
            this.showLoading(500,6,".","Computer is rolling the dice");
            return;
        }
        System.out.print(this.player()+" please press enter to roll the dice...");
        this.input.nextLine();
    }
    private boolean askForRolling(){
        if(this.player()=="Computer")
            return this.computerChoice();
        System.out.print("Would you like to roll again ?(type yes otherWise its no) :  ");
        String command=this.input.nextLine();
        if(command.equals("yes") ||command.equals("y"))
            return false;
        return true;//if yes it means dont change the turn and it returns false
    }
    private boolean rollDice(){
        this.clearScrenn();
        this.showPlayers();
        this.showTurn();
        int firstNum=Random.randNum(1,6);
        int secondNum=Random.randNum(1,6);
        showLoading(500,6,".","You have got "+firstNum+" , "+secondNum);
        if(firstNum==1&&secondNum==1){
            return this.move(0,true,"set","");
        }
        if(firstNum!=1&&secondNum==1 ||secondNum!=1&&firstNum==1 ) {
            return this.move(0, true, "add", "");
        }
        return this.move(firstNum+secondNum,askForRolling(),"add","");

    }
    public void start(){
        this.reset();
        this.addPlayer("Computer");
        this.init(100,1);//get one player
        while (!this.rollDice());
        this.showWinner();
        System.out.print("\npress enter to continue...");
        this.input.nextLine();
    }
    public ArrayList<String> getPlayersList(){
        return this.players;
    }
    public ArrayList<Double> getScoresList(){
        return this.scores;
    }
}

class NumberGames{

    protected double finalScore=0;
    private int playersLen=0;
    private int turn=0;

    protected ArrayList<String> players=new ArrayList<String>();
    protected ArrayList<Double> scores=new ArrayList<Double>();
    protected final Scanner input=new Scanner(System.in);


    protected void reset(){
        this.players.clear();
        this.scores.clear();
        this.turn=0;
    }

    protected void init(double finalScore,int getPlayerCount){
        this.getPlayers(getPlayerCount);
        this.finalScore=finalScore;
        Collections.shuffle(this.players);
    }

    protected void showPlayers(){
        for (int i=0;i<this.players.size();i++)
            System.out.println(this.players.get(i)+" score : "+this.scores.get(i).intValue());
        System.out.println("------------------------------");
    }

    private void getPlayers(int count){
        int counter=1;
        String name;
        while (counter<=count){
            this.clearScrenn();
            System.out.println(counter+".Enter your name please : ");
            name=this.input.nextLine();
            this.addPlayer(name);
            counter++;
        }
    }

    private boolean isWinner(){
        return this.playerScore()>=this.finalScore;
    }

    protected void showWinner(){
        System.out.println("\nWinner is : "+this.player()+" with the score of "+(this.playerScore().intValue()));
    }

    protected void nextTrun(){
        if(this.turn+1==this.playersLen)
            this.turn=0;
        else
            this.turn++;
    }

    protected String player(){
        return this.players.get(this.turn);
    }

    protected Double playerScore(){
        return this.scores.get(this.turn);
    }

    protected void addPlayer(String name){
        this.players.add(name);
        this.scores.add(0.0);
        this.playersLen++;
    }

    private void addScore(double score,String action){//add subtract set
        switch (action){
            case "add":
                this.scores.set(this.turn,this.scores.get(this.turn)+score);
                break;
            case "subtract":
                this.scores.set(this.turn,this.scores.get(this.turn)-score);
                break;
            case "set":
                this.scores.set(this.turn,score);
                break;
            default:
                this.scores.set(this.turn,this.scores.get(this.turn)+score);
        }
    }

    private double getUserinp(){
        String inp;
        while (true){
            this.clearScrenn();
            System.out.print("Dear "+this.player()+",Please enter valid number : ");
            inp=this.input.nextLine();
            if(Validation.isValid(inp,"number"))
                return Double.parseDouble(inp);
        }
    }

    protected boolean randMove(int rangeFrom,int rangeTo,boolean nextTurn,String action,String rule){

        int randNum=Random.randNum(rangeFrom,rangeTo);

        boolean isValid=true;
        if(rule.length()!=0)
            isValid=Validation.isValid( String.valueOf(randNum) ,rule);
        if(isValid==false)
            return false;

        this.addScore(randNum,action);
        if(isWinner())
            return true;
        if (nextTurn)
            this.nextTrun();
        return false;
    }

    protected boolean move(double score,boolean nextTurn,String action,String rule){
        boolean isValid=true;
        if(rule.length()!=0)
            isValid=Validation.isValid( String.valueOf(score) ,rule);
        if(isValid==false)
            return false;


        this.addScore(score,action);

        if(isWinner())
            return true;
        if (nextTurn)
            this.nextTrun();
        return false;
    }

    protected boolean move(boolean nextTurn,String action,String rule){
        double score=this.getUserinp();

        boolean isValid=true;
        if(rule.length()!=0)
            isValid=Validation.isValid( String.valueOf(score) ,rule);
        if(isValid==false)
            return false;

        this.addScore(score,action);

        if(isWinner())
            return true;
        if (nextTurn)
            this.nextTrun();
        return false;
    }

    NumberGames(){

    }

    protected void clearScrenn(){
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
}

class Random{
    public static int randNum(int min,int max){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
}

class Validation{
    public static boolean isValid(String value,String filter ) {
        return Validation.preDefined(value,filter);
    }
    public static boolean preDefined(String value,String filter){

        switch (filter){
            case "boolean":
            case "bool":
                return (value=="true"||value=="false")?true:false;
            case "number":
            case "float":
                try {
                    double tmp=Double.parseDouble(value);
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "negetivenumber":
            case "negetivefloat":
                try {
                    double tmp=Double.parseDouble(value);
                    if(tmp<0)
                        return true;
                    return false;
                }catch (Exception e){
                    return false;
                }
            case "positivenumber":
            case "positivefloat"://Integer.parseInt
                try {
                    double tmp=Double.parseDouble(value);
                    if(tmp>0)
                        return true;
                    return false;
                }catch (Exception e){
                    return false;
                }
            case "int":
                try {
                    int tmp=Integer.parseInt(value);
                }catch (Exception e){
                    return false;
                }
            case "negetiveint":
                try {
                    int tmp=Integer.parseInt(value);
                    if(tmp<0)
                        return true;
                    return false;
                }catch (Exception e){
                    return false;
                }
            case "positiveint":
                try {
                    int tmp=Integer.parseInt(value);
                    if(tmp>0)
                        return true;
                    return false;
                }catch (Exception e){
                    return false;
                }
        }
        double fil;
        double val;
        char first=filter.charAt(0);
        char second=filter.charAt(1);
        if(first=='>'&&second=='='){
            try {
                fil=Double.parseDouble(filter.substring(3,filter.length()-3));
                val=Double.parseDouble(value);
                if(val>=fil)
                    return false;
                return true;
            }catch (Exception e){
                return false;
            }
        }
        else if(first=='>'){
            try {
                fil=Double.parseDouble(filter.substring(2,filter.length()-2));
                val=Double.parseDouble(value);
                if(val>fil)
                    return false;
                return true;
            }catch (Exception e){
                return false;
            }
        }
        else if(first=='<'&&second=='='){
            try {
                fil=Double.parseDouble(filter.substring(3,filter.length()-3));
                val=Double.parseDouble(value);
                if(val<=fil)
                    return false;
                return true;
            }catch (Exception e){
                return false;
            }
        }
        else if(first=='<'){
            try {
                fil=Double.parseDouble(filter.substring(2,filter.length()-2));
                val=Double.parseDouble(value);
                if(val<fil)
                    return false;
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return Validation.range(value,filter);
    }
    public static boolean range(String value,String filter){
        int indexRange=filter.indexOf("range(");
        if(indexRange!=0 || filter.charAt(filter.length()-1)!=')')
            return false;
        try {
            String range=filter.substring(6,filter.length()-7);
            String []ranges=range.split(",");
            if(ranges.length<2)
                return false;
            double val1;
            double val2;
            double val=Double.parseDouble(value);
            if(ranges[0].length()==0 &&ranges[1].length()==0)
                return false;
            if(ranges[0].length()==0){
                val2=Double.parseDouble(ranges[1]);
                if(val<=val2)
                    return true;
                return false;
            }
            if(ranges[1].length()==0){
                val1=Double.parseDouble(ranges[0]);
                if(val>=val1)
                    return true;
                return false;
            }
            val1=Double.parseDouble(ranges[0]);
            val2=Double.parseDouble(ranges[1]);
            if(val>=val1 && val<=val2)
                return true;
            return false;

        }
        catch (Exception e ){
            return false;
        }
    }

}
