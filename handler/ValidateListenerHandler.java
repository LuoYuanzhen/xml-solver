package handler;

import entity.ErrorInfo;
import entity.XMLText;
import parser.DTDXMLParser;
import parser.XMLParser;
import parser.XSDXMLParser;
import view.EditorView;

import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValidateListenerHandler implements ActionListener {

    private EditorView editorView;

    public ValidateListenerHandler(EditorView editorView){
        this.editorView = editorView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String validateMode = this.editorView.getMode();

        XMLParser xmlParser = null;
        if(validateMode.equals(EditorView.MODE_DTD)){
            xmlParser = new DTDXMLParser();
        }else {
            xmlParser = new XSDXMLParser();
        }
        callParser(xmlParser);

        XMLText xmlText = xmlParser.getXmlText();
        List<ErrorInfo> errorInfoList = xmlParser.getErrorInfoList();
        String report = xmlParser.reportMessage();
        if(report.equals("")){
            report = "validate CORRECT.";
        }

        this.editorView.infoWindow.setText(report);
        List<int[]> underlinePositions = calculatePosition(xmlText, errorInfoList);
        reWriteEditor(xmlText);
        drawUnderlines(underlinePositions);
    }

    private void callParser(XMLParser xmlParser){
        String xmlContent = this.editorView.editor.getText();
        xmlParser.setXmlContent(xmlContent);
        xmlParser.parser();
    }

    private void reWriteEditor(XMLText xmlText){
        List<Integer> characterNumber = xmlText.getCharacterNumber();
        List<Integer> soFarNumber = xmlText.getSoFarNumber();

        int line = characterNumber.size() - 1;
        int len = characterNumber.get(line) + soFarNumber.get(line);
        StyledDocument document = this.editorView.editor.getStyledDocument();
        try {
            document.remove(0, len);
            document.insertString(0, xmlText.getXmlString(), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private List<int[]> calculatePosition(XMLText xmlText, List<ErrorInfo> errorInfoList){
        List<int[]> underlinePositions = new ArrayList<>();

        List<Integer> characterNumber = xmlText.getCharacterNumber();
        List<Integer> soFarNumber = xmlText.getSoFarNumber();
        for (ErrorInfo errorInfo: errorInfoList){
            int[] underlinePos = new int[2];

            int[] pos = errorInfo.getPos();
            int row = pos[0] - 1; // pos[0]:10, get(9)
            underlinePos[0] = soFarNumber.get(row);
            underlinePos[1] = characterNumber.get(row);


            underlinePositions.add(underlinePos);
        }

        return underlinePositions;
    }

    private void drawUnderlines(List<int[]> poses){

        if(poses.size() == 0)
            return;
        try {
            StyledDocument document = this.editorView.editor.getStyledDocument();
            SimpleAttributeSet attributeSet = new SimpleAttributeSet();
            StyleConstants.setForeground(attributeSet, Color.red);
            StyleConstants.setUnderline(attributeSet, true);
            for (int[] pos : poses) {
                System.out.println(pos[0] + "," + pos[1]);
                document.setCharacterAttributes(pos[0], pos[1], attributeSet, false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
