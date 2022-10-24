package edu.paulo.app.core.io.file;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.util.ConfigProperties;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.security.PermissionCollection;

public class WriteTextFile {

    private String[] exceptionString = new String[2];
    private ConfigProperties cProp;
    private SimpleToolsDB stdb;
    private boolean isWrite = true;
    private FilePermission fPerm ;
    private FileWriter fWriter;
    private PermissionCollection permission;
    private StringBuilder sBuild ;
    private String strFile;
    private File createDir;

    public WriteTextFile() {
        exceptionString[0] = "WriteTextFile";
        cProp = new ConfigProperties();
        stdb = new SimpleToolsDB();
        sBuild = new StringBuilder();
    }

    public boolean writeToTextFile(String path,String fName,String content)
    {
        sBuild.setLength(0);
        strFile = sBuild.append(path).append("\\").append(fName).toString();
        createDir = new File(path);

        fPerm = new FilePermission(path, "read");
        permission = fPerm.newPermissionCollection();
        permission.add(fPerm);

        fPerm = new FilePermission(path+"\\"+fName, "write");
        permission.add(fPerm);

        try{
            if(fName.endsWith("txt"))
            {
                if(permission.implies(fPerm))
                {
                    if(!createDir.exists())
                    {
                        isWrite = createDir.mkdir();
                    }
                    if(isWrite)
                    {
                        fWriter = new FileWriter(new File(strFile));
                        fWriter.write(content);
                    }else{
                        exceptionString[1] = "writeToTextFile(String path,String fName,String content) -- EXCEPTION LINE  58";
                        stdb.exceptionStringz(exceptionString,new AccessDeniedException("MAKE DIRECTORY FAILED in path "+path), cProp.getfException());
                    }
                }else
                {
                    exceptionString[1] = "writeToTextFile(String path,String fName,String content) -- EXCEPTION LINE  33";
                    stdb.exceptionStringz(exceptionString,new AccessDeniedException("ACCESS DENIED"), cProp.getfException());
                    isWrite = false;
                }
            }
            else
            {
                exceptionString[1] = "writeToTextFile(String path,String fName,String content) -- EXCEPTION LINE  49";
                stdb.exceptionStringz(exceptionString,new FileNotFoundException("FILE TYPE NOT MATCH 'REQUIREMENT .txt EXTENSION FILE ' "), cProp.getfException());
                isWrite = false;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            exceptionString[1] = "setContentFile(String pathFile) -- EXCEPTION LINE  28";
            stdb.exceptionStringz(exceptionString, e, cProp.getfException());
            isWrite = false;
        }finally {
            try {
                fWriter.flush();
                fWriter.close();
            } catch (Exception e) {
                exceptionString[1] = "setContentFile(String pathFile) -- EXCEPTION LINE  73";
                stdb.exceptionStringz(exceptionString, e, cProp.getfException());
            }
            return isWrite;
        }
    }
}