package tubryansk.lisitsyn.cryptapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Controller {
    private byte[] temp;
    @FXML
    public TextField TextKey;
    @FXML
    public Button ButtonChooseKey;
    @FXML
    public Button ButtonApply;
    @FXML
    public TextArea TextSrc;
    @FXML
    public Button ButtonChooseFile;
    @FXML
    public Button ButtonEncrypt;
    @FXML
    public TextArea TextCode;
    @FXML
    public Button ButtonSave;
    @FXML
    public Button ButtonDecrypt;

    public void buttonClickChooseFile(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TEXT", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append('\n');
            }
            TextSrc.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void buttonClickChooseKey(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TEXT", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append('\n');
            }
            TextKey.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void buttonClickSave(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            saveTextToFile(TextCode.getText(), file);
        }
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
        }
    }

    public void buttonClickApply(MouseEvent mouseEvent) {

    }

    public void buttonClickEncrypt(MouseEvent mouseEvent) {
        if (TextSrc.getText().isEmpty())
            return;
        byte[] text = TextSrc.getText().getBytes(StandardCharsets.UTF_8);
        byte[] key = "1234567890".getBytes(StandardCharsets.UTF_8);
        if (!TextKey.getText().isEmpty())
            key = TextKey.getText().getBytes(StandardCharsets.UTF_8);
        byte[] newText = RC6.encrypt(text, key);
        temp = newText;
        TextCode.setText(new String(newText, StandardCharsets.UTF_8));
    }

    public void buttonClickDecrypt(MouseEvent mouseEvent) {
        if (TextCode.getText().isEmpty())
            return;
        byte[] key = "1234567890".getBytes(StandardCharsets.UTF_8);
        if (!TextKey.getText().isEmpty())
            key = TextKey.getText().getBytes(StandardCharsets.UTF_8);
        byte[] text = RC6.decrypt(temp, key);
        TextCode.setText(new String(text, StandardCharsets.UTF_8));
    }
}