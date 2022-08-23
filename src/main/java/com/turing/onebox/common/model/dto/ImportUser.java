package com.turing.onebox.common.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImportUser {
   private MultipartFile file;
   private Integer id;
}
