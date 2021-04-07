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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    Color colBackground = Color.rgb(236, 254, 141);
    double strokeWidth = 3.0;
    public static void main(String[] Args) {
        launch(Args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 800, 800);
        scene.setFill(colBackground);

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
        drawBush(group);
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
        hat.setFill(Color.rgb(227, 50, 40));
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

    public void drawHatLine(Group group) {
        Path line = new Path();
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1);

        line.getElements().addAll(
                new MoveTo(85, 130),
                new CubicCurveTo(85, 155, 300, 184, 335, 130),
                new CubicCurveTo(335, 130, 210, 150, 85, 130)
        );
        line.setFill(Color.rgb(234, 135, 134));
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

    public void drawBush(Group group) {
        Path green1 = new Path();
        green1.getElements().addAll(
                new MoveTo(150, 90),
                new CubicCurveTo(165, 50, 180, 35, 180, 90)
        );

        Rectangle r1 = new Rectangle(100, 200, 100, 100);
        r1.setFill(Color.rgb(177, 246, 12));
        group.getChildren().addAll(r1);

        Rectangle r2 = new Rectangle(225, 270, 50, 100);
        r2.setFill(Color.rgb(177, 246, 12));
        group.getChildren().addAll(r2);

        Path green11 = new Path();
        green11.getElements().addAll(
                new MoveTo(130, 250),
                new QuadCurveTo(155, 240, 180, 280),
                new QuadCurveTo(155, 300, 130, 250)
        );

        green11.setStroke(Color.BLACK);
        green11.setFill(Color.rgb(184, 250, 17));
        group.getChildren().add(green11);

        Path green12 = new Path();
        green12.getElements().addAll(
                new MoveTo(130, 250),
                new QuadCurveTo(155, 300, 180, 285),
                new QuadCurveTo(140, 350, 130, 250)
        );

        green12.setStroke(Color.BLACK);
        green12.setFill(Color.rgb(96, 238, 11));
        group.getChildren().add(green12);


        Path green13 = new Path();
        green13.getElements().addAll(
                new MoveTo(70, 260),
                new LineTo(100, 240),
                new QuadCurveTo(105, 265, 110, 280),
                new LineTo(110, 290),
                new LineTo(100, 302),
                new LineTo(85, 295),
                new LineTo(85, 275),
                new LineTo(70, 260)
        );

        green13.setStroke(Color.BLACK);
        green13.setFill(Color.rgb(96, 157, 7));
        group.getChildren().add(green13);

        Path green14 = new Path();
        green14.getElements().addAll(
                new MoveTo(100, 302),
                new LineTo(85, 309),
                new QuadCurveTo(85, 335, 115, 335),
                new QuadCurveTo(115, 310, 145, 305),
                new LineTo(145, 297),
                new LineTo(150, 297),
                new LineTo(145, 285),
                new QuadCurveTo(110, 290, 100, 302)

        );
        green14.setStroke(Color.BLACK);
        green14.setFill(Color.rgb(175, 245, 11));
        group.getChildren().add(green14);

        Path green15 = new Path();
        green15.getElements().addAll(
                new MoveTo(115, 335),
                new QuadCurveTo(115, 310, 145, 305),
                new QuadCurveTo(150, 315, 163, 307),
                new QuadCurveTo(163, 354, 190, 358),
                new QuadCurveTo(185, 385, 163, 390),
                new QuadCurveTo(160, 375, 150, 380),
                new QuadCurveTo(120, 385, 125, 350)
        );

        Path green17 = new Path();
        green17.getElements().addAll(
                new MoveTo(375, 381),
                new LineTo(375, 350),
                new LineTo(295, 342),
                new LineTo(295, 280),
                new QuadCurveTo(270, 225, 250, 220),
                new LineTo(250, 200),
                new LineTo(225, 190),
                new QuadCurveTo(233, 187, 233, 147),
                new CubicCurveTo(190, 147, 225, 360, 165, 395),
                new LineTo(233, 395),
                new QuadCurveTo(240, 387, 260, 389),
                new LineTo(270, 384),
                new CubicCurveTo(289, 385, 296, 394, 305, 386),
                new CubicCurveTo(323, 389, 350, 394, 375, 381)
        );

        green15.setStroke(Color.BLACK);
        green15.setFill(Color.rgb(94, 158, 5));
        group.getChildren().add(green15);

        green17.setStroke(Color.BLACK);
        green17.setFill(Color.rgb(94, 163, 0));
        group.getChildren().add(green17);

        Path green19 = new Path();
        green19.getElements().addAll(
                new MoveTo(325, 350),
                new QuadCurveTo(370, 360, 370, 325),
                new QuadCurveTo(360, 295, 315, 305)
        );

        Path green20 = new Path();
        green20.getElements().addAll(
                new MoveTo(315, 305),
                new QuadCurveTo(348, 295, 343, 288),
                new QuadCurveTo(305, 288, 315, 305)
        );

        green19.setStroke(Color.BLACK);
        green19.setFill(Color.rgb(174, 235, 20));
        group.getChildren().add(green19);

        green20.setStroke(Color.BLACK);
        green20.setFill(Color.rgb(106, 236, 45));
        group.getChildren().add(green20);

        Path green10 = new Path();
        green10.getElements().addAll(
                new MoveTo(150, 210),
                new QuadCurveTo(140, 225, 148, 240),
                new QuadCurveTo(140, 250, 155, 265),
                new LineTo(165, 270),
                new QuadCurveTo(180, 210, 150, 210)
        );

        green10.setStroke(Color.BLACK);
        green10.setFill(Color.rgb(83, 153, 8));
        group.getChildren().add(green10);

        green1.setStroke(Color.BLACK);
        green1.setFill(Color.rgb(105, 232, 27));
        group.getChildren().add(green1);

        Path green3 = new Path();
        green3.getElements().addAll(
                new MoveTo(200, 35),
                new CubicCurveTo(200, 110, 170, 33, 200, 35)
        );
        green3.setStroke(Color.BLACK);
        green3.setFill(Color.rgb(105, 232, 27));
        group.getChildren().add(green3);

        Path green2 = new Path();
        green2.getElements().addAll(
                new MoveTo(180, 35),
                new CubicCurveTo(210, 15, 200, 70, 180, 70),
                new QuadCurveTo(175, 45, 180, 35)
        );
        green2.setStroke(Color.BLACK);
        green2.setFill(Color.rgb(177, 246, 12));
        group.getChildren().add(green2);


        Path green4 = new Path();
        green4.getElements().addAll(
                new MoveTo(100, 140),
                new CubicCurveTo(173, 145, 135, 160, 100, 180),
                new QuadCurveTo(95, 160, 100, 140)
        );

        green4.setStroke(Color.BLACK);
        green4.setFill(Color.rgb(177, 246, 12));
        group.getChildren().add(green4);

        Path green5 = new Path();
        green5.getElements().addAll(
                new MoveTo(95, 200),
                new CubicCurveTo(95, 160, 102, 170, 105, 190)
        );

        Path green6 = new Path();
        green6.getElements().addAll(
                new MoveTo(105, 190),
                new CubicCurveTo(95, 190, 50, 220, 105, 265),
                new QuadCurveTo(108, 215, 105, 190)
        );

        green5.setStroke(Color.BLACK);
        green5.setFill(Color.rgb(106, 148, 52));
        group.getChildren().add(green5);

        green6.setStroke(Color.BLACK);
        green6.setFill(Color.rgb(184, 237, 25));
        group.getChildren().add(green6);

        Path green7 = new Path();
        green7.getElements().addAll(
                new MoveTo(110, 215),
                new QuadCurveTo(102, 169, 102, 180),
                new QuadCurveTo(140, 145, 170, 145),
                new QuadCurveTo(150, 175, 110, 215)
        );

        green7.setStroke(Color.BLACK);
        green7.setFill(Color.rgb(106, 148, 52));
        group.getChildren().add(green7);

        Path green9 = new Path();
        green9.getElements().addAll(
                new MoveTo(180, 175),
                new QuadCurveTo(165, 200, 150, 210),
                new QuadCurveTo(140, 245, 108, 240),
                new QuadCurveTo(108, 215, 108, 200)
        );

        green9.setStroke(Color.BLACK);
        green9.setFill(Color.rgb(69, 243, 1));
        group.getChildren().add(green9);

        Path green8 = new Path();
        green8.getElements().addAll(
                new MoveTo(170, 145),
                new QuadCurveTo(95, 220, 180, 175),
                new QuadCurveTo(180, 145, 170, 145)
        );

        green8.setStroke(Color.BLACK);
        green8.setFill(Color.rgb(173, 242, 11));
        group.getChildren().add(green8);
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

        Ellipse el1 = createEllipse(266, 110, 10, 3, 168);
        Ellipse el2 = createEllipse(294, 122, 10, 3, 168);
        Ellipse el3 = createEllipse(229, 104, 10, 3, 168);
        Ellipse el4 = createEllipse(229, 117, 10, 4, 168);
        Ellipse el5 = createEllipse(260, 127, 10, 4, 170);
        Ellipse el6 = createEllipse(152, 100, 12, 3, 10);
        Ellipse el7 = createEllipse(160, 122, 10, 3, 10);
        Ellipse el8 = createEllipse(180, 104, 10, 3, 8);
        Ellipse el9 = createEllipse(128, 117, 10, 4, 10);
        Ellipse el10 = createEllipse(110, 127, 11, 3, 15);
        Ellipse el11 = createEllipse(140, 127, 11, 3, 12);
        Ellipse el12 = createEllipse(175, 133, 11, 4, 8);
        Ellipse el13 = createEllipse(180, 120, 8, 3, 0);
        Ellipse el14 = createEllipse(200, 110, 8, 3, 0);
        Ellipse el15 = createEllipse(205, 120, 10, 3, 0);
        Ellipse el16 = createEllipse(240, 130, 10, 3, 170);
        Ellipse el17 = createEllipse(100, 135, 5, 3, 10);
        Ellipse el18 = createEllipse(150, 145, 10, 3, 10);
        Ellipse el19 = createEllipse(190, 150, 6, 4, 0);
        Ellipse el20 = createEllipse(255, 147, 10, 3, 170);
        Ellipse el21 = createEllipse(290, 147, 10, 3, 170);
        Ellipse el22 = createEllipse(310, 140, 8, 3, 170);
        Ellipse el23 = createEllipse(220, 147, 8, 3, 170);

        Ellipse [] ellipses = new Ellipse[] {el1, el2, el3, el4, el5,
                                            el6, el7, el8, el9, el10,
                                            el11, el12, el13, el14, el15,
                                            el16, el17, el18, el19, el20,
                                            el21, el22, el23};

        for (int i = 0; i < ellipses.length; i++) {
            group.getChildren().add(ellipses[i]);
        }
    }

    private Ellipse createEllipse(int x1, int y1, int w, int h, double alpha) {
        Ellipse ellipse = new Ellipse(x1, y1, w, h);
        ellipse.setFill(Color.WHITE);
        ellipse.setRotate(alpha);
        return ellipse;
    }
}
