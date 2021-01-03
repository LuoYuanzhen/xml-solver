package view;

import javax.swing.*;

public interface BaseView {
    String getFilePath(String type);
    JTextPane getFileTextPane(String type);
    JTextPane getInfoTextPane();
}
