package org.potrohit.springboot.GetPasteBinService.domain.bussinesslogic.common;

import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinVao;
import org.springframework.stereotype.Component;

@Component
public class DefaultPasteBinVao {
    public PasteBinVao fillDefaultPasteBinVao(PasteBinVao pasteBinVao) {
        //userId (if null put empty), contentType (if null raise error), ExpireTime (if null, 1 year)
        return pasteBinVao;
    }
}
