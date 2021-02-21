package com.company;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 250);
        float strokeWidth = 8.0F;
        scene.setFill(Color.rgb(251, 0, 6));

        Polygon p1 = getPolygon(89.0, 38.0, 185.0, 17.0, 205.0,  124.0, 110.0, 138.0);
        Polygon p2 = getPolygon(227.0, 34.0, 331.0, 79.0, 287.0,  162.0, 200.0,  123.0);
        Polygon p3 = getPolygon(140.0, 161.0, 204.0, 120.0, 256.0,  195.0, 183.0,  240.0);

        float xCenter1 = getCenter(205,89);
        float yCenter1 = getCenter(138,17);

        float xCenter2 = getCenter(331,200);
        float yCenter2 = getCenter(162,34);

        float xCenter3 = getCenter(256,140);
        float yCenter3 = getCenter(240,120);

        Polyline polyline = new Polyline(xCenter1, yCenter1, xCenter2, yCenter2, xCenter3, yCenter3, xCenter1, yCenter1, xCenter2, yCenter2);
        polyline.setStrokeWidth(strokeWidth);
        polyline.setStroke(Color.rgb(105, 0, 253));

        root.getChildren().addAll(p1, p2, p3, polyline);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public Polygon getPolygon(Double x1, Double y1, Double x2, Double y2, Double x3, Double y3, Double x4, Double y4) {
        Polygon p = new Polygon();
        p.getPoints().addAll(new Double[]{
                x1, y1,
                x2, y2,
                x3, y3,
                x4, y4,});
        p.setFill(Color.rgb(34, 255, 5));
        return p;
    }

    public float getCenter(float x, float y) {
        return (x + y) / 2;
    }
}
