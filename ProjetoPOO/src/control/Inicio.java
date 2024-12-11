package control;

import javax.swing.JOptionPane;

public class Inicio {

    public static void main(String[] args) {
        Controller inicioDoPrograma = new Controller();
        try {
            inicioDoPrograma.iniciarPrograma();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao iniciar Programa: " + ex.getMessage());
        }
    }
}