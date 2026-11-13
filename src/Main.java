import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FileIO fileIO = new FileIO();

        try{
            ArrayList<Movie>movies = fileIO.readMoviesFromFile("Movie.csv");
            Scanner scan = new Scanner(System.in);

            TextUI textUI = new TextUI(movies, scan);

            textUI.start();

            scan.close();

        } catch (IOException e) {

            System.out.println("Filmene kunne ikke indlæses. Tjek at filen Movie.csv ligger i projektmappen.");
            System.out.println("Programmet lukker.");
        }
    }
}
