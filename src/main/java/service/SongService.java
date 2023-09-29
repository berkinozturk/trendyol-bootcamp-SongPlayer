package service;

import exception.NotFoundException;
import model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    private final List<Song> songs = new ArrayList<>();
    private long nextId = 1;

    public List<Song> getAllSongs() {
        return songs;
    }

    public Song getSongById(int id) {
        for (Song song : songs) {
            if (song.getId() == id) {
                return song;
            }
        }
        throw new NotFoundException("Song not found with id: " + id);
    }

    public Song addSong(Song song) {
        song.setId(nextId++);
        songs.add(song);
        return song;
    }

    public boolean deleteSong(int id) {
        Song songToRemove = null;
        for (Song song : songs) {
            if (song.getId() == id) {
                songToRemove = song;
                break;
            }
        }
        if (songToRemove != null) {
            songs.remove(songToRemove);
            return true;
        }
        throw new NotFoundException("Song not found with id: " + id);
    }

    public Song updateSong(int id, Song updatedSong) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getId() == id) {
                updatedSong.setId((long) id);
                songs.set(i, updatedSong);
                return updatedSong;
            }
        }
        throw new NotFoundException("Song not found with id: " + id);
    }
}
