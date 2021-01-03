package handler;

import queryer.XPathQueryer;
import view.XPathView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryListenerHanlder implements ActionListener {

    private XPathView queryView;

    public QueryListenerHanlder(XPathView queryView){
        this.queryView = queryView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String xmlContent = this.queryView.xmlWindow.getText();
        String ql = this.queryView.queryText.getText();

        XPathQueryer queryer = new XPathQueryer(xmlContent);
        queryer.query(ql);

        //String report = queryer.getReport();
        String report = queryer.getHTMLReport();
        this.queryView.resultText.setContentType("text/html");
        this.queryView.resultText.setText(report);
    }
}
