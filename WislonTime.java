import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.control.Label;
import java.text.SimpleDateFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WislonTime extends Application {
	public static boolean Hour24 = true;

	protected BorderPane getPane() {
		BorderPane pane = new BorderPane(); // pane for containing buttons and clock
		

		DigitalClock clock = new DigitalClock(); // clock to be added to pane
		BorderPane.setAlignment(clock, Pos.CENTER);
		pane.setCenter(clock);


		return pane;
	}

	public void start(Stage primaryStage) {
// Create a scene and place it in the stage

		primaryStage.setTitle("WislonianClockApplication"); // Set the stage title
		primaryStage.setScene(new Scene(getPane(), 250, 150)); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		primaryStage.getScene().getRoot().requestFocus();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class DigitalClock extends Label {
	public SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	public Timeline animation;
	public Calendar time;
	String amPm = " ";
	
	public DigitalClock() {
		
		// set text with lambda
		EventHandler<ActionEvent> eventHandler = e -> {
			Date dt = new Date();
			
			double h = dt.getHours();
			double m = dt.getMinutes();
			double s = dt.getSeconds();
			double wt = (h / 2.4) + (((m/1.44)/100) + ((s / 86.4)/100));

			
			int d = (int) wt;
			int c = (int) (wt*10-d*10);
			int mi = (int) (wt*100 - d*100-c*10);
			int u = (int) (wt*1000 - d*1000-c*100-mi*10);
			int t = (int) (wt*10000-d*10000-c*1000-mi*100-u*10);
			
			
		
			System.out.println(d+"."+c+mi+"."+u+t);
			String wtime = (d + "." + c + mi + "." + u + t);
			setText(wtime);
		};

		// change text font here
		this.setFont(Font.font("Arial", 30));
		// set animation here
		animation = new Timeline(new KeyFrame(Duration.millis(860), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}
}
