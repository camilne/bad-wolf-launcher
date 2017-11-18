package TitleScreen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private ImageView imageViewer;

    private ArrayList<Image> avatars = new ArrayList<>();
    private int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File folder = new File("resources/avatars");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                avatars.add(new Image(listOfFiles[i].toURI().toString(),200,200,true,true));
            }
        }

        //TODO: fix buttons and replace repo and fix colors

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
}
