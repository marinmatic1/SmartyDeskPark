import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Connection;
import model.Users;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        Users user = new Users (0,"user1","user1","1234","456-h-756","ADMIN",1);
        try {
            Users.add(user);
            System.out.println("Sucess, out = 200");
        }
        catch(Exception e){
            System.out.println("Nmz na bazu se spojit jebiga");
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
