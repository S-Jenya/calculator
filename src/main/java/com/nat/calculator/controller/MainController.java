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
                              @RequestParam(value = "mainStr", required = false) String mainStr,
                              @RequestParam(value = "paramA", required = false) String paramA,
                              @RequestParam(value = "paramB", required = false) String paramB,
                              @RequestParam(value = "paramC", required = false) String paramC,
                              Model model) {

        ModTrue modTrue = new ModTrue();
        String answer = "";

        if(mode.equals("0") && !paramA.isEmpty()  && !paramB.isEmpty()  && !paramC.isEmpty()) {
            String resultA = modTrue.checkString(paramA, mode);
            String resultB = modTrue.checkString(paramB, mode);
            String resultC = modTrue.checkString(paramC, mode);

            if(!resultA.equals("success")){
                answer = resultA;
            } else if(!resultB.equals("success")) {
                answer = resultB;
            } else if(!resultC.equals("success")) {
                answer = resultC;
            } else {
                SquerCalc squerCalc = new SquerCalc();
                answer = squerCalc.calcSq(paramA, paramB, paramC);
            }
        } else if(mode.equals("1") && !mainStr.isEmpty()) {
            answer = modTrue.checkString(mainStr, mode);
            if(answer.equals("success")) {
                MathCalc calculator = new MathCalc();
                answer = Double.toString(calculator.decide(mainStr));
            }
        }

        model.addAttribute("mainStr", mainStr);
        model.addAttribute("paramA", paramA);
        model.addAttribute("paramB", paramB);
        model.addAttribute("paramC", paramC);
        model.addAttribute("answer", answer);
        return "calc";
    }
}
