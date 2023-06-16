import java.util.HashMap;
import java.util.Map;

class Bank {

  private Map<Integer, String> menu = new HashMap<Integer, String>();

  private ATM atm = new ATM();

  private Map<String, String> user = new HashMap<String, String>();

  public Bank() {
    user.put("name", "taro");
    user.put("balance", "10000");

    menu.put(1, "ATM 利用");
    menu.put(2, "終了");
  }

  public void selectMenu() {
    System.out.println("いらっしゃいませ、ようこそ SLS銀行へ");
    boolean isFinished = true;
    while (isFinished) {
      int inputMenu = CommonIO.inputMenu(this.menu);
      switch (inputMenu) {
        case 1:
          this.useATM();
          break;
        case 2:
          System.out.println("ご利用ありがとうございました、またのご利用お待ちしております");
          isFinished = false;
          break;
      }
    }
  }

  private void useATM() {
    this.atm.setUser(this.user);
    user = this.atm.selectMenu();
    
  }
}
