import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project2 {



    static ArrayList<Song> songList = new ArrayList<>();
    static AVLTree<String> artistTree = new AVLTree<>();
    static AVLTree<String> nameTree = new AVLTree<>();
    static AVLTree<Integer> idTree = new AVLTree<>();
    public static void main(String[] args) throws IOException {

        Scanner kb = new Scanner(System.in, "ISO-8859-9");
        File f = new File("songs.txt");

        Scanner sc = new Scanner(f);
        int index = 0;

        while(sc.hasNext()){
            String songtxt = sc.nextLine();
            String[] songArray = songtxt.split(";");

            Song song = new Song(songArray[0],songArray[1],Integer.parseInt(songArray[2]),songArray[3],Integer.parseInt(songArray[4]));
            song.setIndex(++index);
            songList.add(song);
            nameTree.insert(song.getName(), song.getIndex());
            idTree.insert(song.getId(), song.getIndex());
            artistTree.insert(song.getArtist(), song.getIndex());

        }
        sc.close();

        System.out.println("Reading file...");
        int opt = 0;
        while (opt != 8){
            System.out.println();
            System.out.println("1)Search song by name");
            System.out.println("2)Search song by id");
            System.out.println("3)Search song by artist");
            System.out.println("4)Search song by id range");
            System.out.println("5)Search song by genre");
            System.out.println("6)Add a new song");
            System.out.println("7)Delete a song");
            System.out.println("8)Exit");
            System.out.print("Enter an option: ");
            opt = Integer.parseInt(kb.nextLine());
            if (opt == 1){
                System.out.print("Enter song name: ");
                String name = kb.nextLine();
                System.out.println(songList.get(nameTree.search(byName(name)) - 1));
            }else if (opt == 2){
                System.out.print("Enter song id: ");
                int id = Integer.parseInt(kb.nextLine());
                System.out.println(songList.get(idTree.search(byId(id)) - 1));
            }else if (opt == 3){
                System.out.print("Enter artist name: ");
                String artist = kb.nextLine();
                byArtist(artist);
            }else if (opt == 4){
                System.out.print("Enter first id: ");
                int fid = Integer.parseInt(kb.nextLine());
                System.out.print("Enter second id: ");
                int sid = Integer.parseInt(kb.nextLine());
                byIdRange(fid,sid);
            }else if (opt == 5){
                System.out.print("Enter genre: ");
                String genre = kb.nextLine();
                listGenre(genre);
            }else if (opt == 6){
                System.out.print("Enter song name: ");
                String name = kb.nextLine();
                System.out.print("Enter artist name: ");
                String artist = kb.nextLine();
                System.out.print("Enter id: ");
                int id = Integer.parseInt(kb.nextLine());
                System.out.print("Enter genre: ");
                String genre = kb.nextLine();
                System.out.print("Enter year: ");
                int year = Integer.parseInt(kb.nextLine());


                Song song = new Song(name,artist,id,genre,year);
                song.setIndex(++index);
                songList.add(song);
                nameTree.insert(song.getName(), song.getIndex());
                idTree.insert(song.getId(), song.getIndex());
                artistTree.insert(song.getArtist(), song.getIndex());

                System.out.println(song);
            }else if (opt == 7){
                System.out.print("Enter song id: ");
                int id = Integer.parseInt(kb.nextLine());
                deleteAndChangeIndex(id);
                --index;
            }else if (opt == 8){
                break;
            }
        }
    }

    public static int byName(String name){
        for (Song n : songList)
            if (n.getName().equalsIgnoreCase(name))
                return n.getIndex();

        throw new java.util.NoSuchElementException();
    }

    public static int byId(int id){
        for (Song n : songList)
            if (n.getId() == id)
                return n.getIndex();

        throw new java.util.NoSuchElementException();
    }

    public static void byIdRange(int fid , int sid){
        for (Song n : songList)
            if (n.getId() >= fid && n.getId() <= sid)
                System.out.println(songList.get(idTree.search(n.getIndex()) - 1));


    }

    public static void byArtist(String artist){
        for (Song n : songList)
            if (n.getArtist().equalsIgnoreCase(artist))
                System.out.println(songList.get(artistTree.search(n.getIndex()) - 1));
    }

    public static void deleteAndChangeIndex(int id){
        int key = byId(id) - 1;
        for (Song n : songList)
            if (n.getIndex() > key)
                n.setIndex(n.getIndex() - 1);
        songList.remove(key);
        nameTree.deleteNode(nameTree.root, key);
        idTree.deleteNode(idTree.root, key);
        artistTree.deleteNode(artistTree.root, key);

    }

    public static void listGenre(String genre){
        for (Song n : songList)
            if (n.getGenre().equalsIgnoreCase(genre))
                System.out.println(n);

    }
}
