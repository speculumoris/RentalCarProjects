package com.saferent.controller;

import com.saferent.domain.ContactMessage;
import com.saferent.dto.ContactMessageDTO;
import com.saferent.dto.request.ContactMessageRequest;
import com.saferent.dto.response.SfResponse;
import com.saferent.mapper.*;
import com.saferent.service.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contactmessage")
public class ContactMessageController {

    //@Autowired // field injection yapmamak için commente alındı
    private final ContactMessageService contactMessageService;

    private final ContactMessageMapper contactMessageMapper;

    //@Autowired
    public ContactMessageController(ContactMessageService contactMessageService,
                                    ContactMessageMapper contactMessageMapper) {
        this.contactMessageService = contactMessageService;
        this.contactMessageMapper = contactMessageMapper;
    }

    @PostMapping("/visitors")
    public ResponseEntity<SfResponse> createMessage(@Valid @RequestBody ContactMessageRequest contactMessageRequest){
        // bana gelen DTO yu POJO ya çevirmek için mapStruct yapısını kullanacağım
        ContactMessage contactMessage =
                contactMessageMapper.contactMessageRequestToContactMessage(contactMessageRequest);
        contactMessageService.saveMessage(contactMessage);

        SfResponse response = new SfResponse("ContactMessage successfully created", true);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    // getAll ContactMessages
    @GetMapping
    public ResponseEntity<List<ContactMessageDTO>> getAllContactMessage(){
       List<ContactMessage> contactMessageList= contactMessageService.getAll();

       List<ContactMessageDTO>contactMessageDTOList=contactMessageMapper.map(contactMessageList);
        return ResponseEntity.ok(contactMessageDTOList);
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<ContactMessageDTO>> getAllContactMessageWithPage(@RequestParam){

    }


 }