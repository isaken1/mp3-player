import App.Usuario;
import org.junit.jupiter.api.Test;
import IO.FileHandler;

import java.util.ArrayList;

public class TestFileHandler{

//    @Test
//    void testConstrutor() {
//        FileHandler handler = new FileHandler();
//    }

    @Test
    void testInsercaoUsuario() {
        FileHandler handler = new FileHandler();
        for (int i = 0; i < 10; i++) {
            handler.inserirUsuario("T" + i, "abcd", false);
        }
    }
//
//    @Test
//    void testLeitura() {
//        FileHandler handler = new FileHandler();
//        ArrayList<Usuario> list = new ArrayList<>();
//
//        try {
//            list = handler.resgatarUsuarios();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        for (Usuario u : list) {
//            System.out.println(u.getNome());
//        }
//    }
}
