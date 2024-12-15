package BankingApplicrion;

import java.util.Scanner;

interface BankActions
{
    void addMoney() throws BankException;
    void viewBalance() throws BankException;
    void widthDraw() throws BankException;
    void showBalance() throws BankException;
    void changePin() throws BankException;
}

class Bank implements BankActions
{
    Scanner sc = new Scanner(System.in);
    private int cardNo;
    private int pin;
    private String name;
    private String valDate;
    private int accNo;
    private double balance;
    public Bank(int accNo,int cardNo, String name, String valDate, double balance,int pin) {
        setAccNo(accNo);
        setCardNo(cardNo);
        setName(name);
        setValDate(valDate);
        setBalance(balance);
        setPin(pin);
    }

    @Override
    public void addMoney() throws BankException {
        System.out.println("Enter the amount you want to add :");
        double amount = sc.nextDouble();
        if(amount > balance)
        {
            throw new BankException(balance,accNo,name,3,cardNo,valDate);
        }
        balance = balance + amount;
    }

    @Override
    public void viewBalance() throws BankException {
        System.out.println("Enter Your PIN :");
        int pin = sc.nextInt();
        if(this.pin == pin)
        {
            System.out.println("Account Balance :"+balance);
        }
        else
        {
            throw new BankException(balance,accNo,name,4,cardNo,valDate);
        }
    }

    @Override
    public void widthDraw() throws BankException {
        System.out.println("Enter Your PIN :");
        int pin = sc.nextInt();
        if(this.pin == pin)
        {
            System.out.println("Enter Amount to be Withdrawn :");
            double amount = sc.nextInt();
            if(amount > balance)
            {
                throw new BankException(balance,accNo,name,1,cardNo,valDate);
            }
            else {
                if(balance - amount > 1000)
                balance = balance - amount;
                else
                {
                    throw new BankException(balance,accNo,name,5,cardNo,valDate);
                }
            }
        }
        else
        {
            throw new BankException(balance,accNo,name,4,cardNo,valDate);
        }
    }

    @Override
    public void showBalance() throws BankException {
        System.out.println("Enter Your PIN :");
        int pin = sc.nextInt();
        if(this.pin == pin)
        {
            System.out.println("Account Balance :"+balance);
        }
        else
        {
            throw new BankException(balance,accNo,name,4,cardNo,valDate);
        }
    }

    @Override
    public void changePin() throws BankException {
        System.out.println("Enter Older Pin :");
        int pin = sc.nextInt();
        if(this.pin == pin)
        {
            System.out.println("Enter New Pin :");
            this.pin = sc.nextInt();
        }
        else
        {
            throw new BankException(balance,accNo,name,4,cardNo,valDate);
        }
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    private void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    private void setValDate(String valDate) {
        this.valDate = valDate;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPin(int pin) {
        this.pin = pin;
    }

    private void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }
}
class BankException extends Exception
{
    double balance;
    int accNo;
    String name;
    int type;
    int cardNo;
    String valDate;
    BankException(double balance,int accNo,String name, int type,int cardNo,String valDate)
    {
        this.name = name;
        this.accNo = accNo;
        this.balance = balance;
        this.type = type;
        this.cardNo = cardNo;
        this.valDate = valDate;
    }
    void message()
    {
        switch(type)
        {
            case 1 -> {
                System.out.println("Not Enough Funds!!");
                System.out.println("Account Holder Name :"+name);
                System.out.println("Account Number :"+accNo);
                System.out.println("Balance :"+balance);
                System.out.println("Card No :"+cardNo);
                System.out.println("ValDate :"+valDate);
            }
            case 2 -> {
                System.out.println("Transaction Failed!!");
                System.out.println("Network Error");
                System.out.println("Account Holder Name :"+name);
                System.out.println("Account Number :"+accNo);
                System.out.println("Balance :"+balance);
                System.out.println("Card No :"+cardNo);
                System.out.println("ValDate :"+valDate);
            }
            case 3 -> {
                System.out.println("Cannot Add Money More Than 1500rs At a Time");
                System.out.println("Account Holder Name :"+name);
                System.out.println("Account Number :"+accNo);
                System.out.println("Balance :"+balance);
                System.out.println("Card No :"+cardNo);
                System.out.println("ValDate :"+valDate);
            }
            case 4 -> {
                System.out.println("You have Entered Wrong Pin!!");
                System.out.println("Account Holder Name :"+name);
                System.out.println("Account Number :"+accNo);
                System.out.println("Balance :"+balance);
                System.out.println("Card No :"+cardNo);
                System.out.println("ValDate :"+valDate);
            }
            case 5 -> {
                System.out.println("You must maintain at least 1000 in your account");
                System.out.println("Account Holder Name :"+name);
                System.out.println("Account Number :"+accNo);
                System.out.println("Balance :"+balance);
                System.out.println("Card No :"+cardNo);
                System.out.println("ValDate :"+valDate);
            }
        }
    }
}

class User extends Bank
{
    public User(int accNo , int cardNo, String name, String valDate, int balance, int pin)
    {
        super(accNo,cardNo, name, valDate, balance,pin);
    }
}

public class App
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank user = new User(1232142,89798678,"Vinayak","12/09/03",1500,1234);
        while(true)
        {
            System.out.println("1. Show balance");
            System.out.println("2. Change pin");
            System.out.println("3. Add Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Exit");
            System.out.println("Enter your choice :");
            int ch = sc.nextInt();
            try{
                switch(ch)
                {
                    case 1 -> {
                        user.showBalance();
                    }
                    case 2 -> {
                        user.changePin();
                    }
                    case 3 -> {
                        user.addMoney();
                    }
                    case 4 -> {
                        user.widthDraw();
                    }
                    case 5 -> {
                        break;
                    }
                }

            }
            catch (BankException e)
            {
                e.message();
            }
        }

    }
}
