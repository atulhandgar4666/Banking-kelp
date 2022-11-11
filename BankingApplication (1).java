

import java.util.*;

class Customer{
    private String accNo;
    private String custName;
    private int balance;

    public Customer(String accNo, String custName, int balance){
        this.accNo = accNo;
        this.custName = custName;
        this.balance = balance;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

public class BankingApplication {

    static ArrayList<Customer> customers = new ArrayList<>();

//    public static void show(){
//        for(Customer c: customers){
//            System.out.println(c.getAccNo() + " " + c.getCustName() + " " + c.getBalance());
//        }
//    }

    public static Customer findCustomer(String accNo){
        Customer customer = null;

        for(Customer c: customers){
            if(c.getAccNo().equals(accNo)){
                customer = c;
                break;
            }
        }
        return customer;
    }
    public static void createAccount(String command){
        String[] createQuery = command.split(" ");
        if(createQuery.length == 3 && createQuery[0].equals("CREATE")){
            String action = createQuery[0];
            String accNo = createQuery[1];
            String custName = createQuery[2];

            Customer customer = findCustomer(accNo);

            if(customer == null){
                customers.add(new Customer(accNo, custName, 0));
                System.out.println(String.format("Account created for %s %s %s", accNo, custName, 0)    );
            }
            else{
                System.out.println("Acc No. already exist!");
            }

        }else{
            System.out.println("Enter a valid create command!");
        }
    }

    public static void depositAmount(String command){

        String[] depositQuery = command.split(" ");
        if(depositQuery.length == 3 && depositQuery[0].equals("DEPOSIT")){
            String action = depositQuery[0];
            String accNo = depositQuery[1];
            int amount = 0;
            try {
                amount = Integer.parseInt(depositQuery[2]);
            }catch(NumberFormatException e){
                System.out.println("Enter a valid deposit amount!");
                return;
            }

            Customer customer = findCustomer(accNo);

            if(customer != null){
                customer.setBalance(customer.getBalance()+ amount);
            }
            else{
                System.out.println("Account not found! Enter a valid account number");
            }

        }
        else{
            System.out.println("Enter a valid deposit command!");
        }

    }

    public static void withdrawAmount(String command){

        String[] withdrawQuery = command.split(" ");
        if(withdrawQuery.length == 3 && withdrawQuery[0].equals("WITHDRAW")){
            String action = withdrawQuery[0];
            String accNo = withdrawQuery[1];
            int amount = 0;
            try{
                amount = Integer.parseInt(withdrawQuery[2]);
            }catch(NumberFormatException e){
                System.out.println("Enter a valid withdraw amount!");
                return;
            }

            Customer customer = findCustomer(accNo);

            if(customer != null){
                if((customer.getBalance() - amount) < 0){
                    System.out.println("Insufficient Balance!");
                }
                else{
                    customer.setBalance(customer.getBalance() - amount);
                }
            }
            else{
                System.out.println("Account not found! Enter a valid account number");
            }

        }
        else{
            System.out.println("Enter a valid withdraw command!");
        }
    }

    public static void checkBalance(String command){
        String[] balanceQuery = command.split(" ");

        if(balanceQuery.length == 2 && balanceQuery[0].equals("BALANCE")){
            String action = balanceQuery[0];
            String accNo = balanceQuery[1];

            Customer customer = findCustomer(accNo);

            if(customer != null){
                System.out.println(customer.getCustName()+" "+customer.getBalance());
            }
            else{
                System.out.println("Account not found! Enter a valid account number");
            }
        }
        else{
            System.out.println("Enter a valid balance command!");
        }

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<Customer> customerList = new ArrayList<>();

        String query = "";
        do {
            System.out.println("Enter the following command: \n" +
                    "1. To Create Account: CREATE ACC.NO. NAME \n" +
                    "2. To Deposit: DEPOSIT ACC.NO. AMOUNT \n" +
                    "3. To Withdraw: WITHDRAW ACC.NO. AMOUNT \n" +
                    "4. To Check Balance: BALANCE ACC.NO. \n");
            query = sc.nextLine();

            if(query.contains("CREATE")){
                createAccount(query);
            }else if(query.contains("DEPOSIT")){
                depositAmount(query);
            }else if(query.contains("WITHDRAW")){
                withdrawAmount(query);
            }else if(query.contains("BALANCE")){
                checkBalance(query);
            }else{
                System.out.println("Please enter a valid command!");
            }

            System.out.println("Do you want to continue? 1/0");
        }while(!sc.nextLine().equals("0"));
    }
}
