package org.potrohit.springboot.RegisterService.activity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmPasteBinRequest {
    public String user_id;
    public String pasteBin_id;
}
