package br.com.wine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Controller
@SpringBootApplication
public class WineApplication {

    @RequestMapping("/")
    public String home() {
        return "redirect:/vinhos";
    }

    public static void main(String[] args) {
        SpringApplication.run(WineApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }
}
