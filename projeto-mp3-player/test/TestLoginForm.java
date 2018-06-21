import UI.LoginForm;
import org.junit.jupiter.api.Test;

public class TestLoginForm {

    @Test
    void testLoginForm() {
        LoginForm dialog = new LoginForm();
        dialog.setVisible(true);
    }
}
;