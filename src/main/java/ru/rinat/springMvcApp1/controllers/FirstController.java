package ru.rinat.springMvcApp1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
        model.addAttribute("message", "Hello " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculatorPage(@RequestParam(value = "a") int a,
                                 @RequestParam(value = "b") int b,
                                 @RequestParam(value = "action") String action,
                                 Model model) {
        int result = 0;
        switch (action) {
            case "addition" -> result = a + b;
            case "subtraction" -> result = a - b;
            case "multiplication" -> result = a * b;
            case "division" -> {
                if (b == 0) {
                    model.addAttribute("result", "Division by zero is not allowed");
                    return "first/calculator";
                }
                result = a / b;
            }
        }
        model.addAttribute("result", "The result is " + result);
        return "first/calculator";
    }
}
