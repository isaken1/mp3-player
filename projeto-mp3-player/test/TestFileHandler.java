import org.junit.jupiter.api.Test;
import IO.FileHandler;

import java.io.IOException;

public class TestFileHandler{

    @Test
    void testConstrutor() {
        try {
            FileHandler handler = new FileHandler();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
