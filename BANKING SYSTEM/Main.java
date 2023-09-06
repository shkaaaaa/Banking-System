//package BANK;

import java.util.ArrayList;
import java.util.Scanner;

//import javax.sound.midi.Soundbank;
interface I{
    public abstract void createAccount();
    public abstract void loginAccount();
}
class Transaction {
    int tid;
    Users sender;
    Users receiver;
    long amount;

    public Transaction() {
    }

    public Transaction(Users sender, Users receiver, long amount) {
        tid = generateTID();
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        Main.transactionList.add(this);
    }

    int generateTID() {
        return Main.transactionList.size() + 1111;
    }

    void details() {
        System.out.println("TID: " + tid);
        System.out.println("sender: " + sender.userName);
        System.out.println("receiver: " + receiver.userName);
        System.out.println("Amount: " + amount);
    }
}

public class Main implements I{

    static ArrayList<Users> userList = new ArrayList<>();
    static ArrayList<Transaction> transactionList = new ArrayList<>();

    public static void main(String[] args) {

        Users user1 = new Users("Stefan", "stef@gmail.com", 123432123, "stef77", 20000);
        userList.add(user1);

        Users user2 = new Users("Damon", "damn@gmail.com", 123432123, "damn77", 80000);
        userList.add(user2);

        Users user3 = new Users("Elijah", "elij@gmail.com", 123432123, "elij77", 70000);
        userList.add(user3);

        Users user4 = new Users("Klaus", "klaus@gmail.com", 123432123, "klaus7", 28000);
        userList.add(user4);

        Users user5 = new Users("Enzo", "enz@gmail.com", 123432123, "enzo77", 90000);
        userList.add(user5);

        Users user6 = new Users("Kai", "kai@gmail.com", 123432123, "kai77", 32000);
        userList.add(user6);

        Main ref= new Main();
        ref.menu();
    }

    void menu() {
        System.out.println("|| --- WELCOME TO BANK OF INDIA --- ||");
        System.out.println();
        System.out.println("1. Create Account");
        System.out.println("2. Login Account");
        System.out.println("3. Users Database");
        System.out.println("4. All Transactions");
        System.out.println("5. Specific Transactions");
        System.out.println();
        input();
    }

    void input() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        try {
            int choice = input.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    loginAccount();
                    break;
                case 3:
                    usersDatabase();
                    break;
                case 4:
                    for (Transaction transaction : transactionList) {
                        transaction.details();
                    }
                    menu();
                    break;
                default:
                    System.out.println("Invalid Option");
                    input();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid input,please try again later");
            input();
        }
    }

    // 1. Create new account
    public void createAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String userName = input.next();
        System.out.println("Enter email: ");
        String email = input.next();
        System.out.println("Enter contact: ");
        long contact = input.nextInt();
        System.out.println("Enter password: ");
        String password = input.next();
        System.out.println("Enter Account Balance: ");
        long accountBalance = input.nextInt();
        Users newUser = new Users(userName, email, contact, password, accountBalance);
        userList.add(newUser);
        System.out.println("Successfully created account ^_^");
        System.out.println("--------------------------------");
        System.out.println();
        menu();
    }

    // 2. Login Account

    public void loginAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String name = input.next();
        Users currentUser = null;
        for (Users users : userList) {
            if (users.userName.equalsIgnoreCase(name)) {
                currentUser = users;
            }
        }
        if (currentUser == null) {
            System.out.println("Invalid Username,please try again later :(");
            loginAccount();
        } else {
            System.out.println("Enter password: ");
            String password = input.next();
            if (currentUser.password.equals(password)) {
                System.out.println("Successfully logged in ^_^");
                System.out.println("--------------------------------");
                System.out.println();
                currentUser.UserDetails();
                System.out.println("--------------------------------");
                System.out.println();
                afterLoginMenu();

                System.out.println("Enter your choice: ");
                try {
                    int choice = input.nextInt();
                    if (choice == 1) {
                        System.out.println("Enter amount: ");
                        long amount = input.nextLong();
                        currentUser.accountBalance = currentUser.accountBalance - amount;
                        System.out.println("Account Balance :" + currentUser.accountBalance);
                        System.out.println("Thankyou for visiting us ^_^");
                        System.out.println("--------------------------------");
                        System.out.println();
                        menu();
                    } else if (choice == 2) {
                        System.out.println("Enter amount: ");
                        long amount = input.nextLong();
                        currentUser.accountBalance = currentUser.accountBalance + amount;
                        System.out.println("Account Balance :" + currentUser.accountBalance);
                        System.out.println("Thankyou for visiting us ^_^");
                        System.out.println("--------------------------------");
                        System.out.println();
                        menu();
                    } else if (choice == 3) {
                        System.out.println("Enter Account Number: ");
                        long accnumber1 = input.nextLong();
                        Users currentUser1 = null;
                        for (Users users : userList) {
                            if (users.accountNumber == accnumber1) {
                                currentUser1 = users;
                            }
                        }
                        if (currentUser1 == null) {
                            System.out.println("Invalid Username,please try again :(");
                            System.out.println();
                            menu();
                        } else {
                            System.out.println("Enter Amount to tranfer: ");
                            long amount1 = input.nextLong();
                            currentUser1.accountBalance = currentUser1.accountBalance + amount1;
                            currentUser.accountBalance = currentUser.accountBalance - amount1;
                            Transaction t = new Transaction(currentUser, currentUser1, amount1);
                            t.details();
                            System.out.println();
                            currentUser.UserDetails();

                            System.out.println("Successfully transfered,Thankyou for visiting us ^_^");
                            System.out.println();
                            menu();
                        }

                    } else if (choice == 4) {
                        for (Transaction transaction : transactionList) {
                            if (currentUser.userName.equals(transaction.sender.userName)) {
                                transaction.details();
                                System.out.println();
                            }
                        }
                        menu();
                    } else {
                        menu();
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Invalid input,please try again :(");
                    afterLoginMenu();
                }
            } else {
                System.out.println("Invalid password,please try again :(");
                loginAccount();
            }
        }
    }

    // 3. Users Database
    void usersDatabase() {
        Scanner input = new Scanner(System.in);
        System.out.println("Localhost: ");
        String localhost = input.next();
        if (localhost.equals("Anushka@77")) {
            System.out.println("Password: ");
            long password = input.nextLong();
            if (password == 772000) {
                System.out.println("Successfully logged in ^_^");
                System.out.println("--------------------------------");
                for (Users users : userList) {
                    users.UserDetails();

                }
                menu();
            } else {
                System.out.println("Invalid Password,please try again");
                usersDatabase();
            }
        } else {
            System.out.println("Invalid Localhost,please try again");
            usersDatabase();
        }
    }

    void afterLoginMenu() {
        System.out.println("1. Withdraw Amount");
        System.out.println("2. Deposit Amount");
        System.out.println("3. Transfer Money");
        System.out.println("4. See your Transactions");
        System.out.println("any other to Exit to main menu");
        System.out.println();
        // input1();
    }

    static long generateAccountNo() {
        return userList.size() + 10000;
    }

}

class Users {
    long accountNumber;
    String userName;
    String email;
    long contact;
    String password;
    long accountBalance;

    Users(String userName, String email, long contact, String password, long accountBalance) {
        this.userName = userName;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.accountBalance = accountBalance;
        this.accountNumber = Main.generateAccountNo();
    }

    void UserDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Username: " + userName);
        System.out.println("Email: " + email);
        System.out.println("Contact: " + contact);
        // System.out.println("Password: " + password);
        System.out.println("Account Balance: " + accountBalance);
        System.out.println();
    }
}
