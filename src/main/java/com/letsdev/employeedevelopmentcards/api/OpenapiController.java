package com.letsdev.employeedevelopmentcards.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsdev.employeedevelopmentcards.util.VersionUtilKt;
import lombok.SneakyThrows;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class OpenapiController {

    private final String openapiYaml;
    private final String openapiJson;

    @SneakyThrows
    public OpenapiController(ObjectMapper objectMapper,
                             ResourceLoader resourceLoader,
                             Yaml yaml) {
        Map<String, Object> openapiSpec = yaml.load(
                resourceLoader.getResource("classpath:/openapi/openapi.yaml").getInputStream());
        var openapiYaml = populateVersion(openapiSpec);
        this.openapiYaml = yaml.dump(openapiYaml);
        this.openapiJson = objectMapper.writeValueAsString(openapiYaml);
    }

    @GetMapping("/")
    public String openapiUiRedirect() {
        return "redirect:openapi.json";
    }

    @ResponseBody
    @GetMapping(value = {"/openapi.yaml", "/openapi.yml"}, produces = "text/yaml")
    public String openapiYaml() {
        return openapiYaml;
    }

    @ResponseBody
    @GetMapping("/openapi.json")
    public String openapiJson() {
        return openapiJson;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> populateVersion(Map<String, Object> openapiYaml) {
        if (openapiYaml.containsKey("info")) {
            var info = (Map<String, Object>) openapiYaml.get("info");
            info.put("version", VersionUtilKt.getAppVersion());
        }
        return openapiYaml;
    }

}
