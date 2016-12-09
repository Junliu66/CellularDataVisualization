Tasks for Project 9 and Team [03]
=====================================

Part A
---------

The planned meeting time: 
Time\Place\Date | 12/03 Sat |   11/27 Sun   |  11/29 Tue  | 
----------------|-----------|---------------|-------------|
3:30pm - 5:30pm |  Online   | STEM Center   | STEM Center | 
----------------|-----------|---------------|-------------|

- task 1: Based on the previous project, construct a class named MouseHoverOverData.java which implement MouseListener to show the hidden data when mouse hover over data points in GraphView. 
	- Junliu Zhang is in charge of this class
	- Complete by 12/05 Monday
	- Discuss with Stella and complete merge this file by the end of 12/07 Wednesday

- task 2: Based on the previous project, construct a class named MouseHoverOverCountry.java which implement MouseListener to show only one data flows of a country when mouse hover over country in Legend Panel. 
	- Stella is in charge of this class
	- Complete by 12/05 Monday
	- Discuss with Junliu and complete merge this file by the end of 12/07 Wednesday

- task 3(Add this feature if still have time):Construct a class named CompareTwoCountry.java which implement JOptionPanel to prompt user to enter two countries he or she wants to compare with. Show two countries' data in bar graph. 
	- Junliu and Stella both are in charge of this class
	- Complete by 12/07 Wednesday
	- Discuss with Junliu and complete merge this file by the end of 12/08 Thursday



<br><br>

Part B
---------
- task 1: Based on the previous project, construct a class named MouseHoverOverData.java which implement MouseListener to show the hidden data when mouse hover over data points in GraphView.
-
    Class  | MouseHoverOverData.java
    -------|------------------------
    Method | Constructor
           | mouseMoved();
           | mouseDragged();
	- Junliu worked on this class
	- Complete by 12/05 Monday
	- Completed on 12/05 Tuesday

- task 2: Based on the previous project, construct a class named MouseHoverOverCountry.java which implement MouseListener to show only one data flows of a country when mouse hover over country in Legend Panel.
- 	
	Class  | MouseHoverOverCountry.java
    -------|------------------------
    Method | Constructor
           | mouseMoved();
           | mouseDragged();
	- Stella worked on this class
	- Complete by 12/05 Monday
	- Completed on 12/03 Saturday

- task 3: Based on the previous project, construct a class named MouseClickCountry.java which implement MouseListener to show one specific country data when mouse click country in legend Panel.
-
    Class  | MouseClickCountry.java
    -------|------------------------
    Method | Constructor
           | mouseClicked();
           | mousePressed();
           | mouseReleased();
           | mouseEntered();
           | mouseEntere();
           | mouseExited();
	- Stella worked on this class
	- Completed on 12/05 Monday

- task 4: Construct classes named ShowCountryPanelForFirstFrame.java, SelectedCountryPanelForFirstFrame.java and
MouseClickCountryForFirstFrame.java to let user select countries he or she want to see on the graph.
-
    Class  | ShowCountryPanelForFirstFrame.java | SelectedCountryPanelForFirstFrame.java | MouseClickCountryForFirstFrame
    -------|------------------------------------|----------------------------------------|------------------------------------
    Method | Constructor                        | Constructor                            | Constructor
           | isEmptyClick()                     | paintComponent()                       | mouseClicked()
           | isExistCountry()                   |                                        | mousePressed()
           | addClickCountryName()              |                                        | mouseReleased()
           | getLength()                        |                                        | mouseEntered()
           | getSelectedCountryList()           |                                        | mouseExited()           
           | paintComponent()                   |                                        |
    - Stella and Junliu both worked on this task
    - Completed on 12/07 Wednesday


Extra Credit Discussion (if applicable)
-----------------------

Sample discussion one(12/03):
    The first discussion is regarding some problems we met in the features we implemented. When we were doing mouse 
hover tasks, we first considered to implement MouseListener in our new class. But it could not capture the mouse 
movement in the graph. After we searched online and reviewed samples on gitHub, Stella recommended that we should 
implement MouseMothionListener so that we could use mouseMoved() method to get x and y value of the mouse.
    After we got the mouse movement, we wanted to check if the coordinates of mouse in a specific range of the panel. 
After discussion, we decided to hard code for the legend panel because position of every country in legend panel is 
fixed. However, for the points in graph view, since the data are different in every run, we could not use hard code. 
Junliu tried to check the value in class that implements MouseMotionListener and also tried to pass value to the 
graph view and then check it in paintComponent() method. But all the ways didn't work. So we discussed again about 
how to do this. We both tried several ways again and finally found that we could create a method called 
mouseXYequalsmappedXY() to check the mouse value. After doing that, this task was finished quickly.
    After doing these two tasks, we were trying to implement the third features we planned to do. But we wanted to 
make things easier. Since we already had MouseHoverOverCountry.java, why don't we use a similar way in the legend 
panel to compare two country. Stella, as a navigator, searched how to use mouse click to show the data of only one 
country, and Junliu quickly coded this part based on the examples and previous tasks.

Sample discussion two(12/06):
    The second discussion is mainly about designing. We have already done with the three features in our previous 
task description. Now we wanted to add other feature to make our project more user friendly. So we discussed about 
what should we add. Stella thought its better to let users choose the countries they want and Junliu agreed with that. 
So we decided to print out all the countries instead of generating random countries. Then we discussed more details 
about how to let user select countries. Stella thought of a way to use check box while Junliu thought of a way to 
create a frame with panels. We felt printing out the list of countries in a panel is a more straight forward way to 
show all the countries. So we decided to use two panel to select countries. One panel list all the countries, and 
another one will show the selected country after user click that country.
    After we discussed this task and made a clear design for this part. This time Junliu worked as a navigator and 
did some research online to find examples of and Stella coded for the new frame. After we have done with the 
first frame, which show all the countries and let user select them, we need to connect two frame. We found some 
examples of connecting frames and chose a best way to do that. After doing some small changes we finally made what 
our project currently looks like.
