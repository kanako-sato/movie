/*package application;
 */

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class movie extends Application {
    protected StackPane Pane;
    protected Scene  scene;
    protected File f;
    protected Image img;
    protected ImageView imgView;
    protected Media Video;
    protected MediaPlayer Play;
    protected MediaView mediaView = new MediaView();
    protected Rectangle rect;
    protected Color clr;
    protected double opacity = 0;
    String log = "1";

    public void start(Stage primaryStage) throws Exception {
        try {
            Rectangle2D     primaryScreenBounds     = null;                     // ディスプレイサイズ

            // ディスプレイサイズを取得
            primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            // シーングラフを作成
            Pane        = new StackPane();
/*
            ///////////////////////////////////////////////////////////////////////
            // 画像ファイルのパスを取得
            f = new File("/Users/satokanako/Desktop/ISDL/研究/輝度測定データ/ディスプレイ/使用画像一覧/白.jpeg");

            // 画像をインスタンス化
            img = new Image(f.toURI().toString());
            imgView = new ImageView(img);

            imgView.setFitWidth(1920);
            imgView.setFitHeight(1080);
            ///////////////////////////////////////////////////////////////////////
*/
            // シーンを追加
            scene   = new Scene(Pane, primaryScreenBounds.getWidth(),
                    primaryScreenBounds.getHeight()*2);

            //動画の枠の設定
            //mediaView.setFitHeight(primaryScreenBounds.getHeight());
            mediaView.setFitWidth(primaryScreenBounds.getWidth());
            Pane.getChildren().add(mediaView);

            //矩形
            rect = new Rectangle(0,0,primaryScreenBounds.getWidth()+50,(primaryScreenBounds.getHeight()+50)*2);
            clr = new Color(0, 0, 0, 1);
            rect.setStroke(clr);//線の色を指定
            rect.setFill(clr);//塗りつぶしをしない
            //Pane.getChildren().add(rect);

            //////////////////////////////////////////////////// シーングラフに追加
            //Pane.getChildren().add(imgView);
            ////////////////////////////////////////////////////

            Pane.getChildren().add(rect);
            // ステージ設定
            primaryStage.setFullScreen(true);
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setFullScreenExitHint("");
            primaryStage.setTitle("VideoPlay");
            primaryStage.setScene(scene);
            primaryStage.show();
            scene.setOnKeyPressed(new MyKeyEventHandler());

            // マウス右クリックでポップアップメニューを表示
            ContextMenu popup = createContextMenu(primaryStage);
            Pane.setOnContextMenuRequested(event -> {
                popup.show(primaryStage, event.getScreenX(), event.getScreenY());
            });

            // マウスホイール操作でウィンドウの大きさを変更
            scene.setOnScroll(event -> {
                if (event.isControlDown()) {
                    zoom(event.getDeltaY() > 0 ? 1.1 : 0.9, primaryStage);
                }
            });

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void zoom(double factor, Stage stage) {
        stage.setWidth(stage.getWidth() * factor);
        stage.setHeight(stage.getHeight() * factor);
    }

    private ContextMenu createContextMenu(Stage stage) {
        MenuItem exitItem = new MenuItem("終了");
        exitItem.setStyle("-fx-font-size: 2em");
        exitItem.setOnAction(event -> {
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        ContextMenu popup = new ContextMenu(exitItem);
        return popup;
    }

    class MyKeyEventHandler implements EventHandler<KeyEvent>{
        public void handle(KeyEvent e) {
            KeyCode k = e.getCode();
            switch (k) {
                case ENTER: //黒画像
                    System.out.println("Enterを押下しました。");

                    clr = new Color(0, 0, 0, 1);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りつぶしをしない

                    break;

                case DIGIT0: //KICK
                    System.out.println("0を押下しました。");

                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定

                    // 動画ファイルのパスを取得
                    f = new File("/Users/satokanako/Desktop/M1070003.MP4");

                    // 動画再生クラスをインスタンス化
                    Video = new Media(f.toURI().toString());
                    Play = new MediaPlayer(Video);
                    Play.setVolume(0);

                    //動画の更新
                    mediaView.setMediaPlayer(Play);

                    //　ループ再生
                    Play.setCycleCount(MediaPlayer.INDEFINITE);

                    //動画再生
                    Play.play();

                    break;

                case DIGIT1: //海
                    System.out.println("1を押下しました。");

                    //四角の色更新
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定

                    // 動画ファイルのパスを取得
                    f = new File("/Users/satokanako/Downloads/擬似窓動画/Beatiful_rocks_in_the_beach.mp4");

                    // 動画再生クラスをインスタンス化
                    Video = new Media(f.toURI().toString());
                    Play = new MediaPlayer(Video);
                    Play.setVolume(0);

                    //動画更新
                    mediaView.setMediaPlayer(Play);

                    //　ループ再生
                    Play.setCycleCount(MediaPlayer.INDEFINITE);

                    Play.play();

                    break;

                case DIGIT2: //暗くする
                    opacity = opacity - 0.05;
                    if(opacity<0)
                        opacity = 0;

                    //透過度更新
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定

                    System.out.println(opacity);

                    log = log + "," + opacity;

                    break;

                case DIGIT3: //明るくする
                    opacity = opacity + 0.05;
                    if(opacity>1)
                        opacity = 1;

                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定

                    System.out.println(opacity);
                    log = log + "," + opacity;
                    break;

                case DIGIT5: // 0
                    opacity = 0;
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定
                    break;
                case DIGIT6: // 0.25
                    opacity = 0.25;
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定
                    break;
                case DIGIT7: // 0.45
                    opacity = 0.45;
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定
                    break;
                case DIGIT8: // 0.6
                    opacity = 0.6;
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定
                    break;
                case DIGIT9: // 0.7
                    opacity = 0.7;
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定
                    break;
                case A: // 0.55（従来と同等）
                    opacity = 0.55;
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定
                    break;
                case S: // 0.95（最小）
                    opacity = 0.95;
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定
                    break;

                case COMMA: //pause
                    Play.pause();
                    break;
                case PERIOD: //play
                    Play.play();
                    break;
                case Z:
                    exportCsv(log);
            }
        }
    }

    public static void exportCsv(String log){

        try {

            // 出力ファイルの作成
            FileWriter file = new FileWriter("/Users/satokanako/Desktop/result.csv", false);
            PrintWriter p = new PrintWriter(new BufferedWriter(file));

            // 内容をセットする
            p.print(log);

            // ファイルに書き出し閉じる
            p.close();

            System.out.println("ファイル出力完了");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
