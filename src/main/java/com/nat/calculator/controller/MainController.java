package com.nat.calculator.controller;

import com.nat.calculator.MathCalc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping ("/calc")
    public String getStartPage(){
        return "calc";
    }

    @GetMapping ("/calc/{myAnswer}")
    public String getAnswerPage(@PathVariable("myAnswer") Double myAnswer, Model model){
        model.addAttribute("answer", myAnswer);
        return "calc";
    }

    @RequestMapping(value = "/calculate-me", method = RequestMethod.POST)
    public String DoCalculate(@RequestParam("mainStr") String mainStr) {
        MathCalc calculator = new MathCalc();
        Double answer = calculator.decide(mainStr);
        System.out.println(answer);
        return "redirect:calc/"+ answer;
    }
}
