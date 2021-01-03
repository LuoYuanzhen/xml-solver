package test;

import entity.XMLText;

public class TestXMLText {
    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE university SYSTEM \"src/main/resources/university.dtd\">\n" +
                "\n" +
                "<university name=\"China_University_of_Petromleum\" location=\"Beijing\">\n" +
                "    <college name=\"CISE\" dean=\"WeifengLiu\">\n" +
                "        <profession name=\"CS\" departmentHead=\"LianenJi\" rating=\"B-\">\n" +
                "            <faculty>\n" +
                "                <professor sex=\"male\" phone=\"188xxxxxxxx\"></professor>\n" +
                "                <professor name=\"LianenJi\" sex=\"male\"></professor>\n" +
                "                <lecturer name=\"JiangboFan\" sex=\"female\"></lecturer>\n" +
                "            </faculty>\n" +
                "            <student  name=\"YuanzhenLuo\" sex=\"male\" phone=\"138xxxx7813\"/>\n" +
                "        </profession>\n" +
                "    </college>\n" +
                "    <building name=\"library\">\n" +
                "        <room id=\"303\"></room>\n" +
                "    </building>\n" +
                "</university>";
        XMLText xmlText = new XMLText(xml);
        System.out.println(xmlText.getCharacterNumber());
        System.out.println(xmlText.getSoFarNumber());
    }
}
