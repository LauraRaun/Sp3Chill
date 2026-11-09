import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    private ArrayList<Movie> movies;
    private ArrayList<Movie> favorites;
    private Scanner scan;

    public TextUI(ArrayList<Movie> movies, Scanner scan) {
        this.movies = movies;
        this.favorites = new ArrayList<>();
        this.scan = scan;
    }

    public void start() {
        boolean running = true;

        while (running) {

            System.out.println("--------CHILL--------");
            System.out.println("1. Show all the movies ");
            System.out.println("2. Show favorites ");
            System.out.println("3. Close CHILL");
            System.out.println("Show an option: ");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    showMovies();
                    break;
                case "2":
                    showFavorites();
                    break;
                case "3":
                    running = false;
                    System.out.println("You chose to close CHILL, thanks for watching");
                    break;

                default:
                    System.out.println("That's not a option, try again ");
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
            if (choice == 3) {
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

    private void selectedMovie(Movie movie) {
        System.out.println("\n You have chosen: " + movie.getTitle());
        System.out.println("1. Start the movie");
        System.out.println("2. Add to favorites");
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

            case "0":
                break;

            default:
                System.out.println("Error");
        }

    }

    public void startMovie(Movie movie) {

        System.out.println(movie.getTitle() + "starts now...");
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
