# 学習記録

## 自分が難しく感じたところや調べたこと

logoutはSpringSecurityを使用しているとPOST送信でしか受け付けない
 →CSRF対策でSpringSecurity がPOSTのみを受け付けるようになっている

認証情報の処理の流れ
* AuthenticationとUserDetails、UserDetailsServiceの役割
 * Authentication－現在のユーザーの認証状態と認証情報を保持するオブジェクト
 * UserDetails－Spring Securityが認証時に利用するユーザー情報の構造を定義するインターフェース
 * UserDetailsService－ユーザー名をもとに UserDetails を取得するサービスインターフェース
* Auhenticationとprincipalの違い
 * auhtentication－principal(UserDetails)以外にも認証情報や状態を持っている
 * principal－UserDetailsに関する情報のみ

* Modelと@ModelAttributeの違い
 * Model－ビューに値を渡すときに使用する(一覧表示など)
 * @ModelAttribute－formの値を受け取るときに使用する

CustomValidationアノテーションの定義
アノテーションインターフェース
@interface = アノテーションインターフェースの定義
@Target = アノテーションをつける対象の形式を指定（クラス、メソッド、フィールド）
@Retention() = どの段階でアノテーションがどのタイミングで保持されるかを決める
RUNTIMEが一般的
@Constraint() = バリデーションを実行するクラスを指定
ConstraintValidator<> = バリデーションを実行するisValidメソッドを提供するカスタムバリデーションの実装インターフェース
ConstraintValidatorContext クラス = バリデーションの詳細設定やエラーメッセージのカスタマイズを行いたいときに使える強力なツール

MyBatisでのキーワード検索
プレースホルダ#{key}でバインド
CONCAT('%', #{key}, '%')で文字列結合しワイルドカードにする

日付の扱い
現在はDate型はLocalDate型が推奨されていて、Timestamp型はLocalDateTime型が推奨されている

アプリ設計面
認証情報とUser情報の区分
principalに認証情報以外も入れてもいいか






