package project.yugioh.card_print;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.yugioh.card_print.util.SeleniumUtil;

/**
 * @author DrGilbert
 */
@SpringBootApplication
public class CardPrintApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(CardPrintApplication.class, args);
        SeleniumUtil.pre();
    }
}
