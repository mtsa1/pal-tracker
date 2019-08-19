package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.HashMap;

@RestController
public class EnvController {


    private String port;
    private String memLimit;
    private String instanceIndex;
    private String instanceAddr;

    public EnvController(
            @Value("${port: SET}") String port,
            @Value("${memory.limit:NOT SET}") String memLimit,
            @Value("${cf.instance.index:NOT SET}") String instanceIndex,
            @Value("${cf.instance.addr:NOT SET}") String instanceAddr) {
        this.port = port;
        this.memLimit = memLimit;
        this.instanceIndex = instanceIndex;
        this.instanceAddr = instanceAddr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv(){
        Map<String, String> environments = Map.of("PORT", port,"MEMORY_LIMIT",
                memLimit,"CF_INSTANCE_INDEX", instanceIndex, "CF_INSTANCE_ADDR", instanceAddr);

        return environments;
    }
}
