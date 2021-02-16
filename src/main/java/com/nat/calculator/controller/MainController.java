package com.nat.calculator.controller;

import com.nat.calculator.MathCalc;
import com.nat.calculator.ModTrue;
import com.nat.calculator.SquerCalc;
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
    public String DoCalculate(@RequestParam("mode") String mode,
                              @RequestParam("mainStr") String mainStr,
                              @RequestParam("paramA") String paramA,
                              @RequestParam("paramB") String paramB,
                              @RequestParam("paramC") String paramC,
                              Model model) {

        ModTrue modTrue = new ModTrue();
        String answer = "";

        if(mode.equals("1") && !paramA.isEmpty()  && !paramB.isEmpty()  && !paramC.isEmpty()) {
            SquerCalc squerCalc = new SquerCalc();
            answer = squerCalc.calcSq(paramA, paramB, paramC);
        } else if(mode.equals("0") && !mainStr.isEmpty()) {
            answer = modTrue.checkString(mainStr);
            if(answer.equals("success")) {
                MathCalc calculator = new MathCalc();
                answer = Double.toString(calculator.decide(mainStr));
            }

        }

        model.addAttribute("mainStr", mainStr);
        model.addAttribute("answer", answer);
        return "calc";
    }
}
