import java.util.*;

class InvalidPinException extends Exception {
    InvalidPinException(String str) {
        super(str);
    }
}

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String str) {
        super(str);
    }
}

class ATM {
    private int pin = 1234;
    private int balance = 5000;

    public void pinValidate(int p) throws InvalidPinException {
        if (p != pin) {
            throw new InvalidPinException("Invalid PIN");
        }
        System.out.println("Valid PIN");
    }

    public void checkBalance() {
        System.out.println("Available balance: " + balance);
    }

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposit successful");
    }

    public void withdraw(int amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        balance -= amount;
        System.out.println("Withdrawal successful");
    }
}



public class ExceptionHandling {
    public static void main(String[] args) {
          ATM a1 = new ATM();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter your PIN:");
            int p1 = sc.nextInt();
            a1.pinValidate(p1);

            System.out.println("1.Check Balance  2.Deposit  3.Withdraw");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    a1.checkBalance();
                    break;

                case 2:
                    System.out.println("Enter amount:");
                    int am = sc.nextInt();
                    a1.deposit(am);
                    break;

                case 3:
                    System.out.println("Enter withdraw amount:");
                    int amo = sc.nextInt();
                    a1.withdraw(amo);
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } catch (InvalidPinException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Thank you! Visit again!!");
        }
    }   
}
