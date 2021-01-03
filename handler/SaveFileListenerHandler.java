package handler;

import view.BaseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveFileListenerHandler implements ActionListener {
    private BaseView editorView;
    private String type;

    public SaveFileListenerHandler(BaseView editorView, String type){
        this.editorView = editorView;
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = this.editorView.getFilePath(this.type);
        String fileContent = this.editorView.getFileTextPane(this.type).getText();

        this.saveFile(filePath, fileContent);
    }

    private void saveFile(String filePath, String fileContent){
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileContent.getBytes());
            fos.close();
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
}
