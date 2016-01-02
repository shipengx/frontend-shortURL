package com.shipeng.shortenURL.utils;

public class Helper {

	public String idToShortURL(long n) {
        String map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] dict = map.toCharArray();
        StringBuilder shortURL = new StringBuilder();

        //convert the given integer id to a base 62 number
        while (n > 0) {
            //use above dict to store actual character in short url.
            shortURL.insert(0,dict[(int)n%62]);
            n = n/62;
        }
        return shortURL.toString();
    }

    public long shortURLToId(String shortURL) {

        long id = 0;  //initialize our result
        //a simple base conversion logic
        for (int i = 0; i < shortURL.length(); i++) {
            char curr = shortURL.charAt(i);
            if ('a' <= curr && curr <= 'z') {
                id = id*62 + curr - 'a';
            }
            if ('A' <= curr && curr <= 'Z') {
                id = id*62 + curr - 'A' + 26;
            }
            if ('0' <= curr && curr <= '9') {
                id = id*62 + curr - '0' + 52;
            }
        }
        return id;
    }//end long
	
}
