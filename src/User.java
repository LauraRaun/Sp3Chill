import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Movie>savedMovies;
    private ArrayList<Movie>seenMovies;

    public User(String username, String password){
        this.username = username;
        this.password = password;

        savedMovies = new ArrayList<>();
        seenMovies = new ArrayList<>();
    }

    public String getUsername(){
        return username;
    }

    public boolean checkPassword (String password) {
        return this.password.equals(password);
    }

    public void saveMovie (Movie movie) {
        if(!savedMovies.contains(movie)) {
            savedMovies.add(movie);
        }
    }

    public void addSeenMovie (Movie movie) {
        if (!seenMovies.contains(movie)) {
            seenMovies.add(movie);
        }
    }
    public ArrayList<Movie> getSavedMovies() {
            return savedMovies;
        }

    public ArrayList<Movie> getSeenMovies() {
            return seenMovies;
        }
    }


}
