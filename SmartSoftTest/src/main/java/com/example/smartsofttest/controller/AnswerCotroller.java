package com.example.smartsofttest.controller;

import com.example.smartsofttest.model.Answer;
import com.example.smartsofttest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerCotroller {

    public final AnswerService answerService;

    @Autowired
    public AnswerCotroller(AnswerService answerService) {
        this.answerService = answerService;
    }


    @GetMapping(value = "/NumberToString/{num}")
    public ResponseEntity<String> readInt (@PathVariable(name = "num") String snum) {

        long num;      /** Убрать отсюда, изменить сигнатуру метода*/
        try {
            num = Long.parseLong(snum);
        }
        catch (Exception e )
        {
            return new ResponseEntity<>("Input is not a number", HttpStatus.OK);
        }

        final Answer answer = answerService.NumToStr(num);

        return answer != null
                ? new ResponseEntity<>(answer.getValue(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/StringToNumber/{name}")
    public ResponseEntity<String> readStr (@PathVariable(name = "name") String name) {

        final Answer answer = answerService.StrToNum(name);

        return answer != null
                ? new ResponseEntity<>(answer.getValue(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /* @GetMapping(value = "/StringToNumber/{name}")
    public ResponseEntity<Answer> readStr (@PathVariable(name = "name") String name) {

        final Answer answer = answerService.StrToNum(name);

        return answer != null
                ? new ResponseEntity<>(answer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    */

}
