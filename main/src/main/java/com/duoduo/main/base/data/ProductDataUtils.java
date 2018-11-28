package com.duoduo.main.base.data;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;

import com.duoduo.commonbase.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductDataUtils {

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

    /**
     * 获取商品到手价格字符串的方法
     *
     * @param entity
     * @return
     */
    public static CharSequence getProductHandPriceString(ProductInfoEntity entity) {
        CharSequence handPriceString = entity.getHandPriceString();
        if (TextUtils.isEmpty(handPriceString)) {
            handPriceString = "¥" + NumberUtils.formatDoubleToScale(1, entity.getHandPrice());
            entity.setHandPriceString(handPriceString);
        }
        return handPriceString;
    }

    /**
     * 获取商品主题列表展示的到手价格字符串的方法
     *
     * @param entity
     * @return
     */
    public static CharSequence getProductTopicHandPriceString(ProductInfoEntity entity) {
        CharSequence handPriceString = entity.getHandPriceString();
        if (TextUtils.isEmpty(handPriceString)) {
            String originalString = "¥ " + NumberUtils.formatDoubleToScale(1, entity.getHandPrice());
            int dotIndex = originalString.indexOf(".");
            if (dotIndex >= 0) {
                SpannableString handPriceSpannableString = new SpannableString(originalString);
                AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(14, true);
                handPriceSpannableString.setSpan(absoluteSizeSpan, dotIndex + 1, originalString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                handPriceString = handPriceSpannableString;
            } else {
                handPriceString = originalString;
            }
            entity.setHandPriceString(handPriceString);
        }
        return handPriceString;
    }

    /**
     * 获取商品原最终价格字符串的方法
     *
     * @param entity
     * @return
     */
    public static String getProductFinalPriceString(ProductInfoEntity entity) {
        String finalPriceString = entity.getFinalPriceString();
        if (TextUtils.isEmpty(finalPriceString)) {
            finalPriceString = "¥" + NumberUtils.formatDoubleToScale(1, entity.getFinalPrice());
            entity.setFinalPriceString(finalPriceString);
        }
        return finalPriceString;
    }

    /**
     * 获取商品多少人已买字符串的方法
     *
     * @param entity
     * @return
     */
    public static String getProductSellAmountsString(ProductInfoEntity entity) {
        String sellAmountsString = entity.getSellAmountsString();
        try {
            if (TextUtils.isEmpty(sellAmountsString)) {
                String sellAmounts = entity.getSellAmounts();
                if (!TextUtils.isEmpty(sellAmounts)) {
                    int sellAmountsInt = Integer.parseInt(sellAmounts);
                    if (sellAmountsInt >= 10000) {
                        sellAmountsInt = sellAmountsInt / 10000;
                        sellAmountsString = sellAmountsInt + "万人已买";
                    } else {
                        sellAmountsString = sellAmountsInt + "人已买";
                    }
                }
                entity.setSellAmountsString(sellAmountsString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sellAmountsString;
    }

}
