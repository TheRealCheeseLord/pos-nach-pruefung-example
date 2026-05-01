package businessservice.service;

import businessservice.model.Song;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraduationPartyPlaylistService {

    public Map<String, List<Song>> preparePlaylist(
            List<List<Song>> studentPlaylists
    ) {
        return studentPlaylists.stream()
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.groupingBy(
                        Song::genre,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                this::shuffle
                        )
                ));
    }

    record IndexedSong(int index, Song song) {}

    private List<Song> shuffle(List<Song> songs) {
        return songs.stream()
                .collect(Collectors.groupingBy(Song::artist))
                        .values().stream().flatMap(artistSongs ->
                            IntStream.range(0, artistSongs.size()).mapToObj(i -> new IndexedSong(i, artistSongs.get(i)))
                )
                .sorted(Comparator.comparing(IndexedSong::index))
                .map(IndexedSong::song)
                .collect(Collectors.toList());
    }
}
