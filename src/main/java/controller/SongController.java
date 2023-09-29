package controller;

import model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("api/v1/songs")
@RequestMapping("/api/songs")
public class SongController {

    private final List<Song> songList = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs(){
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Song> addSong(@RequestBody Song song){
        if(song == null ||song.getName() == null || song.getArtist() == null ){
            return new ResponseEntity<>(song, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable int id){
        if(id < 0 || id >= songList.size()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        songList.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable int id, @RequestBody Song updatedSong){
        if(id < 0 || id >= songList.size()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Song songToUpdate = songList.get(id);

        if (updatedSong == null || updatedSong.getArtist() == null || updatedSong.getName()==null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        songToUpdate.setName(updatedSong.getName());
        songToUpdate.setArtist(updatedSong.getArtist());

        return new ResponseEntity<>(songToUpdate, HttpStatus.OK);
    }
}
