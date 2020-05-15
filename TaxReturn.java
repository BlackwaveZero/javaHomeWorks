import java.util.*;
import java.io.IOException;
public class TaxReturn{
    public static void main(String[] args) {
          taxtReturn tax=new taxtReturn(true);
    }
}
class taxtReturn{
    private final Scanner input=new Scanner(System.in);
    private String socialNumber;
    private String fName;
    private String lName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String annualIncome;
    private String martialStatus;
    private String taxLiability;//bedehi
    private boolean validSocialNumber(String number){
        if(number.length()!=11)
            return false;
        if(number.charAt(3)!='-'||number.charAt(6)!='-')
            return false;
        if(!Validation.isValid(String.valueOf(number.charAt(0)),"number"))
            return false;
        if(!Validation.isValid(String.valueOf(number.charAt(1)),"number"))
            return false;
        if(!Validation.isValid(String.valueOf(number.charAt(2)),"number"))
            return false;
        if(!Validation.isValid(String.valueOf(number.charAt(4)),"number"))
            return false;
        if(!Validation.isValid(String.valueOf(number.charAt(5)),"number"))
            return false;
        if(!Validation.isValid(String.valueOf(number.charAt(7)),"number"))
            return false;
        if(!Validation.isValid(String.valueOf(number.charAt(8)),"number"))
            return false;
        if(!Validation.isValid(String.valueOf(number.charAt(9)),"number"))
            return false;
        if(!Validation.isValid(String.valueOf(number.charAt(10)),"number"))
            return false;
        return true;
    }
    public taxtReturn(String socialNumber,String fName,String lName,String streetAddress,String city,String state,String zipCode,String annualIncome,String martialStatus,String taxLiability){
        this.init(socialNumber,fName,lName,streetAddress,city,state,zipCode,annualIncome,martialStatus,taxLiability);
    }
    public taxtReturn(String socialNumber,String fName,String lName,String streetAddress,String city,String state,String zipCode,String annualIncome,String martialStatus){
        if(this.init(socialNumber,fName,lName,streetAddress,city,state,zipCode,annualIncome,martialStatus,"0"))
            this.calcTaxLiability();

    }
    public boolean init(String socialNumber,String fName,String lName,String streetAddress,String city,String state,String zipCode,String annualIncome,String martialStatus,String taxLiability ){
        if(!check(socialNumber,fName,lName,streetAddress,city,state,zipCode,annualIncome,martialStatus,taxLiability))
            return false;
        this.socialNumber=socialNumber;
        this.fName=fName;
        this.lName=lName;
        this.streetAddress=streetAddress;
        this.city=city;
        this.state=state;
        this.zipCode=zipCode;
        this.annualIncome=annualIncome;
        this.martialStatus=martialStatus.toLowerCase();
        this.taxLiability=taxLiability;
        return true;
    }
    private boolean check(String socialNumber,String fName,String lName,String streetAddress,String city,String state,String zipCode,String annualIncome,String martialStatus,String taxLiability ){
        if(socialNumber.length()==0 || socialNumber.length()>11 ||!this.validSocialNumber(socialNumber))
            return false;
        if(fName.length()==0 || fName.length()>64)
            return false;
        if(lName.length()==0 || lName.length()>64)
            return false;
        if(streetAddress.length()==0 || streetAddress.length()>200)
            return false;
        if(city.length()==0 || city.length()>200)
            return false;
        if(state.length()==0 || state.length()>200)
            return false;
        if(zipCode.length()>5 ||!Validation.isValid(zipCode,"positiveint"))
            return false;
        if(Validation.isValid(annualIncome,"<0"))
            return false;
        if(!(
                martialStatus.toLowerCase().equals("married")
                        ||
                        martialStatus.toLowerCase().equals("single")
        )
        )
            return false;
        if(Validation.isValid(annualIncome,"<0"))
            return false;
        return true;
    }
    public void show(){
        if(this.fName.length()==0)
            return;
        System.out.println("***********************************************");
        System.out.println("* Firstname: "+this.fName);
        System.out.println("* lastname: "+this.lName);
        System.out.println("* Street name: "+this.streetAddress);
        System.out.println("* City: "+this.city);
        System.out.println("* State: "+this.state);
        System.out.println("* Zipcode: "+this.zipCode);
        System.out.println("* Annual income: "+this.annualIncome);
        System.out.println("* Martial status: "+this.martialStatus);
        System.out.println("* Tax liability: "+this.taxLiability);
        System.out.println("***********************************************");
    }
    public void calcTaxLiability(){
        if(this.fName.length()==0){
            this.taxLiability= "";
            return;
        }
        double tax=Double.parseDouble(this.annualIncome);
        if(tax<20001)
        {
            if(this.martialStatus=="single")
                tax*=0.15;
            else
                tax*=0.14;
        }
        else if(tax<50001)
        {
            if(this.martialStatus=="single")
                tax*=0.22;
            else
                tax*=0.2;
        }
        else{
            if(this.martialStatus=="single")
                tax*=0.3;
            else
                tax*=0.28;
        }
        this.taxLiability=String.valueOf(tax);
    }
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
    private void pressEnter(){
        System.out.print("Press enter to continue...");
        this.input.nextLine();
    }
    public void read(){
        String data;

        while (true){
            clearScreen();
            System.out.print("enter your firstname:");
            data=this.input.nextLine();
            if(data.length()<64 &&data.length()>0)
                break;
            pressEnter();
        }
        this.fName=data;

        while (true){
            clearScreen();
            System.out.print("enter your lastname:");
            data=this.input.nextLine();
            if(data.length()<64 &&data.length()>0)
                break;
            pressEnter();
        }
        this.lName=data;

        while (true){
            clearScreen();
            System.out.print("enter your social number:");
            data=this.input.nextLine();
            if( this.validSocialNumber(data))
                break;
            pressEnter();
        }
        this.socialNumber=data;

        while (true){
            clearScreen();
            System.out.print("enter your street address:");
            data=this.input.nextLine();
            if(data.length()<200 &&data.length()>0)
                break;
            pressEnter();
        }
        this.streetAddress=data;

        while (true){
            clearScreen();
            System.out.print("enter your city:");
            data=this.input.nextLine();
            if(data.length()<200 &&data.length()>0)
                break;
            pressEnter();
        }
        this.city=data;

        while (true){
            clearScreen();
            System.out.print("enter your state:");
            data=this.input.nextLine();
            if(data.length()<200 &&data.length()>0)
                break;
            pressEnter();
        }
        this.state=data;

        while (true){
            clearScreen();
            System.out.print("enter your zipcode:");
            data=this.input.nextLine();
            if(data.length()==5 && Validation.isValid(data,"int"))
                break;
            pressEnter();
        }
        this.zipCode=data;

        while (true){
            clearScreen();
            System.out.print("enter your annual income:");
            data=this.input.nextLine();
            if(Validation.isValid(data,">=0"))
                break;
            pressEnter();
        }
        this.annualIncome=data;

        while (true){
            clearScreen();
            System.out.print("enter your martial status:");
            data=this.input.nextLine();
            if((data.toLowerCase().equals("married") || data.toLowerCase().equals("single")))
                break;
            pressEnter();
        }
        this.martialStatus=data;


    }
    public taxtReturn(boolean run){
        if(!run)
            return;
        this.read();
        clearScreen();
        this.calcTaxLiability();
        this.show();
    }
    public taxtReturn(){}
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
                    return true;
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
                fil=Double.parseDouble(filter.substring(2));
                val=Double.parseDouble(value);

                if(val>=fil)
                    return true;

                return false;
            }catch (Exception e){

                return false;
            }
        }
        else if(first=='>'){
            try {
                fil=Double.parseDouble(filter.substring(1));
                val=Double.parseDouble(value);
                if(val>fil)
                    return true;
                return false;
            }catch (Exception e){
                return false;
            }
        }
        else if(first=='<'&&second=='='){
            try {
                fil=Double.parseDouble(filter.substring(2));
                val=Double.parseDouble(value);
                if(val<=fil)
                    return true;
                return false;
            }catch (Exception e){
                return false;
            }
        }
        else if(first=='<'){
            try {
                fil=Double.parseDouble(filter.substring(1));
                val=Double.parseDouble(value);
                if(val<fil)
                    return true;
                return false;
            }catch (Exception e){
                return false;
            }
        }
        else if(first=='='&&second=='='){
            return value.equals(filter.substring(2));
        }
        return Validation.range(value,filter);
    }
    public static boolean range(String value,String filter){
        int indexRange=filter.indexOf("range(");
        if(indexRange!=0 || filter.charAt(filter.length()-1)!=')')
            return false;
        try {
            String range=filter.substring(6,filter.length()-2);
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
