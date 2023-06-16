import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

// CommonIOはインスタンス化して利用しなくてもいいのでクラス変数とクラスメソッドで作成
public class CommonIO {

  private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
// ・private 同じクラス内でしか使用できない
    // ・static インスタンス化しなくても使用できる
    // ・final 定数なのでBRという変数は別の値に上書きできない
    // ・BR.readLine()というふうに同じクラス内で呼び出せば文字入力が可能になる
    // ・BufferedReader クラスは、テキストを行単位で読み取るためのメソッドを提供する
    private static void showMenu(Map<Integer, String> menu) {
       //引数で渡された配列をメッセージで表示
    menu.forEach((key, value) -> System.out.print(key + ":" + value + " "));
    System.out.println();
  }// メニュー番号入力

  public static int inputMenu(Map<Integer, String> menu) {
    while (true) {//ループ開始
      showMenu(menu);//　預け入れ,引き出し,残高照会,終了　メッセージを表示する
      int input = inputNum();//キー入力用メソッドを呼び出して変数に整数値を代入させる

      // menuの範囲内であるかを判断する
            // 1〜4までの数値ならば入力値をreturnして処理終了
            // 範囲外の場合はwhile文の開始地点に戻りループ
      if (input > 0 && input <= menu.size()) {
        return input;
      }

      System.out.println("該当する半角数字で入力してください");
    }
  }
// 数字入力
  public static int inputNum() {
    try {
      return Integer.parseInt(BR.readLine());
} catch (NumberFormatException | IOException e) {
       // NumberFormatException 数値に変換できない例外が発生した場合（数字以外や空白を入力したなど
        // IOException 入力中になんらかのトラブル（エラー）が発生した場合に出力される　ファイルが開けない、入力ストリームが閉じられた場合など
      return 0;
    }
  }
}
