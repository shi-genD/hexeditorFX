package editor.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by shi on 17.08.16.
 */
public class TextIn {

    private final StringProperty text;

    public TextIn() {
        this.text = new SimpleStringProperty("");
    }

    public TextIn(String text) {
        this.text = new SimpleStringProperty(text);
    }

    public String getText() {return text.get();}

    public void setText(String text) {this.text.set(text);}

    public StringProperty textProperty() {return text;}
}
