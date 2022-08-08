package com.example.smartsofttest.service;

import java.sql.SQLException;

public class ConvertorToInt {


    private ConnectorJDBC connector;

    public ConvertorToInt(){
        connector = new ConnectorJDBC();
    }

    public String readStr (String s) throws SQLException {

        long sum = 0;
        String[] words = s.split(" ");
        boolean minus = false;

        for (String word : words) {
            if (word.equals("minus"))
            {
                minus = true;
                continue;
            }

            if ((word.equals("hundred")) || (word.equals("thousand")) ||
                    (word.equals("million")) || (word.equals("billion")) )
            {
                try {
                    sum*= Long.parseLong(connector.readNumber(word));
                }
                catch (Exception e)
                {
                    return ("There may be an error in writing the numbers");
                }
            }
            else
            {
                try {
                    sum+= Long.parseLong(connector.readNumber(word));
                }
                catch (Exception e)
                {
                    return ("There may be an error in writing the numbers");
                }
            }

            //System.out.println(word);
        }

        if(minus) sum*= -1;

        connector.writeLog(1, s, Long.toString(sum));
        return Long.toString(sum);
    }
}
