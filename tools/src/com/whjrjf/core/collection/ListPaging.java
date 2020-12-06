package com.whjrjf.core.collection;

import java.util.Collections;
import java.util.List;

/**
 * 手动分页方法
 * @author zhangyT
 */
public class ListPaging {

    /**T
     * 参考来源:
     * https://www.cnblogs.com/theRhyme/p/10299144.html
     * https://blog.csdn.net/qq_33230584/article/details/81537291
     * https://www.cnblogs.com/zbcat/p/11690520.html
     */
    public List<List> paging(int cursor, int limit, List list){
        //手动实现分页
        if (cursor <= 0 || cursor > list.size() || limit <= 0) {
            return null;
        }
        int fromIndex=(cursor-1)*limit;
        int toIndex=(list.size()-fromIndex)>limit?fromIndex+limit:list.size();
        //获得分页后的list
        if(fromIndex<=toIndex){
            list = list.subList(fromIndex, toIndex);
        }else {
            list = Collections.emptyList();
        }
        return list;
    }

}
