public class Song {
    private int index;
    private String name;
    private String artist;
    private int id;


    private String genre;
    private int year;

    public Song(String name, String artist, int id, String genre,int year){
        this.name = name;
        this.artist = artist;
        this.id = id;
        this.genre = genre;
        this.year = year;

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString(){
        return name + "-"  + artist + "(" + genre + "," + year +")";
    }

}
