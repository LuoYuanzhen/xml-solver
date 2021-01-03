package view;

import handler.OpenFileListenerHandle;
import handler.QueryListenerHanlder;
import handler.SaveFileListenerHandler;

import javax.swing.*;
import java.awt.*;

public class XPathView extends JFrame implements BaseView{

    private String[] typeMode = {"xml"};

    public JTextPane queryText;
    public JTextPane resultText;
    public JTextPane filePathText;
    public JTextPane xmlWindow;

    private JPanel mainLeftPanel;
    private JPanel mainRightPanel;

    private JPanel queryPanel;

    private JScrollPane xmlScrollPane;
    private JScrollPane queryTextScrollPane;
    private JScrollPane resultScrollPane;
    private JScrollPane filePathScrollPane;

    private JPanel xmlPanel;
    private JPanel filePanel;

    private JButton queryBtn;
    private JButton openBtn;
    private JButton saveBtn;


    private Dimension dimension;

    public XPathView(){
        this.setTitle("Xpath query window");
        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = dimension.width / 2, windowHeight = dimension.height / 2 + 100;
        this.setSize(windowWidth, windowHeight);
        this.setLayout(new GridLayout(1, 2, 5, 5));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.initLeft(windowWidth, windowHeight);
        this.initRight(windowWidth, windowHeight);

        this.add(mainLeftPanel);
        this.add(mainRightPanel);

        this.setVisible(true);
    }

    private void initLeft(int windowWidth, int windowHeight){

        int width = windowWidth / 2;
        int height = windowHeight;

        this.mainLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        this.xmlPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        this.filePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        Dimension dimension = new Dimension(width, height - 100);
        Font font = new Font(null, 0, 25);

        this.xmlWindow = new JTextPane();
        this.setTextPane(this.xmlWindow, font, dimension, Color.BLACK, true);
        this.xmlScrollPane = new JScrollPane(this.xmlWindow);
        this.xmlScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.filePathText = new JTextPane();
        this.setTextPane(this.filePathText, null, new Dimension(width / 3 - 15, 20), Color.BLACK,
                        true);
        this.filePathText.setText("/home/tolyzot/validate_file/test_university.xml");
        this.filePathScrollPane = new JScrollPane(this.filePathText);
        this.filePathScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.openBtn = new JButton("open xml");
        this.openBtn.addActionListener(new OpenFileListenerHandle(this, typeMode[0]));
        this.saveBtn = new JButton("save xml");
        this.saveBtn.addActionListener(new SaveFileListenerHandler(this, typeMode[0]));

        this.filePanel.add(this.filePathScrollPane);
        this.filePanel.add(this.openBtn);
        this.filePanel.add(this.saveBtn);

        this.xmlPanel.add(this.xmlScrollPane);
        this.xmlPanel.add(filePanel);
        this.mainLeftPanel.add(xmlPanel);
        this.mainLeftPanel.add(filePanel);

    }

    private void initRight(int windowWidth, int windowHeight){
        int width = windowWidth / 2;
        int height = windowHeight;

        this.mainRightPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        this.queryPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        Dimension dimension = new Dimension(width / 3 * 2, 30);
        Font font = new Font(null, 0, 25);

        this.queryText = new JTextPane();
        this.setTextPane(this.queryText, font, dimension, Color.BLACK, true);
        this.queryTextScrollPane = new JScrollPane(this.queryText);
        this.queryTextScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.resultText = new JTextPane();
        this.setTextPane(this.resultText, font, new Dimension(width - 20, height - 100),
                Color.BLACK, false);
        this.resultScrollPane = new JScrollPane(this.resultText);

        this.queryBtn = new JButton("query");
        this.queryBtn.setSize(width / 4, 30);
        this.queryBtn.addActionListener(new QueryListenerHanlder(this));

        this.queryPanel.add(queryTextScrollPane);
        this.queryPanel.add(queryBtn);
        this.mainRightPanel.add(queryPanel);
        this.mainRightPanel.add(resultScrollPane);

    }

    private void setTextPane(JTextPane textPane, Font font, Dimension dimension, Color color, boolean editable){
        if(font != null)
            textPane.setFont(font);
        textPane.setForeground(color);
        textPane.setPreferredSize(dimension);
        textPane.setEditable(editable);
    }

    @Override
    public String getFilePath(String type) {
        if(type.equals(this.typeMode[0]))
            return this.filePathText.getText();
        return null;
    }

    @Override
    public JTextPane getInfoTextPane() {
        return this.resultText;
    }

    @Override
    public JTextPane getFileTextPane(String type) {
        if(type.equals(this.typeMode[0]))
            return this.xmlWindow;
        return null;
    }


    public static void main(String[] args) {
        new XPathView();
    }
}
