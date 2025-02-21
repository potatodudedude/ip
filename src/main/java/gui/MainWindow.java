package gui;

import dodo.Dodo;
import dodo.command.ReminderCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dodo dodo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Wojak.jpeg"));
    private Image dodoImage = new Image(this.getClass().getResourceAsStream("/images/dodo.jpeg"));

    /** Initialise gui elements */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDodo(Dodo d) {
        dodo = d;
        try {
            dodo.run();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        sendDodoMessage(dodo.getUi().getIntroMessage(), "noCommand");

        ReminderCommand reminder = new ReminderCommand();
        sendDodoMessage(reminder.execute(dodo.getTasks(), dodo.getUi(), dodo.getStorage()), reminder.getCommandType());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dodo.getResponse(input);
        String commandType = dodo.getCommandType();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDodoDialog(response, dodoImage, commandType)
        );
        userInput.clear();
        if (response.equals(dodo.getUi().getByeMessage())) {
            Platform.exit();
            System.exit(0);
        }
    }

    /**
     * Sends the input message as a dodo DialogBox
     *
     * @param message Message to send through Dodo
     */
    private void sendDodoMessage(String message, String commandType) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDodoDialog(message, dodoImage, commandType)
        );
    }
}
