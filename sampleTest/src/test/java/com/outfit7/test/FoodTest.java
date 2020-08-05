package com.outfit7.test;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodTest extends Base {

    @Test
    void feedCakeMakeScreenshotTest() throws IOException {
        WebDriverWait waitForApp = new WebDriverWait(driver, 1);

        Assertions.assertFalse(driver.getPageSource().contains("Cake"), "There is an unexpected cake scene!");

        driver.findElement(By.id("com.outfit7.talkingtom:id/foodButton")).click();
        waitForApp.until(ExpectedConditions.elementToBeClickable(By.id("com.outfit7.talkingtom:id/foodItemCake")));
        driver.findElement(By.id("com.outfit7.talkingtom:id/foodItemCake")).click();

        Assertions.assertTrue(driver.getPageSource().contains("Cake"), "There is no cake scene at all!");

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,
                new File(String.format("%s\\%s", System.getProperty("user.dir"), "eatingTom.png")));
    }
}
