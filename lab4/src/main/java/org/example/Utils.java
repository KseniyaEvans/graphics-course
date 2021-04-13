package org.example;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.Color4f;
import java.awt.*;
import java.util.Objects;

public class Utils {

    public static Texture loadTexture(String picture) {
        TextureLoader loader = new TextureLoader(Objects.requireNonNull(Utils.class.getClassLoader().getResource(picture)), "RGB", new Container());
        Texture texture = loader.getTexture();

        // задаємо властивості границі
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));

        return texture;
    }

    public static Appearance getDiskAppearance(String picture) {
        Appearance ap = new Appearance();

        if (picture != "") {
            Texture texture = loadTexture(picture);
            TextureAttributes texAttr = new TextureAttributes();
            texAttr.setTextureMode(TextureAttributes.MODULATE);

            ap.setTexture(texture);
            ap.setTextureAttributes(texAttr);
        }
        return ap;
    }

    public static Appearance getHandleAppearance(String picture) {
        Appearance ap = new Appearance();
        Material material = new Material(); // create texture
        ap.setMaterial(material);

        if (picture != "") {
            Texture texture = loadTexture(picture);
            TextureAttributes texAttr = new TextureAttributes();
            texAttr.setTextureMode(TextureAttributes.MODULATE);

            ap.setTexture(texture);
            ap.setTextureAttributes(texAttr);
        }
        return ap;
    }
}


