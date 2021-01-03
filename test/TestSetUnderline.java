package test;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextPaneUI;
import javax.swing.text.*;
import java.awt.*;

public class TestSetUnderline {
    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextPane pane = new JTextPane();
        pane.setEditorKit(new CustomEditorKit());
        pane.setText("Underline With Different Color");

        StyledDocument doc = (StyledDocument) pane.getDocument();
        MutableAttributeSet attrs = new SimpleAttributeSet();
        attrs.addAttribute("Underline-Color", Color.red);
        doc.setCharacterAttributes(0, doc.getLength() - 1, attrs, true);

        JScrollPane sp = new JScrollPane(pane);
        frame.setContentPane(sp);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

class CustomEditorKit extends StyledEditorKit {

    public ViewFactory getViewFactory() {
        System.out.println("customEditorKit");
        return new CustomUI();
    }
}

class CustomUI extends BasicTextPaneUI {
    @Override
    public View create(Element elem) {
        System.out.println("create");
        View result = null;
        String kind = elem.getName();
        if (kind != null) {
            if (kind.equals(AbstractDocument.ContentElementName)) {
                result = new MyLabelView(elem);
            } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                result = new ParagraphView(elem);
            } else if (kind.equals(AbstractDocument.SectionElementName)) {
                result = new BoxView(elem, View.Y_AXIS);
            } else if (kind.equals(StyleConstants.ComponentElementName)) {
                result = new ComponentView(elem);
            } else if (kind.equals(StyleConstants.IconElementName)) {
                result = new IconView(elem);
            } else {
                result = new LabelView(elem);
            }
        } else {
            result = super.create(elem);
        }
        return result;
    }
}

class MyLabelView extends LabelView {

    public MyLabelView(Element arg0) {
        super(arg0);
    }

    public void paint(Graphics g, Shape a) {
        System.out.println("paint");
        super.paint(g, a);
        Color c = (Color) getElement().getAttributes().getAttribute(
                "Underline-Color");
        if (c != null) {
            int y = a.getBounds().y + (int) getGlyphPainter().getAscent(this);
            int x1 = a.getBounds().x;
            int x2 = a.getBounds().width + x1;

            g.setColor(c);
            g.drawLine(x1, y, x2, y);
        }
    }
}
