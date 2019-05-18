package com.x.mall.service;

import com.x.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * 商品品牌服务接口
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();
    int createBrand(PmsBrand brand);
    int updateBrand(Long id ,PmsBrand brand);
    int deleteBrand(Long id);
    List<PmsBrand> listBrand(int pageNum, int pageSize);
    PmsBrand getBrand(Long id);
}
