package com.example.admin.roll3dview;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String s = "userId=10,token=null,stage=null,client=2";

        Map<String, String> map = f(s);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    Map<String,String> f(String str){
        Map<String, String> map = new HashMap<>();
        String []strs = str.split(",");
        for (int i = 0; i < strs.length; i++) {
            String data[] = strs[i].split("=");
            if(data[1].equals("null")){
                map.put(data[0], "aaa");
            }else {
                map.put(data[0], data[1]);
            }
        }
        return map;
    }
}