package edu.paulo.app.core.io.file;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.util.ConfigProperties;

import java.io.File;

public class DeletingFile {

    private String[] exceptionString = new String[2];
    private ConfigProperties cProp;
    private SimpleToolsDB stdb;
    private File file ;

    public DeletingFile() {
        exceptionString[0] = "DeleteTextFile";
        cProp = new ConfigProperties();
        stdb = new SimpleToolsDB();
    }

    public void deletingFile(String path)
    {
        file = new File(path);
        try {
            if (file.delete()) {
                exceptionString[1] = "deleteOneFile(String path)"+file.getName() + " is deleted!";
            } else {
                exceptionString[1] = "deleteOneFile(String path) ---- Delete file operation is failed.";
            }
        }catch (Exception e)
        {
            exceptionString[1] = "deleteOneFile(String path) -- EXCEPTION LINE  34";
            stdb.exceptionStringz(exceptionString, e, cProp.getfException());
        }
    }

    public void deletingFile(String[] path)
    {
        for(int i=0 ;i<path.length;i++)
        {
            file = new File(path[i]);
            try {
                if (file.delete()) {
                    exceptionString[1] = "deleteOneFile(String path)"+file.getName() + " is deleted!";
                } else {
                    exceptionString[1] = "deleteOneFile(String path) ---- Delete file operation is failed.";
                }
            }catch (Exception e)
            {
                exceptionString[1] = "deleteOneFile(String path) -- EXCEPTION LINE  34";
                stdb.exceptionStringz(exceptionString, e, cProp.getfException());
            }
        }
    }
}