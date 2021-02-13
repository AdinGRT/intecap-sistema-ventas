package main;

import controlador.*;
import vistas.*;

/**
 *
 * @author Adin Rubio
 */
public class Main {
    public static void main(String[] args) {
        LoginFormulario vistaLogin = new LoginFormulario();
        ControladorVendedor controladorVendedor = new ControladorVendedor(vistaLogin);
        vistaLogin.setVisible(true);
    }
}
