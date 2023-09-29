package service;

import exception.NotFoundException;
import model.Playlist;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class PlaylistService {

    private final List<Playlist> playlists = new ArrayList<>();
    private long nextId = 1;

    public List<Playlist> getAllPlaylists() {
        return playlists;
    }

    public Playlist getPlaylistById(int id) {
        for (Playlist playlist : playlists) {
            if (playlist.getId() == id) {
                return playlist;
            }
        }
        throw new NotFoundException("Playlist not found with id: " + id);

    }

    public Playlist addPlaylist(Playlist playlist) {
        playlist.setId(nextId++);
        playlists.add(playlist);
        return playlist;
    }

    public boolean deletePlaylist(int id) {
        Playlist playlistToRemove = null;
        for (Playlist playlist : playlists) {
            if (playlist.getId() == id) {
                playlistToRemove = playlist;
                break;
            }
        }
        if (playlistToRemove != null) {
            playlists.remove(playlistToRemove);
            return true;
        }
        throw new NotFoundException("Playlist not found with id: " + id);

    }

    public Playlist updatePlaylist(int id, Playlist updatedPlaylist) {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getId() == id) {
                updatedPlaylist.setId((long) id);
                playlists.set(i, updatedPlaylist);
                return updatedPlaylist;
            }
        }
        throw new NotFoundException("Playlist not found with id: " + id);

    }
}
