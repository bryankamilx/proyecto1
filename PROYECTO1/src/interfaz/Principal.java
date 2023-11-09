package interfaz;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logica.SistemaAlquiler;

public class Principal extends Application {

    private SistemaAlquiler sistema;
    private Label labelResultado;
    private Button btnIniciarEmpleado;
    private Button btnIniciarAdmin;
    private Button btnIniciarAdminLocal;
    private Scene escenaPrincipal;  // Variable para almacenar la escena principal

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Alquiler de Vehículos");

        // Crear el sistema y cargar datos
        sistema = new SistemaAlquiler();

        // Elementos de la interfaz
        Button btnRegistrarse = new Button("Registrarse");
        Button btnIniciarSesion = new Button("Iniciar Sesión");
        Button btnOpcionesAvanzadas = new Button("Opciones Avanzadas");
        btnIniciarEmpleado = new Button("Iniciar Sesión como Empleado");
        btnIniciarAdmin = new Button("Iniciar Sesión como Administrador");
        btnIniciarAdminLocal = new Button("Iniciar Sesión como Administrador Local");
        Button btnSalir = new Button("Salir");
        labelResultado = new Label();

        // Eventos de los botones
        btnRegistrarse.setOnAction(e -> abrirVentanaRegistro());
        btnIniciarSesion.setOnAction(e -> abrirVentanaIniciarSesion());
        btnOpcionesAvanzadas.setOnAction(e -> mostrarOpcionesAvanzadas(primaryStage));
        btnIniciarEmpleado.setOnAction(e -> iniciarSesionEmpleado());
        btnIniciarAdmin.setOnAction(e -> iniciarSesionAdministrador());
        btnIniciarAdminLocal.setOnAction(e -> iniciarSesionAdminLocal());
        btnSalir.setOnAction(e -> primaryStage.close());

        // Diseño de la interfaz
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(btnRegistrarse, btnIniciarSesion, btnOpcionesAvanzadas, btnSalir, labelResultado);

        // Escena
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);

        // Almacenar la escena principal
        escenaPrincipal = scene;

        // Mostrar la interfaz
        primaryStage.show();
    }

    private void abrirVentanaRegistro() {
        // Lógica para abrir la ventana de registro
        labelResultado.setText("Ventana de Registro");
    }

    private void abrirVentanaIniciarSesion() {
        // Lógica para abrir la ventana de inicio de sesión
        labelResultado.setText("Ventana de Inicio de Sesión");
    }

    private void mostrarOpcionesAvanzadas(Stage primaryStage) {
        // Lógica para mostrar los botones de opciones avanzadas
        VBox opcionesAvanzadasLayout = new VBox(10);
        opcionesAvanzadasLayout.setPadding(new Insets(10));
        opcionesAvanzadasLayout.getChildren().addAll(btnIniciarEmpleado, btnIniciarAdmin, btnIniciarAdminLocal, crearBtnRegresar(primaryStage));
        Scene opcionesAvanzadasScene = new Scene(opcionesAvanzadasLayout, 400, 200);

        // Actualizar la escena actual
        primaryStage.setScene(opcionesAvanzadasScene);
    }

    private Button crearBtnRegresar(Stage primaryStage) {
        Button btnRegresar = new Button("Regresar");
        btnRegresar.setOnAction(e -> primaryStage.setScene(escenaPrincipal));  // Restaurar la escena principal
        return btnRegresar;
    }

    private void iniciarSesionEmpleado() {
        // Lógica para iniciar sesión como empleado
        labelResultado.setText("Iniciar Sesión como Empleado");
    }

    private void iniciarSesionAdministrador() {
        // Lógica para iniciar sesión como administrador
        labelResultado.setText("Iniciar Sesión como Administrador");
    }

    private void iniciarSesionAdminLocal() {
        // Lógica para iniciar sesión como administrador local
        labelResultado.setText("Iniciar Sesión como Administrador Local");
    }
}
