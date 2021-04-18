package com.company;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            UFO window = new UFO();
            window.setVisible(true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
