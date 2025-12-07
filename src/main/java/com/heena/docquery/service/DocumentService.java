package com.heena.docquery.service;

import com.heena.docquery.dto.DocumentResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    DocumentResponseDTO process(MultipartFile file);
}
