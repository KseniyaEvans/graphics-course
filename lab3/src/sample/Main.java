package sample;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    Color colorBackground = Color.rgb(236, 254, 141);
    Color colorGrassLight = Color.rgb(177, 246, 12);
    Color colorGrassDark = Color.rgb(114, 155, 47);
    Color colorGrassBrown = Color.rgb(131, 128, 38);
    Color colorHat = Color.rgb(227, 50, 40);
    Color colorHatPart = Color.rgb(234, 135, 134);
    double strokeWidth = 3.0;
    public static void main(String[] Args) {
        launch(Args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 800, 800);
        scene.setFill(colorBackground);

        drawImage(group);

        primaryStage.setScene(scene);
        primaryStage.show();

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000), group);
        translateTransition.setFromX(200);
        translateTransition.setToX(250);
        translateTransition.setFromY(150);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(3000), group);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), group);
        scaleTransition.setToX(2f);
        scaleTransition.setToY(2f);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                translateTransition,
                rotateTransition,
                scaleTransition
        );

        primaryStage.setTitle("Mushrooms");
        parallelTransition.play();
    }

    public void drawImage(Group group) {
        drawGrass(group);

        drawLeg(group);
        drawLegPart(group);
        drawNeck(group);

        drawHat(group);
        drawHatLine(group);
        drawSpots(group);

        drawChild(group);
    }

    public void drawHat(Group group) {
        Path hat = new Path();
        hat.setStrokeWidth(strokeWidth);
        hat.setFill(colorHat);
        hat.setStrokeWidth(strokeWidth);
        hat.getElements().addAll(
                new MoveTo(95, 145),
                new ArcTo(10.0, 10.0, 0, 100, 120, false, true),
                new QuadCurveTo(130, 105, 150, 90),
                new CubicCurveTo(170, 60, 250, 60, 250, 90),
                new QuadCurveTo(260, 105, 320, 120),
                new ArcTo(10, 10, 0, 325, 145, false, true),
                new QuadCurveTo(210, 190, 95, 145)
        );
        group.getChildren().add(hat);
    }
    public void drawGrass(Group group) {
        Ellipse bush1 = createEllipse(170-30, 320-30, 100, 30, 30, colorGrassLight);
        Ellipse bush2 = createEllipse(310-30, 320-30, 100, 30, 150, colorGrassDark);
        Ellipse bush3 = createEllipse(290-30, 310-30, 100, 30, 130, colorGrassLight);
        Ellipse bush4 = createEllipse(190-30, 290-30, 100, 30, 70, colorGrassBrown);

        Ellipse bush5 = createEllipse(170+100, 320+20, 45, 20, 30, colorGrassBrown);
        Ellipse bush6 = createEllipse(170+120, 320+10, 45, 20, 70, colorGrassLight);
        Ellipse bush7 = createEllipse(290+30, 310+20, 45, 20, 120, colorGrassDark);
        Ellipse bush8 = createEllipse(290+40, 310+40, 45, 20, 165, colorGrassLight);

        bush1.setStrokeWidth(strokeWidth);
        bush1.setStroke(Color.BLACK);
        bush2.setStrokeWidth(strokeWidth);
        bush2.setStroke(Color.BLACK);
        bush3.setStrokeWidth(strokeWidth);
        bush3.setStroke(Color.BLACK);
        bush4.setStrokeWidth(strokeWidth);
        bush4.setStroke(Color.BLACK);

        bush5.setStrokeWidth(strokeWidth);
        bush5.setStroke(Color.BLACK);
        bush6.setStrokeWidth(strokeWidth);
        bush6.setStroke(Color.BLACK);
        bush7.setStrokeWidth(strokeWidth);
        bush7.setStroke(Color.BLACK);
        bush8.setStrokeWidth(strokeWidth);
        bush8.setStroke(Color.BLACK);

        group.getChildren().add(bush4);
        group.getChildren().add(bush1);
        group.getChildren().add(bush2);
        group.getChildren().add(bush3);

        group.getChildren().add(bush5);
        group.getChildren().add(bush6);
        group.getChildren().add(bush7);
        group.getChildren().add(bush8);

    }

    public void drawHatLine(Group group) {
        Path line = new Path();
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1);

        line.getElements().addAll(
                new MoveTo(85, 130),
                new CubicCurveTo(85, 155, 300, 184, 335, 130),
                new CubicCurveTo(335, 130, 210, 150, 85, 130)
        );
        line.setFill(colorHatPart);
        group.getChildren().add(line);
    }

    public void drawLeg(Group group) {
        Path leg = new Path();
        leg.getElements().addAll(
                new MoveTo(170, 210),
                new QuadCurveTo(150, 354, 190, 358),
                new CubicCurveTo(210, 358, 250, 358, 215, 212)
        );
        leg.setFill(Color.WHITE);
        leg.setStrokeWidth(strokeWidth);
        leg.setStroke(Color.BLACK);
        group.getChildren().add(leg);
    }

    public void drawNeck(Group group) {
        Path neck = new Path();
        neck.setStrokeWidth(strokeWidth);
        neck.setFill(Color.WHITE);
        neck.getElements().addAll(
                new MoveTo(180, 153),
                new LineTo(165, 200),
                new QuadCurveTo(150, 210, 150, 215),
                new LineTo(165, 215),
                new QuadCurveTo(170, 220, 179, 219),
                new QuadCurveTo(182, 215, 185, 215),
                new QuadCurveTo(200, 228, 205, 222),
                new LineTo(220, 222),
                new QuadCurveTo(220, 219, 235, 215),
                new LineTo(235, 207),
                new QuadCurveTo(220, 175, 220, 153)
        );
        group.getChildren().add(neck);
    }

    public void drawLegPart(Group group) {
        Path partLeg = new Path();
        partLeg.getElements().addAll(
                new MoveTo(216, 222),
                new LineTo(220, 223),
                new QuadCurveTo(250, 340, 217, 350)
        );
        partLeg.setFill(Color.WHITE);
        partLeg.setStrokeWidth(strokeWidth);
        partLeg.setStroke(Color.BLACK);
        group.getChildren().add(partLeg);
    }

    int[][] xy_circles = new int[][] {
            {290, 235},
            {270, 235},
            {285, 217},
            {300, 222},
            {325, 227},
            {335, 250},
            {320, 245},
            {305, 275},
            {290, 260},
            {270, 260},
            {320, 270}
    };

    public void drawChild(Group group) {
        Path childLeg = new Path();
        childLeg.getElements().addAll(
                new MoveTo(290, 240),
                new CubicCurveTo(290, 340, 240, 380, 300, 380),
                new CubicCurveTo(360, 380, 315, 340, 305, 240)
        );
        childLeg.setFill(Color.WHITE);
        childLeg.setStrokeWidth(strokeWidth);
        childLeg.setStroke(Color.BLACK);
        group.getChildren().add(childLeg);
        //
        Circle hat = new Circle(300, 250, 40);
        hat.setStrokeWidth(strokeWidth);
        hat.setStroke(Color.BLACK);
        hat.setFill(Color.rgb(245, 1, 14));
        group.getChildren().add(hat);
        //
        for (int i = 0; i < xy_circles.length; i++) {
            Circle circle = new Circle(xy_circles[i][0], xy_circles[i][1], 3);
            circle.setFill(Color.WHITE);
            group.getChildren().add(circle);
        }
    }

    public void drawSpots(Group group) {
        Path round = new Path();
        round.getElements().addAll(
                new MoveTo(150, 90),
                new CubicCurveTo(200, 110, 235, 97, 240, 80)
        );
        round.setStrokeWidth(strokeWidth-1);
        group.getChildren().add(round);

        Ellipse el1 = createEllipse(266, 110, 10, 3, 168, Color.WHITE);
        Ellipse el2 = createEllipse(294, 122, 10, 3, 168, Color.WHITE);
        Ellipse el3 = createEllipse(229, 104, 10, 3, 168, Color.WHITE);
        Ellipse el4 = createEllipse(229, 117, 10, 4, 168, Color.WHITE);
        Ellipse el5 = createEllipse(260, 127, 10, 4, 170, Color.WHITE);
        Ellipse el6 = createEllipse(152, 100, 12, 3, 10, Color.WHITE);
        Ellipse el7 = createEllipse(160, 122, 10, 3, 10, Color.WHITE);
        Ellipse el8 = createEllipse(180, 104, 10, 3, 8, Color.WHITE);
        Ellipse el9 = createEllipse(128, 117, 10, 4, 10, Color.WHITE);
        Ellipse el10 = createEllipse(110, 127, 11, 3, 15, Color.WHITE);
        Ellipse el11 = createEllipse(140, 127, 11, 3, 12, Color.WHITE);
        Ellipse el12 = createEllipse(175, 133, 11, 4, 8, Color.WHITE);
        Ellipse el13 = createEllipse(180, 120, 8, 3, 0, Color.WHITE);
        Ellipse el14 = createEllipse(200, 110, 8, 3, 0, Color.WHITE);
        Ellipse el15 = createEllipse(205, 120, 10, 3, 0, Color.WHITE);
        Ellipse el16 = createEllipse(240, 130, 10, 3, 170, Color.WHITE);
        Ellipse el17 = createEllipse(100, 135, 5, 3, 10, Color.WHITE);
        Ellipse el18 = createEllipse(150, 145, 10, 3, 10, Color.WHITE);
        Ellipse el19 = createEllipse(190, 150, 6, 4, 0, Color.WHITE);
        Ellipse el20 = createEllipse(255, 147, 10, 3, 170, Color.WHITE);
        Ellipse el21 = createEllipse(290, 147, 10, 3, 170, Color.WHITE);
        Ellipse el22 = createEllipse(310, 140, 8, 3, 170, Color.WHITE);
        Ellipse el23 = createEllipse(220, 147, 8, 3, 170, Color.WHITE);

        Ellipse [] ellipses = new Ellipse[] {el1, el2, el3, el4, el5,
                                            el6, el7, el8, el9, el10,
                                            el11, el12, el13, el14, el15,
                                            el16, el17, el18, el19, el20,
                                            el21, el22, el23};

        for (int i = 0; i < ellipses.length; i++) {
            group.getChildren().add(ellipses[i]);
        }
    }

    private Ellipse createEllipse(int x1, int y1, int w, int h, double alpha, Color color) {
        Ellipse ellipse = new Ellipse(x1, y1, w, h);
        ellipse.setFill(color);
        ellipse.setRotate(alpha);
        return ellipse;
    }
}
