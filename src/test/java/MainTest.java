import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testTranslateFunction() {
        Main main = new Main();
        try (BufferedReader br = new BufferedReader(new FileReader("src/input.txt"))) {
            String str = br.readLine();
            while (str != null) {
                assertEquals(str.split(" ", 2)[1], main.translate(str.split(" ", 2)[0]));
                str = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}