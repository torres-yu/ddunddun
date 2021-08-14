package io.torres.practice.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogMakerUtil {

    private static final int MAX_DATA_LENGTH = 200;
    private static final Logger developLogger = LoggerFactory.getLogger("DEVELOP_LOGGER");

    /**
     * string 데이터를 정제
     *     jsonObject -> Map<String,Object>
     *     jsonArray -> List<Map<String,Object>
     *     json 파싱이 안될 경우 : 쿼리 스트링(=,&) 파싱을 통해 정제
     * @param jsonStr
     * @return
     */
    public static Object editData(String jsonStr)  {
        try {
            JSONParser parser = new JSONParser();
            if(jsonStr.isEmpty()) return null;
            Object obj = parser.parse(jsonStr);

            if(obj instanceof JSONArray) {
                List<Object> params = new ArrayList<>();
                JSONArray jsonArray = (JSONArray) obj;
                jsonArray.forEach(jsonObj -> {

                    if(jsonObj instanceof JSONObject) {
                        params.add(makeOneMapFromObject((JSONObject)jsonObj));
                    }
                    else {
                        params.add(jsonObj);
                    }

                });
                return params;
            } else if(obj instanceof JSONObject) {
                return makeOneMapFromObject((JSONObject) obj);
            } else {
                return jsonStr;
            }

        }catch (ParseException e) {
            // json 파싱이 안되는 거라면.. url 파싱 시도
            return getJsonFromQuery(jsonStr);
        }

    }

    private static Map<String,Object> makeOneMapFromObject(JSONObject jsonObject){
        Map<String,Object> params = new HashMap<>();

        jsonObject.keySet().forEach(key -> {

            if(jsonObject.get(key) instanceof JSONObject) {
                params.put((String)key,makeOneMapFromObject((JSONObject) jsonObject.get(key)));
            } else if(jsonObject.get(key) instanceof JSONArray){
                List<Map<String,Object>> listParam = new ArrayList<>();
                JSONArray array = (JSONArray) jsonObject.get(key);
                array.forEach(row -> {
                    listParam.add(makeOneMapFromObject((JSONObject) row));
                });
                params.put((String)key,listParam);
            }

            else if(jsonObject.get(key) instanceof String) {
                if (((String) jsonObject.get(key)).length() > MAX_DATA_LENGTH) {
                    jsonObject.put(key, ((String) jsonObject.get(key)).substring(0, MAX_DATA_LENGTH));
                }
                params.put((String)key, jsonObject.get(key));
            }
            else {
                params.put((String)key, jsonObject.get(key));
            }
        });
        return params;
    }


    /**
     * 쿼리 스트링을 map으로 리턴한다.
     * @param query
     * @return
     */
    public static Map<String,Object> getJsonFromQuery(String query){
        Map<String,Object> params = new HashMap<>();
        String[] paramStrs = query.split("&");
        for(String paramsStr : paramStrs) {
            String[] kv = paramsStr.split("=");
            params.put(kv[0],kv[1]);
        }
        return params;
    }

    @Value("${spring.log-api.phase}")
    private static String phase;

}
