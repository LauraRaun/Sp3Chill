import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TextUI {
    private ArrayList<Movie> movies;
    private ArrayList<Movie> favorites;
    private Scanner scan;
    private User user;




    public TextUI(ArrayList<Movie> movies, Scanner scan) {
        this.movies = movies;
        this.favorites = new ArrayList<>();
        this.scan = scan;
        this.user = null;
    }

    public void start() {
        boolean running = true;

        while (running) {

            System.out.println("--------CHILL--------");
            System.out.println("1.Register. ");
            System.out.println("2. login.  ");
            System.out.println("3. Close CHILL");
            System.out.println("Choose an option: ");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    registerUser();
                    break;

                case "2":
                    if (loginUser()) {
                        showMainMenu();
                    }
                    break;

                case "3":
                    running = false;
                    System.out.println("You chose o close Chill, thanks for watching");
            }
        }
    }

            private void registerUser(){
                if(user !=null) {
                    System.out.println("This user is already registered");
                    return;
                }

                System.out.println("\n --------REGISTER--------");
                System.out.println("Enter a username: ");
                String username = scan.nextLine();

                System.out.println("Enter your password: ");
                String password = scan.nextLine();

                if (username.isEmpty() || password.isEmpty()) {
                    System.out.println("you have to type in your username and password");
                    return;
                }

                user = new User (username, password);
                System.out.println("You are now registered");
            }
            private boolean loginUser() {


                if (user == null) {
                    System.out.println("You have no account, please register first");
                    return false;
                }
                System.out.println("\n--------LOGIN--------");

                System.out.println("Enter username: ");
                String username = scan.nextLine();

                System.out.println("Enter password: ");
                String password = scan.nextLine();

                if(user.getUsername().equals(username) && user.checkPassword(password)){
                    System.out.println("--------Welcome to Chill--------");
                    return true;
                } else {
                    System.out.println("Wrong username or password");
                    return false;
                }
            }

            private void showMainMenu() {
                boolean running = true;

                while (running) {
                    System.out.println("--------CHILL--------");
                    System.out.println("1. Show all the movies");
                    System.out.println("2. Search for a movie");
                    System.out.println("3. Show all saved movies");
                    System.out.println("4. Show all seen movies");
                    System.out.println("5. Show favorites");
                    System.out.println("6. Logout");
                    System.out.println(" Choose an option: ");

                    String choice = scan.nextLine();

                    switch (choice){
                        case "1":
                            showMovies();
                            break;

                        case "2":
                            searchMovie();
                            break;


                        case "3":
                            showSavedMovies();
                            break;

                        case "4":
                            showSeenMovies();
                            break;

                        case "5":
                            showFavorites();
                            break;

                        case "6":
                            running = false;
                            System.out.println("\nYou have logged out");
                            System.out.println("Thank you for watching");
                            break;

                        default:
                            System.out.println("That's not an option, try again");
                    }

                }
            }



    public void showMovies() {
        System.out.println("--------SHOW ALL MOVIES--------");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }
        try {
            int choice = Integer.parseInt(scan.nextLine());
            if (choice == 0) {
                return;
            }
            if (choice >= 1 && choice <= movies.size()) {
                Movie selectedMovie = movies.get(choice - 1);
                selectedMovie(selectedMovie);

            } else {
                System.out.println("There is no movies with this number, try again");
            }
        } catch (NumberFormatException e) {
            System.out.println("You have to chose a number. ");

        }
    }

    private void searchMovie(){
        System.out.println("--------SEARCH MOVIE--------");
        System.out.println("Enter movie title: ");

        String search = scan.nextLine();

        ArrayList<Movie>searchResults = new ArrayList<>();

        for(Movie movie : movies){

            if(movie.getTitle().toLowerCase().contains(search.toLowerCase())){

                searchResults.add(movie);
            }
        }

        if (searchResults.isEmpty()){
            System.out.println("There is not a movie with this title");
            return;
        }

        System.out.println("\nMovies found: ");
        for(int i = 0; i < searchResults.size(); i++){
            System.out.println((i+1) + ". " + searchResults.get(i));
        }

        System.out.println("0.Go back");
        System.out.println("Choose a movie");

        try {
            int choice = Integer.parseInt(scan.nextLine());

            if (choice == 0) {
                return;
            }
            if (choice >= 1 && choice <= searchResults.size()) {
                Movie selectedMovie = searchResults.get(choice - 1);
                selectedMovie(selectedMovie);

            } else {
                System.out.println("There is no movie with this number");
            }
        } catch (NumberFormatException e){
            System.out.println("You have to choose a number");
        }
    }

    private void selectedMovie(Movie movie) {
        System.out.println("\n You have chosen: " + movie.getTitle());
        System.out.println("1. Start the movie");
        System.out.println("2. Add to favorites");
        System.out.println("3. Save movie");
        System.out.println("0. Go back");

        System.out.println("Choose an option: ");

        String choice = scan.nextLine();

        switch (choice) {

            case "1":
                startMovie(movie);
                break;

            case "2":
                addToFavorites(movie);
                break;

            case "3":
                saveMovie(movie);
                break;

            case "0":
                break;

            default:
                System.out.println("Error, try again");
        }

    }

    public void startMovie(Movie movie) {

        System.out.println(movie.getTitle() + "starts now...");

        user.addSeenMovie(movie);

        System.out.println("This movie has been added to your seen movies");
    }

    public void saveMovie(Movie movie) {

        if (!user.getSavedMovies().contains(movie)){
           user.saveMovie(movie);

            System.out.println(movie.getTitle() + " has been added to your saved movies");

        } else {

            System.out.println("This movie is already saved");
        }
    }

    private void showSavedMovies() {
        System.out.println("--------SAVED MOVIES--------");

        ArrayList<Movie>savedMovies = user.getSavedMovies();

        if(savedMovies.isEmpty()){
            System.out.println("Your saved movies list is empty");

        }else {
            for(Movie movie : savedMovies) {
                System.out.println(movie);
            }
        }

    }

    private void showSeenMovies() {
        System.out.println("\n--------SEEN MOVIES--------");

        ArrayList<Movie> seenMovies = user.getSeenMovies();

        if (seenMovies.isEmpty()) {
            System.out.println(" You have not seen any movies yet, get busy");
        } else {

            for (Movie movie : seenMovies) {
                System.out.println(movie);
            }

        }
    }

    public void addToFavorites(Movie movie) {
        if (!favorites.contains(movie)) {

            favorites.add(movie);

            System.out.println(movie.getTitle() + " Has been added to favorites");

        } else {
            System.out.println("This movie is already on your favorites");
        }
    }

    private void showFavorites() {
        System.out.println("--------Favorites--------");

        if(favorites.isEmpty()){

            System.out.println("Your favorites are empty");

        } else {

            for (int i = 0; i < favorites.size(); i++) {
                System.out.println((i + 1) + ". " + favorites.get(i));
            }

            System.out.println("Would you like to play one of your favorite movies? (yes/no)");
            String answer = scan.nextLine().toLowerCase();

            if (answer.equals("yes")) {
                System.out.println("Choose a number:");
                try {
                    int choice = Integer.parseInt(scan.nextLine());

                    if (choice >= 1 && choice <= favorites.size()) {
                        Movie chosen = favorites.get(choice - 1);
                        startMovie(chosen);
                    } else {
                        System.out.println("There is no movie with this number");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You have to choose a number.");
                }
            }
        }



    }


}
