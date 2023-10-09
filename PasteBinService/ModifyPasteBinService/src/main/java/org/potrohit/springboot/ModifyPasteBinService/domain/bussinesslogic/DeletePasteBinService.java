package org.potrohit.springboot.ModifyPasteBinService.domain.bussinesslogic;

import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinAccessVao;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinVao;
import org.springframework.stereotype.Service;

@Service
public interface DeletePasteBinService {
    public PasteBinAccessVao deletePasteBin(PasteBinVao PasteBinVao);
}
