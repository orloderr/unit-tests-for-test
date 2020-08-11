# unit-tests-for-test
## General information
This test project consists of two classes:
* `DefaultTestEnvironment` class preparing a test environment (loads the game into the main scene);
* `UserInteractionTest` class with three different tests covering some user interactions with the game: feeding, checking "Info" page and video recording tool.

## Custom test scenario
The custom test is covered in `recordVideoCheckSharingWindowAndCloseTest` test method.

### Requirement
When a user pushed the record video button two times, a menu with play button and sharing options must open.

### Verification
1. Prepare the application's main scene.
2. Press a video recording button.
2. Press a video recording button again.
5. Verify that a menu with sharing options and play button appeared on the screen.  





