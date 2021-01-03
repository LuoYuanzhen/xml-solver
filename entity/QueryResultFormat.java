package entity;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.DefaultDocument;

import java.util.*;

public class QueryResultFormat {

    private List<Node> queryResult;
    private String htmlTableString;

    private final String table = "<table border='1'>\n";
    private final String etable = "</table>\n";
    private final String tr = "<tr>\n";
    private final String etr = "</tr>\n";
    private final String th = "<th>";
    private final String eth = "</th>\n";
    private final String td = "<td>";
    private final String etd = "</td>\n";

    public QueryResultFormat(List<Node> queryResult){
        this.queryResult = queryResult;
    }

    private String formatToRow(Element element, boolean isHead){
        String s = td, e = etd;
        if (isHead){
            s = th;
            e = eth;
        }

        String html = "";

        html += tr;
        html += s + (isHead?"ELEMENT":element.getName()) + e;
        for(Attribute attribute: element.attributes()){
            html += s + (isHead?attribute.getName():attribute.getValue()) + e;
        }
        html += etr;

        return html;
    }

    private String recurRoot(List<Element> roots){
        String html = "";

        Queue<List<Element>> elementsDeque = new LinkedList<>();
        elementsDeque.offer(roots);
        while (!elementsDeque.isEmpty()){
            String tableHtml = "";
            List<Element> tableElements = elementsDeque.poll();
            tableHtml += table;
            tableHtml += formatToRow(tableElements.get(0), true);
            for (Element element: tableElements){
                tableHtml += formatToRow(element, false);
                List<Element> childElements = element.elements();
                if (childElements.size() != 0){
                    elementsDeque.offer(childElements);
                }
            }
            tableHtml += etable;
            html += tableHtml;
        }

        return html;
    }

    public String formatToHtmlTableString(){
        List<Element> roots = new LinkedList<>();
        if (this.queryResult == null){
            return "NullPointException";
        }
        try {
            for (Node node : this.queryResult) {
                if ((node instanceof DefaultDocument)){
                    roots.add(((DefaultDocument) node).getRootElement());
                }
                else{
                    roots.add((Element) node);
                }
            }
        }catch (ClassCastException cce){
            return "ClassCastException";
        }
        this.htmlTableString = recurRoot(roots);
        return this.htmlTableString;
    }
}
