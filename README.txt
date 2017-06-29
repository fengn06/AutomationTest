Code language: JAVA
Testing tool: Selenium Webdriver
Testing website: mobile.walmart.com

# Code structure
1. package "com.ning.walmart.page"
   Page class is an abstract class which is inherited by several concrete subclasses.
   Each subclass represents a type of real webpage in Walmart website.
2. package "com.ning.walmart.drive":
   DriverInterface provides an interface for different drivers to implement
   SeleniumDriver is a concrete class implementing DriverInterface and encapsulates
   all the driver api for web elements manipulation
3. package "com.ning.walmart.automationtest": main test entry "TestMain.java"
4. package "com.ning.walmart.config": configuration

# Code design
1. A website like Walmart.com contains many types of webpages and many complicated
transitions among webpages. It can be seen as a state machine with transitions
connecting different states. I followed the State pattern, and  designed several
subpage classes to wrap each
state. Each action (such as clicking the button) is represented as functions in
the Page state class, and they will return the next Page state when finished. So
in short each State method will return a new State. This
allows a series of actions to be chained together.
2. DriverInterface provides a interface that all the drivers need to follow.
There can be many types of drivers (Selenium, Watir, etc). As long as they implement the interface,
they can be used to fetch the web elements. The interface hides the specific
driver that are actually used from Pages. Factory pattern is used for initializing the Driver. Page class can get a Driver object
from configuration file without caring about which backend (such as Selenium) it is actually using.
3. To avoid creating too many pages (for instance, test cases can navigate back to the
same page), each subpage is created only once, and managed
 inside a pool for later repeated use by the parent Page class (this follows the Flyweight pattern).
4. Each subpage class encapsulates all the functionalities of the corresponding
webpage. Such a design makes the code easy to understand and maintain. More
functions can be added into specific pages which makes the project extendable.
5. Most importantly, test specific variables can be set and stored in the Configuration class
rather than hard coded. Each test case can specify options to choose Driver, web browser,
walmart URL easily. This is vary useful when we need to test using different
browsers (chrome or safari), drivers (Selenium or Watir), or testing against
different Walmart servers (beta server or production server).

# Functionalities and results
All the functional requirements are met and all
the test cases can pass testing.

# Something can be implemented further
1. This project only implements the basic purchase flow. There are many other
states that are not addressed in the code. For example, the product can be out
of stock and is not purchasable; or the product can only be picked up by person
and cannot be shipped.
2. Each subpage can contain a few inner states. The code flow needs to be redirected
by inner states. For example, when the user has been already logged in, the page
after clicking checkout should be different.
3. After redirecting to each page, we need to check whether we are on the correct
page before doing next action. It's implemented by a few pages now (such as ProductListPage,
it will test if it's actually is a product list when loaded), but not all of them.
