package com.saferent.service;

import com.saferent.repository.*;
import org.springframework.stereotype.*;

@Service
public class ImageFileService {

    private final ImageFileRepository imageFileRepository;

    public ImageFileService(ImageFileRepository imageFileRepository) {
        this.imageFileRepository = imageFileRepository;
    }
}
