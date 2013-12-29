== McCon ==
McCon is a new Economy plugin for Bukkit. The concept is simplicity and ease of use. Other economy plugins are outdated, or have many issues, or they are built into other plugins and Don't have the features of a Stand-Alone Economy plugin. The Attempt of McCon is to create an Easy to use, lightweight economy plugin using SQLite, and possibly other Databases in the future.

=== Commands & Permissions ===
==== Commands ====
* **/money** [Aliases: /bal, /balance] - Display your Balance
* **/money <account>** [Aliases: /bal, /balance] - Display another user's balance
* **/pay <account> <amount>** - Send a user money from your account.
* **/request <account> <amount>** [Aliases: /invoice] - Request Money from another user.
* **/money give <account> <amount>** - Spawn money into an account.
* **/money set <account> <balance>** - Set <account> to <balance>
* **/money take <account> <amount>** - Take money from <account>
* **/money admin** - Configuration settings [Displays Help Menu]
* **/money stats** - Display Money Statistical Information (If Enabled)

==== Permissions ====
* **mccon.balance** - Basic Permission. Allows a user to have a balance
* **mccon.pay** - Send money to another user
* **mccon.admin.give** - Spawn money into an account
* **mccon.admin.set** - Set an account balance
* **mccon.admin.take** - Take money from an account
* **mccon.admin.settings** - View Administration configuration command
* **mccon.stats** - View Economy Statistics

=== Issues, Feature Requests, Todo ===
==== Issues & Feature Requests ====
* Create a new Issue on our [[https://github.com/Saes/McCon/issues/new|GitHub repo]]. Please explain the details of the issue (how to reproduce the issue, the Stacktrace error from the console, etc.) or the Details of the Feature Request (What the feature is, the permission, the command, what it does, You could even submit a Pull request on GitHub with the Code!)

==== ToDo & Planned Features ====
* Banks (Money Holding, configurable interest Rates, Possibly Player or Server created?)
* Infinite accounts
* Transaction Logging
* Suggest Features on the GitHub repo!