package com.outfit7.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.junit.jupiter.api.Assertions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InfoTest extends Base {

    @Test
    void clickOnInfoButtonTest() {
        driver.findElement(By.id("com.outfit7.talkingtom:id/buttonInfo")).click();
        driver.findElement(By.id("com.outfit7.talkingtom:id/infoWebButtonHowToPlay")).click();

        Assertions.assertTrue(driver.getPageSource().contains("Talk to Tom and he repeats with a funny voice"), "There is no given text.");
    }
}
