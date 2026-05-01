package businessservice.service;

import businessservice.model.Song;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GraduationPartyPlaylistServiceTest {

    private final GraduationPartyPlaylistService service = new GraduationPartyPlaylistService();

    @Test
    void can_prepare_playlist() {
        Song song1_1 = new Song("song1_1", "artist1", "duplicate");
        Song song1_2 = new Song("song1_2", "artist1", "genre2");
        Song song2 = new Song("song2", "artist2", "genre2");
        Song song3_1 = new Song("song1_1", "artist1", "duplicate"); // Duplicate
        Song song3_2 = new Song("song3_2", "artist3", "genre4");
        Song song4_1 = new Song("song4_1", "artist4", "genre4");
        Song song4_2 = new Song("song4_2", "artist4", "genre5");
        Song song4_3 = new Song("song4_3", "artist4", "genre5");
        Song song4_4 = new Song("song4_4", "artist5", "genre5");
        Song song4_5 = new Song("song4_5", "artist5", "genre5");

        List<List<Song>> providedPlaylists = List.of(
                List.of(song1_1, song1_2),
                List.of(song2),
                List.of(song3_1, song3_2),
                List.of(song4_1, song4_2, song4_3, song4_4, song4_5)
        );

        var prepared = service.preparePlaylist(providedPlaylists);

        assertThat(prepared).isNotNull();
        assertThat(prepared).isNotEmpty();
        assertThat(prepared.get("genre2").size()).isEqualTo(2);
        assertThat(prepared.get("genre4").size()).isEqualTo(2);
        assertThat(prepared.get("genre5").size()).isEqualTo(4);
        assertThat(prepared.get("duplicate").size()).isEqualTo(1);
        assertThat(prepared.get("genre5").getFirst().artist()).isEqualTo("artist4");
        assertThat(prepared.get("genre5").get(1).artist()).isEqualTo("artist5");
        assertThat(prepared.get("genre5").get(2).artist()).isEqualTo("artist4");
        assertThat(prepared.get("genre5").get(3).artist()).isEqualTo("artist5");
    }
}
