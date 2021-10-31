package io.github.klimenko.videouploader.tagProcessing;

import io.github.klimenko.videouploader.youtube.VideoPreset;

import java.io.File;
import java.util.List;

/**
 * Interface all TagProcessors need to implement. A TagProcessor can assume init(...) is called before any process method.
 * The init method should provide all information needed for the TagProcessor to prepare for a call to one of the process
 * methods. Note that init(...) gets a preset as one of its parameters and that the data provided in the process methods
 * may look different than the preset as other tags may have executed already
 */
public interface ITagProcessor {
    /**
     * The constructor for a TagProcessor. A TagProcessor do not need to worry about a process method being called before
     * init(...) has been called. This is a good place to do some initial processing and prepare as much as possible to
     * reduce the amount of work needed in the process methods. For example if the tag this TagProcessor is looking for
     * does not exist in the preset it can set a boolean so it returns directly in the process methods.
     *
     * @param preset        the selected VideoPreset
     * @param initialAutoNum the value in the auto num field when the apply preset button was pressed
     */
    void init(VideoPreset preset, int initialAutoNum);

    /**
     * Processes the title field of a video and replaces the tags target by this TagProcessor with new data. Note that the
     * parameter currentTitle may differ from the title in the preset as other TagProcessors may already have modified the title
     *
     * @param currentTitle the title of the VideoUpload that is currently being processed
     * @param videoFile The file for the video currently being processed
     * @return the value in currentTitle with or without modifications, depending if any of tags searched for was found
     */
    String processTitle(String currentTitle, File videoFile);

    /**
     * Processes the description field of a video and replaces the tags target by this TagProcessor with new data. Note that the
     * parameter currentDescription may differ from the description in the preset as other TagProcessors may already have modified the description
     *
     * @param currentDescription the description of the VideoUpload that is currently being processed
     * @param videoFile The file for the video currently being processed
     * @return the value in currentDescription with or without modifications, depending if any of tags searched for was found
     */
    String processDescription(String currentDescription, File videoFile);

    /**
     * Processes the VideoTags field of a video and replaces the tags target by this TagProcessor with new data. Note that the
     * parameter currentTags may differ from the VideoTags in the preset as other TagProcessors may already have modified the VideoTags
     *
     * @param currentTags the VideoTags of the VideoUpload that is currently being processed
     * @param videoFile The file for the video currently being processed
     * @return the value in currentTitle with or without modifications, depending if any of tags searched for was found
     */
    List<String> processTags(List<String> currentTags, File videoFile);

    /**
     * @return the name of this TagProcessor
     */
    String processorName();
}
