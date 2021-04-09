

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.sql.SQLException;


public class Main extends Application {
    private Stage mainStage;
    public int ID;

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

        //Background Image

        BackgroundImage bgImage = new BackgroundImage(new Image(getClass().getResourceAsStream("proactiveIMG2.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        mainPageRoot.setBackground(new Background(bgImage));




        Label proActiveName = new Label();
        proActiveName.setText("Pro-Active");
        proActiveName.setTextFill(Color.WHITE); //UNCOMMENT THIS WHEN YOU HAVE BACKGROUND IMAGE
        //proActiveName.setTextFill(Color.BLACK);
        proActiveName.setFont(Font.font("PMingLiU-ExtB", FontWeight.EXTRA_BOLD,65));
        proActiveName.setTranslateX(340);
        proActiveName.setTranslateY(165);

        //Login button + styling
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setPrefSize(200,40);
        loginButton.setTranslateX(400); // negative = Left, positive = right
        loginButton.setTranslateY(290);
        loginButton.setStyle("" +
                        "-fx-background-radius: 5em; " +
                        "-fx-font: normal 20px 'Arial Nova Cond Light'"
        );


      loginButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              mainStage.setScene(loginPage());
          }
      });

        //connects to next scene

        //Registration Button + Styling
        Button registerButton = new Button();
        registerButton.setText("Register");
        registerButton.setPrefSize(200,40); //length, height

        registerButton.setTranslateX(400); // negative = Left, positive = right
        registerButton.setTranslateY(370);

        registerButton.setStyle("" +
                "-fx-background-radius: 5em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light'"
        );

        registerButton.setOnAction(event -> mainStage.setScene(registrationPage()));




