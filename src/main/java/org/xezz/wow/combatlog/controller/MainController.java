package org.xezz.wow.combatlog.controller;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.xezz.wow.combatlog.model.LogModel;
import org.xezz.wow.log.LogTask;
import org.xezz.wow.log.helper.IdleType;

import java.io.File;

public class MainController {

    public TextArea logOutputTextArea;
    public TextField selectedCombatLogTextField;
    public TextField selectedOutputDirectoryTextField;
    public TextField combatDurationTextField;
    public CheckBox deleteInputFileCheckBox;
    public RadioButton secondsRadioButton;
    public RadioButton minutesRadioButton;
    public CheckBox createSubdirectoryCheckBox;
    public Button startSplittingButton;
    public ToggleGroup durationToggleGroup;
    public Button combatLogSelectButton;
    public Button outputDirectorySelectButton;
    private LogModel model = new LogModel();


    public void selectCombatLogFired(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select WowCombatLog file");
        final File file = fileChooser.showOpenDialog(null);
        if (null != file) {
            model.setCombatLogLocation(file.toPath());
            selectedCombatLogTextField.setText(model.getCombatLogLocation().toString());
        }
    }

    public void selectOutputDirectoryFired(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select output directory");
        final File directory = directoryChooser.showDialog(null);
        if (null != directory) {
            model.setOutputDirectoryLocation(directory.toPath());
            selectedOutputDirectoryTextField.setText(model.getOutputDirectoryLocation().toString());
        }
    }

    public void startSplittingFired(ActionEvent actionEvent) {
        logOutputTextArea.setText("");
        combatLogSelectButton.setDisable(true);
        outputDirectorySelectButton.setDisable(true);
        startSplittingButton.setDisable(true);
        final String userData = (String) durationToggleGroup.getSelectedToggle().getUserData();
        logOutputTextArea.appendText(userData);
        model.setIdleType(IdleType.byValue(userData));
        int idleTime = 3;
        try {
            idleTime = Integer.parseInt(combatDurationTextField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();  // FIXME: Show a dialog
        }
        model.setIdleTime(idleTime);
        final LogTask logSplitter = new LogTask(model.getCombatLogLocation(), model.getOutputDirectoryLocation(), model.getIdleType(), model.getIdleTime());
        logOutputTextArea.textProperty().bind(logSplitter.getNotifications());
        logSplitter.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                processingFinished();
            }
        });
        //Platform.runLater(logSplitter);
        new Thread(logSplitter).start();

    }


    private void processingFinished() {
        combatLogSelectButton.setDisable(false);
        outputDirectorySelectButton.setDisable(false);
        startSplittingButton.setDisable(false);
        logOutputTextArea.textProperty().unbind();

    }


}
