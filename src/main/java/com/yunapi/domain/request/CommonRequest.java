package com.yunapi.domain.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Data
@Component
public class CommonRequest {
    @NotNull
    private Long id;
    private String module;
    private Long sId;
    private String sToken;

}
