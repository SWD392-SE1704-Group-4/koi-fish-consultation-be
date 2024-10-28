package com.fengshui.common.shared.Request.HeavenlyEarthlyModel;

import com.fengshui.common.shared.Model.PagingOption;
import com.fengshui.common.shared.Model.SortOption;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetHeavenlyEarthlyRequestModel implements Serializable {
    private String keyword;
    private PagingOption pagingOption;
    private SortOption sortOption;
}
