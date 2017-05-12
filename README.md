Widgets with listViews updated by MainActivity
---------------------

- Dagger2 helps with providing dependencies to the widget and the activity
- Realm is used to store items
- RealmResults are switch to RealmLists, and clones each realmModel to solve thread issues.
- Changes happening from Activity update listViews in widgets
- Rebooting device shows the last state from Realm

![Activity form](/readme/activity.png)
![Widget](/readme/widget.png)