package handler;

import view.BaseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OpenFileListenerHandle implements ActionListener{

    private BaseView editorView;
    private String type;

    public OpenFileListenerHandle(BaseView editorView, String type){
        this.editorView = editorView;
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = this.editorView.getFilePath(this.type);
        String fileContent = readFile(filePath);

        this.editorView.getFileTextPane(this.type).setText(fileContent);
    }

    private String readFile(String filePath){
        StringBuffer fileContent = new StringBuffer();
        try {
            InputStream is = new FileInputStream(filePath);
            String line = "";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line + "\n");
            }
            bufferedReader.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}
