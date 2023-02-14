package com.saferent.controller;

import com.saferent.service.ContactMessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactmessage")
public class ContactMessageController {

    private ContactMessageService contactMessageService;

}
