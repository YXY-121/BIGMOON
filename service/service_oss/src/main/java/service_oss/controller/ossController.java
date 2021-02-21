package service_oss.controller;

import com.zongce.serviceBase.resultCode.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import service_oss.service.ossService;

import java.util.List;

@RestController
@CrossOrigin
public class ossController {
//    @Autowired
//    ossService ossService;
//    @PostMapping("uploadPicture")
//    public ResultUtils uploadPicture(MultipartFile file){
////        List<String> strings = ossService.uploadFileAvatar(file);
//        return ResultUtils.OK().data("地址",strings.get(0));
//    }
//
//    @PostMapping("deletePicture")
//    public ResultUtils deletePicture(String pictureUrl){
//    ossService.deleteFile(pictureUrl);
//
//        return ResultUtils.OK();
//    }
}
