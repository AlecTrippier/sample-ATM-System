import java.util.HashMap;
import java.util.Map;

class ATM {

        private static final int MIN_DEPOSIT_AMOUNT = 1; // 預け入れ下限 1 円

        private static final int MAX_DEPOSIT_AMOUNT = 2000000; // 預け入れ上限 200 万円

        private static final int MIN_WITHDRAWAL_AMOUNT = 1; // 引き出し下限 1 円

        private static final int MAX_WITHDRAWAL_AMOUNT = 500000; // 引き出し上限 50 万円

        private Map<Integer, String> menu = new HashMap<Integer, String>();// 初期のメニュー選択肢を格納する
        private Map<String, String> user = new HashMap<String, String>();// 捜査対象のユーザー名を格納する

        public ATM() { // 捜査対象のユーザー名を格納する
                menu.put(1, "預け入れ");
                menu.put(2, "引き出し");
                menu.put(3, "残高照会");
                menu.put(4, "終了");
        }

        // メニュー選択
        public Map<String, String> selectMenu() {
                System.out.println(this.user.get("name") + " 様ようこそ"); // Mapからgetメソッドでnameを取得する
                boolean isFinished = true; // 繰り返し処理を実行するためtrueを格納
                while (isFinished) {// 繰り返し処理開始
                        int inputMenu = CommonIO.inputMenu(menu); // 初期メッセージを表示させるため引数に配列を渡す , 返り値としてキーの入力値（整数）が代入される
                        switch (inputMenu) {
                                case 1:
                                        // 預け入れ処理追加
                                        this.deposit();
                                        break;
                                case 2:
                                        // 引き出し処理追加
                                        this.withdrawal();
                                        break;
                                case 3:
                                        // 残高照会処理追加
                                        this.balanceInquiry();
                                        break;
                                case 4:
                                        // 終了
                                        System.out.println("ATM のご利用ありがとうございました");
                                        isFinished = false;
                                        break;
                        }
                }
                return this.user;
        }

        // 残高照会
        private void balanceInquiry() {
                // メッセージ： 残高はxx円です
                System.out.println("残高は " + this.user.get("balance") + " 円です");
        }

        // 預け入れ
        private void deposit() {// 預け入れ処理
                int amount = -1; // if文でtrueと評価しないよう初期値に範囲外の数値を代入しておく
                while (true) {
                        System.out.println("ご入金額を入力してください");
                        // CommonIOクラスのinputNumメソッドを使用する
                        amount = CommonIO.inputNum(); // 整数値入力メソッドで入金金額を代入
                        if (amount >= MIN_DEPOSIT_AMOUNT && amount <= MAX_DEPOSIT_AMOUNT) { // 入力された整数値が1から2000000の範囲内であればループから抜ける
                                break; // ループから抜ける
                        }
                        // メッセージ : 1円から200万円以内で入力してください
                        System.out.println(MIN_DEPOSIT_AMOUNT + " 円から "
                                        + (MAX_DEPOSIT_AMOUNT / 10000) + " 万円以内で入力してください");
                }
                // replaceは特定の要素や値を別の要素や値で置き換えるためのメソッド user.replace(元の要素, 置き換える要素);
                // マップのuserから"balance" キーに対応する値である 初期値の"10000" が入れ替えの対象となる
                // マップの"10000"は文字列なのでString.valueOfで文字列に整数値を変換している
                // userマップの"balance"キーに対応する値10000に預け入れ額をプラスした額を上書きする
                this.user.replace("balance", String.valueOf(Integer.parseInt(this.user.get("balance")) + amount));
                // メッセージ：xx円お預かりしました
                System.out.println(amount + " 円お預かりしました");
        }

        // 引き出し
        private void withdrawal() { // 引き出し処理
                // Integer.parseInt メソッドは文字列を整数値に変換する
                int balance = Integer.parseInt(this.user.get("balance"));
                // 残高がなければコマンドを終了させる
                if (balance <= 0) {
                        System.out.println("残高がありません");
                        return;
                }

                int amount = -1;// if文でtrueと評価しないよう初期値に範囲外の数値を代入しておく
                while (true) {
                        System.out.println("ご出金額を入力してください");
                        // CommonIOクラスのinputNumメソッドを使用する
                        amount = CommonIO.inputNum();// 入力用の外部メソッドで整数値を入力する
                        if (amount < MIN_WITHDRAWAL_AMOUNT || amount > MAX_WITHDRAWAL_AMOUNT) {// 1~500000までが範囲内
                                // メッセージ : 1円から50万円以内で入力してください
                                System.out.println(MIN_WITHDRAWAL_AMOUNT + " 円から "
                                                + (MAX_WITHDRAWAL_AMOUNT / 10000) + " 万円以内で入力してください");
                                continue;
                        }
                        if (amount <= balance) {// 引き出し額が残高以内であればループを抜ける
                                break;
                        }
                        // メッセージ : 残高が不足しています、残高は xx 円です
                        System.out.println("残高が不足しています、残高は " + this.user.get("balance") + " 円です");
                }
                // ループを抜けた場合出金額をメッセージする
                // メッセージ : xx 円お返ししました
                System.out.println(amount + " 円お返ししました");
                // replaceは特定の要素や値を別の要素や値で置き換えるためのメソッド user.replace(元の要素, 置き換える要素);
                // userマップの"balance"キーに対応する値10000に預け入れ額から出金額をマイナスした数を上書きする
                this.user.replace("balance", String.valueOf(balance - amount));
        }// ユーザー情報セット
         // 外部ファイル（Bank.java）から渡されたuserマップ情報の引数をこのファイルのuserマップに追加する

        // ユーザー情報セット
        public void setUser(Map<String, String> user) {
                this.user = user;
        }
}
