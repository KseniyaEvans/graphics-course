package com.company;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class UFO extends JFrame {
    private Canvas3D canvas;
    private SimpleUniverse universe;
    private BranchGroup root;

    private TransformGroup space = new TransformGroup();
    private TransformGroup Ufo;

    private Map<String, Shape3D> shapeMap;
    private Color lightColor = Color.white;
    private Color cockpitColor = new Color(108, 164, 248);

    private static SimpleAudioPlayer audioPlayer;
    private static String filePath = "resources/ufo.wav";

    public UFO() throws IOException {
        configureWindow();
        configureCanvas();
        configureUniverse();

        root = new BranchGroup();
        root.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        addImageBackground("resources/cat.jpg");
        addLightToUniverse();
        ChangeViewAngle();
        Ufo = getUFOGroup();
        space.addChild(Ufo);
        root.addChild(space);
        addAppearance();
        Animation UfoAnimation = new Animation(this);
        canvas.addKeyListener(UfoAnimation);
        root.compile();
        universe.addBranchGraph(root);
        playMusic();
    }

    private void playMusic() {
        try {
            audioPlayer = new SimpleAudioPlayer(filePath, 0);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        audioPlayer.play();
    }

    private void configureWindow() {
        setTitle("UFO Animation");
        setSize(973, 640);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureCanvas() {
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        canvas.setFocusable(true);
        add(canvas, BorderLayout.CENTER);
    }

    private void configureUniverse() {
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }

    private void addImageBackground(String path) {
        TextureLoader t = new TextureLoader(path, canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }

    private void addLightToUniverse() {
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(1000);

        DirectionalLight directionalLight = new DirectionalLight(
                new Color3f(lightColor),
                new Vector3f(0, -0.5f, -0.5f));
        directionalLight.setInfluencingBounds(bounds);

        AmbientLight ambientLight = new AmbientLight(
                new Color3f(lightColor));
        ambientLight.setInfluencingBounds(bounds);

        root.addChild(directionalLight);
        root.addChild(ambientLight);
    }

    private void ChangeViewAngle() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();
        vpTranslation.setTranslation(new Vector3f(0, 0, 6));
        vpGroup.setTransform(vpTranslation);
    }

    private TransformGroup getUFOGroup() throws IOException {
        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(-10, -10, 0));
        TransformGroup group = getModelGroup("resources/ufo.obj");
        group.setTransform(transform3D);

        return group;
    }

    private TransformGroup getModelGroup(String path) throws IOException {
        Scene scene = getSceneFromFile(path);
        shapeMap = scene.getNamedObjects();
        printModelElementsList(shapeMap);
        TransformGroup group = new TransformGroup();

        for (String shapeName : shapeMap.keySet()) {
            Shape3D shape = shapeMap.get(shapeName);
            scene.getSceneGroup().removeChild(shape);
            group.addChild(shape);
        }

        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        return group;
    }

    private void printModelElementsList(Map<String, Shape3D> shapeMap) {
        for (String name : shapeMap.keySet()) {
            System.out.printf("Name: %s\n", name);
        }
    }

    public static Scene getSceneFromFile(String location) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(location));
    }

    private void addAppearance() {
        Appearance bodyAppearance = new Appearance();
        bodyAppearance.setTexture(getTexture("resources/ufo_diffuse.png"));
        TextureAttributes texAttr1 = new TextureAttributes();
        texAttr1.setTextureMode(TextureAttributes.BLEND);
        bodyAppearance.setMaterial(getMaterial(
                Color.BLACK,
                new Color(236, 232, 232)));
        shapeMap.get("ufo_body").setAppearance(bodyAppearance);

        Appearance cockpitAppearance = new Appearance();
        cockpitAppearance.setTexture(getTexture("resources/ufo_diffuse_glow.png"));
        TextureAttributes texAttr2 = new TextureAttributes();
        texAttr2.setTextureMode(TextureAttributes.BLEND);
        cockpitAppearance.setTextureAttributes(texAttr2);
        cockpitAppearance.setMaterial(getMaterial(Color.BLUE, cockpitColor));
        Shape3D cockpit = shapeMap.get("ufo_cockpit");
        cockpit.setAppearance(cockpitAppearance);
    }

    public TransformGroup getUfoTransformGroup() {
        return Ufo;
    }

    Texture getTexture(String path) {
        TextureLoader textureLoader = new TextureLoader(path, "LUMINANCE", canvas);
        Texture texture = textureLoader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));
        return texture;
    }

    Material getMaterial(Color emissiveColor, Color defaultColor) {
        Material material = new Material();
        material.setEmissiveColor(new Color3f(emissiveColor));
        material.setAmbientColor(new Color3f(defaultColor));
        material.setDiffuseColor(new Color3f(defaultColor));
        material.setSpecularColor(new Color3f(defaultColor));
        material.setShininess(61);
        material.setLightingEnable(true);
        return material;
    }
}
