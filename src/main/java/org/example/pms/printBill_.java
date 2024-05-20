package org.example.pms;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class printBill_ extends removeVehiclePA {
    public class PrintBillController {
        private String arg1;
        private String arg2;
        private int arg3;
        private int arg4;
        private int arg5;
        private  int arg6;
        private String arg7;

        // Constructor to accept 5 arguments
        public PrintBillController(String arg1, String arg2, int arg3, int arg4, int arg5,int arg6 , String arg7) {
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.arg3 = arg3;
            this.arg4 = arg4;
            this.arg5 = arg5;
            this.arg6 = arg6;
            this.arg7 = arg7;
        }

        // Other methods and code in your controller class...
    }


    public void start(Stage mainStage, String arg1, String arg2, int arg3, int arg4, int arg5, int arg6,String arg7) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Auto_Park.class.getResource("printBill.fxml"));
        PrintBillController controller = new PrintBillController(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);

        mainStage.setTitle("Bill");
        mainStage.setResizable(false);

        mainStage.setScene(scene);
        mainStage.show();
    }


}
