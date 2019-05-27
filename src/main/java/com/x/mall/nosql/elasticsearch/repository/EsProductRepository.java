package com.x.mall.nosql.elasticsearch.repository;

import com.x.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品ES操作类
 *
 * 继承ElasticsearchRepository接口，这样就拥有了一些基本的Elasticsearch数据操作方法，同时定义了一个衍生查询方法。
 *
 * 在接口中直接指定查询方法名称便可查询，无需进行实现，如商品表中有商品名称、标题和关键字，直接定义以下查询，就可以对这三个字段进行全文搜索。
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct,Long> {
    /**
     * 搜索查询
     * @param name  商品名称
     * @param subTitle  商品标题
     * @param keywords  商品关键字
     * @param page  分页信息
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
