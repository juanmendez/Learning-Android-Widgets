Each widget updates itself
---------------
- Making sure we provide OurWidgetProvider.onReceive
- Widget button has an intent having `EXTRA_APPWIDGET_ID` as extra
- PendingIntent has `widgetId` instead of just zero

