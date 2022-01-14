package tubryansk.lisitsyn.cryptapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Controller {
    private Data data = new Data();
    @FXML
    public TextField byteCrypt;
    @FXML
    public TextField byteText;
    @FXML
    public TextField TextKey;
    @FXML
    public TextField TextR;
    @FXML
    public Button ButtonChooseKey;
    @FXML
    public TextArea TextSrc;
    @FXML
    public Button ButtonChooseFile;
    @FXML
    public Button ButtonEncrypt;
    @FXML
    public TextArea TextCrypt;
    @FXML
    public TextArea TextDecrypt;
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
            saveTextToFile(TextCrypt.getText(), file);
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

    public void buttonClickEncrypt(MouseEvent mouseEvent) {
        // Проверка на пустой текст
        if (TextSrc.getText().isEmpty())
            return;
        // Проверка ввели мы ключ или используем стандартный
        if (TextKey.getText().isEmpty())
            data.setDefaultKey();
        else
            data.setByteKey(TextKey.getText().getBytes(StandardCharsets.UTF_8));
        // Проверка ввели ли мы кол-во раундов
        if (!TextR.getText().isEmpty()) {
            data.setR(Short.parseShort(TextR.getText()));
            RC6.setR(data.getR());
        }
        // Перевод текста в байт код
        data.setByteText(TextSrc.getText().getBytes(StandardCharsets.UTF_8));
        // Шифровка текста
        data.setByteCryptText(RC6.encrypt(data.getByteText(), data.getByteKey()));
        // Вывод текста в приложение
        TextCrypt.setText(new String(data.getByteCryptText(), StandardCharsets.UTF_8));
        // Вывод текста в 16 форме
        byteText.setText(data.byteArrayToHex(data.getByteText()));
        byteCrypt.setText(data.byteArrayToHex(data.getByteCryptText()));
    }

    public void buttonClickDecrypt(MouseEvent mouseEvent) {
        // Проверка на пустой текст
        if (TextCrypt.getText().isEmpty())
            return;
        // Проверка ввели мы ключ или используем стандартный
        if (TextKey.getText().isEmpty())
            data.setDefaultKey();
        else
            data.setByteKey(TextKey.getText().getBytes(StandardCharsets.UTF_8));
        // Проверка ввели ли мы кол-во раундов
        if (!TextR.getText().isEmpty()) {
            data.setR(Short.parseShort(TextR.getText()));
            RC6.setR(data.getR());
        }
        // Расшифровка текста
        data.setByteDecryptText(RC6.decrypt(data.hexStringToByteArray(byteCrypt.getText()), data.getByteKey()));
        // Вывод текста на экран
        TextDecrypt.setText(new String(data.getByteDecryptText(), StandardCharsets.UTF_8));
    }
}