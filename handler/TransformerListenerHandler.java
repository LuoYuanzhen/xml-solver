package handler;

import transformer.XSLTransformer;
import view.XSLTView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class TransformerListenerHandler implements ActionListener {
    private XSLTView xsltView;

    public TransformerListenerHandler(XSLTView xsltView){
        this.xsltView = xsltView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String xmlContent = this.xsltView.xmlFileTextPane.getText();
        String xslContent = this.xsltView.xslFileTextPane.getText();
        String htmlPath = this.xsltView.htmlFilePathPane.getText();

        XSLTransformer transformer = new XSLTransformer(xmlContent, xslContent);
        transformer.transform(htmlPath);

        String htmlString = transformer.getOutputHtmlContent();
        this.xsltView.resultPane.setContentType("text/html");
        this.xsltView.resultPane.setText(htmlString);
    }
}
