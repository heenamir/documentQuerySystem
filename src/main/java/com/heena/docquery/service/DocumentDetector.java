package com.heena.docquery.service;

import org.springframework.web.multipart.MultipartFile;
import com.heena.docquery.model.DocumentType;

public interface DocumentDetector {
    DocumentType detect(MultipartFile file);
}
