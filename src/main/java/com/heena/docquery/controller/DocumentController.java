package com.heena.docquery.controller;

import com.heena.docquery.dto.DocumentResponseDTO;
import com.heena.docquery.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

    private final DocumentService documentService;

    /*
        TODO: change to async processing
     */
    @PostMapping("/upload")
    public ResponseEntity<DocumentResponseDTO> upload(
            @RequestParam("file") MultipartFile file
    ) {
        log.info("Received document upload request: {}", file.getOriginalFilename());

        DocumentResponseDTO response = documentService.process(file);

        /*
            TODO: add an API response DTO to be returned
         */
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
