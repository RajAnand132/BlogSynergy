# <h1 align="center">BlogSynergy : Blogging Platform</h1>
<p align="center">
  <a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
  </a>
  <a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-4.0-brightgreen.svg" />
  </a>
  <a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.1.3-brightgreen.svg" />
  </a>
   <img alt = "GPL v3" src="https://img.shields.io/badge/License-GPLv3-blue.svg" />
</p>

<p align="left">


## Table of Contents

- [Introduction](#introduction)
- [Features](#key-features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Data Flow Diagram](#data-flow-diagram)
  - [Database Diagram](#database-diagram)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
  - [User Controller](#user-controller)
  - [Post Controller](#post-controller)
  - [Comment Controller](#comment-controller)
- [Authentication](#authentication)
- [Flow Explanation](#flow-explanation)
- [Testing](#testing)
- [Contributing](#contributing)
- [Acknowledgements](#acknowledgements)
- [Swagger Configuration](#swagger-configuration)
- [Contact Information](#contact-information)


## Introduction

BlogSynergy is a feature-rich blogging platform designed to provide an intuitive and user-friendly experience for bloggers and readers alike. This platform serves as a one-stop destination for blog creation, reading, commenting, following, and user engagement. It aims to facilitate seamless interactions between bloggers and their audience.

## Key Features

### User Registration and Authentication
- New users can easily register and create accounts, providing their unique usernames, email addresses, and secure passwords.
- Robust authentication ensures that only registered users can access and engage with the platform.

### User Login and Logout
- Registered users can log in to their accounts, enabling personalized interactions.
- Logging out is straightforward, ensuring security and privacy.

### Blogging
- Bloggers can create and publish posts with a title, content, and optional media attachments.
- The posts are organized by type, such as text, images, or links, catering to various content formats.

### Comments
- Users can comment on blog posts to engage in discussions or provide feedback.
- Commenting is open to all registered users.

### Followers and Following
- The platform supports a social component where users can follow other bloggers.
- Followers receive updates about the bloggers they follow.

### User Profile Management
- Users have dedicated profiles that display their username, email, and user-specific information.
- Registered users can manage their profiles and view the blogs they've published.

### Password Encryption
- User passwords are securely stored and encrypted to protect user data.

### Blog Search and Pagination
- The platform enables users to search for blogs based on keywords.
- Search results are paginated to provide efficient navigation and load times.

### Content Curation
- The platform enhances user experience by providing content curation.
- Blog posts can be categorized and organized based on content type (text, images, links), making it easier for readers to discover content of interest.


## Prerequisites

Before getting started, ensure you have the following installed on your system:

- Java Development Kit (JDK) 8 or higher
- Spring Boot
- MySQL Database
- IDE (e.g., IntelliJ IDEA, Eclipse)

## Data Flow Diagram



                             ┌─────────────────────────┐
                             │         APIs            │
                             └───────────┬─────────────┘
                                         │
                                         ▼
                             ┌─────────────────────────┐
                             │       Controllers       │
                             └───────────┬─────────────┘
                                         │                                  
                                         ▼
                             ┌─────────────────────────┐
                             │     Authentication      |
                             |            &            |
                             |      Authorization      │
                             └───────────┬─────────────┘
                                         |
                                         ▼
                             ┌─────────────────────────┐
                             │       Services          │
                             └───────────┬─────────────┘
                                         │
                                         ▼
                             ┌─────────────────────────┐
                             │       Repositories      │
                             └───────────┬─────────────┘
                                         │
                                         ▼
                             ┌─────────────────────────┐
                             │       Database          │
                             └─────────────────────────┘

## Database Diagram

![Database Diagram]()

## Getting Started

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/RajAnand132/BlogSynergy.git
2. Open the project in your preferred IDE.

3. Configure your MySQL database settings in src/main/resources/application.properties.

4. Build and run the application.


## Configuration
Before running the application, make sure to configure the database and other settings in the application.properties file. Example:

```
spring.datasource.url=jdbc:mysql://localhost:3306/bloggingPlatform
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## Usage

Once the blogging platform is up and running, users can access the user interface and API endpoints to interact with the system. Here's how to use the platform effectively:

- **User Registration and Authentication**
  1. New users can register for an account by providing a unique username, email address, and a secure password.
  2. Robust authentication ensures that only registered users can access the platform.

- **User Login and Logout**
  1. Registered users can log in to their accounts to access personalized features and content.
  2. Users can log out of their accounts to ensure the security and privacy of their sessions.

- **Blogging**
  1. Bloggers can create and publish posts, including a title, content, and optional media attachments.
  2. Posts are organized by type, such as text, images, or links, to cater to various content formats.

- **Comments**
  1. Registered users can comment on blog posts to engage in discussions, provide feedback, or interact with bloggers.
  2. Commenting is open to all registered users, fostering community and conversation.

- **Followers and Following**
  1. Users can follow other bloggers to stay updated on their latest posts and activities.
  2. A social component allows users to build a network of followers and interact with the bloggers they follow.

- **User Profile Management**
  1. Users have dedicated profiles where they can view and update their information.
  2. Registered users can manage their profiles and access a list of blogs they've published.

- **Password Encryption**
  1. User passwords are securely stored and encrypted to protect user data from unauthorized access.

- **Blog Search and Pagination**
  1. Users can perform keyword-based searches to discover blogs of interest.
  2. Search results are paginated, offering a smooth and efficient browsing experience.

- **Content Curation**
  1. The platform enhances user experience by providing content curation features.
  2. Blogs can be categorized and organized based on content type (text, images, links), making it easier for readers to find relevant content.

By following these steps, users can fully utilize the blogging platform to create, share, and explore engaging content and discussions.

## API Endpoints
Once the application is up and running, you can access the user interface and API endpoints. Here's how to use the system:

#### Access the Application

The application will be available at localhost
```
http://localhost:8080
```

## User Controller

### Register User
New user can register themselves into this blog portal.
- **Endpoint**: 
```
/signUp
```
- **Method**: `POST`
- **Request Parameters**:
  - `userName` (String, required): The username for the new user.
  - `email` (String, required): The email for the new user.
  - `password` (String, required): The password for the new user.
- **Response**:
  - Success: "User with username: {username} successfully registered."
  - User already exists: "User with username: {username} already exists."
  - Internal server error: "Internal Server Error while registering, please try again later."

### Login User
Registerd users can sign in using this api endpoint.
- **Endpoint**: 
```
/signIn
```
- **Method**: `POST`
- **Request Parameters**:
  - `userName` (String, required): The username for the user.
  - `password` (String, required): The password for the user.
- **Response**:
  - Success: "Successfully logged in."
  - Invalid credentials: "Invalid Credentials."
  - Internal server error: "Internal Server Error while logging in, please try again later."

### Log Off User
Signed in user can log off themselves from the blog portal.
- **Endpoint**: 
```
/signOut
```
- **Method**: `POST`
- **Request Parameters**:
  - `userName` (String, required): The username for the user.
- **Response**:
  - Success: "Successfully logged off. You are now logged out."
  - User not found: "User not found."
  - User not currently logged in: "You are not currently logged in. No action taken."

### Follow User
Signed in users can follow other users.
- **Endpoint**: 
```
/follow
```
- **Method**: `POST`
- **Request Parameters**:
  - `userName` (String, required): The username of the follower.
  - `userNameToFollow` (String, required): The username of the user to follow.
- **Response**:
  - Success: "You are now started following {followedUserName}."
  - Follow failed: "Follow Failed: The user you are trying to follow does not exist."
  - Access denied: "Access Denied: You are not logged in. Please log in first."

### Unfollow User
Signed in users can unfollow other users.
- **Endpoint**: 
```
/unfollow
```
- **Method**: `POST`
- **Request Parameters**:
  - `userName` (String, required): The username of the follower.
  - `userNameToUnFollow` (String, required): The username of the user to unfollow.
- **Response**:
  - Success: "You are no longer following {unfollowedUserName}."
  - Unfollow failed: "Unfollow Failed: The user you are trying to unfollow does not exist."
  - Access denied: "Access Denied: You are not logged in. Please log in first."

### Get Followers
User can see all the followers.
- **Endpoint**: 
```
/followers
```
- **Method**: `GET`
- **Request Parameters**:
  - `userName` (String, required): The username of the user.
- **Response**:
  - List of followers or "You don't have any followers yet."
  - Invalid user: "Invalid user: The specified user does not exist."
  - Access denied: "Access Denied: You are not logged in. Please log in first."


## Post Controller

### Create Post
Create a new blog post.

**Endpoint:** 
```
/post
```

- **Method**: `POST`

**Parameters:**
- `userName` (String) - The username of the post author.
- `title` (String) - The title of the post.
- `postType` (PostType) - The type of the post (e.g., Text, Image, Link).
- `content` (String) - The content of the post.

**Response:**
- Success: A success message confirming the post creation.
- Error: An error message if the post creation fails.

### Get Posts

Retrieve a user's blog posts, with optional pagination for comments.

**Endpoint:** 
```
/posts
```

- **Method**: `GET`

**Parameters:**
- `userName` (String) - The username of the user.
- `commentPageNumber` (Integer) - The page number for comments pagination.

**Response:**
- Success: A list of the user's blog posts. Comments can be paginated based on the provided page number.
- Error: An error message if the request fails.

### Update Post

Update an existing blog post.

**Endpoint:** 
```
/post
```

- **Method**: `PUT`

**Parameters:**
- `userName` (String) - The username of the post author.
- `postId` (Long) - The unique identifier of the post.
- `title` (String) - The updated title of the post.
- `postType` (PostType) - The updated type of the post.
- `content` (String) - The updated content of the post.

**Response:**
- Success: A success message confirming the post update.
- Error: An error message if the update fails.

### Delete Post

Delete a user's blog post.

**Endpoint:** 
```
/post
```

- **Method**: `DELETE`

**Parameters:**
- `userName` (String) - The username of the post author.
- `postId` (Long) - The unique identifier of the post to be deleted.

**Response:**
- Success: A success message confirming the post deletion.
- Error: An error message if the deletion fails.

### Search Posts

Search for blog posts based on a query, with optional pagination for comments.

**Endpoint:** 
```
/posts/search
```

- **Method**: `GET`

**Parameters:**
- `userName` (String) - The username of the user.
- `query` (String) - The search query.
- `commentPageNumber` (Integer) - The page number for comments pagination.

**Response:**
- Success: A list of blog posts matching the search query. Comments can be paginated based on the provided page number.
- Error: An error message if the search fails.



## Comment Controller

### Create Comment

Add a new comment to a blog post.

**Endpoint:** 
```
/comment
```

- **Method**: `POST`

**Parameters:**
- `userName` (String) - The username of the commenter.
- `comment` (String) - The text of the comment.
- `postId` (Long) - The unique identifier of the post to which the comment is added.

**Response:**
- Success: A success message confirming the comment creation.
- Error: An error message if the comment creation fails.

### Get Comment

Retrieve a specific comment by its ID.

**Endpoint:** 
```
/commentId
```

- **Method**: `GET`

**Parameters:**
- `userName` (String) - The username of the user.
- `commentId` (Long) - The unique identifier of the comment.

**Response:**
- Success: The requested comment.
- Error: An error message if the request fails.

### Update Comment

Edit an existing comment.

**Endpoint:** 
```
/commentId
```

- **Method**: `PUT`

**Parameters:**
- `userName` (String) - The username of the commenter.
- `commentId` (Long) - The unique identifier of the comment.
- `comment` (String) - The updated text of the comment.

**Response:**
- Success: A success message confirming the comment update.
- Error: An error message if the update fails.

### Delete Comment

Remove a comment from a blog post.

**Endpoint:** 
```
/commentId
```

- **Method**: `DELETE`

**Parameters:**
- `userName` (String) - The username of the commenter.
- `commentId` (Long) - The unique identifier of the comment to be deleted.

**Response:**
- Success: A success message confirming the comment deletion.
- Error: An error message if the deletion fails.



## Authentication

Authentication in this blogging platform is fundamental to safeguard user data and ensure that only authenticated users can perform specific actions.

### Process

#### User Registration:

- A new user can register on the platform by providing a unique `userName`, an email address, and a password.
- The password is securely encrypted before being stored in the database to ensure confidentiality, even in the event of a data breach.
- Upon successful registration, the user is ready to log in.

#### User Login:

- To log in, users provide their `userName` and password.
- The entered password is encrypted and matched with the stored encrypted password in the database.
- If the credentials match, the user is authenticated, and a new session is initiated for the user.
- A `LoginCheck` record might be created or updated in the database to keep track of the user's logged-in status.

#### User Logout:

- When a user decides to log out, the associated `LoginCheck` record is deleted or updated, effectively ending the user's session.
- This ensures that even if someone gains access to a user's session token or identifier, they won't be able to misuse it after the user logs out.

#### Authorization Check:

- For specific actions, such as creating a post or leaving comments, the system checks if the user is logged in by referencing the `LoginCheck` service or a similar mechanism.
- This check ensures that only authenticated users can perform certain actions. If a user is not logged in, they receive a message prompting them to log in first.

#### Data Integrity and Security:

- When displaying user data, sensitive information like passwords and emails are often set to null before sending the data to the frontend to ensure user privacy and data security.

### Additional Notes

- Always ensure the use of secure methods and libraries when dealing with passwords and user data. Avoid storing plain-text passwords.
- It's advisable to use HTTPS for all authentication requests to prevent man-in-the-middle attacks.
- Regularly review and update authentication mechanisms to address any potential vulnerabilities or improvements.


 

## Flow Explanation

To provide a more detailed explanation of the flow:

### User Registration and Authentication

- New users can register by providing a unique username, email address, and password.
- The password is securely encrypted before being stored in the database to protect user data.
- After successful registration, users can log in to their accounts.

### User Login and Logout

- Registered users can log in using their username and password.
- The entered password is encrypted and matched with the stored encrypted password in the database.
- If the credentials match, the user is authenticated, and a new session is initiated.
- Users can log out, which ends their session by deleting or updating the associated session record.

### Blogging

- Bloggers can create and publish posts with titles, content, and optional media attachments.
- Posts are categorized by type, such as text, images, or links, to cater to various content formats.

### Comments

- Users can comment on blog posts, engaging in discussions or providing feedback.
- Commenting is open to all registered users.

### Followers and Following

- Users can follow other bloggers, and followers receive updates about the bloggers they follow.
- Following allows users to stay updated on their favorite bloggers' posts.

### User Profile Management

- Users have dedicated profiles displaying their username, email, and user-specific information.
- Registered users can manage their profiles and view the blogs they've published.

### Password Encryption

- User passwords are securely stored and encrypted to protect user data.
- Passwords are hashed before storing in the database, enhancing security.

### Blog Search and Pagination

- Users can search for blogs based on keywords.
- Search results are paginated to provide efficient navigation and load times.
- Users can access a limited number of comments on each page for improved usability.

### Content Curation

- The platform enhances user experience by providing content curation.
- Blog posts are categorized and organized based on content types (text, images, links) for easier content discovery.

### Data Privacy and Security

- Sensitive user data like email addresses and passwords are nullified when displaying user data, ensuring privacy.
- User privacy and data security are prioritized throughout the platform.

This flow ensures that users can easily create, share, and engage with blog content while maintaining data privacy and security. The system promotes a dynamic and interactive blogging community with features for both bloggers and readers.


## Testing

Ensuring that our blogging platform is robust and error-free is crucial. We've incorporated both unit tests and integration tests to ensure the system's reliability.

- **Unit Tests**: These are used to test the smallest parts of the application in isolation (e.g., methods or functions). They ensure that individual components of the app work as they should.

- **Integration Tests**: These tests ensure that various components of the application work together harmoniously. They can be used to test the system as a whole or specific interactions between components.

To run the tests, you can use the following methods:

- **Using IDE**: Most modern IDEs come with built-in support for running tests. Navigate to the test directory and execute them directly from the IDE.

- **Using Gradle**: If the project is set up with Gradle, navigate to the root directory of the project in your terminal and execute `gradle test`.

- **Using Maven**: If the project is set up with Maven, you can run tests by navigating to the project's root directory in your terminal and executing `mvn test`.

Remember, continuous testing during the development process helps in identifying issues early, making the debugging process more manageable.

## Contributing

We believe in the power of collaborative development. Hence, contributions to enhance this blogging platform are wholeheartedly welcome.

1. **Fork and Clone**: Start by forking this repository and then clone your forked repository to your machine.

2. **Create a New Branch**: Always create a new branch for your changes. This ensures that the master/main branch only contains production-ready code.

3. **Make Your Changes**: Implement your features or bug fixes, ensuring that you've added appropriate tests for your changes.

4. **Run Tests**: Ensure all tests pass after your changes.

5. **Commit and Push**: Commit your changes with a meaningful commit message. Push your changes to your forked repository on GitHub.

6. **Create a Pull Request**: Open a pull request to the main repository. Provide a detailed description of the changes you made.

7. **Code Review**: Your pull request will be reviewed, and you may need to make some additional changes based on feedback before it gets merged.

8. **Merge**: Once approved, your pull request will be merged into the main codebase.

If you have feature suggestions, ideas, or find any bugs, please open an issue. Let's collaborate to make this platform even better!

## Acknowledgements

Thank you for considering this backend design as a starting point for your Blogging Platform. Special thanks to all contributors and supporters. Your trust drives us to create better, robust, and user-friendly systems. Good luck with your project!




## Swagger Configuration

**LocalHost**
```
http://localhost:8080/swagger-ui/index.html#/
```

**Public Address**
```
http://65.2.74.221:8080/swagger-ui/index.html#/
```


## Contact Information

If you have any questions, issues, or need further assistance related to the Blogging Platform project, feel free to contact us. We are here to help!

- **Raj Anand**
  - Email: [rajanandirctc@gmail.com](mailto:rajanandirctc@gmail.com)
  - LinkedIn: [LinkedIn Profile](https://www.linkedin.com/in/raj-anand132)
  - GitHub: [GitHub Profile](https://github.com/RajAnand132)


Thank you for your interest in the Model-Based CRUD Operations Portal project. We look forward to hearing from you and hope you find this resource valuable for your endeavors. Your contributions and feedback are highly appreciated.

# Happy coding!!!!!



