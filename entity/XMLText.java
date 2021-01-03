package entity;

import java.util.ArrayList;
import java.util.List;


public class XMLText {
    private String xmlString;
    private List<Integer> characterNumber = new ArrayList<>();
    private List<Integer> soFarNumber = new ArrayList<>();

    public XMLText(String xmlContent){
        this.xmlString = xmlContent;

        int len = xmlContent.length();
        int totalCount = 0;
        int count = 1; // start with 1, we contain the '\n' in here.
        this.soFarNumber.add(0);
        for (int i = 0; i < len; i++){
            if (xmlContent.charAt(i) == '\n'){
                this.characterNumber.add(count);
                totalCount += count;
                this.soFarNumber.add(totalCount);
                count = 1;
            }
            else {
                count++;
            }
        }
        if(count != 1){ // there is a line without '\n'
            this.characterNumber.add(count-1);
        }
    }

    public String getXmlString() {
        return this.xmlString;
    }

    public List<Integer> getCharacterNumber() {
        return this.characterNumber;
    }

    public List<Integer> getSoFarNumber(){
        return this.soFarNumber;
    }
}
