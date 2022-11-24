package tests.com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    public static void setup() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successfulSubmit() {

        //Arrange
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        //Act
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("qwe123@gmail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("2000");
        $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", "10")).click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/mem.jpeg"));
        $("#currentAddress").setValue("100, Lenina street");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Panipat").pressEnter();
        $("#submit").click();

        //Assertion
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']").parent().shouldHave(text("Ivan Ivanov"));
        $x("//td[text()='Student Email']").parent().shouldHave(text("qwe123@gmail.com"));
        $x("//td[text()='Gender']").parent().shouldHave(text("Male"));
        $x("//td[text()='Mobile']").parent().shouldHave(text("1234567890"));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text("10 January,2000"));
        $x("//td[text()='Subjects']").parent().shouldHave(text("Maths, Chemistry"));
        $x("//td[text()='Hobbies']").parent().shouldHave(text("Sports, Music"));
        $x("//td[text()='Picture']").parent().shouldHave(text("mem.jpeg"));
        $x("//td[text()='Address']").parent().shouldHave(text("100, Lenina street"));
        $x("//td[text()='State and City']").parent().shouldHave(text("Haryana Panipat"));
    }
}