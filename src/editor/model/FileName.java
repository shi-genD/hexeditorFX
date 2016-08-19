package editor.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by shi on 17.08.16.
 */
public class FileName {

    private String fileName;

    public FileName(String s){fileName = new String(s);}

    public void setFileName(String fileName){this.fileName = new String(fileName);}

    public String getFileName(){return fileName;}


}
