package view;

import handler.OpenFileListenerHandle;
import handler.SaveFileListenerHandler;
import handler.TransformerListenerHandler;

import javax.swing.*;
import java.awt.*;

public class XSLTView extends JFrame implements BaseView{

    private String[] typeMode = {"xml", "xsl"};

    public JTextPane xmlFilePathPane;
    public JTextPane xmlFileTextPane;
    public JTextPane xslFilePathPane;
    public JTextPane xslFileTextPane;
    public JTextPane htmlFilePathPane;

    public JTextPane resultPane;

    private JPanel leftPanel;
    private JPanel rightPanel;

    private JButton xmlOpenBtn;
    private JButton xslOpenBtn;
    private JButton xmlSaveBtn;
    private JButton xslSaveBtn;
    private JButton transBtn;

    private Dimension dimension;

    public XSLTView(){
        this.setTitle("XSLT transform view");
        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = dimension.width / 2, windowHeight = dimension.height / 2 + 100;
        this.setSize(windowWidth, windowHeight);
        this.setLayout(new GridLayout(1, 2, 5, 5));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.initLeft(windowWidth, windowHeight);
        this.initRight(windowWidth, windowHeight);

        this.add(this.leftPanel);
        this.add(this.rightPanel);

        this.setVisible(true);
    }

    private void initLeft(int windowWidth, int windowHeight){
        int width = (windowWidth - 15) / 2;
        int height = windowHeight - 10;

        this.leftPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        this.xmlFileTextPane = new JTextPane();
        this.xmlFilePathPane = new JTextPane();
        this.xmlFilePathPane.setText("/home/tolyzot/validate_file/xmlTest.xml");
        this.xmlOpenBtn = new JButton("open xml");
        this.xmlOpenBtn.addActionListener(new OpenFileListenerHandle(this, this.typeMode[0]));
        this.xmlSaveBtn = new JButton("save xml");
        this.xmlSaveBtn.addActionListener(new SaveFileListenerHandler(this, this.typeMode[0]));

        this.xslFileTextPane = new JTextPane();
        this.xslFilePathPane = new JTextPane();
        this.xslFilePathPane.setText("/home/tolyzot/validate_file/xslTest.xsl");
        this.xslOpenBtn = new JButton("open xsl");
        this.xslOpenBtn.addActionListener(new OpenFileListenerHandle(this, this.typeMode[1]));
        this.xslSaveBtn = new JButton("save xsl");
        this.xslSaveBtn.addActionListener(new SaveFileListenerHandler(this, this.typeMode[1]));

        Dimension filePathDimension = new Dimension(width / 2, 25);
        Dimension fileTextDimension = new Dimension(width, (height - 60 - 60) / 2);
        Font font = new Font(null, 0, 15);
        this.setTextPane(xmlFilePathPane, font, filePathDimension, Color.BLACK, true);
        this.setTextPane(xmlFileTextPane, font, fileTextDimension, Color.BLACK, true);
        this.setTextPane(xslFilePathPane, font, filePathDimension, Color.BLACK, true);
        this.setTextPane(xslFileTextPane, font, fileTextDimension, Color.BLACK, true);

        JScrollPane xmlPathScroll = new JScrollPane(this.xmlFilePathPane);
        xmlPathScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane xmlTextScroll = new JScrollPane(this.xmlFileTextPane);
        JScrollPane xslPathScroll = new JScrollPane(this.xslFilePathPane);
        xslPathScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane xslTextScroll = new JScrollPane(this.xslFileTextPane);


        upPanel.add(xmlTextScroll);
        upPanel.add(xmlPathScroll);
        upPanel.add(this.xmlOpenBtn);
        upPanel.add(this.xmlSaveBtn);
        bottomPanel.add(xslTextScroll);
        bottomPanel.add(xslPathScroll);
        bottomPanel.add(this.xslOpenBtn);
        bottomPanel.add(this.xslSaveBtn);

        this.leftPanel.add(upPanel);
        this.leftPanel.add(bottomPanel);

    }

    private void initRight(int windowWidth, int windowHeight){
        int width = (windowWidth - 15) / 2;
        int height = windowHeight - 10;

        this.rightPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        Dimension pathDimension = new Dimension(width / 3 * 2, 25);
        Dimension resultDimension = new Dimension(width - 20, height - 95);
        Font font = new Font(null, 0, 15);

        JLabel tipLabel = new JLabel("save to:");
        this.htmlFilePathPane = new JTextPane();
        this.htmlFilePathPane.setText("/home/tolyzot/validate_file/htmlTest.html");
        this.setTextPane(this.htmlFilePathPane, font, pathDimension, Color.BLACK, true);

        this.transBtn = new JButton("trans");
        this.transBtn.addActionListener(new TransformerListenerHandler(this));

        this.resultPane = new JTextPane();
        this.setTextPane(this.resultPane, font, resultDimension, Color.BLACK, false);

        JScrollPane pathScroll = new JScrollPane(this.htmlFilePathPane);
        pathScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane resultScroll = new JScrollPane(this.resultPane);

        this.rightPanel.add(tipLabel);
        this.rightPanel.add(pathScroll);
        this.rightPanel.add(transBtn);
        this.rightPanel.add(resultScroll);
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
        if(type.equals(typeMode[0])){
            return this.xmlFilePathPane.getText();
        }
        else {
            return this.xslFilePathPane.getText();
        }
    }

    @Override
    public JTextPane getFileTextPane(String type) {
        if(type.equals(typeMode[0])){
            return this.xmlFileTextPane;
        }
        else {
            return this.xslFileTextPane;
        }
    }

    @Override
    public JTextPane getInfoTextPane() {
        return this.resultPane;
    }

    public static void main(String[] args) {
        new XSLTView();
    }
}
