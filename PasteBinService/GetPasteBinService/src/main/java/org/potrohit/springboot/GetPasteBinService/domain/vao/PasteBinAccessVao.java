package org.potrohit.springboot.GetPasteBinService.domain.vao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasteBinAccessVao {
    private String pasteBinId;
    private String accessURL;
}
