import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EnterpriseTest {


    @BeforeAll
    static void setUp() {
        String browser = System.getProperty("browser", "firefox");
        Configuration.browser = browser;
        Configuration.browserSize = "1920x1080";


        switch (browser) {
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            //case "edge"    -> WebDriverManager.edgedriver().setup();
            //case "opera"   -> WebDriverManager.operadriver().setup();
            //default        -> WebDriverManager.chromedriver().setup();
        }
    }

@Test
    void shouldFindEnterprise(){
    open("https://github.com/");
    $(".HeaderMenu-nav").$(byText("Solutions")).hover();
    $(byText("Enterprises")).click();
    $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));
}

}
