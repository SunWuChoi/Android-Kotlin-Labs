COSC435 Final Exam

Directions: Delete all the choices that are wrong for each question. Leave only the correct answer. Include the edited file in your push to Bitbucket.

Example: 
0. What is the current version of Android?
    a. Android 8
    b. Android 9
    c. Android 10
    d. Android 11

Example solution: (Edit the file to remove a, b, and d, leaving only the answer: c)
0. What is the current version of Android?
    c. Android 10


Questions:

1. List the 4 HTTP methods used in a REST API that match those 4 CRUD operations.
    a. GET, PASTE, POST, DELETE
    b. GET, UPDATE, POST, REMOVE
    c. RETRIEVE, PUT, PASTE, DELETE
    d. GET, POST, PUT, DELETE

2. Which snippet will run a coroutine on a background thread?
    a. withCoroutine(Dispatchers.Background) {}
    b. with(Dispatchers.Main) {}
    c. withContext(Dispatchers.Default) {}
    d. withContext(Dispatchers.Main) {}

3. How does Android choose which layout to inflate when a device is in portrait or landscape mode?
    a. Must be manually done through code
    b. Define 2 layouts (app crashes if landscape layout is missing)
    c. Define 2 layouts (one goes in layout, one goes in layout-land)
    d. Define 2 layouts (one called layout-portrait, the other called layout-land)

4. What is the purpose of the Bundle argument to Activity.onCreate?
    a. To pass data to the Activity
    b. To save instance state
    c. To pass data to other activities
    d. To read data from a database

5. Which of the following explains the steps involved in starting and activity and receiving a result from it?
    a. Call startActivity to go to activity2. Call startActivity to go back to activity1.
    b. Call startActivityForResult to go to activity2. Call startActivity to go back to activity1.
    c. Call startActivityForResult to go to activity2. Call setResult and finish to return to activity1.
    d. Call startActivity to go to activity2. Call setResult and finish to return to activity1.

6. An activity is launched, then within that activity, a new activity is started. Which option correctly lists all of the activity lifecycle methods that are called, in the correct order?
    a. Activity2: onCreate, onResume, onStart
       Activity1: onStop, onPause, onDestroy
    b. Activity2: onStart, onResume
       Activity1: onPause, onStop, onDestroy
    c. Activity2: onStart, onResume
       Activity1: onPause, onStop
    d. Activity2: onCreate, onStart, onResume
       Activity1: onPause, onStop

7. Continuing question 6, if the user then presses the back button, then presses the home button, describe the lifecycle methods that are called, in order?
    a. Activity2: onDestroy
       Activity1: onResume, onStop
    b. Activity2: onPause, onStop, onDestroy
       Activity1: onStart, onResume, onPause, onStop
    c. Activity2: onPause, onStop
       Activity1: onStart, onStop, onDestroy
    d. Activity2: onStop, onDestroy
       Activity1: onStart, onResume, onPause, onStop

8. Which service type runs on a background thread?
    a. Service, JobService, IntentService
    b. Service
    c. IntentService
    d. JobService, IntentService

9. 2 Activities can be displayed at the same time.
    a. true
    b. false

10. 2 different apps can access each other’s files directly.
    a. true
    b. false



