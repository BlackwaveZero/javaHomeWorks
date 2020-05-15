import java.util.*;
public class JoinStrings{
    public static void main(String[] args) {
        Strings quote=new Strings("Wanna get big!? Eat big. ");
        Strings person=new Strings("ronnie coleman");
        Strings date=new Strings("Cuttently alive");
        Strings all=new Strings();
        all.append(quote.get());
        all.append(" - ");
        all.append(person.get());
        all.append(" - ");
        all.append(date.get());
        System.out.println("quote : "+quote.get());
        System.out.println("by : "+person.get());
        System.out.println("date : "+date.get());
        System.out.println(all.get());
    }
}
class Strings{
    private String data="";
    private final Scanner input=new Scanner(System.in);
    public Strings(String data){
        this.set(data);
    }
    public Strings() { }
    public String set(String data){
        this.data=data;
        return this.data;
    }
    public String set(Strings data){
        this.data=data.get();
        return this.data;
    }
    public String get(){
        return this.data;
    }
    public String append(String data){
        this.data+=data;
        return this.data;
    }
    public String insert(String data,int index){
        if(index==-1)
            return this.append(data);
        int len=this.len()+data.length();
        StringBuilder tmp=new StringBuilder(len);
        int i;
        for(i=0;i<index;i++)
            tmp.append(this.data.charAt(i));
        for(i=0;i<data.length();i++)
            tmp.append(data.charAt(i));
        for(i=index;i<this.data.length();i++)
            tmp.append(this.data.charAt(i));
        this.data=tmp.toString();
        return this.data;
    }
    public int len(){
        return this.data.length();
    }
    public int count(String toBeCounted){
        System.out.println(toBeCounted);
        int count=0;
        int step=toBeCounted.length();
        int i,j;
        for(i=0;i<this.len()-step+1;i++){
            for(j=0;j<step;j++){
                if(!this.equalChars(this.data.charAt(j+i),toBeCounted.charAt(j))){
                    count--;
                    break;
                }
            }
            count++;
        }
        return count;
    }
    private boolean equalChars(char a,char b){
        return a==b;
    }
    public void readLine(){
        this.data=this.input.nextLine();
    }
    public void readLine(String message){
        System.out.print(message);
        this.data=this.input.nextLine();
    }
    public int countWords(){
        if(this.len()==0)
            return 0;
        boolean isSpace=this.data.charAt(0)==' ';
        int count=0;
        if(!isSpace)
            count=1;
        for(char ch:this.data.toCharArray()){
            if(isSpace){
                if(ch!=' ')
                {
                    isSpace=false;
                    count++;
                }
            }
            else {
                if(ch==' ')
                    isSpace=true;
            }
        }
        return count;
    }
    private char lowerChar(char c){
        if(c>64 && c<91)
            return (char)(c+32);
        return (char)c;
    }
    private char upperChar(char c){
        if(c>96 && c<123)
            return (char)(c-32);
        return (char)c;
    }
    private boolean isAlpha(char c){
        if((c>64 && c<91 )|| (c>96 && c<123))
            return true;
        return false;
    }
    public String acronym(){
        String acr="";
        if(this.len()==0)
            return "";

        boolean isSpace=this.data.charAt(0)==' ';

        if(isSpace==false && isAlpha(this.data.charAt(0)))
            acr+=this.upperChar(this.data.charAt(0));

        for(char ch:this.data.toCharArray()){
            if(isSpace){
                if(ch!=' ')
                {
                    isSpace=false;
                    acr+=(this.isAlpha(ch)?this.upperChar(ch):"");
                }
            }
            else{
                if(ch==' ')
                    isSpace=true;
            }

        }
        return acr;
    }
import java.util.*;
public class CountWhiteSpaces{
    public static void main(String[] args) {
        Strings str=new Strings();
        str.readLine("Enter a text : ");
        System.out.println("Number of whitespaces in the entered text is: "+str.count(" "));
    }
}
class Strings{
    private String data="";
    private final Scanner input=new Scanner(System.in);
    public Strings(String data){
        this.set(data);
    }
    public Strings() { }
    public String set(String data){
        this.data=data;
        return this.data;
    }
    public String set(Strings data){
        this.data=data.get();
        return this.data;
    }
    public String get(){
        return this.data;
    }
    public String append(String data){
        this.data+=data;
        return this.data;
    }
    public String insert(String data,int index){
        if(index==-1)
            return this.append(data);
        int len=this.len()+data.length();
        StringBuilder tmp=new StringBuilder(len);
        int i;
        for(i=0;i<index;i++)
            tmp.append(this.data.charAt(i));
        for(i=0;i<data.length();i++)
            tmp.append(data.charAt(i));
        for(i=index;i<this.data.length();i++)
            tmp.append(this.data.charAt(i));
        this.data=tmp.toString();
        return this.data;
    }
    public int len(){
        return this.data.length();
    }
    public int count(String toBeCounted){
        System.out.println(toBeCounted);
        int count=0;
        int step=toBeCounted.length();
        int i,j;
        for(i=0;i<this.len()-step+1;i++){
            for(j=0;j<step;j++){
                if(!this.equalChars(this.data.charAt(j+i),toBeCounted.charAt(j))){
                    count--;
                    break;
                }
            }
            count++;
        }
        return count;
    }
    private boolean equalChars(char a,char b){
        return a==b;
    }
    public void readLine(){
        this.data=this.input.nextLine();
    }
    public void readLine(String message){
        System.out.print(message);
        this.data=this.input.nextLine();
    }
    public int countWords(){
        if(this.len()==0)
            return 0;
        boolean isSpace=this.data.charAt(0)==' ';
        int count=0;
        if(!isSpace)
            count=1;
        for(char ch:this.data.toCharArray()){
            if(isSpace){
                if(ch!=' ')
                {
                    isSpace=false;
                    count++;
                }
            }
            else {
                if(ch==' ')
                    isSpace=true;
            }
        }
        return count;
    }
    private char lowerChar(char c){
        if(c>64 && c<91)
            return (char)(c+32);
        return (char)c;
    }
    private char upperChar(char c){
        if(c>96 && c<123)
            return (char)(c-32);
        return (char)c;
    }
    private boolean isAlpha(char c){
        if((c>64 && c<91 )|| (c>96 && c<123))
            return true;
        return false;
    }
    public String acronym(){
        String acr="";
        if(this.len()==0)
            return "";

        boolean isSpace=this.data.charAt(0)==' ';

        if(isSpace==false && isAlpha(this.data.charAt(0)))
            acr+=this.upperChar(this.data.charAt(0));

        for(char ch:this.data.toCharArray()){
            if(isSpace){
                if(ch!=' ')
                {
                    isSpace=false;
                    acr+=(this.isAlpha(ch)?this.upperChar(ch):"");
                }
            }
            else{
                if(ch==' ')
                    isSpace=true;
            }

        }
        return acr;
    }
    public boolean palindrome(){
        int len=this.len();
        if(len==0)
            return false;
        if(len==1)
            return true;

        int half=len/2;
        for(int i =0;i<half;i++){
            if(this.data.charAt(i)!=this.data.charAt(len-i-1))
                return false;
        }
        return true;
    }
    public boolean palindrome(boolean caseSensetive){
        if(caseSensetive)
            return this.palindrome();
        int len=this.len();
        if(len==0)
            return false;
        if(len==1)
            return true;

        int half=len/2;

        for(int i =0;i<half;i++){
            if(this.lowerChar(this.data.charAt(i))!=this.data.charAt(len-i-1))
                return false;
        }
        return true;
    }
}

}
