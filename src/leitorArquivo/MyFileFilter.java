
package leitorArquivo;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getAbsolutePath().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Text documents (*.txt)";
    }
    
}
