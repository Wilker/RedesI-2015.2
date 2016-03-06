/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.DosFileAttributes;

/**
 *
 * @author wilkerrj
 */
public class Filter implements FileFilter {

    @Override
    public boolean accept(File dir) {
        Path path = Paths.get(dir.getAbsolutePath());
        DosFileAttributes dfa;
        try {
            dfa = Files.readAttributes(path, DosFileAttributes.class);
        } catch (IOException e) {
            // bad practice
            return false;
        }
        return (!dfa.isHidden() && !dfa.isSystem());
    }
}
