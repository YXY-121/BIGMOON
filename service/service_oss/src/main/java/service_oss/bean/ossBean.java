package service_oss.bean;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ossBean implements InitializingBean {
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    @Value("${aliyun.oss.file.keyid}")
    private String accessKeyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    public static String ENDPOINT;
    public static String ACCESSKEYID;
    public static String ACCESSKEYSECRET;
    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = this.endpoint;
        ACCESSKEYID = this.accessKeyId;
        ACCESSKEYSECRET = this.accessKeySecret;
        BUCKETNAME = this.bucketName;
    }

    /*    为了让private成员被访问到可以imlInitializingBean*/

}
