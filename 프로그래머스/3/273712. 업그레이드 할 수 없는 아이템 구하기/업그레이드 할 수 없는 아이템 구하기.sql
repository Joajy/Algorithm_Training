SELECT A.ITEM_ID, ITEM_NAME, RARITY 
  FROM ITEM_INFO A
     , ITEM_TREE B
 WHERE 1 = 1
   AND A.ITEM_ID = B.ITEM_ID
   AND A.ITEM_ID NOT IN (
                            SELECT PARENT_ITEM_ID
                              FROM ITEM_TREE
                             WHERE PARENT_ITEM_ID IS NOT NULL
                        )            -- 업그레이드 가능한 아이템 제외
 ORDER BY A.ITEM_ID DESC
 ;