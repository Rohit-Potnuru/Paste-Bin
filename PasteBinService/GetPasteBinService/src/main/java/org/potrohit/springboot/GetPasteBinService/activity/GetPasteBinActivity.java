package org.potrohit.springboot.GetPasteBinService.activity;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.GetPasteBinService.activity.common.GetPasteBinRequest;
import org.potrohit.springboot.GetPasteBinService.activity.common.PasteBinResponse;
import org.potrohit.springboot.GetPasteBinService.activity.common.RouteMappings;
import org.potrohit.springboot.GetPasteBinService.domain.bussinesslogic.GetPasteBinService;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinAccessVao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = RouteMappings.GET_ACTIVITY)
public class GetPasteBinActivity {

    private final GetPasteBinService getPasteBinService;

    @Autowired
    public GetPasteBinActivity(GetPasteBinService getPasteBinService) {
        this.getPasteBinService = getPasteBinService;
    }

    @RequestMapping(value = RouteMappings.GET, method = RequestMethod.GET)
    public @ResponseBody PasteBinResponse getPasteBin(@PathVariable String pasteBinId ) {
        log.info("GetPasteBinActivity/getPasteBin: Starting GET API Call for pasteBinId: " + pasteBinId);
        GetPasteBinRequest getPasteBinRequest = GetPasteBinRequest.builder()
                                                                  .pasteBinId(pasteBinId)
                                                                  .build();
        log.info("GetPasteBinActivity/getPasteBin: Triggered GET API Call for getting data based on PasteBinId");
        log.info("GetPasteBinActivity/getPasteBin: Calling GetPasteBinService to retrieve data for given PasteBinId");
        PasteBinAccessVao pasteBinAccessVao = getPasteBinService.getPasteBin(getPasteBinRequest.toPasteBinVao());
        log.info("GetPasteBinActivity/getPasteBin: Retried PasteBin data by calling GetPasteBinService");

        log.info("GetPasteBinActivity/getPasteBin: Converting PasteBinAccessVao to response body");
        PasteBinResponse pasteBinResponse = PasteBinResponse.fromPasteBinAccessVao(pasteBinAccessVao);
        log.info("GetPasteBinActivity/getPasteBin: Finshing GET API Call for pasteBinId: " + pasteBinId);
        return pasteBinResponse;
    }

    @RequestMapping(value = RouteMappings.GET_USER, method = RequestMethod.GET)
    public @ResponseBody List<PasteBinResponse> getUserPasteBin(@PathVariable String userId ) {
        log.info("GetPasteBinActivity/getUserPasteBin: Starting GET USer Data API Call for userId: " + userId);
        GetPasteBinRequest getPasteBinRequest = GetPasteBinRequest.builder()
                .userId(userId)
                .build();
        log.info("GetPasteBinActivity/getUserPasteBin: Triggered GET API Call for getting pasteBinIds data for userId " + userId);
        log.info("GetPasteBinActivity/getUserPasteBin: Calling GetPasteBinService to retrieve data for given userId");
        List<PasteBinAccessVao> pasteBinAccessVaos = getPasteBinService.getUserPasteBin(getPasteBinRequest.toPasteBinVao());
        log.info("GetPasteBinActivity/getUserPasteBin: Retried PasteBin data by calling GetPasteBinService");

        log.info("GetPasteBinActivity/getUserPasteBin: Converting PasteBinAccessVaos to response body");
        List<PasteBinResponse> pasteBinResponse = pasteBinAccessVaos.stream()
                .map(pasteBinAccessVao -> PasteBinResponse.fromPasteBinAccessVao(pasteBinAccessVao))
                .collect(Collectors.toList());
        log.info("GetPasteBinActivity/getUserPasteBin: Finshing GET API Call for getting pasteBinIds data for userId : " + userId);
        return pasteBinResponse;
    }
}
