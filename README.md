Each widget updates itself, MainActivity is also doing updates
---------------
- `MainActivity` allows user to enter a numeric value which is added as extra to an intent sent using `sendBroadcast`
- `OurWidgetProvider.onReceive()` checks for the extra value, and sends it as fourth parameter in `updateWidget()`
- `OurWidgetProvider.updateWidget()` checks if the value is zero, then it comes with a random value; otherwise, it prints what's being given


