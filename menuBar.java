import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class menuBar {
    boolean saved = false;
    Draw h = new Draw();
    
    void menu(Canvas canvas, BorderPane pane, GraphicsContext gc, Stage primaryStage, StackPane stack, Scene scene) throws FileNotFoundException {
        h.draw(canvas, pane, gc, primaryStage, stack, scene);//Calls objects giving variables they need 
        primaryStage.setTitle("Paint");
         
        
        //Makes new menu and adds all option to the dropdown
        MenuBar menu = new MenuBar(); 
        
        menu.prefWidthProperty().bind(primaryStage.widthProperty()); 
        Menu file = new Menu("File");      
        Menu edit = new Menu("Edit");     
        MenuItem open = new MenuItem("Open");
        MenuItem saveas = new MenuItem("Save As");
        MenuItem save = new MenuItem("Save");
        MenuItem close = new MenuItem("Close");
        MenuItem undo = new MenuItem("Undo");
        MenuItem redo = new MenuItem("Redo");
        //OPens file from file selector
        
        fileFunctions b = new fileFunctions();
        
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));//Open will execute if clicked on or Ctrl+C
        open.setOnAction(e->{
            b.open(primaryStage, canvas, gc, saved);
            h.base(canvas);
        });
        undo.setAccelerator(new KeyCodeCombination(KeyCode.U, KeyCombination.CONTROL_DOWN));//Open will execute if clicked on or Ctrl+C
        undo.setOnAction(e->{
           h.Undo(gc);
        });
        redo.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));//Open will execute if clicked on or Ctrl+C
        redo.setOnAction(e->{
           h.redo(gc, canvas);
        });
        //Declares a new save object
        Save a = new Save();
        //Save As
        saveas.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        saveas.setOnAction(e->{
           a.saveAs(primaryStage, canvas);
           saved=true;
           primaryStage.setTitle("Paint");
        });
        //Save
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));//Save will open on click or ctrl+s
        save.setOnAction(e->{
            a.save(primaryStage, canvas);
            saved=true;
            primaryStage.setTitle("Paint");
        });
        close.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));//Exit will open on click or ctrl+e
        close.setOnAction(e->{
            b.exit(primaryStage, canvas, a, saved);
        });
        
      
            //Sets everything on the file
        menu.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.setTop(menu);  
        file.getItems().add(open);
        file.getItems().add(saveas);
        file.getItems().add(save);
        edit.getItems().add(undo);
        edit.getItems().add(redo);
        file.getItems().add(close);
        menu.getMenus().addAll(file, edit); //Opens menu for file menu
    }
}