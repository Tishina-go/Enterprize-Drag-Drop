import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDrop {

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
    void ActionsMoveclass(){

        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        //Перенос прямоугольника А на место В помощью Selenide.actions()
        actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
        //Проверка, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

    @Test
    void DragDropClass(){
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        //Перенос прямоугольника А на место В помощью команды $(element).dragAndDrop($(to-element))
        $("#column-a").dragAndDropTo($("#column-b"));
        //$("#column-a").dragAndDrop(DragAndDropOptions.to("#column-b"));  => 2й вариант
        //Проверка, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}
