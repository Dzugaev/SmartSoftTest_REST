package com.example.smartsofttest.service;

import java.sql.SQLException;

public class ConverterToString {

    ConnectorJDBC connector ;
    public ConverterToString(){
        connector = new ConnectorJDBC();
    }

    public String readNum(long n) throws SQLException {
        String output = "";
        long forLog = n;
        if(n < 0)
        {
            output+="minus ";
            n *=(-1);
        }

        if ( ((n /100000)/10000000)>0 )  return "Nuber is out of bounds";

        while (n>0) {
            if (n < 21) {
                output+= connector.readName((int)n);
                break;
            }
            if ((n > 20) && (n < 1000)) {     /** меньше тысячи */
                output += getHundreds((int) n);
                n=-1;
            }

            if ((n > 999) && (n < 1000000)) {       /** от тысячи до 999 тысяч  */
                output += getThousands((int)n);
                n = n % 1000;
            }

            if ((n > 999999) && (n < 1000000000)) { /** от миллиона до 999 миллионов  */
                output+= getMillions(n);
                n = n % 1000000;
            }

            if (n > 999999999) {    /** от миллиарда */
                output+= getBillions(n);
                n = n % 1000000000;
            }
        }

        connector.writeLog(2, Long.toString(forLog), output);

        return output;
    }

    private String getBillions(long n) throws SQLException {
        String output ="";
        output+= getHundreds((int)(n/1000000000))+"billion ";
        return output;
    }

    private String getMillions(long n) throws SQLException {
        String output ="";
        output+= getHundreds((int)(n/1000000))+"million ";
        return output;
    }

    private String getThousands(int n) throws SQLException {
        String output ="";
        output+= getHundreds((n/1000))+"thousand ";
        return output;
    }


    private String getHundreds(int n) throws SQLException {
        String output ="";
        int a = n / 100;
        if(a>0) output+= connector.readName(a)+" hundred ";

        a = n%100;
        if (a == 0)  return output; /** если остаток равен нулю -> выход*/

        if(a<21)
        {
            output+= connector.readName(a)+" ";
        }
        else
        {
            int b = (a / 10)*10;
            output+= connector.readName(b)+" ";
            b = a%10;
            if(b==0) return output;                 /** если остаток равен нулю -> выход*/
            else output+= connector.readName(b)+" ";
        }
        return output;
    }
}
