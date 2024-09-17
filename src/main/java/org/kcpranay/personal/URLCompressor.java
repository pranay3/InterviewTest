package org.kcpranay.personal;

import org.apache.commons.lang3.StringUtils;

public class URLCompressor {

    private int maxMinorCount;
    public URLCompressor(final int maxMinorCount) {
        this.maxMinorCount = maxMinorCount;
    }

    public String compress(String s) {
        if(StringUtils.isBlank(s)) {
            return s;
        }
        StringBuilder compressedString = new StringBuilder();
        String[] majorParts = s.split("/");
        for(String majorPart: majorParts) {
            if(compressedString.length() > 0) {
                compressedString.append("/");
            }
            String compressedMajor = splitAndCompressMajorPart(majorPart);
            compressedString.append(compressedMajor);
        }
        return compressedString.toString();
    }

    private String splitAndCompressMajorPart(final String majorPart) {
        if (StringUtils.isNotBlank(majorPart)) {
            StringBuilder compressedString = new StringBuilder();
            String[] minors = majorPart.split("\\.");
            int minorCount = 0;
            int pos = 0;
            while(minorCount < Math.min(minors.length,maxMinorCount)) {
                String minor = minors[minorCount++];
                if(compressedString.length() > 0) {
                    compressedString.append(".");
                }
                String compressedMinor = compressPart(minor);
                compressedString.append(compressedMinor);
                pos+=minor.length()+1;
            }

            if(minors.length > maxMinorCount) {
                String lastPart = ".";
                lastPart+=majorPart.charAt(pos);
                lastPart+=majorPart.length()-1 -pos -1;
                lastPart+=majorPart.charAt(majorPart.length()-1);
                compressedString.append(lastPart);
            }
            return compressedString.toString();
        }
        return majorPart;
    }

    private String compressPart(final String part) {
        if(part.length() == 1) {
            return part;
        }
        int start = 0, end = part.length()-1;
        return "" + part.charAt(start) + (end-start-1) + part.charAt(end);
    }


}
