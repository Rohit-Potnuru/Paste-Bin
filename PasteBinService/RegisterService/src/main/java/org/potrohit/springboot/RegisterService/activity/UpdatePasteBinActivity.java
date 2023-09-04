//package org.potrohit.springboot.RegisterService.activity;
//
//import org.potrohit.springboot.RegisterService.activity.common.RegisterPasteBinRequest;
//import org.potrohit.springboot.RegisterService.activity.common.PasteBinResponse;
//import org.potrohit.springboot.RegisterService.activity.common.RequestMappings;
//import org.potrohit.springboot.RegisterService.domain.bussinesslogic.UpdatePasteBinService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping
//public class UpdatePasteBinActivity {
//    @Autowired
//    private UpdatePasteBinService updatePasteBinService;
//
//    @RequestMapping(value = RequestMappings.REGISTER, method = RequestMethod.POST)
//    public @ResponseBody PasteBinResponse registerPasteBin(@RequestBody RegisterPasteBinRequest registerPasteBinRequest) {
//
//        return updatePasteBinService.registerPasteBin(registerPasteBinRequest);
//    }
//
//
//}
