package service_oss.hessian;

import com.alibaba.dubbo.config.ProtocolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class hessianConfig {
    @Bean("hessian")
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("hessian");
        protocolConfig.setId("hessian");
        protocolConfig.setServer("servlet");
        protocolConfig.setPort(20887);

        protocolConfig.setAccepts(500);
        protocolConfig.setThreads(100);
        // 可继续增加其它配置
        return protocolConfig;
    }

}
