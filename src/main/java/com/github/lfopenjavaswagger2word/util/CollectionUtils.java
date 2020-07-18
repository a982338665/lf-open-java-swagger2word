package com.github.lfopenjavaswagger2word.util;

import java.util.Collection;

/**
 * @author : Mr huangye
 * URL : CSDN 皇夜_
 * createTime : 2020/7/18 15:16
 * Description :
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.size()>0;
    }
}
