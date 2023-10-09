package org.potrohit.springboot.ModifyPasteBinService.domain.bussinesslogic;

import org.potrohit.springboot.ModifyPasteBinService.activity.common.ConfirmPasteBinRequest;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.UpdatePasteBinRequest;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.PasteBinResponse;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinAccessVao;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinVao;
import org.springframework.stereotype.Service;

@Service
public interface UpdatePasteBinService {
    public PasteBinAccessVao updatePasteBin(PasteBinVao PasteBinVao);

    public PasteBinAccessVao confirmUpdatePasteBin(PasteBinVao PasteBinVa);
}
