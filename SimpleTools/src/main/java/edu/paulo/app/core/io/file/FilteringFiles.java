package edu.paulo.app.core.io.file;

import java.io.File;
import java.io.FileFilter;

public class FilteringFiles implements FileFilter {

    @Override
    public boolean accept(File pathname) {

        if (!pathname.isFile()) {
            return false;
        }else
        {
            if (pathname.getAbsolutePath().endsWith(".txt")) {
                return true;
            }else
            {
                return false;
            }
        }
    }
}
