package de.livia.rechnerservice.controller;

import de.livia.rechnerservice.services.AddiererService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RechnerAppController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    AddiererService addiererService;

    /**
     * Calls the form page rechne.html
     * @return rechne.html
     */
    @RequestMapping(value = "/rechne", method = RequestMethod.GET)
    public String rechne(Model model) {
        model.addAttribute("result", 0.00);
        model.addAttribute("summand1", 0.00);
        model.addAttribute("summand2", 0.00);
        return "rechne";
    }

    /**
     * Invokes the remote REST service Addierer, calculates the result and retrieves it to result page
     * @return
     */
    @RequestMapping(value = "/rechneErgebnis", method = RequestMethod.POST)
    public String rechneErgebnis(@RequestParam(name = "summand1") double summand1,
                                 @RequestParam(name = "summand2") double summand2, Model model) {
        logger.debug("Summand 1: " + summand1);
        logger.debug("Summand 2: " + summand2);
        model.addAttribute("summand1", summand1);
        model.addAttribute("summand2", summand2);
        model.addAttribute("result", addiererService.calculateTotal(summand1, summand2));
        return "rechne";
    }
}
