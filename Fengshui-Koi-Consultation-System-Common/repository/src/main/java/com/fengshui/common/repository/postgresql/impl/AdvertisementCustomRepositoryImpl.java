package com.fengshui.common.repository.postgresql.impl;

import com.fengshui.common.repository.postgresql.IAdvertisementCustomRepository;
import com.fengshui.common.repository.postgresql.entities.AdvertisementEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AdvertisementCustomRepositoryImpl implements IAdvertisementCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<AdvertisementEntity> queryAdvertisement(String querySql, String keyword, List<UUID> adsTypes, Pageable pageable){
        Query query = entityManager.createNativeQuery(querySql, AdvertisementEntity.class);
        query.setParameter("keyword", "%" + keyword + "%");
        if (!adsTypes.isEmpty()) {
            query.setParameter("adsTypes", adsTypes);
        }
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // Fetch results
        List<AdvertisementEntity> resultList = query.getResultList();

        // Count query for total number of results
        String countSql = "SELECT COUNT(*) FROM (" + querySql + ") as countQuery";
        Query countQuery = entityManager.createNativeQuery(countSql);
        countQuery.setParameter("keyword", "%" + keyword + "%");
        if (!adsTypes.isEmpty()) {
            countQuery.setParameter("adsTypes", adsTypes);
        }
        long totalResults = ((Number) countQuery.getSingleResult()).longValue();

        return new PageImpl<>(resultList, pageable, totalResults).getContent();
    }
}
