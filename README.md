**MODULE 1_3**

Basic CRUD console app for demonstrating work with Gson, exceptions, Java scanner and project design

**To run this app** 
1. create project.properties file from [project.properties.example](project.properties.example) and edit path to json files in this props file.
2. start app using `run` task from app directory

This program is a console application with menu and sub-menus.
You can add, delete or edit each entity (Writer, Post, Label). Also, you can get list of each.

**Structure**

**Writer** - has name, surname, status and can have list of Posts.
**Post** - has content, date created, date updated, status and can have list of Labels.
**Label** - has name and status.

**Menu**

All menus have navigation using digits and have hints
