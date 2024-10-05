import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nrand
 */
public class PaintTest {
    
    public PaintTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of save method, of class Paint.
     */
    @Test
    public void testSave() {
	System.out.println("save");
	Paint instance = new Paint();
	instance.save();
    }

    /**
     * Test of undo method, of class Paint.
     */
    @Test
    public void testUndo() {
	System.out.println("undo");
	Paint instance = new Paint();
	instance.undo();
	
    }

    /**
     * Test of redo method, of class Paint.
     */
    @Test
    public void testRedo() {
	System.out.println("redo");
	Paint instance = new Paint();
	instance.redo();
	
    }
}