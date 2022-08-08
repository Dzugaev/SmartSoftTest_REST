package com.example.smartsofttest.service;

import com.example.smartsofttest.model.Answer;

public interface AnswerService {

    /**
     * Возвращает число написанное цифрами
     * @return string c числом
     */
    Answer StrToNum(String s);

    /**
     * Возвращает число написанное буквами
     * @return string c названием
     */
    Answer NumToStr(long n);
}
