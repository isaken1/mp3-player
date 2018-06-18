import App.Usuario;
import org.junit.jupiter.api.Test;
import IO.FileHandler;

import java.util.ArrayList;

public class TestFileHandler{

    @Test
    void testConstrutor() {
        FileHandler handler = new FileHandler();
    }

    @Test
    void testInsercaoUsuario() {
        FileHandler handler = new FileHandler();
        ArrayList<Usuario> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Usuario u = new Usuario("T" + i, "abcd");
            handler.inserirUsuario(u);
        }
    }

    @Test
    void testLeitura() {
        FileHandler handler = new FileHandler();
        ArrayList<Usuario> list = new ArrayList<>();

        try {
            list = handler.resgatarUsuarios();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (Usuario u : list) {
            System.out.println(u.getNome());
        }
    }
}
