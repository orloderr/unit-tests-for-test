# unit-tests-for-test
## General information
This test project consists of four test classes:
* feeding Tom (`FoodTest`);
* reading text from "How To Play" instructions (`InfoTest`);
* custom test for touchscreen interactions with Tom (`TouchTest`);
* test class is a combination of three previous classes (`UserInteractionTest`).

Reinstalling the APK before each test class running is quite a time-consuming process. That is why there is the fourth test class (`UserInteractionTest`).

Though at the same it is a bad practice to have tests depending on a result of each other (for example, the "Info" page needs to be closed before the next test, or the feeding animation must clearly end).

## Custom test scenario

### Problem
Tom needs to react with certain animation to a swipe movement and with another to a tap on a screen.

### Verification
1. Prepare the application's main scene.
2. Make a swipe movement in the middle of a screen.
3. Verify that Tom has a "happy cat face" reaction to the swipe.
4. Tap in the middle of a screen.
5. Verify that Tom reacts with knocked animation.  





