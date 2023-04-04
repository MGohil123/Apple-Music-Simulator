//Malvika Gohil
//501178814
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				//make index = 0
				int index = 0;
				
				System.out.print("Store Content #: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				AudioContent content = store.getContent(index);
				if (content == null)
					System.out.println("Content Not Found in Store");
				else if (!mylibrary.download(content))
						System.out.println(mylibrary.getErrorMessage());
									
			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				// Print error message if the song doesn't exist in the library
				int idx = 0;
				System.out.println(" Song Number: ");
				if (scanner.hasNextInt())
				{
					// the index of the song 
					idx = scanner.nextInt();
					scanner.nextLine(); 
				}
				//if the index is not valid then it prints an error message 
				if (!mylibrary.playSong(idx))
				{
					System.out.println(mylibrary.getErrorMessage());
				}

			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
			// Print error message if the book doesn't exist in the library
				int idx = 0;
				System.out.print("AudioBook Number: ");
				if (scanner.hasNextInt())
				{
					// the index of the song 
					idx = scanner.nextInt();
					scanner.nextLine(); 
				}
				//if the index is not valid then it prints an error message 
				if (!mylibrary.printAudioBookTOC(idx))
				{
					System.out.println(mylibrary.getErrorMessage());
				}

			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int numofBook = 0;
				int bookChapter = 0;
				System.out.println("AudioBook NUmber: ");
				if(scanner.hasNextInt())
				{
					//gets the index of the book 
					numofBook = scanner.nextInt();
					scanner.nextLine();
				}
				System.out.println("Chapter: ");
				if(scanner.hasNextInt())
				{
					//gets the index of the book 
					bookChapter = scanner.nextInt();
					scanner.nextLine();
				}
				//if the index is invalid then it prints an error message 
				if (!mylibrary.playAudioBook(numofBook, bookChapter))
				{
					System.out.println(mylibrary.getErrorMessage());
				}
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				//empty string 
				String playlistTitle = "";

				System.out.println("Playlist Title: ");

				if(scanner.hasNext())
				{
					//store playlist answer in a variable 
					playlistTitle = scanner.nextLine();
				
				}
				//if the index is invalid then it prints an error message 
				if(!mylibrary.playPlaylist(playlistTitle))
				{
					System.out.println(mylibrary.getErrorMessage());
				}
				
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String title = "";
				int number = 0;
				System.out.println("Playlist Title: ");
				if(scanner.hasNext())
				{
					title = scanner.nextLine();
				
				}
				
				
					System.out.println("Content Number");
					if(scanner.hasNextInt())
					{
						//asks user to enter conetnt number 
						number = scanner.nextInt();
						scanner.nextLine();
					}
					//if input is invalid 

					if(!mylibrary.playPlaylist(title,number))
					{
						System.out.println(mylibrary.getErrorMessage());
					}
				
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				//set index variable to 0
					int index = 0;
					System.out.println("Library Song #: ");
					if(scanner.hasNext())
					{
						//aks user to enter the library content 
						index = scanner.nextInt();
						scanner.nextLine();
					}
					//if input is invalid 
					if(!mylibrary.deleteSong(index))
					{
						System.out.println(mylibrary.getErrorMessage());
					}
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title = "";
				
				System.out.println("Playlist Title: ");
				if(scanner.hasNext())
				{
					//asks use to input title 
					title = scanner.nextLine();
				
				}
				//if the index is invalid then it prints an error message 
				if(!mylibrary.makePlaylist(title))
				{
					System.out.println(mylibrary.getErrorMessage());
				}
			
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String title = "";
				
				System.out.println("Playlist Title: ");
				if(scanner.hasNext())
				{
					
					title = scanner.nextLine();
				
				}
				//if the index is invalid then it prints an error message 
				if(!mylibrary.printPlaylist(title))
				{
					System.out.println(mylibrary.getErrorMessage());
				}
			
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				String title = "";
				String type = "";
				int index =0;
				System.out.println("Playlist Title: ");
				if(scanner.hasNext())
				{
					//stores input into a variable 
					title = scanner.nextLine();
			
				}
				System.out.println("Content Type [SONG, AUDIOBOOK]: ");
				if(scanner.hasNext())
				{
					//stores input into a variable 
					type= scanner.nextLine();
			
				}
				System.out.println("Library Content #: ");
				if(scanner.hasNextInt())
				{
					//stores input into a variable 
					index = scanner.nextInt();
					scanner.nextLine();
			
				}
				//invalid input 
				if(!mylibrary.addContentToPlaylist(type,index,title));
				{
					System.out.println(mylibrary.getErrorMessage());
				}

				
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				int index = 0;
				String title = "";
				System.out.println("Playlist Title: ");
				if(scanner.hasNext())
				{
					//stores input into a variable 
					title = scanner.nextLine();
				}
				System.out.println("Playlist Content #: ");
				if(scanner.hasNextInt())
				{
					//stores input into a variable 
					index = scanner.nextInt();
					scanner.nextLine();
				}
				//for invalid input 
				if(!mylibrary.delContentFromPlaylist(index,title))
				{
					System.out.println(mylibrary.getErrorMessage());
				}
				
				
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			System.out.print("\n>");
		}
	}
}
