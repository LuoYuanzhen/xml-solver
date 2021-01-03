package test;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestSetTextColor {

    public static void main(String args[]) throws BadLocationException {
        JFrame jf = new JFrame("StyledText");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = jf.getContentPane();

        JTextPane pane = new JTextPane();
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setBold(set, true);

        // Set the attributes before adding text
        pane.setCharacterAttributes(set, true);
        pane.setText("www.w3cschool.cn ");

        set = new SimpleAttributeSet();
        StyleConstants.setItalic(set, true);
        StyleConstants.setForeground(set, Color.red);
        StyleConstants.setBackground(set, Color.blue);

        Document doc = pane.getStyledDocument();
        doc.insertString(doc.getLength(), "Swing ", set);


        set = new SimpleAttributeSet();
        StyleConstants.setFontSize(set, 24);

        doc.insertString(doc.getLength(), "Tutorial", set);

        JScrollPane scrollPane = new JScrollPane(pane);
        cp.add(scrollPane, BorderLayout.CENTER);

        jf.setSize(400, 300);
        jf.setVisible(true);
    }
}
