package org.potrohit.springboot.ModifyPasteBinService.activity;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.ModifyPasteBinService.activity.common.*;
import org.potrohit.springboot.ModifyPasteBinService.domain.bussinesslogic.DeletePasteBinService;
import org.potrohit.springboot.ModifyPasteBinService.domain.bussinesslogic.UpdatePasteBinService;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinAccessVao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping(RouteMappings.DELETE_ACTIVITY)
public class DeletePasteBinActivity {

    private final DeletePasteBinService deletePasteBinService;

    @Autowired
    public DeletePasteBinActivity(DeletePasteBinService deletePasteBinService) {
        this.deletePasteBinService = deletePasteBinService;
    }

    @RequestMapping(value = RouteMappings.DELETE, method = RequestMethod.DELETE)
    public @ResponseBody PasteBinResponse deletePasteBin(@RequestBody DeletePasteBinRequest deletePasteBinRequest) {
        log.info("DeletePasteBinActivity/deletePasteBin: Triggered delete DELETE API Call");
        log.info("DeletePasteBinActivity/deletePasteBin: Calling DeletePasteBinService to delete PasteBin");
        PasteBinAccessVao pasteBinAccessVao = deletePasteBinService.deletePasteBin(deletePasteBinRequest.toPasteBinVao());
        log.info("DeletePasteBinActivity/deletePasteBin: Deleted PasteBin by calling DeletePasteBinService");

        log.info("DeletePasteBinActivity/deletePasteBin: Converting PasteBinAccessVao to response body");
        PasteBinResponse pasteBinResponse = PasteBinResponse.fromPasteBinAccessVao(pasteBinAccessVao);
        return pasteBinResponse;
    }
}
