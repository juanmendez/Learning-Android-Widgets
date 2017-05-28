Making a widget update the database and change is reflected on it
---------------------

- This branch shows a widget having four buttons you select either one of them and you are updating the database. Then you can reboot your device and see the last button selected.
- We use a regular Service to update the database. The widget sets a pending intent with each button's information which is then used by the Service and updates the database and then broadcast to the widgets there has been an update.
- The database has only one record which is set to be updated upon button selected. The record stores the button viewId.
- This is extra, I found [Mike Penz' iconic library]()https://github.com/mikepenz/Android-Iconics) very helpfull to use iconic fonts for each button.