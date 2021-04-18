package com.company;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.*;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Animation extends KeyAdapter implements ActionListener {
    private static final float DELTA_DISTANCE = 0.01f;
    private static final float DELTA_ANGLE = 0.05f;

    private UFO Ufo;
    private TransformGroup UfoTransformGroup;
    private Transform3D transform3D = new Transform3D();

    private float xLoc = 0;
    private float yLoc = 0;
    private float zoom = 0.8f;

    private float xAngle = 0;
    private float yAngle = 0;
    private float zAngle = 0;


    private boolean resetXRotation = false;
    private boolean resetYRotation = false;
    private boolean resetZRotation = false;

    private boolean isRotatedPosY = false;
    private boolean isRotatedNegY = false;

    private boolean isPressedW = false;
    private boolean isPressedS = false;
    private boolean isPressedA = false;
    private boolean isPressedD = false;
    private boolean isPressedRight = false;
    private boolean isPressedLeft = false;
    private boolean isPressedUp = false;
    private boolean isPressedDown = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        Move();
    }

    public Animation(UFO ufo) {
        this.Ufo = ufo;

        this.UfoTransformGroup = Ufo.getUfoTransformGroup();
        this.UfoTransformGroup.getTransform(this.transform3D);
        transform3D.setScale(new Vector3d(zoom,zoom,zoom));

        Timer timer = new Timer(20, this);
        timer.start();
    }

    private void Move() {
        if (isPressedW) {
            yLoc += DELTA_DISTANCE;
        }

        if (isPressedS) {
            yLoc -= DELTA_DISTANCE;
        }

        if (isPressedA) {
            xLoc -= DELTA_DISTANCE;
        }

        if (isPressedD) {
            xLoc += DELTA_DISTANCE;
        }

        transform3D.setTranslation(new Vector3f(xLoc, yLoc, 0));

        if (isPressedRight) {
            Transform3D rotation = new Transform3D();
            rotation.rotZ(DELTA_ANGLE);
            zAngle += DELTA_ANGLE;
            transform3D.mul(rotation);
        }

        if (isPressedLeft) {
            Transform3D rotation = new Transform3D();
            rotation.rotZ(-DELTA_ANGLE);
            zAngle -= DELTA_ANGLE;
            transform3D.mul(rotation);
        }

        if (isPressedUp) {
            Transform3D rotation = new Transform3D();
            rotation.rotX(-DELTA_ANGLE);
            xAngle -= DELTA_ANGLE;
            transform3D.mul(rotation);
        }

        if (isPressedDown) {
            Transform3D rotation = new Transform3D();
            rotation.rotX(DELTA_ANGLE);
            xAngle += DELTA_ANGLE;
            transform3D.mul(rotation);
        }

        if (isRotatedPosY) {
            Transform3D rotation = new Transform3D();
            rotation.rotY(degree(20));
            transform3D.mul(rotation);

            yAngle += degree(20);
            isRotatedPosY = false;
        }

        if (isRotatedNegY) {
            Transform3D rotation = new Transform3D();
            rotation.rotY(degree(-20));
            transform3D.mul(rotation);

            yAngle += degree(-20);
            isRotatedNegY = false;
        }

        if (resetYRotation) {
            Transform3D rotation = new Transform3D();
            rotation.rotY(-yAngle);
            transform3D.mul(rotation);

            resetYRotation = false;
            yAngle = 0;
        }

        if (resetZRotation) {
            Transform3D rotation = new Transform3D();
            rotation.rotZ(-zAngle);
            transform3D.mul(rotation);

            resetZRotation = false;
            zAngle = 0;
        }

        if (resetXRotation) {
            Transform3D rotation = new Transform3D();
            rotation.rotX(-xAngle);
            transform3D.mul(rotation);

            resetXRotation = false;
            xAngle = 0;
        }

        UfoTransformGroup.setTransform(transform3D);
    }

    @Override
    public void keyPressed(KeyEvent ev) {
        switch (ev.getKeyCode()) {
            case 87: // W
                isPressedW = true;
                break;
            case 83: // S
                isPressedS = true;
                break;
            case 65: // A
                if (!isPressedA) {
                    isPressedA = true;
                    isRotatedNegY = true;
                }
                break;
            case 68: // D
                if (!isPressedD) {
                    isPressedD = true;
                    isRotatedPosY = true;
                }
                break;
            case KeyEvent.VK_LEFT:
                isPressedLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                isPressedRight = true;
                break;
            case KeyEvent.VK_UP:
                isPressedUp = true;
                break;
            case KeyEvent.VK_DOWN:
                isPressedDown = true;
                break;
            case 48: // 0
                resetXRotation = true;
                resetYRotation = true;
                resetZRotation = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ev) {
        switch (ev.getKeyCode()) {
            case 87: // W
                isPressedW = false;
                break;
            case 83: //S
                isPressedS = false;
                break;
            case 65: //A
                isPressedA = false;
                resetYRotation = true;
                break;
            case 68: //D
                isPressedD = false;
                resetYRotation = true;
                break;
            case KeyEvent.VK_RIGHT:
                isPressedRight = false;
                break;
            case KeyEvent.VK_LEFT:
                isPressedLeft = false;
                break;
            case KeyEvent.VK_UP:
                isPressedUp = false;
                break;
            case KeyEvent.VK_DOWN:
                isPressedDown = false;
                break;
        }
    }

    private float degree(float degrees) {
        return (float) (degrees * Math.PI / 180);
    }
}
