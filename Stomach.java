import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Stomach3DModel extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, true);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-1000);

        scene.setCamera(camera);

        // Create a cylinder to represent the upper part of the stomach
        Cylinder upperStomach = new Cylinder(200, 400);
        upperStomach.setTranslateY(-200);
        upperStomach.setMaterial(new PhongMaterial(Color.RED));

        // Create a sphere to represent the lower part of the stomach
        Sphere lowerStomach = new Sphere(200);
        lowerStomach.setTranslateY(200);
        lowerStomach.setMaterial(new PhongMaterial(Color.RED));

        // Add the stomach parts to the root
        root.getChildren().addAll(upperStomach, lowerStomach);

        // Set up rotation for visualization
        Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
        root.getTransforms().addAll(rotateX, rotateY);

        // Add mouse event handlers for interactive rotation
        scene.setOnMousePressed(event -> {
            scene.setCursor(Cursor.CLOSED_HAND);
            rotateX.setAngle(0);
            rotateY.setAngle(0);
        });

        scene.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - scene.getWidth() / 2;
            double deltaY = event.getSceneY() - scene.getHeight() / 2;

            rotateX.setAngle(rotateX.getAngle() - deltaY);
            rotateY.setAngle(rotateY.getAngle() + deltaX);
        });

        scene.setOnMouseReleased(event -> scene.setCursor(Cursor.DEFAULT));

        primaryStage.setTitle("3D Stomach Model");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
