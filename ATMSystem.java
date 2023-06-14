import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSystem {

    public Map<Integer, String> menu = new HashMap<Integer, String>(); // ATMのメニュー
    private Scanner scanner; // scannerインスタンス
    private int balance;

    public ATMSystem() {
        menu.put(1, "預け入れ");
        menu.put(2, "引き出し");
        menu.put(3, "残高照会");
        menu.put(4, "終了");

        scanner = new Scanner(System.in); // Scannerインスタンスの作成
    }

    public void mainSystem() {
        int inputNumber = this.inputNumber();
        this.selectMessage(inputNumber);
    }

    // ここからSystemメッセージ

    public int inputNumber() {
        int number = 0;

        do {
            this.showMenu(menu);
            String systemInput = scanner.next(); // 文字列の入力を受け付ける
            if (systemInput.matches("[1-4]")) { // 1〜4までの数値かどうか判定する
                number = Integer.parseInt(systemInput); // 文字列型から整数値型に変換して代入する
                break; // ループを抜ける
            } else {
                System.out.println("該当する半角数字で入力してください");
            }
        } while (true);

        return number;
    }

    public void selectMessage(int inputNumber) {
        switch (inputNumber) {
            case 1:
                System.out.println("ご入金額を入力してください");
                DepositHandler.depositHandler();
                mainSystem();

                break;
            case 2:
                break;
            case 3:
                System.out.println("残高は " + getBalance() + " 円です");
                break;
            case 4:
                systemExit();
                break;
        }
    }



    public void showMenu(Map<Integer, String> menu) {
        menu.forEach((key, value) -> System.out.print(key + ":" + value + " "));
        System.out.println("");
    }

    public void systemDeposit() {
    }

    public void systemBalance() {
    }

    public void systemExit() {
        System.out.println("ATM のご利用ありがとうございました");
    }

    // ここまでSystemメッセージ

    // ここからゲッターとセッターのメソッド

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
    // ここまでゲッターとセッターのメソッド

    public static void main(String[] args) {
        ATMSystem atm = new ATMSystem();
        atm.mainSystem();
    }
}