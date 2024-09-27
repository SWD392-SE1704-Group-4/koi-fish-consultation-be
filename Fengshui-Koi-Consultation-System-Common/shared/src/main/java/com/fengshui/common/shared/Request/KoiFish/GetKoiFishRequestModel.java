package com.fengshui.common.shared.Request.KoiFish;

import com.fengshui.common.shared.Model.PagingOption;
import com.fengshui.common.shared.Model.SortOption;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GetKoiFishRequestModel implements Serializable {
    String keyword;
    List<UUID> categoryIds;
    PagingOption pagingOption;
    SortOption sortOption;
}
