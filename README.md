Android Widgets
---------------

This demo makes all widgets update to the same value

What you need to know
- Widgets are hosted by a widgetProvider whose parent is BroadcastReceiver
    - Therefore you can update widgets from other Android components like you already do with BroadcastReceiver
- From Android version 14 there is padding added to widgets.
    - Add padding to previous version by creating a resource dimension which targets before v-14, and after
- Have fun, first widget updates all other instances upon a button click.

