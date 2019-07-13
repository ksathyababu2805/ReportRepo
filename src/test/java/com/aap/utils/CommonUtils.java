package com.aap.utils;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class CommonUtils {

	public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String digits = "0123456789";
    public static String alphanum = digits;
    private final Random random;
    private final char[] symbols;
    private final char[] buf;
    int charLimit;
    

    public CommonUtils(int length, Random random, String symbols,Boolean isAlphaNumeric) {
    	if (isAlphaNumeric==true) alphanum = alphanum+upper;
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }
    
    public CommonUtils(int length,Boolean isAlphaNumeric) {
        this(length, new SecureRandom(),alphanum,false);
    	this.charLimit = length;
    }
	
	public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
	
	public static void main (String args[]) throws InterruptedException{
		CommonUtils tutil = new CommonUtils(8,false);
		for (int i=0;i<3;i++){
			System.out.println("In Loop "+i+" ::: Randomn String generated is --> "+tutil.nextString());
		}
		
	}
}
 