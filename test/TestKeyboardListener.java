package test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestKeyboardListener extends JFrame {
    class keyBoardListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println("typed:" + KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("pressed:" + KeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("released:" + KeyEvent.getKeyText(e.getKeyCode()));
        }
    }

    public TestKeyboardListener(){
        this.setSize(300, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addKeyListener(new keyBoardListener());
    }

    public static void main(String[] args) {
        new TestKeyboardListener();
    }
}
