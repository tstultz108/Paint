import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * Creates a fileBar that is a custom/premade menubar
 */
public class FileBar extends Paint{
    
    /**
     * Keeps the current width of the canvas so resize can be called externally
     */
    public DoubleProperty canvasWidth  = new SimpleDoubleProperty();

    /**
     *Keeps the current height of the canvas so resize can be called externally
     */
    public DoubleProperty canvasHeight = new SimpleDoubleProperty();

    MenuBar menuBar = new MenuBar();
    
    Menu menuFile = new Menu("_File");
    MenuItem open = new MenuItem("Open");
    MenuItem save = new MenuItem("Save");
    MenuItem saveAs = new MenuItem("Save As");
    MenuItem undo = new MenuItem("Undo");
    MenuItem redo = new MenuItem("Redo");
    MenuItem exit = new MenuItem("Close");
    
    Menu menuEdit = new Menu("_Edit");
    MenuItem resize = new MenuItem("Resize Canvas");
    
    Menu menuView = new Menu("_View");
    MenuItem zoomIn = new MenuItem("Zoom In");
    MenuItem zoomOut = new MenuItem("Zoom Out");
    
    Menu menuHelp = new Menu("_Help");
    MenuItem about = new MenuItem("About");
    CheckMenuItem timerVisible = new CheckMenuItem("Autosave Timer Visible");
    MenuItem autoSave = new MenuItem("Set AutoSave Interval");
    
    /**
     * Constructs the premade filebar
     */
    public FileBar(){
    
    // --- Menu File
    
    //Open
    
    open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
    //save
    
    save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

    //saveas
    
    saveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_DOWN));


    //undo + redo
    
    undo.setAccelerator(new KeyCodeCombination(KeyCode.U, KeyCombination.CONTROL_DOWN));

    
    redo.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
    
    //Exit
    
    exit.setOnAction(new EventHandler<ActionEvent>() {
        boolean saved = false;
        public void handle(ActionEvent t) {
            while(!saved) {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to exit without saving?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.YES) {
                    System.exit(0);
                }
                if(alert.getResult() == ButtonType.NO) {
                    saved = true;
                }
            }
            saved = false;
        }
    });
    exit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));

    menuFile.getItems().addAll(open,save,saveAs,undo,redo,exit);



    // --- Menu Edit
    
    resize.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Resize Canvas");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField from = new TextField();
        from.setPromptText("From");
        TextField to = new TextField();
        to.setPromptText("To");

        gridPane.add(from, 0, 0);
        gridPane.add(new Label("To:"), 1, 0);
        gridPane.add(to, 2, 0);

        dialog.getDialogPane().setContent(gridPane);
        Platform.runLater(() -> from.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
            return new Pair<>(from.getText(), to.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(pair -> {
            canvasWidth.set(Double.parseDouble(pair.getKey()));
            canvasHeight.set(Double.parseDouble(pair.getValue())); 
        });
        }
    });
    
    resize.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
    menuEdit.getItems().add(resize);


    // --- Menu View
    
    zoomIn.setAccelerator(new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.CONTROL_DOWN));

    
    zoomOut.setAccelerator(new KeyCodeCombination(KeyCode.MINUS, KeyCombination.CONTROL_DOWN));

    menuView.getItems().addAll(zoomIn,zoomOut);

    // --- Menu Help
    
    menuHelp.getItems().addAll(about, timerVisible,autoSave);
    about.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
        TextArea textArea = new TextArea("Paint\n"
            + "This is a paint program designed to draw things on the screen.\n"
            + "The program can display a chosen image, and you can draw on the image.\n"
            + "If you would like to keep track of changes made to the project, please go to:\n\n"
            + "GitHub: https://github.com/tstultz108/Paint\n\n");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Window");
        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
        }
    });
        
    menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
    }
    
    
    
    
}