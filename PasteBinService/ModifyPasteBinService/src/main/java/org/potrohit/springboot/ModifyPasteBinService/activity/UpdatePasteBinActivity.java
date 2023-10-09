package org.potrohit.springboot.ModifyPasteBinService.activity;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.ConfirmPasteBinRequest;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.PasteBinResponse;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.RouteMappings;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.UpdatePasteBinRequest;
import org.potrohit.springboot.ModifyPasteBinService.domain.bussinesslogic.UpdatePasteBinService;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinAccessVao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping(RouteMappings.UPDATE_ACTIVITY)
public class UpdatePasteBinActivity {

    private final UpdatePasteBinService updatePasteBinService;

    @Autowired
    public UpdatePasteBinActivity(UpdatePasteBinService updatePasteBinService) {
        this.updatePasteBinService = updatePasteBinService;
    }

    @RequestMapping(value = RouteMappings.UPDATE, method = RequestMethod.PUT)
    public @ResponseBody PasteBinResponse getPasteBin(@RequestBody UpdatePasteBinRequest updatePasteBinRequest) {
        log.info("UpdatePasteBinActivity/getPasteBin: Triggered /register PUT API Call");
        log.info("UpdatePasteBinActivity/getPasteBin: Calling UpdatePasteBinService to update PasteBin");
        PasteBinAccessVao pasteBinAccessVao = updatePasteBinService.updatePasteBin(updatePasteBinRequest.toPasteBinVao());
        log.info("UpdatePasteBinActivity/getPasteBin: Updated PasteBin by calling UpdatePasteBinService");

        log.info("UpdatePasteBinActivity/getPasteBin: Converting PasteBinAccessVao to response body");
        PasteBinResponse pasteBinResponse = PasteBinResponse.fromPasteBinAccessVao(pasteBinAccessVao);
        return pasteBinResponse;
    }

    @RequestMapping(value = RouteMappings.UPDATE_CONFIRM, method = RequestMethod.PUT)
    public @ResponseBody PasteBinResponse confirmRegisterPasteBin(@RequestBody ConfirmPasteBinRequest confirmPasteBinRequest) {
        log.info("UpdatePasteBinActivity/confirmRegisterPasteBin: Triggered /register Post API Call");
        log.info("UpdatePasteBinActivity/confirmRegisterPasteBin: Calling RegisterPasteBinService to register PasteBin");
        PasteBinAccessVao pasteBinAccessVao = updatePasteBinService.confirmUpdatePasteBin(confirmPasteBinRequest.toPasteBinVao());
        log.info("UpdatePasteBinActivity/confirmRegisterPasteBin: Registering Paste Bin completed by calling RegisterPasteBinService");

        log.info("UpdatePasteBinActivity/confirmRegisterPasteBin: Converting PasteBinAccessVao to response body");
        PasteBinResponse pasteBinResponse = PasteBinResponse.fromPasteBinAccessVao(pasteBinAccessVao);
        return pasteBinResponse;
    }
}
