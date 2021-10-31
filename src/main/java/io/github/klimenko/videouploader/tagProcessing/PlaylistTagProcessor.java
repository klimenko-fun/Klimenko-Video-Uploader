package io.github.klimenko.videouploader.tagProcessing;

import io.github.klimenko.videouploader.youtube.VideoPreset;
import io.github.klimenko.videouploader.youtube.utils.PlaylistUtils;

import java.io.File;
import java.util.List;

/**
 * Locates the $(playlist) tag in video descriptions and replaces them with the URL to the playlist set in the preset
 */
public class PlaylistTagProcessor implements ITagProcessor {
    private String playlistUrl;
    private boolean tagFound;
    private final String PLAYLIST_TAG = "$(playlist)";

    public PlaylistTagProcessor() {
    }

    public void init(VideoPreset preset, int initialAutoNum) {
        tagFound = preset.getVideoDescription().contains(PLAYLIST_TAG);
        if (!tagFound)
            return;
        PlaylistUtils playlistUtils = PlaylistUtils.INSTANCE;
        playlistUrl = playlistUtils.getPlaylistUrl(preset.getSelectedPlaylist());
        if (playlistUrl == null) {
            playlistUrl = "";
        }
    }

    @Override
    public String processTitle(String currentTitle, File videoFile) {
        return currentTitle;
    }

    @Override
    public String processDescription(String currentDescription, File videoFile) {
        if (tagFound)
            return currentDescription.replace(PLAYLIST_TAG, playlistUrl);
        else
            return currentDescription;
    }

    @Override
    public List<String> processTags(List<String> currentTags, File videoFile) {
        return currentTags;
    }

    @Override
    public String processorName() {
        return "Playlist TagProcessor";
    }
}
