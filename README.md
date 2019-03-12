# Journals

A simple news app based on this Api https://newsapi.org/ to showcase various technologies, libraries and techniques.

## Technical Overview

The app is implemented using mvvm, clean architecture with paging and a local database and finally unit testing.

1- Dagger ViewModel Factory:

	The app utilizes a technique to reduce ViewModelFactory clutter and support scaling. The factory is implemented with 
	Dagger 2's Multibingind to extract ViewModel creation out of the ViewModelFactory to support an unlimited number 
	of ViewModels in a maintainable way.

2- Data Binding:

	Databinding is used to reduce UI logic as much as possible with custom adapters when needed. Also, binding adapters
	are used in conjuction with dagger to further clean up the UI code.
	
3- Paging with two data sources:

	Paging is implemented with network and local data sources which achieves multiple features. The app supports working 
	without	an network connection and reduces load times since data is supported by a local DB. The logic ensures the local
	DB is always in sync with the API as long as there is a network connection.
	
4- Clean Architecture:

	The app is implemented in Clean Architecture with a module per layer style and ensures that all dependencies flow 
	inwards towards the Domain Layer. Heavy use of dependency inversion and mapping helps reduce each modules scope
	whilst increasing robustness going towards the Domain.
	
5- MVVM:

	Android's ViewModel is used to support orientation changes and reactive programming. This helps in using multiple
	technologies accordingly. LiveData is used in the ViewModels to utilize lifecycle awareness while RxJava is used
	in deeper layers to utilize threading and data operators.
	
6- Dagger:

	Dagger is used to manage dependencies in conjuction with interfaces and qualifiers which helps creating a plug-in
	Architecture with third party Libraries to reduce coupling and to dynamically change dependencies.
	
7- Unit Tests:

	The app has an appropriate amount of unit tests for the chosen architecture. They enforce certain behaviors to
	support future refactoring and changes when needed. Mockito is used to help ease mocking and testing.
	
8- Neat UI;

	While UI is not the focus of this project a minimalistic and neat UI design was chosen. The UI also supports landscape.
	
## Installation

Android studio will handle the Installation, the app targets SDK 28 and the latest gradle plugin and wrapper versions
3.3.2 and 4.10.1 respectively at the time of writing this. Also, it uses AndroidX libraries.
	
## Contributing

The App is open sourced, however, contribution is not currently needed. 	
	
## License

To Be Added soon.
