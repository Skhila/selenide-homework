package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testbuilders.SelenideTestBuilder;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Listeners({SoftAsserts.class})
public class SelenideTestsSecond extends SelenideTestBuilder {

//    This test finds all books published by O'Reilly Media and containing 'javascript' in title,
//    Then clicks titles of all of them.
    @Test
    public void firstBooksTest(){
        open("https://demoqa.com/books");

//        Find column Headers and row records
        ElementsCollection columnHeadersCollection = $("div.rt-thead").findAll("[role='columnheader']");
        List<String> columnHeaders = columnHeadersCollection.texts();

        ElementsCollection booksCollection = $("div.rt-table").find("div.rt-tbody").findAll("[role='row']")
                .filterBy(and("Only books with exact publisher and 'javascript' in title",text("O'Reilly Media"), text("javascript")));

//        Some soft assertions
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(booksCollection.size(), 10, "Unequal sizes!!!");

        String firstMatchedRowTitle = booksCollection.first().find("div", columnHeaders.indexOf("Title")).getText();

        softAssert.assertEquals(firstMatchedRowTitle, "Learning JavaScript Design Patterns");
        System.out.println("Success case!");

//        Click on all books titles
        booksCollection.asDynamicIterable().stream().forEach(el -> {
            el.$(".rt-td:nth-child("+ (columnHeaders.indexOf("Title") + 1) +") a").scrollTo().click();
            back();
        });

        softAssert.assertAll();
    }

//    This test checks if all the chosen books have valid images
    @Test
    public void secondBooksTest(){
        open("https://demoqa.com/books");

        ElementsCollection booksCollection = $("div.ReactTable").find("div.rt-table").find("div.rt-tbody").findAll("[role='row']")
                .filterBy(and("Only books with exact publisher and 'javascript' in title",text("O'Reilly Media"), text("javascript")));

        for (SelenideElement book : booksCollection) {
            book.find("div.rt-td").find("img")
                    .shouldHave(and("'src' attribute exist and is not empty", attribute("src"), not(attribute("src", ""))));
            System.out.println("Success case!");
        }
    }
}
