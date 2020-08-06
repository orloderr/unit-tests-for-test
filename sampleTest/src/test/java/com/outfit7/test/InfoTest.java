package com.outfit7.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.junit.jupiter.api.Assertions;

/**
 * Class contains tests related to "Info" button.
 */
class InfoTest extends DefaultTestEnvironment {

    /**
     * Clicks on "Info" button, then on "How To Play" button and checks presence of a given text.
     */
    @Test
    void checkHowToPlayTextInfo() {
        driver.findElement(By.id("com.outfit7.talkingtom:id/buttonInfo")).click();
        driver.findElement(By.id("com.outfit7.talkingtom:id/infoWebButtonHowToPlay")).click();

        Assertions.assertTrue(driver.getPageSource().contains("Talk to Tom and he repeats with a funny voice"),
                "There is no given text.");
    }
}
