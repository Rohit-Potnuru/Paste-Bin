package org.potrohit.springboot.GetPasteBinService.domain.ports;

import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinAccessVao;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinVao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PasteBinDataStore {
    public PasteBinAccessVao getPasteBin(PasteBinVao PasteBinVao);
    public List<PasteBinAccessVao> getUserPasteBin(PasteBinVao PasteBinVao);
}
