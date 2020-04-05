package adf2;

import adf2.entily.Account;
import adf2.helper.ConnectionHelper;
import adf2.model.AccountModel;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MainThread {

    private static AccountModel accountModel = new AccountModel();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        accountModel.test();
        seeding();
        try {
            while (true) {
                System.out.println("-----------------------------Account Manager----------------------------");
                System.out.println("|      1. Show all information in account                               |");
                System.out.println("|      2. Display account information by account name and password      |");
                System.out.println("|      3. Exit the program                                              |");
                System.out.println("-------------------------------------------------------------------------");
                System.out.print("Choice = ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        printAllAccount();
                        break;
                    case 2:
                        printAccountInformation();
                        break;
                    case 3:
                        System.out.println("Goodbye!!");
                        break;
                    default:
                        System.out.println("Invalid, please re-enter");
                        break;
                }

                if (choice == 3) {
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Error!!!" + e.getMessage());
        }
    }



    public static void seeding(){
        AccountModel accountModel = new AccountModel();
        accountModel.save(new Account("Thanh","123456","Vuong Ha Thanh","2020-04-04"));
        accountModel.save(new Account("Manh","123456","Nguyen Van Manh","2020-04-04"));
        accountModel.save(new Account("Dat","123456","Vu Tuan Dat","2020-04-04"));
        accountModel.save(new Account("Dung","123456","Vuong Toan Hung","2020-04-04"));
        accountModel.save(new Account("Phong","123456","Pham Van Phong","2020-04-04"));
        accountModel.save(new Account("Vu","123456","Vuong Vu Tuan","2020-04-04"));
        accountModel. save(new Account("Tuan","123456","Vuong Tuan Vu","2020-04-04"));
        accountModel.save(new Account("Hieu","123456","Vuong Toan Hieu","2020-04-04"));
        accountModel. save(new Account("Lan","123456","Nguyen Thi Lan","2020-04-04"));
        accountModel.save(new Account("Tien","123456","Vuong Toan Hieu","2020-04-04"));
    }

    public static void printAllAccount(){
        ArrayList<Account> accountArrayList = accountModel.loadAccount();
        if (accountArrayList.size() == 0) {
            System.out.println("There are currently no records!");
        } else {
            System.out.println("List of account");
            System.out.println("_________________________________________________________________________________________________________");
            System.out.println(String.format("| %5s %20s %5s | %5s %20s %5s | %5s %20s %5s | %5s %20s %5s |",
                    "", "userName", "",
                    "", "password", "",
                    "", "fullname", "",
                    "", "dateCreate", "")
            );
            System.out.println("----------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < accountArrayList.size(); i++) {
                System.out.println(String.format("| %5s %20s %5s | %5s %20s %5s | %5s %20s %5s | %5s %20s %5s |\n",
                        "", (((Account) accountArrayList.get(i)).getUsername()), "",
                        "", (((Account) accountArrayList.get(i)).getPassword()), "",
                        "", (((Account) accountArrayList.get(i)).getFullName()), "",
                        "", (((Account) accountArrayList.get(i)).getCreatedDate()), "")
                );
            }
        }
    }

    public static void printAccountInformation(){
        Account account = new Account();
        System.out.println("Enter the username and password you want to find");
        System.out.print("Enter userName: ");
        account.setUsername(scanner.nextLine());
        System.out.print("Enter password: ");
        account.setPassword(scanner.nextLine());
        Account act = accountModel.getAccount(account.getUsername(),account.getPassword());
        if(act==null){
            System.out.println("\n-----------------------------------------------------------------------------------------------");
            System.out.println("|    User or password information has been deleted or The currently entered name does not exist. |");
            System.out.println("-------------------------------------------------------------------------------------------------\n");
        }else {
            System.out.println("_---------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("| %5s %20s %5s | %5s %20s %5s | %5s %20s %5s | %5s %20s %5s |",
                    "", "userName", "",
                    "", "password", "",
                    "", "fullname", "",
                    "", "dateCreate", "")
            );
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("| %5s %20s %5s | %5s %20s %5s | %5s %20s %5s | %5s %20s %5s |\n",
                    "", act.getUsername(), "",
                    "", act.getPassword(), "",
                    "", act.getFullName(), "",
                    "", act.getCreatedDate(), "")
            );
        }
    }

}
