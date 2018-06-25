package com.mc.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {
    @Autowired
    MovieRepository movies;

    @RequestMapping("/")
    public String showHomepage(Model model){
        model.addAttribute("movie", new Movie());
        return "index";
    }

    @PostMapping("/savemovie")
    public String saveList(@ModelAttribute("movie") Movie movie){
        movies.save(movie);
        return "redirect:/showTitles";
    }

    @RequestMapping("/showTitles")
    public String showTitles(Model model){
        model.addAttribute("movies", movies.findAll());
        return "show";
    }
    @RequestMapping("/showDetails/{id}")
    public String showList(@PathVariable("id") long id, Model model){
        model.addAttribute("movie", movies.findById(id).get());
        return "showDetails";
    }

}
