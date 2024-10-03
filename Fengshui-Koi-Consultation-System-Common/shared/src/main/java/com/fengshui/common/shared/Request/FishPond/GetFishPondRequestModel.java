package com.fengshui.common.shared.Request.FishPond;

import com.fengshui.common.shared.Model.PagingOption;
import com.fengshui.common.shared.Model.SortOption;

import java.util.List;
import java.util.UUID;

public class GetFishPondRequestModel {
    String keyword;
    List<UUID> categoryIds;
    PagingOption pagingOption;
    SortOption sortOption;
}