        mainPageRoot.getChildren().addAll(loginButton,registerButton,proActiveName);
        return new Scene(mainPageRoot,1024,600);

    }

    protected Scene loginPage() {

        Pane loginRoot = new Pane();

        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("backgroundIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        loginRoot.setBackground(new Background(backgroundImage));


        HBox loginPageNameRoot = new HBox();
        BackgroundImage bgImage = new BackgroundImage(new Image(getClass().getResourceAsStream("headerIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        loginPageNameRoot.setBackground(new Background(bgImage));


        loginPageNameRoot.setPrefSize(1025,200);


        Label loginText = new Label();
        loginText.setText("Login");
        // login.setTextFill(Color.WHITE); Uncomment when image has been added
        loginText.setTextFill(Color.WHITE);
        loginText.setFont(Font.font("PMingLiU-ExtB", FontWeight.EXTRA_BOLD,60));
        loginText.setTranslateX(50);
        loginText.setTranslateY(60);

        loginPageNameRoot.getChildren().addAll(loginText);



        Label forgotPass = new Label("Forgot Password?");
        forgotPass.setFont(Font.font("Arial Nova Cond Light", FontPosture.ITALIC,13));
        forgotPass.setTranslateX(271);
        forgotPass.setTranslateY(410);


        //Username Field
        TextField userNameTextField = new TextField();
        userNameTextField.setPromptText("Username");
        userNameTextField.setPrefSize(450,40);
        userNameTextField.setTranslateX(270);
        userNameTextField.setTranslateY(300);

        //Password Field
        PasswordField userPassword = new PasswordField();
        userPassword.setPromptText("Password");
        userPassword.setPrefSize(450,40);
        userPassword.setTranslateX(270);
        userPassword.setTranslateY(360);


        //Login button for the login page
        Button lButton = new Button();
        lButton.setText("Login");
        lButton.setTextFill(Color.WHITE);
        lButton.setPrefSize(200, 40);
        lButton.setTranslateX(390); // negative = Left, positive = right
        lButton.setTranslateY(450); //Bottom
        lButton.setStyle("-fx-background-radius: 5em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #FDA000");

        lButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login login = new Login();

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
                else {
                    ID = login.main(userNameTextField.getText(), userPassword.getText());
                    if(ID < 0) {
                        errorWarning.setContentText("Incorrect Login Details, Please Try Again");
                        errorWarning.show();
                    }
                    else{
                        mainStage.setScene(accountPage());
                    }
                }
            }
        });

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setTextFill(Color.WHITE);
        backButton.setPrefSize(200, 40);
        backButton.setTranslateX(390);
        backButton.setTranslateY(500);
        backButton.setStyle("-fx-background-radius: 5em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" +  "-fx-background-color: #FDA000");

        backButton.setOnAction(event -> mainStage.setScene(mainPage()));


        // lButton.setOnAction(errorMessage  -> System.out.println("Welcome" + userNameTextField.getText()));

        loginRoot.getChildren().addAll(lButton,userNameTextField, userPassword,forgotPass,loginPageNameRoot,backButton);

        return new Scene(loginRoot, 1024, 600);
    }

    protected Scene registrationPage() {

        Pane registerRoot = new Pane();

        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("backgroundIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        registerRoot.setBackground(new Background(backgroundImage));



        HBox registerPageNameRoot = new HBox();
        BackgroundImage bgImage = new BackgroundImage(new Image(getClass().getResourceAsStream("headerIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        registerPageNameRoot.setBackground(new Background(bgImage));


        registerPageNameRoot.setPrefSize(1025,200);

        Label registerPageName = new Label();
        registerPageName.setText("Register");
        registerPageName.setTextFill(Color.WHITE);
        registerPageName.setFont(Font.font("PMingLiU-ExtB", FontWeight.EXTRA_BOLD,60));
        registerPageName.setTranslateX(50);
        registerPageName.setTranslateY(55);

        registerPageNameRoot.getChildren().addAll(registerPageName);


        //Username Text field
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameTextField.setPrefSize(540,40);
        usernameTextField.setTranslateX(250);
        usernameTextField.setTranslateY(240);


        //Email Text field
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailTextField.setPrefSize(540,40);
        emailTextField.setTranslateX(250);
        emailTextField.setTranslateY(300);

        //Password Text field
        TextField passwordTextField = new TextField();
        passwordTextField.setPromptText("Password");
        passwordTextField.setPrefSize(540,40);
        passwordTextField.setTranslateX(250);
        passwordTextField.setTranslateY(360);

        //Confirm Password Text Field
        TextField confirmPasswordTextField = new TextField();
        confirmPasswordTextField.setPromptText("Confirm Password");
        confirmPasswordTextField.setPrefSize(540,40);
        confirmPasswordTextField.setTranslateX(250);
        confirmPasswordTextField.setTranslateY(420);


        //Register Button
        Button rButton = new Button();
        rButton.setText("Register");
        rButton.setTextFill(Color.WHITE);
        rButton.setPrefSize(200, 40);
        rButton.setTranslateX(420);
        rButton.setTranslateY(490);
        rButton.setStyle("-fx-background-radius: 5em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" +  "-fx-background-color: #FDA000");


        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setTextFill(Color.WHITE);
        backButton.setPrefSize(200, 40);
        backButton.setTranslateX(420);
        backButton.setTranslateY(540);
        backButton.setStyle("-fx-background-radius: 5em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" +  "-fx-background-color: #FDA000");

        backButton.setOnAction(event -> mainStage.setScene(mainPage()));


        //Text field error handling


        rButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Registration register = new Registration();
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
                else if((confirmPasswordTextField.getText().compareTo(passwordTextField.getText()))!= 0){
                    errorWarning.setContentText("Passwords do not Match");
                    errorWarning.show();
                }
                //Reference from: https://www.tutorialspoint.com/validate-email-address-in-java
                else if(!(emailTextField.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))){
                    errorWarning.setContentText("Email Formatting Incorrect");
                    errorWarning.show();
                }
                else {
                    try {
                        if(register.main(usernameTextField.getText(), emailTextField.getText(), passwordTextField.getText()) == -1){
                            errorWarning.setContentText("Username Already in Use");
                            errorWarning.show();
                        }
                        else if(register.main(usernameTextField.getText(), emailTextField.getText(), passwordTextField.getText()) == -2){
                            errorWarning.setContentText("Email Already in Use");
                            errorWarning.show();
                        } else {
                            Login login = new Login();
                            ID = login.getUser(usernameTextField.getText(), passwordTextField.getText());
                            mainStage.setScene(accountPage());

                        };
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });


        registerRoot.getChildren().addAll(usernameTextField,rButton,emailTextField,passwordTextField,confirmPasswordTextField,registerPageNameRoot, backButton);

        return new Scene(registerRoot, 1024, 600);
    }

    protected Scene accountPage() {
        Pane accountRoot = new Pane();


        //Adding image on side panel
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("backgroundIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        accountRoot.setBackground(new Background(backgroundImage));


       // accountRoot.setStyle("-fx-background-color: #000000");

        Label accountPageName = new Label();
        accountPageName.setText("Welcome <Username> to your Pro-Active");
        accountPageName.setTextFill(Color.rgb(55,77,95));
        accountPageName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,35));
        accountPageName.setTranslateX(300);
        accountPageName.setTranslateY(40);

        Label editDetailsLabel = new Label("Edit Details");
        editDetailsLabel.setTranslateX(525);
        editDetailsLabel.setTranslateY(100);
        editDetailsLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");


        Label usernameLabel = new Label("Username: ");
        usernameLabel.setTranslateX(400);
        usernameLabel.setTranslateY(145);
        usernameLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField username = new TextField();
        username.setPrefSize(300,40);
        username.setTranslateX(600);
        username.setTranslateY(140);



        Label passResetLabel = new Label("Password Reset:");
        passResetLabel.setTranslateX(320);
        passResetLabel.setTranslateY(180);
        passResetLabel.setUnderline(true);
        passResetLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");


        //Old Password
        Label oldPassLabel = new Label("Old Password: ");
        oldPassLabel.setTranslateX(401);
        oldPassLabel.setTranslateY(208);
        oldPassLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField oldPass = new TextField();
        oldPass.setPrefSize(300,40);
        oldPass.setTranslateX(600);
        oldPass.setTranslateY(200);


        //New Password
        Label newPassLabel = new Label("New Password: ");
        newPassLabel.setTranslateX(403);
        newPassLabel.setTranslateY(260);
        newPassLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField newPass = new TextField();
        newPass.setPrefSize(300,40);
        newPass.setTranslateX(600);
        newPass.setTranslateY(250);


        //Confirm Password
        Label confirmPassLabel = new Label("Confirm Password: ");
        confirmPassLabel.setTranslateX(390);
        confirmPassLabel.setTranslateY(309);
        confirmPassLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField confirmPass = new TextField();
        confirmPass.setPrefSize(300,40);
        confirmPass.setTranslateX(600);
        confirmPass.setTranslateY(300);

        Button passSendButton = new Button("Send");
        passSendButton.setPrefSize(90, 10);
        passSendButton.setTranslateX(810); // negative = Left, positive = right
        passSendButton.setTranslateY(350); //Bottom
        passSendButton.setStyle("-fx-font: normal 16px 'Didact Gothic'");



        Label changeEmailLabel = new Label("Change Email:");
        changeEmailLabel.setTranslateX(320);
        changeEmailLabel.setTranslateY(390);
        changeEmailLabel.setUnderline(true);
        changeEmailLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        //Current Email
        Label currentEmailLabel = new Label("Current Email: ");
        currentEmailLabel.setTranslateX(400);
        currentEmailLabel.setTranslateY(415);
        currentEmailLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField currentEmail = new TextField();
        currentEmail.setPrefSize(300,40);
        currentEmail.setTranslateX(600);
        currentEmail.setTranslateY(410);

        //New Email
        Label newEmailLabel = new Label("New Email: ");
        newEmailLabel.setTranslateX(408);
        newEmailLabel.setTranslateY(465);
        newEmailLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField newEmail = new TextField();
        newEmail.setPrefSize(300,40);
        newEmail.setTranslateX(600);
        newEmail.setTranslateY(460);

        Button emailSendButton = new Button("Send");
        emailSendButton.setPrefSize(90, 10);
        emailSendButton.setTranslateX(810); // negative = Left, positive = right
        emailSendButton.setTranslateY(510); //Bottom
        emailSendButton.setStyle("-fx-font: normal 16px 'Didact Gothic'");



        VBox sideButtons = new VBox(91);

        //Adding image on side panel
         BackgroundImage bgImage = new BackgroundImage(new Image(getClass().getResourceAsStream("proactiveside3.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
        ,new BackgroundSize(1.0,1.0,true,true,false,false));
           sideButtons.setBackground(new Background(bgImage));


        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.WHITE);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,35));
        proActiveName2.setTranslateX(8);
        proActiveName2.setTranslateY(40);

        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setTextFill(Color.WHITE);
        accountButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        accountButton.setPrefSize(190, 40);
        accountButton.setTranslateX(5); // negative = Left, positive = right
        accountButton.setTranslateY(5); //Bottom

        //Adding Icon
        Image accountIcon = new Image(getClass().getResourceAsStream("user.png"));
        ImageView aImg = new ImageView(accountIcon);
        aImg.setFitHeight(30);
        aImg.setFitWidth(30);
        aImg.setTranslateX(-10);
        accountButton.setGraphic(aImg);
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));


        //Exercise Log button design
        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setTextFill(Color.WHITE);
        exerciseLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" +"-fx-background-color: transparent");
        exerciseLogButton.setPrefSize(190, 40);
        exerciseLogButton.setTranslateX(5); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(-20); //Bottom
        exerciseLogButton.setOnAction(event -> mainStage.setScene(exerciseLog()));

        Image exerciseIcon = new Image(getClass().getResourceAsStream("clipboard.png"));
        ImageView eImg = new ImageView(exerciseIcon);
        eImg.setFitHeight(30);
        eImg.setFitWidth(30);
        eImg.setTranslateX(-10);
        exerciseLogButton.setGraphic(eImg);


        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setTextFill(Color.WHITE);
        dietaryLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        dietaryLogButton.setPrefSize(190, 40);
        dietaryLogButton.setTranslateX(5); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(-30); //Bottom
        dietaryLogButton.setOnAction(event -> mainStage.setScene(dietaryLog()));


        //Adding Icon to Dietary log button
        Image dietaryIcon = new Image(getClass().getResourceAsStream("salad.png"));
        ImageView dImg = new ImageView(dietaryIcon);
        dImg.setFitHeight(30);
        dImg.setFitWidth(30);
        dImg.setTranslateX(-10);
        dietaryLogButton.setGraphic(dImg);

        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setTextFill(Color.WHITE);
        groupsButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        groupsButton.setPrefSize(190, 40);
        groupsButton.setTranslateX(5); // negative = Left, positive = right
        groupsButton.setTranslateY(-60); //Bottom


        //Adding Icon to the group button
        Image groupIcon = new Image(getClass().getResourceAsStream("groupIcon.png"));
        ImageView gImg = new ImageView(groupIcon);
        gImg.setFitHeight(30);
        gImg.setFitWidth(30);
        gImg.setTranslateX(-10);
        groupsButton.setGraphic(gImg);

        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);



        accountRoot.getChildren().addAll(sideButtons,accountPageName,username,oldPass,newPass,confirmPass,currentEmail,
                newEmail,emailSendButton,passSendButton,oldPassLabel,newPassLabel,confirmPassLabel,currentEmailLabel,
                newEmailLabel,usernameLabel,editDetailsLabel,passResetLabel,changeEmailLabel );

