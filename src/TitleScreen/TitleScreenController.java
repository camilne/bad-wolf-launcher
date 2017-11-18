package TitleScreen;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * bad-wolf
 *
 * @author Kayso
 * date 11/17/2017
 * @version 1.0.0
 */
public class TitleScreenController implements Initializable{
    @FXML
    VBox vbox;
    @FXML
    private ImageView imageViewer;
    @FXML
    ComboBox<String> screenSizeOptions;

    private File video = new File("story.mp4");

    @FXML
    private MediaView videoPlayer;

    private File[] listOfFiles;
    private ArrayList<Image> avatars = new ArrayList<>();
    private int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        videoPlayer.setMediaPlayer(new MediaPlayer(new Media(video.toURI().toString())));
        videoPlayer.setVisible(false);
        screenSizeOptions.setItems(FXCollections.observableArrayList("Full Screen", "Medium"));
        screenSizeOptions.getSelectionModel().selectFirst();
        File folder = new File("avatars");
        listOfFiles = folder.listFiles((dir, name) -> !name.equals(".DS_Store"));
        if(listOfFiles == null)
            return;

        for (File file : listOfFiles) {
            if (file.isFile()) {
                avatars.add(new Image(file.toURI().toString(), 200, 200, true, true));
            }
        }

        imageViewer.setImage(avatars.get(0));
    }

    @FXML
    public void cycleCharRight(){
        if(index < avatars.size() - 1){
            index++;
        } else {
            index = 0;
        }
        imageViewer.setImage(avatars.get(index));

    }

    @FXML
    public void cycleCharLeft(){
        if(index > 0){
            index--;
        }
        else{
            index = avatars.size() - 1;
        }
        imageViewer.setImage(avatars.get(index));
    }

    @FXML
    public void launchGame() {
        vbox.getChildren().clear();
        videoPlayer.setVisible(true);
        videoPlayer.setY(50);
        videoPlayer.setPreserveRatio(true);
        videoPlayer.getMediaPlayer().play();
        videoPlayer.getMediaPlayer().setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder builder = new StringBuilder(
                            "java -jar bad-wolf.jar");
                    StringBuilder size = new StringBuilder();
                    if (screenSizeOptions.getValue().equals("Full Screen")) {
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        size.append((int) screenSize.getWidth() + " " + (int) screenSize.getHeight() + " " + "false");
                    } else {
                        size.append("1280 720 false");
                    }
                    Runtime.getRuntime().exec(builder.toString() + " " + listOfFiles[index].toString() + " " + size);
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
