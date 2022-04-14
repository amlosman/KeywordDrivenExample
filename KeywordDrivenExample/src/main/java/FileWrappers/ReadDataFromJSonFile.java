package FileWrappers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.json.JsonException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadDataFromJSonFile {
    public  static  String jsonPathfile = "DataSet.json";
    JSONObject jsonObject;
   public ReadDataFromJSonFile(String file)
   {
       parseJson(file);
   }

   public void parseJson(String file) {
       String jsonData = readFile(file);
       try {
           jsonObject = new JSONObject(jsonData);
       }
       catch (Exception ex)
       {
           LoggingHandling.logError(ex);       }
   }
   public String readFile(String file) {
       String result="";
       try {
           BufferedReader bufferedReader = new BufferedReader( new FileReader(file));
           StringBuilder stringBuilder = new StringBuilder();
           String Line = bufferedReader.readLine();

           while (Line!=null)
           {
               stringBuilder.append(Line);
               Line= bufferedReader.readLine();
           }
           result = stringBuilder.toString();
           return result;

       } catch (IOException e) {
           LoggingHandling.logError(e);
       }
       return result;
   }
   public String getValueOfNode(String parent){

       String [] tree= parent.split("/");
        if(tree.length==1)
        {
            return jsonObject.getString(tree[0]);
        }
        int i = 1;
        JSONObject parentNode;
        parentNode = (JSONObject) jsonObject.get(tree[0]);
        while (i < tree.length-1){
            parentNode = (JSONObject)parentNode.get(tree[i]);
            i++;
        }

        return parentNode.getString(tree[i]);
    }
    public List<String> getValuesOf(String parent, String child){
        JSONArray value = null;
        boolean isArray = false;
        String singleValue = null;
        Object obj ;
        if(child == null){
            try{
                obj = jsonObject.get(parent);
                if(obj instanceof JSONArray){
                    value = new JSONArray(jsonObject.getJSONArray(parent).toString());
                    isArray = true;
                }
                else {
                    singleValue = jsonObject.getString(parent);
                }
            }catch (JsonException e){
                LoggingHandling.logError(e);
            }

        }else {
            JSONObject parentNode;
            try {
                parentNode = (JSONObject) jsonObject.get(parent);
                obj = parentNode.get(child);
                if (obj instanceof JSONArray){
                    value = new JSONArray(parentNode.getJSONArray(child).toString());
                    isArray = true;
                }
                else {
                    singleValue = parentNode.getString(child);
                }

            }catch (JsonException e){
                LoggingHandling.logError(e);
                return null;
            }
        }
        List<String> list = new ArrayList<>();
        if (isArray){
            for (int i = 0; i < value.length(); i++){
                try {
                    list.add(value.getString(i));
                }catch (JsonException e){
                    LoggingHandling.logError(e);
                    return null;
                }
            }
        }
        else {
            if(singleValue != null){
                list.add(singleValue);
            }
        }
        return list;

    }
}
