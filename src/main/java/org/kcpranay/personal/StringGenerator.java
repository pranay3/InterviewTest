package org.kcpranay.personal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringGenerator {

    public List<String> generateStrings(String input) {
        if(!input.contains("{") || !input.contains("}")) {
            return Collections.singletonList(input);
        }
        List<String> finalStrings = new ArrayList<>();

        int startBracePos = input.indexOf('{');
        int endBracePos = input.indexOf('}');

        if(startBracePos>endBracePos || startBracePos+1==endBracePos) {
            return Collections.singletonList(input);
        }
        String prefix = "";
        String options = "";
        String suffix = "";
        if(startBracePos>0) {
            prefix = input.substring(0, startBracePos);
        }

        if(input.indexOf('}')<input.length()-1) {
            suffix = input.substring(endBracePos+1);
        }

        options = input.substring(startBracePos+1, endBracePos);

        for(String option: options.split(",")) {
            for(String suffixIt: generateStrings(suffix)) {
                StringBuilder builder = new StringBuilder();
                builder.append(prefix);
                builder.append(option);
                builder.append(suffixIt);
                finalStrings.add(builder.toString());
            }
        }
        return finalStrings;
    }
}
