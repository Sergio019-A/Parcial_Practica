module co.edu.poli.examen2_Acero {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens co.edu.poli.examen2_Acero.vista to javafx.fxml, javafx.graphics;
    opens co.edu.poli.examen2_Acero.controlador to javafx.fxml;
    exports co.edu.poli.examen2_Acero.controlador;
    exports co.edu.poli.examen2_Acero.modelo;
    exports co.edu.poli.examen2_Acero.servicios;
    exports co.edu.poli.examen2_Acero.vista;
}
