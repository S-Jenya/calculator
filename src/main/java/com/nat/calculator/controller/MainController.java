package com.nat.calculator.controller;

import com.nat.calculator.MathCalc;
import com.nat.calculator.ModTrue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping ("/calc")
    public String getStartPage(){
        return "calc";
    }

    @GetMapping ("/calc/{mainStr}/{myAnswer}")
    public String getAnswerPage(@PathVariable("mainStr") String mainStr, @PathVariable("myAnswer") Double myAnswer, Model model){
        model.addAttribute("mainStr", mainStr);
        model.addAttribute("answer", myAnswer);
        return "calc";
    }

    @RequestMapping(value = "/calculate-me", method = RequestMethod.POST)
    public String DoCalculate(@RequestParam("mainStr") String mainStr, Model model) {
        ModTrue modTrue = new ModTrue();
        String answer = modTrue.checkString(mainStr);
        if(answer.equals("success")) {
            MathCalc calculator = new MathCalc();
            answer = Double.toString(calculator.decide(mainStr));
        }

        System.out.println(answer);
        model.addAttribute("mainStr", mainStr);
        model.addAttribute("answer", answer);
        return "calc";
    }
}
