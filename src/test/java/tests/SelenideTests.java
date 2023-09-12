package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.Listeners;

import org.testng.annotations.Test;
import testbuilders.SelenideTestBuilder;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


@Listeners({ SoftAsserts.class})
public class SelenideTests extends SelenideTestBuilder {

    @Test
    public void checkBoxTest(){
//        Navigate to the desired relative path
        open("/checkboxes");

//        Locate all checkboxes for further operations
        ElementsCollection checkboxes = $("#checkboxes").$$("input");
//        Select the first checkbox
        checkboxes.get(0).setSelected(true);

//        Check if all the checkboxes have type checkbox
//        We can use this forEach,but it is deprecated, so I used standard java for-each loop.
//        checkboxes.forEach(checkbox -> checkbox.shouldHave(attribute("type", "checkbox")));
        for (SelenideElement checkbox: checkboxes) {
            checkbox.shouldHave(attribute("type", "checkbox"));
        }

//        Result
        boolean isTheFirstCheckboxSelected = checkboxes.get(0).isSelected();
        System.out.printf("""
                Result of checkBoxTest:\s
                 Is the first checkbox selected? > %b;\s
                 Both checkboxes have type 'checkbox'.
                """, isTheFirstCheckboxSelected);

        //        If you run this test you will see:

        //    Result of checkBoxTest:
        //    Is the first checkbox selected? > true;
        //    Both checkboxes have type 'checkbox'.
    }

    @Test
    public void dropDownTest(){
//        Navigate to the desired relative path
        open("/dropdown");

//        Locate dropdown and selected option by default
        SelenideElement dropdown = $("#dropdown");
        SelenideElement defaultSelectedOption = dropdown.getSelectedOption().shouldHave(exactText("Please select an option"));
        String defaultSelectedOptionText = dropdown.getSelectedOptionText();

//        Select 'Option 2' and validate this selection
        dropdown.selectOptionByValue("2");
        SelenideElement currentSelectedOption = dropdown.getSelectedOption().shouldHave(text("Option 2"));
        String currentSelectedOptionText = dropdown.getSelectedOptionText();

//        Result
        System.out.printf("""
                Result of dropDownTest:\s
                 Default selected option text: %s;\s
                 Selected option text after user selection: %s.\s
                """, defaultSelectedOptionText, currentSelectedOptionText);

        //        If you run this test you will see:

        //    Result of dropDownTest:
        //    Default selected option text: Please select an option;
        //    Selected option text after user selection: Option 2.
    }

    @Test
    public void formTest(){
//        Navigate to the desired absolute path
        open("https://demoqa.com/text-box");

//        Select form elements with different selectors
        SelenideElement inputFullName = $("#userName");
        SelenideElement inputEmail = $$("input").findBy(attribute("type", "email"));
        SelenideElement inputCurrentAddress = $$("textarea").findBy(attribute("placeholder", "Current Address"));
        SelenideElement inputPermanentAddress = $("textarea", 1);
        SelenideElement submitButton = $(byText("Submit"));

//        Create values for form
        String fullName = "Bacho Skhiladze";
        String email = "Bacho@gmail.com";
        String currentAddress = "Earth";
        String permanentAddress = "Mars";

//        Fill the form with them and submit
        inputFullName.setValue(fullName);
        inputEmail.setValue(email);
        inputCurrentAddress.setValue(currentAddress);
        inputPermanentAddress.setValue(permanentAddress);

//        On small screens like laptops submit button is not visible, so we have to scroll to it.
        submitButton.scrollTo().click();

//        Result (input values after filling the form)
        String finalFullName = inputFullName.getValue();
        String finalEmail = inputEmail.getValue();
        String finalCurrentAddress = inputCurrentAddress.getValue();
        String finalPermanentAddress = inputPermanentAddress.getValue();

        System.out.printf("""
                Result of formTest:
                 Full Name: %s;\s
                 Email: %s;\s
                 Current Address: %s;\s
                 Permanent Address: %s.\s
                """, finalFullName, finalEmail, finalCurrentAddress, finalPermanentAddress);

//        Validate output
        ElementsCollection output = $("#output").$$("p");
        output.shouldHave(exactTexts("Name:" + fullName, "Email:"+email, "Current Address :"+currentAddress,
                "Permananet Address :"+permanentAddress));

        //        If you run this test you will see:

        //    Result of formTest:
        //    Full Name: Bacho Skhiladze;
        //    Email: Bacho@gmail.com;
        //    Current Address: Earth;
        //    Permanent Address: Mars.
    }


}
