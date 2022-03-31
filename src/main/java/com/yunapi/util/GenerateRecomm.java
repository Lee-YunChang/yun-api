package com.yunapi.util;

import java.util.Random;

public class GenerateRecomm {
	private int recommLength = 6;
//    private final char[] recommTable =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
//                                            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
//                                            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	private final char[] recommTable =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
          'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public String excuteGenerate() {
        Random random = new Random(System.currentTimeMillis());
        int tablelength = recommTable.length;
        StringBuffer buf = new StringBuffer();
        
        for(int i = 0; i < recommLength; i++) {
            buf.append(recommTable[random.nextInt(tablelength)]);
        }
        
        return buf.toString();
    }
    public String excuteGenerateWithUserId(String userId) {
        Random random = new Random(System.currentTimeMillis());
        int tablelength = recommTable.length;
        StringBuffer buf = new StringBuffer();
        char[] chs = userId.toCharArray();
        for(int i = 0; i < recommLength; i++) {
            buf.append(recommTable[random.nextInt(tablelength)]);
            if(chs.length > i)
            	buf.append(chs[i]);
        }
        
        return buf.toString();
    }

    public int getRRcommLength() {
        return recommLength;
    }

    public void setRecommLength(int recommLength) {
        this.recommLength = recommLength;
    }

}
