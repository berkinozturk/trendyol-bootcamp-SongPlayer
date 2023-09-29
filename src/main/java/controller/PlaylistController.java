package controller;

import model.Playlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("api/v1/playlists")
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final List<Playlist> playlist = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists(){
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Playlist> addPlaylist(@RequestBody Playlist playlist){
        if(playlist == null ||playlist.getName() == null || playlist.getSongList() == null ){

            return new ResponseEntity<>(playlist, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(playlist, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Playlist> deletePlaylist(@PathVariable int id){
        if(id < 0 || id >= playlist.size()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlist.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable int id, @RequestBody Playlist updatedplaylist){
        if(id < 0 || id >= playlist.size()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Playlist playlistToUpdate = playlist.get(id);

        if (updatedplaylist == null || updatedplaylist.getSongList() == null || updatedplaylist.getName()==null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        playlistToUpdate.setName(updatedplaylist.getName());
        playlistToUpdate.setSongList(updatedplaylist.getSongList());

        return new ResponseEntity<>(playlistToUpdate, HttpStatus.OK);
    }
}
