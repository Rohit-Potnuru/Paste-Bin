package org.potrohit.springboot.RegisterService.domain.bussinesslogic;

import org.potrohit.springboot.RegisterService.activity.common.ConfirmPasteBinRequest;
import org.potrohit.springboot.RegisterService.activity.common.RegisterPasteBinRequest;
import org.potrohit.springboot.RegisterService.activity.common.ModifyPasteBinRequest;
import org.potrohit.springboot.RegisterService.activity.common.PasteBinResponse;

public interface UpdatePasteBinService {

    public PasteBinResponse registerPasteBin(RegisterPasteBinRequest registerPasteBinRequest);

    public PasteBinResponse confirmPasteBin(ConfirmPasteBinRequest confirmPasteBinRequest);

    public PasteBinResponse modifyPasteBin(ModifyPasteBinRequest modifyPasteBinRequest);

    public PasteBinResponse deletePasteBin(ModifyPasteBinRequest deletePasteBinRequest);
}
