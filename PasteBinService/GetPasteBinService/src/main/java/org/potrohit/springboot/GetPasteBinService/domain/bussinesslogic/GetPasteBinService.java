package org.potrohit.springboot.GetPasteBinService.domain.bussinesslogic;

import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinAccessVao;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinVao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetPasteBinService {
    public PasteBinAccessVao getPasteBin(PasteBinVao PasteBinVao);
    public List<PasteBinAccessVao> getUserPasteBin(PasteBinVao PasteBinVao);
}
