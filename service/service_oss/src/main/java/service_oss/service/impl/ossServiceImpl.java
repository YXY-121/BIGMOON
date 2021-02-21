package service_oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service_oss.bean.ossBean;
import service_oss.service.ossService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
//@Service(interfaceClass = ossService.class,protocol = {"hessian"})
public class ossServiceImpl implements ossService {
    @Override
    public List<String> uploadFileAvatar(MultipartFile file) {
        String endpoint = ossBean.ENDPOINT;
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = ossBean.ACCESSKEYID;
        String accessKeySecret = ossBean.ACCESSKEYSECRET;
        String bucketName = ossBean.BUCKETNAME;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //获取上传文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew01.jpg
            fileName = uuid + fileName;

            //2 把文件按照日期进行分类
            //获取当前日期
            //   2019/11/12
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            //  2019/11/12/ewtqr313401.jpg
            fileName = datePath + "/" + fileName;

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            System.out.println(fileName);
            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //  https://edu-guli-1010.oss-cn-beijing.aliyuncs.com/01.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            List list =new ArrayList<>();
            list.add(url);
            list.add(fileName);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public void deleteFile(String url) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ossBean.ENDPOINT;
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = ossBean.ACCESSKEYID;
        String accessKeySecret = ossBean.ACCESSKEYSECRET;
        String bucketName = ossBean.BUCKETNAME;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, url);

// 关闭OSSClient。
        ossClient.shutdown();

    }
}
