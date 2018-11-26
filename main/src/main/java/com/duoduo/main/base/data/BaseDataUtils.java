package com.duoduo.main.base.data;

import java.util.ArrayList;
import java.util.List;

public class BaseDataUtils {

    /**
     * 把商品列表转换成主题商品两行一列列表的方法
     * @param targetList
     * @param sourceList
     * @return
     */
    public static List<TopicTwoProductListEntity> makeTopicTwoProductListEntitys(List<TopicTwoProductListEntity> targetList, List<ProductInfoEntity> sourceList) {
        if (targetList == null) {
            targetList = new ArrayList<TopicTwoProductListEntity>();
        }
        if (sourceList != null && !sourceList.isEmpty()) {
            TopicTwoProductListEntity topicTwoProductListEntity = new TopicTwoProductListEntity();
            int sourceSize = sourceList.size();
            for (int i = 0; i < sourceSize; i++) {
                ProductInfoEntity entity = sourceList.get(i);
                if (i % 2 == 0) {
                    topicTwoProductListEntity.setLeftProductInfoEntity(entity);
                } else {
                    topicTwoProductListEntity.setRightProductInfoEntity(entity);
                    targetList.add(topicTwoProductListEntity);
                    topicTwoProductListEntity = new TopicTwoProductListEntity();
                }
            }
        }
        return targetList;
    }
}
