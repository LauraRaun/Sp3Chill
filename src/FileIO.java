import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileIO {

    public ArrayList<Movie> readMoviesFromFile(String fileName) throws IOException {
        ArrayList<Movie> movies = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName), StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(";");

                if (parts.length >= 4) {

                    String title = parts[0].trim();
                    int year = Integer.parseInt(parts[1].trim());
                    String category = parts[2].trim();

                    double rating = Double.parseDouble(parts[3].trim().replace(",", "."));

                    Movie movie = new Movie(title, year, category, rating);

                    movies.add(movie);
                }

            }

            return movies;

        }
    }
}
