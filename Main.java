package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application {
    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        Scene mainScene = mainPage();
        primaryStage.setTitle("Pro-Active");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    //Main Pro Active Page
    public Scene mainPage(){
        Pane mainPageRoot = new Pane();

        Label proActiveName = new Label();
        proActiveName.setText("Pro-Active");
        //proActiveName.setTextFill(Color.WHITE); //UNCOMMENT THIS WHEN YOU HAVE BACKGROUND IMAGE
        proActiveName.setTextFill(Color.BLACK);
        proActiveName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,50));
        proActiveName.setTranslateX(200);
        proActiveName.setTranslateY(165);
        
        //Login button + styling
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setPrefSize(200,40);
        loginButton.setTranslateX(220); // negative = Left, positive = right
        loginButton.setTranslateY(290);

        loginButton.setOnAction(event -> mainStage.setScene(loginPage())); //connects to next scene
        loginButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.setScene(loginPage());
            }
        });

        //Registration Button + Styling
        Button registerButton = new Button();
        registerButton.setText("Register");
        registerButton.setPrefSize(200,40); //length, height

        registerButton.setTranslateX(220); // negative = Left, positive = right
        registerButton.setTranslateY(350);

        registerButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");

        registerButton.setOnAction(event -> mainStage.setScene(registrationPage()));


        //Background Image
        // String image = Main.class.getResource("fitness.png").toExternalForm();
        //mainPageRoot.setStyle("-fx-background-image: url('" + image + "');");


        mainPageRoot.getChildren().addAll(loginButton,registerButton,proActiveName);
        return new Scene(mainPageRoot,640,426);

    }

    protected Scene loginPage() {
        HBox loginText = new HBox();

        GridPane loginRoot = new GridPane();
        loginRoot.setStyle("-fx-background-color: #F7EDDE");
        loginRoot.setAlignment(Pos.CENTER);

        //loginText.setPadding(new Insets(50,100,100,100));
        // loginText.setStyle("-fx-background-color: #000000");

        Label login = new Label();
        login.setText("Login");
        // login.setTextFill(Color.WHITE); Uncomment when image has been added
        login.setTextFill(Color.BLACK);
        login.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        loginText.setTranslateX(-0);
        loginText.setTranslateY(-150);

        loginText.getChildren().addAll(login);

        //Username Field
        TextField userNameTextField = new TextField();
        userNameTextField.setPromptText("Username");
        userNameTextField.setPrefSize(450,40);
        userNameTextField.setTranslateX(-2);
        userNameTextField.setTranslateY(-10);

        //Password Field
        PasswordField userPassword = new PasswordField();
        userPassword.setPromptText("Password");
        userPassword.setPrefSize(450,40);
        userPassword.setTranslateX(-2);
        userPassword.setTranslateY(50);


        //Login button for the login page
        Button lButton = new Button();
        lButton.setText("Login");
        lButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        lButton.setPrefSize(200, 40);
        lButton.setTranslateX(120); // negative = Left, positive = right
        lButton.setTranslateY(150); //Bottom

        lButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //Error Handling
                Alert errorWarning = new Alert(Alert.AlertType.ERROR);
                errorWarning.setTitle("Error");
                errorWarning.setHeaderText("Oops Something went wrong");

                if(userNameTextField.getText().isEmpty() && userPassword.getText().isEmpty()){
                    errorWarning.setContentText("Forgot to enter your Username & Password");
                    errorWarning.show();
                }
                else if(userNameTextField.getText().isEmpty()){
                    errorWarning.setContentText("Forgot to enter your username");
                    errorWarning.show();
                }
                //Error Handling
                else if(userPassword.getText().isEmpty()){
                    errorWarning.setContentText("Forgot to enter your Password");
                    errorWarning.show();
                }
                else mainStage.setScene(accountPage());

            }
        });

        // lButton.setOnAction(errorMessage  -> System.out.println("Welcome" + userNameTextField.getText()));

        loginRoot.getChildren().addAll(loginText, lButton,userNameTextField, userPassword);

        return new Scene(loginRoot, 640, 426);
    }

    protected Scene registrationPage() {
        Pane registerRoot = new Pane();

        registerRoot.setStyle("-fx-background-color: #F7EDDE");

        Label registerPageName = new Label();
        registerPageName.setText("Register");
        registerPageName.setTextFill(Color.BLACK);
        registerPageName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        registerPageName.setTranslateX(50);
        registerPageName.setTranslateY(30);


        //Username Text field
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameTextField.setPrefSize(540,40);
        usernameTextField.setTranslateX(30);
        usernameTextField.setTranslateY(150);


        //Email Text field
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailTextField.setPrefSize(540,40);
        emailTextField.setTranslateX(30);
        emailTextField.setTranslateY(200);

        //Password Text field
        TextField passwordTextField = new TextField();
        passwordTextField.setPromptText("Password");
        passwordTextField.setPrefSize(540,40);
        passwordTextField.setTranslateX(30);
        passwordTextField.setTranslateY(250);

        //Confirm Password Text Field
        TextField confirmPasswordTextField = new TextField();
        confirmPasswordTextField.setPromptText("Confirm Password");
        confirmPasswordTextField.setPrefSize(540,40);
        confirmPasswordTextField.setTranslateX(30);
        confirmPasswordTextField.setTranslateY(300);


        //Register Button
        Button rButton = new Button();
        rButton.setText("Register");
        rButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        rButton.setPrefSize(200, 40);
        rButton.setTranslateX(220); // negative = Left, positive = right
        rButton.setTranslateY(370); //Bottom


        //Text field error handling

        rButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Alert errorWarning = new Alert(Alert.AlertType.ERROR);
                errorWarning.setTitle("Error");
                errorWarning.setHeaderText("Oops Something went wrong");

                if(usernameTextField.getText().isEmpty() && emailTextField.getText().isEmpty() &&
                        passwordTextField.getText().isEmpty() && confirmPasswordTextField.getText().isEmpty()){

                    errorWarning.setContentText("""
                            Forgot to enter your:
                            Username
                            Email
                            Password
                            Confirm Password""");
                    errorWarning.show();
                }
                else if(usernameTextField.getText().isEmpty()){
                    errorWarning.setContentText("Forgot to enter your username");
                    errorWarning.show();
                }
                else if(emailTextField.getText().isEmpty()){
                    errorWarning.setContentText("Forgot to enter your Email");
                    errorWarning.show();

                }
                else if(passwordTextField.getText().isEmpty()){
                    errorWarning.setContentText("Forgot to enter your Password");
                    errorWarning.show();
                }
                else if(confirmPasswordTextField.getText().isEmpty()){
                    errorWarning.setContentText("Forgot to Confirm your Password");
                    errorWarning.show();
                }

                else mainStage.setScene(accountPage()); //Takes user to Account Page

            }
        });


        registerRoot.getChildren().addAll(usernameTextField,registerPageName,rButton,emailTextField,passwordTextField,confirmPasswordTextField);

        return new Scene(registerRoot, 640, 426);

    }

    protected Scene accountPage() {
        Pane accountRoot = new Pane();
        accountRoot.setStyle("-fx-background-color: #F7EDDE");



        Label accountPageName = new Label();
        accountPageName.setText("Welcome <USERNAME> to Your Pro-Active");
        accountPageName.setTextFill(Color.BLACK);
        accountPageName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,20));
        accountPageName.setTranslateX(220);
        accountPageName.setTranslateY(5);

        accountRoot.getChildren().addAll(accountPageName);



        return new Scene(accountRoot, 640, 426);
    }






    public static void main(String[] args) {
        launch(args);
    }
}
