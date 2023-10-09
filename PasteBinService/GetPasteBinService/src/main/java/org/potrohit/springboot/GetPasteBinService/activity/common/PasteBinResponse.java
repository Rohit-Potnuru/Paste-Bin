package org.potrohit.springboot.GetPasteBinService.activity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinAccessVao;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasteBinResponse
{
    public String pasteBinId;
    public String accessURL;

    public static PasteBinResponse fromPasteBinAccessVao(PasteBinAccessVao pasteBinAccessVao) {
        return PasteBinResponse.builder()
                .pasteBinId(pasteBinAccessVao.getPasteBinId())
                .accessURL(pasteBinAccessVao.getAccessURL())
                .build();
    }
}