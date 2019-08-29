# CodeFellowship
Code Fellows 401 Java Labs 16-20

An app that allows people learning to code to connect with each other and support each other on their coding journeys.


## Instructions for Running

### Setting Up:
Before running the app for the first time, you will need to complete a couple steps.
1. Create a PostgreSQL database on your machine
    1. Open your terminal and log in to `psql`
    2. Run the command 
       ```
       CREATE DATABASE codefellowship
       ```
    3. Exit PostgreSQL by typing `\q`
2. Modify [application.properties](/src/main/resources/application.properties)
    1. Open application.properties in `/src/main/resources/application.properties`
    2. Modify the first three lines of code to match your `database name`, `psql username`, and `psql password`
    3. Uncomment the last line of code __only the first time you run the app__. Then you should re-comment this line out, as your database will be cleared of information on restart if not.
       ```
       spring.datasource.url=jdbc:postgresql://localhost:5432/<DATABASE NAME>
       spring.datasource.username=<PSQL USERNAME>
       spring.datasource.password=<PSQL PASSWORD>
       #spring.jpa.hibernate.ddl-auto=create
       ```

### To run the app:
Navigate your copy of the repository and run:
```
$ gradle bootRun
```

## Available Routes
Public routes:
* `/` splash page with links to login, signup, and view profile
* `/login` login page
* `/signup` sign up page to create a new account

Private routes (user must be logged in):
* `/myprofile` logged in user's profile page and posts
* `/users/{id}` view a user's information and posts
* `/posts/create` create a new post
* `/users` view all users
* `/feed` view a feed containing posts from users you follow


## Feature Tasks
### Lab 16: Spring Auth
-[x] Build an app that allows users to create their profile on CodeFellowship.
-[x] The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.
-[x] An ApplicationUser should have a username, password ( hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
-[x] The site should allow users to create an ApplicationUser on the “sign up” page.
-[x] Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run passwordEncoder.encode(password) before saving the password into the new user.
-[x] The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
-[x] This should include a default profile picture, which is the same for every user, and their basic information.
-[x] Using the above cheat sheet, add the ability for users to log in to your app.
-[x] Upon logging in, users should be taken to a /myprofile route that displays their information.
-[x] Ensure that user registration also logs users into your app automatically.
-[x] The site should be reasonably styled. (This can be using CSS that you did not create yourself.)
-[x] The site should contain integration testing. At a minimum, there should be tests to ensure basic functionality for the splash page and the sign up page.

### Lab 17: CodeFellowship Profiles with Login
-[x] Allow users to log in to CodeFellowship and create posts.
-[x] Add the ability for users to log in to your app.
-[x] Upon logging in, users should be taken to a /myprofile route that displays their information.
-[x] Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
-[x] Ensure that user registration also logs users into your app automatically.
-[x] Add a Post entity to your app.
    -[x] A Post has a body and a createdAt timestamp.
-[x] A logged-in user should be able to create a Post, and a post should belong to the user that created it.
     hint: this is a relationship between two pieces of data
-[x] A user’s posts should be visible on their profile page.
-[x] When a user is logged in, the app should display the user’s username on every page (probably in the heading).

### Lab 18: Following Users on CodeFellowship
-[x] Ensure that users can’t perform SQL injection or HTML injection with their posts.
-[x] Allow users to follow other users. Following a user means that their posts show up in the logged-in user’s feed, where they can see what all of their followed users have posted recently.
    -[x] Ensure there is some way (like a users index page) that a user can discover other users on the service.
    -[x] On a user profile page that does NOT belong to the currently logged-in user, display a “Follow” button. When a user clicks that follow button, the logged-in user is now following the viewed-profile-page user.
        -[x] note: this will require a self-join on ApplicationUsers.
    -[x] A user can visit a url (like /feed) to view all of the posts from the users that they follow.
        -[x] Each post should have a link to the user profile of the user who wrote the post.

## Resources
* [Spring Auth Cheat Sheet](https://github.com/codefellows/seattle-java-401d2/blob/master/SpringAuthCheatSheet.md)
* [PostgreSQL Column Definition](https://docs.oracle.com/javaee/5/api/javax/persistence/Column.html#columnDefinition())
* [Bootstrap Cheat Sheet](https://hackerthemes.com/bootstrap-cheatsheet/)
* [Bootstrap Form Inputs](https://www.w3schools.com/bootstrap4/bootstrap_forms_inputs.asp)
* [CSS Flexbox text-overflow](https://css-tricks.com/flexbox-truncated-text/)
* [CSS Flexbox text-overflow example](https://codepen.io/chriscoyier/pen/zqedErc)