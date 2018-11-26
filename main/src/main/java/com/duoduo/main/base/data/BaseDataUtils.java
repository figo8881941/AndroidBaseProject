package com.duoduo.main.base.data;

import java.util.ArrayList;
import java.util.List;

public class BaseDataUtils {

    /**
     * 把商品列表转换成主题商品两行一列列表的方法
     *
     * @param targetList
     * @param sourceList
     * @return
     */
    public static List<TopicTwoProductListEntity> makeTopicTwoProductListEntitys(List<TopicTwoProductListEntity> targetList, List<ProductInfoEntity> sourceList) {
        if (targetList == null) {
            targetList = new ArrayList<TopicTwoProductListEntity>();
        }
        TopicTwoProductListEntity lastTopicTwoProductListEntity = null;
        if (targetList.size() > 0) {
            lastTopicTwoProductListEntity = targetList.get(targetList.size() - 1);
        }
        if (sourceList != null && !sourceList.isEmpty()) {
            int i = 0;
            if (lastTopicTwoProductListEntity != null && lastTopicTwoProductListEntity.getRightProductInfoEntity() == null) {
                lastTopicTwoProductListEntity.setRightProductInfoEntity(sourceList.get(0));
                i++;
            }
            TopicTwoProductListEntity topicTwoProductListEntity = new TopicTwoProductListEntity();
            int sourceSize = sourceList.size();
            for (int j = 0; i < sourceSize; j++, i++) {
                ProductInfoEntity entity = sourceList.get(i);
                if (j % 2 == 0) {
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
