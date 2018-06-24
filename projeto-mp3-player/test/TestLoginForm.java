import App.Auth;
import UI.LoginForm;
import org.junit.jupiter.api.Test;

public class TestLoginForm {

    @Test
    void testLoginForm() {
        Auth a = new Auth();
        LoginForm dialog = new LoginForm(a);
    }
}