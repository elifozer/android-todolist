# ToDO List: Recycler View and Database Operations
In this app, I created a simple to-do list application where user creates
desired to-do lists with desired items and modify them when needed.

In my project, in order to implement database operations I used SQLiteOpenHelper.
I also implemented custom RecyclerView adapters to bind the data to views.

## Screenshots

If user does not have to-do lists in the database, below screen is the
first screen user encounters.

![ssone.PNG](docs/screenshots/ssone.PNG)

As soon as the user adds a to-do list using the floating action button,
user will be redirected to the lists screen. Lists screen is a screen
where user's all to-do lists are displayed. When user runs the app if
user has existing to-do lists, introduction screen will be bypassed and lists
screen will be shown immediately.

![sstwo.PNG](docs/screenshots/sstwo.PNG) &nbsp;&nbsp;&nbsp; ![ssthree.PNG](docs/screenshots/ssthree.PNG)

On lists screen user can add more to-do lists using the floating action
button.

![ssfour.PNG](docs/screenshots/ssfour.PNG)

User can update an existing to-do list by clicking the corresponding edit
icon.

![ssfive.PNG](docs/screenshots/ssfive.PNG)

User can also delete an existing to-do list by clicking the corresponding
delete icon. If all existing lists are deleted, user gets redirected to
the introduction screen.

![sssix.PNG](docs/screenshots/sssix.PNG) &nbsp;&nbsp;&nbsp; ![ssseven.PNG](docs/screenshots/ssseven.PNG)

Once user clicks forward icon on a certain to-do list, he/she will be redirected
to the items screen of that particular to-do list. On this screen, user
can add new to-do items.

![sseight.PNG](docs/screenshots/sseight.PNG)

User can check the to-do items he/she already completed, as well as being
able to uncheck them if desired.

![ssnine.PNG](docs/screenshots/ssnine.PNG)

