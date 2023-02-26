package com.saferent.controller;

import com.saferent.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
public class ImageFileController {

    private final ImageFileService imageFileService;

    public ImageFileController(ImageFileService imageFileService) {
        this.imageFileService = imageFileService;
    }
}
