package com.fengshui.common.shared.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PagingOption implements Serializable {
    int pageIndex;
    int pageTotal;
}
