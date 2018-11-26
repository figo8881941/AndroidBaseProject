package com.duoduo.main.base.data;

/**
 * 一行两列商品主题列表数据实体
 */
public class TopicTwoProductListEntity {

    private ProductInfoEntity leftProductInfoEntity;

    private ProductInfoEntity rightProductInfoEntity;

    public ProductInfoEntity getLeftProductInfoEntity() {
        return leftProductInfoEntity;
    }

    public void setLeftProductInfoEntity(ProductInfoEntity leftProductInfoEntity) {
        this.leftProductInfoEntity = leftProductInfoEntity;
    }

    public ProductInfoEntity getRightProductInfoEntity() {
        return rightProductInfoEntity;
    }

    public void setRightProductInfoEntity(ProductInfoEntity rightProductInfoEntity) {
        this.rightProductInfoEntity = rightProductInfoEntity;
    }
}
