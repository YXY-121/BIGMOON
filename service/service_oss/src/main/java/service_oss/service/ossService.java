package service_oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ossService {
    List<String> uploadFileAvatar(MultipartFile file);
    void deleteFile(String fileName);

}
