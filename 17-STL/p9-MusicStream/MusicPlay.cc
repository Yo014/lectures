#include <string>
#include <iostream>
#include <iomanip>
#include <vector>
#include <algorithm>
using namespace std;

class Song{	
    friend ostream& operator<<(ostream&, const Song&);
    public:
       Song(const string& t, const string& a): title(t), artist(a){}
       
       const string& getArtist()const {return artist;}
       const string& getTitle() const{return title;}

       void play();
    
    private:
       string title;
       string artist;
};

ostream& operator<<(ostream& ost, const Song& song){
    ost << "Artist: "<<song.artist<<endl;
    ost << "Title:  "<<song.title<<endl;
    return ost;
}


class MusicPlay{
    public:
        MusicPlay();   
        ~MusicPlay();                 
        
        // make a Song and add it in alphabetical order by title
        void addSong(const string& title, const string& artist);

        // get the Song with the title given
        bool getSong(const string& title, Song**) const;

        // remove the Song with the title given
        bool removeSong(const string& title, Song**);
        
        // add all songs by the given artist to songs, and sort by title
        void getAllSongsBy(const string& artist, vector<Song*>& byArtist) const; 

    private:
        vector<Song*> songs;
        
};	


MusicPlay::MusicPlay(){
   
}

MusicPlay::~MusicPlay(){    
    for (Song* s: songs){
        delete s;
    }
}

void MusicPlay::addSong(const string& title, const string& artist){
    songs.push_back(new Song(title, artist));
}


bool MusicPlay::getSong(const string& title, Song** song) const {
    vector<Song*>::const_iterator itr = find_if(songs.begin(), songs.end(), [&title](Song* s){return s->getTitle() == title;});
    if (itr == songs.end())return false;

    *song = *itr;
    return true;
}

bool MusicPlay::removeSong(const string& title, Song** song)  {
    if (!getSong(title, song)){ return false;}
    vector<Song*>::iterator itr = remove_if(songs.begin(), songs.end(), [&title](Song* s){return s->getTitle() == title;});
    return true;
}

void MusicPlay::getAllSongsBy(const string& artist, vector<Song*>& byArtist) const{
   copy_if(songs.begin(), songs.end(), back_inserter(byArtist), [&artist](Song* s){return s->getArtist() == artist;});
    
    if (byArtist.size() == 0){
        cout<<"No songs by that artist found"<<endl;
        return;
    }
    //sort songs alphabetical by title
    sort(byArtist.begin(), byArtist.end(), [](Song* s1, Song* s2){
        return s1->getTitle() < s2->getTitle();
    });
}


int main(){
    MusicPlay ms;

    ms.addSong("My Hero", "Foo Fighters");
    ms.addSong("Big Me", "Foo Fighters");
    ms.addSong("Learn to Fly", "Foo Fighters");
    ms.addSong("Run", "Foo Fighters");
    ms.addSong("Staying Alive", "The BeeGees");
    ms.addSong("Mamma Mia", "ABBA");

    Song* song;

    ms.getSong("Run", &song);
    cout <<*song <<endl;

    vector<Song*> foo;
    ms.getAllSongsBy("Foo Fighters", foo);

    for (Song* song: foo){
        cout<<*song<<endl;
    }

    
}
