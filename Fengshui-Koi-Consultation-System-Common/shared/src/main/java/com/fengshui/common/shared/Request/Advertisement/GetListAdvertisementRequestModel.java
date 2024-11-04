package com.fengshui.common.shared.Request.Advertisement;

import com.fengshui.common.shared.Model.PagingOption;
import com.fengshui.common.shared.Model.SortOption;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GetListAdvertisementRequestModel {
    String keyword;
    List<UUID> adsTypes;
    String elementName;
    PagingOption pagingOption;
    SortOption sortOption;
}
