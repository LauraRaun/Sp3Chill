public class Movie {
    private String title;
    private int year;
    private String category;
    private double rating;


    public Movie(String title, int year, String category, double rating) {
        this.title = title;
        this.year = year;
        this.category = category;
        this.rating = rating;

    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return title + "( " + year + ") - " + category + " - Rating: " + rating;
    }
}
