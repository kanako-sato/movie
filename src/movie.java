/*package application;
 */

import java.io.File;

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
import javafx.stage.WindowEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class movie extends Application {
    //Stage primaryStage = new Stage();
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

    public void start(Stage primaryStage) throws Exception {
        try {
            //primaryStage.initStyle(StageStyle.TRANSPARENT);
            //primaryStage.getScene().getRoot().setStyle(-fx-background-color: transparent");

            Rectangle2D     primaryScreenBounds     = null;                     // ディスプレイサイズ

            // ディスプレイサイズを取得
            primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            System.out.println(primaryScreenBounds.getWidth());
            System.out.println(primaryScreenBounds.getHeight());

            // シーングラフを作成
            Pane        = new StackPane();

            ///////////////////////////////////////////////////////////////////////
            // 画像ファイルのパスを取得
            f = new File("/Users/satokanako/Desktop/black.png");

            // 画像をインスタンス化
            img = new Image(f.toURI().toString());
            imgView = new ImageView(img);
            ///////////////////////////////////////////////////////////////////////

            // シーンを追加
            scene   = new Scene(Pane, primaryScreenBounds.getWidth(),
                    primaryScreenBounds.getHeight());

            //動画の枠の設定
            Pane.getChildren().add(mediaView);

            //矩形
            rect = new Rectangle(0,0,primaryScreenBounds.getWidth()+50,primaryScreenBounds.getHeight()+50);
            clr = new Color(0, 0, 0, 1);
            rect.setStroke(clr);//線の色を指定
            rect.setFill(clr);//塗りつぶしをしない
            Pane.getChildren().add(rect);

            //////////////////////////////////////////////////// シーングラフに追加
            Pane.getChildren().add(imgView);
            ////////////////////////////////////////////////////

            // ステージ設定
            primaryStage.setFullScreen(true);
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

        } catch(Exception e) {
            e.printStackTrace();
        }
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

                case DIGIT0: //クリスマス
                    System.out.println("0を押下しました。");

                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定

                    // 動画ファイルのパスを取得
                    f = new File("/Users/satokanako/Downloads/擬似窓動画/Christmas_Santa_Sleigh_Motion_Background_4K.mp4");

                    // 動画再生クラスをインスタンス化
                    Video = new Media(f.toURI().toString());
                    Play = new MediaPlayer(Video);

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
                    //動画更新
                    mediaView.setMediaPlayer(Play);

                    //　ループ再生
                    Play.setCycleCount(MediaPlayer.INDEFINITE);

                    Play.play();

                    break;

                case DIGIT2:
                    opacity = opacity - 0.05;
                    if(opacity<0)
                        opacity = 0;

                    //透過度更新
                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定

                    System.out.println(opacity);
                    break;

                case DIGIT3:
                    opacity = opacity + 0.05;
                    if(opacity>1)
                        opacity = 1;

                    clr = new Color(0, 0, 0, opacity);
                    rect.setStroke(clr);//線の色を指定
                    rect.setFill(clr);//塗りを指定

                    System.out.println(opacity);
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

