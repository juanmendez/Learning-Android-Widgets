Widgets with listViews updated by MainActivity
---------------------

- Dagger2 helps with providing dependencies to the widget and the activity
- Realm is used to store items
- Realm requires to use its data in the main thread, but cloning realmModels works for other threads.
- Changes happening from Activity update listViews in widgets
- Rebooting device shows the last state from the app's realm

![Activity form](/readme/activity.png)
![Widget](/readme/widget.png)