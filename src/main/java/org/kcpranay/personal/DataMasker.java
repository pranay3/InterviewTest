package org.kcpranay.personal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataMasker {

    private static final Logger LOGGER = LogManager.getLogger(DataMasker.class);
    public static String maskData(String input, String regex) {
        String output = input;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(output);
        int startPos = 0;
        while(m.find(startPos)){
            int start = m.start();
            int end = m.end();
            LOGGER.info("Found a match at {}. Will mask data", start);
            String first = output.substring(0,start);
            String last = output.substring(end);
            char[] mask = new char[end-start+1];
            Arrays.fill(mask,'#');
            output = first + new String(mask) + last;
            m = p.matcher(output);
            startPos = end;
        }
        return output;
    }

    public static String maskDataSTL(String input, String regex) {
        String output = input;
        return output.replaceAll(regex,"#######");
    }
}
