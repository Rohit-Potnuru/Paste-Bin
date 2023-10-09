package org.potrohit.springboot.ModifyPasteBinService.activity;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.ConfirmPasteBinRequest;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.PasteBinResponse;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.RegisterPasteBinRequest;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.RouteMappings;
import org.potrohit.springboot.ModifyPasteBinService.domain.bussinesslogic.RegisterPasteBinService;
import org.potrohit.springboot.ModifyPasteBinService.domain.ports.PasteBinDataStore;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinAccessVao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping(RouteMappings.REGISTER_ACTIVITY)
public class RegisterPasteBinActivity {

    private final RegisterPasteBinService registerPasteBinService;

    @Autowired
    public RegisterPasteBinActivity(RegisterPasteBinService registerPasteBinService) {
        this.registerPasteBinService = registerPasteBinService;
    }

    @RequestMapping(value = RouteMappings.REGISTER, method = RequestMethod.POST)
    public @ResponseBody PasteBinResponse registerPasteBin(@RequestBody RegisterPasteBinRequest registerPasteBinRequest) {
        log.info("RegisterPasteBinActivity/registerPasteBin: Triggered /register Post API Call");
        log.info("RegisterPasteBinActivity/registerPasteBin: Calling RegisterPasteBinService to register PasteBin");
        PasteBinAccessVao pasteBinAccessVao = registerPasteBinService.registerPasteBin(registerPasteBinRequest.toPasteBinVao());
        log.info("RegisterPasteBinActivity/registerPasteBin: Registering Paste Bin completed by calling RegisterPasteBinService");

        log.info("RegisterPasteBinActivity/registerPasteBin: Converting PasteBinAccessVao to response body");
        PasteBinResponse pasteBinResponse = PasteBinResponse.fromPasteBinAccessVao(pasteBinAccessVao);
        return pasteBinResponse;
    }

    @RequestMapping(value = RouteMappings.REGISTER_CONFIRM, method = RequestMethod.POST)
    public @ResponseBody PasteBinResponse confirmRegisterPasteBin(@RequestBody ConfirmPasteBinRequest confirmPasteBinRequest) {
        log.info("RegisterPasteBinActivity/confirmRegisterPasteBin: Triggered /register Post API Call");
        log.info("RegisterPasteBinActivity/confirmRegisterPasteBin: Calling RegisterPasteBinService to register PasteBin");
        PasteBinAccessVao pasteBinAccessVao = registerPasteBinService.confirmRegisterPasteBin(confirmPasteBinRequest.toPasteBinVao());
        log.info("RegisterPasteBinActivity/confirmRegisterPasteBin: Registering Paste Bin completed by calling RegisterPasteBinService");

        log.info("RegisterPasteBinActivity/confirmRegisterPasteBin: Converting PasteBinAccessVao to response body");
        PasteBinResponse pasteBinResponse = PasteBinResponse.fromPasteBinAccessVao(pasteBinAccessVao);
        return pasteBinResponse;
    }

}