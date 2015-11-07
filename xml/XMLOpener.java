package xml;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class XMLOpener extends FileFilter {

    public File open(boolean readOnly) {
        JFileChooser jFileChooserXML = new JFileChooser();
        jFileChooserXML.setFileFilter(this);
        jFileChooserXML.showOpenDialog(null);
        return jFileChooserXML.getSelectedFile();
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }

        String fileName = file.getName().toLowerCase();
        if(fileName.endsWith(".xml")) {
            return true;
        }

        return false;
    }

    @Override
    public String getDescription() {
        return ".xml";
    }
}
