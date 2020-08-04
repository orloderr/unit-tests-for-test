package com.outfit7.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodTest extends BaseTest {

    @Test
    void clickOnFoodButton() {
        driver.findElement(By.id("com.outfit7.talkingtom:id/foodButton")).click();
    }
}
