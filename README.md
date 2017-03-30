# SOEN-6441-project
* Code Structure

```
ddg.builder

	the builder pattern we used
ddg.model

	data model in game
ddg.model.entity

	entity and item in model
ddg.ui

	main game page/JFrame
ddg.ui.view

	JPanel and view used in ui
ddg.ui.view.component

	custom component in view
ddg.ui.view.dialog

	dialog in view
ddg.utils

	some tools
```

* For Windows platform

1.set text file encoding : UTF-8

2.set new text file line : UNIX

* For all user 

execute git command

git config --global core.autocrlf false

git config --global core.safecrlf true

* For code template

Window->preferences->Code Style->Code Templates->comments

->types
```
/**
 * 
 * This class
 * 
 * @author Your Name
 * @date ${date}
 */
```

->Methods
```
/**
 * 
 * This method
 * 
 * ${tags}
 */
```