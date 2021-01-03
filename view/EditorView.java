package view;

import handler.OpenFileListenerHandle;
import handler.SaveFileListenerHandler;
import handler.ValidateListenerHandler;

import javax.swing.*;
import java.awt.*;


public class EditorView extends JFrame implements BaseView{

    public static final String MODE_DTD="dtd";
    public static final String MODE_XSD="xsd";
    private String[] typeMode = {"validateFile"};

    public JTextPane editor;
    public JTextPane infoWindow;
    public JTextPane fileWindow;
    public JTextPane validaterFilePath;

    private JScrollPane editorScroll;
    private JScrollPane infoWindowScroll;
    private JScrollPane fileWindowScroll;

    private JPanel mainPanel;
    private JPanel mainRightPanel;
    private JPanel buttonPanel;
    private JPanel buttonLeftPanel;
    private JPanel buttonRightPanel;
    private JPanel buttonGoup;

    private JButton validate;
    private JButton openValidaterFile;
    private JButton saveValidaterFile;

    private JRadioButton dtdRB;
    private JRadioButton xsdRB;
    private ButtonGroup modeGroup;

    private Dimension dimension;

    public EditorView(){
        this.setTitle("XML eidtor window");
        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(this.dimension.width, this.dimension.height);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        int editorWidth = this.dimension.width / 2 - 50;
        int editorHeight = this.dimension.height - 150;

        Dimension paneDimension = new Dimension(editorWidth, editorHeight);
        Dimension subPaneDimension = new Dimension(editorWidth, editorHeight / 2);
        Font textFont = new Font(null, 0, 30);

        this.mainPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        this.mainRightPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        this.editor = new JTextPane();
        this.editor.setPreferredSize(paneDimension);
        this.editor.setFont(textFont);

        this.fileWindow = new JTextPane();
        this.fileWindow.setPreferredSize(subPaneDimension);
        this.fileWindow.setFont(textFont);

        this.infoWindow = new JTextPane();
        this.infoWindow.setPreferredSize(subPaneDimension);
        this.infoWindow.setForeground(Color.RED);
        this.infoWindow.setFont(textFont);
        this.infoWindow.setEditable(false);

        this.editorScroll = new JScrollPane(this.editor);
        this.fileWindowScroll = new JScrollPane(this.fileWindow);
        this.infoWindowScroll = new JScrollPane(this.infoWindow);

        this.mainPanel.add(editorScroll);
        this.mainPanel.add(mainRightPanel);
        this.mainRightPanel.add(infoWindowScroll);
        this.mainRightPanel.add(fileWindowScroll);

        this.buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        this.buttonLeftPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        this.buttonRightPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        this.buttonGoup = new JPanel(new GridLayout(1, 2, 5, 5));

        this.validate = new JButton("validate");
        this.validate.addActionListener(new ValidateListenerHandler(this));
        this.validate.setSize(editorWidth, 50);

        this.initFileButton(editorWidth, 50);

        this.initFilePath(editorWidth / 3, 50);

        this.addRadioButton();

        this.buttonPanel.add(this.buttonLeftPanel);
        this.buttonPanel.add(this.buttonRightPanel);

        this.buttonLeftPanel.add(this.buttonGoup);
        this.buttonLeftPanel.add(this.validate);

        this.buttonRightPanel.add(this.validaterFilePath);
        this.buttonRightPanel.add(this.openValidaterFile);
        this.buttonRightPanel.add(this.saveValidaterFile);

        this.add(this.mainPanel);
        this.add(this.buttonPanel);

        this.setVisible(true);
    }

    private void initFileButton(int width, int height){
        OpenFileListenerHandle openListener = new OpenFileListenerHandle(this, this.typeMode[0]);
        SaveFileListenerHandler saveListener = new SaveFileListenerHandler(this, this.typeMode[0]);

        this.openValidaterFile = new JButton("open xsd/dtd");
        this.openValidaterFile.addActionListener(openListener);
        this.openValidaterFile.setSize(width, height);

        this.saveValidaterFile = new JButton("save xsd/dtd");
        this.saveValidaterFile.addActionListener(saveListener);
        this.saveValidaterFile.setSize(width, height);
    }

    private void initFilePath(int width, int height){

        this.validaterFilePath = new JTextPane();
        this.validaterFilePath.setText("/home/tolyzot/validate_file/university.dtd");
        this.validaterFilePath.setPreferredSize(new Dimension(width, height));

    }

    private void addRadioButton(){
        this.dtdRB = new JRadioButton("DTD");
        this.xsdRB = new JRadioButton("XSD");
        this.modeGroup = new ButtonGroup();

        this.modeGroup.add(this.dtdRB);
        this.modeGroup.add(this.xsdRB);

        this.buttonGoup.add(dtdRB);
        this.buttonGoup.add(xsdRB);
        this.buttonGoup.setSize(50, 50);

        this.dtdRB.setSelected(true);
    }

    public String getMode(){
        return this.dtdRB.isSelected()?MODE_DTD:MODE_XSD;
    }


    public static void main(String[] args) {
        new EditorView();
    }

    @Override
    public String getFilePath(String type) {
        String result = "";
        if(type.equals(this.typeMode[0]))
            result = this.validaterFilePath.getText();
        return result;
    }

    @Override
    public JTextPane getInfoTextPane() {
        return this.infoWindow;
    }

    @Override
    public JTextPane getFileTextPane(String type) {
        if (type.equals(this.typeMode[0]))
            return this.fileWindow;
        else
            return null;
    }
}
