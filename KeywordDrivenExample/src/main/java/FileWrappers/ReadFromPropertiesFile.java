package FileWrappers;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public  class ReadFromPropertiesFile {
    static String fileNamePath = "DocomentElements.properties";
    public static Properties  properties = new Properties();

    static {
        FileInputStream fs =null ;
        try {
            fs = new FileInputStream(fileNamePath);
            properties.load(fs);
        } catch (FileNotFoundException e) {
            LoggingHandling.logError(e);
        }
        catch (IOException e) {
            LoggingHandling.logError(e);

        }
        finally {
            try {
                fs.close();

            } catch (IOException e) {
                LoggingHandling.logError(e);
            }

        }

    }
    public static String getValue(String  Key)
    {
        try {
            return properties.getProperty(Key);
        }
        catch (Exception ex)
        {
            LoggingHandling.logError(ex);
            return  null;
        }
    }
}
