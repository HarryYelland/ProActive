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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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

        Label proActiveName = new Label();
        proActiveName.setText("Pro-Active");
        //proActiveName.setTextFill(Color.WHITE); //UNCOMMENT THIS WHEN YOU HAVE BACKGROUND IMAGE
        proActiveName.setTextFill(Color.BLACK);
        proActiveName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,50));
        proActiveName.setTranslateX(380);
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

        loginButton.setOnAction(event -> mainStage.setScene(loginPage())); //connects to next scene

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


        /*Background Image
         String image = Main.class.getResource("fitness.png").toExternalForm();
         mainPageRoot.setStyle("-fx-background-image: url('" + image + "');");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());*/


        mainPageRoot.getChildren().addAll(loginButton,registerButton,proActiveName);
        return new Scene(mainPageRoot,1024,600);

    }

    protected Scene loginPage() {
        HBox loginText = new HBox();

        Pane loginRoot = new Pane();


        Label login = new Label();
        login.setText("Login");
        // login.setTextFill(Color.WHITE); Uncomment when image has been added
        login.setTextFill(Color.BLACK);
        login.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        loginText.setTranslateX(30);
        loginText.setTranslateY(30);
        loginText.getChildren().addAll(login);


        Label forgotPass = new Label("Forgot Password?");
        forgotPass.setFont(Font.font("Arial Nova Cond Light", FontPosture.ITALIC,13));
        forgotPass.setTranslateX(271);
        forgotPass.setTranslateY(320);


        //Username Field
        TextField userNameTextField = new TextField();
        userNameTextField.setPromptText("Username");
        userNameTextField.setPrefSize(450,40);
        userNameTextField.setTranslateX(270);
        userNameTextField.setTranslateY(200);

        //Password Field
        PasswordField userPassword = new PasswordField();
        userPassword.setPromptText("Password");
        userPassword.setPrefSize(450,40);
        userPassword.setTranslateX(270);
        userPassword.setTranslateY(275);


        //Login button for the login page
        Button lButton = new Button();
        lButton.setText("Login");
        lButton.setPrefSize(200, 40);
        lButton.setTranslateX(390); // negative = Left, positive = right
        lButton.setTranslateY(350); //Bottom
        lButton.setStyle("-fx-background-radius: 5em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light'");

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
                        try {
                            mainStage.setScene(accountPage());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            }
        });


        loginRoot.getChildren().addAll(loginText, lButton,userNameTextField, userPassword,forgotPass);

        return new Scene(loginRoot, 1024, 600);
    }

    protected Scene registrationPage() {

        Pane registerRoot = new Pane();

        Label registerPageName = new Label();
        registerPageName.setText("Register");
        registerPageName.setTextFill(Color.BLACK);
        registerPageName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        registerPageName.setTranslateX(50);
        registerPageName.setTranslateY(30);

        //registerRoot.setStyle("-fx-background-color: #F7EDDE");

        //Username Text field
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameTextField.setPrefSize(540,40);
        usernameTextField.setTranslateX(250);
        usernameTextField.setTranslateY(150);


        //Email Text field
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailTextField.setPrefSize(540,40);
        emailTextField.setTranslateX(250);
        emailTextField.setTranslateY(210);

        //Password Text field
        TextField passwordTextField = new TextField();
        passwordTextField.setPromptText("Password");
        passwordTextField.setPrefSize(540,40);
        passwordTextField.setTranslateX(250);
        passwordTextField.setTranslateY(270);

        //Confirm Password Text Field
        TextField confirmPasswordTextField = new TextField();
        confirmPasswordTextField.setPromptText("Confirm Password");
        confirmPasswordTextField.setPrefSize(540,40);
        confirmPasswordTextField.setTranslateX(250);
        confirmPasswordTextField.setTranslateY(330);


        //Register Button
        Button rButton = new Button();
        rButton.setText("Register");
        rButton.setPrefSize(200, 40);
        rButton.setTranslateX(420);
        rButton.setTranslateY(400);
        rButton.setStyle("-fx-background-radius: 5em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light'");


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
                            mainStage.setScene(loginPage());
                        };
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                   }
                }
            }
        });


        registerRoot.getChildren().addAll(usernameTextField,registerPageName,rButton,emailTextField,passwordTextField,confirmPasswordTextField);

        return new Scene(registerRoot, 1024, 600);
    }

    protected Scene accountPage() {
        Pane accountRoot = new Pane();

        // accountRoot.setStyle("-fx-background-color: #F7EDDE");
        Label accountPageName = new Label();
        accountPageName.setText("Welcome <USERNAME> to Your Pro-Active");
        accountPageName.setTextFill(Color.BLACK);
        accountPageName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,35));
        accountPageName.setTranslateX(300);
        accountPageName.setTranslateY(46);


        Label usernameLabel = new Label("Username: ");
        usernameLabel.setTranslateX(525);
        usernameLabel.setTranslateY(145);
        usernameLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");


        TextField username = new TextField();
        username.setPrefSize(230,40);
        username.setTranslateX(670);
        username.setTranslateY(140);



        //Old Password
        Label oldPassLabel = new Label("Old Password: ");
        oldPassLabel.setTranslateX(509);
        oldPassLabel.setTranslateY(208);
        oldPassLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField oldPass = new TextField();
        oldPass.setPrefSize(230,40);
        oldPass.setTranslateX(670);
        oldPass.setTranslateY(200);


        //New Password
        Label newPassLabel = new Label("New Password: ");
        newPassLabel.setTranslateX(503);
        newPassLabel.setTranslateY(260);
        newPassLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField newPass = new TextField();
        newPass.setPrefSize(230,40);
        newPass.setTranslateX(670);
        newPass.setTranslateY(250);


        //Confirm Password
        Label confirmPassLabel = new Label("Confirm Password: ");
        confirmPassLabel.setTranslateX(490);
        confirmPassLabel.setTranslateY(309);
        confirmPassLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField confirmPass = new TextField();
        confirmPass.setPrefSize(230,40);
        confirmPass.setTranslateX(670);
        confirmPass.setTranslateY(300);

        Button passSendButton = new Button("Send");
        passSendButton.setPrefSize(90, 10);
        passSendButton.setTranslateX(810); // negative = Left, positive = right
        passSendButton.setTranslateY(350); //Bottom
        passSendButton.setStyle("-fx-font: normal 16px 'Didact Gothic'");



        //Current Email
        Label currentEmailLabel = new Label("Current Email: ");
        currentEmailLabel.setTranslateX(500);
        currentEmailLabel.setTranslateY(415);
        currentEmailLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField currentEmail = new TextField();
        currentEmail.setPrefSize(230,40);
        currentEmail.setTranslateX(670);
        currentEmail.setTranslateY(410);

        //New Email
        Label newEmailLabel = new Label("New Email: ");
        newEmailLabel.setTranslateX(510);
        newEmailLabel.setTranslateY(465);
        newEmailLabel.setStyle("-fx-font: normal 17px 'Didact Gothic'");

        TextField newEmail = new TextField();
        newEmail.setPrefSize(230,40);
        newEmail.setTranslateX(670);
        newEmail.setTranslateY(460);

        Button emailSendButton = new Button("Send");
        emailSendButton.setPrefSize(90, 10);
        emailSendButton.setTranslateX(810); // negative = Left, positive = right
        emailSendButton.setTranslateY(510); //Bottom
        emailSendButton.setStyle("-fx-font: normal 16px 'Didact Gothic'");




        VBox sideButtons = new VBox();
        //sideButtons.setStyle("-fx-background-color: #000000");
        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.BLACK);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        proActiveName2.setTranslateX(10);
        proActiveName2.setTranslateY(40);


        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        accountButton.setPrefSize(200, 40);
        accountButton.setTranslateX(10); // negative = Left, positive = right
        accountButton.setTranslateY(120); //Bottom
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));


        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        exerciseLogButton.setPrefSize(200, 40);
        exerciseLogButton.setTranslateX(10); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(185); //Bottom


        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        dietaryLogButton.setPrefSize(200, 40);
        dietaryLogButton.setTranslateX(10); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(250); //Bottom


        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        groupsButton.setPrefSize(200, 40);
        groupsButton.setTranslateX(10); // negative = Left, positive = right
        groupsButton.setTranslateY(320); //Bottom
        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);



        accountRoot.getChildren().addAll(sideButtons,accountPageName,username,oldPass,newPass,confirmPass,currentEmail,
                newEmail,emailSendButton,passSendButton,oldPassLabel,newPassLabel,confirmPassLabel,currentEmailLabel,
                newEmailLabel,usernameLabel);

        if(account.setPassword(ID, "b", "c", "c")){
            System.out.println("pass changed");
        }

        return new Scene(accountRoot, 1024, 600);
    }


    protected Scene groupPage(){
        Pane groupRoot = new Pane();

        Label groupPageName = new Label("Your Groups");
        groupPageName.setTextFill(Color.BLACK);
        groupPageName.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        groupPageName.setTranslateX(500);
        groupPageName.setTranslateY(45);

        //groupRoot.setStyle("-fx-background-color: #000000");

        VBox sideButtons = new VBox();
        //sideButtons.setStyle("-fx-background-color: #000000");

        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.BLACK);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        proActiveName2.setTranslateX(10);
        proActiveName2.setTranslateY(40);

        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        accountButton.setPrefSize(200, 40);
        accountButton.setTranslateX(10); // negative = Left, positive = right
        accountButton.setTranslateY(120); //Bottom
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));

        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        exerciseLogButton.setPrefSize(200, 40);
        exerciseLogButton.setTranslateX(10); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(185); //Bottom


        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        dietaryLogButton.setPrefSize(200, 40);
        dietaryLogButton.setTranslateX(10); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(250); //Bottom


        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        groupsButton.setPrefSize(200, 40);
        groupsButton.setTranslateX(10); // negative = Left, positive = right
        groupsButton.setTranslateY(320); //Bottom
        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);

        //Create Group Button
        Button createGroupButton = new Button();
        createGroupButton.setText("Create Group");
        createGroupButton.setPrefSize(540, 40);
        createGroupButton.setTranslateX(345); // negative = Left, positive = right
        createGroupButton.setTranslateY(300); //Bottom
        createGroupButton.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light'");

        createGroupButton.setOnAction(event -> mainStage.setScene(createGroup()));

        //Join Group Button
        Button joinGroupButton = new Button();
        joinGroupButton.setText("Join Groups");
        joinGroupButton.setPrefSize(540, 40);
        joinGroupButton.setTranslateX(345); // negative = Left, positive = right
        joinGroupButton.setTranslateY(420); //Bottom
        joinGroupButton.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light'");

        joinGroupButton.setOnAction(event -> mainStage.setScene(joinGroupPage()));

        groupRoot.getChildren().addAll(sideButtons,createGroupButton,joinGroupButton,groupPageName);

        return new Scene(groupRoot,1024,600);
    }


    protected Scene createGroup(){
        Pane createGroupRoot = new Pane();
        Label createGroupName = new Label("Create Groups");
        createGroupName.setTextFill(Color.BLACK);
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
                "-fx-font: normal 20px 'Arial Nova Cond Light'");

        Button addFriendButton = new Button("Add Friend");
        addFriendButton.setPrefSize(120, 40);
        addFriendButton.setTranslateX(730); // negative = Left, positive = right
        addFriendButton.setTranslateY(340); //Bottom
        addFriendButton.setStyle("-fx-background-radius: 5em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light'");




        VBox sideButtons = new VBox();
        //sideButtons.setStyle("-fx-background-color: #000000");

        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.BLACK);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        proActiveName2.setTranslateX(10);
        proActiveName2.setTranslateY(40);

        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        accountButton.setPrefSize(200, 40);
        accountButton.setTranslateX(10); // negative = Left, positive = right
        accountButton.setTranslateY(120); //Bottom
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));

        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        exerciseLogButton.setPrefSize(200, 40);
        exerciseLogButton.setTranslateX(10); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(185); //Bottom


        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        dietaryLogButton.setPrefSize(200, 40);
        dietaryLogButton.setTranslateX(10); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(250); //Bottom
        // dietaryLogButton(event -> mainStage.setScene());


        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        groupsButton.setPrefSize(200, 40);
        groupsButton.setTranslateX(10); // negative = Left, positive = right
        groupsButton.setTranslateY(320); //Bottom
        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);

        createGroupRoot.getChildren().addAll(sideButtons,confirmGroupTextField,groupNameTextField
                ,inviteTextField,createGroupName,createGroupButton, addFriendButton);
        return new Scene(createGroupRoot,1024,600);

    }

    protected Scene joinGroupPage(){
        Pane joinGroupRoot = new Pane();


        Label joinGroupsPageName = new Label("Join Groups");
        joinGroupsPageName .setTextFill(Color.BLACK);
        joinGroupsPageName .setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        joinGroupsPageName .setTranslateX(500);
        joinGroupsPageName .setTranslateY(45);


        //Creating graphic img

        // Image img = new Image(getClass().getResource("mealPrep.png").toURI().toString());

        // ImageView mealPrepImg = new ImageView(img);
//        ImageView mealPrepImg = new ImageView(getClass().getResource("mealPrep.png").toExternalForm());
//
//        mealPrepImg.setFitHeight(80);
//        mealPrepImg.setPreserveRatio(true);


        //Top Join Group Buttons
        Button group1 = new Button("Group 1");
        //Location of button
        group1.setTranslateX(280);
        group1.setTranslateY(200);
        //size of button
        group1.setPrefSize(210,150);
        //mealPrep.setGraphic(mealPrepImg);
        group1.setStyle("-fx-background-radius: 1em;" +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #efd6ac");

        Button group2 = new Button("Group 2");
        //Location of button
        group2.setTranslateX(530);
        group2.setTranslateY(200);
        group2.setPrefSize(210,150);
        group2.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #efd6ac");


        Button group3 = new Button("Group 3");
        //Location of button
        group3.setTranslateX(780);
        group3.setTranslateY(200);
        group3.setPrefSize(210,150);
        group3.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #efd6ac");




        Button group4= new Button("Group 4");
        //size of button
        group4.setPrefSize(210,150);
        //mealPrep.setGraphic(mealPrepImg);
        group4.setTranslateX(280);
        group4.setTranslateY(380);
        group4.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #efd6ac");


        Button group5 = new Button("Group 5");
        //Location of button
        group5.setPrefSize(210,150);
        group5.setTranslateX(530);
        group5.setTranslateY(380);
        group5.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #efd6ac");

        Button group6 = new Button("Group 6");
        //Location of button
        group6.setTranslateX(780);
        group6.setTranslateY(380);
        group6.setPrefSize(210,150);
        group6.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light'");
        group6.setStyle("-fx-background-radius: 1em; " +
                "-fx-font: normal 20px 'Arial Nova Cond Light';" + "-fx-background-color: #efd6ac");



        VBox sideButtons = new VBox();
        //sideButtons.setStyle("-fx-background-color: #000000");

        Label proActiveName2 = new Label("Pro-Active");
        proActiveName2.setTextFill(Color.BLACK);
        proActiveName2.setFont(Font.font("PMingLiU-ExtB", FontWeight.LIGHT,40));
        proActiveName2.setTranslateX(10);
        proActiveName2.setTranslateY(40);

        Button accountButton = new Button();
        accountButton.setText("Account");
        accountButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        accountButton.setPrefSize(200, 40);
        accountButton.setTranslateX(10); // negative = Left, positive = right
        accountButton.setTranslateY(120); //Bottom
        accountButton.setOnAction(event -> mainStage.setScene(accountPage()));

        Button exerciseLogButton = new Button();
        exerciseLogButton.setText("Exercise Log");
        exerciseLogButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        exerciseLogButton.setPrefSize(200, 40);
        exerciseLogButton.setTranslateX(10); // negative = Left, positive = right
        exerciseLogButton.setTranslateY(185); //Bottom



        Button dietaryLogButton = new Button();
        dietaryLogButton.setText("Dietary Log");
        dietaryLogButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        dietaryLogButton.setPrefSize(200, 40);
        dietaryLogButton.setTranslateX(10); // negative = Left, positive = right
        dietaryLogButton.setTranslateY(250); //Bottom
        // dietaryLogButton(event -> mainStage.setScene());


        Button groupsButton = new Button();
        groupsButton.setText("Groups");
        groupsButton.setStyle("-fx-font: normal 20px 'Arial Nova Cond Light'");
        groupsButton.setPrefSize(200, 40);
        groupsButton.setTranslateX(10); // negative = Left, positive = right
        groupsButton.setTranslateY(320); //Bottom
        groupsButton.setOnAction(event -> mainStage.setScene(groupPage()));


        sideButtons.getChildren().addAll(proActiveName2,accountButton,exerciseLogButton,dietaryLogButton,groupsButton);


        joinGroupRoot.getChildren().addAll(sideButtons,group1,group2,group3,group4,group5,group6,joinGroupsPageName);


        return new Scene(joinGroupRoot,1024,600);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