//        if(account.setPassword(ID, "b", "c", "c")){
//            System.out.println("pass changed");
//        }

        return new Scene(accountRoot, 1024, 600);
    }

    protected Scene exerciseLog (){

        Pane exerciseRoot = new Pane();
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("backgroundIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        exerciseRoot.setBackground(new Background(backgroundImage));


        VBox sideButtons = new VBox(91);

        //Adding image on side panel
        sideButtons.setStyle("-fx-border-radius: 50px");

        BackgroundImage bgImage = new BackgroundImage(new Image(getClass().getResourceAsStream("proactiveside3.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        sideButtons.setBackground(new Background(bgImage));


        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.WHITE);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,35));
        proActiveName2.setTranslateX(8);
        proActiveName2.setTranslateY(40);

        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setTextFill(Color.WHITE);
        accountButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        accountButton.setPrefSize(190, 40);
        accountButton.setTranslateX(5); // negative = Left, positive = right
        accountButton.setTranslateY(5); //Bottom

        //Adding Icon
        Image accountIcon = new Image(getClass().getResourceAsStream("user.png"));
        ImageView aImg = new ImageView(accountIcon);
        aImg.setFitHeight(30);
        aImg.setFitWidth(30);
        aImg.setTranslateX(-10);
        accountButton.setGraphic(aImg);
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));


        //Exercise Log button design
        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setTextFill(Color.WHITE);
        exerciseLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" +"-fx-background-color: transparent");
        exerciseLogButton.setPrefSize(190, 40);
        exerciseLogButton.setTranslateX(5); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(-20); //Bottom
        exerciseLogButton.setOnAction(event -> mainStage.setScene(exerciseLog()));

        Image exerciseIcon = new Image(getClass().getResourceAsStream("clipboard.png"));
        ImageView eImg = new ImageView(exerciseIcon);
        eImg.setFitHeight(30);
        eImg.setFitWidth(30);
        eImg.setTranslateX(-10);
        exerciseLogButton.setGraphic(eImg);


        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setTextFill(Color.WHITE);
        dietaryLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        dietaryLogButton.setPrefSize(190, 40);
        dietaryLogButton.setTranslateX(5); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(-30); //Bottom
        dietaryLogButton.setOnAction(event -> mainStage.setScene(dietaryLog()));


        //Adding Icon to Dietary log button
        Image dietaryIcon = new Image(getClass().getResourceAsStream("salad.png"));
        ImageView dImg = new ImageView(dietaryIcon);
        dImg.setFitHeight(30);
        dImg.setFitWidth(30);
        dImg.setTranslateX(-10);
        dietaryLogButton.setGraphic(dImg);

        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setTextFill(Color.WHITE);
        groupsButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        groupsButton.setPrefSize(190, 40);
        groupsButton.setTranslateX(5); // negative = Left, positive = right
        groupsButton.setTranslateY(-60); //Bottom


        //Adding Icon to the group button
        Image groupIcon = new Image(getClass().getResourceAsStream("groupIcon.png"));
        ImageView gImg = new ImageView(groupIcon);
        gImg.setFitHeight(30);
        gImg.setFitWidth(30);
        gImg.setTranslateX(-10);
        groupsButton.setGraphic(gImg);

        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);



        exerciseRoot.getChildren().addAll(sideButtons);
        return new Scene(exerciseRoot, 1024, 600);


    }


    protected Scene dietaryLog (){

        Pane dietaryLogRoot = new Pane();
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("backgroundIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        dietaryLogRoot.setBackground(new Background(backgroundImage));


        VBox sideButtons = new VBox(91);

        //Adding image on side panel
        sideButtons.setStyle("-fx-border-radius: 50px");

        BackgroundImage bgImage = new BackgroundImage(new Image(getClass().getResourceAsStream("proactiveside3.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        sideButtons.setBackground(new Background(bgImage));


        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.WHITE);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,35));
        proActiveName2.setTranslateX(8);
        proActiveName2.setTranslateY(40);

        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setTextFill(Color.WHITE);
        accountButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        accountButton.setPrefSize(190, 40);
        accountButton.setTranslateX(5); // negative = Left, positive = right
        accountButton.setTranslateY(5); //Bottom

        //Adding Icon
        Image accountIcon = new Image(getClass().getResourceAsStream("user.png"));
        ImageView aImg = new ImageView(accountIcon);
        aImg.setFitHeight(30);
        aImg.setFitWidth(30);
        aImg.setTranslateX(-10);
        accountButton.setGraphic(aImg);
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));


        //Exercise Log button design
        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setTextFill(Color.WHITE);
        exerciseLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" +"-fx-background-color: transparent");
        exerciseLogButton.setPrefSize(190, 40);
        exerciseLogButton.setTranslateX(5); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(-20); //Bottom
        exerciseLogButton.setOnAction(event -> mainStage.setScene(exerciseLog()));

        Image exerciseIcon = new Image(getClass().getResourceAsStream("clipboard.png"));
        ImageView eImg = new ImageView(exerciseIcon);
        eImg.setFitHeight(30);
        eImg.setFitWidth(30);
        eImg.setTranslateX(-10);
        exerciseLogButton.setGraphic(eImg);


        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setTextFill(Color.WHITE);
        dietaryLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        dietaryLogButton.setPrefSize(190, 40);
        dietaryLogButton.setTranslateX(5); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(-30); //Bottom
        dietaryLogButton.setOnAction(event -> mainStage.setScene(dietaryLog()));

        //Adding Icon to Dietary log button
        Image dietaryIcon = new Image(getClass().getResourceAsStream("salad.png"));
        ImageView dImg = new ImageView(dietaryIcon);
        dImg.setFitHeight(30);
        dImg.setFitWidth(30);
        dImg.setTranslateX(-10);
        dietaryLogButton.setGraphic(dImg);

        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setTextFill(Color.WHITE);
        groupsButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        groupsButton.setPrefSize(190, 40);
        groupsButton.setTranslateX(5); // negative = Left, positive = right
        groupsButton.setTranslateY(-60); //Bottom


        //Adding Icon to the group button
        Image groupIcon = new Image(getClass().getResourceAsStream("groupIcon.png"));
        ImageView gImg = new ImageView(groupIcon);
        gImg.setFitHeight(30);
        gImg.setFitWidth(30);
        gImg.setTranslateX(-10);
        groupsButton.setGraphic(gImg);

        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);



        dietaryLogRoot.getChildren().addAll(sideButtons);
        return new Scene(dietaryLogRoot, 1024, 600);


    }

    protected Scene groupPage(){
        Pane groupRoot = new Pane();

        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("backgroundIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        groupRoot.setBackground(new Background(backgroundImage));


        Label groupPageName = new Label("Your Groups");
        groupPageName.setTextFill(Color.rgb(55,77,95));
        groupPageName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        groupPageName.setTranslateX(500);
        groupPageName.setTranslateY(40);


        //Circle Group Button
        Button joinedGroup1Button = new Button();
        joinedGroup1Button.setText("Group 1");
        joinedGroup1Button.setTextFill(Color.WHITE);
        joinedGroup1Button.setPrefSize(100, 100);
        joinedGroup1Button.setTranslateX(340); // negative = Left, positive = right
        joinedGroup1Button.setTranslateY(120); //Bottom
        joinedGroup1Button.setStyle("-fx-background-radius: 100px; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #FDA000");


        //Circle Group2 Button
        Button joinedGroup2Button = new Button();
        joinedGroup2Button.setText("Group 2");
        joinedGroup2Button.setTextFill(Color.WHITE);
        joinedGroup2Button.setPrefSize(100, 100);
        joinedGroup2Button.setTranslateX(560); // negative = Left, positive = right
        joinedGroup2Button.setTranslateY(120); //Bottom
        joinedGroup2Button.setStyle("-fx-background-radius: 100px; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #D3208B");


        //Circle Group3 Button
        Button joinedGroup3Button = new Button();
        joinedGroup3Button.setText("Group 3");
        joinedGroup3Button.setTextFill(Color.WHITE);
        joinedGroup3Button.setPrefSize(100, 100);
        joinedGroup3Button.setTranslateX(790); // negative = Left, positive = right
        joinedGroup3Button.setTranslateY(120); //Bottom
        joinedGroup3Button.setStyle("-fx-background-radius: 100px; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #879AF2");







        //groupRoot.setStyle("-fx-background-color: #000000");

        VBox sideButtons = new VBox(91);

        //Adding image on side panel
        sideButtons.setStyle("-fx-border-radius: 50px");

        BackgroundImage bgImage = new BackgroundImage(new Image(getClass().getResourceAsStream("proactiveside3.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        sideButtons.setBackground(new Background(bgImage));


        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.WHITE);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,35));
        proActiveName2.setTranslateX(8);
        proActiveName2.setTranslateY(40);

        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setTextFill(Color.WHITE);
        accountButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        accountButton.setPrefSize(190, 40);
        accountButton.setTranslateX(5); // negative = Left, positive = right
        accountButton.setTranslateY(5); //Bottom

        //Adding Icon
        Image accountIcon = new Image(getClass().getResourceAsStream("user.png"));
        ImageView aImg = new ImageView(accountIcon);
        aImg.setFitHeight(30);
        aImg.setFitWidth(30);
        aImg.setTranslateX(-10);
        accountButton.setGraphic(aImg);
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));


        //Exercise Log button design
        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setTextFill(Color.WHITE);
        exerciseLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" +"-fx-background-color: transparent");
        exerciseLogButton.setPrefSize(190, 40);
        exerciseLogButton.setTranslateX(5); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(-20); //Bottom
        exerciseLogButton.setOnAction(event -> mainStage.setScene(exerciseLog()));

        Image exerciseIcon = new Image(getClass().getResourceAsStream("clipboard.png"));
        ImageView eImg = new ImageView(exerciseIcon);
        eImg.setFitHeight(30);
        eImg.setFitWidth(30);
        eImg.setTranslateX(-10);
        exerciseLogButton.setGraphic(eImg);


        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setTextFill(Color.WHITE);
        dietaryLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        dietaryLogButton.setPrefSize(190, 40);
        dietaryLogButton.setTranslateX(5); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(-30); //Bottom
        dietaryLogButton.setOnAction(event -> mainStage.setScene(dietaryLog()));


        //Adding Icon to Dietary log button
        Image dietaryIcon = new Image(getClass().getResourceAsStream("salad.png"));
        ImageView dImg = new ImageView(dietaryIcon);
        dImg.setFitHeight(30);
        dImg.setFitWidth(30);
        dImg.setTranslateX(-10);
        dietaryLogButton.setGraphic(dImg);

        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setTextFill(Color.WHITE);
        groupsButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        groupsButton.setPrefSize(190, 40);
        groupsButton.setTranslateX(5); // negative = Left, positive = right
        groupsButton.setTranslateY(-60); //Bottom


        //Adding Icon to the group button
        Image groupIcon = new Image(getClass().getResourceAsStream("groupIcon.png"));
        ImageView gImg = new ImageView(groupIcon);
        gImg.setFitHeight(30);
        gImg.setFitWidth(30);
        gImg.setTranslateX(-10);
        groupsButton.setGraphic(gImg);

        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);


        //Create Group Button
        Button createGroupButton = new Button();
        createGroupButton.setText("Create Group");
        createGroupButton.setTextFill(Color.WHITE);
        createGroupButton.setPrefSize(540, 40);
        createGroupButton.setTranslateX(345); // negative = Left, positive = right
        createGroupButton.setTranslateY(300); //Bottom
        createGroupButton.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #879AF2");

        createGroupButton.setOnAction(event -> mainStage.setScene(createGroup()));

        //Join Group Button
        Button joinGroupButton = new Button();
        joinGroupButton.setText("Join Groups");
        joinGroupButton.setTextFill(Color.WHITE);
        joinGroupButton.setPrefSize(540, 40);
        joinGroupButton.setTranslateX(345); // negative = Left, positive = right
        joinGroupButton.setTranslateY(420); //Bottom
        joinGroupButton.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #879AF2");

        joinGroupButton.setOnAction(event -> mainStage.setScene(joinGroupPage()));

        groupRoot.getChildren().addAll(sideButtons,createGroupButton,joinGroupButton,groupPageName,joinedGroup1Button,
                joinedGroup2Button,joinedGroup3Button);

        return new Scene(groupRoot,1024,600);
    }


    protected Scene createGroup(){
        Pane createGroupRoot = new Pane();
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("backgroundIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        createGroupRoot.setBackground(new Background(backgroundImage));


        Label createGroupName = new Label("Create Groups");
        createGroupName.setTextFill(Color.rgb(55,77,95));
        createGroupName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        createGroupName.setTranslateX(500);
        createGroupName.setTranslateY(45);

        //Text Fields
        TextField groupNameTextField = new TextField();
        groupNameTextField.setPromptText("Group name");
        groupNameTextField.setPrefSize(500,40);
        groupNameTextField.setTranslateX(360);
        groupNameTextField.setTranslateY(200);

        TextField confirmGroupTextField = new TextField();
        confirmGroupTextField.setPromptText("Confirm group name");
        confirmGroupTextField.setPrefSize(500,40);
        confirmGroupTextField.setTranslateX(360);
        confirmGroupTextField.setTranslateY(270);

        TextField inviteTextField = new TextField();
        inviteTextField.setPromptText("Friend username");
        inviteTextField.setPrefSize(350,40);
        inviteTextField.setTranslateX(360);
        inviteTextField.setTranslateY(340);


        Button createGroupButton = new Button("Create Group");
        createGroupButton.setPrefSize(200, 40);
        createGroupButton.setTranslateX(360); // negative = Left, positive = right
        createGroupButton.setTranslateY(400); //Bottom
        createGroupButton.setStyle("-fx-background-radius: 5em; " +
                "-fx-font: normal 16px 'Didact Gothic'");


        Button addFriendButton = new Button("Add Friend");
        addFriendButton.setPrefSize(120, 40);
        addFriendButton.setTranslateX(730); // negative = Left, positive = right
        addFriendButton.setTranslateY(340); //Bottom
        addFriendButton.setStyle("-fx-background-radius: 5em; " +
                "-fx-font:  normal 16px 'Didact Gothic'");




        VBox sideButtons = new VBox(91);

        //Adding image on side panel
        sideButtons.setStyle("-fx-border-radius: 50px");

        BackgroundImage bgImage = new BackgroundImage(new Image(getClass().getResourceAsStream("proactiveside3.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        sideButtons.setBackground(new Background(bgImage));


        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.WHITE);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,35));
        proActiveName2.setTranslateX(8);
        proActiveName2.setTranslateY(40);

        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setTextFill(Color.WHITE);
        accountButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        accountButton.setPrefSize(190, 40);
        accountButton.setTranslateX(5); // negative = Left, positive = right
        accountButton.setTranslateY(5); //Bottom

        //Adding Icon
        Image accountIcon = new Image(getClass().getResourceAsStream("user.png"));
        ImageView aImg = new ImageView(accountIcon);
        aImg.setFitHeight(30);
        aImg.setFitWidth(30);
        aImg.setTranslateX(-10);
        accountButton.setGraphic(aImg);
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));


        //Exercise Log button design
        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setTextFill(Color.WHITE);
        exerciseLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" +"-fx-background-color: transparent");
        exerciseLogButton.setPrefSize(190, 40);
        exerciseLogButton.setTranslateX(5); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(-20); //Bottom
        exerciseLogButton.setOnAction(event -> mainStage.setScene(exerciseLog()));

        Image exerciseIcon = new Image(getClass().getResourceAsStream("clipboard.png"));
        ImageView eImg = new ImageView(exerciseIcon);
        eImg.setFitHeight(30);
        eImg.setFitWidth(30);
        eImg.setTranslateX(-10);
        exerciseLogButton.setGraphic(eImg);


        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setTextFill(Color.WHITE);
        dietaryLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        dietaryLogButton.setPrefSize(190, 40);
        dietaryLogButton.setTranslateX(5); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(-30); //Bottom
        dietaryLogButton.setOnAction(event -> mainStage.setScene(dietaryLog()));


        //Adding Icon to Dietary log button
        Image dietaryIcon = new Image(getClass().getResourceAsStream("salad.png"));
        ImageView dImg = new ImageView(dietaryIcon);
        dImg.setFitHeight(30);
        dImg.setFitWidth(30);
        dImg.setTranslateX(-10);
        dietaryLogButton.setGraphic(dImg);

        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setTextFill(Color.WHITE);
        groupsButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        groupsButton.setPrefSize(190, 40);
        groupsButton.setTranslateX(5); // negative = Left, positive = right
        groupsButton.setTranslateY(-60); //Bottom


        //Adding Icon to the group button
        Image groupIcon = new Image(getClass().getResourceAsStream("groupIcon.png"));
        ImageView gImg = new ImageView(groupIcon);
        gImg.setFitHeight(30);
        gImg.setFitWidth(30);
        gImg.setTranslateX(-10);
        groupsButton.setGraphic(gImg);

        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);


        createGroupRoot.getChildren().addAll(sideButtons,confirmGroupTextField,groupNameTextField
        ,inviteTextField,createGroupName,createGroupButton, addFriendButton);
        return new Scene(createGroupRoot,1024,600);

    }

    protected Scene joinGroupPage(){
        Pane joinGroupRoot = new Pane();

        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("backgroundIMG.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        joinGroupRoot.setBackground(new Background(backgroundImage));



        Label joinGroupsPageName = new Label("Join Groups");
        joinGroupsPageName.setTextFill(Color.rgb(55,77,95));
        joinGroupsPageName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        joinGroupsPageName.setTranslateX(500);
        joinGroupsPageName.setTranslateY(45);

        Text para = new Text();
        para.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore \n" +
                " et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip");

       para.setTranslateX(350);
       para.setTranslateY(145);



        //Creating graphic img

        //Top Join Group Buttons
        Button group1 = new Button();

        //Location of button
        group1.setTranslateX(280);
        group1.setTranslateY(200);
        group1.setPrefSize(210,150);
        //size of button

        Rectangle rec = new Rectangle(0,0,210,150);
        rec.setArcHeight(50.0); //Styling corner radius for image
        rec.setArcWidth(50.0);

        ImagePattern p = new ImagePattern(new Image(getClass().getResourceAsStream("MealPrep3.png")));

        rec.setFill(p);
        rec.setEffect(new DropShadow(20,Color.BLACK));
        //rec.setEffect(new DropShadow(20,Color.rgb(251,85,184)));
        group1.setGraphic(rec);

        group1.setStyle("-fx-background-color: transparent;");

        group1.setOnAction(event -> mainStage.setScene(joinGroup1()));


        // Styling join group2 button
        Button group2 = new Button();

        //Location of button
        group2.setTranslateX(530);
        group2.setTranslateY(200);

        Rectangle rec2 = new Rectangle(0,0,210,150);
        rec2.setArcHeight(50.0); //Styling corner radius for image
        rec2.setArcWidth(50.0);

        ImagePattern pattern2 = new ImagePattern(new Image(getClass().getResourceAsStream("fitChallenge3.png")));

        rec2.setFill(pattern2);
        rec2.setEffect(new DropShadow(20,Color.BLACK));

       // rec2.setEffect(new DropShadow(20,Color.rgb(253,160,0)));
        group2.setGraphic(rec2);

        //group2.setPrefSize(210,150);
        group2.setStyle("-fx-background-color: transparent;");
        group2.setOnAction(event -> mainStage.setScene(joinGroup2()));


        //JOIN GROUP BUTTON 3

        Button group3 = new Button();
        //Location of button
        group3.setTranslateX(780);
        group3.setTranslateY(200);

        Rectangle rec3 = new Rectangle(0,0,210,150);
        rec3.setArcHeight(50.0); //Styling corner radius for image
        rec3.setArcWidth(50.0);

        ImagePattern pattern3 = new ImagePattern(new Image(getClass().getResourceAsStream("funandFit3.png")));

        rec3.setFill(pattern3);
        rec3.setEffect(new DropShadow(20,Color.BLACK));
        group3.setGraphic(rec3);

        group3.setStyle("-fx-background-color: transparent;");
        group3.setOnAction(event -> mainStage.setScene(joinGroup3()));


        //Join group Button 4
        Button group4= new Button();
        group4.setTranslateX(280);
        group4.setTranslateY(380);

        Rectangle rec4 = new Rectangle(0,0,210,150);
        rec4.setArcHeight(50.0); //Styling corner radius for image
        rec4.setArcWidth(50.0);

        ImagePattern pattern4 = new ImagePattern(new Image(getClass().getResourceAsStream("motivateFit3.png")));

        rec4.setFill(pattern4);
        rec4.setEffect(new DropShadow(20,Color.BLACK));
        //rec4.setEffect(new DropShadow(20,Color.rgb(211,32,139)));
        group4.setGraphic(rec4);
        group4.setStyle("-fx-background-color: transparent;");
        group4.setOnAction(event -> mainStage.setScene(joinGroup4()));


        //Join Group 5 BUTTON STYLING
        Button group5 = new Button();
        //Location of button
        group5.setTranslateX(530);
        group5.setTranslateY(380);

        Rectangle rec5 = new Rectangle(0,0,210,150);
        rec5.setArcHeight(50.0); //Styling corner radius for image
        rec5.setArcWidth(50.0);

        ImagePattern pattern5 = new ImagePattern(new Image(getClass().getResourceAsStream("weightL3.png")));

        rec5.setFill(pattern5);
        rec5.setEffect(new DropShadow(20,Color.BLACK));
       // rec5.setEffect(new DropShadow(20,Color.rgb(121,143,242)));

        group5.setGraphic(rec5);
        group5.setStyle("-fx-background-color: transparent;");
        group5.setOnAction(event -> mainStage.setScene(joinGroup5()));


        //JOIN GROUP BUTTON 6 STYLING
        Button group6 = new Button();
        //Location of button
        group6.setTranslateX(780);
        group6.setTranslateY(380);

        Rectangle rec6 = new Rectangle(0,0,210,150);
        rec6.setArcHeight(50.0); //Styling corner radius for image
        rec6.setArcWidth(50.0);

        ImagePattern pattern6 = new ImagePattern(new Image(getClass().getResourceAsStream("fitnessFirst3.png")));

        rec6.setFill(pattern6);
        rec6.setEffect(new DropShadow(20,Color.BLACK));
        //rec6.setEffect(new DropShadow(20,Color.rgb(251,85,184)));
        group6.setGraphic(rec6);

        group6.setStyle("-fx-background-color: transparent;");
        group6.setOnAction(event -> mainStage.setScene(joinGroup6()));



        VBox sideButtons = new VBox(91);

        //Adding image on side panel
        sideButtons.setStyle("-fx-border-radius: 50px");

        BackgroundImage bgImage = new BackgroundImage(new Image(getClass().getResourceAsStream("proactiveside3.png")),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER
                ,new BackgroundSize(1.0,1.0,true,true,false,false));
        sideButtons.setBackground(new Background(bgImage));


        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.WHITE);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,35));
        proActiveName2.setTranslateX(8);
        proActiveName2.setTranslateY(40);

        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setTextFill(Color.WHITE);
        accountButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        accountButton.setPrefSize(190, 40);
        accountButton.setTranslateX(5); // negative = Left, positive = right
        accountButton.setTranslateY(5); //Bottom

        //Adding Icon
        Image accountIcon = new Image(getClass().getResourceAsStream("user.png"));
        ImageView aImg = new ImageView(accountIcon);
        aImg.setFitHeight(30);
        aImg.setFitWidth(30);
        aImg.setTranslateX(-10);
        accountButton.setGraphic(aImg);
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));


        //Exercise Log button design
        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setTextFill(Color.WHITE);
        exerciseLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" +"-fx-background-color: transparent");
        exerciseLogButton.setPrefSize(190, 40);
        exerciseLogButton.setTranslateX(5); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(-20); //Bottom
        exerciseLogButton.setOnAction(event -> mainStage.setScene(exerciseLog()));

        Image exerciseIcon = new Image(getClass().getResourceAsStream("clipboard.png"));
        ImageView eImg = new ImageView(exerciseIcon);
        eImg.setFitHeight(30);
        eImg.setFitWidth(30);
        eImg.setTranslateX(-10);
        exerciseLogButton.setGraphic(eImg);


        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setTextFill(Color.WHITE);
        dietaryLogButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        dietaryLogButton.setPrefSize(190, 40);
        dietaryLogButton.setTranslateX(5); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(-30); //Bottom
        dietaryLogButton.setOnAction(event -> mainStage.setScene(dietaryLog()));


        //Adding Icon to Dietary log button
        Image dietaryIcon = new Image(getClass().getResourceAsStream("salad.png"));
        ImageView dImg = new ImageView(dietaryIcon);
        dImg.setFitHeight(30);
        dImg.setFitWidth(30);
        dImg.setTranslateX(-10);
        dietaryLogButton.setGraphic(dImg);

        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setTextFill(Color.WHITE);
        groupsButton.setStyle("-fx-font: normal 25px 'Arial Nova Cond Light';" + "-fx-background-color: transparent");
        groupsButton.setPrefSize(190, 40);
        groupsButton.setTranslateX(5); // negative = Left, positive = right
        groupsButton.setTranslateY(-60); //Bottom


        //Adding Icon to the group button
        Image groupIcon = new Image(getClass().getResourceAsStream("groupIcon.png"));
        ImageView gImg = new ImageView(groupIcon);
        gImg.setFitHeight(30);
        gImg.setFitWidth(30);
        gImg.setTranslateX(-10);
        groupsButton.setGraphic(gImg);

        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);



        joinGroupRoot.getChildren().addAll(sideButtons,group1,group2,group3,group4,group5,group6,joinGroupsPageName,para);


        return new Scene(joinGroupRoot,1024,600);
    }

    protected Scene joinGroup1(){

        Pane joinGroup1Root = new Pane();


        return new Scene(joinGroup1Root,1024,600);

    }

    protected Scene joinGroup2(){

        Pane joinGroup2Root = new Pane();


        return new Scene(joinGroup2Root,1024,600);

    }
    protected Scene joinGroup3(){

        Pane joinGroup3Root = new Pane();


        return new Scene(joinGroup3Root,1024,600);

    }
    protected Scene joinGroup4(){

        Pane joinGroup4Root = new Pane();


        return new Scene(joinGroup4Root,1024,600);

    }
    protected Scene joinGroup5(){

        Pane joinGroup5Root = new Pane();


        return new Scene(joinGroup5Root,1024,600);

    }
    protected Scene joinGroup6(){

        Pane joinGroup6Root = new Pane();


        return new Scene(joinGroup6Root,1024,600);

    }



    public static void main(String[] args) {
        launch(args);
    }
}
