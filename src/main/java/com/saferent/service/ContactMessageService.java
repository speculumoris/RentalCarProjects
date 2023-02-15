package com.saferent.service;

import com.saferent.domain.ContactMessage;
import com.saferent.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {
    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }


    public void saveMessage(ContactMessage contactMessage) {
        contactMessageRepository.save(contactMessage);
    }

    public List<ContactMessage> getAll() {
        return contactMessageRepository.findAll();

    }
}

