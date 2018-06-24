package Main;

import App.Auth;
import App.Usuario;
import UI.MainForm;

public class Main {

    static private Auth a;

    public static void main(String[] args) {
        a = new Auth();
        while (a.getLogado() == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        if (a.getLogado() != null) {
            Usuario l = a.getLogado();
            System.out.println(l.getNome());

            MainForm main = new MainForm();
        } else {
            System.out.println("Falha no login.");
        }
    }


}
