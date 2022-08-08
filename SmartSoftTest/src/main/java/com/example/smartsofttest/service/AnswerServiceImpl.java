package com.example.smartsofttest.service;

import com.example.smartsofttest.model.Answer;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AnswerServiceImpl implements AnswerService{



    @Override
    public Answer StrToNum(String s) {
        Answer output = new Answer();
        ConvertorToInt convertor = new ConvertorToInt();
        String num = "";

        try {
            num = convertor.readStr(s);
        }
        catch (SQLException e )
        {
            num = "SQl exception in method StrToNum AnswerServiceImpl class";
            //e.printStackTrace();
        }

        output.setValue(num);
        return output;
    }

    @Override
    public Answer NumToStr(long n) {
        Answer output = new Answer();
        ConverterToString converter = new ConverterToString();

        String name = "";

        try {
            name = converter.readNum(n);
        } catch (SQLException e) {
            name = "SQl exception in method NumToStr AnswerServiceImpl class";
            //e.printStackTrace();
        }
        output.setValue(name);

        return output;
    }
}
